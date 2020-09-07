package com.xidian.iot.databiz.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

/**
 * @author mrl
 * @Title: MongoService
 * @Package com.xidian.iot.dataapi
 * @Description: mongo service
 * @date 2020/9/1 5:30 下午
 */
@Service
public class MongoService {

    @Autowired
    private MongoTemplate mongoTemplate;
}
