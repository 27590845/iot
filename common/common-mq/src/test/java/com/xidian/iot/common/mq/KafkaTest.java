package com.xidian.iot.common.mq;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.xidian.iot.common.mq.activemq.ActivemqSender;
import com.xidian.iot.common.mq.kafka.KafkaSender;
import com.xidian.iot.common.util.JsonUtil;
import lombok.Data;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import javax.jms.JMSException;
import java.io.Serializable;
import java.util.Date;

/**
 * @author mrl
 * @Title: KafkaTest
 * @Package
 * @Description:
 * @date 2020/9/8 6:24 下午
 */
@RunWith(SpringJUnit4ClassRunner.class)
//@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:spring/application-kafka-producer-def.xml"})
public class KafkaTest {

    static final Logger logger = LoggerFactory.getLogger(ActivemqTest.class);

    @Resource
    MqSender mqSender;

    @Test
    public void send1() throws JsonProcessingException {
        mqSender.send("hello_topic", "不烦");
    }

    @Test
    public void send2() throws Exception {
        mqSender.sendSeriObjByte("hello_topic", JsonUtil.toJson(new User("hansey", "niupi", 9999)));
    }

    @Test
    public void send3() throws JsonProcessingException {
        mqSender.sendSeriObj("hello_topic", new User("hansey", "niupi", 9999));
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
