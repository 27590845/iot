package com.xidian.iot.database.entity;

import java.util.Date;

/**
 * 节点表，一个场景下有多个节点。逻辑上可看作一个数据单元；物理上可看作数据采集设备，比如采集卡
 *
 * @author xidianiot
 * @date   2021/01/21
 */
public class Node {
    /**
     * 节点ID
     */
    private Long nodeId;

    /**
     * 场景ID，一个节点对应一个场景，一个场景可包含多个节点
     */
    private Long sceneId;

    /**
     * 网关SN
     */
    private String sceneSn;

    /**
     * 节点名称
     */
    private String nodeName;

    /**
     * 描述
     */
    private String nodeDesc;

    /**
     * 节点物理节点号
     */
    private String nodeSn;

    /**
     * 节点映射实际地址
     */
    private String nodeMap;

    /**
     * 创建时间
     */
    private Date createTime;

    public Long getNodeId() {
        return nodeId;
    }

    public void setNodeId(Long nodeId) {
        this.nodeId = nodeId;
    }

    public Long getSceneId() {
        return sceneId;
    }

    public void setSceneId(Long sceneId) {
        this.sceneId = sceneId;
    }

    public String getSceneSn() {
        return sceneSn;
    }

    public void setSceneSn(String sceneSn) {
        this.sceneSn = sceneSn == null ? null : sceneSn.trim();
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName == null ? null : nodeName.trim();
    }

    public String getNodeDesc() {
        return nodeDesc;
    }

    public void setNodeDesc(String nodeDesc) {
        this.nodeDesc = nodeDesc == null ? null : nodeDesc.trim();
    }

    public String getNodeSn() {
        return nodeSn;
    }

    public void setNodeSn(String nodeSn) {
        this.nodeSn = nodeSn == null ? null : nodeSn.trim();
    }

    public String getNodeMap() {
        return nodeMap;
    }

    public void setNodeMap(String nodeMap) {
        this.nodeMap = nodeMap == null ? null : nodeMap.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}