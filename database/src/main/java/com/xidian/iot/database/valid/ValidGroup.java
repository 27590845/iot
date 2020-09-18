package com.xidian.iot.database.valid;

/**
 * @author mrl
 * @Title: ValidGroup
 * @Package
 * @Description: 分类 http request 类型，主要应用于validate分组
 * @date 2020/9/18 12:23 下午
 */
public interface ValidGroup {

    /**
     * 1.当不适用分组校验时，尤其是集合校验，需要在controller类上添加@validated注解，在入参处添加@valid注解
     *
     * 2.当使用分组校验时，
     *
     * （1）直接在controller层进行校验时，只需要在入参处添加@validated(xxx.class)即可
     *
     * （2）若是在service层进行校验时，需要在方法体上方加入@validated(xxx.class)，在入参处添 加@valid注解，也需要在类上加上@validated注解
     *
     * ps：在service层进行校验时，若是不是controller层直接调用，校验会失效，若存在逻辑判断进入方法体，需将逻辑写在controller层，具体原因不清楚，待分析
     */

    interface SELECT {}
    interface INSERT {}
    interface UPDATE {}
    interface DELETE {}
}
