package com.xidian.iot.common.mq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.jms.*;

/**
 * @author mrl
 * @Title: ActivemqClient
 * @Package com.xidian.iot.common.mq
 * @Description: activemq client
 * @date 2020/9/1 10:53 下午
 */
public class ActivemqClient {

    private static final Logger logger = LoggerFactory.getLogger(ActivemqClient.class);

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

    public JmsTemplate getJmsTemplate() {
        return jmsTemplate;
    }

    public void setJmsTemplate(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }


}
