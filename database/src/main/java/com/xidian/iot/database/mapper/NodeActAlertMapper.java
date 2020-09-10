package com.xidian.iot.database.mapper;

import com.xidian.iot.database.entity.NodeActAlert;
import com.xidian.iot.database.entity.NodeActAlertExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface NodeActAlertMapper {
    long countByExample(NodeActAlertExample example);

    int deleteByExample(NodeActAlertExample example);

    int deleteByPrimaryKey(Long naaId);

    int insert(NodeActAlert record);

    int insertSelective(NodeActAlert record);

    List<NodeActAlert> selectByExample(NodeActAlertExample example);

    NodeActAlert selectByPrimaryKey(Long naaId);

    int updateByExampleSelective(@Param("record") NodeActAlert record, @Param("example") NodeActAlertExample example);

    int updateByExample(@Param("record") NodeActAlert record, @Param("example") NodeActAlertExample example);

    int updateByPrimaryKeySelective(NodeActAlert record);

    int updateByPrimaryKey(NodeActAlert record);
}