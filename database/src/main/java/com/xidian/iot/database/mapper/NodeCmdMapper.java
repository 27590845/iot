package com.xidian.iot.database.mapper;

import com.xidian.iot.database.pojo.NodeCmd;
import com.xidian.iot.database.pojo.NodeCmdExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface NodeCmdMapper {
    long countByExample(NodeCmdExample example);

    int deleteByExample(NodeCmdExample example);

    int deleteByPrimaryKey(Long ncId);

    int insert(NodeCmd record);

    int insertSelective(NodeCmd record);

    List<NodeCmd> selectByExample(NodeCmdExample example);

    NodeCmd selectByPrimaryKey(Long ncId);

    int updateByExampleSelective(@Param("record") NodeCmd record, @Param("example") NodeCmdExample example);

    int updateByExample(@Param("record") NodeCmd record, @Param("example") NodeCmdExample example);

    int updateByPrimaryKeySelective(NodeCmd record);

    int updateByPrimaryKey(NodeCmd record);
}