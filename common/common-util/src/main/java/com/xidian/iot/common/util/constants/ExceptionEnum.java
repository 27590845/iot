package com.xidian.iot.common.util.constants;


import com.xidian.iot.common.util.exception.ExceptionInfo;

/**
 * 异常响应结果枚举
 * @author: Hansey
 * @date: 2020-09-06 20:32
 */
public enum ExceptionEnum implements ExceptionInfo {


    GET_TOKEN_ERROR(-10001,"获取token失败"),
    INVALID_TOKEN(-10002, "无效的token"),
    EXPIRE_TOKEN(-10003, "token过期"),
    MISMATCH_TOKEN(-10004,"token不匹配"),

    UNKNOWN_ACCOUNT(-10005, "用户名或密码错误"),
    NETWORK_ERROR(-10006, "网络异常，请稍后重试"),
    PARAMETER_VERIFICATION_ERROR(-10007, "参数校验错误"),


    SCENE_NOT_EXIST(-10008, "该网关不存在"),
    NODE_NOT_EXIST(-10009, "该节点不存在"),
    NODE_ATTR_NOT_EXIST(-10010, "该节点属性不存在"),
    NODE_ATTR_STD_NOT_EXIST(-10011, "该节点属性模版不存在"),

    SCENE_ALREADY_EXIST(-10012, "该网关已经存在"),
    NODE_ALREADY_EXIST(-10013, "该节点已经存在"),


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