package com.xidian.iot.databiz.constants;

/**
 * 使用场景枚举类，从配置文件动态加载
 * @author: Hansey
 * @date: 2020-09-09 22:41
 */
public enum UsageEnum {
    ;
    String usageCode;
    String usageType;

    UsageEnum(String usageCode, String usageType) {
        this.usageCode = usageCode;
        this.usageType = usageType;
    }

    public String getUsageCode() {
        return usageCode;
    }

    public String getUsageType() {
        return usageType;
    }

    @Override
    public String toString() {
        return "UsageEnum{" +
                "usageCode=" + usageCode +
                ", usageType='" + usageType + '\'' +
                "} " + super.toString();
    }
}
