package com.xidian.iot.common.mq;

import static org.junit.Assert.assertTrue;

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
public class AppTest 
{
    static final Logger logger = LoggerFactory.getLogger(AppTest.class);

    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    @Resource
    JmsSender jmsSender;

    @Test
    public void send(){
        jmsSender.send("test_topic", "hello word");
    }

    @Resource
    JmsSubscriber jmsSubscriber;

    @Test
    public void subscribe() throws InterruptedException, JMSException {
        final CountDownLatch latch = new CountDownLatch(1);
        String clientId = jmsSubscriber.subscribe("test_topic", new MessageListener() {
            @Override
            public void onMessage(Message message) {
                System.out.println("test_topic-----"+message.toString());
                latch.countDown();
            }
        });
        latch.await();
        logger.info("####close connection###");
        if(clientId!=null){
            Connection connection = JmsSubscriber.getConnection(clientId);
            if(connection!=null){
                connection.close();
            }
        }
    }
}
