package com.xidian.iot.databiz.service;

import com.xidian.iot.database.entity.NodeCmdGroupStd;

import java.util.List;

/**
 * NodeCmdGroupStd节点命令组名称模版
 * @author: Hansey
 * @date: 2020-09-21 14:51
 */
public interface NodeCmdGroupStdService {

    /**
     * 获取当前系统内节点命令组名称模版的总数量
     * @param
     * @return int
     * */
    int getCount();

    /**
     * 分页获取系统内节点命令组名称模版
     * @param page 页码
     * @param limit 每页数量
     * @return java.util.List<com.xidian.iot.database.entity.NodeCmdGroupStd>
     * */
    List<NodeCmdGroupStd> getNodeCmdGroupStds(int page, int limit);
}
