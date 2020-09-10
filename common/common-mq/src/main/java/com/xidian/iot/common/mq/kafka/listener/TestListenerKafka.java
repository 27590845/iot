package com.xidian.iot.common.mq.kafka.listener;

/**
 * @author mrl
 * @Title: TestListenerKafka
 * @Package
 * @Description:
 * @date 2020/9/10 2:18 下午
 */
public class TestListenerKafka extends KafkaConsumerListener {
    @Override
    public void onMessage(Object topicName, Object message) {
        System.out.printf("topicName = %s, message = %s\n", topicName, message);
    }
}
