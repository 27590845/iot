package com.xidian.iot.common.alert.email;

import com.xidian.iot.common.alert.AlertVo;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * 邮件 
 */
public abstract class EmailAlertVo implements AlertVo {
	//收件人地址
	private List<String> emails = new ArrayList<>();
	//标题
	@Getter
	@Setter
	private String title;

	/**
	 * 添加收件人
	 * @param email 收件人地址
	 */
	public void addEmail(String... email) {
		for (String e : email) {
			emails.add(e);
		}
	}

	/**
	 * 获得收件人地址
	 * 
	 * @return 收件人地址数组
	 */
	public String[] getEmails() {
		return emails.toArray(new String[] {});
	}
}
