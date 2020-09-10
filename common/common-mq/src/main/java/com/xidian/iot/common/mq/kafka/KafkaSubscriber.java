package com.xidian.iot.common.mq.kafka;

import com.xidian.iot.common.mq.MqMessageListener;
import com.xidian.iot.common.mq.MqSubscriber;
import com.xidian.iot.common.mq.kafka.factory.MessageListenerContainerFactory;
import com.xidian.iot.common.mq.kafka.parser.KafkaMessageParser;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.kafka.listener.KafkaMessageListenerContainer;
import org.springframework.kafka.listener.MessageListener;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author mrl
 * @Title: KafkaSubscriber
 * @Package
 * @Description: 动态订阅 每开启一个container就会启动一个线程，另外kafka可以实现多个主题通过一个container监听，因此建议将相关的主题交由一个container监听
 * @date 2020/9/9 12:31 上午
 */
public class KafkaSubscriber implements MqSubscriber {

//    @Setter
//    private ConsumerFactory strConsumerFactory;
//    @Setter
//    private ConsumerFactory byteConsumerFactory;
//    @Setter
//    private ConsumerFactory objConsumerFactory;
    @Setter
    private MessageListenerContainerFactory containerFactory;

//    @Override
//    public String subscribe(MqMessageListener listener, String ... topicNames){
//        Consumer<String, String> consumer = strConsumerFactory.createConsumer();
//        consumer.subscribe(Arrays.asList(topicNames));
//        consumer.commitAsync();
//        while (true) {
//            ConsumerRecords<String, String> records = consumer.poll(5000);
//            for (ConsumerRecord<String, String> record : records)
//                listener.onMessage(record.topic(), record);
//        }
//    }

    /**
     * 该map用来保存订阅者及其对应的 activemq connection
     */
    private static final ConcurrentHashMap<String, KafkaMessageListenerContainer> containers = new ConcurrentHashMap<>();

    @Override
    public String subscribe(final MqMessageListener listener, String... topics) {
        KafkaMessageListenerContainer container = containerFactory.getKafkaMessageListenerContainer(MessageListenerContainerFactory.STR_FACTORY, new MessageListener() {
            @Override
            public void onMessage(Object data) {
                Map<Integer, Object> res = KafkaMessageParser.parse(data);
                if(res==null || res.size()<=0) return;
                listener.onMessage(res.get(KafkaMessageParser.MSG_TOPIC), res.get(KafkaMessageParser.MSG_CONTENT));
            }
        }, topics);
        //将container的hashcode作为consumerId
        String consumerId = String.valueOf(container.hashCode());
        containers.put(consumerId, container);
        container.start();
        return consumerId;
    }

    @Override
    public boolean unSubscribe(String consumerId) {
        if(StringUtils.isEmpty(consumerId)) return false;
        KafkaMessageListenerContainer container = containers.get(consumerId);
        if(container != null){
            container.stop();
            containers.remove(consumerId);
        }
        return true;
    }
}
