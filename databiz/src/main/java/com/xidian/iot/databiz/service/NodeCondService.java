package com.xidian.iot.databiz.service;

import com.xidian.iot.database.entity.NodeCond;
import com.xidian.iot.database.entity.custom.NodeCondExt;

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
     * 根据sceneSn和nodeSn获取可用的NodeCond列表：nodeTrig.getNtExec()==1||nodeTrig.getNtExpr().before(new Date())
     * @param sceneSn
     * @param nodeSn
     * @return
     */
    List<NodeCond> getNodeCondBySnAvl(String sceneSn, String nodeSn);

    /**
     * 根据sceneSn和nodeSn获取可用的NodeCond列表：nodeTrig.getNtExec()==1||nodeTrig.getNtExpr().before(new Date())
     * @param sceneSn
     * @param nodeSn
     * @return
     */
    List<NodeCondExt> getNodeCondExtBySnAvl(String sceneSn, String nodeSn);

    /**
     * 根据nodeTrig.ntId获取NodeCondExt
     * @param ntId
     * @return
     */
    List<NodeCondExt> getNodeCondExtByNtId(Long ntId);

    /**
     * 更新条件的condition 并更新到缓存
     * @param nodeCondExt 条件
     */
    NodeCondExt changeNodeCondExt(NodeCondExt nodeCondExt);
}
