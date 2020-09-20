package com.xidian.iot.database.entity;

/**
 * 一旦一个节点触发器被触发，可能会执行多个命令
 *
 * @author mrl
 * @date   2020/09/20
 */
public class NodeActCmd {
    /**
     * 
     */
    private Long nacId;

    /**
     * 关联的命令，node_cmd.nc_id
     */
    private Long ncId;

    /**
     * 
     */
    private Long ntId;

    /**
     * 节点SN
     */
    private String nodeSn;

    /**
     * 场景SN
     */
    private String sceneSn;

    public Long getNacId() {
        return nacId;
    }

    public void setNacId(Long nacId) {
        this.nacId = nacId;
    }

    public Long getNcId() {
        return ncId;
    }

    public void setNcId(Long ncId) {
        this.ncId = ncId;
    }

    public Long getNtId() {
        return ntId;
    }

    public void setNtId(Long ntId) {
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