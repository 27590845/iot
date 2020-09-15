package com.xidian.iot.database.entity;

/**
 * 节点属性模版，记录了一类节点属性的元数据，比如记录了某节点属性的计数单位
 *
 * @author mrl
 * @date   2020/09/10
 */
public class NodeAttrStd {
    /**
     * 节点属性模版ID
     */
    private Long nasId;

    /**
     * 主属性的归一化表示
     */
    private String nasKey;

    /**
     * 对主属性的描述
     */
    private String nasDesc;

    /**
     * 主属性单位
     */
    private String nasUnit;

    /**
     * 主属性符号
     */
    private String nasSym;

    public Long getNasId() {
        return nasId;
    }

    public void setNasId(Long nasId) {
        this.nasId = nasId;
    }

    public String getNasKey() {
        return nasKey;
    }

    public void setNasKey(String nasKey) {
        this.nasKey = nasKey == null ? null : nasKey.trim();
    }

    public String getNasDesc() {
        return nasDesc;
    }

    public void setNasDesc(String nasDesc) {
        this.nasDesc = nasDesc == null ? null : nasDesc.trim();
    }

    public String getNasUnit() {
        return nasUnit;
    }

    public void setNasUnit(String nasUnit) {
        this.nasUnit = nasUnit == null ? null : nasUnit.trim();
    }

    public String getNasSym() {
        return nasSym;
    }

    public void setNasSym(String nasSym) {
        this.nasSym = nasSym == null ? null : nasSym.trim();
    }
}