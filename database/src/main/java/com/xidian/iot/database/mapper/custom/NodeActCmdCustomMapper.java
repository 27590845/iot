package com.xidian.iot.database.mapper.custom;

import com.xidian.iot.database.entity.NodeActCmd;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author mrl
 * @Title: NodeActCmdCustomMapper
 * @Package
 * @Description:
 * @date 2020/9/21 10:10 下午
 */
public interface NodeActCmdCustomMapper {

    int addBatch(@Param("nacs") List<NodeActCmd> nodeActCmds);
}
