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
    List<Long> getNcIdsBySn(String sceneSn, String nodeSn);

    /**
     * 根据nodeTrig.ntId获取关联的NodeCond
     * @param ntId
     * @return
     */
    List<Long> getNcIdsByNtId(Long ntId);

    /**
     * 通过nodeCond.ncId获取NodeCondExt
     * @param ncId
     * @return
     */
    NodeCondExt getNodeCondExtById(Long ncId);

    /**
     * 更新条件的condition 只更新到缓存
     * @param nodeCondExt 条件
     */
    NodeCondExt changeNodeCondExt(NodeCondExt nodeCondExt);

    /**
     * 根据nodeCond.ncId删除缓存中的nodeCond
     * @param ncId
     * @return
     */
    void cleanNodeCondById(Long ncId);

    /**
     * 通过NodeTrig.ntId获取nodeCond列表
     * @param ntId
     * @return
     */
    List<NodeCond> getNodeCondsByNtId(Long ntId);

    /**
     * 批量添加NodeCond 应保证除nodeCond.ncId，其他字段非空，否则以后会出问题
     * @param nodeConds
     * @return
     */
    int addNodeConds(List<NodeCond> nodeConds);

    /**
     * 根据nodeCond.ntId删除关联的NodeCond
     * @param ntId
     * @return
     */
    int delNodeCondByNtId(Long ntId);
}
