package com.xidian.iot.datacenter.service.triger;

import com.xidian.iot.database.entity.NodeCmd;
import com.xidian.iot.database.entity.NodeCmdGroup;
import com.xidian.iot.datacenter.service.BaseTask;
import lombok.Setter;

import javax.annotation.Resource;
import java.util.concurrent.TimeoutException;

/**
 * @author mrl
 * @Title: SendCommandTask
 * @Package
 * @Description: 触发器被触发后，执行对应的命令    copy from langyan
 * @date 2020/9/10 7:21 下午
 */
public class SendCommandTask extends BaseTask implements Runnable {

    /**
     * 触发器ID。
     */
    @Setter
    private Long ntId;
    /**
     * 节点数据访问接口。
     */
    @Resource
    private NodeDao nodeDao;
    /**
     * 场景数据访问接口。
     */
    @Resource
    private SceneDao sceneDao;
    /**
     * 命令数据访问接口。
     */
    @Resource
    private NodeCmdDao nodeCmdDao;
    /**
     * 命令组数据访问接口。
     */
    @Resource
    private NodeCmdGroupDao nodeCmdGroupDao;
    /**
     * 命令数据访问接口。
     */
    @Resource
    private ProductCommandDao productCommandDao;
    /**
     * 命令组数据访问接口。
     */
    @Resource
    private ProductCommandGroupDao productCommandGroupDao;
    /**
     * 命令数据访问接口。
     */
    @Resource
    private NodeActionCommandDao nodeActionCommandDao;

    /**
     * 消息队列客户端。
     */
    // @Resource
    // private XMemcachedClient kestrelClient;

    /**
     * 任务从这里开始。
     */
    @Override
    public void run() {

        // 查询触发器的命令列表
        for (Long naaId : nodeActionCommandDao
                .getNacIdListByNtId(new NacIdListByNtId(ntId))) {

            NodeActionCommand nodeActionCommand = nodeActionCommandDao
                    .getNodeActionCommandById(new NodeActionCommand(naaId));

            // 获得节点
            Node node = nodeDao.getNodeBySceneSnNodeSn(new Node(
                    nodeActionCommand.getSceneSn(), nodeActionCommand
                    .getNodeSn()));

            // 判断产品是否是自由产品
            if (node.isProductNode()) {
                // 获得产品命令
                ProductCommand productCommand = productCommandDao
                        .getProductCommandById(new ProductCommand(
                                nodeActionCommand.getNcId()));
                if (productCommand != null) {

                    ProductCommandGroup productCommandGroup = productCommandGroupDao
                            .getProductCommandGroupById(new ProductCommandGroup(
                                    productCommand.getPcgId()));

                    // 发送命令
                    sendCommand(nodeActionCommand.getSceneSn(),
                            nodeActionCommand.getNodeSn(),
                            productCommandGroup.getCmdKey(),
                            productCommand.getCmdStr());
                }
            } else {
                // 获得节点命令
                NodeCmd nodeCmd = nodeCmdDao
                        .getNodeCmdById(new NodeCmd(nodeActionCommand
                                .getNcId()));
                if (nodeCmd != null) {

                    NodeCmdGroup nodeCmdGroup = nodeCmdGroupDao
                            .getNodeCmdGroupById(new NodeCmdGroup(
                                    nodeCmd.getNcgId()));

                    // 发送命令
                    sendCommand(nodeActionCommand.getSceneSn(),
                            nodeActionCommand.getNodeSn(),
                            nodeCmdGroup.getCmdKey(),
                            nodeCmd.getCmdStr());
                }
            }
        }

    }

    /**
     * 执行触发器中的下行的命令，将命令组封装成对象放入队列中等待被处理。
     *
     * @param nodeTrigger
     *            此次触发的节点触发器。
     * @param nodeSn
     *            节点序列号。
     */
    private void sendCommand(String sceneSn, String nodeSn, String cmdKey,
                             String cmdStr) {

        // 创建一条队列命令。
        QueueCommand queueCommand = new QueueCommand();
        queueCommand.setSceneSn(sceneSn);
        queueCommand.setNodeSn(nodeSn);
        // 添加下发命令
        queueCommand.addCommand(cmdKey, cmdStr);
        // 将对队列命令添加到队列中
        try {
            log.info("Add to queue '[{}]',{}", QueueCommand.QUEUE_NAME,
                    queueCommand);
            // kestrelClient.set(QueueCommand.QUEUE_NAME, 0, queueCommand);
            com.thingslink.commons.jms.ActiveMqClient.sendMessage(
                    QueueCommand.QUEUE_NAME, JsonUtil.toJson(queueCommand));
        } catch (TimeoutException e) {
            log.error("kestrel client set() error.", e);
        } catch (InterruptedException e) {
            log.error("kestrel client set() error.", e);
        } catch (Exception e) {
            log.error("kestrel client set() error.", e);
        }
    }
}