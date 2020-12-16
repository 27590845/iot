package com.xidian.iot.datacenter.listener;

import com.xidian.iot.common.monitor.Frame;
import com.xidian.iot.common.monitor.FrameCaughtListener;
import lombok.extern.slf4j.Slf4j;

/**
 * @description:
 * @author: mrl
 * @date: 2020/12/14 下午11:21
 */
@Slf4j
public class MonitorListener implements FrameCaughtListener {
    @Override
    public void onFrameCaught(Frame frame) {
        log.info("捕获到输出：{}", frame.toString());
    }
}
