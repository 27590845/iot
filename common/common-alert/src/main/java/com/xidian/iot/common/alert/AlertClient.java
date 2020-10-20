package com.xidian.iot.common.alert;

import java.util.List;

/**
 * 消息客户端接口
 * 
 * @author starcloud
 * 
 */
public interface AlertClient {

	/**
	 * 发送一条消息。
	 * 
	 * @param vo
	 *            消息对象
	 */
	boolean send(AlertVo vo);

	/**
	 * 发送一组消息。
	 * 
	 * @param list
	 *            消息对象列表
	 */
	void send(List<AlertVo> list);
}
