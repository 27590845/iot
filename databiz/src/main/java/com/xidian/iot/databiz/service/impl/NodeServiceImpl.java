package com.xidian.iot.databiz.service.impl;

import com.xidian.iot.common.util.constants.ExceptionEnum;
import com.xidian.iot.common.util.Assert;
import com.xidian.iot.common.util.exception.BusinessException;
import com.xidian.iot.database.entity.*;
import com.xidian.iot.database.entity.mongo.NodeData;
import com.xidian.iot.database.mapper.NodeMapper;
import com.xidian.iot.database.mapper.custom.NodeCustomMapper;
import com.xidian.iot.database.param.NodeAddParam;
import com.xidian.iot.database.param.NodeAttrParam;
import com.xidian.iot.database.param.NodeCmdParam;
import com.xidian.iot.database.param.NodeUpdateParam;
import com.xidian.iot.database.vo.NodeVo;
import com.xidian.iot.databiz.service.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.WriteResultChecking;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


/**
 * @author: Hansey
 * @date: 2020-09-10 21:45
 */
@Service
@Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
@EnableAspectJAutoProxy(proxyTargetClass = true, exposeProxy = true)
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
    private NodeAttrService nodeAttrService;
    @Autowired
    private NodeCmdService nodeCmdService;
    @Autowired
    private NodeActCmdService nodeActCmdService;
    @Autowired
    private UidGenerator uidGenerator;

    @Override
    public NodeVo addNode(NodeAddParam param) {
        //????????????
        NodeVo nodeVo = null;
        //??????scene????????????
        Scene scene = sceneService.getSceneBySn(param.getSceneSn());
        Node node = param.buildNode(scene.getSceneId());
        NodeExample nodeExample = new NodeExample();
        nodeExample.createCriteria().andSceneIdEqualTo(scene.getSceneId());
        //??????nodeSn
        if(StringUtils.isBlank(node.getNodeSn())){
            String lastNodeSn = nodeCustomMapper.getSceneLastNodeSn(scene.getSceneId());
            //?????????????????????????????????1??????
            lastNodeSn = Objects.isNull(lastNodeSn) ? "0" : lastNodeSn;
            node.setNodeSn(String.format("%06d", Integer.valueOf(lastNodeSn) + 1));
        }else {
            Assert.isFalse(isNodeExistBySn(node.getSceneSn(), node.getNodeSn()), ExceptionEnum.NODE_ALREADY_EXIST);
        }
        node.setNodeId(uidGenerator.getUID());
        if (nodeMapper.insertSelective(node) > 0) {
            //????????????????????????NodeVo
            nodeVo = new NodeVo(node);
            //??????????????????
            List<NodeAttrParam> nodeAttrParams = param.getNodeAttrParams();
            if (!Objects.isNull(nodeAttrParams) && nodeAttrParams.size() > 0) {
                //??????????????????????????????????????????
                nodeAttrService.checkReptAttrKeys(param.getNodeAttrParams());
                List<NodeAttr> nodeAttrs = nodeAttrService.addNodeAttr(node.getSceneSn(), node.getNodeSn(), node.getNodeId(), nodeAttrParams);
                nodeVo.setNodeAttrList(nodeAttrs);
            }
            //??????????????????
            List<NodeCmdParam> nodeCmdParams = param.getNodeCmdParams();
            if (!Objects.isNull(nodeCmdParams) && nodeCmdParams.size() > 0) {
                //?????????????????????????????????????????????????????????/????????????
                nodeCmdService.checkReptCmds(nodeCmdParams);
                List<NodeCmd> nodeCmds = nodeCmdService.addNodeCmds(node.getSceneSn(), node.getNodeSn(), node.getNodeId(), nodeCmdParams);
                nodeVo.setNodeCmdList(nodeCmds);
            }
        }
        return nodeVo;
    }

    @Cacheable(value = "Node", key = "'getNodeBySn:'+#sceneSn+'-'+#nodeSn")
    @Override
    public Node getNodeBySn(String sceneSn, String nodeSn) {
        NodeExample nodeExample = new NodeExample();
        nodeExample.createCriteria().andSceneSnEqualTo(sceneSn).andNodeSnEqualTo(nodeSn);
        List<Node> nodes = nodeMapper.selectByExample(nodeExample);
        Assert.isTrue(nodes.size() > 0, ExceptionEnum.NODE_NOT_EXIST);
        return nodes.get(0);
    }

    @CacheEvict(value = "Node", key = "'getNodeBySn:'+#sceneSn+'-'+#nodeSn")
    @Override
    public void delNode(String sceneSn, String nodeSn) {
        //?????????????????????????????????????????????????????????????????????????????????????????????????????????
        Node node = getNodeBySn(sceneSn, nodeSn);
        nodeMapper.deleteByPrimaryKey(node.getNodeId());
        nodeAttrService.deleteByNodeId(node.getNodeId());
        List<Long> ncIds = nodeCmdService.getNcIdsByNodeId(node.getNodeId());
        nodeCmdService.deleteByNodeId(node.getNodeId());
        //????????????nodeActCmd
        if (!Objects.isNull(ncIds) && ncIds.size() > 0) {
            nodeActCmdService.delNodeActCmdByNcIds(ncIds);
        }
    }

    @CachePut(value = "Node", key = "'getNodeBySn:'+#sceneSn+'-'+#nodeSn")
    @Override
    public Node updateNode(String sceneSn, String nodeSn, NodeUpdateParam param) {
        Node node = new Node();
        //????????????????????????????????????????????????springCache?????????
        //?????????Cacheable?????????Aop???????????????????????????????????????????????????????????????????????????????????????????????????
        node.setNodeId(getNodeBySn(sceneSn, nodeSn).getNodeId());
        node.setNodeName(param.getNodeName());
        node.setNodeDesc(param.getNodeDesc());
        node.setNodeMap(param.getNodeMap());
        nodeMapper.updateByPrimaryKeySelective(node);
        return node;
    }

    @Override
    public NodeVo getNodeVoBySn(String sceneSn, String nodeSn) {
        NodeServiceImpl currentProxy = (NodeServiceImpl) AopContext.currentProxy();
        //?????????sceneSn???nodeSn??????????????????????????????
        return nodeCustomMapper.getNodeVoByNodeId(currentProxy.getNodeBySn(sceneSn, nodeSn).getNodeId());
    }

    @Override
    public NodeData getMongoCD(String sceneSn, String nodeSn) {
        NodeData nodeData = null;
        mongoTemplate.setWriteResultChecking(WriteResultChecking.EXCEPTION);
        Query query = null;
        query = new Query().addCriteria(Criteria.where("sceneSn").is(sceneSn));
        if (!Objects.isNull(nodeSn)) {
            query.addCriteria(Criteria.where("nodeSn").is(nodeSn));
        }
        Long et = System.currentTimeMillis();
        Long st = et - 60 * 15 * 1000;
        // long et = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).getEpochSecond();
        // long st = et - 60 * 15;//15????????????????????????
        query.addCriteria(Criteria.where("at").gte(st).lte(et));
        query.with(Sort.by(Sort.Direction.DESC, "at"));
        nodeData = mongoTemplate.findOne(query, NodeData.class, "nodedata");
        return nodeData;
    }

    @Override
    public NodeData getMongoLD(String sceneSn, String nodeSn) {
        NodeData nodeData = null;
        mongoTemplate.setWriteResultChecking(WriteResultChecking.EXCEPTION);
        Query query = null;
        query = new Query().addCriteria(Criteria.where("sceneSn").is(sceneSn));
        if (!Objects.isNull(nodeSn)) {
            query.addCriteria(Criteria.where("nodeSn").is(nodeSn));
        }
        query.with(Sort.by(Sort.Direction.DESC, "at"));
        nodeData = mongoTemplate.findOne(query, NodeData.class, "nodedata");
        return nodeData;
    }

    @Override
    public List<NodeData> getMongoData(String sceneSn, String nodeSn, Long st, Long et) {
        List<NodeData> nodeData = new ArrayList<>();
        mongoTemplate.setWriteResultChecking(WriteResultChecking.EXCEPTION);
        Query query = null;
        query = new Query().addCriteria(Criteria.where("sceneSn").is(sceneSn));
        if (!Objects.isNull(nodeSn)) {
            query.addCriteria(Criteria.where("nodeSn").is(nodeSn));
        }
        query.addCriteria(Criteria.where("at").gte(st).lte(et));
        nodeData = mongoTemplate.find(query, NodeData.class, "nodedata");
        return nodeData;
    }

    @Override
    public Node getNodeByNodeId(Long nodeId) {
        Node node = nodeMapper.selectByPrimaryKey(nodeId);
        if (Objects.isNull(node)) throw new BusinessException(-1, "???????????????Id???" + nodeId + "?????????");
        return node;
    }

    @Override
    public int delNodesBySceneSn(String sceneSn) {
        NodeExample nodeExample = new NodeExample();
        nodeExample.createCriteria().andSceneSnEqualTo(sceneSn);
        return nodeMapper.deleteByExample(nodeExample);
    }

    private boolean isNodeExistBySn(String sceneSn, String nodeSn){
        NodeExample nodeExample = new NodeExample();
        nodeExample.createCriteria().andSceneSnEqualTo(sceneSn).andNodeSnEqualTo(nodeSn);
        List<Node> nodes = nodeMapper.selectByExample(nodeExample);
        return nodes!=null&&nodes.size()>0;
    }
}
