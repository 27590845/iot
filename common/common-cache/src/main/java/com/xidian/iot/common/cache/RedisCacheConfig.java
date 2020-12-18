package com.xidian.iot.common.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.time.Duration;

/**
 * @author mrl
 * @Title: RedisCacheConfig
 * @Package com.xidian.iot.common.cache
 * @Description: redis cache config
 * @date 2020/9/2 4:17 下午
 */
@EnableCaching
@Configuration
//@PropertySource("classpath:conf/redis-def.properties")
@Deprecated//统一使用xml配置方式声明bean
public class RedisCacheConfig extends CachingConfigurerSupport {

//    @Getter
//    private volatile JedisConnectionFactory jedisConnectionFactory;
//    @Getter
//    private volatile RedisTemplate<String, String> redisTemplate;
//    @Getter
//    private volatile RedisCacheManager redisCacheManager;

//    public RedisCacheConfig() {
//        super();
//    }

//    /**
//     * 带参数的构造方法 初始化所有的成员变量
//     *
//     * @param jedisConnectionFactory
//     * @param redisTemplate
//     * @param redisCacheManager
//     */
//    public RedisCacheConfig(JedisConnectionFactory jedisConnectionFactory
//            , RedisTemplate<String, String> redisTemplate
//            , RedisCacheManager redisCacheManager) {
//        this.jedisConnectionFactory = jedisConnectionFactory;
//        this.redisTemplate = redisTemplate;
//        this.redisCacheManager = redisCacheManager;
//    }

    @Resource(name = "jedisConnectionFactory")
    private RedisConnectionFactory redisConnectionFactory;

    @Value("${redis.expire}")
    private Long expireSeconds;

    @Bean
    public CacheManager cacheManager() {
        //初始化一个RedisCacheWriter
        RedisCacheWriter redisCacheWriter = RedisCacheWriter.nonLockingRedisCacheWriter(redisConnectionFactory);
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

    @Bean
    public KeyGenerator keyGenerator() {
        return new KeyGenerator() {
            //规定  本类名-方法名-参数名 为key(这个是没有自己指定key的时候，自己默认生成的)
            @Override
            public Object generate(Object o, Method method, Object... params) {
                StringBuilder sb = new StringBuilder();
                sb.append(o.getClass().getName());
                sb.append("-");
                sb.append(method.getName());
                sb.append("-");
                for (Object param : params) {
                    sb.append(param.toString());
                }
                return sb.toString();
            }
        };
    }
}
