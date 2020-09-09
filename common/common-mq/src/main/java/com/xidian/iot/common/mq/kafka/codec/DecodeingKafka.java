package com.xidian.iot.common.mq.kafka.codec;

import com.xidian.iot.common.util.BeanUtils;
import org.apache.kafka.common.serialization.Deserializer;

import java.util.Map;

/**
 * @author mrl
 * @Title: DecodeingKafka
 * @Package
 * @Description:
 * @date 2020/9/8 10:45 下午
 */
public class DecodeingKafka implements Deserializer<Object> {

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
    }

    @Override
    public Object deserialize(String topic, byte[] data) {
        return BeanUtils.byte2Obj(data);
    }

    @Override
    public void close() {

    }

}
