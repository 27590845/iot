package com.xidian.iot.databiz.service;

import com.xidian.iot.database.entity.NodeCmdGroup;

/**
 * @author mrl
 * @Title: NodeCmdGroupService
 * @Package
 * @Description: NodeCmdGroup相关服务
 * @date 2020/9/11 8:31 下午
 */
public interface NodeCmdGroupService {

    /**
     * 根据nodeCmdGroup.ncgId获取NodeCmdGroup
     * @param ncgId
     * @return
     */
    NodeCmdGroup getNodeCmdGroupById(Long ncgId);
}
