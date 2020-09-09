package com.xidian.iot.common.mq.kafka.listener;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.listener.MessageListener;

/**
 * @author mrl
 * @Title: TestStrConsumerListener
 * @Package
 * @Description:
 * @date 2020/9/9 12:25 上午
 */
@Slf4j
public class TestStrConsumerListener implements MessageListener<String, String> {

    /**
     * 监听器自动执行该方法 消费消息 自动提交offset 执行业务代码 （high level api
     * 不提供offset管理，不能指定offset进行消费）
     */
    @Override
    public void onMessage(ConsumerRecord<String, String> record) {
        log.info("=============kafkaConsumer开始消费=============");
        String topic = record.topic();
        String key = record.key();
        String value = record.value();
        long offset = record.offset();
        int partition = record.partition();
        log.info("-------------topic:" + topic);
        log.info("-------------value:" + value);
        log.info("-------------key:" + key);
        log.info("-------------offset:" + offset);
        log.info("-------------partition:" + partition);
        log.info("~~~~~~~~~~~~~kafkaConsumer消费结束~~~~~~~~~~~~~");
        log.info("consumer topic:" + topic + ",value:" + value);
    }
}
