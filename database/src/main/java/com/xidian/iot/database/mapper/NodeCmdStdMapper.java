package com.xidian.iot.database.mapper;

import com.xidian.iot.database.pojo.NodeCmdStd;
import com.xidian.iot.database.pojo.NodeCmdStdExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface NodeCmdStdMapper {
    long countByExample(NodeCmdStdExample example);

    int deleteByExample(NodeCmdStdExample example);

    int deleteByPrimaryKey(Integer ncsId);

    int insert(NodeCmdStd record);

    int insertSelective(NodeCmdStd record);

    List<NodeCmdStd> selectByExample(NodeCmdStdExample example);

    NodeCmdStd selectByPrimaryKey(Integer ncsId);

    int updateByExampleSelective(@Param("record") NodeCmdStd record, @Param("example") NodeCmdStdExample example);

    int updateByExample(@Param("record") NodeCmdStd record, @Param("example") NodeCmdStdExample example);

    int updateByPrimaryKeySelective(NodeCmdStd record);

    int updateByPrimaryKey(NodeCmdStd record);
}