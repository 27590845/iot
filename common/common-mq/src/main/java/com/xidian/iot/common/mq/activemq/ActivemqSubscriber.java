package com.xidian.iot.common.mq.activemq;

import com.xidian.iot.common.mq.MqSubscriber;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.command.ActiveMQTopic;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author mrl
 * @Title: ActivemqSubscriber
 * @Package com.xidian.iot.common.mq.activemq
 * @Description: 动态订阅
 * @date 2020/9/1 11:06 下午
 */
@Slf4j
public class ActivemqSubscriber implements MqSubscriber {

    /**
     * 该map用来保存订阅者及其对应的 activemq connection
     */
    private static final ConcurrentHashMap<String, Connection> connections = new ConcurrentHashMap<>();

    @Setter
    private JmsTemplate jmsTemplate;

    public static Connection getConnection(String clientId) {
        return connections.get(clientId);
    }

    public String subscribe(String topic, MessageListener listener) throws JMSException {
        MessageConsumer consumer;
        Connection connection;
        Session session;
        connection = jmsTemplate.getConnectionFactory().createConnection();
        connection.start();
        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        log.info("订阅的topic：  " + topic);
        consumer = session.createConsumer(new ActiveMQTopic(topic));
        consumer.setMessageListener(listener);
        connections.put(connection.getClientID(), connection);
        return connection.getClientID();
    }

    public boolean unSubscribe(String clientId) throws JMSException {
        if(StringUtils.isNotEmpty(clientId) && connections.contains(clientId)){
            Connection connection = connections.get(clientId);
            if(connection!=null){
                connection.close();
                connections.remove(clientId);
                return true;
            }
        }
        return false;
    }
}
