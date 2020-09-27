package com.xidian.iot.databiz.service.impl;

import com.xidian.iot.database.entity.NodeActCmd;
import com.xidian.iot.database.entity.NodeActCmdExample;
import com.xidian.iot.database.mapper.NodeActCmdMapper;
import com.xidian.iot.database.mapper.custom.NodeActCmdCustomMapper;
import com.xidian.iot.databiz.service.NodeActCmdService;
import com.xidian.iot.databiz.service.UidGenerator;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author mrl
 * @Title: NodeActCmdServiceImpl
 * @Package
 * @Description:
 * @date 2020/9/14 7:20 下午
 */
@Service
public class NodeActCmdServiceImpl implements NodeActCmdService {

    @Resource
    private UidGenerator uidGenerator;
    @Resource
    NodeActCmdMapper nodeActCmdMapper;
    @Resource
    private NodeActCmdCustomMapper nodeActCmdCustomMapper;

    @Override
    public List<NodeActCmd> getNodeActCmdByNtId(Long ntId) {
        NodeActCmdExample nodeActCmdExample = new NodeActCmdExample();
        nodeActCmdExample.createCriteria().andNtIdEqualTo(ntId);
        return nodeActCmdMapper.selectByExample(nodeActCmdExample);
    }

    @Override
    public int delNodeActCmdByNtId(Long ntId) {
        NodeActCmdExample nodeActCmdExample = new NodeActCmdExample();
        nodeActCmdExample.createCriteria().andNtIdEqualTo(ntId);
        return nodeActCmdMapper.deleteByExample(nodeActCmdExample);
    }

    @Override
    public int addNodeActCmds(List<NodeActCmd> nodeActCmds) {
        nodeActCmds.stream().forEach(nac -> nac.setNacId(uidGenerator.getUID()));
        return nodeActCmdCustomMapper.addBatch(nodeActCmds);
    }

    public int delNodeActCmdByNcId(Long ncId) {
        NodeActCmdExample nodeActCmdExample = new NodeActCmdExample();
        nodeActCmdExample.createCriteria().andNcIdEqualTo(ncId);
        return nodeActCmdMapper.deleteByExample(nodeActCmdExample);
    }

    @Override
    public int delNodeActCmdByNtIds(List<Long> ncIds) {
        NodeActCmdExample nodeActCmdExample = new NodeActCmdExample();
        nodeActCmdExample.createCriteria().andNcIdIn(ncIds);
        return nodeActCmdMapper.deleteByExample(nodeActCmdExample);
    }
}
