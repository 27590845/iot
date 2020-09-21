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
}
