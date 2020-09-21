package com.xidian.iot.database.entity;

import java.util.Date;

/**
 * 节点触发器，一个节点可配置多个触发器，一个触发器也可对应多个节点，甚至不同场景下的节点；一个触发器下的全部条件都满足时才会触发
 *
 * @author mrl
 * @date   2020/09/20
 */
public class NodeTrig {
    /**
     * 节点触发器ID
     */
    private Long ntId;

    /**
     * 节点触发器名称
     */
    private String ntName;

    /**
     * 节点触发器描述
     */
    private String ntDesc;

    /**
     * 间隔时间，单位为秒
     */
    private Integer ntInvl;

    /**
     * 是否重复执行 0重复 1不重复
     */
    private Byte ntRept;

    /**
     * 是否执行 0执行 1不执行
     */
    private Byte ntExec;

    /**
     * 失效时间
     */
    private Date ntExpr;

    public Long getNtId() {
        return ntId;
    }

    public void setNtId(Long ntId) {
        this.ntId = ntId;
    }

    public String getNtName() {
        return ntName;
    }

    public void setNtName(String ntName) {
        this.ntName = ntName == null ? null : ntName.trim();
    }

    public String getNtDesc() {
        return ntDesc;
    }

    public void setNtDesc(String ntDesc) {
        this.ntDesc = ntDesc == null ? null : ntDesc.trim();
    }

    public Integer getNtInvl() {
        return ntInvl;
    }

    public void setNtInvl(Integer ntInvl) {
        this.ntInvl = ntInvl;
    }

    public Byte getNtRept() {
        return ntRept;
    }

    public void setNtRept(Byte ntRept) {
        this.ntRept = ntRept;
    }

    public Byte getNtExec() {
        return ntExec;
    }

    public void setNtExec(Byte ntExec) {
        this.ntExec = ntExec;
    }

    public Date getNtExpr() {
        return ntExpr;
    }

    public void setNtExpr(Date ntExpr) {
        this.ntExpr = ntExpr;
    }
}