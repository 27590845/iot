/*
 * Timeloit.com Inc.
 * Copyright (c) 2012 时代凌宇物联网数据平台. All Rights Reserved
 */
package com.xidian.iot.common.alert.site;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 站内信
 * 
 * @author zhengrunjin
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SiteLetter implements java.io.Serializable {

	private static final long serialVersionUID = -1268378342580503257L;

	/**
	 * 站内信ID
	 */
	private Long slId;
	/**
	 * 触发器ID
	 */
	private Long ntId;
	/**
	 * 用户ID
	 */
	private Long userId;
	/**
	 * 节点ID
	 */
	private Long nodeId;
	/**
	 * 节点名称
	 */
	private String nodeName;
	/**
	 * 场景ID
	 */
	private Long sceneId;
	/**
	 * 场景标题
	 */
	private String sceneTitle;
	/**
	 * 场景序列号
	 */
	private String sceneSn;
	/**
	 * 是否已读
	 */
	private Short isRead;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 触发类型
	 */
	private Short actionType;
	/**
	 * 报警内容
	 */
	private String alertContent;
	/**
	 * 报警值
	 */
	private String alertValue;
	/**
	 * 当前值
	 */
	private String currentValue;
	/**
	 * 发送人ID
	 */
	private String senderId;
	/**
	 * 发送人
	 */
	private String sender;
	/**
	 * 主题
	 */
	private String subject;
	/**
	 * 内容
	 */
	private String content;
	
	/**
	 *上一天站内信id 
	 */
	private Long prevId;
	/**
     *下一天站内信id 
     */
	private Long nextId;
	
}
