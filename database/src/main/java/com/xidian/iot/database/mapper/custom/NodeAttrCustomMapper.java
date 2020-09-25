package com.xidian.iot.database.mapper.custom;

import com.xidian.iot.database.entity.NodeAttr;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 自定义节点属性mapper
 * @author: Hansey
 * @date: 2020-09-16 10:10
 */
public interface NodeAttrCustomMapper {

    int insertBatch(@Param("nodeAttrs") List<NodeAttr> nodeAttrs);

    List<NodeAttr> getNodeAttrByNodeId(@Param("nodeId") Long nodeId);

    List<Map<String, Object>> getNaSimplesBySn(@Param("sceneSn") String sceneSn, @Param("nodeSn") String nodeSn);

    /**
     * 根据节点Id获取该节点下所有的的节点属性Id
     * 目的是为了级联删除相关节点触发条件
     * @param nodeId 节点Id
     * @return java.util.List<java.lang.Long>
     * */
    List<Long> getNAIdsByNodeId(@Param("nodeId") Long nodeId);
}
