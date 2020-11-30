package com.xidian.iot.database.dynamic.annotation;

import com.xidian.iot.database.dynamic.DynamicDataSourceHolder;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;

/**
 * @author mrl
 * @Title: DynamicDataSourceAspect
 * @Package
 * @Description:
 * @date 2020/11/22 2:33 上午
 */
@Slf4j
public class DynamicDataSourceInterceptor {
    public void pointCut(){};
    public void before(JoinPoint point)
    {
        Object target = point.getTarget();
        String methodName = point.getSignature().getName();
        Class<?>[] clazz = target.getClass().getInterfaces();
        Class<?>[] parameterTypes = ((MethodSignature) point.getSignature()).getMethod().getParameterTypes();
        try {
            Method method = clazz[0].getMethod(methodName, parameterTypes);
            if (method != null && method.isAnnotationPresent(DataSource.class)) {
                DataSource data = method.getAnnotation(DataSource.class);
                DynamicDataSourceHolder.putDataSource(data.value());
            }
        } catch (Exception e) {
            log.error(String.format("Choose DataSource error, method:%s, msg:%s", methodName, e.getMessage()));
        }
    }
    public void after(JoinPoint point) {
        DynamicDataSourceHolder.clearDataSource();
    }
}
