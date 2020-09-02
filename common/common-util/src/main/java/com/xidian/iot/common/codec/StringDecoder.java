package com.xidian.iot.common.codec;

/**
 * 解密一段字符串。
 * 
 * @author <a href="mailto:zhengrunjin@specl.com">郑润锦</a><a target="_blank" href="http://wpa.qq.com/msgrd?v=3&uin=254917726&site=qq&menu=yes"><img border="0" src="http://wpa.qq.com/pa?p=2:254917726:47" alt="郑润锦" title="郑润锦"></a>
 * @since java 1.6
 * @since commons v0.3
 */
public interface StringDecoder {

	/**
	 * 解密一段字符串密文并将原文返回。
	 * @param string 密文。
	 * @return 原文。
	 */
	public String decode(String string);
	
	/**
	 * 解密一段字符串密文并将原文返回，并为原文指定编码形式。
	 * @param string 密文。
	 * @param encoding 原文编码形式。
	 * @return 原文。
	 */
	public String decode(String string, String encoding);
	
}
