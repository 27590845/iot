package com.xidian.iot.common.util.uid;

import lombok.Setter;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @author mrl
 * @Title: UidPrefixFactoryImpl
 * @Package
 * @Description:
 * @date 2020/9/14 3:51 下午
 */
public class UidPrefixFactoryImpl implements UidPrefixFactory {

    private volatile AtomicLong start = new AtomicLong(System.currentTimeMillis()>>16);//大概除以了一分钟的时间

    //uid前缀资源定位符
    @Setter
    private String prefixSourceUri;

    @Override
    public long getPrefix(){
        //这里应该根据prefixSource获取
        return start.addAndGet(1);
    }
}