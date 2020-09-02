package com.xidian.iot.database.pojo;

/**
 * 节点命令组名称模版，node_cmd_group_std.ncgs_name对应node_cmd_group.ncg_name，供添加命令组选择“命令组名称“
 *
 * @author mrl
 * @date   2020/09/01
 */
public class NodeCmdGroupStd {
    /**
     * 命令组模版ID
     */
    private Integer ncgsId;

    /**
     * 命令组名称
     */
    private String ncgsName;

    /**
     * 命令组属性
     */
    private String ncgsKey;

    public Integer getNcgsId() {
        return ncgsId;
    }

    public void setNcgsId(Integer ncgsId) {
        this.ncgsId = ncgsId;
    }

    public String getNcgsName() {
        return ncgsName;
    }

    public void setNcgsName(String ncgsName) {
        this.ncgsName = ncgsName == null ? null : ncgsName.trim();
    }

    public String getNcgsKey() {
        return ncgsKey;
    }

    public void setNcgsKey(String ncgsKey) {
        this.ncgsKey = ncgsKey == null ? null : ncgsKey.trim();
    }
}