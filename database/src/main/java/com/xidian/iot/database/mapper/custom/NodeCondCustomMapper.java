package com.xidian.iot.database.mapper.custom;

import com.xidian.iot.database.entity.NodeCond;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author mrl
 * @Title: NodeCondCustomMapper
 * @Package
 * @Description: 自定义NodeCond数据库查询
 * @date 2020/9/14 7:27 下午
 */
public interface NodeCondCustomMapper {

    List<Long> getNodeCondIdsAvl(@Param("sceneSn") String sceneSn, @Param("nodeSn") String nodeSn);
}
