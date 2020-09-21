package com.xidian.iot.database.mapper.custom;

import java.util.List;

/**
 * @author mrl
 * @Title: NodeTrigCustomMapper
 * @Package
 * @Description: 自定义Nodetrig数据库操作接口
 * @date 2020/9/21 9:35 上午
 */
public interface NodeTrigCustomMapper {

    /**
     * 通过{@link com.xidian.iot.database.entity.NodeActCmd}.ncId列表获取NodeTrig.ntId列表
     * @param ncIds
     * @return
     */
    List<Long> getNtIdsByNcIds(List<Long> ncIds);
}
