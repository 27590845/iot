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
/*

    @Autowired
    private SocketHandler socketHandler;

    public void task() {
        List<Integer> deviceIds = socketHandler.getDeviceIds();
        for (Integer deviceId : deviceIds) {
            JSONObject res = new JSONObject();
            String sn = deviceService.getSnById(deviceId);//网关号
            List<SensorData> list = sensorDataService.getSensorData(deviceId);
            JSONObject sceneData = platformUtil.getSceneData(sn);
            if (sceneData != null && sceneData.size() > 0) {
                res.put("time", sceneData.getLongValue("at"));
                JSONObject sceneObject = sceneData.getJSONObject("data");
                for (SensorData sensorData : list) {
                    String sensorName = sensorData.getName();
                    if (sceneObject.containsKey(sensorName)) {
                        // 直接赋值
                        float sensorValue = sceneObject.getFloatValue(sensorName);
                        sensorData.setValue(sensorValue);
                    }
                    if (Objects.nonNull(sensorData.getAliasTypeName())) {
                        sensorData.setType(sensorData.getAliasTypeName());
                        sensorData.setName(sensorData.getAliasName());
                        //进行数值换算
                    }
                }
//                list = sensorDataService.getValue(list,sceneData);
                // 进行阀值警报判断
                list = sensorDataService.judgeAbnormal(list);
            } else {
                res.put("time", null);
            }
            res.put("sensorData", list);
            JSONArray alarmClassify = alarmService.getClassify(deviceId, list);
            res.put("alarmClassify", alarmClassify);


//            Device device = deviceService.getById(deviceId);
//            DeviceVo deviceVo = new DeviceVo();
//            deviceVo.setDeviceId(deviceId);
//            deviceVo.setDeviceName(device.getDeviceName());
//            deviceVo.setSceneSn(device.getSceneId());
//            NodeExample nodeExample = new NodeExample();
//            nodeExample.createCriteria().andDeviceIdEqualTo(deviceId);
//            //设备下的传感器数量
//            Integer sensorNum = sensorService.getCountByDeviceId(deviceVo.getDeviceId());
//            List<Node> nodes = nodeMapper.selectByExample(nodeExample);
//            for (Node node : nodes) {
//                deviceVo = sensorDataService.getSensorData(deviceVo, node.getNodeSn(), node.getNodeId(), false);
//            }
////            int normalNum = sensorNum-deviceVo.getFaultNum();
////            deviceVo.setNormalNum(normalNum);
//            if (sensorNum > 0) {
//                deviceVo.setAlarmClassify(alarmService.getClassify(deviceId, deviceVo.getSensorDataList()));
//            }

            socketHandler.sendMessageToUser(deviceId, new TextMessage(JSONObject.toJSONString(res)));
        }
    }

 */
}
