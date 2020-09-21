package com.xidian.iot.database.valid;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author mrl
 * @Title: EnumValidation
 * @Package
 * @Description: 参数值域校验 参数值必须包含在指定的集合里
 * @date 2020/9/18 1:51 下午
 */
@Documented
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RUNTIME)
@Repeatable(EnumValidation.List.class)
@Constraint(validatedBy = {EnumValidator.class})
public @interface EnumValidation {

    //暂时不用显示指定的方式判断参数类型，而是根据每种类型的集合长度是否大于零判断
//    int STRING_PARAM = 0;
//    int INT_PARAM = 1;
//    int LONG_PARAM = 2;
//    int DOUBLE_PARAM = 3;
//    int ENUM_TYPE = 4;
    /**
     * 待检验的参数的类型，非空，默认是String类型，该属性和集合类型要对应
     */
//    int paramType() default 0;

    String message() default "{*.validation.constraint.Enum.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    /**
     * 枚举类的类名，该枚举类内存储了指定的集合
     * @return Class
     */
    Class<?> clazz() default Object.class;

    /*************  快速使用方式 start  *************/

    /**
     * 枚举类中"用于返回集合元素值的函数"的函数名，该函数的返回值将用于和入参进行比较
     * @return method's name
     */
    String method() default "ordinal";

    /**
     * 如果觉得定义一个枚举类太麻烦，可以选择直接选择传入一个数组作为集合，待校验的参数必须出现在该集合内
     * 但是java8的自定义注解不接受Object类型，所以各个类型的集合只能分别定义，比如strings是个String类型的集合
     * @return
     */
    String[] strings() default {};

    /**
     * 如果觉得定义一个枚举类太麻烦，可以选择直接选择传入一个数组作为集合，待校验的参数必须出现在该集合内
     * 但是java8的自定义注解不接受Object类型，所以各个类型的集合只能分别定义，比如ints是个int类型的集合
     * @return
     */
    int[] ints() default {};

    /**
     * 如果觉得定义一个枚举类太麻烦，可以选择直接选择传入一个数组作为集合，待校验的参数必须出现在该集合内
     * 但是java8的自定义注解不接受Object类型，所以各个类型的集合只能分别定义，比如longs是个long类型的集合
     * @return
     */
    long[] longs() default {};

    /**
     * 如果觉得定义一个枚举类太麻烦，可以选择直接选择传入一个数组作为集合，待校验的参数必须出现在该集合内
     * 但是java8的自定义注解不接受Object类型，所以各个类型的集合只能分别定义，比如doubles是个double类型的集合
     * @return
     */
    double[] doubles() default {};

    /*************  快速使用方式 end  *************/

    /**
     * 约定的集合 和{@link EnumValidation}一起使用
     * @see EnumValidation
     */
    @Documented
    @Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
    @Retention(RUNTIME)
    @interface List {
        EnumValidation[] value();
    }

    /**
     * 用法示例1
     * @ {@link EnumValidation}(clazz = {@link com.xidian.iot.database.contant.TestEnum}.class, method = "getKey", message = "参数不在已知集合内")
     * private String paramVal;
     *
     * 用法示例2
     * @ {@link EnumValidation}(ints = {1, 2, 3}, message = "参数不在已知集合内")
     * private int paramVal;
     */
}
