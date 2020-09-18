package com.xidian.iot.databiz.service;

import com.xidian.iot.database.entity.NodeCond;
import com.xidian.iot.database.entity.custom.NodeCondExt;

import java.util.List;

/**
 * @author mrl
 * @Title: NodeCondService
 * @Package
 * @Description: 为了使用缓存，统一使用主键查询NodeCond相关信息，并提供获取其主键的常用函数
 * @date 2020/9/10 9:52 下午
 */
public interface NodeCondService {

    /**
     * 根据sceneSn和nodeSn获取可用的nodeCond.ncId列表：nodeTrig.getNtExec()==1||nodeTrig.getNtExpr().before(new Date())
     * @param sceneSn
     * @param nodeSn
     * @return
     */
    List<Long> getNodeCondIdsAvlBySn(String sceneSn, String nodeSn);

    /**
     * 根据nodeTrig.ntId获取关联的NodeCond
     * @param ntId
     * @return
     */
    List<Long> getNodeCondIdsByNtId(Long ntId);

    /**
     * 通过nodeCond.ncId获取NodeCondExt
     * @param ncId
     * @return
     */
    NodeCondExt getNodeCondExtById(Long ncId);

    /**
     * 根据sceneSn和nodeSn获取可用的NodeCond列表：nodeTrig.getNtExec()==1||nodeTrig.getNtExpr().before(new Date())
     * @param sceneSn
     * @param nodeSn
     * @return
     */
//    List<NodeCondExt> getNodeCondExtAvlBySn(String sceneSn, String nodeSn);

    /**
     * 根据nodeTrig.ntId获取NodeCondExt
     * @param ntId
     * @return
     */
//    List<NodeCondExt> getNodeCondExtByNtId(Long ntId);

    /**
     * 更新条件的condition 只更新到缓存
     * @param nodeCondExt 条件
     */
    NodeCondExt changeNodeCondExt(NodeCondExt nodeCondExt);

    /**
     * 添加一个NodeCond
     * @param nodeCond
     * @return
     */
    NodeCond addNodeCond(NodeCond nodeCond);
}
