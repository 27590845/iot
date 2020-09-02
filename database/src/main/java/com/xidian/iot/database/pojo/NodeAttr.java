package com.xidian.iot.database.pojo;

/**
 * 节点属性表，一个节点下有多个属性。逻辑上可看作数据单元里的一个字段；物理上可看作底层感知设备，比如传感器
 *
 * @author mrl
 * @date   2020/09/01
 */
public class NodeAttr {
    /**
     * 节点属性ID
     */
    private Integer naId;

    /**
     * 节点ID，一个节点属性对应一个节点，一个节点有多个节点属性
     */
    private Integer nodeId;

    /**
     * 属性标识
     */
    private String naKey;

    /**
     * 属性名称
     */
    private String naName;

    /**
     * 属性单位
     */
    private String naUnit;

    /**
     * 属性符号
     */
    private String naSym;

    public Integer getNaId() {
        return naId;
    }

    public void setNaId(Integer naId) {
        this.naId = naId;
    }

    public Integer getNodeId() {
        return nodeId;
    }

    public void setNodeId(Integer nodeId) {
        this.nodeId = nodeId;
    }

    public String getNaKey() {
        return naKey;
    }

    public void setNaKey(String naKey) {
        this.naKey = naKey == null ? null : naKey.trim();
    }

    public String getNaName() {
        return naName;
    }

    public void setNaName(String naName) {
        this.naName = naName == null ? null : naName.trim();
    }

    public String getNaUnit() {
        return naUnit;
    }

    public void setNaUnit(String naUnit) {
        this.naUnit = naUnit == null ? null : naUnit.trim();
    }

    public String getNaSym() {
        return naSym;
    }

    public void setNaSym(String naSym) {
        this.naSym = naSym == null ? null : naSym.trim();
    }
}