package com.xidian.iot.common.util.uid;

/**
 * @author mrl
 * @Title: UidPrefixFactory
 * @Package
 * @Description: 用于从master获取uid前缀
 * @date 2020/9/14 4:07 下午
 */
public interface UidPrefixFactory {

    /**
     * 获取前缀
     * @return
     */
    long getPrefix();
}
