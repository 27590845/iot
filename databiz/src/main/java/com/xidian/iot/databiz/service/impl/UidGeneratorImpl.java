package com.xidian.iot.databiz.service.impl;

import com.xidian.iot.databiz.service.UidGenerator;
import org.springframework.stereotype.Service;

/**
 * d
 *
 * @author: Hansey
 * @date: 2020-09-14 16:44
 */
@Service
public class UidGeneratorImpl implements UidGenerator {


    @Override
    public long getUID() {
        return 0;
    }

    @Override
    public boolean parseUID(long uid) {
        return false;
    }
}
