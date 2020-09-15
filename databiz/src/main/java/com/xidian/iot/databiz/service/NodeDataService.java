package com.xidian.iot.databiz.service;

import com.xidian.iot.database.entity.mongo.NodeData;

import java.util.List;

/**
 * @author mrl
 * @Title: NodeDataService
 * @Package
 * @Description: 提供了NodeData的常规操作（NodeData存储在mongo中）
 * @date 2020/9/11 2:04 下午
 */
public interface NodeDataService {

    /**
     * 增加一条datastreams数据
     * @param nodeData
     */
    void addNodeData(NodeData nodeData);

    /**
     * 批量添加datastream数据
     * @param nodeDataList
     */
    void addNodeData(List<NodeData> nodeDataList);
}
