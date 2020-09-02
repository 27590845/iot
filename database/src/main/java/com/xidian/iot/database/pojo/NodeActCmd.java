package com.xidian.iot.database.pojo;

/**
 * 一旦一个节点触发器被触发，可能会执行多个命令
 *
 * @author mrl
 * @date   2020/09/01
 */
public class NodeActCmd {
    /**
     * 
     */
    private Integer nacId;

    /**
     * 
     */
    private Integer ncId;

    /**
     * 
     */
    private Integer nodeId;

    /**
     * 
     */
    private Integer sceneId;

    /**
     * 
     */
    private Integer ntId;

    /**
     * 节点SN
     */
    private String nodeSn;

    /**
     * 场景SN
     */
    private String sceneSn;

    public Integer getNacId() {
        return nacId;
    }

    public void setNacId(Integer nacId) {
        this.nacId = nacId;
    }

    public Integer getNcId() {
        return ncId;
    }

    public void setNcId(Integer ncId) {
        this.ncId = ncId;
    }

    public Integer getNodeId() {
        return nodeId;
    }

    public void setNodeId(Integer nodeId) {
        this.nodeId = nodeId;
    }

    public Integer getSceneId() {
        return sceneId;
    }

    public void setSceneId(Integer sceneId) {
        this.sceneId = sceneId;
    }

    public Integer getNtId() {
        return ntId;
    }

    public void setNtId(Integer ntId) {
        this.ntId = ntId;
    }

    public String getNodeSn() {
        return nodeSn;
    }

    public void setNodeSn(String nodeSn) {
        this.nodeSn = nodeSn == null ? null : nodeSn.trim();
    }

    public String getSceneSn() {
        return sceneSn;
    }

    public void setSceneSn(String sceneSn) {
        this.sceneSn = sceneSn == null ? null : sceneSn.trim();
    }
}