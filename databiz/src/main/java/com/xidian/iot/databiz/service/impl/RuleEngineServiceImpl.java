package com.xidian.iot.databiz.service.impl;

import com.xidian.iot.common.util.exception.BusinessException;
import com.xidian.iot.database.entity.NodeActCmd;
import com.xidian.iot.database.entity.NodeCond;
import com.xidian.iot.database.param.NodeCondParam;
import com.xidian.iot.database.param.NodeTrigParam;
import com.xidian.iot.databiz.service.NodeActCmdService;
import com.xidian.iot.databiz.service.NodeCondService;
import com.xidian.iot.databiz.service.NodeTrigService;
import com.xidian.iot.databiz.service.RuleEngineService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author mrl
 * @Title: RuleEngineServiceImpl
 * @Package
 * @Description:
 * @date 2020/9/21 11:25 下午
 */
@Service
public class RuleEngineServiceImpl implements RuleEngineService {

    @Resource
    private NodeTrigService nodeTrigService;
    @Resource
    private NodeCondService nodeCondService;
    @Resource
    private NodeActCmdService nodeActCmdService;

    @Override
    public int addRuleEngine(NodeTrigParam nodeTrigParam) {
        int success = 0;
        checkReptCondition(nodeTrigParam);
        success += nodeTrigService.addNodeTrig(nodeTrigParam);
        if(success<=0) throw new BusinessException(-1, "触发器插入失败");
        //插入nodeCond列表 List<Child>不能直接转为List<Parent>
        nodeTrigParam.getNodeCondParams().forEach(nc -> nc.setNtId(nodeTrigParam.getNtId()));
        success += nodeCondService.addNodeConds(nodeTrigParam.getNodeCondParams()
                .stream().map(param -> (NodeCond) param).collect(Collectors.toList()));
        //插入nodeActCmd列表
        nodeTrigParam.getNodeActCmdParams().forEach(nac -> nac.setNtId(nodeTrigParam.getNtId()));
        success += nodeActCmdService.addNodeActCmds(nodeTrigParam.getNodeActCmdParams()
                .stream().map(param -> (NodeActCmd) param).collect(Collectors.toList()));
        return success;
    }

    @Override
    public int delRuleEngine(Long ntId) {
        //删除触发器关联的nodeActCmd
        int success = nodeActCmdService.delNodeActCmdByNtId(ntId);
        //删除触发器关联的nodeCond
        success += nodeCondService.delNodeCondByNtId(ntId);
        //获取触发器关联的nodeCond.ncId，然后手动清理缓存中的nodeCondExt
        List<Long> ncIds = nodeCondService.getNcIdsByNtId(ntId);
        ncIds.stream().forEach(ncId -> nodeCondService.cleanNodeCondById(ncId));
        //删除触发器
        success += nodeTrigService.delNodeTrigByNtId(ntId);
        return success;
    }

    void checkReptCondition(NodeTrigParam nodeTrigParam){
        List<NodeCondParam> currNodeConds = nodeTrigParam.getNodeCondParams();
        //获取和当前规则有相同联动命令的规则
        List<Long> ntIds = nodeTrigService.getNtIdsByNcIds(
                nodeTrigParam.getNodeActCmdParams().stream().map(nac -> nac.getNcId()).collect(Collectors.toList()));
        if(ntIds!=null && ntIds.size()>0){
            ntIds.stream().forEach(ntId -> {
                List<NodeCond> nodeConds = nodeCondService.getNodeCondsByNtId(ntId);
                if(nodeConds!=null && nodeConds.size()==currNodeConds.size() && currNodeConds.containsAll(nodeConds))
                    throw new BusinessException(-1, "不允许不同规则有相同的条件列表和联动命令条目");
            });
        }
    }
}
