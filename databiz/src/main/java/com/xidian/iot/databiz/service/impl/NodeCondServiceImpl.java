package com.xidian.iot.databiz.service.impl;

import com.xidian.iot.database.entity.NodeCond;
import com.xidian.iot.database.entity.NodeCondExample;
import com.xidian.iot.database.entity.NodeTrig;
import com.xidian.iot.database.entity.custom.NodeCondExt;
import com.xidian.iot.database.mapper.NodeCondMapper;
import com.xidian.iot.database.mapper.custom.NodeCondCustomMapper;
import com.xidian.iot.databiz.service.NodeCondService;
import com.xidian.iot.databiz.service.NodeTrigService;
import com.xidian.iot.databiz.service.UidGenerator;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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

    @Cacheable(value = "NodeCondIds", key = "'getNcIdsBySn:'+#sceneSn+':'+#nodeSn")
    @Override
    public List<Long> getNcIdsBySn(String sceneSn, String nodeSn) {
        return nodeCondCustomMapper.getNcIdsBySn(sceneSn, nodeSn);
    }

    @Cacheable(value = "NodeCondIds", key = "'getNcIdsByNtId:'+#ntId")
    @Override
    public List<Long> getNcIdsByNtId(Long ntId) {
        return nodeCondCustomMapper.getNcIdsByNtId(ntId);
    }

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
