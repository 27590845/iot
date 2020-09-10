package com.xidian.iot.database.mapper;

import com.xidian.iot.database.entity.NodeCmdGroup;
import com.xidian.iot.database.entity.NodeCmdGroupExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface NodeCmdGroupMapper {
    long countByExample(NodeCmdGroupExample example);

    int deleteByExample(NodeCmdGroupExample example);

    int deleteByPrimaryKey(Long ncgId);

    int insert(NodeCmdGroup record);

    int insertSelective(NodeCmdGroup record);

    List<NodeCmdGroup> selectByExample(NodeCmdGroupExample example);

    NodeCmdGroup selectByPrimaryKey(Long ncgId);

    int updateByExampleSelective(@Param("record") NodeCmdGroup record, @Param("example") NodeCmdGroupExample example);

    int updateByExample(@Param("record") NodeCmdGroup record, @Param("example") NodeCmdGroupExample example);

    int updateByPrimaryKeySelective(NodeCmdGroup record);

    int updateByPrimaryKey(NodeCmdGroup record);
}