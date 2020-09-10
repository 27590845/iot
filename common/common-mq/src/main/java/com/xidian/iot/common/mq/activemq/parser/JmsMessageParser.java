package com.xidian.iot.common.mq.activemq.parser;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.jms.*;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author mrl
 * @Title: JmsMessageParser
 * @Package
 * @Description: 解析Jms消息
 * @date 2020/9/10 1:10 下午
 */
@Slf4j
public class JmsMessageParser {

    public final static int MSG_TOPIC = 0;
    public final static int MSG_CONTENT = 1;

    public static Map<Integer, Object> parseJmsMessage(Message msg) {
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

    public static Map<Integer, Object> parse(Object data) {
        if(data!=null && data instanceof Message) {
            return parseJmsMessage((Message) data);
        }else {
            return null;
        }
    }
}
