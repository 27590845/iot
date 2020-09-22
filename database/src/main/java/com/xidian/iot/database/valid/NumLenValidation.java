package com.xidian.iot.database.valid;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.ElementType.TYPE_USE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author mrl
 * @Title: NumLenValidation
 * @Package
 * @Description: 整数长度校验
 * @date 2020/9/22 10:50 上午
 */
@Documented
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RUNTIME)
@Repeatable(NumLenValidation.List.class)
@Constraint(validatedBy = {NumLenValidator.class})
public @interface NumLenValidation {

    String message() default "{*.validation.constraint.Enum.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    /**
     * 待验证参数的长度值域
     * @return
     */
    int[] lens();

    /**
     * lens()指的是否是二进制下的长度，默认为false，即十进制下的长度
     * @return
     */
    boolean binary() default false;

    /**
     * 枚举类的类名，该枚举类内存储了指定的集合
     * @return Class
     */
    Class<?> clazz() default Object.class;

    @Documented
    @Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
    @Retention(RUNTIME)
    @interface List {
        NumLenValidation[] value();
    }
}
