package com.xidian.iot.datacenter.service.triger;

import com.xidian.iot.common.mq.MqSender;
import com.xidian.iot.common.util.JsonUtil;
import com.xidian.iot.database.entity.Node;
import com.xidian.iot.database.entity.NodeActCmd;
import com.xidian.iot.database.entity.NodeCmd;
import com.xidian.iot.database.entity.NodeCmdGroup;
import com.xidian.iot.database.entity.msg.QueueCommand;
import com.xidian.iot.databiz.service.NodeActCmdService;
import com.xidian.iot.databiz.service.NodeCmdGroupService;
import com.xidian.iot.databiz.service.NodeCmdService;
import com.xidian.iot.databiz.service.NodeService;
import com.xidian.iot.datacenter.service.BaseTask;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;

/**
 * @author mrl
 * @Title: SendCmdTask
 * @Package
 * @Description: 触发器被触发后，执行对应的命令    copy from langyan
 * @date 2020/9/10 7:21 下午
 */
@Slf4j
//@Component
public class SendCmdTask extends BaseTask implements Runnable {

    /**
     * 触发器ID。
     */
    @Setter
    private Long ntId;
    /**
     * 节点数据访问接口。
     */
    @Resource
    private NodeService nodeService;
    /**
     * 命令数据访问接口。
     */
    @Resource
    private NodeCmdService nodeCmdService;
    /**
     * 命令组数据访问接口。
     */
    @Resource
    private NodeCmdGroupService nodeCmdGroupService;
    /**
     * 命令数据访问接口。
     */
    @Resource
    private NodeActCmdService nodeActCmdService;

    /**
     * 消息队列客户端。
     */
     @Resource
     private MqSender mqSender;

    /**
     * 任务从这里开始。
     */
    @Override
    public void run() {
        // 查询触发器的命令列表
        for(NodeActCmd nodeActCmd : nodeActCmdService.getNodeActCmdByNtId(ntId)){
            NodeCmd nodeCmd = nodeCmdService.getNodeCmdById(nodeActCmd.getNcId());
            if(nodeCmd != null){
                NodeCmdGroup nodeCmdGroup = nodeCmdGroupService.getNodeCmdGroupById(nodeCmd.getNcgId());
                // 发送命令
                sendCommand(nodeCmd.getSceneSn(),
                        nodeCmd.getNodeSn(),
                        nodeCmdGroup.getNcgKey(),
                        nodeCmd.getNcContent());
            }
        }
    }

    /**
     * 执行触发器中的下行的命令，将命令组封装成对象放入队列中等待被处理
     * @param sceneSn
     * @param nodeSn
     * @param cmdKey
     * @param cmdStr
     */
    private void sendCommand(String sceneSn, String nodeSn, String cmdKey, String cmdStr) {
        // 创建一条队列命令。
        QueueCommand queueCommand = new QueueCommand();
        queueCommand.setSceneSn(sceneSn);
        queueCommand.setNodeSn(nodeSn);
        // 添加下发命令
        queueCommand.addCommand(cmdKey, cmdStr);
        // 将对队列命令添加到队列中
        try {
            log.debug("Add to queue '[{}]',{}", QueueCommand.QUEUE_NAME,
                    queueCommand);
            // kestrelClient.set(QueueCommand.QUEUE_NAME, 0, queueCommand);
            mqSender.send(QueueCommand.QUEUE_NAME, JsonUtil.toJson(queueCommand));
        } catch (Exception e) {
            log.error("kestrel client set() error.", e);
        }
    }
}