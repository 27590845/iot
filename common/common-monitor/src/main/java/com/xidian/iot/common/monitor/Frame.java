package com.xidian.iot.common.monitor;

import lombok.Data;

/**
 * @description: 一次日志输出，格式化之后的形态
 * @author: mrl
 * @date: 2020/12/14 下午8:03
 */
@Data
public class Frame {

    public static final int TYPE_ALL = -1;
    public static final int TYPE_INFO = 0;  //对应logback中的info级别
    public static final int TYPE_WARN = 1;  //对应logback中的warn级别
    public static final int TYPE_ERROR = 2; //对应logback中的error级别

    private int type;

    //捕获时间
    private long timeStamp;

    //捕获地点(哪个节点)
//    private String locale;

    //捕获对象(导致这次日志被打印的对象，比如某个方法的哪行代码)
    private String signature;

    //日志主体内容
    private String content;

    @Override
    public String toString() {
        return "Frame{" +
                "type=" + type +
                ", timeStamp=" + timeStamp +
                ", signature='" + signature + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
