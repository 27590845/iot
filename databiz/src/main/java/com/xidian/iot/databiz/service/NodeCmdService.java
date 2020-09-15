package com.xidian.iot.databiz.service;

import com.xidian.iot.database.entity.NodeCmd;

/**
 * @author mrl
 * @Title: NodeCmdService
 * @Package
 * @Description: NodeCmd相关服务
 * @date 2020/9/11 8:30 下午
 */
public interface NodeCmdService {

    /**
     * 根据nodeCmd.ncId获取NodeCmd
     * @param ncId
     * @return
     */
    NodeCmd getNodeCmdById(Long ncId);
}
