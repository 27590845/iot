package com.xidian.iot.databiz.service;

import com.xidian.iot.database.param.NodeTrigParam;

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
    int addRuleEngine(NodeTrigParam nodeTrigParam);

    /**
     * 根据nodeTrig.ntId删除一条规则，并级联删除触发器关联的nodeCond，nodeActCmd，以及清理缓存
     * @param ntId
     * @return
     */
    int delRuleEngine(Long ntId);
}