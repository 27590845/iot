package com.xidian.iot.common.alert.sms;

import com.xidian.iot.common.alert.AlertVo;
import lombok.Data;

/**
 * 短信。
 * 
 * @author wmr
 */
@Data
public class SMSAlertVo implements AlertVo {
	/**
	 * 目标手机号码，字符串，如：1386222222、1861等
	 */
	private String phoneNumber;
	/**
	 * 短信内容，字符串，最高200个汉字，有自动分割功能
	 */
	private String content;
}
