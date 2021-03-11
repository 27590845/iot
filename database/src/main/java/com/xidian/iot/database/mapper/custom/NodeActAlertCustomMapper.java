package com.xidian.iot.database.mapper.custom;

import com.xidian.iot.database.entity.NodeActAlert;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author guangyu
 * @Title: NodeActAlertCustomMapper
 * @Package
 * @Description
 * @date: 2021.3.9 19:23 晚上
 */
public interface NodeActAlertCustomMapper {
    int addBatch(@Param("naas") List<NodeActAlert> nodeActAlerts);

    int updateBatch(@Param("naas") List<NodeActAlert> nodeActAlerts);
}
