package com.xidian.iot.database.mapper.custom;

import com.xidian.iot.database.entity.NodeCmd;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author mrl
 * @Title: NodeCmdCustomMapper
 * @Package
 * @Description:
 * @date 2020/9/22 10:29 上午
 */
public interface NodeCmdCustomMapper {

    int addBatch(@Param("ncs")List<NodeCmd> nodeCmds);
}
