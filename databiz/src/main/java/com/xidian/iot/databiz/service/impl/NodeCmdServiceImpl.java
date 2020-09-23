package com.xidian.iot.databiz.service.impl;

import com.xidian.iot.database.entity.Node;
import com.xidian.iot.database.entity.NodeCmd;
import com.xidian.iot.database.mapper.NodeCmdMapper;
import com.xidian.iot.database.mapper.custom.NodeCmdCustomMapper;
import com.xidian.iot.database.param.NodeCmdParam;
import com.xidian.iot.databiz.service.NodeCmdService;
import com.xidian.iot.databiz.service.NodeService;
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
    @Resource
    private NodeService nodeService;

    @Override
    public NodeCmd getNodeCmdById(Long ncId) {
        return nodeCmdMapper.selectByPrimaryKey(ncId);
    }

    @Override
    public int addNodeCmds(List<NodeCmd> nodeCmds) {
        nodeCmds.forEach(nc -> nc.setNcId(uidGenerator.getUID()));
        return nodeCmdCustomMapper.addBatch(nodeCmds);
    }

    @Override
    public void addNodeCmds(String sceneSn, String nodeSn, List<NodeCmdParam> nodeCmdParams) {
        //检查该节点是否存在
        Node node = nodeService.getNodeBySn(sceneSn, nodeSn);


    }
}
