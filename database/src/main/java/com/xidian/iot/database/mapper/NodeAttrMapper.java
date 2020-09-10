package com.xidian.iot.database.mapper;

import com.xidian.iot.database.entity.NodeAttr;
import com.xidian.iot.database.entity.NodeAttrExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface NodeAttrMapper {
    long countByExample(NodeAttrExample example);

    int deleteByExample(NodeAttrExample example);

    int deleteByPrimaryKey(Long naId);

    int insert(NodeAttr record);

    int insertSelective(NodeAttr record);

    List<NodeAttr> selectByExample(NodeAttrExample example);

    NodeAttr selectByPrimaryKey(Long naId);

    int updateByExampleSelective(@Param("record") NodeAttr record, @Param("example") NodeAttrExample example);

    int updateByExample(@Param("record") NodeAttr record, @Param("example") NodeAttrExample example);

    int updateByPrimaryKeySelective(NodeAttr record);

    int updateByPrimaryKey(NodeAttr record);
}