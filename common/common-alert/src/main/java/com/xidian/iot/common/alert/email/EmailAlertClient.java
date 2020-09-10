package com.xidian.iot.common.alert.email;

import com.xidian.iot.common.alert.AlertClient;
import com.xidian.iot.common.alert.AlertVo;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.Setter;
import org.apache.log4j.Logger;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactoryBean;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.List;

/**
 * 邮件客户端
 */
public class EmailAlertClient implements AlertClient {

	private static Logger log = Logger.getLogger(EmailAlertClient.class);

	//模板位置
	@Setter
	private String templatePath;
	//模板编码
	@Setter
	private String templateEncoding;
	//邮件编码
	@Setter
	private String mailEncoding;
	//邮件发送显示联系人
	@Setter
	private String mailSenderDisplay;
	//邮件发送]
	@Setter
	private JavaMailSender mailSender;

	//freemarker模板配置
	private Configuration config = null;

	/**
	 * 初始化方法
	 */
	public void init() {
		// 实例化模板配置
		FreeMarkerConfigurationFactoryBean bean = new FreeMarkerConfigurationFactoryBean();
		bean.setTemplateLoaderPath(templatePath);
		try {
			config = bean.createConfiguration();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TemplateException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 发送单个邮件。
	 * 
	 * @param vo 邮件。
	 */
	public boolean send(AlertVo vo) {

		// 转换为抽象邮件
		EmailAlertVo emailAlertVo = (EmailAlertVo) vo;

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
			helper.setText(getContent(vo), true);
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
	 * 获得邮件内容
	 * 
	 * @param vo  邮件
	 * @return 邮件内容
	 */
	private String getContent(AlertVo vo) {

		if (vo instanceof SimpleEmailAlertVo) {
			// 简单邮件
			SimpleEmailAlertVo simpleVo = (SimpleEmailAlertVo) vo;
			return simpleVo.getContent();
		} else if (vo instanceof TemplateEmailAlertVo) {
			// 模板邮件
			TemplateEmailAlertVo templateVo = (TemplateEmailAlertVo) vo;
			try {
				// 获取模板
				Template template = config.getTemplate(templateVo.getTemplateName(), templateEncoding);
				// 编译模板内容
				return FreeMarkerTemplateUtils.processTemplateIntoString(template, templateVo.getTemplateParams());
			} catch (IOException e) {
				log.error("read template error.", e);
			} catch (TemplateException e) {
				log.error("read template error.", e);
			}
		}
		return null;
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
