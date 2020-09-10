package com.xidian.iot.datacenter.constant;

/**
 * @author mrl
 * @Title: ErrorCode
 * @Package
 * @Description: error code and desc    copy from langyan
 * @date 2020/9/10 10:40 上午
 */
public enum ErrorCode {
    /**
     * 你的场景并不存在。
     */
    ERR100010(-100100, "Your scene does not exist."),
    /**
     * 您的key没有通过，你可以使用场景key或用户key。
     */
    ERR100020(-100200, "Your key did not passed , your can use the scene's key or user's key."),
    /**
     * 您的key没有通过，你只能使用场景key。
     */
    ERR100021(-100201, "Your key did not passed , you can only use the scene's key."),
    /**
     * 您的JSON数据为空，请设置HTTP的body。
     */
    ERR100030(-100030, "Your JSON data is empty , please set the HTTP body."),
    /**
     * 您的HTTP body数据不符合json格式。
     */
    ERR100031(-100031, "Your HTTP body data does not meet the json format."),
    /**
     * 您的JSON数据缺少datastreams字段。
     */
    ERR100032(-100032, "Your JSON data missing 'datastreams' field."),
    /**
     * 您的datastreams字段无值。
     */
    ERR100033(-100033, "Your 'datastreams' field no value."),
    /**
     * 您的datastreams字段没有合法数据,请检查是否包含[%s]。被丢弃的数据[%s]。
     */
    ERR100034(
            -100034,
            "Your 'datastreams' field have no legal data , please check whether contains '%s' or not contains any attribute that except '%s' '%s'. The discard data [%s]."),
    /**
     * 保存文件发生错误.
     */
    ERR100040(-100040, "Save the up file happen error.");

    /**
     * 错误码
     */
    private int code;
    /**
     * 错误信息
     */
    private String msg;

    /**
     * 构造一个错误码
     *
     * @param code 错误码
     * @param msg 错误消息
     */
    ErrorCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 获得错误消息。
     *
     * @return 错误消息
     */
    public String getMsg() {
        return msg;
    }

    /**
     * 获得错误码
     *
     * @return 错误码
     */
    public int getCode() {
        return code;
    }
}
