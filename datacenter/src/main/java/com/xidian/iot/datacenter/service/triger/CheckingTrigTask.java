package com.xidian.iot.datacenter.service.triger;

import com.xidian.iot.database.entity.NodeCond;
import com.xidian.iot.database.entity.NodeTrig;
import com.xidian.iot.datacenter.service.BaseTask;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author mrl
 * @Title: CheckingTrigTask
 * @Package
 * @Description: 检查是否触发某一触发器，根据触发器的条件是否都满足，如果都满足则交由{@link SendMessageTask}进行消息发送    copy from langyan
 * @date 2020/9/10 5:53 下午
 */
@Slf4j
public class CheckingTrigTask extends BaseTask implements Runnable {

    /**
     * 触发器ID。
     */
    @Setter
    private Long ntId;
    /**
     * 触发器服务类
     */
    @Resource
    private TrigService trigService;
    /**
     * 节点触发器条件数据访问接口。
     */
    @Resource
    private NodeCondDao nodeCondDao;
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

        // 获得触发器条件
        List<NodeCond> nodeCondList = getNodeCondList();

        log.info("doing CheckingTrigTask ntId=[{}]", ntId);
        log.info("Node trig have condition [{}]", nodeCondList);

        // 判断是否出发这个触发器
        if (isTrig(nodeCondList)) {
            // 执行发送消息的任务
            doSendMessageTask(nodeCondList);
            // 执行发送命令的任务
            doSendCommandTask();
            // 设置更新最后运行时间及是否需要继续执行
            updateLastRunTime();
            // 复位触发器条件
            reset();
        }
    }

    /**
     * 更新触发器最后运行时间及是否需要继续执行
     */
    private void updateLastRunTime() {
        NodeTrig nodeTrig = nodeTrigDao.getNodeTrigById(new NodeTrig(ntId));
        nodeTrig.setLastRunTime(new Date());
        if(nodeTrig.getRepeated()==1){		//如触发器不重复执行则将executed置为1（不再执行）
            nodeTrig.setExecuted(1);
        }
        nodeTrigDao.updateNodeTrig(nodeTrig);
    }

    /**
     * 执行发送命令的任务
     */
    private void doSendCommandTask() {
        SendCommandTask sendCommandTask = (SendCommandTask) applicationContext.getBean("sendCommandTask");
        // 设置触发器ID
        sendCommandTask.setNtId(ntId);
        sendCommandTask.run();
//		taskExecutor.execute(sendCommandTask);
    }

    /**
     * 执行发送消息的任务
     */
    private void doSendMessageTask(List<NodeCond> nodeCondList) {
        SendMessageTask sendMessageTask = (SendMessageTask) applicationContext.getBean("sendMessageTask");
        // 设置触发器ID
        sendMessageTask.setNtId(ntId);
        // 设置触发条件
        sendMessageTask.setNodeCondList(nodeCondList);
        sendMessageTask.run();
//		taskExecutor.execute(sendMessageTask);
    }

    /**
     * 重置触发器条件
     */
    private void reset() {
        for (NodeCond nodeCond : getNodeCondList()) {
            nodeCond.reset();
            trigService.changeCondition(nodeCond);
        }
    }

    /**
     * 判断触发器的所有条件是否都满足。
     *
     * @param nodeCondList
     *            触发条件列表。
     * @return true 触发，false 不触发
     */
    private boolean isTrig(List<NodeCond> nodeCondList) {

        // 如果一个条件都没有，返回false.
        if (CollectionUtils.isEmpty(nodeCondList)) {
            log.info("isTrig() nodeCondList is empty.return false;");
            return false;
        }

        for (NodeCond nodeCond : nodeCondList) {
            if (nodeCond.isMeet()) {
                log.info("nodeCond is meet,id is ({})", nodeCond.getNcId());
                continue;
            }
            log.info("nodeCond is not meet,id is ({})", nodeCond.getNcId());
            // 任何一个条件不满足，不能触发
            return false;
        }

        log.info("isTrig() return true;this nodeTrig id is ({})", ntId);
        return true;
    }

    /**
     * 获得触发器条件列表
     *
     * @return 返回{@link #ntId}的条件列表
     */
    private List<NodeCond> getNodeCondList() {
        // 获得触发器条件列表
        List<NodeCond> nodeCondList = new ArrayList<NodeCond>();
        for (Long ncId : nodeCondDao.getNcIdListByNtId(new NcIdListByNtId(ntId))) {
            nodeCondList.add(nodeCondDao.getNodeCondById(new NodeCond(ncId)));
        }
        return nodeCondList;
    }

}
