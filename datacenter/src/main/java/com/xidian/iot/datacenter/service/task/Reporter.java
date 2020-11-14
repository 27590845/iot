package com.xidian.iot.datacenter.service.task;

import com.xidian.iot.common.mq.MqSender;
import com.xidian.iot.databiz.service.NodeCondService;
import com.xidian.iot.datacenter.system.SystemParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author mrl
 * @Title: Reporter
 * @Package
 * @Description: 定时做些什么事情
 * @date 2020/9/24 7:35 下午
 */
@Component
@Slf4j
public class Reporter {

    final static String sceneSn = "186610102211000001";
    final static String nodeSn = "000001";
    final static String topicIot = "node.updata."+sceneSn;
    String msg = "{\"datastreams\":[{\"tem1\":110,\"tem2\":44.0,\"at\":1600570048,\"sn\":\""+nodeSn+"\"}]}";

    @Resource
    private MqSender mqSender;
    @Resource
    private MongoTemplate mongoTemplate;
    @Resource
    private RedisTemplate redisTemplate;
    @Resource
    private NodeCondService nodeCondService;

    public void report(){
        if(!SystemParam.isReportEnable()) return;
        try{
            long time = new Date().getTime();
            log.info("======> 定时检测组件 <======");
            log.info("======> 检测mongodb <======");
            log.info("mongodb.iotdata.nodedata.count(): "+mongoTemplate.count(new Query(), "nodedata"));
            log.info("======> 检测redis <======");
            log.info("redis.keys: "+redisTemplate.keys("check.dataceter").toString());
            log.info("======> 检测mysql <======");
            log.info("NodeCondService.getNcIdsBySn: "+nodeCondService.getNcIdsBySn(sceneSn, nodeSn).toString());
            log.info("======> 检测消息队列 <======");
            mqSender.sendTopic("datacenter.check", "check the connection");
            log.info("======> 定时检测完成 <======");
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
}
