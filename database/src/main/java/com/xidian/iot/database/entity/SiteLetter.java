package com.xidian.iot.database.entity;

import java.util.Date;

/**
 * 
 *
 * @author mrl
 * @date   2020/09/20
 */
public class SiteLetter {
    /**
     * 
     */
    private Long slId;

    /**
     * 
     */
    private Short isRead;

    /**
     * 
     */
    private Date createTime;

    /**
     * 
     */
    private String currentValue;

    public Long getSlId() {
        return slId;
    }

    public void setSlId(Long slId) {
        this.slId = slId;
    }

    public Short getIsRead() {
        return isRead;
    }

    public void setIsRead(Short isRead) {
        this.isRead = isRead;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCurrentValue() {
        return currentValue;
    }

    public void setCurrentValue(String currentValue) {
        this.currentValue = currentValue == null ? null : currentValue.trim();
    }
}