package com.xidian.iot.common.mq.activemq;

import com.xidian.iot.common.mq.MqMessageListener;
import com.xidian.iot.common.mq.MqSubscriber;
import com.xidian.iot.common.mq.activemq.parser.JmsMessageParser;
import lombok.Setter;
import org.apache.activemq.command.ActiveMQTopic;
import org.apache.commons.lang3.StringUtils;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.*;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author mrl
 * @Title: ActivemqSubscriber
 * @Package com.xidian.iot.common.mq.activemq
 * @Description: 动态订阅
 * @date 2020/9/1 11:06 下午
 */
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

    /**
     *activemq一次只订阅一个主题，有多个主题时取第一个
     */
    public String subscribe(final MqMessageListener listener, final String ... topics) throws JMSException {
        if(topics == null && topics.length<=0) return null;
        MessageConsumer consumer;
        Connection connection;
        Session session;
        connection = jmsTemplate.getConnectionFactory().createConnection();
        connection.start();
        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        consumer = session.createConsumer(new ActiveMQTopic(topics[0]));
        consumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                Map<Integer, Object> res = JmsMessageParser.parse(message);
                if(res==null || res.size()<=0) return;
                listener.onMessage(res.get(JmsMessageParser.MSG_TOPIC), res.get(JmsMessageParser.MSG_CONTENT));
            }
        });
        //将connection的clientId作为consumerId
        String consumerId = connection.getClientID();
        connections.put(consumerId, connection);
        return consumerId;
    }

    public boolean unSubscribe(String consumerId) throws JMSException {
        if(StringUtils.isNotEmpty(consumerId) && connections.contains(consumerId)){
            Connection connection = connections.get(consumerId);
            if(connection!=null){
                connection.close();
                connections.remove(consumerId);
                return true;
            }
        }
        return false;
    }
}
