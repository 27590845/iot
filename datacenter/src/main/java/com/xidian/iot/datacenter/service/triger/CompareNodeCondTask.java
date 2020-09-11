package com.xidian.iot.datacenter.service.triger;

import com.xidian.iot.database.entity.Node;
import com.xidian.iot.database.entity.NodeAttr;
import com.xidian.iot.database.entity.NodeCond;
import com.xidian.iot.database.entity.NodeTrig;
import com.xidian.iot.datacenter.service.BaseTask;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.math.NumberUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

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
    private TrigService trigService;
    /**
     * 触发条件列表
     */
    @Setter
    private List<NodeCond> nodeCondList;
    /**
     * 节点属性数据访问接口。
     */
    @Resource
    private NodeAttrDao nodeAttrDao;
    /**
     * 产品属性数据访问接口。
     */
    @Resource
    private ProductAttrDao productAttrDao;
    /**
     * 节点数据访问接口。
     */
    @Resource
    private NodeDao nodeDao;
    /**
     * 节点触发器访问接口
     */
    @Resource
    private NodeTrigDao nodeTrigDao;

    /**
     * 任务从这里开始。
     */
    @Override
    public void run() {

        // 当条件或数据为null或空，则不进行比较
        if (CollectionUtils.isEmpty(nodeCondList)
                || MapUtils.isEmpty(data)) {
            return;
        }

        log.info("doing CompareNodeCondTask");
        log.info("data[{}]", data);

        // 处理触发器条件
        for (NodeCond nodeCond : nodeCondList) {

            log.info("CompareNodeCondTask foreach nodeCondList,nodeCond=[{}]", nodeCond);

            // 获得属性key
            String attrKey = hasKey(nodeCond);
            if (attrKey == null) {
                continue;
            }

            // 间隔时间外才能正常进行条件比较
            if (outIntervalTime(nodeCond)) {
                log.info("this condition out IntervalTime.");
                // 比较条件，并检查触发器
                compareCondition(nodeCond, attrKey);
            }
        }

    }

    /**
     * 判断条件的触发器是否处于间隔时间外
     *
     * @param nodeCond
     * @return
     */
    private boolean outIntervalTime(NodeCond nodeCond) {

        NodeTrig nodeTrig = nodeTrigDao
                .getNodeTrigById(new NodeTrig(nodeCond.getNtId()));

        if (nodeTrig.getLastRunTime() != null) {

            // 计算间隔时间
            long time = nodeTrig.getLastRunTime().getTime();
            time += (nodeTrig.getIntervalTime() * 1000);

            // 若当前时间没有超出间隔时间，则返回false代表还在间隔时间内
            if (time > System.currentTimeMillis()) {
                return false;
            }
        }

        return true;
    }

    /**
     * 上述数据是否包含比较的属性key
     *
     * @param nodeCond
     * @return null 不包含这个key,not null 返回属性key.
     */
    private String hasKey(NodeCond nodeCond) {
        // 获得节点
        Node node = nodeDao.getNodeBySceneSnNodeSn(new Node(nodeCond.getSceneSn(),nodeCond.getNodeSn()));

        // 判断产品是否是自由产品
        String attrKey = null;
        if (node.isProductNode()) {
            // 获得产品属性
            ProductAttr productAttr = productAttrDao.getProductAttrById(new ProductAttr(nodeCond.getNaId()));
            attrKey = productAttr.getAttrKey();
            log.info("get product attr {}", productAttr);
        } else {
            // 获得节点属性
            NodeAttr nodeAttr = nodeAttrDao.getNodeAttrById(new NodeAttr(nodeCond.getNaId()));
            attrKey = nodeAttr.getAttrKey();
            log.info("get node attr {}", nodeAttr);
        }

        // 判断此次上数数据不包含此属性则不做操作
        if (!data.containsKey(attrKey)) {
            log.info("Compare not contains key {},{}", attrKey, data);
            return null;
        }

        return attrKey;
    }

    /**
     * 比较条件，看是否满足触发需求
     *
     * @param nodeCond
     * @param attrKey
     */
    private void compareCondition(NodeCond nodeCond, String attrKey) {
        // 判断这个条件是否满足
        boolean meet = isMeetCondition(nodeCond, attrKey);
        log.info("compareCondition() meet={}", meet);

        // 判断如果发生了变化，才执行更新、检查触发器操作
        if (meet != nodeCond.isMeet()) {

            // 更新变化后的meet值，并更新到缓存
            nodeCond.setMeet(meet);
            trigService.changeCondition(nodeCond);

            // 发生变化，且是满足条件的就执行触发器检查
            // 因为条件不满足是不触发检查的
            if (meet) {
                log.debug("doCheckingTrigTask() nodeCond=({})", nodeCond);
                // 执行触发器检查任务，检查触发器所有条件
                doCheckingTrigTask(nodeCond.getNtId());
            }
        } else {
            // 更新满足次数
            trigService.changeCondition(nodeCond);
        }
    }

    /**
     * 比较某一触发器条件是否得到满足。
     *
     * @param nodeCond
     *            触发器条件
     * @return true 条件被满足，false 条件不满足
     */
    private boolean isMeetCondition(NodeCond nodeCond, String attrKey) {
        // 获得上数值
        Double value = toDoubleValue(data.get(attrKey));
        // 当上数值不合法则此条件不满足
        if (value == null) {
            log.info("isMeetCondition() value is null.");
            return false;
        }
        // 将当前值保存，用于SendMessageTask拼接站内信时发送currentValue.
        nodeCond.setCurrentValue(value);
        // 与条件进行比较，看是否能满足
        if (nodeCond.compare(value)) {
            // 当查出条件
            // 计算满足次数
            int currentMeetCount = nodeCond.getCurrentMeetCount() + 1;
            nodeCond.setCurrentMeetCount(currentMeetCount);
            // 当次数达到要求，则此条件为满足
            if (currentMeetCount == nodeCond.getMeetCount()) {
                log.info("isMeetCondition() meet count==currentMeetCount.");
                return true;
            }
        } else {
            // 为保证条件是连续满足的，所以一旦有一次不满足，计数应被清零。
            nodeCond.setCurrentMeetCount(0);
        }

        log.info("isMeetCondition() return false.");
        return false;
    }

    /**
     * 将上数值转换为双精度型
     *
     * @param value
     *            上数值
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
     *
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
