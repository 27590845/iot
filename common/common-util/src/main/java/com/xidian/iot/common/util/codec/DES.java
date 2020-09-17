package com.xidian.iot.common.util.codec;

import com.xidian.iot.common.util.MathUtil;

import javax.crypto.*;
import javax.crypto.spec.DESKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

/**
 * DES加密器。
 * 
 * @author <a href="mailto:zhengrj@thingslink.com">郑润锦</a>
 * @since 0.1
 */
public class DES implements StringEncoder, StringDecoder {

	/**
	 * byte密钥。
	 */
	private byte[] rawKeyData;

	/**
	 * 十六进制密钥。
	 */
	private String hexKey;

	/**
	 * 加密编译器。
	 */
	private Cipher encryptCipher;
	/**
	 * 解密编译器。
	 */
	private Cipher decryptCipher;

	/**
	 * 构造一个DES加密器，通过一串十六进制的字符串密钥产生加密器。
	 * 
	 * @param hexKey
	 *            十六进制密钥。
	 * @throws InvalidKeySpecException
	 * @throws NoSuchPaddingException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeyException
	 */
	public DES(String hexKey) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException {
		this.hexKey = hexKey;
		this.rawKeyData = MathUtil.hexToBytes(hexKey);
		initKey();
	}

	/**
	 * 构造一个DES加密器，通过byte数组产生加密器。
	 * 
	 * @throws InvalidKeySpecException
	 * @throws NoSuchPaddingException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeyException
	 */
	public DES(byte[] rawKeyData) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException {
		this.rawKeyData = rawKeyData;
		initKey();
	}

	/**
	 * 初始化密钥。
	 * 
	 * @throws InvalidKeyException
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchPaddingException
	 * @throws InvalidKeySpecException
	 */
	private void initKey() throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException {
		// 从原始密匙数据创建一个DESKeySpec对象
		DESKeySpec dks = new DESKeySpec(rawKeyData);
		// 创建一个密匙工厂，然后用它把DESKeySpec转换成一个SecretKey对象
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		SecretKey key = keyFactory.generateSecret(dks);
		// 加密编译器
		encryptCipher = Cipher.getInstance("DES");
		encryptCipher.init(Cipher.ENCRYPT_MODE, key);
		// 解密编译器
		decryptCipher = Cipher.getInstance("DES");
		decryptCipher.init(Cipher.DECRYPT_MODE, key);
	}

	/**
	 * 获得密钥的byte数组。
	 * 
	 * @return byte数组密钥。
	 */
	public byte[] getRawKeyData() {
		return rawKeyData;
	}

	/**
	 * 获得十六进制密钥。
	 * <p>
	 * 此方法获得十六进制密钥，但如果没有使用十六进制的初始化方法，此方法将返回null。
	 * 
	 * @return 十六进制密钥,如果未用十六进制方法进行初始化将返回null。
	 */
	public String getHexKey() {
		return hexKey;
	}

	/**
	 * 字符串解密，根据密钥解密并将原文返回。
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
	 * 字符串解密，根据密钥解密并将原文返回。
	 * <p>
	 * 此方法会根据指定的原文编码形式对原文进行编码指定。
	 * 
	 * @param string
	 *            密文。
	 * @param encoding
	 *            原文编码形式。
	 * @return 原文。
	 */
	@Override
	public String decode(String string, String encoding) {
		try {
			// 转化为16进制
			byte[] encryptData = MathUtil.hexToBytes(string);
			// 解密
			byte[] decryptData = decryptCipher.doFinal(encryptData);
			// 组装为原文
			if (encoding == null) {
				return new String(decryptData);
			} else {
				return new String(decryptData, encoding);
			}
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 字符串加密，根据密钥加密并将密文返回。
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
	 * 字符串加密，根据密钥加密并将密文返回。
	 * <p>
	 * 此方法会根据指定的编码形式，对获取原文byte数组时进行指定。
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
			// 根据指定编码获得原文byte数组，如果为空才去默认编码
			byte[] planData = null;
			if (encoding == null) {
				planData = string.getBytes();
			} else {
				planData = string.getBytes(encoding);
			}
			// 对原文进行加密
			encryptData = encryptCipher.doFinal(planData);
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		// 将密文byte数组转化为十六进制表示
		return MathUtil.bytesToHex(encryptData);
	}
}
