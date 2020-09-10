package com.xidian.iot.common.mq;

/**
 * @author mrl
 * @Title: MqMessageListener
 * @Package com.xidian.iot.common.mq
 * @Description: common MqMessageListener
 * @date 2020/9/9 10:50 上午
 */
public interface MqMessageListener<K, V> {

    void onMessage(K topicName, V message);
}
