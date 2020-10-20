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
 *  使用Consumer high level API时，同一Topic的一条消息只能被同一个Consumer Group内的一个Consumer消费，但多个Consumer Group可同时消费这一消息。
 *  这是Kafka用来实现一个Topic消息的广播（发给所有的Consumer）和单播（发给某一个Consumer）的手段。一个Topic可以对应多个Consumer Group。
 *  如果需要实现广播，只要每个Consumer有一个独立的Group就可以了。要实现单播只要所有的Consumer在同一个Group里。用Consumer Group还可以将Consumer进行自由的分组而不需要多次发送消息到不同的Topic。
 *  具体介绍连接: https://www.cnblogs.com/liuwei6/p/6900686.html
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
    public String subscribeTopic(final MqMessageListener listener, String... topics) {
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

    /**
     * kafka没有点对点模式和订阅者模式的概念，而是用groupId来区分，具体区别见{@link KafkaSubscriber}的注释
     * 另外，因为配置文件{classpath:/spring/application-kafka-consumer-def.xml}里把groupId固定了，所以相当于点对点模式
     */
    @Override
    public String subscribeQueue(MqMessageListener listener, String... topics){
        return subscribeTopic(listener, topics);
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
