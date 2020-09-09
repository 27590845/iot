package com.xidian.iot.database.mapper;

import com.xidian.iot.database.pojo.NodeAttrStd;
import com.xidian.iot.database.pojo.NodeAttrStdExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface NodeAttrStdMapper {
    long countByExample(NodeAttrStdExample example);

    int deleteByExample(NodeAttrStdExample example);

    int deleteByPrimaryKey(Long nasId);

    int insert(NodeAttrStd record);

    int insertSelective(NodeAttrStd record);

    List<NodeAttrStd> selectByExample(NodeAttrStdExample example);

    NodeAttrStd selectByPrimaryKey(Long nasId);

    int updateByExampleSelective(@Param("record") NodeAttrStd record, @Param("example") NodeAttrStdExample example);

    int updateByExample(@Param("record") NodeAttrStd record, @Param("example") NodeAttrStdExample example);

    int updateByPrimaryKeySelective(NodeAttrStd record);

    int updateByPrimaryKey(NodeAttrStd record);
}