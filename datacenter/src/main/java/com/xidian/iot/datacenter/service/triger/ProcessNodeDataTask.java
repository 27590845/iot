package com.xidian.iot.datacenter.service.triger;

import com.xidian.iot.common.mq.MqSender;
import com.xidian.iot.database.entity.NodeCond;
import com.xidian.iot.database.entity.NodeTrig;
import com.xidian.iot.database.entity.Scene;
import com.xidian.iot.database.entity.mongo.NodeData;
import com.xidian.iot.databiz.service.NodeCondService;
import com.xidian.iot.datacenter.service.BaseTask;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author mrl
 * @Title: ProcessNodeDataTask
 * @Package
 * @Description: 处理上传数据的task，将每个节点关系到的触发条件查询出来，并交给{@link CompareNodeCondTask}    copy from langyan
 * @date 2020/9/10 5:19 下午
 */
@Slf4j
public class ProcessNodeDataTask extends BaseTask implements Runnable {

    /**
     * 此次上数的节点数据。
     */
    @Setter
    private List<NodeData> nodeDataList;
    /**
     * 此次上数的场景
     */
    @Setter
    private Scene scene;
    /**
     * 触发器条件数据访问接口。
     */
    @Resource
    private NodeCondService nodeCondService;
    /**
     * 手机推送服务
     */
    @Resource
    private MqSender mqttSender;

    /**
     * 任务从这里开始。
     */
    @Override
    public void run() {
        long ss = System.currentTimeMillis();
        // 处理节点数据
        processNodeData();
        log.debug("processNodeData:{}ms",System.currentTimeMillis() - ss);
    }

    /**
     * 处理节点数据，并检测是否有数据符合触发器条件
     * modify by mrl
     */
    private void processNodeData() {
        // 逐个处理上行数据
        for (NodeData nodeData : nodeDataList) {
            log.info("nodeData({})", nodeData);
            //只获取条件所关联的触发器未失效的节点条件
            List<NodeCond> nodeCondListResult = nodeCondService.getByNodeSnAvl(nodeData.getNodeSn());
            //执行条件比较任务
            doCompareNodeCondTask(nodeData, nodeCondListResult);
        }
    }

    /**
     * 执行比较触发条件的任务
     * @param nodeData 上传数据
     * @param nodeCondList 条件列表
     */
    private void doCompareNodeCondTask(NodeData nodeData, List<NodeCond> nodeCondList) {
        CompareNodeCondTask compareNodeCondTask = (CompareNodeCondTask) applicationContext.getBean("compareNodeCondTask");
        // 设置节点数据
        compareNodeCondTask.setData(nodeData.getData());
        // 设置条件列表
        compareNodeCondTask.setNodeCondList(nodeCondList);
//        compareNodeCondTask.run();
		taskExecutor.execute(compareNodeCondTask);
    }

    /**
     * 产生微信规则
     * @param nodeData
     */
//	private void doCreateWeixinRuleTask(NodeData nodeData){
//		CreateWeixinRuleTask createWeixinRuleTask = (CreateWeixinRuleTask) applicationContext.getBean("createWeixinRuleTask");
//		createWeixinRuleTask.setNodeData(nodeData);
//		createWeixinRuleTask.run();
//	}
}