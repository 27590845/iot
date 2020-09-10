package com.xidian.iot.database.mapper;

import com.xidian.iot.database.entity.Node;
import com.xidian.iot.database.entity.NodeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface NodeMapper {
    long countByExample(NodeExample example);

    int deleteByExample(NodeExample example);

    int deleteByPrimaryKey(Long nodeId);

    int insert(Node record);

    int insertSelective(Node record);

    List<Node> selectByExample(NodeExample example);

    Node selectByPrimaryKey(Long nodeId);

    int updateByExampleSelective(@Param("record") Node record, @Param("example") NodeExample example);

    int updateByExample(@Param("record") Node record, @Param("example") NodeExample example);

    int updateByPrimaryKeySelective(Node record);

    int updateByPrimaryKey(Node record);
}