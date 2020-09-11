package com.xidian.iot.databiz.service;

import com.xidian.iot.database.entity.NodeAttr;

/**
 * @author mrl
 * @Title: NodeAttrService
 * @Package
 * @Description: NodeAttr 相关服务
 * @date 2020/9/11 7:54 下午
 */
public interface NodeAttrService {

    /**
     * 根据naId获取NodeAttr
     * @param naId
     * @return
     */
    NodeAttr getNodeAttrById(Long naId);
}
