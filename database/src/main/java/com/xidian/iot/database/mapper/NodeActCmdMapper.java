package com.xidian.iot.database.mapper;

import com.xidian.iot.database.pojo.NodeActCmd;
import com.xidian.iot.database.pojo.NodeActCmdExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface NodeActCmdMapper {
    long countByExample(NodeActCmdExample example);

    int deleteByExample(NodeActCmdExample example);

    int deleteByPrimaryKey(Long nacId);

    int insert(NodeActCmd record);

    int insertSelective(NodeActCmd record);

    List<NodeActCmd> selectByExample(NodeActCmdExample example);

    NodeActCmd selectByPrimaryKey(Long nacId);

    int updateByExampleSelective(@Param("record") NodeActCmd record, @Param("example") NodeActCmdExample example);

    int updateByExample(@Param("record") NodeActCmd record, @Param("example") NodeActCmdExample example);

    int updateByPrimaryKeySelective(NodeActCmd record);

    int updateByPrimaryKey(NodeActCmd record);
}