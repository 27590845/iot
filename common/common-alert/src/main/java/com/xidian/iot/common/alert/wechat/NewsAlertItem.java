package com.xidian.iot.common.alert.wechat;

import lombok.Data;

@Data
public class NewsAlertItem {

	private String title;
	private String description;
	private String picurl;
	private String url;
	
	public NewsAlertItem(String title, String description, String picUrl,
						 String url) {
		super();
		this.title = title;
		this.description = description;
		this.picurl = picUrl;
		this.url = url;
	}
}
