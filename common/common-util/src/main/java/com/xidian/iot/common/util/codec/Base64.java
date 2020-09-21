package com.xidian.iot.common.util.codec;

import java.io.UnsupportedEncodingException;

/**
 * Base64编码器。
 * 
 * @author <a href="mailto:zhengrj@thingslink.com">郑润锦</a>
 * @since 0.1
 */
public class Base64 implements StringEncoder, StringDecoder {

	/**
	 * 构造一个Base64编码器。
	 */
	public Base64() {
	}

	/**
	 * 以Base64形式解码一段字符串，并将解码后的字符串返回。
	 * 
	 * @param string
	 *            密文。
	 * @return 原文。
	 */
	@Override
	public String decode(String string) {
		return decode(string, null);
	}

	/**
	 * 以Base64形式解码一段字符串，通过指定的编码形式指定解码后的字符串编码形式。
	 * 
	 * @param string
	 *            密文。
	 * @param encoding
	 *            编码形式。
	 * @return 原文。
	 */
	@Override
	public String decode(String string, String encoding) {
		try {
			// 返回数据
			if (encoding == null) {
				return new String(decodeToBytes(string, null));
			} else {
				return new String(decodeToBytes(string, encoding));
			}
		} catch (UnsupportedEncodingException e) {
		}
		return null;
	}

	/**
	 * 以Base64形式解码一段字符串，通过默认的编码形式解密成二进制。
	 * 
	 * @param str
	 *            密文。
	 * @return 原文二进制。
	 */
	public byte[] decodeToBytes(String str) throws UnsupportedEncodingException {
		return decodeToBytes(str, null);
	}

	/**
	 * 以Base64形式解码一段字符串，通过指定的编码形式解密成二进制。
	 * 
	 * @param str
	 *            密文。
	 * @param encoding
	 *            编码形式。
	 * @return 原文二进制。
	 */
	public byte[] decodeToBytes(String str, String encoding) throws UnsupportedEncodingException {

		// 获得密文的二进制数据
		byte[] encryptData = null;
		if (encoding == null) {
			encryptData = str.getBytes();
		} else {
			encryptData = str.getBytes(encoding);
		}
		// 以base64解密
		return org.apache.commons.codec.binary.Base64.decodeBase64(encryptData);
	}

	/**
	 * 以Base64形式编码一段字符串，并将编码后的字符串返回。
	 * 
	 * @param string
	 *            原文。
	 * @return 密文。
	 */
	@Override
	public String encode(String string) {
		return encode(string, null);
	}

	/**
	 * 以Base64形式编码一段字符串，通过指定的编码形式读取原文后再进行Base64编码。
	 * 
	 * @param string
	 *            原文。
	 * @param encoding
	 *            原文编码形式。
	 * @return 密文。
	 */
	@Override
	public String encode(String string, String encoding) {

		byte[] encryptData = null;
		try {
			// 根据原文编码形式获得二进制数据，如果为null则采用默认编码
			byte[] binaryData = null;
			if (encoding == null) {
				binaryData = string.getBytes();
			} else {
				binaryData = string.getBytes(encoding);
			}
			// 通过base64编码
			encryptData = org.apache.commons.codec.binary.Base64.encodeBase64(binaryData);

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		// 返回密文
		return new String(encryptData);
	}

}
