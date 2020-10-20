package com.xidian.iot.common.mq.activemq;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.xidian.iot.common.mq.MqSender;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import javax.jms.*;
import java.io.Serializable;

/**
 * @author mrl
 * @Title: ActivemqSender
 * @Package com.xidian.iot.common.mq.activemq
 * @Description: 发送各种格式的消息
 * @date 2020/9/1 10:55 下午
 */
@Slf4j
public class ActivemqSender implements MqSender {

    @Setter
    private JmsTemplate jmsTemplate;

//    @Override
//    public void sendSeriObj(String topicName, final Serializable serializedObj) {
//        Topic topic = null;
//        topic=new ActiveMQTopic(topicName);
//        jmsTemplate.send(topicName, new MessageCreator() {
//            public Message createMessage(Session session) throws JMSException {
//                ObjectMessage om = session.createObjectMessage(serializedObj);
//                return om;
//            }
//        });
//        log.debug("成功发送了一条JMS消息--ActivemqSender  topic:"+topicName);
//    }

//    @Override
//    public void sendSeriObjByte(String topicName, final String jsonStr) {
//        Topic topic = null;
//        topic=new ActiveMQTopic(topicName);
//        jmsTemplate.send(topic, new MessageCreator() {
//            public Message createMessage(Session session) throws JMSException {
//                BytesMessage bm = session.createBytesMessage();
//                try {
//                    bm.writeBytes(jsonStr.getBytes());
//                } catch (Exception e) {
//                    // TODO Auto-generated catch block
//                    e.printStackTrace();
//                }
//
//                return bm;
//            }
//        });
//        log.debug("成功发送了一条JMS消息--ActivemqSender  topic:"+topicName);
//    }

    @Override
    public void sendTopic(String topicName, final String message){
        send(new ActiveMQTopic(topicName), message);
    }

    @Override
    public void sendQueue(String topicName, String message) {
        send(new ActiveMQQueue(topicName), message);
    }

    private void send(Destination destination, final String message){
        jmsTemplate.send(destination, new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(message);
            }
        });
    }

}
