package com.xidian.iot.databiz.service.impl;

import com.xidian.iot.common.util.TimeUtil;
import com.xidian.iot.common.util.constants.ExceptionEnum;
import com.xidian.iot.common.util.Assert;
import com.xidian.iot.database.entity.Node;
import com.xidian.iot.database.entity.NodeExample;
import com.xidian.iot.database.entity.Scene;
import com.xidian.iot.database.entity.mongo.NodeData;
import com.xidian.iot.database.mapper.NodeMapper;
import com.xidian.iot.database.mapper.custom.NodeCustomMapper;
import com.xidian.iot.database.param.NodeAddParam;
import com.xidian.iot.database.param.NodeUpdateParam;
import com.xidian.iot.database.vo.NodeVo;
import com.xidian.iot.databiz.service.NodeService;
import com.xidian.iot.databiz.service.SceneService;
import com.xidian.iot.databiz.service.UidGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.WriteResultChecking;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.w3c.dom.NodeList;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.springframework.data.mongodb.core.query.Criteria.byExample;
import static org.springframework.data.mongodb.core.query.Criteria.where;

/**
 * @author: Hansey
 * @date: 2020-09-10 21:45
 */
@Service
public class NodeServiceImpl implements NodeService {
    @Autowired
    private NodeMapper nodeMapper;
    @Autowired
    private NodeCustomMapper nodeCustomMapper;
    @Autowired
    private MongoTemplate mongoTemplate;
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
        Node node = new Node();
        node.setNodeId(getNodeBySn(sceneSn, nodeSn).getNodeId());
        node.setNodeName(param.getNodeName());
        node.setNodeDesc(param.getNodeName());
        nodeMapper.updateByPrimaryKeySelective(node);
        return node;
    }

    @Override
    public Node getNodeBySn(String sceneSn, String nodeSn) {
        NodeExample nodeExample = new NodeExample();
        nodeExample.createCriteria().andSceneSnEqualTo(sceneSn).andNodeSnEqualTo(nodeSn);
        List<Node> nodes = nodeMapper.selectByExample(nodeExample);
        Assert.isTrue(nodes.size() > 0, ExceptionEnum.NODE_NOT_EXIST);
        return nodes.get(0);
    }

    @Override
    public NodeVo getNodeVoBySn(String sceneSn, String nodeSn) {
        //这里由sceneSn、nodeSn先判断是否存在此节点
        return nodeCustomMapper.getNodeVoByNodeId(getNodeBySn(sceneSn, nodeSn).getNodeId());
    }

    @Override
    public NodeData getMongoCD(String sceneSn, String nodeSn) {
        NodeData nodeData = null;
        try {
            mongoTemplate.setWriteResultChecking(WriteResultChecking.EXCEPTION);
            Query query = null;
            query = new Query().addCriteria(Criteria.where("sceneSn").is(sceneSn));
            if (!Objects.isNull(nodeSn)) {
                query.addCriteria(Criteria.where("nodeSn").is(nodeSn));
            }
            long et = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).getEpochSecond();
            long st = et - 60 * 15;//15分钟之前的时间戳
            query.addCriteria(Criteria.where("at").gte(st).lte(et));
            query.with(Sort.by(Sort.Direction.DESC, "at"));
            nodeData = mongoTemplate.findOne(query, NodeData.class, "nodedata");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return nodeData;
    }

    @Override
    public NodeData getMongoLD(String sceneSn, String nodeSn) {
        NodeData nodeData = null;
        //判断是否存在该节点
        getNodeBySn(sceneSn,nodeSn);
        try {
            mongoTemplate.setWriteResultChecking(WriteResultChecking.EXCEPTION);
            Query query = null;
            query = new Query().addCriteria(Criteria.where("sceneSn").is(sceneSn));
            if (!Objects.isNull(nodeSn)) {
                query.addCriteria(Criteria.where("nodeSn").is(nodeSn));
            }
            query.with(Sort.by(Sort.Direction.DESC, "at"));
            nodeData = mongoTemplate.findOne(query, NodeData.class, "nodedata");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return nodeData;
    }

    @Override
    public List<NodeData> getMongoData(String sceneSn, String nodeSn, Long st, Long et) {
        List<NodeData> nodeData = new ArrayList<>();
        //首先判断该节点是否存在
        getNodeBySn(sceneSn,nodeSn);
        try {
            mongoTemplate.setWriteResultChecking(WriteResultChecking.EXCEPTION);
            Query query = null;
            query = new Query().addCriteria(Criteria.where("sceneSn").is(sceneSn));
            if (!Objects.isNull(nodeSn)) {
                query.addCriteria(Criteria.where("nodeSn").is(nodeSn));
            }
            query.addCriteria(Criteria.where("at").gte(st).lte(et));
            nodeData = mongoTemplate.find(query, NodeData.class, "nodedata");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return nodeData;
    }
}
