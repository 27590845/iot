package com.xidian.iot.common.mq;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.xidian.iot.common.util.JsonUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.concurrent.CountDownLatch;

/**
 * @author mrl
 * @Title: KafkaTest
 * @Package
 * @Description:
 * @date 2020/9/8 6:24 下午
 */
@RunWith(SpringJUnit4ClassRunner.class)
//@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:spring/application-kafka-producer-def.xml"
        ,"classpath:spring/application-kafka-consumer-def.xml"})
@Slf4j
public class KafkaTest {

    static final Logger logger = LoggerFactory.getLogger(ActivemqTest.class);

    @Resource
    MqSender mqSender;

    @Resource
    MqSubscriber mqSubscriber;

    final static String topic = "hello_topic";

    @Test
    public void send1() throws JsonProcessingException, InterruptedException {
        for(int i=0; i< 1000; i++){
            mqSender.send(topic, "👋"+System.nanoTime());
            Thread.sleep(1000);
        }
    }

    @Test
    public void send2() throws Exception {
        mqSender.sendSeriObjByte(topic, JsonUtil.toJson(new User("hansey", "niupi", 9999)));
    }

    @Test
    public void send3() throws JsonProcessingException {
        mqSender.sendSeriObj(topic, new User("hansey", "niupi", 9999));
    }

    @Test
    public void subscribe1() throws Exception {
        final CountDownLatch latch = new CountDownLatch(1000);
        String consumerId = mqSubscriber.subscribe(new MqMessageListener() {
            @Override
            public void onMessage(Object topicName, Object message) {
                System.out.printf("topicName = %s, message = %s\n", topicName, message);
                latch.countDown();
            }
        }, topic);
        latch.await();
    }

    @Data
    class User implements Serializable {
        String name;
        String gender;
        Integer age;
        public User(String name, String gender, Integer age){
            this.name = name;
            this.age = age;
            this.gender = gender;
        }
    }
}
