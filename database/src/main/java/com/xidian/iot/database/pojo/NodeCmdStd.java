package com.xidian.iot.database.pojo;

/**
 * 节点命令名称模版，node_cmd_std.ncs_name字段对应node_cmd.nc_name，供添加命令选择“控制名称”
 *
 * @author mrl
 * @date   2020/09/01
 */
public class NodeCmdStd {
    /**
     * 节点命令模版ID
     */
    private Integer ncsId;

    /**
     * 命令属性
     */
    private String ncsKey;

    /**
     * 命令属性名称
     */
    private String ncsName;

    public Integer getNcsId() {
        return ncsId;
    }

    public void setNcsId(Integer ncsId) {
        this.ncsId = ncsId;
    }

    public String getNcsKey() {
        return ncsKey;
    }

    public void setNcsKey(String ncsKey) {
        this.ncsKey = ncsKey == null ? null : ncsKey.trim();
    }

    public String getNcsName() {
        return ncsName;
    }

    public void setNcsName(String ncsName) {
        this.ncsName = ncsName == null ? null : ncsName.trim();
    }
}