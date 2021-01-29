package com.xidian.iot.database.entity;

/**
 * 一旦一个节点触发器被触发，可能会执行多个命令
 *
 * @author xidianiot
 * @date   2021/01/21
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
}