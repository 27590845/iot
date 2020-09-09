package com.xidian.iot.database.mapper;

import com.xidian.iot.database.pojo.NodeCond;
import com.xidian.iot.database.pojo.NodeCondExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface NodeCondMapper {
    long countByExample(NodeCondExample example);

    int deleteByExample(NodeCondExample example);

    int deleteByPrimaryKey(Long ncId);

    int insert(NodeCond record);

    int insertSelective(NodeCond record);

    List<NodeCond> selectByExample(NodeCondExample example);

    NodeCond selectByPrimaryKey(Long ncId);

    int updateByExampleSelective(@Param("record") NodeCond record, @Param("example") NodeCondExample example);

    int updateByExample(@Param("record") NodeCond record, @Param("example") NodeCondExample example);

    int updateByPrimaryKeySelective(NodeCond record);

    int updateByPrimaryKey(NodeCond record);
}