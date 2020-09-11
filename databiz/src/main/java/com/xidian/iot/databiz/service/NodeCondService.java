package com.xidian.iot.databiz.service;

import com.xidian.iot.database.entity.NodeCond;

import java.util.List;

/**
 * @author mrl
 * @Title: NodeCondService
 * @Package
 * @Description:
 * @date 2020/9/10 9:52 下午
 */
public interface NodeCondService {

    /**
     * 根据nodeId获取NodeCond列表
     * @param nodeSn
     * @return
     */
    List<NodeCond> getByNodeId(String nodeSn);

    /**
     * 根据nodeId获取可用的NodeCond列表：nodeTrig.getNtExec()==1||nodeTrig.getNtExpr().before(new Date())
     * @param nodeSn
     * @return
     */
    List<NodeCond> getByNodeSnAvl(String nodeSn);
}
