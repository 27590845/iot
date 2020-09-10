package com.xidian.iot.common.alert.wechat;

import com.xidian.iot.common.alert.AlertVo;
import lombok.Data;

import java.util.List;

/**
 * 推送微信图文消息
 * 
 * @author sujinbo
 */
@Data
public class WechatNewsAlertVo implements AlertVo {
	
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
	private List<NewsAlertItem> news;
	
}
