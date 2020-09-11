package com.xidian.iot.databiz.service;

import com.xidian.iot.database.entity.Node;
import com.xidian.iot.database.param.NodeAddParam;

/**
 * @author: Hansey
 * @date: 2020-09-10 21:45
 */
public interface NodeService {

    /** 
     * 添加节点
     * @param param
     * @return com.xidian.iot.database.entity.Node
     * */ 
    Node addNode(NodeAddParam param);
}
