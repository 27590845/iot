package com.xidian.iot.databiz.service.impl;

import com.xidian.iot.database.entity.NodeCond;
import com.xidian.iot.database.entity.custom.NodeCondExt;
import com.xidian.iot.database.mapper.NodeCondMapper;
import com.xidian.iot.database.mapper.custom.NodeCondCustomMapper;
import com.xidian.iot.databiz.service.NodeCondService;
import com.xidian.iot.databiz.service.UidGenerator;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
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
    private UidGenerator uidGenerator;
    @Resource
    private NodeCondMapper nodeCondMapper;
    @Resource
    private NodeCondCustomMapper nodeCondCustomMapper;

    @Cacheable(value = "NodeCondIds", key = "'getNodeCondIdsAvlBySn:'+#sceneSn+':'+#nodeSn")
    @Override
    public List<Long> getNodeCondIdsAvlBySn(String sceneSn, String nodeSn) {
        return nodeCondCustomMapper.getNodeCondIdsAvlBySn(sceneSn, nodeSn);
    }

    @Cacheable(value = "NodeCondIds", key = "'getNodeCondIdsByNtId:'+#ntId")
    @Override
    public List<Long> getNodeCondIdsByNtId(Long ntId) {
        return nodeCondCustomMapper.getNodeCondIdsByNtId(ntId);
    }


//    @Override
//    public List<NodeCondExt> getNodeCondExtAvlBySn(String sceneSn, String nodeSn) {
//        List<Long> nodeCondIds = nodeCondCustomMapper.getNodeCondIdsAvl(sceneSn, nodeSn);
//        List<NodeCondExt> nodeCondExtList = new ArrayList<>();
//        for(Long ncId : nodeCondIds){
//            NodeCond nodeCond = getNodeCondExtById(ncId);
//            if(nodeCond != null) {
//                nodeCondExtList.add(new NodeCondExt(nodeCond));
//            }
//        }
//        return nodeCondExtList;
//    }

//    @Override
//    public List<NodeCondExt> getNodeCondExtByNtId(Long ntId) {
//        NodeCondExample nodeCondExample = new NodeCondExample();
//        nodeCondExample.createCriteria().andNtIdEqualTo(ntId);
//        List<NodeCond> nodeConds = nodeCondMapper.selectByExample(nodeCondExample);
//        List<NodeCondExt> nodeCondExtList = NodeCondExt.getExts(nodeConds);
//        return nodeCondExtList;
//    }

    @Override
    @Cacheable(value = "NodeCondExt", key = "'getNodeCondExtById:'+#ncId")
    public NodeCondExt getNodeCondExtById(Long ncId) {
        NodeCond nodeCond = nodeCondMapper.selectByPrimaryKey(ncId);
        return new NodeCondExt(nodeCond);
    }

    @Override
    @CachePut(value = "NodeCondExt", key = "'getNodeCondExtById:'+#nodeCondExt.ncId")
    public NodeCondExt changeNodeCondExt(NodeCondExt nodeCondExt) {
        return nodeCondExt;
    }

    @Override
    public NodeCond addNodeCond(NodeCond nodeCond) {
        return null;
    }
}
