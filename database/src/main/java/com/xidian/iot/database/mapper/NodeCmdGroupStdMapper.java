package com.xidian.iot.database.mapper;

import com.xidian.iot.database.pojo.NodeCmdGroupStd;
import com.xidian.iot.database.pojo.NodeCmdGroupStdExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface NodeCmdGroupStdMapper {
    long countByExample(NodeCmdGroupStdExample example);

    int deleteByExample(NodeCmdGroupStdExample example);

    int deleteByPrimaryKey(Long ncgsId);

    int insert(NodeCmdGroupStd record);

    int insertSelective(NodeCmdGroupStd record);

    List<NodeCmdGroupStd> selectByExample(NodeCmdGroupStdExample example);

    NodeCmdGroupStd selectByPrimaryKey(Long ncgsId);

    int updateByExampleSelective(@Param("record") NodeCmdGroupStd record, @Param("example") NodeCmdGroupStdExample example);

    int updateByExample(@Param("record") NodeCmdGroupStd record, @Param("example") NodeCmdGroupStdExample example);

    int updateByPrimaryKeySelective(NodeCmdGroupStd record);

    int updateByPrimaryKey(NodeCmdGroupStd record);
}