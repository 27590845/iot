package com.xidian.iot.common.alert.email;

import com.xidian.iot.common.alert.AlertClient;
import com.xidian.iot.common.alert.AlertVo;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.internet.MimeMessage;
import java.util.List;

/**
 * 邮件客户端
 */
@Slf4j
public class EmailAlertClient implements AlertClient {


	//邮件编码
	@Setter
	private String mailEncoding;
	//邮件发送显示联系人
	@Setter
	private String mailSenderDisplay;
	//邮件发送]
	@Setter
	private JavaMailSender mailSender;


	/**
	 * 发送单个邮件。
	 * 
	 * @param vo 邮件。
	 */
	public boolean send(AlertVo vo) {

		// 转换为邮件
		SimpleEmailAlertVo emailAlertVo = (SimpleEmailAlertVo) vo;

		try {
			// 创建邮件消息体
			MimeMessage msg = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(msg, true, mailEncoding);
			// 设置邮件发送列表
			helper.setTo(emailAlertVo.getEmails());
			// 设置发送现实人
			helper.setFrom(AddressUtil.getFromInternetAddress(mailSenderDisplay));
			// 设置主题
			helper.setSubject(emailAlertVo.getTitle());
			// 设置发送内容
			helper.setText(emailAlertVo.getContent(), true);
			// 发送邮件
			mailSender.send(msg);
			log.info("邮件发送成功 :" + emailAlertVo.getEmails());
		} catch (Exception e) {
			log.error("构造邮件失败", e);
			return false;
		}
		return true;
	}


	/**
	 * 发送多个邮件。
	 * 
	 * @param list  邮件列表。
	 */
	public void send(List<AlertVo> list) {
		for (AlertVo sms : list) {
			send(sms);
		}
	}
}
