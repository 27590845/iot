package com.xidian.iot.datacenter.exception;

import com.xidian.iot.common.util.exception.ExceptionInfo;
import com.xidian.iot.datacenter.constant.ErrorCode;
import lombok.Getter;
import org.springframework.core.NestedCheckedException;

/**
 * @author mrl
 * @Title: AbortChainException
 * @Package
 * @Description: 执行责任链过程中出现的异常    copy from langyan
 * @date 2020/9/10 10:22 上午
 */
public class AbortChainException extends NestedCheckedException implements ExceptionInfo {

    private static final long serialVersionUID = 5814107678686110112L;

    /**
     * 状态码
     */
    @Getter
    private int code;

    /**
     * 实例化一个需要捕获的异常
     *
     * @param errorCode 错误码
     */
    public AbortChainException(ErrorCode errorCode) {
        super(errorCode.getMsg());
        this.code = errorCode.getCode();
    }

    /**
     * 实例化一个需要捕获的异常
     *
     * @param errorCode 错误码
     * @param args 错误消息参数
     */
    public AbortChainException(ErrorCode errorCode, Object... args) {
        super(String.format(errorCode.getMsg(), args));
        this.code = errorCode.getCode();
        ;
    }
}