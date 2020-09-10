package com.xidian.iot.common.mq.kafka.listener;

import com.xidian.iot.common.mq.MqMessageListener;
import com.xidian.iot.common.mq.kafka.parser.KafkaMessageParser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.listener.MessageListener;

import java.util.Map;

/**
 * @author mrl
 * @Title: KafkaConsumerListener
 * @Package
 * @Description:
 * @date 2020/9/9 12:25 上午
 */
@Slf4j
public abstract class KafkaConsumerListener implements MessageListener, MqMessageListener {

    /**
     * 监听器自动执行该方法 消费消息 自动提交offset 执行业务代码 （high level api
     * 不提供offset管理，不能指定offset进行消费）
     */
    @Override
    public void onMessage(Object data) {
        Map<Integer, Object> res = KafkaMessageParser.parse(data);
        if(res==null || res.size()<=0) return;
        onMessage(res.get(KafkaMessageParser.MSG_TOPIC), res.get(KafkaMessageParser.MSG_CONTENT));
    }
}
