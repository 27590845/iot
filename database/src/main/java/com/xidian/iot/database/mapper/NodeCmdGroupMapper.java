package com.xidian.iot.database.mapper;

import com.xidian.iot.database.pojo.NodeCmdGroup;
import com.xidian.iot.database.pojo.NodeCmdGroupExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface NodeCmdGroupMapper {
    long countByExample(NodeCmdGroupExample example);

    int deleteByExample(NodeCmdGroupExample example);

    int deleteByPrimaryKey(Integer ncgId);

    int insert(NodeCmdGroup record);

    int insertSelective(NodeCmdGroup record);

    List<NodeCmdGroup> selectByExample(NodeCmdGroupExample example);

    NodeCmdGroup selectByPrimaryKey(Integer ncgId);

    int updateByExampleSelective(@Param("record") NodeCmdGroup record, @Param("example") NodeCmdGroupExample example);

    int updateByExample(@Param("record") NodeCmdGroup record, @Param("example") NodeCmdGroupExample example);

    int updateByPrimaryKeySelective(NodeCmdGroup record);

    int updateByPrimaryKey(NodeCmdGroup record);
}