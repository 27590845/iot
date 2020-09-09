package com.xidian.iot.common.mq.activemq;

import com.xidian.iot.common.mq.MqMessageListener;
import com.xidian.iot.common.mq.MqSubscriber;
import com.xidian.iot.common.util.StringUtil;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.command.ActiveMQTopic;
import org.apache.commons.lang3.StringUtils;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.*;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
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

    public final static int MSG_TOPIC = 0;
    public final static int MSG_CONTENT = 1;

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
                Map<Integer, Object> res = parseJmsMessage(message);
                listener.onMessage(res.get(MSG_TOPIC), res.get(MSG_CONTENT));
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

    public Map<Integer, Object> parseJmsMessage(Message msg) {
        if(msg == null) return null;
        Object msgContent = null;
        String msgtopic = null;
        Map<Integer, Object> res = null;
        try {
            msgtopic = msg.getJMSDestination().toString();
            if (msg instanceof BytesMessage) {
                log.info("------Received BytesMessage------");
                BytesMessage message = (BytesMessage) msg;
                byte[] byteContent = new byte[1024];
                int length = -1;
                StringBuffer content = new StringBuffer();
                //如果消息体不为空
                while ((length = message.readBytes(byteContent)) != -1) {
                    content.append(new String(byteContent, 0, length));
                }
                // 消息体
                msgContent = content.toString();
            }else if(msg instanceof ObjectMessage){
                log.info("------Received ObjectMessage------");
                Serializable message = ((ObjectMessage)msg).getObject();
                msgContent = message.toString();
            }else {
                log.info("------Received TextMessage------");
                TextMessage message = (TextMessage) msg;
                msgContent = message.getText();
            }
        } catch (JMSException e) {
            e.printStackTrace();
        }
        if(StringUtils.isNotEmpty(msgtopic)){
            res = new HashMap<>();
            res.put(MSG_TOPIC, msgtopic);
            res.put(MSG_CONTENT, msgContent);
        }
        return res;
    }
}
