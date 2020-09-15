package com.xidian.iot.common.uid;

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

    private volatile AtomicLong start = new AtomicLong(System.currentTimeMillis()/1000);
    @Setter
    private String prefixSource;

    @Override
    public long getPrefix(){
        return start.addAndGet(1);
    }
}