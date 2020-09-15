package com.xidian.iot.common.util.codec;

/**
 * 加密一段字符串。
 * 
 * @author <a href="mailto:zhengrunjin@specl.com">郑润锦</a><a target="_blank" href="http://wpa.qq.com/msgrd?v=3&uin=254917726&site=qq&menu=yes"><img border="0" src="http://wpa.qq.com/pa?p=2:254917726:47" alt="郑润锦" title="郑润锦"></a>
 * @since java 1.6
 * @since commons v0.3
 */
public interface StringEncoder {

	/**
	 * 加密一段字符串原文并将密文返回。
	 * @param string 原文。
	 * @return 密文。
	 */
	public String encode(String string);
	
	/**
	 * 加密一段字符串原文并将密文返回，并指定读取原文的编码格式。
	 * @param string 原文。
	 * @param encoding 原文编码格式。
	 * @return 密文。
	 */
	public String encode(String string, String encoding);
	
}
