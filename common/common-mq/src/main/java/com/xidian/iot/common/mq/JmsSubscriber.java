package com.xidian.iot.common.mq;

import org.apache.activemq.command.ActiveMQTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.jms.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;

/**
 * @author mrl
 * @Title: JmsSubscriber
 * @Package com.xidian.iot.common.mq
 * @Description: 动态订阅
 * @date 2020/9/1 11:06 下午
 */
public class JmsSubscriber {

    private static final Logger log = LoggerFactory.getLogger(JmsSubscriber.class);

    /**
     * 该map用来保存订阅者及其对应的 activemq connection
     */
    private static final ConcurrentHashMap<String, Connection> connections = new ConcurrentHashMap<>();

    private JmsTemplate jmsTemplate;

    public JmsTemplate getJmsTemplate() {
        return jmsTemplate;
    }

    public void setJmsTemplate(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }
    public static Connection getConnection(String clientId) {
        return connections.get(clientId);
    }

    /**
     * 动态生成一个消费者
     * @param topic
     * @param listener
     */
    public String subscribe(String topic, MessageListener listener) {
        MessageConsumer consumer;
        Connection connection;
        Session session;
        try {
            connection = jmsTemplate.getConnectionFactory().createConnection();
            connection.start();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            log.info("订阅的topic：  " + topic);
            consumer = session.createConsumer(new ActiveMQTopic(topic));
            consumer.setMessageListener(listener);
            connections.put(connection.getClientID(), connection);
            return connection.getClientID();
        } catch (JMSException e) {
            e.printStackTrace();
        }
        return null;
    }
}
