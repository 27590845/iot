package com.xidian.iot.databiz.constants;

/**
 * 标示第一位
 * @author: Hansey
 * @date: 2020-09-10 10:35
 */
public enum EncodeType {
    /**
     * 节点标识
     */
    EncodeNode(0,"节点标识"),
    /**
     * 网关标识
     */
    EncodeGateway(1,"网关标识"),
    /**
     * 域标识
     */
    EncodeDomain(2,"域标识");


    /**
     * 标识码
     */
    private int code;

    /**
     * 标识信息
     */
    private String msg;


    /**
     * 构造一个标识码
     *
     * @param code
     * @param msg
     */
    EncodeType(int code ,String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     *
     * 获得标识码
     */
    public int getCode() {
        return code;
    }

    /**
     * 获得标识信息
     *
     * @return  标识信息
     */
    public String getMsg() {
        return msg;
    }
}
