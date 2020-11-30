package com.xidian.iot.database.dynamic.annotation;
import com.xidian.iot.database.dynamic.DynamicDataSourceGlobal;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author mrl
 * @Title: DataSource
 * @Package
 * @Description: 加在任意函数上，指定使用read数据库还是write数据库
 * @date 2020/11/22 2:29 上午
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface DataSource {
    public DynamicDataSourceGlobal value() default DynamicDataSourceGlobal.READ;
}
