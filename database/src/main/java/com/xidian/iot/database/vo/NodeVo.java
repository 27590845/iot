package com.xidian.iot.database.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.xidian.iot.database.entity.Node;
import com.xidian.iot.database.entity.NodeAttr;
import com.xidian.iot.database.entity.NodeCmd;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * 返回给前端展示的node及其属性
 * @author: Hansey
 * @date: 2020-09-16 20:28
 */
@Data
@JsonIgnoreProperties(value = {"handler"})
public class NodeVo extends Node implements Serializable {

    public NodeVo() {
    }

    public NodeVo(Node node) {
        setSceneId(node.getSceneId());
        setSceneSn(node.getSceneSn());
        setNodeDesc(node.getNodeDesc());
        setNodeId(node.getNodeId());
        setNodeName(node.getNodeName());
        setCreateTime(node.getCreateTime());
        setNodeSn(node.getNodeSn());
        setNodeAttrname(node.getNodeAttrname());
    }

    public NodeVo(List<NodeAttr> nodeAttrList) {
        this.nodeAttrList = nodeAttrList;
    }

    public List<NodeAttr> nodeAttrList;

    public List<NodeCmd> nodeCmdList;

}
