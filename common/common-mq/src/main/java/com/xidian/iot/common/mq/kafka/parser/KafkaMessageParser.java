package com.xidian.iot.common.mq.kafka.parser;

import org.apache.commons.lang3.StringUtils;
import org.apache.kafka.clients.consumer.ConsumerRecord;

import java.util.HashMap;
import java.util.Map;

/**
 * @author mrl
 * @Title: KafkaMessageParser
 * @Package
 * @Description: 解析kafka消息
 * @date 2020/9/10 1:34 下午
 */
public class KafkaMessageParser {

    public final static int MSG_TOPIC = 0;
    public final static int MSG_CONTENT = 1;

    public static Map<Integer, Object> parseKafkaMessage(ConsumerRecord record){
        Map<Integer, Object> res = null;
        String topic = record.topic();
        String key = (String) record.key();
        String value = (String) record.value();
        long offset = record.offset();
        int partition = record.partition();
        if(StringUtils.isNotEmpty(topic)){
            res = new HashMap<>();
            res.put(MSG_TOPIC, topic);
            res.put(MSG_CONTENT, value);
        }
        return res;
    }

    public static Map<Integer, Object> parse(Object data) {
        if(data != null && data instanceof ConsumerRecord) {
            return parseKafkaMessage((ConsumerRecord) data);
        }else {
            return null;
        }
    }
}
