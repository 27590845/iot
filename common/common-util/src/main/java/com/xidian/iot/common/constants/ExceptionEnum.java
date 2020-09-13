package com.xidian.iot.common.constants;


import com.xidian.iot.common.exception.ExceptionInfo;

/**
 * 异常响应结果枚举
 * @author: Hansey
 * @date: 2020-09-06 20:32
 */
public enum ExceptionEnum implements ExceptionInfo {


    GET_TOKEN_ERROR(-10001,"获取token失败"),
    INVALID_TOKEN(-10002, "无效的token"),
    UNKNOWN_ACCOUNT(-10003, "用户名或密码错误"),
    NETWORK_ERROR(-10004, "网络异常，请稍后重试"),
    PARAMETER_VERIFICATION_ERROR(-10005, "参数校验错误"),


    SCENE_NOT_EXIST(-10006, "该网关不存在"),
    NODE_NOT_EXIST(-10007, "该节点不存在"),
    NODE_ATTR_NOT_EXIST(-10008, "该节点属性不存在"),
    NODE_ATTR_STD_EXIST(-10009, "该节点属性模版不存在"),


    ;

    private int code;
    private String message;

    ExceptionEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}