package com.xidian.iot.datacenter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author mrl
 * @Title: MongoService
 * @Package: com.xidian.iot.datacenter.service
 * @Description: mongo service
 * @date 2020/9/1 2:47 下午
 */
@Service
public class MongoService {

    @Resource
    private MongoTemplate mongoTemplate;
}
