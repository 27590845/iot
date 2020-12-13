package com.xidian.iot.common.cache.factory;

import com.xidian.iot.common.cache.executer.DynamicRedisCacheWriter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;

import java.time.Duration;

/**
 * @author mrl
 * @Title: RedisCacheManagerFactory
 * @Package
 * @Description:
 * @date 2020/9/13 7:48 下午
 */
public class RedisCacheManagerFactory {

    /**
     * 获取RedisCacheManager的工厂类
     * @param expireSeconds 缓存有效时间
     * @param redisConnectionFactories redis连接工厂，一个工厂对应一个redis节点。
     *  当工厂数量为2时，说明启用读写分离，默认第一个工厂绑定到master节点，第二个绑定到slave(readonly)节点；否则使用单节点模式，默认第一个工厂为唯一节点
     * @return
     */
    public RedisCacheManager getRedisCacheManager(Long expireSeconds, RedisConnectionFactory ...redisConnectionFactories) {
        if(redisConnectionFactories == null || redisConnectionFactories.length==0) return null;
        RedisCacheWriter redisCacheWriter;
        if(redisConnectionFactories.length==2){
            redisCacheWriter = new DynamicRedisCacheWriter(redisConnectionFactories[0], redisConnectionFactories[1]);
        }else{
            redisCacheWriter = RedisCacheWriter.nonLockingRedisCacheWriter(redisConnectionFactories[0]);
        }
        //设置CacheManager的值序列化方式为json序列化
        RedisSerializer<Object> jsonSerializer = new GenericJackson2JsonRedisSerializer();
        RedisSerializationContext.SerializationPair<Object> pair = RedisSerializationContext.SerializationPair.fromSerializer(jsonSerializer);
        //必须通过链式函数初始化config
        RedisCacheConfiguration defaultCacheConfig = RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofSeconds(expireSeconds))
                .serializeValuesWith(pair);
        //初始化RedisCacheManager
        return new RedisCacheManager(redisCacheWriter, defaultCacheConfig);
    }
}
