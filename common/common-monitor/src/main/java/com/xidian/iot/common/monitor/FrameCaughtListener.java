package com.xidian.iot.common.monitor;

import lombok.Setter;

/**
 * @description: 每次捕获到日志后要做的事
 * @author: mrl
 * @date: 2020/12/14 下午6:19
 */
public interface FrameCaughtListener {

    void onFrameCaught(Frame frame);
}
