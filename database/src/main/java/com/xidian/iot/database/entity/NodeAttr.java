package com.xidian.iot.database.entity;

/**
 * 节点属性表，一个节点下有多个属性。逻辑上可看作数据单元里的一个字段；物理上可看作底层感知设备，比如传感器
 *
 * @author xidianiot
 * @date   2021/01/21
 */
public class NodeAttr {
    /**
     * 节点属性ID
     */
    private Long naId;

    /**
     * 节点ID，一个节点属性对应一个节点，一个节点有多个节点属性
     */
    private Long nodeId;

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

    /**
     * 属性值格式化公式（选填），R表示量程，X表示原始数值
     */
    private String naForm;

    /**
     * 属性映射的传感器偏移地址（选填）
     */
    private String naMap;

    /**
     * 场景SN
     */
    private String sceneSn;

    /**
     * 节点SN
     */
    private String nodeSn;

    public Long getNaId() {
        return naId;
    }

    public void setNaId(Long naId) {
        this.naId = naId;
    }

    public Long getNodeId() {
        return nodeId;
    }

    public void setNodeId(Long nodeId) {
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

    public String getNaForm() {
        return naForm;
    }

    public void setNaForm(String naForm) {
        this.naForm = naForm == null ? null : naForm.trim();
    }

    public String getNaMap() {
        return naMap;
    }

    public void setNaMap(String naMap) {
        this.naMap = naMap == null ? null : naMap.trim();
    }

    public String getSceneSn() {
        return sceneSn;
    }

    public void setSceneSn(String sceneSn) {
        this.sceneSn = sceneSn == null ? null : sceneSn.trim();
    }

    public String getNodeSn() {
        return nodeSn;
    }

    public void setNodeSn(String nodeSn) {
        this.nodeSn = nodeSn == null ? null : nodeSn.trim();
    }
}