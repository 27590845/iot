package com.xidian.iot.databiz.service;

import com.xidian.iot.exception.UidGenerateException;

/**
 * id生成工具
 * @author: Hansey
 * @date: 2020-09-14 16:41
 */
public interface UidGenerator {

    /**
     * Get a unique ID
     *
     * @return UID
     * @throws UidGenerateException
     */
    long getUID() throws UidGenerateException;

    /**
     * Parse the UID into elements which are used to generate the UID. <br>
     * Such as timestamp & workerId & sequence...
     *
     * @param uid
     * @return Parsed info
     */
    String parseUID(long uid);
}
