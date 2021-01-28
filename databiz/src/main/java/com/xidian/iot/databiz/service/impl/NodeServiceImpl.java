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
        //返回结果
        NodeVo nodeVo = null;
        //查询scene是否存在
        Scene scene = sceneService.getSceneBySn(param.getSceneSn());
        Node node = param.buildNode(scene.getSceneId());
        NodeExample nodeExample = new NodeExample();
        nodeExample.createCriteria().andSceneIdEqualTo(scene.getSceneId());
        //设置nodeSn
        if(StringUtils.isBlank(node.getNodeSn())){
            String lastNodeSn = nodeCustomMapper.getSceneLastNodeSn(scene.getSceneId());
            //如果网关不存在节点则从1开始
            lastNodeSn = Objects.isNull(lastNodeSn) ? "0" : lastNodeSn;
            node.setNodeSn(String.format("%06d", Integer.valueOf(lastNodeSn) + 1));
        }else {
            Assert.isFalse(isNodeExistBySn(node.getSceneSn(), node.getNodeSn()), ExceptionEnum.NODE_ALREADY_EXIST);
        }
        node.setNodeId(uidGenerator.getUID());
        if (nodeMapper.insertSelective(node) > 0) {
            //将返回结果转化为NodeVo
            nodeVo = new NodeVo(node);
            //添加节点属性
            List<NodeAttrParam> nodeAttrParams = param.getNodeAttrParams();
            if (!Objects.isNull(nodeAttrParams) && nodeAttrParams.size() > 0) {
                //验证输入的节点属性是否有重复
                nodeAttrService.checkReptAttrKeys(param.getNodeAttrParams());
                List<NodeAttr> nodeAttrs = nodeAttrService.addNodeAttr(node.getSceneSn(), node.getNodeSn(), node.getNodeId(), nodeAttrParams);
                nodeVo.setNodeAttrList(nodeAttrs);
            }
            //添加节点命令
            List<NodeCmdParam> nodeCmdParams = param.getNodeCmdParams();
            if (!Objects.isNull(nodeCmdParams) && nodeCmdParams.size() > 0) {
                //验证添加的节点命令是否存在重复命令内容/命令名称
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
        //检查是否存在此节点、这里同样是不走缓存的因为删除的并发量并没有这么大。
        Node node = getNodeBySn(sceneSn, nodeSn);
        nodeMapper.deleteByPrimaryKey(node.getNodeId());
        nodeAttrService.deleteByNodeId(node.getNodeId());
        List<Long> ncIds = nodeCmdService.getNcIdsByNodeId(node.getNodeId());
        nodeCmdService.deleteByNodeId(node.getNodeId());
        //级联删除nodeActCmd
        if (!Objects.isNull(ncIds) && ncIds.size() > 0) {
            nodeActCmdService.delNodeActCmdByNcIds(ncIds);
        }
    }

    @CachePut(value = "Node", key = "'getNodeBySn:'+#sceneSn+'-'+#nodeSn")
    @Override
    public Node updateNode(String sceneSn, String nodeSn, NodeUpdateParam param) {
        Node node = new Node();
        //更新的并发不大、这里内部调用不走springCache的缓存
        //原因是Cacheable是使用Aop动态代理实现的，类内部的互相调用是不走代理的，所以并不会调用缓存。
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
        //这里由sceneSn、nodeSn先判断是否存在此节点
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
        // long st = et - 60 * 15;//15分钟之前的时间戳
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
        if (Objects.isNull(node)) throw new BusinessException(-1, "不存在节点Id为" + nodeId + "的节点");
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
