package com.xidian.iot.databiz.service.impl;

import com.xidian.iot.common.util.exception.BusinessException;
import com.xidian.iot.database.entity.NodeActCmd;
import com.xidian.iot.database.entity.NodeCond;
import com.xidian.iot.database.entity.NodeTrig;
import com.xidian.iot.database.mapper.NodeTrigMapper;
import com.xidian.iot.database.mapper.custom.NodeTrigCustomMapper;
import com.xidian.iot.database.param.NodeActAlertParam;
import com.xidian.iot.database.param.NodeCondParam;
import com.xidian.iot.database.param.NodeTrigParam;
import com.xidian.iot.databiz.service.*;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author mrl
 * @Title: RuleEngineServiceImpl
 * @Package
 * @Description:
 * @date 2020/9/21 11:25 下午
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class RuleEngineServiceImpl implements RuleEngineService {

    @Resource
    private NodeTrigService nodeTrigService;
    @Resource
    private NodeCondService nodeCondService;
    @Resource
    private NodeActCmdService nodeActCmdService;
    @Resource
    private NodeActAlertService nodeActAlertService;
    @Resource
    private NodeTrigCustomMapper nodeTrigCustomMapper;

    @Override
    public NodeTrigParam addRuleEngine(NodeTrigParam nodeTrigParam) {
        int success = 0;
        nodeTrigService.addNodeTrig(nodeTrigParam);
        // 先判断是否有节点命令 如果有则直接事务回滚
        if (!Objects.isNull(nodeTrigParam.getNodeActCmdParams())) {
            checkReptCondition(nodeTrigParam);
            nodeTrigParam.getNodeActCmdParams().forEach(nac -> nac.setNtId(nodeTrigParam.getNtId()));
            //插入nodeActCmd列表
            success += nodeActCmdService.addNodeActCmds(nodeTrigParam.getNodeActCmdParams()
                    .stream().map(param -> (NodeActCmd) param).collect(Collectors.toList()));
        }
        nodeTrigParam.getNodeActAlertParam().setNtId(nodeTrigParam.getNtId());
        // 插入触发报警信息
        nodeActAlertService.addNodeActAlert(nodeTrigParam.getNodeActAlertParam());
        // 插入nodeCond列表 List<Child>不能直接转为List<Parent>
        nodeTrigParam.getNodeCondParams().forEach(nc -> nc.setNtId(nodeTrigParam.getNtId()));
        success += nodeCondService.addNodeConds(nodeTrigParam.getNodeCondParams()
                .stream().map(param -> (NodeCond) param).collect(Collectors.toList()));
        return nodeTrigParam;
    }

    @Override
    public int delRuleEngine(Long ntId) {
        //删除触发器关联的nodeActCmd
        int success = nodeActCmdService.delNodeActCmdByNtId(ntId);
        //获取触发器关联的nodeCond.ncId，然后手动清理缓存中的nodeCondExt
        List<Long> ncIds = nodeCondService.getNcIdsByNtId(ntId);
        ncIds.stream().forEach(ncId -> nodeCondService.cleanNodeCondById(ncId));
        //删除触发器关联的nodeCond
        success += nodeCondService.delNodeCondByNtId(ntId);
        //删除触发器
        success += nodeTrigService.delNodeTrigByNtId(ntId);
        //删除触发器报警信息
        success += nodeActAlertService.delNodeActAlertByNtId(ntId);
        return success;
    }

    void checkReptCondition(NodeTrigParam nodeTrigParam) {
        List<NodeCondParam> currNodeConds = nodeTrigParam.getNodeCondParams();
        // 获取和当前规则有相同联动命令的规则
        List<Long> ntIds = nodeTrigService.getNtIdsByNcIds(
                nodeTrigParam.getNodeActCmdParams().stream().map(nac -> nac.getNcId()).collect(Collectors.toList()));
        if (ntIds != null && ntIds.size() > 0) {
            ntIds.stream().forEach(ntId -> {
                List<NodeCond> nodeConds = nodeCondService.getNodeCondsByNtId(ntId);
                if (nodeConds != null && nodeConds.size() == currNodeConds.size() && currNodeConds.containsAll(nodeConds))
                    throw new BusinessException(-1, "不允许不同规则有相同的条件列表和联动命令条目");
            });
        }
    }

    @Override
    public int delNodeCondByNcId(Long ncId) {
        int res = nodeCondService.delNodeCondByNcId(ncId);
        //清理缓存 内部调用不走缓存
        nodeCondService.cleanNodeCondById(ncId);
        return res;
    }

    @Override
    public int delNodeCondByNcIds(List<Long> ncIds) {
        int res = nodeCondService.delNodeCondByNcIds(ncIds);
        for (Long ncId : ncIds) {
            nodeCondService.cleanNodeCondById(ncId);
        }
        return res;
    }

    @Override
    public void updateRuleEngine(Long ntId, NodeTrigParam nodeTrigParam) {
        if (Objects.isNull(nodeTrigService.getNodeTrigExtById(ntId))) {
            throw new BusinessException(-1, "该触发器不存在");
        }
        // 更新触发器
        nodeTrigService.updateNodeTrigById(nodeTrigParam);
        // 先判断是否有节点命令 如果有则直接事务回滚
        if (!Objects.isNull(nodeTrigParam.getNodeActCmdParams()) && nodeTrigParam.getNodeActCmdParams().size() > 0) {
            checkReptCondition(nodeTrigParam);
            // 更新nodeActCmd列表 也就是更新 ncId命令id
            nodeActCmdService.updateNodeActCmds(nodeTrigParam.getNodeActCmdParams()
                    .stream().map(param -> {
                        param.setNtId(ntId);
                        return (NodeActCmd) param;
                    }).collect(Collectors.toList()));
        }
        // 更新触发报警信息
        nodeActAlertService.updateNodeActAlert(nodeTrigParam.getNodeActAlertParam());
        // 更新nodeCond列表 List<Child>不能直接转为List<Parent>
        nodeCondService.updateNodeConds(nodeTrigParam.getNodeCondParams()
                .stream().map(param -> (NodeCond) param).collect(Collectors.toList()));
    }

    @Override
    public NodeCond addNodeCond(Long ntId, NodeCond nodeCondParam) {
        List<NodeCond> nodeConds = nodeCondService.getNodeCondsByNtId(ntId);
        // 判断新增触发条件所属的触发器中是否存在此触发条件（或者是同一个传感器而且操作符也相同）
        List<NodeCond> repeatNodeConds = nodeConds.stream().filter(nodeCond ->
                nodeCond.getNaId().equals(nodeCondParam.getNaId())
                        && nodeCond.getNcOp().equals(nodeCondParam.getNcOp())).collect(Collectors.toList());
        if (repeatNodeConds.size() > 0) {
            throw new BusinessException(-1, "该触发器已有传感器的触发符号存在，请检查后再添加");
        }
        nodeCondParam.setNtId(ntId);
        nodeCondService.addNodeCond(nodeCondParam);
        return nodeCondParam;
    }

    @Override
    public NodeTrigParam getRuleEngine(Long ntId) {
        // 先判断此ntId是否存在
        NodeTrig nodeTrig = nodeTrigService.getNodeTrigExtById(ntId);
        if (Objects.isNull(nodeTrig)) throw new BusinessException(-1, "该触发器不存在");
        NodeTrigParam nodeTrigParam = nodeTrigCustomMapper.getNodeTrigParamByNtId(ntId);
        return nodeTrigParam;
    }

    @Override
    public int updateNodeTrig(Long ntId, NodeTrig nodeTrig) {
        if (Objects.isNull(nodeTrigService.getNodeTrigExtById(ntId))) {
            throw new BusinessException(-1, "该触发器不存在");
        }
        return nodeTrigService.updateNodeTrigById(nodeTrig);
    }


    public void updateRuleEngine1(Long ntId, NodeTrigParam nodeTrigParam) {
        if (Objects.isNull(nodeTrigService.getNodeTrigExtById(ntId))) {
            throw new BusinessException(-1, "该触发器不存在");
        }
        // 更新触发器
        nodeTrigService.updateNodeTrigById(nodeTrigParam);
        // 先判断是否有节点命令 如果有则直接事务回滚
        if (!Objects.isNull(nodeTrigParam.getNodeActCmdParams()) && nodeTrigParam.getNodeActCmdParams().size() > 0) {
            checkReptCondition(nodeTrigParam);
            // 更新nodeActCmd列表 也就是更新 ncId命令id
            nodeActCmdService.updateNodeActCmds(nodeTrigParam.getNodeActCmdParams()
                    .stream().map(param -> {
                        param.setNtId(ntId);
                        return (NodeActCmd) param;
                    }).collect(Collectors.toList()));
        }
        // 更新触发报警信息(nodeactalert)
        nodeActAlertService.updateNodeActAlert(nodeTrigParam.getNodeActAlertParam());
        //以下操作nodecond表
        //已有的
        List<NodeCondParam> existNodeCond = nodeTrigParam.getNodeCondParams().stream().filter(nodeCondParam -> !Objects.isNull(nodeCondParam.getNcId())).collect(Collectors.toList());
        //新增的
        List<NodeCondParam> newNodeCond = nodeTrigParam.getNodeCondParams().stream().filter(nodeCondParam -> Objects.isNull(nodeCondParam.getNcId())).collect(Collectors.toList());
        //1.删除
        List<Long> delNcIds = nodeCondService.getNcIdsByNtId(nodeTrigParam.getNtId());
        int count = delNcIds.size();
        for(NodeCondParam nodeCondParam:existNodeCond){
            for(int i=0;i<delNcIds.size();i++){
                if(delNcIds.get(i).equals(nodeCondParam.getNcId())) {
                    delNcIds.remove(i);
                    i--;
                }
            }
        }
        if(delNcIds.size()>0){
            if(count == delNcIds.size() && newNodeCond.size() == 0){
                throw new BusinessException(-1, "触发条件不可全部删除");
            }
            nodeCondService.delNodeCondByNcIds(delNcIds);
        }
        //2.新增
        if(newNodeCond.size()>0) {
            nodeCondService.addNodeConds(newNodeCond.stream().map(param -> (NodeCond) param).collect(Collectors.toList()));
        }
        //3.更新
        if(existNodeCond.size()>0) {
            nodeCondService.updateNodeConds(existNodeCond.stream().map(param -> (NodeCond) param).collect(Collectors.toList()));
        }
        //删除缓存
        nodeCondService.cleanNcIdsByNtId(ntId);
    }
}
