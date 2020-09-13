package com.xidian.iot.datacenter.service.triger;

import com.xidian.iot.database.entity.NodeAttr;
import com.xidian.iot.database.entity.custom.NodeCondExt;
import com.xidian.iot.database.entity.custom.NodeTrigExt;
import com.xidian.iot.databiz.service.NodeAttrService;
import com.xidian.iot.databiz.service.NodeCondService;
import com.xidian.iot.databiz.service.NodeService;
import com.xidian.iot.databiz.service.NodeTrigService;
import com.xidian.iot.datacenter.service.BaseTask;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.math.NumberUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 触发规则，对nodeCondExtList中每个元素执行如下操作：
 * 1. 判断该条件关联的nodeAttr是否存在，否则跳过本次循环
 * 2. 当前时间与上次判断该条件的时间差，是否超过nodeTrig.ntInvl，否则跳过本次循环
 * 3. 该条件在数值上是否满足，是则nodeCondExt.currentFitCount加一，否则归零
 * 4. 当前条件是否满足nodeCondExt.currentFitCount>=nodeCondExt.ncFitTime，是则本次条件满足，否则不满足
 * 5. 当前条件的满足状态是否和上次判断该条件得出的满足状态相异，是，并且条件满足，则进入下一个任务
 * 6. 更新缓存中的nodeCondExt
 */

/**
 * @author mrl
 * @Title: CompareNodeCondTask
 * @Package
 * @Description: 比较触发器条件，处理节点上数的数据与条件是否匹配，若是匹配上，将交由{@link CheckingTrigTask}
 * @date 2020/9/10 5:38 下午
 */
@Slf4j
public class CompareNodeCondTask extends BaseTask implements Runnable {

    /**
     * 上数数据
     */
    @Setter
    private Map<String, Object> data;
    /**
     * 触发器服务类
     */
    @Resource
    private NodeTrigService nodeTrigService;
    /**
     * 触发器条件服务
     */
    @Resource
    private NodeCondService nodeCondService;
    /**
     * 触发条件列表
     */
    @Setter
    private List<NodeCondExt> nodeCondExtList;
    /**
     * 节点属性数据访问接口。
     */
    @Resource
    private NodeAttrService nodeAttrService;

    /**
     * 任务从这里开始。
     */
    @Override
    public void run() {
        // 当条件或数据为null或空，则不进行比较
        if (CollectionUtils.isEmpty(nodeCondExtList)
                || MapUtils.isEmpty(data)) {
            return;
        }
        log.info("doing CompareNodeCondTask");
        log.info("data[{}]", data);
        // 处理触发器条件
        for (NodeCondExt nodeCondExt : nodeCondExtList) {
            log.info("CompareNodeCondTask foreach nodeCondList,nodeCondExt=[{}]", nodeCondExt);
            // 获得属性key
            String attrKey = hasKey(nodeCondExt);
            if (attrKey == null) {
                continue;
            }
            // 间隔时间外才能正常进行条件比较
            if (outIntervalTime(nodeCondExt)) {
                log.info("this condition out IntervalTime.");
                // 比较条件，并检查触发器
                compareCondition(nodeCondExt, attrKey);
            }
        }
    }

    /**
     * 判断条件的触发器是否处于间隔时间外
     * @param nodeCondExt
     * @return
     */
    private boolean outIntervalTime(NodeCondExt nodeCondExt) {
        NodeTrigExt nodeTrigExt = nodeTrigService.getNodeTrigExtById(nodeCondExt.getNtId());
        if (nodeTrigExt.getLastRunTime() != null) {
            // 计算间隔时间
            long time = nodeTrigExt.getLastRunTime().getTime();
            time += (nodeTrigExt.getNtInvl() * 1000);
            // 若当前时间没有超出间隔时间，则返回false代表还在间隔时间内
            if (time > System.currentTimeMillis()) {
                return false;
            }
        }
        return true;
    }

    /**
     * 上述数据是否包含比较的属性key
     * @param nodeCondExt
     * @return null 不包含这个key,not null 返回属性key.
     */
    private String hasKey(NodeCondExt nodeCondExt) {
        // 获得节点属性
        NodeAttr nodeAttr = nodeAttrService.getNodeAttrById(nodeCondExt.getNaId());
        String attrKey = nodeAttr.getNaKey();
        log.info("get node attr {}", nodeAttr);
        // 判断此次上数数据不包含此属性则不做操作
        if (!data.containsKey(attrKey)) {
            log.info("Compare not contains key {},{}", attrKey, data);
            return null;
        }
        return attrKey;
    }

    /**
     * 比较条件，看是否满足触发需求
     * @param nodeCondExt
     * @param attrKey
     */
    private void compareCondition(NodeCondExt nodeCondExt, String attrKey) {
        // 判断这个条件是否满足
        boolean fit = isFitCondition(nodeCondExt, attrKey);
        log.info("compareCondition() fit={}", fit);
        // 判断如果发生了变化，才执行更新、检查触发器操作z
        if (fit != nodeCondExt.isFit()) {
            // 更新变化后的fit值，并更新到缓存
            nodeCondExt.setFit(fit);
            nodeCondService.updateNodeCondExt(nodeCondExt);
            // 发生变化，且是满足条件的就执行触发器检查
            // 因为条件不满足是不触发检查的
            if (fit) {
                log.debug("doCheckingTrigTask() nodeCondExt=({})", nodeCondExt);
                // 执行触发器检查任务，检查触发器所有条件
                doCheckingTrigTask(nodeCondExt.getNtId());
            }
        } else {
            // 更新满足次数
            nodeCondService.updateNodeCondExt(nodeCondExt);
        }
    }

    /**
     * 比较某一触发器条件是否得到满足。
     * @param nodeCondExt 触发器条件
     * @return true 条件被满足，false 条件不满足
     */
    private boolean isFitCondition(NodeCondExt nodeCondExt, String attrKey) {
        // 获得上数值
        Double value = toDoubleValue(data.get(attrKey));
        // 当上数值不合法则此条件不满足
        if (value == null) {
            log.info("isFitCondition() value is null.");
            return false;
        }
        // 将当前值保存，用于SendMessageTask拼接站内信时发送currentValue.
        nodeCondExt.setCurrentValue(value);
        // 与条件进行比较，看是否能满足
        if (nodeCondExt.compare(value)) {
            // 当查出条件
            // 计算满足次数
            int currentFitCount = nodeCondExt.getCurrentFitCount() + 1;
            nodeCondExt.setCurrentFitCount(currentFitCount);
            // 当次数达到要求，则此条件为满足
            if (currentFitCount == nodeCondExt.getNcFitTime()) {
                log.info("isFitCondition() fit count==currentFitCount.");
                return true;
            }
        } else {
            // 为保证条件是连续满足的，所以一旦有一次不满足，计数应被清零。
            nodeCondExt.setCurrentFitCount(0);
        }

        log.info("isFitCondition() return false.");
        return false;
    }

    /**
     * 将上数值转换为双精度型
     * @param value 上数值
     * @return double类型上数值
     */
    private Double toDoubleValue(Object value) {

        if (value instanceof String) {
            if (NumberUtils.isNumber(value.toString()))
                return NumberUtils.toDouble(value.toString());
        } else if (value instanceof Integer) {
            return NumberUtils.toDouble(value.toString());
        } else if (value instanceof Double) {
            return (Double) value;
        }

        return null;
    }

    /**
     * 执行检查触发器所有条件的任务
     * @param ntId
     */
    private void doCheckingTrigTask(Long ntId) {
        CheckingTrigTask checkingTrigTask = (CheckingTrigTask) applicationContext
                .getBean("checkingTrigTask");
        // 设置触发器ID
        checkingTrigTask.setNtId(ntId);
//        checkingTrigTask.run();
		taskExecutor.execute(checkingTrigTask);
    }

}
