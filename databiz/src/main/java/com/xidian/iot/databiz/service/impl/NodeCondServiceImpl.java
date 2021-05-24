package com.xidian.iot.databiz.service.impl;

import com.xidian.iot.common.util.exception.BusinessException;
import com.xidian.iot.database.entity.NodeCond;
import com.xidian.iot.database.entity.NodeCondExample;
import com.xidian.iot.database.entity.custom.NodeCondExt;
import com.xidian.iot.database.mapper.NodeCondMapper;
import com.xidian.iot.database.mapper.custom.NodeCondCustomMapper;
import com.xidian.iot.database.param.NodeCondParam;
import com.xidian.iot.databiz.service.NodeAttrService;
import com.xidian.iot.databiz.service.NodeCondService;
import com.xidian.iot.databiz.service.UidGenerator;
import org.springframework.aop.framework.AopContext;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author mrl
 * @Title: NodeCondServiceImpl
 * @Package
 * @Description:
 * @date 2020/9/14 5:47 下午
 */
@Service
@EnableAspectJAutoProxy(proxyTargetClass = true, exposeProxy = true)
public class NodeCondServiceImpl implements NodeCondService {

    @Resource
    private UidGenerator uidGenerator;
    @Resource
    private NodeAttrService nodeAttrService;
    @Resource
    private NodeCondMapper nodeCondMapper;
    @Resource
    private NodeCondCustomMapper nodeCondCustomMapper;

    //如果有该缓存对应的持久化数据有变动，比如该节点关联的nodeCond有增删，而该缓存不可能得到同步，从而影响效率
//    @Cacheable(value = "NodeCondIds", key = "'getNcIdsBySn:'+#sceneSn+':'+#nodeSn")
    @Override
    public List<Long> getNcIdsBySn(String sceneSn, String nodeSn) {
        return nodeCondCustomMapper.getNcIdsBySn(sceneSn, nodeSn);
    }

    @Cacheable(value = "NodeCondIds", key = "'getNcIdsByNtId:'+#ntId")
    @Override
    public List<Long> getNcIdsByNtId(Long ntId) {
        return nodeCondCustomMapper.getNcIdsByNtId(ntId);
    }

    @CacheEvict(value = "NodeCondIds", key = "'getNcIdsByNtId:'+#ntId")
    @Override
    public void cleanNcIdsByNtId(Long ntId) {
    }

    @Cacheable(value = "NodeCondExt", key = "'getNodeCondExtById:'+#ncId")
    @Override
    public NodeCondExt getNodeCondExtById(Long ncId) {
        NodeCond nodeCond = nodeCondMapper.selectByPrimaryKey(ncId);
        return new NodeCondExt(nodeCond);
    }

    @CachePut(value = "NodeCondExt", key = "'getNodeCondExtById:'+#nodeCondExt.ncId")
    @Override
    public NodeCondExt changeNodeCondExt(NodeCondExt nodeCondExt) {
        return nodeCondExt;
    }

    @CacheEvict(value = "NodeCondExt", key = "'getNodeCondExtById:'+#ncId")
    @Override
    public void cleanNodeCondById(Long ncId) {
    }

    @Override
    public List<NodeCond> getNodeCondsByNtId(Long ntId) {
        NodeCondExample nodeCondExample = new NodeCondExample();
        nodeCondExample.createCriteria().andNtIdEqualTo(ntId);
        return nodeCondMapper.selectByExample(nodeCondExample);
    }

    @Override
    public int addNodeConds(List<NodeCond> nodeConds) {
        // 判断是否其scene_sn、node_sn、na_id是否真实存在、只要有一个不存在就抛出异常
        for (NodeCond nodeCond : nodeConds) {
            checkExistNodeAttr(nodeCond);
        }
        nodeConds.stream().forEach(nc -> nc.setNcId(uidGenerator.getUID()));
        return nodeCondCustomMapper.addBatch(nodeConds);
    }

    @CacheEvict(value = "NodeCondExt", key = "'getNcIdsByNtId:'+#ntId")
    @Override
    public int delNodeCondByNtId(Long ntId) {
        List<Long> ncIds = nodeCondCustomMapper.getNcIdsByNtId(ntId);
        NodeCondServiceImpl currentProxy = (NodeCondServiceImpl) AopContext.currentProxy();
        if (ncIds.size() > 0) {
            ncIds.stream().forEach(ncId -> currentProxy.cleanNodeCondById(ncId));
        }
        NodeCondExample nodeCondExample = new NodeCondExample();
        nodeCondExample.createCriteria().andNtIdEqualTo(ntId);
        return nodeCondMapper.deleteByExample(nodeCondExample);
    }

    @Override
    public void delNodeCondByNaIds(List<Long> naIds) {
        List<Long> ncIds = nodeCondCustomMapper.getNcIdsByNaIds(naIds);
        NodeCondServiceImpl currentProxy = (NodeCondServiceImpl) AopContext.currentProxy();
        if (ncIds.size() > 0) {
            //清除缓存中的节点条件----但是节点触发器中所有节点触发条件被删除应当把节点触发器也删除 采用定时清除机制
            ncIds.stream().forEach(ncId -> currentProxy.cleanNodeCondById(ncId));
            //根据节点触发条件批量删除
            nodeCondCustomMapper.delBatchsByNcIds(ncIds);
        }
    }

    @Override
    public int delNodeCondBySceneSn(String sceneSn) {
        List<Long> ncIds = nodeCondCustomMapper.getNcIdsBySceneSn(sceneSn);
        NodeCondExample nodeCondExample = new NodeCondExample();
        nodeCondExample.createCriteria().andSceneSnEqualTo(sceneSn);
        // 内部调用不走代理
        NodeCondServiceImpl currentProxy = (NodeCondServiceImpl) AopContext.currentProxy();
        if (ncIds.size() > 0) {
            ncIds.stream().forEach(ncId -> currentProxy.cleanNodeCondById(ncId));
        }
        return nodeCondMapper.deleteByExample(nodeCondExample);
    }

    @Override
    public int delNodeCondByNcId(Long ncId) {
        NodeCondExample nodeCondExample = new NodeCondExample();
        nodeCondExample.createCriteria().andNcIdEqualTo(ncId);
        return nodeCondMapper.deleteByExample(nodeCondExample);
    }

    @Override
    public int delNodeCondByNcIds(List<Long> ncIds) {
        NodeCondExample nodeCondExample = new NodeCondExample();
        nodeCondExample.createCriteria().andNcIdIn(ncIds);
        return nodeCondMapper.deleteByExample(nodeCondExample);
    }

    @Override
    public int updateNodeConds(List<NodeCond> nodeConds) {
        if (Objects.isNull(nodeConds) || nodeConds.size() == 0) return 0;
        // 内部调用不走代理
        NodeCondServiceImpl currentProxy = (NodeCondServiceImpl) AopContext.currentProxy();
        int res = nodeCondCustomMapper.updateBatch(nodeConds);
        for (NodeCond nodeCond : nodeConds) {
//            res += updateNodeCond(nodeCond);
            currentProxy.changeNodeCondExt(new NodeCondExt(nodeCond));
        }
        return res;
    }

    @Override
    public int updateNodeCond(NodeCond nodeCond) {
        // 检查是否合法
        // 是否存在此NaId
        if (!nodeAttrService.checkExistNodeAttr(nodeCond.getNaId())) {
            throw new BusinessException(-1, "不存在此节点");
        }
        // 是否存在此nc_id、nt_id
        if (checkExistNodeCond(nodeCond.getNcId())) {
            throw new BusinessException(-1, "不存在此节点触发条件");
        }
        nodeCond.setNodeSn(null);
        nodeCond.setSceneSn(null);
        nodeCond.setNaId(null);
        nodeCond.setNtId(null);
        int res = nodeCondMapper.updateByPrimaryKeySelective(nodeCond);
        NodeCondServiceImpl currentProxy = (NodeCondServiceImpl) AopContext.currentProxy();
        currentProxy.changeNodeCondExt(new NodeCondExt(nodeCond));
        return res;
    }

    private Boolean checkExistNodeCond(Long ncId) {
        return Objects.isNull(nodeCondMapper.selectByPrimaryKey(ncId));
    }

    @Override
    public int addNodeCond(NodeCond nodeCondParam) {
        checkExistNodeAttr(nodeCondParam);
        nodeCondParam.setNcId(uidGenerator.getUID());
        return nodeCondMapper.insert(nodeCondParam);
    }

    private void checkExistNodeAttr(NodeCond nodeCond){
        if(!nodeAttrService.checkExistNodeAttr(nodeCond.getSceneSn(),nodeCond.getNodeSn(),nodeCond.getNaId())){
            throw new BusinessException(-1, "该sceneSn:"+nodeCond.getSceneSn()+"，nodeSn:"+nodeCond.getNodeSn()+"，naId:"+nodeCond.getNaId().toString()+"不存在，请检查后重新添加");
        }
    }

}
