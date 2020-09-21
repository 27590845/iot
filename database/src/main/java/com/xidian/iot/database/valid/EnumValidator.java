package com.xidian.iot.database.valid;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.Method;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * @author mrl
 * @Title: EnumValidator
 * @Package
 * @Description: 参数值域校验逻辑
 * @date 2020/9/18 1:56 下午
 */
public class EnumValidator implements ConstraintValidator<EnumValidation, Object> {

    private EnumValidation annotation;

    @Override
    public void initialize(EnumValidation constraintAnnotation) {
        this.annotation = constraintAnnotation;
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        //如果为null就不校验
        if (value == null) return true;
        boolean flag  =false;
        if(annotation.strings().length>0){
            flag = Stream.of(annotation.strings()).anyMatch(x -> value.equals(x));
        }else if(annotation.ints().length>0){
//            elements = Arrays.stream(annotation.ints()).boxed().toArray(Integer[]::new);  //装箱方式会影响效率，弃用
            int val = (value instanceof Byte)? Byte.toUnsignedInt((byte) value): (int) value;
            flag = IntStream.of(annotation.ints()).anyMatch(x -> val==x);
        }else if(annotation.longs().length>0){
            flag = LongStream.of(annotation.longs()).anyMatch(x -> (long)value==x);
        }else if(annotation.doubles().length>0){
            flag = DoubleStream.of(annotation.doubles()).anyMatch(x -> (double)value==x);
        }else if(annotation.clazz()!=null) {
            Object[] objects = annotation.clazz().getEnumConstants();
            try {
                Method method = annotation.clazz().getMethod(annotation.method());
                //被校验的值必须出现在规定的集合内
                for (Object o : objects) {
                    if (value.equals(method.invoke(o))) {
                        flag = true;
                        break;
                    }
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return flag;
    }
}
