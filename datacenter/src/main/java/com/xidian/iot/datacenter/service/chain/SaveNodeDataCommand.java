package com.xidian.iot.datacenter.service.chain;

import com.xidian.iot.database.entity.Scene;
import com.xidian.iot.database.entity.mongo.NodeData;
import com.xidian.iot.databiz.service.NodeDataService;
import com.xidian.iot.datacenter.service.triger.ProcessNodeDataTask;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.chain.Command;
import org.apache.commons.chain.Context;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.task.TaskExecutor;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author mrl
 * @Title: SaveNodeDataCommand
 * @Package
 * @Description: 存储节点数据    copy from langyan
 * @date 2020/9/10 5:12 下午
 */
@Slf4j
public class SaveNodeDataCommand implements Command, ApplicationContextAware {

    /**
     * 节点数据访问接口。
     */
    @Resource
    private NodeDataService nodeDataService;
    /**
     * 线程池引用
     */
    @Resource
    private TaskExecutor taskExecutor;

    /**
     * Spring上下文，因为获得task时得到的非单例，为保证线程安全直接从上下文中获得bean对象。
     */
    private ApplicationContext applicationContext;

    @Override
    public boolean execute(Context context) throws Exception {

        BaseContext upContext = (BaseContext) context;

        // 获得节点数据
        List<NodeData> nodeDataList = upContext.getNodeDataList();

        // 保存节点数据
        log.info("================================Start Saving nodeDataList.[{}]", nodeDataList);
        nodeDataService.addNodeData(nodeDataList);
        log.info("================================Complete Saved Node Data.");

        // 执行触发器
        doProcessNodeDataTask(upContext.getSceneSn(), nodeDataList);

        // 到此责任链完成
        return true;
    }

    /**
     * 执行一个节点的触发器任务
     * @param sceneSn
     * @param nodeDataList
     */
    public void doProcessNodeDataTask(String sceneSn, List<NodeData> nodeDataList) {
        ProcessNodeDataTask processNodeDataTask = (ProcessNodeDataTask) applicationContext.getBean("processNodeDataTask");
        // 设置节点数据列表
        processNodeDataTask.setNodeDataList(nodeDataList);

        taskExecutor.execute(processNodeDataTask);
//        processNodeDataTask.run();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
