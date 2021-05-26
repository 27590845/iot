package com.xidian.iot.databiz.service.impl;

import com.xidian.iot.common.util.exception.BusinessException;
import com.xidian.iot.database.entity.NodeActCmd;
import com.xidian.iot.database.entity.NodeActCmdExample;
import com.xidian.iot.database.entity.NodeAttr;
import com.xidian.iot.database.entity.NodeCmd;
import com.xidian.iot.database.mapper.NodeActCmdMapper;
import com.xidian.iot.database.mapper.custom.NodeActCmdCustomMapper;
import com.xidian.iot.databiz.service.NodeActCmdService;
import com.xidian.iot.databiz.service.NodeCmdService;
import com.xidian.iot.databiz.service.UidGenerator;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

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
    private NodeCmdService nodeCmdService;
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
        // 将重复的NcId取出来
        List<Long> repeNcIds = nodeActCmds.stream()
                .map(e -> e.getNcId()).collect(Collectors.toMap(e -> e, e -> 1, (a, b) -> a + b))
                .entrySet().stream()
                .filter(entry -> entry.getValue() > 1)
                .map(entry -> entry.getKey())
                .collect(Collectors.toList());
        if (repeNcIds.size() > 0) {
            throw new BusinessException(-1, "传入的" + repeNcIds.toString() + "，这些节点命令重复、请移除重复命令后重新添加");
        }
        // 判断列表中的ncId是否都存在、直接根据na_id查出对应的数量、如果数量匹配则说明都存在
        List<Long> ncIds = nodeActCmds.stream()
                .map(e->e.getNcId())
                .collect(Collectors.toList());
        List<NodeCmd> alreadyExisted = nodeCmdService.getNodeCmdsByNCIds(ncIds);
        // 数量不匹配就找出不存在的ncId、抛出异常
        if(alreadyExisted.size()!=nodeActCmds.size()){
            //将alreadyExisted中的naKeys转化为Long类型的List
            List<Long> existedNcIds = alreadyExisted.stream().map(e -> e.getNcId()).collect(Collectors.toList());
            //过滤传入naKeys在该节点命令中实际未存在的属性
            List<Long> notExistedKeys = ncIds.stream().filter(item -> !existedNcIds.contains(item)).collect(Collectors.toList());
            throw new BusinessException(-1, "传入的" + notExistedKeys.toString() + "，这些节点命令不存在、请确认后重新添加");
        }
        nodeActCmds.stream().forEach(nac -> nac.setNacId(uidGenerator.getUID()));
        return nodeActCmdCustomMapper.addBatch(nodeActCmds);
    }

    public int delNodeActCmdByNcId(Long ncId) {
        NodeActCmdExample nodeActCmdExample = new NodeActCmdExample();
        nodeActCmdExample.createCriteria().andNcIdEqualTo(ncId);
        return nodeActCmdMapper.deleteByExample(nodeActCmdExample);
    }

    @Override
    public int delNodeActCmdByNcIds(List<Long> ncIds) {
        NodeActCmdExample nodeActCmdExample = new NodeActCmdExample();
        nodeActCmdExample.createCriteria().andNcIdIn(ncIds);
        return nodeActCmdMapper.deleteByExample(nodeActCmdExample);
    }

    @Override
    public int updateNodeActCmds(List<NodeActCmd> nodeActCmds) {
        return nodeActCmdCustomMapper.updateBatch(nodeActCmds);
    }
}
