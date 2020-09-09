package com.xidian.iot.common.mq;

import static org.junit.Assert.assertTrue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.xidian.iot.common.mq.activemq.ActivemqSender;
import com.xidian.iot.common.mq.activemq.ActivemqSubscriber;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import java.util.concurrent.CountDownLatch;

/**
 * Unit test for simple App.
 */
@RunWith(SpringJUnit4ClassRunner.class)
//@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:spring/application-activemq-def.xml"})
public class ActivemqTest
{
    static final Logger logger = LoggerFactory.getLogger(ActivemqTest.class);

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

    @Test
    public void send() throws JsonProcessingException {
        mqSender.send("test_topic", "hello word");
    }

    @Resource
    ActivemqSubscriber mqSubscriber;

    @Test
    public void subscribe() throws JMSException, InterruptedException {
        final CountDownLatch latch = new CountDownLatch(1);
        String clientId = mqSubscriber.subscribe("test_topic", new MessageListener() {
            @Override
            public void onMessage(Message message) {
                System.out.println("test_topic-----"+message.toString());
                latch.countDown();
            }
        });
        latch.await();
        logger.info("####close connection###");
        if(clientId!=null){
            Connection connection = ActivemqSubscriber.getConnection(clientId);
            if(connection!=null){
                connection.close();
            }
        }
    }
}
