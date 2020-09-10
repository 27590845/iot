package com.xidian.iot.common.alert.wechat;

import com.xidian.iot.common.alert.AlertVo;
import lombok.Data;

/**
 * 推送微信文本消息
 * 
 * @author sujinbo
 */
@Data
public class WechatTextAlertVo implements AlertVo {
	
	/**
	 * 公众账号accesstoken
	 */
	private String accessToken;
	
	/**
	 * 微信用户openid
	 */
	private String openid;
	
	/**
	 * 推送消息内容
	 */
	private String content;
}
