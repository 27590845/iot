package com.xidian.iot.common.mq.activemq.listener;

/**
 * @author mrl
 * @Title: TestListenerJms
 * @Package
 * @Description:
 * @date 2020/9/10 1:18 下午
 */
public class TestListenerJms extends JmsConsumerListener {
    @Override
    public void onMessage(Object topicName, Object message) {
        System.out.printf("topicName = %s, message = %s\n", topicName, message);
    }
}
