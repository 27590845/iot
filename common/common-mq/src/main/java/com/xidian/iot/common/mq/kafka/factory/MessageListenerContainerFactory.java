package com.xidian.iot.common.mq.kafka.factory;

import lombok.Setter;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.listener.KafkaMessageListenerContainer;
import org.springframework.kafka.listener.MessageListener;

/**
 * @author mrl
 * @Title: MessageListenerContainerFactory
 * @Package
 * @Description: 动态订阅容器工厂
 * @date 2020/9/9 3:00 下午
 */
public class MessageListenerContainerFactory {

    @Setter
    private ConsumerFactory strConsumerFactory;
    @Setter
    private ConsumerFactory byteConsumerFactory;
    @Setter
    private ConsumerFactory objConsumerFactory;

    public static final int STR_FACTORY = 0;
    public static final int BYTE_FACTORY = 1;
    public static final int OBJ_FACTORY = 2;

    public KafkaMessageListenerContainer getKafkaMessageListenerContainer(int factoryType, MessageListener messageListener, String ... topicName){
        ContainerProperties properties = new ContainerProperties(topicName);
        properties.setMessageListener(messageListener);
        KafkaMessageListenerContainer container = null;
        switch (factoryType){
            case STR_FACTORY:
                container = new KafkaMessageListenerContainer(strConsumerFactory, properties);
                break;
            case BYTE_FACTORY:
                container = new KafkaMessageListenerContainer(byteConsumerFactory, properties);
                break;
            case OBJ_FACTORY:
                container = new KafkaMessageListenerContainer(objConsumerFactory, properties);
                break;
        }
        if (container != null){
            container.setAutoStartup(false);
        }
        return container;
    }

}
