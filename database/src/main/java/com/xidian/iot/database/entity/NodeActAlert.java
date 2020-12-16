package com.xidian.iot.database.entity;

/**
 * 一旦一个节点触发器被触发，就会有一个报警信息被发出
 *
 * @author mrl
 * @date   2020/12/09
 */
public class NodeActAlert {
    /**
     * 触发报警信息ID
     */
    private Long naaId;

    /**
     * 节点触发器消息ID
     */
    private Long ntId;

    /**
     * 警报类型 1短信 2email 
     */
    private Byte naaType;

    /**
     * 发警报对象的数值手机号、邮箱
     */
    private String naaVal;

    /**
     * 发送内容
     */
    private String naaContent;

    public Long getNaaId() {
        return naaId;
    }

    public void setNaaId(Long naaId) {
        this.naaId = naaId;
    }

    public Long getNtId() {
        return ntId;
    }

    public void setNtId(Long ntId) {
        this.ntId = ntId;
    }

    public Byte getNaaType() {
        return naaType;
    }

    public void setNaaType(Byte naaType) {
        this.naaType = naaType;
    }

    public String getNaaVal() {
        return naaVal;
    }

    public void setNaaVal(String naaVal) {
        this.naaVal = naaVal == null ? null : naaVal.trim();
    }

    public String getNaaContent() {
        return naaContent;
    }

    public void setNaaContent(String naaContent) {
        this.naaContent = naaContent == null ? null : naaContent.trim();
    }
}