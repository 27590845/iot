package com.xidian.iot.dataapi.constants;

import com.xidian.iot.dataapi.exception.ExceptionInfo;

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