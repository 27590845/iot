package com.xidian.iot.common.util.uid;

/**
 * @author mrl
 * @Title: SimpleUidGenerator
 * @Package
 * @Description:
 * @date 2020/9/14 3:59 下午
 */
public interface SimpleUidGenerator {

    /**
     * 规定时间内未能获取到UId就主动放弃
     * @param waitMis 等待的时间
     * @return uid
     * @throws InterruptedException
     */
    long getUID(long waitMis) throws InterruptedException;


}
