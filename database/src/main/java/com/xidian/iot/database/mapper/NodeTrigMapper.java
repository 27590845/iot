package com.xidian.iot.database.mapper;

import com.xidian.iot.database.entity.NodeTrig;
import com.xidian.iot.database.entity.NodeTrigExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface NodeTrigMapper {
    long countByExample(NodeTrigExample example);

    int deleteByExample(NodeTrigExample example);

    int deleteByPrimaryKey(Long ntId);

    int insert(NodeTrig record);

    int insertSelective(NodeTrig record);

    List<NodeTrig> selectByExample(NodeTrigExample example);

    NodeTrig selectByPrimaryKey(Long ntId);

    int updateByExampleSelective(@Param("record") NodeTrig record, @Param("example") NodeTrigExample example);

    int updateByExample(@Param("record") NodeTrig record, @Param("example") NodeTrigExample example);

    int updateByPrimaryKeySelective(NodeTrig record);

    int updateByPrimaryKey(NodeTrig record);
}