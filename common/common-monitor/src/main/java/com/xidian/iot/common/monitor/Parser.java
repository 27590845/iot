package com.xidian.iot.common.monitor;

/**
 * @description: 将原始的日志输出字符串，解析成约定的格式
 * @author: mrl
 * @date: 2020/12/14 下午4:24
 */
public interface Parser {

    /**
     * 获取帧间隔符，帧间隔符用于标识一次日志输出的开始
     * @return
     */
    String getFrameHeaderTag();

    /**
     * 作用类似于中间件中的数据帧解析
     * @param frameStr
     * @return
     */
    Frame parseFrame(String frameStr);
}
