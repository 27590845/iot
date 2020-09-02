package com.xidian.iot.common.mq;

import org.apache.activemq.command.ActiveMQTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import javax.jms.*;
import java.io.Serializable;
import java.util.HashMap;

/**
 * @author mrl
 * @Title: JmsSender
 * @Package com.xidian.iot.common.mq
 * @Description: 发送各种格式的消息
 * @date 2020/9/1 10:55 下午
 */
public class JmsSender {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private JmsTemplate jmsTemplate;
    /*
     * 消息体是一个对象，把HashMap转化为消息体
     */
    public void sendMessage(final HashMap<String, Object> map, String topicName)
            throws JMSException {
        Topic topic = null;
        topic=new ActiveMQTopic(topicName);
        jmsTemplate.send(topic, new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                ObjectMessage om = session.createObjectMessage(map);
                return om;
            }
        });
        logger.info("成功发送了一条JMS消息--JmsSender  topic:"+topicName);
    }
    /*
     * 消息体是一个对象，把可序列化对象转化为消息体
     */
    public void sendSeriObj(final Serializable serializedObj, String topicName)
            throws JMSException {
        Topic topic = null;
        topic=new ActiveMQTopic(topicName);
        jmsTemplate.send(topic, new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                ObjectMessage om = session.createObjectMessage(serializedObj);
                return om;
            }
        });
        logger.info("成功发送了一条JMS消息--JmsSender  topic:"+topicName);

    }
    /**
     * 发送activemq byte message
     * @param jsonStr
     * @param topicName
     * @throws JMSException
     */
    public void sendSeriObjByte(final String jsonStr, String topicName)
            throws JMSException {
        Topic topic = null;
        topic=new ActiveMQTopic(topicName);
        jmsTemplate.send(topic, new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                BytesMessage bm = session.createBytesMessage();
                try {
                    bm.writeBytes(jsonStr.getBytes());
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                return bm;
            }
        });
        logger.info("成功发送了一条JMS消息--JmsSender  topic:"+topicName);

    }

    /**
     * 邬俊杰 4.19 添加
     * 发送一条消息到指定的队列（目标）
     * @param topicName 队列名称
     * @param message 消息内容
     */
    public void send(String topicName,final String message){
        Topic topic = null;
        topic=new ActiveMQTopic(topicName);
        jmsTemplate.send(topic, new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(message);
            }
        });
        logger.info("成功发送了一条JMS消息--JmsSender  topic:"+topicName);
    }

    public JmsTemplate getJmsTemplate() {
        return jmsTemplate;
    }

    public void setJmsTemplate(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }
}
