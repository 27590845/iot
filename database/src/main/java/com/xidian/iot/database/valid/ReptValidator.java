package com.xidian.iot.database.valid;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;

/**
 * @author mrl
 * @Title: ReptValidator
 * @Package
 * @Description: 校验数组或List中是否有重复值(用equal函数)
 * @date 2020/9/20 5:45 下午
 */
public class ReptValidator implements ConstraintValidator<ReptValidation, Object> {

    private ReptValidation annotation;

    @Override
    public void initialize(ReptValidation constraintAnnotation) {
        this.annotation = constraintAnnotation;
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        //如果为null就不校验
        if(value == null) return true;
        List<Object> elements = null;
        if(value instanceof  List) elements = (List<Object>) value;
        else if (value.getClass().isArray()) elements = Arrays.asList(value);
        if(elements == null) return true;
        for(int i=0; i<elements.size(); i++){
            Object element = elements.get(i);
            if(element == null) continue;
            for (int j=i+1; j<elements.size(); j++){
                Object member = elements.get(j);
                if(member == null) continue;
                if(element.equals(member)) return false;
            }
        }
        return true;
    }
}
