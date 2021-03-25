package com.xidian.iot.datacenter.system;

import com.xidian.iot.common.cache.RedisUtil;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class SystemParamShared implements InitializingBean {

    @Resource
    private RedisUtil redisUtil;

    public static final String TIME_STAMP_DIV = "timeStampDiv";
    public static final String TRIGGER_ENABLE = "triggerEnable";
    public static final String REPORT_ENABLE = "reportEnable";
    public static final String INFLUX_ENABLE = "influxEnable";

    private SystemParamShared() {
    }

    public int getTimeStampDiv() {
        return (int) redisUtil.get(TIME_STAMP_DIV);
    }

    public void setTimeStampDiv(int timeStampDiv) {
        if(timeStampDiv<=0) return;
        redisUtil.set(TIME_STAMP_DIV, timeStampDiv);
        System.out.printf("set success : %d \n", timeStampDiv);
    }

    public boolean isTriggerEnable() {
        return (boolean) redisUtil.get(TRIGGER_ENABLE);
    }

    public void setTriggerEnable(boolean triggerEnable) {
        redisUtil.set(TRIGGER_ENABLE, triggerEnable);
    }

    public boolean isReportEnable() {
        return (boolean) redisUtil.get(REPORT_ENABLE);
    }

    public void setReportEnable(boolean reportEnable) {
        redisUtil.set(REPORT_ENABLE, reportEnable);
    }

    public boolean isInfluxEnable() {
        return (boolean) redisUtil.get(INFLUX_ENABLE);
    }

    public void setInfluxEnable(boolean influxEnable) {
        redisUtil.set(INFLUX_ENABLE, influxEnable);
    }

    public String getDesc() {
        return "{\n" +
                "   SystemParam:{\n" +
                "       timeStampDiv:"+ redisUtil.get(TIME_STAMP_DIV) +",\n" +
                "       triggerEnable:"+ redisUtil.get(TRIGGER_ENABLE) +",\n" +
                "       reportEnable:"+ redisUtil.get(REPORT_ENABLE) +"\n" +
                "       influxEnable:"+ redisUtil.get(INFLUX_ENABLE) +"\n"+
                "   }\n" +
                "}";
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        redisUtil.set(TIME_STAMP_DIV, 1);
        redisUtil.set(TRIGGER_ENABLE, false);
        redisUtil.set(REPORT_ENABLE, false);
        redisUtil.set(INFLUX_ENABLE, false);
    }
}