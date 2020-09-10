package com.xidian.iot.common.alert.email;

import com.xidian.iot.common.alert.AlertVo;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 邮件。
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SimpleEmailAlertVo extends EmailAlertVo implements AlertVo {
	/**
	 * 内容
	 */
	private String content;
}
