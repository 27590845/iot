package com.xidian.iot.common.alert.alertsender;

import com.xidian.iot.common.alert.AlertVo;

/**
 * 报警对象接口
 * @author wmr
 *
 */
public interface Alert {
    /**
     * 设置报警信息
     *
     * @param destination 报警的接收人
     * @param content 报警文本
     * @return 报警信息对象
     */
    AlertVo set(String destination,String content);
    
    /**
     * 发送报警信息
     * 
     * @param destination 报警的接收人
     * @param content 报警文本
     */
     void send(String destination,String content);
}