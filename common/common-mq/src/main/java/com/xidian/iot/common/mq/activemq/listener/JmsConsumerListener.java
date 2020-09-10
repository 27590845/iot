package com.xidian.iot.common.mq.activemq.listener;

import com.xidian.iot.common.mq.MqMessageListener;
import com.xidian.iot.common.mq.activemq.parser.JmsMessageParser;

import javax.jms.Message;
import javax.jms.MessageListener;
import java.util.Map;

/**
 * @author mrl
 * @Title: JmsConsumerListener
 * @Package
 * @Description:
 * @date 2020/9/9 12:46 上午
 */
public abstract class JmsConsumerListener implements MessageListener, MqMessageListener {

    // 接受消息，并且提取消息体和topic，并交给具体业务逻辑层
    @Override
    public void onMessage(Message msg) {
        Map<Integer, Object> res = JmsMessageParser.parseJmsMessage(msg);
        if(res==null || res.size()<=0) return;
        onMessage(res.get(JmsMessageParser.MSG_TOPIC), res.get(JmsMessageParser.MSG_CONTENT));
    }
}
