package com.xidian.iot.common.mq;

import javax.jms.JMSException;
import javax.jms.MessageListener;

/**
 * @author mrl
 * @Title: ActivemqSubscriber
 * @Package com.xidian.iot.common.mq
 * @Description: subscrib message from mq
 * @date 2020/9/8 3:38 下午
 */
public interface MqSubscriber {

    /**
     * 动态生成一个消费者
     * @param topic
     * @param listener
     */
    String subscribe(String topic, MessageListener listener) throws JMSException;

    /**
     * 根据subscribe生成的clientId，关闭一个消费者连接
     * @param clientId
     * @return
     */
    boolean unSubscribe(String clientId) throws JMSException;
}
