package com.xidian.iot.databiz.service.impl;

import com.xidian.iot.common.uid.exception.UidGenerateException;
import com.xidian.iot.common.util.uid.UidGen;
import com.xidian.iot.databiz.service.UidGenerator;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author mrl
 * @Title: UidGen
 * @Package
 * @Description: 另一种Uid生成方式，测试时用较方便
 * @date 2020/9/17 9:18 上午
 */
@Service
public class SimpleUidGeneratorImpl implements UidGenerator {

    @Resource
    private UidGen uidGen;

    @Override
    public long getUID() throws UidGenerateException {
        try {
            return uidGen.getUID(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public String parseUID(long uid) {
        return null;
    }
}
