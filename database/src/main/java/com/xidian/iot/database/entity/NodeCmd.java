package com.xidian.iot.database.entity;

/**
 * 节点命令，节点命令的接收者是通信终端，一般通信终端会通过寻址机制将收到的命令作用于某个数据采集设备，命令可分为“收集数据“”调整参数”两类
 *
 * @author mrl
 * @date   2020/09/20
 */
public class NodeCmd {
    /**
     * 节点命令ID
     */
    private Long ncId;

    /**
     * 节点ID，一个节点命令对应一个节点，一个节点有多个命令
     */
    private Long nodeId;

    /**
     * 命令组ID，一个节点命令对应一个命令组，一个命令组有多个节点命令，用于对命令分类，比如“开关”“转动”“频率”等类
     */
    private Long ncgId;

    /**
     * 命令内容
     */
    private String ncContent;

    /**
     * 命令中文标题
     */
    private String ncName;

    public Long getNcId() {
        return ncId;
    }

    public void setNcId(Long ncId) {
        this.ncId = ncId;
    }

    public Long getNodeId() {
        return nodeId;
    }

    public void setNodeId(Long nodeId) {
        this.nodeId = nodeId;
    }

    public Long getNcgId() {
        return ncgId;
    }

    public void setNcgId(Long ncgId) {
        this.ncgId = ncgId;
    }

    public String getNcContent() {
        return ncContent;
    }

    public void setNcContent(String ncContent) {
        this.ncContent = ncContent == null ? null : ncContent.trim();
    }

    public String getNcName() {
        return ncName;
    }

    public void setNcName(String ncName) {
        this.ncName = ncName == null ? null : ncName.trim();
    }
}