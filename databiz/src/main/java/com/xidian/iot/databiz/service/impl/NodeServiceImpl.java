package com.xidian.iot.databiz.service.impl;

//import com.baidu.fsg.uid.UidGenerator;
import com.xidian.iot.database.entity.Node;
import com.xidian.iot.database.entity.Scene;
import com.xidian.iot.database.mapper.NodeMapper;
import com.xidian.iot.database.param.NodeAddParam;
import com.xidian.iot.databiz.service.NodeService;
import com.xidian.iot.databiz.service.SceneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: Hansey
 * @date: 2020-09-10 21:45
 */
@Service
public class NodeServiceImpl implements NodeService {
    @Autowired
    private NodeMapper nodeMapper;
    @Autowired
    private SceneService sceneService;
//    @Autowired
//    private UidGenerator uidGenerator;

    @Override
    public Node addNode(NodeAddParam param) {
        //查询scene是否存在
        Scene scene = sceneService.getSceneBySn(param.getSceneSn());
        Node node = param.buildNode(scene.getSceneId());
//        node.setNodeId(uidGenerator.getUID());

        //设置nodeSn
        return null;
    }

    @Override
    public Node getNodeBySn(String sceneSn, String nodeSn) {
        return null;
    }
}
