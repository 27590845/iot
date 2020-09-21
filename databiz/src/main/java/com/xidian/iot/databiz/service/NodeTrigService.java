package com.xidian.iot.databiz.service;

import com.xidian.iot.database.entity.NodeTrig;
import com.xidian.iot.database.entity.custom.NodeTrigExt;
import com.xidian.iot.database.param.NodeTrigParam;

/**
 * @author mrl
 * @Title: NodeTrigService
 * @Package
 * @Description:
 * @date 2020/9/10 9:52 下午
 */
public interface NodeTrigService {

    /**
     * 根据NodeTrig.ntId获取NodeTrig的拓展类
     * @param ntId
     * @return
     */
    NodeTrigExt getNodeTrigExtById(Long ntId);

    /**
     * 通过Id更新NodeTrigExt，更新至数据库、缓存
     * @param nodeTrigExt
     * @return
     */
    NodeTrigExt updateNodeTrigExtById(NodeTrigExt nodeTrigExt);

    /**
     * 添加一套规则引擎，包含nodeActCmd的添加，nodeCond的添加，nodeTrig的添加，以及一些逻辑校验
     * @param nodeTrigParam
     * @return
     */
    int addRuleEngine(NodeTrigParam nodeTrigParam);
}
