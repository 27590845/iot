package com.xidian.iot.database.mapper;

import com.xidian.iot.database.pojo.NodeCmdProt;
import com.xidian.iot.database.pojo.NodeCmdProtExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface NodeCmdProtMapper {
    long countByExample(NodeCmdProtExample example);

    int deleteByExample(NodeCmdProtExample example);

    int deleteByPrimaryKey(Integer ncpId);

    int insert(NodeCmdProt record);

    int insertSelective(NodeCmdProt record);

    List<NodeCmdProt> selectByExample(NodeCmdProtExample example);

    NodeCmdProt selectByPrimaryKey(Integer ncpId);

    int updateByExampleSelective(@Param("record") NodeCmdProt record, @Param("example") NodeCmdProtExample example);

    int updateByExample(@Param("record") NodeCmdProt record, @Param("example") NodeCmdProtExample example);

    int updateByPrimaryKeySelective(NodeCmdProt record);

    int updateByPrimaryKey(NodeCmdProt record);
}