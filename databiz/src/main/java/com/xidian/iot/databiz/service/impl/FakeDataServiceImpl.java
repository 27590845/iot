package com.xidian.iot.databiz.service.impl;

import com.xidian.iot.common.util.RandomUtil;
import com.xidian.iot.database.entity.mongo.NodeData;
import com.xidian.iot.database.vo.SceneVo;
import com.xidian.iot.databiz.service.FakeDataService;
import com.xidian.iot.databiz.service.NodeDataService;
import com.xidian.iot.databiz.service.SceneService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 制造假数据
 *
 * @author: Hansey
 * @date: 2021-05-28 21:16
 */
@Service
@Slf4j
public class FakeDataServiceImpl implements FakeDataService {

    private static final int CPU = Runtime.getRuntime().availableProcessors();

    @Resource
    private NodeDataService nodeDataService;

    @Resource
    private SceneService sceneService;

    private static ScheduledThreadPoolExecutor scheduledThreadPoolExecutor;
    /**
     * 存储目前需要造假数据的scene
     *
     * @param null
     * @return
     */
    private static Set<String> scenes;

    {
        scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(CPU + 1);
        scenes = new HashSet<>();
    }

    @Override
    public void makeFakeDate(String sceneSn, int avg, int variance, int period) {
        if (!scenes.contains(sceneSn)) {
            scenes.add(sceneSn);
        } else {
            return;
        }
        SceneVo scene = sceneService.getSceneVoBySn(sceneSn);
        if (scene == null) return;
        List<NodeData> fakeDate = new ArrayList<>();
        scene.getNodeVos().forEach(nodeVo -> {
            NodeData data = new NodeData();
            data.setSceneSn(nodeVo.getSceneSn());
            data.setNodeSn(nodeVo.getNodeSn());
            Map<String, Object> map = new HashMap<>();
            data.setData(map);
            nodeVo.getNodeAttrList().forEach(nodeAttr -> {
                map.put(nodeAttr.getNaKey(), 1);
            });
            fakeDate.add(data);
        });
        try {
            scheduledThreadPoolExecutor.scheduleWithFixedDelay(new FakeDate(fakeDate, avg, variance), 0, period, TimeUnit.SECONDS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    class FakeDate implements Runnable {

        public final List<NodeData> fakeDate;

        public final int avg;

        public final int variance;

        public FakeDate(List<NodeData> fakeDate, int avg, int variance) {
            this.fakeDate = fakeDate;
            this.avg = avg;
            this.variance = variance;
        }

        @Override
        public void run() {
            fakeDate.forEach(nodeData -> {

                nodeData.setAt(System.currentTimeMillis());
                Map<String, Object> hashMap = nodeData.getData();
                hashMap.forEach((k, v) -> {
                    hashMap.put(k, (int) RandomUtil.NormalDistribution(avg, variance));
                });
            });
            nodeDataService.addNodeData(fakeDate);
        }
    }
}