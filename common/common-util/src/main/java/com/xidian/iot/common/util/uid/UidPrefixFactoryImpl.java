package com.xidian.iot.common.util.uid;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @author mrl
 * @Title: UidPrefixFactoryImpl
 * @Package
 * @Description:
 * @date 2020/9/14 3:51 下午
 */
public class UidPrefixFactoryImpl implements UidPrefixFactory {

    private final AtomicLong start = new AtomicLong(System.currentTimeMillis()>>16);//大概除以了一分钟的时间

    @Override
    public long getPrefix(){
        //这里应该根据prefixSource获取
        return start.addAndGet(1);
    }
}