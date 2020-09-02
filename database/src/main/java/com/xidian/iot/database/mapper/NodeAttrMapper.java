package com.xidian.iot.database.mapper;

import com.xidian.iot.database.pojo.NodeAttr;
import com.xidian.iot.database.pojo.NodeAttrExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface NodeAttrMapper {
    long countByExample(NodeAttrExample example);

    int deleteByExample(NodeAttrExample example);

    int deleteByPrimaryKey(Integer naId);

    int insert(NodeAttr record);

    int insertSelective(NodeAttr record);

    List<NodeAttr> selectByExample(NodeAttrExample example);

    NodeAttr selectByPrimaryKey(Integer naId);

    int updateByExampleSelective(@Param("record") NodeAttr record, @Param("example") NodeAttrExample example);

    int updateByExample(@Param("record") NodeAttr record, @Param("example") NodeAttrExample example);

    int updateByPrimaryKeySelective(NodeAttr record);

    int updateByPrimaryKey(NodeAttr record);
}