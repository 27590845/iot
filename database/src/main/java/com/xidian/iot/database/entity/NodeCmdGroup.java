package com.xidian.iot.database.entity;

/**
 * 节点命令组，发不同类型的控制命令时，会对应不同的的处理方式，该表对控制命令进行了分类，比如“视频”“转动““频率“等不同类别的动作；一个命令对应到一个命令组，一个命令组可包含多条命令
 *
 * @author mrl
 * @date   2020/09/20
 */
public class NodeCmdGroup {
    /**
     * 节点命令组ID
     */
    private Long ncgId;

    /**
     * 节点ID，一个命令组对应一个节点，具体关系待商榷
     */
    private Long nodeId;

    /**
     * 节点命令组名称
     */
    private String ncgName;

    /**
     * 节点命令组key
     */
    private String ncgKey;

    public Long getNcgId() {
        return ncgId;
    }

    public void setNcgId(Long ncgId) {
        this.ncgId = ncgId;
    }

    public Long getNodeId() {
        return nodeId;
    }

    public void setNodeId(Long nodeId) {
        this.nodeId = nodeId;
    }

    public String getNcgName() {
        return ncgName;
    }

    public void setNcgName(String ncgName) {
        this.ncgName = ncgName == null ? null : ncgName.trim();
    }

    public String getNcgKey() {
        return ncgKey;
    }

    public void setNcgKey(String ncgKey) {
        this.ncgKey = ncgKey == null ? null : ncgKey.trim();
    }
}