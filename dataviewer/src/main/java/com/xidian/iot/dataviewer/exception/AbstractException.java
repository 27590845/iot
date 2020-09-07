package com.xidian.iot.dataviewer.exception;

abstract class AbstractException extends RuntimeException implements ExceptionInfo {
    private static final long serialVersionUID = 3289598099629324399L;
    private final int code;
    private final String message;
    //transient 声明变量时，当对象存储时，它的值不需要维持，即不持久化。也就是不会为这个变量分配内存来保存。并且在序列化时被忽略。
    private final transient Object body;
    //抽象类中可以有构造函数哦
    public AbstractException(ExceptionInfo info){
        this.code = info.getCode();
        this.message = info.getMessage();
        this.body = null;
    }


    public AbstractException(int code){
        this.code = code;
        this.message = null;
        this.body = null;
    }

    public AbstractException(ExceptionInfo info, Object body){
        this.code = info.getCode();
        this.message = info.getMessage();
        this.body = body;
    }

    public AbstractException(int code, String message){
        this.code = code;
        this.message = message;
        this.body = null;
    }
    @Override
    public String getMessage() {
        return message;
    }
    @Override
    public int getCode() {
        return code;
    }

    public Object getBody() {
        return body;
    }
}
