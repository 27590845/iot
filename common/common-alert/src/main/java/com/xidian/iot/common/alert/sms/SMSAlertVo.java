package com.xidian.iot.common.alert.sms;

import com.xidian.iot.common.alert.AlertVo;
import lombok.Data;

import java.util.Date;

/**
 * 短信。
 * 
 * @author zhengrunjin
 */
@Data
public class SMSAlertVo implements AlertVo {
	/**
	 * 发信人
	 */
	private String userName;
	/**
	 * 目标手机号码，字符串，如：1386222222、1861等
	 */
	private String phoneNumber;
	/**
	 * 短信内容，字符串，最高200个汉字，有自动分割功能
	 */
	private String content;
	/**
	 * 计划发送的时间，日期型，如：2005-8-19 21:31:11
	 */
	private Date sendTime;
	/**
	 * 是否需发送报告，数值型，Report=1时需报告，Report=0时不需报告
	 */
	private int report;
	/**
	 * 端口号，数值型，设定发送本条信息所使用的端口号，为0（或不填）时为自动端口号
	 */
	private int comPort = 11;
}
