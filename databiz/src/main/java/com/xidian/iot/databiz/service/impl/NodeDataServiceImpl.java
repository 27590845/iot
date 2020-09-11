package com.xidian.iot.databiz.service.impl;

import com.xidian.iot.database.entity.mongo.NodeData;
import com.xidian.iot.databiz.service.NodeDataService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author mrl
 * @Title: NodeDataImpl
 * @Package
 * @Description:
 * @date 2020/9/11 2:19 下午
 */
@Service
public class NodeDataServiceImpl implements NodeDataService {

    @Value("${mongo.nodedata.collection.name}")
    private String collectionName;

    @Resource
    private MongoTemplate mongoTemplate;

    @Override
    public void addNodeData(NodeData nodeData) {
        mongoTemplate.insert(nodeData, collectionName);
    }

    @Override
    public void addNodeData(List<NodeData> nodeDataList) {
        mongoTemplate.insert(nodeDataList, collectionName);
    }
}
