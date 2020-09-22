package com.xidian.iot.databiz.service;

import com.xidian.iot.database.entity.NodeActCmd;

import java.util.List;

/**
 * @author mrl
 * @Title: NodeActCmdService
 * @Package
 * @Description: NodeActCmd相关服务
 * @date 2020/9/11 8:33 下午
 */
public interface NodeActCmdService {

    /**
     * 根据nodeTrig.ntId获取NodeActCmd
     * @param ntId
     * @return
     */
    List<NodeActCmd> getNodeActCmdByNtId(Long ntId);

    /**
     * 根据nodeActCmd.ntId删除nodeActCmd，(无清缓存操作)
     * @param ntId
     * @return
     */
    int delNodeActCmdByNtId(Long ntId);

    /**
     * 批量添加nodeActCmd 应保证nodeActCmd.ncId非空
     * @param nodeActCmds
     * @return
     */
    int addNodeActCmds(List<NodeActCmd> nodeActCmds);
}
