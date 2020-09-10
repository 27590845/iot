package com.xidian.iot.databiz.constants;

/**
 * 通讯协议枚举类，从动态加载
 * @author: Hansey
 * @date: 2020-09-09 22:00
 */
public enum CommunicateEnum {
    ;
    String commCode;
    String commType;

    CommunicateEnum(String commCode, String commType) {
        this.commCode = commCode;
        this.commType = commType;
    }

    public String getCommCode() {
        return commCode;
    }

    public String getCommType() {
        return commType;
    }

    @Override
    public String toString() {
        return "CommunicateEnum{" +
                "commCode=" + commCode +
                ", commType='" + commType + '\'' +
                "} " + super.toString();
    }
}
