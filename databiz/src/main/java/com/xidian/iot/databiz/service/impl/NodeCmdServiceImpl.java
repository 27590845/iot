package com.xidian.iot.databiz.service.impl;

import com.xidian.iot.common.util.exception.BusinessException;
import com.xidian.iot.database.entity.NodeCmd;
import com.xidian.iot.database.entity.NodeCmdExample;
import com.xidian.iot.database.mapper.NodeCmdMapper;
import com.xidian.iot.database.mapper.custom.NodeCmdCustomMapper;
import com.xidian.iot.database.param.NodeCmdParam;
import com.xidian.iot.database.param.ValidList;
import com.xidian.iot.databiz.service.NodeActCmdService;
import com.xidian.iot.databiz.service.NodeCmdService;
import com.xidian.iot.databiz.service.UidGenerator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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
    private NodeActCmdService nodeActCmdService;

    @Override
    public NodeCmd getNodeCmdById(Long ncId) {
        NodeCmd nodeCmd = nodeCmdMapper.selectByPrimaryKey(ncId);
        if (Objects.isNull(nodeCmd)) throw new BusinessException(-1, "该条节点命令不存在");
        return nodeCmd;
    }

    @Override
    public List<NodeCmd> addNodeCmds(String sceneSn, String nodeSn, Long nodeId, List<NodeCmdParam> nodeCmdParams) {
        List<NodeCmd> nodeCmds = nodeCmdParams.stream().map(
                nodeCmdParam -> {
                    return nodeCmdParam.buildNodeCmd(uidGenerator.getUID(), nodeId, sceneSn, nodeSn);
                }
        ).collect(Collectors.toList());
        nodeCmdCustomMapper.addBatch(nodeCmds);
        return nodeCmds;
    }

    @Override
    public void checkReptCmds(List<NodeCmdParam> nodeCmdParams) {
        //将具有相同名字的节点命令取出来
        List<String> repeNcName = nodeCmdParams.stream()
                .map(e -> e.getNcName()).collect(Collectors.toMap(e -> e, e -> 1, (a, b) -> a + b))
                .entrySet().stream()
                .filter(entry -> entry.getValue() > 1)
                .map(entry -> entry.getKey())
                .collect(Collectors.toList());
        if (repeNcName.size() > 0) {
            throw new BusinessException(-1, "传入的" + repeNcName.toString() + "，这些节点命令名称重复、请确认后重新添加");
        }
        //将具有相同节点命令取出来
        List<String> repeNcCt = nodeCmdParams.stream()
                .map(e -> e.getNcContent()).collect(Collectors.toMap(e -> e, e -> 1, (a, b) -> a + b))
                .entrySet().stream()
                .filter(entry -> entry.getValue() > 1)
                .map(entry -> entry.getKey())
                .collect(Collectors.toList());
        if (repeNcCt.size() > 0) {
            throw new BusinessException(-1, "传入的" + repeNcCt.toString() + "，这些节点命令内容重复、请确认后重新添加");
        }
    }

    @Override
    public void checkExistCmds(Long nodeId, ValidList<NodeCmdParam> nodeCmdParams) {
        //从节点命令表中找到已经存在的节点名称
        List<String> existedNCN = nodeCmdCustomMapper.getExistedNCN(nodeId, nodeCmdParams.stream()
                .map(e -> e.getNcName()).collect(Collectors.toList()));
        if (existedNCN.size() > 0) {
            throw new BusinessException(-1, existedNCN.toString() + "添加节点命令失败，这些节点命令名称重复、请确认后重新添加");
        }
        //从节点命令表中周到已经存在的节点命令
        List<String> existedNCC = nodeCmdCustomMapper.getExistedNCC(nodeId, nodeCmdParams.stream()
                .map(e -> e.getNcContent()).collect(Collectors.toList()));
        if (existedNCC.size() > 0) {
            throw new BusinessException(-1, "添加节点命令失败，该节点已存在" + existedNCC.toString() + "此节点命令内容、请确认后重新添加");
        }
    }

    @Override
    public List<NodeCmd> getNodeCmdsByNodeId(Long nodeId) {
        NodeCmdExample nodeCmdExample = new NodeCmdExample();
        nodeCmdExample.createCriteria().andNodeIdEqualTo(nodeId);
        return nodeCmdMapper.selectByExample(nodeCmdExample);
    }

    @Override
    public void deleteByNcId(Long ncId) {
        nodeActCmdService.delNodeActCmdByNcId(ncId);
        nodeCmdMapper.deleteByPrimaryKey(ncId);
    }

    @Override
    public void deleteByNodeId(Long nodeId) {
        List<Long> ncIds = nodeCmdCustomMapper.getNcIdsByNodeId(nodeId);
        NodeCmdExample nodeCmdExample = new NodeCmdExample();
        nodeCmdExample.createCriteria().andNodeIdEqualTo(nodeId);
        nodeCmdMapper.deleteByExample(nodeCmdExample);
    }

    @Override
    public NodeCmd updateByNcId(Long ncId, NodeCmdParam nodeCmdParam) {
        //检查是否存在此节点命令
        NodeCmd nodeCmd = getNodeCmdById(ncId);
        NodeCmdExample nodeCmdExample = new NodeCmdExample();
        if (StringUtils.isNotBlank(nodeCmdParam.getNcName())) {
            nodeCmdExample.or().andNcContentEqualTo(nodeCmdParam.getNcName());
        }
        if (StringUtils.isNotBlank(nodeCmdParam.getNcContent())) {
            nodeCmdExample.or().andNcContentEqualTo(nodeCmdParam.getNcContent());
        }
        List<NodeCmd> nodeCmds = nodeCmdMapper.selectByExample(nodeCmdExample);
        if (nodeCmds.size() > 0) {
            throw new BusinessException(-1, "该节点命令名称或节点命令内容存在");
        }
        nodeCmd.setNcId(ncId);
        nodeCmd.setNcContent(StringUtils.isNotBlank(nodeCmdParam.getNcContent()) ? nodeCmdParam.getNcContent() : null);
        nodeCmd.setNcName(StringUtils.isNotBlank(nodeCmdParam.getNcName()) ? nodeCmdParam.getNcName() : null);
        nodeCmdMapper.updateByPrimaryKeySelective(nodeCmd);
        return nodeCmd;
    }

    @Override
    public int delBySceneSn(String sceneSn) {
        int res = 0;
        //查询该场景所有节点命令Id
        List<Long> ncIds = nodeCmdCustomMapper.getNcIdsBySceneSn(sceneSn);
        NodeCmdExample nodeCmdExample = new NodeCmdExample();
        nodeCmdExample.createCriteria().andSceneSnEqualTo(sceneSn);
        res = nodeCmdMapper.deleteByExample(nodeCmdExample);
        if(!Objects.isNull(ncIds)&&ncIds.size()>0){
            nodeActCmdService.delNodeActCmdByNcIds(ncIds);
        }
        return res;

    }

    @Override
    public List<Long> getNcIdsByNodeId(Long nodeId) {
        return nodeCmdCustomMapper.getNcIdsByNodeId(nodeId);
    }
}
