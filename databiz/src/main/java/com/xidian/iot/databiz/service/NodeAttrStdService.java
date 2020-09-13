package com.xidian.iot.databiz.service;

import com.xidian.iot.database.entity.NodeAttr;
import com.xidian.iot.database.entity.NodeAttrStd;

import java.util.List;

/**
 * 节点属性模版业务逻辑类
 * @author: Hansey
 * @date: 2020-09-13 11:18
 */
public interface NodeAttrStdService {

    /**
     *  分页获取节点属性模版
     * @param page
     * @param limit
     * @return java.util.List<com.xidian.iot.database.entity.NodeAttrStd>
     * */
    List<NodeAttrStd> getNodeAttrStds(int page, int limit);

    /**
     *  获取指定节点属性模版
     * @param nasId
     * @return com.xidian.iot.database.entity.NodeAttrStd
     * */
    NodeAttrStd getNodeAttrStd(Long nasId);
}
