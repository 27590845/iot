package com.xidian.iot.common.alert.alertsender;

import com.xidian.iot.common.alert.sms.SMSAlertVo;
import com.xidian.iot.common.alert.sms.SMSClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 短信报警对象
 * @author wmr
 *
 */
@Slf4j
@Component
public class SMSAlert implements Alert {
    /**
     * sms客户端
     */
    @Resource
    private SMSClient smsClient;
    /**
     * 设置报警短信
     *
     * @param destination 报警的接收人
     * @param content 报警文本
     * @return 报警信息对象
     */
    @Override
    public SMSAlertVo set(String destination,String content){
        // 发送短信
        SMSAlertVo smsVo = new SMSAlertVo();
        smsVo.setPhoneNumber(destination);// 电话号码
        smsVo.setContent(content);// 短信内容
        return smsVo;
    }

    /**
     * 发送报警短信
     *
     * @param destination 报警的接收人
     * @param content 报警文本
     */
    @Override
    public void send(String destination,String content){
        //ApplicationContext smstest = new ClassPathXmlApplicationContext("spring/spring-sms.xml");
        //SMSClient client = (SMSClient) smstest.getBean("smsClient");
        SMSAlertVo smsVo = set(destination,content);
        //client.send(smsVo);
        smsClient.send(smsVo);
        log.info("send sms:"+destination+" "+content);
    }

    /**
     * 测试发送报警短信
     *
     *
     */
    public static void main(String[] args){
        SMSAlert alert = new SMSAlert();
        alert.send("18821713046","alert");
    }
}