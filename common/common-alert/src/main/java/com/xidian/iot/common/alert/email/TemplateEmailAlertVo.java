package com.xidian.iot.common.alert.email;

import com.xidian.iot.common.alert.AlertVo;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Map;

/**
 * 邮件
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TemplateEmailAlertVo extends EmailAlertVo implements AlertVo {
	//模板名
	private String templateName;
	//模板参数
	private Map<String, Object> templateParams;
}
