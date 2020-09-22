package com.xidian.iot.databiz.service.impl;

import com.xidian.iot.database.entity.NodeCmd;
import com.xidian.iot.database.mapper.NodeCmdMapper;
import com.xidian.iot.database.mapper.custom.NodeCmdCustomMapper;
import com.xidian.iot.databiz.service.NodeCmdService;
import com.xidian.iot.databiz.service.UidGenerator;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author mrl
 * @Title: NodeCmdServiceImpl
 * @Package
 * @Description:
 * @date 2020/9/14 7:19 下午
 */
@Service
public class NodeCmdServiceImpl implements NodeCmdService {

    @Resource
    private UidGenerator uidGenerator;
    @Resource
    private NodeCmdMapper nodeCmdMapper;
    @Resource
    private NodeCmdCustomMapper nodeCmdCustomMapper;

    @Override
    public NodeCmd getNodeCmdById(Long ncId) {
        return nodeCmdMapper.selectByPrimaryKey(ncId);
    }

    @Override
    public int addNodeCmds(List<NodeCmd> nodeCmds) {
        nodeCmds.forEach(nc -> nc.setNcId(uidGenerator.getUID()));
        return nodeCmdCustomMapper.addBatch(nodeCmds);
    }
}
