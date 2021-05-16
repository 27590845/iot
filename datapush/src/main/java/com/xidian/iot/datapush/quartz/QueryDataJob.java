package com.xidian.iot.datapush.quartz;


import com.alibaba.fastjson.JSONObject;
import com.xidian.iot.database.entity.mongo.NodeData;
import com.xidian.iot.databiz.service.NodeService;
import com.xidian.iot.databiz.service.SceneService;
import com.xidian.iot.datapush.socket.Constant;
import com.xidian.iot.datapush.socket.NodeSocketHandler;
import com.xidian.iot.datapush.socket.SceneSocketHandler;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.web.socket.TextMessage;
import org.slf4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;


import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: Hansey
 * @date: 2021-04-1 11:16 上午
 */
public class QueryDataJob extends QuartzJobBean {

    private final static Logger logger = LoggerFactory.getLogger(QueryDataJob.class);


    @Autowired
    private SceneService sceneService;

    @Autowired
    private SceneSocketHandler sceneSocketHandler;

    @Autowired
    private NodeService nodeService;


    @Autowired
    private NodeSocketHandler nodeSocketHandler;

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        logger.info("Weather Data Sync Job. Start！");
        List<String> sceneSns= sceneSocketHandler.getSceneSns();
        for(String sceneSn:sceneSns){
            JSONObject res = new JSONObject();
            List<NodeData> latestNodesData =sceneService.getLatestNodesData(sceneSn);
            NodeData nodeData= nodeService.getMongoCD(sceneSn,null);
            res.put("NodesData",latestNodesData);
            sceneSocketHandler.sendMessageToUser(sceneSn, new TextMessage(JSONObject.toJSONString(res)));
        }

        List<Map<String,String>> nodes= nodeSocketHandler.getNodes();
        for(Map<String,String> node:nodes){
            JSONObject res = new JSONObject();
            NodeData nodeData= nodeService.getMongoLD(node.get(Constant.SCENE_SN),node.get(Constant.Node));
            res.put("NodesData",nodeData);
            nodeSocketHandler.sendMessageToUser(node.get(Constant.SCENE_SN),node.get(Constant.Node), new TextMessage(JSONObject.toJSONString(res)));
        }


        logger.info("Weather Data Sync Job. End！");   //定时执行
    }



    public void task() {

        List<String> sceneSns= sceneSocketHandler.getSceneSns();
        for(String sceneSn:sceneSns){
            JSONObject res = new JSONObject();
            List<NodeData> latestNodesData =sceneService.getLatestNodesData(sceneSn);
            res.put("NodesData",latestNodesData);
            sceneSocketHandler.sendMessageToUser(sceneSn, new TextMessage(JSONObject.toJSONString(res)));
        }

    }
}
