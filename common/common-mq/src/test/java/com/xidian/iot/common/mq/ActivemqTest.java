package com.xidian.iot.common.mq;

import static org.junit.Assert.assertTrue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.xidian.iot.common.mq.activemq.ActivemqSender;
import com.xidian.iot.common.mq.activemq.ActivemqSubscriber;
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
import javax.jms.*;
import java.io.Serializable;
import java.util.concurrent.CountDownLatch;

/**
 * Unit test for simple App.
 */
@RunWith(SpringJUnit4ClassRunner.class)
//@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:spring/application-activemq-def.xml"})
@Slf4j
public class ActivemqTest
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    @Resource
    MqSender mqSender;

    final static String topic = "hello_topic";

    @Test
    public void send1() throws JsonProcessingException {
        mqSender.send(topic, "hello word");
    }

    @Test
    public void send2() throws JsonProcessingException {
        mqSender.sendSeriObjByte(topic, JsonUtil.toJson(new User("hansey", "niupi", 9999)));
    }

    @Test
    public void send3() throws JsonProcessingException {
        mqSender.sendSeriObj(topic, new User("hansey", "niupi", 9999));
    }

    @Resource
    ActivemqSubscriber mqSubscriber;

    @Test
    public void subscribe() throws JMSException, InterruptedException {
        //消费1000个消息后关闭
        final CountDownLatch latch = new CountDownLatch(1000);
        String clientId = mqSubscriber.subscribe(new MqMessageListener() {
            @Override
            public void onMessage(Object topicName, Object message) {
                System.out.printf("topicName = %s, message = %s\n", topicName, message);
                latch.countDown();
            }
        }, topic);
        latch.await();
        log.debug("####close connection###");
        if(clientId!=null){
            Connection connection = ActivemqSubscriber.getConnection(clientId);
            if(connection!=null){
                connection.close();
            }
        }
    }

    @Data
    class User implements Serializable {
        String name;
        String gender;
        Integer age;
        public User(){}
        public User(String name, String gender, Integer age){
            this.name = name;
            this.age = age;
            this.gender = gender;
        }
    }

}
