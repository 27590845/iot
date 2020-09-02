package com.xidian.iot.database.pojo;

/**
 * 节点触发器条件，一个节点可配置多个条件，一个条件对应一个触发器；一次“添加关联”操作可添加多个条件以及生成一个触发器(这些条件可能对应到不同节点，甚至不同场景下的不同节点)
 *
 * @author mrl
 * @date   2020/09/01
 */
public class NodeCond {
    /**
     * 节点触发器触发条件ID
     */
    private Integer ncId;

    /**
     * 节点属性ID，一个条件对应一个节点属性，一个节点属性有多个条件，条件的作用(判断)对象是节点属性
     */
    private Integer naId;

    /**
     * 场景ID
     */
    private Integer sceneId;

    /**
     * 节点ID
     */
    private Integer nodeId;

    /**
     * 节点触发器ID，一个条件对应一个节点触发器，一个节点触发器有多个条件
     */
    private Integer ntId;

    /**
     * 操作符1>2>=3<4<=5==6新值7冻结8复活
     */
    private Byte ncOp;

    /**
     * 条件满足的数值
     */
    private String ncVal;

    /**
     * 节点SN
     */
    private String nodeSn;

    /**
     * 场景SN
     */
    private String sceneSn;

    /**
     * 满足条件几次才算条件匹配成功
     */
    private Integer ncFitTime;

    public Integer getNcId() {
        return ncId;
    }

    public void setNcId(Integer ncId) {
        this.ncId = ncId;
    }

    public Integer getNaId() {
        return naId;
    }

    public void setNaId(Integer naId) {
        this.naId = naId;
    }

    public Integer getSceneId() {
        return sceneId;
    }

    public void setSceneId(Integer sceneId) {
        this.sceneId = sceneId;
    }

    public Integer getNodeId() {
        return nodeId;
    }

    public void setNodeId(Integer nodeId) {
        this.nodeId = nodeId;
    }

    public Integer getNtId() {
        return ntId;
    }

    public void setNtId(Integer ntId) {
        this.ntId = ntId;
    }

    public Byte getNcOp() {
        return ncOp;
    }

    public void setNcOp(Byte ncOp) {
        this.ncOp = ncOp;
    }

    public String getNcVal() {
        return ncVal;
    }

    public void setNcVal(String ncVal) {
        this.ncVal = ncVal == null ? null : ncVal.trim();
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

    public Integer getNcFitTime() {
        return ncFitTime;
    }

    public void setNcFitTime(Integer ncFitTime) {
        this.ncFitTime = ncFitTime;
    }
}