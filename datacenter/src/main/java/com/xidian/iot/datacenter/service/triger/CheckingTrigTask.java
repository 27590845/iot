package com.xidian.iot.datacenter.service.triger;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.xidian.iot.common.alert.alertsender.AlertFactory;
import com.xidian.iot.common.mq.MqSender;
import com.xidian.iot.database.entity.NodeActAlert;
import com.xidian.iot.database.entity.custom.NodeCondExt;
import com.xidian.iot.database.entity.custom.NodeTrigExt;
import com.xidian.iot.datacenter.service.BaseTask;
import com.xidian.iot.datacenter.service.CommonService;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author mrl
 * @Title: CheckingTrigTask
 * @Package
 * @Description: 检查是否触发某一触发器，根据触发器的条件是否都满足，如果都满足则交由{@link SendCmdTask}进行发送命令    copy from langyan
 * @date 2020/9/10 5:53 下午
 */
@Slf4j
@Component
public class CheckingTrigTask extends BaseTask implements Runnable {

    /**
     * 触发器ID。
     */
    @Setter
    private Long ntId;

    @Resource
    private CommonService commonService;

    @Resource
    private AlertFactory alertFactory;

    @Resource
    private MqSender mqSender;

    /**
     * 任务从这里开始。
     */
    @Override
    public void run() {

        // 获得触发器条件
        List<NodeCondExt> nodeCondExtList = commonService.getNodeCondExts(ntId);

        log.debug("doing CheckingTrigTask ntId=[{}]", ntId);
        log.debug("Node trig have condition [{}]", nodeCondExtList);

        // 判断是否出发这个触发器
        if (isTrig(nodeCondExtList)) {
            // 执行发送消息的任务
            doSendMessageTask(ntId);
            // 执行发送命令的任务
//            doSendCommandTask();
            // 设置更新最后运行时间及是否需要继续执行
            updateLastRunTime();
            // 复位触发器条件
            reset(nodeCondExtList);
            log.info("\t====>>触发报警  ntId={}", ntId);
        }
        log.debug("------process completed-------");
    }

    /**
     * 更新触发器最后运行时间及是否需要继续执行
     */
    private void updateLastRunTime() {
        NodeTrigExt nodeTrigExt = commonService.getNodeTrigExt(ntId);
        nodeTrigExt.setLastRunTime(new Date());
        if(nodeTrigExt.getNtRept()==1){		//如触发器不重复执行则将executed置为1（不再执行）
            nodeTrigExt.setNtExec((byte) 1);
        }
        commonService.updateNodeTrigExtById(nodeTrigExt);
    }

    /**
     * 执行发送命令的任务
     */
    private void doSendCommandTask() {
        SendCmdTask sendCmdTask = (SendCmdTask) applicationContext.getBean("sendCmdTask");
        // 设置触发器ID
        sendCmdTask.setNtId(ntId);
        sendCmdTask.run();
//		taskExecutor.execute(sendCmdTask);
    }

    /**
     * 执行发送消息的任务
     */
    private void doSendMessageTask(Long ntId){
        List<NodeActAlert> nodeActAlerts = commonService.getNodeActAlerts(ntId);
        for (int i = 0; i < nodeActAlerts.size(); i++) {
            NodeActAlert nodeActAlert = nodeActAlerts.get(i);
            alertFactory.getAlert(nodeActAlert.getNaaType(),nodeActAlert.getNaaVal(),nodeActAlert.getNaaContent());
        }
        // 触发一次就发送消息队列、内容有时间、ntId
        JSONObject alert = new JSONObject();
        alert.put("ntId",ntId);
        alert.put("time",new Date());
        try {
            mqSender.sendTopic("Alert",alert.toString());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            log.error("触发发送消息失败");
        }
    }
//    private void doSendMessageTask(List<NodeCondExt> nodeCondExtList) {
//        SendMessageTask sendMessageTask = (SendMessageTask) applicationContext.getBean("sendMessageTask");
//        // 设置触发器ID
//        sendMessageTask.setNtId(ntId);
//        // 设置触发条件
//        sendMessageTask.setNodeCondExtList(nodeCondExtList);
//        sendMessageTask.run();
////		taskExecutor.execute(sendMessageTask);
//    }

    /**
     * 重置触发器条件
     */
    private void reset(List<NodeCondExt> nodeCondExtList) {
        for (NodeCondExt nodeCondExt : nodeCondExtList) {
            nodeCondExt.reset();
            commonService.changeNodeCondExt(nodeCondExt);
        }
    }

    /**
     * 判断触发器的所有条件是否都满足。
     * @param nodeCondExtList 触发条件列表。
     * @return true 触发，false 不触发
     */
    private boolean isTrig(List<NodeCondExt> nodeCondExtList) {
        // 如果一个条件都没有，返回false.
        if (CollectionUtils.isEmpty(nodeCondExtList)) {
            log.info("isTrig() nodeCondExtList is empty.return false;");
            return false;
        }
        for (NodeCondExt nodeCondExt : nodeCondExtList) {
            if (nodeCondExt.isFit()) {
                log.debug("nodeCondExt is meet,id is ({})", nodeCondExt.getNcId());
                continue;
            }
            log.debug("nodeCondExt is not meet,id is ({})", nodeCondExt.getNcId());
            // 任何一个条件不满足，不能触发
            return false;
        }
        log.debug("isTrig() return true;this nodeTrig id is ({})", ntId);
        return true;
    }
}
