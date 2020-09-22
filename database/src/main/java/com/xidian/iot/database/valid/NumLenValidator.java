package com.xidian.iot.database.valid;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * @author mrl
 * @Title: NumLenValidator
 * @Package
 * @Description: 整数长度验证处理器 传入
 * @date 2020/9/22 10:51 上午
 */
public class NumLenValidator implements ConstraintValidator<NumLenValidation, Number> {

    private NumLenValidation annotation;

    @Override
    public void initialize(NumLenValidation constraintAnnotation) {
        this.annotation = constraintAnnotation;
    }

    @Override
    public boolean isValid(Number value, ConstraintValidatorContext context) {
        if(value == null) return true;
        boolean flag;
        Long num = value.longValue();
        //因为最后一次右移后，count不会自增，所以count初始值为1
        int count = 1;
        if(annotation.binary()){
            while ((num >>= 1) > 0) ++count;
        }else {
            while ((num /= 10) > 0) ++count;
        }
        int finalCount = count;
        flag = Arrays.stream(annotation.lens()).filter(x -> x==finalCount).count() > 0;
        return flag;
    }
}
