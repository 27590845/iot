package com.xidian.iot.databiz.service.impl;

import com.xidian.iot.database.entity.NodeCond;
import com.xidian.iot.database.entity.NodeCondExample;
import com.xidian.iot.database.entity.custom.NodeCondExt;
import com.xidian.iot.database.mapper.NodeCondMapper;
import com.xidian.iot.database.mapper.custom.NodeCondCustomMapper;
import com.xidian.iot.databiz.service.NodeCondService;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

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
    private NodeCondMapper nodeCondMapper;
    @Resource
    private NodeCondCustomMapper nodeCondCustomMapper;

    private final Cache cache = new Cache();

    @Override
    public List<NodeCondExt> getNodeCondExtBySnAvl(String sceneSn, String nodeSn) {
        List<Long> nodeCondIds = nodeCondCustomMapper.getNodeCondIdsAvl(sceneSn, nodeSn);
        List<NodeCondExt> nodeCondExtList = new ArrayList<>();
        for(Long ncId : nodeCondIds){
            NodeCond nodeCond = getNodeCondExtById(ncId);
            if(nodeCond != null) {
                nodeCondExtList.add(new NodeCondExt(nodeCond));
            }
        }
        return nodeCondExtList;
    }

    @Override
    public List<NodeCondExt> getNodeCondExtByNtId(Long ntId) {
        NodeCondExample nodeCondExample = new NodeCondExample();
        nodeCondExample.createCriteria().andNtIdEqualTo(ntId);
        List<NodeCond> nodeConds = nodeCondMapper.selectByExample(nodeCondExample);
        List<NodeCondExt> nodeCondExtList = NodeCondExt.getExts(nodeConds);
        return nodeCondExtList;
    }

    @Override
    public NodeCondExt getNodeCondExtById(Long ncId) {
        return cache.getNodeCondExtById(ncId);
    }

    @Override
    @CachePut(value = "NodeCondExt", key = "'getNodeCondExtById:'+#nodeCondExt.ncId")
    public NodeCondExt changeNodeCondExt(NodeCondExt nodeCondExt) {
        return nodeCondExt;
    }

    /**
     * 内部调用，org.springframework.cache 不会生效，所以暂时把内部调用时用到cache的函数写到内部类中
     */
    class Cache {
        @Cacheable(value = "NodeCondExt", key = "'getNodeCondExtById:'+#ncId")
        public NodeCondExt getNodeCondExtById(Long ncId) {
            NodeCond nodeCond = nodeCondMapper.selectByPrimaryKey(ncId);
            return new NodeCondExt(nodeCond);
        }
    }
}
