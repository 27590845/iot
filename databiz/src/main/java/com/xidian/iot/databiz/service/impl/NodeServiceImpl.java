package com.xidian.iot.databiz.service.impl;

import com.xidian.iot.common.constants.ExceptionEnum;
import com.xidian.iot.common.util.Assert;
import com.xidian.iot.database.entity.Node;
import com.xidian.iot.database.entity.NodeExample;
import com.xidian.iot.database.entity.Scene;
import com.xidian.iot.database.mapper.NodeMapper;
import com.xidian.iot.database.param.NodeAddParam;
import com.xidian.iot.database.param.NodeUpdateParam;
import com.xidian.iot.databiz.service.NodeService;
import com.xidian.iot.databiz.service.SceneService;
import com.xidian.iot.databiz.service.UidGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    @Autowired
    private UidGenerator uidGenerator;

    @Override
    public Node addNode(NodeAddParam param) {
        //查询scene是否存在
        Scene scene = sceneService.getSceneBySn(param.getSceneSn());
        Node node = param.buildNode(scene.getSceneId());
        node.setNodeId(uidGenerator.getUID());
        NodeExample nodeExample = new NodeExample();
        nodeExample.createCriteria().andSceneIdEqualTo(scene.getSceneId());
        node.setNodeSn(String.format("%06d", nodeMapper.countByExample(nodeExample) + 1));
        //设置nodeSn
        nodeMapper.insertSelective(node);
        return node;
    }

    @Override
    public void delNode(String sceneSn, String nodeSn) {
        nodeMapper.deleteByPrimaryKey(getNodeBySn(sceneSn, nodeSn).getNodeId());
        NodeExample nodeExample = new NodeExample();
        nodeExample.createCriteria().andSceneSnEqualTo(sceneSn).andNodeSnEqualTo(sceneSn);
        Assert.isTrue(nodeMapper.deleteByExample(nodeExample) > 0, ExceptionEnum.NODE_NOT_EXIST);
    }

    @Override
    public Node updateNode(String sceneSn, String nodeSn, NodeUpdateParam param) {
        Node node = getNodeBySn(sceneSn, nodeSn);
        node.setNodeName(param.getNodeName());
        node.setNodeDesc(param.getNodeDesc());
        nodeMapper.updateByPrimaryKeySelective(node);
        return node;
    }

    @Override
    public Node getNodeBySn(String sceneSn, String nodeSn) {
        NodeExample nodeExample = new NodeExample();
        nodeExample.createCriteria().andSceneSnEqualTo(sceneSn).andNodeSnEqualTo(sceneSn);
        List<Node> nodes = nodeMapper.selectByExample(nodeExample);
        Assert.isTrue(nodes.size() > 0, ExceptionEnum.NODE_NOT_EXIST);
        return nodes.get(0);
    }


}
