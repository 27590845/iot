package com.xidian.iot.datacenter.service.task;

import com.xidian.iot.common.cache.RedisUtil;
import com.xidian.iot.common.mq.MqSender;
import com.xidian.iot.common.util.RandomUtil;
import com.xidian.iot.database.entity.mongo.NodeData;
import com.xidian.iot.database.vo.SceneVo;
import com.xidian.iot.databiz.service.NodeService;
import com.xidian.iot.databiz.service.SceneService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @description:
 * @author: mrl
 * @date: 2021/3/23 下午3:02
 */
@Slf4j
@Component
public class FakeDataTask{

    final static String PREFIX = "node.updata.";
    final static String FAKE_KEY = "fakeDataSet";

    @Resource
    private SceneService sceneService;
    @Resource
    private NodeService nodeService;
    @Resource
    MqSender mqSender;
    @Resource
    private RedisUtil redisUtil;
    @Resource
    private TaskExecutor taskExecutor;

    private Set<String> scenes = new HashSet<>();

    public void addFakeTask(String sceneSn, int period, long num){
        if(redisUtil.sHasKey(FAKE_KEY, sceneSn)){
            log.info("FakeDataTask already exist: {}", sceneSn);
            return;
        }
        redisUtil.sSet(FAKE_KEY, sceneSn);
        if(!scenes.contains(sceneSn)){
            scenes.add(sceneSn);
        }
        SceneVo scene = sceneService.getSceneVoBySn(sceneSn);
        if(scene==null) return;
        taskExecutor.execute(new PublishFakeData(scene, period, num));
    }

    public boolean delFakeTask(String sceneSn){
        scenes.remove(sceneSn);
        return redisUtil.setRemove(FAKE_KEY, sceneSn)>0;
    }



    class PublishFakeData implements Runnable{

        final static long checkPeriod = 5000;
        private final SceneVo scene;
        private final Integer period;
        private final Long num;
        private long lastCheckTime;
        private Map<String, Double> dataAvg = new HashMap<>();

        public PublishFakeData(SceneVo scene, int period, long num){
            this.scene = scene;
            this.period = period;
            this.num = num;
            lastCheckTime = System.currentTimeMillis();
        }

        @Override
        public void run() {
            initSceneAvg();
            for(int i=0; i< num; i++){
                StringBuilder msgBuilder = new StringBuilder();
                msgBuilder.append("{\"datastreams\":[");
                scene.getNodeVos().forEach(nodeVo -> {
                    msgBuilder.append("{\"at\":"+ System.currentTimeMillis())
                            .append(",\"sn\":\""+nodeVo.getNodeSn()+"\"");
                    nodeVo.getNodeAttrList().forEach(nodeAttr -> {
                        double avg = 30;
                        if(dataAvg.containsKey(nodeVo.getNodeSn()+nodeAttr.getNaKey())){
                            avg = dataAvg.get(nodeVo.getNodeSn()+nodeAttr.getNaKey());
                        }
                        msgBuilder.append(",\""+nodeAttr.getNaKey()+"\":"+ RandomUtil.NormalDistribution(avg, 1));
                    });
                    msgBuilder.append("},");
                });
                msgBuilder.deleteCharAt(msgBuilder.length()-1);
                msgBuilder.append("]}");
                try {
                    mqSender.sendQueue(PREFIX+scene.getSceneSn(), msgBuilder.toString());
                    if(System.currentTimeMillis()-lastCheckTime > checkPeriod
                            && !redisUtil.sHasKey(FAKE_KEY, scene.getSceneSn())){
                        return;
                    }
                    Thread.sleep(period);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        private void initSceneAvg(){
            scene.getNodeVos().forEach(nodeVo -> {
                NodeData nodeData = nodeService.getMongoLD(scene.getSceneSn(), nodeVo.getNodeSn());
                if (null != nodeData && null != nodeData.getData()) {
                    nodeData.getData().entrySet().forEach(entry -> {
                        dataAvg.put(nodeVo.getNodeSn() + entry.getKey(), Double.parseDouble(String.valueOf(entry.getValue())));
                    });
                }
            });
        }
    }
}
