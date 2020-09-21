package com.xidian.iot.databiz.service.impl;

import com.xidian.iot.database.entity.NodeTrig;
import com.xidian.iot.database.entity.custom.NodeTrigExt;
import com.xidian.iot.database.mapper.NodeTrigMapper;
import com.xidian.iot.database.mapper.custom.NodeTrigCustomMapper;
import com.xidian.iot.database.param.NodeActCmdParam;
import com.xidian.iot.database.param.NodeTrigParam;
import com.xidian.iot.databiz.service.NodeTrigService;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

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
    private NodeTrigMapper nodeTrigMapper;
    @Resource
    private NodeTrigCustomMapper nodeTrigCustomMapper;

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

    @Override
    public int addRuleEngine(NodeTrigParam nodeTrigParam) {
        List<Long> ntIds = nodeTrigCustomMapper.getNtIdsByNcIds(
                nodeTrigParam.getNodeActCmdParams().stream().map(nac -> nac.getNcId()).collect(Collectors.toList()));
        if(ntIds!=null && ntIds.size()>0){

        }
        return 0;
    }


}
