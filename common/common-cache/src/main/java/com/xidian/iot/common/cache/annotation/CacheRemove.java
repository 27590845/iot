package com.xidian.iot.common.cache.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author mrl
 * @Title: CacheRemove
 * @Package
 * @Description: 支持模糊匹配
 * @date 2020/9/22 6:19 下午
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CacheRemove {

    /**
     * 需要清除的大类 例如 autocms 所有缓存
     *
     * @return
     */
    String value() default "";


    /**
     * 需要清除的具体的额类型
     *
     * @return
     */
    String[] key() default {};
}
