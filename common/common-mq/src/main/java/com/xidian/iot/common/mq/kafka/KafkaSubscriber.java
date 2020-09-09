package com.xidian.iot.common.mq.kafka;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRebalanceListener;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.common.TopicPartition;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

/**
 * @author mrl
 * @Title: KafkaSubscriber
 * @Package
 * @Description: 动态订阅 未完成
 * @date 2020/9/9 12:31 上午
 */
@Deprecated
public class KafkaSubscriber {

    DefaultKafkaConsumerFactory factory;

    public void subscribe(String ... topicNames){
        Consumer<String, String> consumer = factory.createConsumer();
        consumer.subscribe(Arrays.asList(topicNames));
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(100);
            for (ConsumerRecord<String, String> record : records)

                // print the offset,key and value for the consumer records.
                System.out.printf("offset = %d, key = %s, value = %s\n",
                        record.offset(), record.key(), record.value());
        }

    }
}
