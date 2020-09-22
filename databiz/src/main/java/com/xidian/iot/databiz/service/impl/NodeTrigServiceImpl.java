package com.xidian.iot.databiz.service.impl;

import com.xidian.iot.common.util.exception.BusinessException;
import com.xidian.iot.database.entity.NodeTrig;
import com.xidian.iot.database.entity.custom.NodeTrigExt;
import com.xidian.iot.database.mapper.NodeTrigMapper;
import com.xidian.iot.database.mapper.custom.NodeTrigCustomMapper;
import com.xidian.iot.databiz.service.NodeTrigService;
import com.xidian.iot.databiz.service.UidGenerator;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author mrl
 * @Title: NodeTrigServiceImpl
 * @Package
 * @Description:
 * @date 2020/9/14 7:18 下午
 */
@Service
public class NodeTrigServiceImpl implements NodeTrigService {

    @Resource
    private UidGenerator uidGenerator;
    @Resource
    private NodeTrigMapper nodeTrigMapper;
    @Resource
    private NodeTrigCustomMapper nodeTrigCustomMapper;

    @Override
    public List<Long> getNtIdsByNcIds(List<Long> ncIds) {
        return nodeTrigCustomMapper.getNtIdsByNcIds(ncIds);
    }

    @Cacheable(value = "NodeTrigExt", key = "'getNodeTrigExtById:'+#ntId")
    @Override
    public NodeTrigExt getNodeTrigExtById(Long ntId) {
        NodeTrig nodeTrig = nodeTrigMapper.selectByPrimaryKey(ntId);
        return new NodeTrigExt(nodeTrig);
    }

    @CachePut(value = "NodeTrigExt", key = "'getNodeTrigExtById:'+#nodeTrigExt.ntId")
    @Override
    public NodeTrigExt updateNodeTrigExtById(NodeTrigExt nodeTrigExt) {
//        nodeTrigMapper.updateByExample();
        return nodeTrigExt;
    }

    @CacheEvict(value = "NodeTrigExt", key = "'getNodeTrigExtById:'+#ntId")
    @Override
    public int delNodeTrigByNtId(Long ntId) {
        return nodeTrigMapper.deleteByPrimaryKey(ntId);
    }

    @Override
    public int addNodeTrig(NodeTrig nodeTrig) {
        nodeTrig.setNtId(uidGenerator.getUID());
        return nodeTrigMapper.insert(nodeTrig);
    }
}
