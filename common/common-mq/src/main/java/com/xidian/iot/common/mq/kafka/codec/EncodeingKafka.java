package com.xidian.iot.common.mq.kafka.codec;

import com.xidian.iot.common.util.BeanUtils;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

/**
 * @author mrl
 * @Title: EncodeingKafka
 * @Package
 * @Description:
 * @date 2020/9/8 10:48 下午
 */
public class EncodeingKafka implements Serializer<Object> {
    @Override
    public void configure(Map configs, boolean isKey) {

    }
    @Override
    public byte[] serialize(String topic, Object data) {
        return BeanUtils.bean2Byte(data);
    }
    /*
     * producer调用close()方法是调用
     */
    @Override
    public void close() {
        System.out.println("EncodeingKafka is close");
    }
}
