package com.xidian.iot.common.mq;

import static org.junit.Assert.assertTrue;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.xidian.iot.common.mq.activemq.ActivemqSubscriber;
import com.xidian.iot.common.util.JsonUtil;
import com.xidian.iot.common.util.RandomUtil;
import com.xidian.iot.common.util.TimeUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import javax.jms.*;
import java.io.Serializable;
import java.util.Date;
import java.util.concurrent.CountDownLatch;

/**
 * Unit test for simple App.
 */
@RunWith(SpringJUnit4ClassRunner.class)
//@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:spring/application-activemq-def.xml"})
@Slf4j
public class ActivemqTest {

    static {
        System.setProperty("spring.profiles.active", "development");
    }

    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() {
        assertTrue(true);
    }

    @Resource
    MqSender mqSender;

    final static String topic = "hello_topic";

    final static String sceneSn = "186610102211000001";
    final static String nodeSn = "000001";
    final static String topicIot = "node.updata."+sceneSn;

    @Test
    public void appTest() throws JsonProcessingException, InterruptedException {
//        String msg = "{\"datastreams\":[{\"TVOC\":69,\"hum\":54.0,\"at\":1597737850021,\"pm2p5\":33,\"co2\":509,\"pm10\":59,\"sn\":\""+nodeSn+"\",\"ch20\":19,\"tem\":32.0}]}";
        for(int i=0; i< 1000; i++){
            String msg = "{\"datastreams\":[{"
                    + "\"tem1\":"+ RandomUtil.nextInt(10, 19)
                    +",\"tem2\":"+RandomUtil.nextInt(20, 29)
                    +",\"tem3\":"+RandomUtil.nextInt(30, 39)
                    +",\"tem4\":"+ RandomUtil.nextInt(40, 49)
                    +",\"hum\":"+ RandomUtil.nextInt(0, 9)
                    +",\"at\":"+ TimeUtil.getTimeStamp(null)
                    +",\"sn\":\""+nodeSn+"\"}]}";
            mqSender.sendQueue(topicIot, msg);
            Thread.sleep(1000);
        }
    }

    @Test
    public void send2() throws JsonProcessingException, InterruptedException {
        JSONObject alert = new JSONObject();
        alert.put("ntId", 21658431299607040l);
        alert.put("time", new Date());
        mqSender.sendQueue("Alert", alert.toString());
    }

//    @Test
//    public void send2() throws JsonProcessingException {
//        mqSender.sendSeriObjByte(topic, JsonUtil.toJson(new User("hansey", "niupi", 9999)));
//    }

//    @Test
//    public void send3() throws JsonProcessingException {
//        mqSender.sendSeriObj(topic, new User("hansey", "niupi", 9999));
//    }

    @Resource
    ActivemqSubscriber mqSubscriber;

    @Test
    public void subscribe() throws JMSException, InterruptedException {
        //??????1000??????????????????
        final CountDownLatch latch = new CountDownLatch(1000);
        String clientId1 = mqSubscriber.subscribeQueue(new MqMessageListener() {
            @Override
            public void onMessage(Object topicName, Object message) {
                System.out.printf("client = %s, message = %s\n", 1, message);
                latch.countDown();
            }
        }, topic);
        String clientId2 = mqSubscriber.subscribeQueue(new MqMessageListener() {
            @Override
            public void onMessage(Object topicName, Object message) {
                System.out.printf("client = %s, message = %s\n", 2, message);
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

        public User() {
        }

        public User(String name, String gender, Integer age) {
            this.name = name;
            this.age = age;
            this.gender = gender;
        }
    }

}
