package com.xidian.iot.databiz.service.impl;

import com.xidian.iot.database.entity.NodeCond;
import com.xidian.iot.database.entity.NodeCondExample;
import com.xidian.iot.database.entity.custom.NodeCondExt;
import com.xidian.iot.database.mapper.NodeCondMapper;
import com.xidian.iot.database.mapper.custom.NodeCondCustomMapper;
import com.xidian.iot.databiz.service.NodeCondService;
import com.xidian.iot.databiz.service.UidGenerator;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author mrl
 * @Title: NodeCondServiceImpl
 * @Package
 * @Description:
 * @date 2020/9/14 5:47 下午
 */
@Service
public class NodeCondServiceImpl implements NodeCondService {

    @Resource
    private UidGenerator uidGenerator;
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
        nodeConds.stream().forEach(nc -> nc.setNcId(uidGenerator.getUID()));
        return nodeCondCustomMapper.addBatch(nodeConds);
    }

    @Override
    public int delNodeCondByNtId(Long ntId) {
        NodeCondExample nodeCondExample = new NodeCondExample();
        nodeCondExample.createCriteria().andNtIdEqualTo(ntId);
        return nodeCondMapper.deleteByExample(nodeCondExample);
    }

    @Override
    public void delNodeCondByNaIds(List<Long> naIds) {
        List<Long> ncIds = nodeCondCustomMapper.getNcIdsByNaIds(naIds);
        if (ncIds.size() > 0) {
            //清除缓存中的节点条件----但是节点触发器中所有节点触发条件被删除应当把节点触发器也删除 采用定时清除机制
            ncIds.stream().forEach(ncId -> cleanNodeCondById(ncId));
            //根据节点触发条件批量删除
            nodeCondCustomMapper.delBatchsByNcIds(ncIds);
        }
    }

    @Override
    public int delNodeCondBySceneSn(String sceneSn) {
        List<Long> ncIds = nodeCondCustomMapper.getNcIdsBySceneSn(sceneSn);
        NodeCondExample nodeCondExample = new NodeCondExample();
        nodeCondExample.createCriteria().andSceneSnEqualTo(sceneSn);
        if(ncIds.size()>0){
            ncIds.stream().forEach(ncId -> cleanNodeCondById(ncId));
        }
        return nodeCondMapper.deleteByExample(nodeCondExample);
    }
}
