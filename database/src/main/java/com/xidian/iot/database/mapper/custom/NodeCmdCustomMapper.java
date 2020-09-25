package com.xidian.iot.database.mapper.custom;

import com.xidian.iot.database.entity.NodeCmd;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author mrl
 * @Title: NodeCmdCustomMapper
 * @Package
 * @Description:
 * @date 2020/9/22 10:29 上午
 */
public interface NodeCmdCustomMapper {

    int addBatch(@Param("ncs")List<NodeCmd> nodeCmds);

    /**
     * 根据节点命令名称数组和节点Id获取
     * 该节点已经存在的节点命令名称
     * @param nodeId  节点Id
     * @param collect 节点命令名称数组
     * @return java.util.List<java.lang.String>
     * */
    List<String> getExistedNCN(@Param("nodeId")Long nodeId, @Param("collect")List<String> collect);

    /**
     * 根据节点命令内容数组和节点Id获取
     * @param nodeId 节点Id
     * @param collect 节点命令内容
     * @return java.util.List<java.lang.String>
     * */
    List<String> getExistedNCC(@Param("nodeId")Long nodeId, @Param("collect")List<String> collect);

    /**
     * 根据节点Id获取节点命令Id
     * @param nodeId 节点Id
     * @return java.util.List<java.lang.Long>
     * */
    List<Long> getNcIdsByNodeId(Long nodeId);
}
