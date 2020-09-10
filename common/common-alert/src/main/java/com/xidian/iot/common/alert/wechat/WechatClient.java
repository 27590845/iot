package com.xidian.iot.common.alert.wechat;

import com.xidian.iot.common.alert.AlertClient;
import com.xidian.iot.common.alert.AlertVo;
import com.xidian.iot.common.alert.util.JsonUtil;
import org.apache.log4j.Logger;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class WechatClient implements AlertClient {

	private static Logger log = Logger.getLogger(WechatClient.class);

	/**
	 * 发送post请求，传递json数据
	 * 
	 * @param requestURL
	 * @param json
	 * @return
	 * @throws IOException
	 */
	private String postJson(String requestURL, String json)
			throws IOException {
		URL url = new URL(requestURL);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setDoInput(true);
		connection.setDoOutput(true);
		connection.setRequestMethod("POST");// 设置请求方式为Post
		connection.setUseCaches(false);// 不缓存
		connection.setRequestProperty("Content-Type", "application/json");
		connection.setConnectTimeout(10000);// 如果连接超过20秒,则该方法会抛出异常

		connection.connect();
		OutputStream outputStream = connection.getOutputStream();
		DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
		dataOutputStream.write(json.getBytes());// 写入json
		dataOutputStream.flush();
		dataOutputStream.close();
		outputStream.close();
		InputStream inputStream = connection.getInputStream();
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				inputStream, "UTF-8"));// 读取响应数据
		StringBuffer buffer = new StringBuffer();
		String line = null;
		while ((line = reader.readLine()) != null) {
			buffer.append(line);
		}
		reader.close();
		inputStream.close();
		connection.disconnect();// 关闭连接
		String resultData = buffer.toString();
		return resultData;
	}

	@Override
	public boolean send(AlertVo vo) {
		try {
			if (vo instanceof WechatTextAlertVo) { // 文本消息
				WechatTextAlertVo wechatTextMessageVo = (WechatTextAlertVo) vo;
				log.info("推送微信文本消息：" + wechatTextMessageVo.getContent());
				String result = postJson(
						"https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token="
								+ wechatTextMessageVo.getAccessToken(),
						"{\"touser\":\""
								+ wechatTextMessageVo.getOpenid()
								+ "\",\"msgtype\":\"text\",\"text\":{\"content\":\""
								+ wechatTextMessageVo.getContent() + "\"}}");
				log.info("推送微信文本消息结果：" + result);
			}else if(vo instanceof WechatNewsAlertVo){
				WechatNewsAlertVo wechatNewsMessageVo = (WechatNewsAlertVo) vo;
				log.info("推送微信图文消息...");
				String result = "推送失败，抛异常了...";
				try {
					result = postJson(
							"https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token="
									+ wechatNewsMessageVo.getAccessToken(),
							"{\"touser\":\""
									+ wechatNewsMessageVo.getOpenid()
									+ "\",\"msgtype\":\"news\",\"news\":{\"articles\":"
									+ JsonUtil.toJson(wechatNewsMessageVo.getNews()) + "}}");
				} catch (Exception e) {
					e.printStackTrace();
					return false;
				}
				log.info("推送微信文本消息结果：" + result);
			}
		} catch (IOException e) {
			log.error("推送微信文本消息失败", e);
			return false;
		}
		return true;
	}

	@Override
	public void send(List<AlertVo> list) {
		for (AlertVo alertVo : list) {
			send(alertVo);
		}
	}

	public static void main(String[] args){
		//推送文本消息
//		WechatTextMessageVo wtmv = new WechatTextMessageVo();
//		wtmv.setAccessToken("Xp6sVXuFfqviFyhpMYUB--NmtdwfdG118PL-hn6jANvZaovoS-ssmtP6K6OlAhlLx_nxqjF7QV6aoBrScZEL01MSM9VJ_usSVP_xxCMOJlz6FvhIfV28zQNEJEjMiZRaP4W6QSIg193g6MMufGY9nw");
//		wtmv.setOpenid("oqQ9Kt3Ty0zc5rrdb1kYvs8XgQBA");
//		wtmv.setContent("wechat content");
//		new WechatClient().send(wtmv);
		
		//推送图文消息
		WechatNewsAlertVo wnmv = new WechatNewsAlertVo();
		wnmv.setAccessToken("XbmUuI41OH1Ra4i8bO0M4RbdSLRTeVoSEGBAFBeoHN9RjH53yIh5Zi8wmPTH4ZGlfrcQCWI6wq2fUAl3cRRFNTZWj7Xn1zE7a1A_7XeAhM4N9m6roJlQWKCtmcHdjCpMnqKz2SKDb45qPyuw9FIcvg");
		wnmv.setOpenid("oqQ9Kt3Ty0zc5rrdb1kYvs8XgQBA");
		
		List<NewsAlertItem> news = new ArrayList<NewsAlertItem>();
		news.add(new NewsAlertItem("标题", "描述", "http://www.baidu.com/img/bdlogo.gif", "http://www.baidu.com"));
		
		wnmv.setNews(news);
		new WechatClient().send(wnmv);

	}

}
