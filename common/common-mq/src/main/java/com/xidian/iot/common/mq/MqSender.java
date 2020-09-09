package com.xidian.iot.common.mq;

import com.fasterxml.jackson.core.JsonProcessingException;

import javax.jms.JMSException;
import java.io.Serializable;
import java.util.HashMap;

/**
 * @author mrl
 * @Title: ActivemqSender
 * @Package com.xidian.iot.common.mq
 * @Description: send message to mq
 * @date 2020/9/8 3:37 下午
 */
public interface MqSender {

    /*
     * 消息体是一个对象，把可序列化对象转化为消息体
     */
    void sendSeriObj(String topicName, final Serializable serializedObj) throws JsonProcessingException;

    /**
     * 发送activemq byte message
     * @param topicName
     * @param jsonStr
     * @throws JMSException
     */
    void sendSeriObjByte(String topicName, final String jsonStr) throws JsonProcessingException;

    /**
     * 发送一条消息到指定的队列（目标）
     * @param topicName 队列名称
     * @param message 消息内容
     */
    void send(String topicName, final String message) throws JsonProcessingException;
}
