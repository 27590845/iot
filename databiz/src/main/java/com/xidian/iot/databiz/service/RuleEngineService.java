package com.xidian.iot.databiz.service;

import com.xidian.iot.database.entity.NodeCond;
import com.xidian.iot.database.param.NodeCondParam;
import com.xidian.iot.database.param.NodeTrigParam;

import java.util.List;

/**
 * @author mrl
 * @Title: RuleEngineService
 * @Package
 * @Description: 规则引擎服务类
 * @date 2020/9/21 11:25 下午
 */
public interface RuleEngineService {

    /**
     * 添加一套规则引擎，包含nodeActCmd的添加，nodeCond的添加，nodeTrig的添加，以及一些逻辑校验
     * @param nodeTrigParam
     * @return
     */
    NodeTrigParam addRuleEngine(NodeTrigParam nodeTrigParam);

    /**
     * 根据nodeTrig.ntId删除一条规则，并级联删除触发器关联的nodeCond，nodeActCmd，以及清理缓存
     * @param ntId
     * @return
     */
    int delRuleEngine(Long ntId);

    /**
     * 根据节点触发ncId删除对应的节点触发规则
     * @param ncId
     * @return int
     * */
    int delNodeCondByNcId(Long ncId);

    /**
     * 根据节点触发ncId同时删除多个节点触发规则、同时清理缓存
     * @param ncIds
     * @return int
     * */
    int delNodeCondByNcIds(List<Long> ncIds);

    /**
     * 更新节点触发规则
     * @param ntId
     * @param nodeTrigParam
     * @return void
     * */
    void updateRuleEngine(Long ntId, NodeTrigParam nodeTrigParam);

    /**
     * 添加单个节点触发规则、首先先判断该触发器是否已经有此节点触发规则
     * @param ntId
     * @param nodeCond
     * @return com.xidian.iot.database.entity.NodeCond
     * */
    NodeCond addNodeCond(Long ntId, NodeCond nodeCond);

    /**
     * 首先看是否存在
     * @param ntId
     * @return com.xidian.iot.database.param.NodeTrigParam
     * */
    NodeTrigParam getRuleEngine(Long ntId);

    /**
     * 添加一套规则引擎，包含nodeActCmd的添加，nodeCond的添加，nodeTrig的添加，以及一些逻辑校验
     * 相比addRuleEngine支持同时增加多条nodeActAlert
     * @param nodeTrigParam
     * @return
     */
    NodeTrigParam addRuleEngineAndNaa(NodeTrigParam nodeTrigParam);


    /**
     * 更新节点触发规则,包含多条nodeActAlert
     * @param ntId
     * @param nodeTrigParam
     * @return void
     * */
    void updateRuleEngineAndNaa(Long ntId, NodeTrigParam nodeTrigParam);
}
