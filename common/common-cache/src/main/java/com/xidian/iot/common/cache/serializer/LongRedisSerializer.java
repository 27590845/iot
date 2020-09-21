package com.xidian.iot.common.cache.serializer;

import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

/**
 * @author mrl
 * @Title: LongRedisSerializer
 * @Package
 * @Description: redis 的Long类型序列化策略
 * @date 2020/9/20 4:38 下午
 */
public class LongRedisSerializer implements RedisSerializer<Long> {


    @Override
    public byte[] serialize(Long aLong) throws SerializationException {
        if (aLong != null) {
            byte[] b = new byte[8];
            for (int i = 0; i < b.length; i++) {
                b[i] = new Long(aLong & 0xff).byteValue();//
                aLong = aLong >> 8; // 向右移8位
            }
            return b;
        }
        return new byte[0];
    }

    @Override
    public Long deserialize(byte[] bytes) throws SerializationException {
        if(bytes!=null && bytes.length>0){
            long s = 0;
            long s0 = bytes[0] & 0xff;// 最低位
            long s1 = bytes[1] & 0xff;
            long s2 = bytes[2] & 0xff;
            long s3 = bytes[3] & 0xff;
            long s4 = bytes[4] & 0xff;// 最低位
            long s5 = bytes[5] & 0xff;
            long s6 = bytes[6] & 0xff;
            long s7 = bytes[7] & 0xff;

            // s0不变
            s1 <<= 8;
            s2 <<= 16;
            s3 <<= 24;
            s4 <<= 8 * 4;
            s5 <<= 8 * 5;
            s6 <<= 8 * 6;
            s7 <<= 8 * 7;
            s = s0 | s1 | s2 | s3 | s4 | s5 | s6 | s7;
            return s;
        }
        return -1L;
    }

    public static void main(String[] args) {
        LongRedisSerializer serializer = new LongRedisSerializer();
        byte[] b = serializer.serialize(123456L);
        System.out.println(b);
        Long l = serializer.deserialize(b);
        System.out.println(l);
    }
}
