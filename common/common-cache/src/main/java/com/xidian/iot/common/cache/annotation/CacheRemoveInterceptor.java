package com.xidian.iot.common.cache.annotation;

import com.xidian.iot.common.cache.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Method;

/**
 * @author mrl
 * @Title: CacheRemoveInterceptor
 * @Package
 * @Description:
 * @date 2020/9/22 6:23 下午
 */
@Aspect
@Slf4j
@Component
public class CacheRemoveInterceptor {

    @Resource
    RedisUtil redisUtil;

    //截获标有@CacheRemove的方法
    @Pointcut(value = "(execution(* *.*(..)) && @annotation(CacheRemove))")
    private void pointcut() {
    }

    /**
     * 功能描述: 切面在截获方法返回值之后
     *
     * @return void
     * @throws
     * @author fuyuchao
     * @date 2018/9/14 16:55
     * @params [joinPoint]
     */
    @AfterReturning(value = "pointcut()")
    private void process(JoinPoint joinPoint) {
        //获取被代理的类
        Object target = joinPoint.getTarget();
        //获取切入方法的数据
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        //获取切入方法
        Method method = signature.getMethod();
        //获得注解
        CacheRemove cacheRemove = method.getAnnotation(CacheRemove.class);

        if (cacheRemove != null) {
            //清除当前类的缓存
            //cleanRedisCache("*" + target.getClass().toString() + "*");

            String value = cacheRemove.value();
            if (!value.equals("")) {
                //缓存的项目所有redis业务部缓存
                redisUtil.del(value);
            }
            //需要移除的正则key
            String[] keys = cacheRemove.key();
            if(keys!=null && keys.length>0){
                redisUtil.del(keys);
            }
        }
    }

    private void cleanRedisCache(String key) {
        if (key != null) {
            redisUtil.del(key);//删除缓存
            log.info("清除 " + key + " 缓存");
        }
    }
}
