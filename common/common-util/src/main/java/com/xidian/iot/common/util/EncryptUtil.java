package com.xidian.iot.common.util;

import com.xidian.iot.common.util.codec.Base64;
import com.xidian.iot.common.util.codec.DES;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

/**
 * 加密工具类，包含Base64、DES、MD5等加密算法。
 * <p>
 * 实现对原数据的加密，包括Base64、DES、MD5等。
 * 
 * @author zhengrunjin
 * @since 0.1
 */
public class EncryptUtil {

	private static Logger log = LoggerFactory.getLogger(com.xidian.iot.common.util.EncryptUtil.class);

	/**
	 * DES加密器。
	 */
	private static DES des = null;

	/**
	 * Base64加密器。
	 */
	private static Base64 base64 = null;

	static {

		// DES加密的16进制密钥。
		String desHexKey = "20d0b90e77842a51";
		try {
			// 初始化DES加密器
			des = new DES(desHexKey);
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			e.printStackTrace();
		}

		// Base64加密器
		base64 = new Base64();
	}

	/**
	 * 将一个字符串按照UTF8编码进行DES加密。
	 * <p>
	 * 
	 * @param plainText
	 *            原文。
	 * @return 密文。
	 */
	public static String encodeDES(String plainText) {
		return encodeDES(plainText, "UTF-8");
	}

	/**
	 * 将一个字符串按照指定编码进行DES加密。
	 * <p>
	 * 
	 * @param plainText
	 *            原文。
	 * @param encoding
	 *            编码。
	 * @return 密文。
	 */
	public static String encodeDES(String plainText, String encoding) {
		if (plainText == null) {
			return null;
		}
		return des.encode(plainText, encoding);
	}

	/**
	 * 将一个字符串以UTF8编码进行DES解密。
	 * 
	 * @param encryptText
	 *            密文。
	 * @return 原文。
	 */
	public static String decodeDES(String encryptText) {
		return decodeDES(encryptText, "UTF-8");
	}

	/**
	 * 将一个字符串以指定编码进行DES解密。
	 * 
	 * @param encryptText
	 *            密文。
	 * @param encoding
	 *            编码。
	 * @return 原文。
	 */
	public static String decodeDES(String encryptText, String encoding) {
		if (encryptText == null) {
			return null;
		}
		return des.decode(encryptText, encoding);
	}

	/**
	 * 将一个字符串以原始编码进行Base64编码。
	 * 
	 * @param plainText
	 *            原文。
	 * @return 密文。
	 */
	public static String encodeBASE64(String plainText) {
		if (plainText == null) {
			return null;
		}
		return base64.encode(plainText);
	}

	/**
	 * 将一个字符串以指定编码进行Base64编码。
	 * 
	 * @param plainText
	 *            原文。
	 * @param encoding
	 *            编码。
	 * @return 密文。
	 */
	public static String encodeBASE64(String plainText, String encoding) {
		if (plainText == null) {
			return null;
		}
		return base64.encode(plainText, encoding);
	}

	/**
	 * 将一个字符串以原始编码进行Base64解码。
	 * 
	 * @param ecryptText
	 *            密文。
	 * @return 原文。
	 */
	public static byte[] decodeBASE64ToBytes(String ecryptText) {
		if (ecryptText == null) {
			return null;
		}
		try {
			return base64.decodeToBytes(ecryptText);
		} catch (UnsupportedEncodingException e) {
			log.error("not found encoding.", e);
		}
		return null;
	}

	/**
	 * 将一个字符串以指定编码进行Base64解码。
	 * 
	 * @param ecryptText
	 *            密文。
	 * @param encoding
	 *            编码。
	 * @return 原文。
	 */
	public static byte[] decodeBASE64ToBytes(String ecryptText, String encoding) {
		if (ecryptText == null) {
			return null;
		}
		try {
			return base64.decodeToBytes(ecryptText, encoding);
		} catch (UnsupportedEncodingException e) {
			log.error("not found encoding.", e);
		}
		return null;
	}

	/**
	 * 将一个字符串以指定编码进行Base64解码。
	 * 
	 * @param ecryptText
	 *            密文。
	 * @return 原文。
	 */
	public static String decodeBASE64(String ecryptText, String encoding) {
		if (ecryptText == null) {
			return null;
		}
		return base64.decode(ecryptText, encoding);
	}

	/**
	 * 将一个字符串以指定编码进行Base64解码。
	 * 
	 * @param ecryptText
	 *            密文。
	 * @return 原文。
	 */
	public static String decodeBASE64(String ecryptText) {
		if (ecryptText == null) {
			return null;
		}
		return base64.decode(ecryptText);
	}

	/**
	 * 获得MD5摘要。
	 * 
	 * @param plainString
	 *            原始字符串。
	 * @return 16位的MD5二进制数组。
	 */
	public static byte[] md5(String plainString) {
		try {
			return md5(plainString, "UTF-8");
		} catch (UnsupportedEncodingException e) {
		}
		return null;
	}

	/**
	 * 获得MD5摘要。
	 * 
	 * @param plainString
	 *            原始字符串。
	 * @param encoding
	 *            编码方式。
	 * @return 16位的MD5二进制数组。
	 * @throws UnsupportedEncodingException
	 *             当编码格式未知时抛出此异常。
	 */
	public static byte[] md5(String plainString, String encoding) throws UnsupportedEncodingException {

		if (plainString == null) {
			return null;
		}

		try {
			// 得到MD5摘要实例
			MessageDigest md = MessageDigest.getInstance("MD5");

			if (encoding != null) {
				// 更新摘要内容
				md.update(plainString.getBytes(encoding));
			} else {
				// 更新摘要内容
				md.update(plainString.getBytes());
			}

			// 获得16位MD5 byte[] 摘要
			return md.digest();

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 获得MD5摘要。
	 * 
	 * @param plainString
	 *            原始字符串。
	 * @return 十六进制MD5字符串。
	 */
	public static String md5Hex(String plainString) {
		return MathUtil.bytesToHex(md5(plainString));
	}

	/**
	 * 获得MD5摘要。
	 * 
	 * @param plainString
	 *            原始字符串。
	 * @param encoding
	 *            编码方式。
	 * @return 十六进制MD5字符串。
	 * @throws UnsupportedEncodingException
	 *             当编码格式未知时抛出此异常。
	 */
	public static String md5Hex(String plainString, String encoding) throws UnsupportedEncodingException {
		return MathUtil.bytesToHex(md5(plainString, encoding));
	}
}
