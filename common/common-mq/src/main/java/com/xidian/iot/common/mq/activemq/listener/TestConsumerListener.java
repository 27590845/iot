package com.xidian.iot.common.mq.activemq.listener;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.BytesMessage;
import javax.jms.Message;
import javax.jms.MessageListener;

/**
 * @author mrl
 * @Title: TestConsumerListener
 * @Package
 * @Description:
 * @date 2020/9/9 12:46 上午
 */
@Slf4j
public class TestConsumerListener implements MessageListener {

    // 接受消息，并且提取消息体和topic
    public void onMessage(Message msg) {
        try {
            if (msg instanceof BytesMessage) {
                log.info("------Received BytesMessage------");
                BytesMessage message = (BytesMessage) msg;
                byte[] byteContent = new byte[1024];
                int length = -1;
                StringBuffer content = new StringBuffer();
                //如果消息体不为空
                while ((length = message.readBytes(byteContent)) != -1) {
                    content.append(new String(byteContent, 0, length));
                }
                // 消息体
                String msgString = content.toString();
                // 获取topic
                String msgtopic = message.getJMSDestination().toString();
            }
        } catch (Exception e) {
            log.error("解析消息出错:", e);
        }
    }
}
