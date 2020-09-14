package com.xidian.iot.databiz.service;

/**
 * id生成工具
 * @author: Hansey
 * @date: 2020-09-14 16:41
 */
public interface UidGenerator {

    long getUID();

    boolean parseUID(long uid);
}
