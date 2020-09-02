package com.xidian.iot.database.pojo;

/**
 * 节点通信协议，一个节点可以对应一个数据上传协议，和一个命令下发协议
 *
 * @author mrl
 * @date   2020/09/01
 */
public class NodeCmdProt {
    /**
     * 节点命令协议ID
     */
    private Integer ncpId;

    /**
     * 节点ID，一个节点命令协议对应一个节点，一个节点一般有两个节点命令协议：数据上传协议、命令下发协议
     */
    private Integer nodeId;

    /**
     * 与此节点通信(虽然通信的基本单位是scene，但是scene会通过寻址机制定位到某一节点，所以下发的命令最终作用于节点，上传的数据也是以节点为数据单元；scene每次上传的数据的格式应该是：以数据单元为元素的数组，再附加一些scene、node本身的信息)采用的协议类型
     */
    private String ncpType;

    /**
     * 数据上传格式
     */
    private String ncpDown;

    /**
     * 命令下发格式
     */
    private String ncpUp;

    public Integer getNcpId() {
        return ncpId;
    }

    public void setNcpId(Integer ncpId) {
        this.ncpId = ncpId;
    }

    public Integer getNodeId() {
        return nodeId;
    }

    public void setNodeId(Integer nodeId) {
        this.nodeId = nodeId;
    }

    public String getNcpType() {
        return ncpType;
    }

    public void setNcpType(String ncpType) {
        this.ncpType = ncpType == null ? null : ncpType.trim();
    }

    public String getNcpDown() {
        return ncpDown;
    }

    public void setNcpDown(String ncpDown) {
        this.ncpDown = ncpDown == null ? null : ncpDown.trim();
    }

    public String getNcpUp() {
        return ncpUp;
    }

    public void setNcpUp(String ncpUp) {
        this.ncpUp = ncpUp == null ? null : ncpUp.trim();
    }
}