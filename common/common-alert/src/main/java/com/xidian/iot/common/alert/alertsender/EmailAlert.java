package com.xidian.iot.common.alert.alertsender;

import com.xidian.iot.common.alert.email.EmailAlertClient;
import com.xidian.iot.common.alert.email.SimpleEmailAlertVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 邮件报警对象
 * @author wmr
 *
 */
@Slf4j
@Component
public class EmailAlert implements Alert {
    // 邮件警报标题
    private String nodeAlertTitle = "报警信息";
    /**
     * e-mail客户端
     */
    @Resource
    private EmailAlertClient emailAlertClient;
    /**
     * 设置报警邮件
     *
     * @param destination 报警的接收人
     * @param content 报警文本
     * @return 报警邮件对象
     */
    @Override
    public SimpleEmailAlertVo set(String destination,String content){
        SimpleEmailAlertVo emailVo = new SimpleEmailAlertVo();
        emailVo.addEmail(destination);// 邮件地址
        emailVo.setTitle(nodeAlertTitle);// 邮件标题
        emailVo.setContent(content);// 邮件内容
        return emailVo;
    }

    /**
     * 发送报警邮件
     *
     * @param destination 报警的接收人
     * @param content 报警文本
     */
    @Override
    public void send(String destination,String content){
//        ApplicationContext etest = new ClassPathXmlApplicationContext("spring/application-alert.xml");
//        EmailAlertClient client = (EmailAlertClient) etest.getBean("emailAlertClient");
        SimpleEmailAlertVo emailVo = set(destination,content);
        emailAlertClient.send(emailVo);
//        client.send(emailVo);
        log.info("send email:"+destination+" "+content);
    }

    /**
     * 测试发送报警邮件
     *
     *
     */
    public static void main(String[] args){
        EmailAlert alert = new EmailAlert();
        alert.send("654353294@qq.com","alert!");
    }
}