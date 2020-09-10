package com.xidian.iot.common.alert.sms;


import com.xidian.iot.common.alert.AlertClient;
import com.xidian.iot.common.alert.AlertVo;
import lombok.Setter;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * 短信客户端。此类需要注入短信数据源。
 * 
 * @author zhengrunjin
 */
public class SMSClient implements AlertClient {

	/**
	 * 短信数据源
	 */
	@Setter
	private DataSource dataSource;

	/**
	 * 发送一条短信。
	 * 
	 * @param vo
	 *            短信。
	 */
	public boolean send(AlertVo vo) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			SMSAlertVo messageVo = (SMSAlertVo) vo;
			if (null != messageVo.getPhoneNumber() || null != messageVo.getContent()) {
				String sql = "INSERT INTO OutBox(username,Mbno,Msg)VALUES(?,?,?)";
				String sqlt = "INSERT INTO OutBox(username,Mbno,Msg,SendTime)VALUES(?,?,?,?)";
				connection = getconn();
				preparedStatement = connection.prepareStatement(null == messageVo.getSendTime() ? sql : sqlt);
				preparedStatement.setString(1, messageVo.getUserName());
				preparedStatement.setString(2, messageVo.getPhoneNumber());
				preparedStatement.setString(3, messageVo.getContent());
				if (null != messageVo.getSendTime()) {
					preparedStatement.setDate(4, (Date) messageVo.getSendTime());
				}
				preparedStatement.execute();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return true;
	}

	/**
	 * 发送多条短信。
	 * 
	 * @param list
	 *            短信列表。
	 */
	public void send(List<AlertVo> list) {
		if (null != list && list.size() > 0) {
			for (AlertVo alertVo : list) {
				send(alertVo);
			}
		}
	}

	/**
	 * 获得连接。
	 * 
	 * @return 返回一个JDBC连接。
	 * @throws SQLException
	 *             获得连接异常。
	 */
	private Connection getconn() throws SQLException {
		return dataSource.getConnection();
	}

	public static void main(String[] args) {
		SMSAlertVo vo = new SMSAlertVo();
		vo.setUserName("tester");
		vo.setPhoneNumber("13910612064");
		vo.setContent("this is a test message");
		new SMSClient().send(vo);
	}
}
