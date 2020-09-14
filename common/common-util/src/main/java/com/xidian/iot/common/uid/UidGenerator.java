package com.xidian.iot.common.uid;

/**
 * @author mrl
 * @Title: UidGenerator
 * @Package
 * @Description:
 * @date 2020/9/14 3:59 下午
 */
public interface UidGenerator {

    /**
     * 在限定获取
     * @param waitMis
     * @return uid
     * @throws InterruptedException
     */
    long getUID(long waitMis) throws InterruptedException;
}
