package com.xidian.iot.databiz.service.impl;

import com.xidian.iot.database.entity.NodeCond;
import com.xidian.iot.database.entity.NodeCondExample;
import com.xidian.iot.database.entity.custom.NodeCondExt;
import com.xidian.iot.database.mapper.NodeCondMapper;
import com.xidian.iot.database.mapper.custom.NodeCondCustomMapper;
import com.xidian.iot.databiz.service.NodeCondService;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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

    @Override
    public List<NodeCond> getNodeCondBySnAvl(String sceneSn, String nodeSn) {
        return null;
    }

    @Override
    public List<NodeCondExt> getNodeCondExtBySnAvl(String sceneSn, String nodeSn) {
        List<NodeCond> nodeConds = nodeCondCustomMapper.getNodeCondAvl(sceneSn, nodeSn);
        return NodeCondExt.getExts(nodeConds);
    }

    @Override
    public List<NodeCondExt> getNodeCondExtByNtId(Long ntId) {
        NodeCondExample nodeCondExample = new NodeCondExample();
        nodeCondExample.createCriteria().andNtIdEqualTo(ntId);
        List<NodeCond> nodeConds = nodeCondMapper.selectByExample(nodeCondExample);
        List<NodeCondExt> nodeCondExtList = NodeCondExt.getExts(nodeConds);
        return nodeCondExtList;
    }

    @CachePut
    @Override
    public NodeCondExt updateNodeCondExt(NodeCondExt nodeCondExt) {
        return nodeCondExt;
    }
}
