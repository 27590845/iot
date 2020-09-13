package com.xidian.iot.datacenter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.xidian.iot.common.mq.MqSender;
import com.xidian.iot.database.entity.mongo.NodeData;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author mrl
 * @Title: AppTest
 * @Package
 * @Description:
 * @date 2020/9/11 10:26 下午
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/application-context.xml"})
public class AppTest {

    final static String topic = "hello_topic";

    @Resource
    MqSender mqSender;

    @Test
    public void appTest() throws JsonProcessingException, InterruptedException {
        String msg = "{\"datastreams\":[{\"TVOC\":69,\"hum\":54.0,\"at\":1597737850021,\"pm2p5\":33,\"co2\":509,\"pm10\":59,\"sn\":1,\"ch20\":19,\"tem\":32.0}]}";
        for(int i=0; i< 1000; i++){
            mqSender.send(topic, msg);
            Thread.sleep(1000);
        }
    }
}
