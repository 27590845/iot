package com.xidian.iot.common.mq.activemq;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import javax.jms.*;

/**
 * @author mrl
 * @Title: ActivemqClient
 * @Package com.xidian.iot.common.mq.activemq
 * @Description: activemq client
 * @date 2020/9/1 10:53 下午
 */
public class ActivemqClient {

    @Setter
    private static JmsTemplate jmsTemplate;

    /**
     * 发送消息
     * @param topicName
     * @param jsonStr
     * @throws Exception
     */
    public static void sendMessage(String topicName , final String jsonStr) throws Exception {
        jmsTemplate.setDeliveryMode(DeliveryMode.PERSISTENT);
        jmsTemplate.setSessionAcknowledgeMode(Session.AUTO_ACKNOWLEDGE);
        jmsTemplate.send(topicName, new MessageCreator(){
            public Message createMessage(Session session) throws JMSException {
                ObjectMessage om = null;
                try {
                    om = session.createObjectMessage(jsonStr);
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                return om;
            }
        });
    }

}
