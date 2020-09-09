package com.xidian.iot.common.mq;

import javax.jms.JMSException;

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
     * @param topics
     * @param listener
     */
    String subscribe(MqMessageListener listener, String ... topics) throws Exception;

    /**
     * 根据subscribe生成的consumerId，关闭一个消费者连接
     * @param consumerId
     * @return
     */
    boolean unSubscribe(String consumerId) throws Exception;
}
