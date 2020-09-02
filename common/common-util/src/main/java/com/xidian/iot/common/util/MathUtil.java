package com.xidian.iot.common.util;

/**
 * 数学工具类,提供数值进制互转、字节进制互转等数学操作。
 * <p>
 * 此类是进制工具集合，包括关于数值的进制转化方法。
 * 
 * @author zhengrunjin
 * @since 0.1
 */
public class MathUtil {

	/**
	 * 16进制字符数组
	 */
	private final static char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

	/**
	 * 计算和。
	 * 
	 * @param nums
	 *            数值数组。
	 * @return 和。
	 */
	public static int sum(int[] nums) {
		int totalNum = 0;
		for (int num : nums) {
			totalNum += num;
		}
		return totalNum;
	}

	/**
	 * 计算和。
	 * 
	 * @param nums
	 *            数值数组。
	 * @return 和。
	 */
	public static double sum(double[] nums) {
		int totalNum = 0;
		for (double num : nums) {
			totalNum += num;
		}
		return totalNum;
	}

	/**
	 * 计算和。
	 * 
	 * @param nums
	 *            数值数组。
	 * @return 和。
	 */
	public static float sum(float[] nums) {
		int totalNum = 0;
		for (float num : nums) {
			totalNum += num;
		}
		return totalNum;
	}

	/**
	 * 计算平均值。
	 * <p>
	 * 
	 * @param nums
	 *            数值数组。
	 * @return 平均值。
	 */
	public static int avg(int[] nums) {
		return sum(nums) / nums.length;
	}

	/**
	 * 计算平均值。
	 * <p>
	 * 
	 * @param nums
	 *            数值数组。
	 * @return 平均值。
	 */
	public static float avg(float[] nums) {
		return sum(nums) / nums.length;
	}

	/**
	 * 计算平均值。
	 * <p>
	 * 
	 * @param nums
	 *            数值数组。
	 * @return 平均值。
	 */
	public static double avg(double[] nums) {
		return sum(nums) / nums.length;
	}

	/**
	 * 将字节数组转十六进制字符串。
	 * <p>
	 * 由于一个字节代表8位，而十六进制则用4位表示，所以把低4位和高4位分别用两个16进制字符表示。最后把字符串组成字符串返回。
	 * 
	 * @param byteArray
	 *            字节数组。
	 * @return 十六进制字符串。
	 */
	public static String bytesToHex(byte[] byteArray) {

		if (byteArray == null) {
			return null;
		}

		int j = byteArray.length;
		char chars[] = new char[j * 2];
		int k = 0;
		for (int i = 0; i < j; i++) {
			byte byte0 = byteArray[i];
			chars[k++] = hexDigits[byte0 >>> 4 & 0xf];
			chars[k++] = hexDigits[byte0 & 0xf];

		}

		return new String(chars);
	}

	/**
	 * 字节数组到整数的转换。
	 * <p>
	 * 
	 * @param bytes
	 *            字节数组。
	 * @return 整型。<br/>
	 *         当数组长度不为4返回0.<br/>
	 *         当数组为null返回0.
	 */
	public static int bytesToInt(byte bytes[]) {
		int s = 0;
		if (bytes == null) {
			return 0;
		}
		if (bytes.length != 4) {
			return 0;
		}
		s = ((((bytes[0] & 0xff) << 8 | (bytes[1] & 0xff)) << 8) | (bytes[2] & 0xff)) << 8 | (bytes[3] & 0xff);
		return s;
	}

	/**
	 * 整数到字节数组转换。
	 * <p>
	 * 
	 * @param num
	 *            整数。
	 * @return 字节数组。
	 */
	public static byte[] intToBytes(int num) {
		byte[] ab = new byte[4];
		ab[3] = (byte) (0xff & num);
		ab[2] = (byte) ((0xff00 & num) >> 8);
		ab[1] = (byte) ((0xff0000 & num) >> 16);
		ab[0] = (byte) ((0xff000000 & num) >> 24);
		return ab;
	}

	/**
	 * 十进制转十六进制。
	 * 
	 * @param num
	 *            十进制整型。
	 * @return 十六进制字符串。
	 */
	public static String intToHex(int num) {
		return bytesToHex(intToBytes(num));
	}

	/**
	 * 十六进制转字节数组。
	 * 
	 * @param hexString
	 *            16进制字符串。
	 * @return 字节数组。
	 */
	public static byte[] hexToBytes(String hexString) {
		byte[] ret = new byte[hexString.length() / 2];
		for (int i = 0; i < ret.length; i++) {
			ret[i] = Integer.decode("#" + hexString.substring(2 * i, 2 * i + 2)).byteValue();
		}
		return ret;
	}

	/**
	 * 十六进制转十进制。
	 * 
	 * @param hexString
	 *            十六进制字符串。
	 * @return 十进制数。<br/>
	 */
	public static int hexToInt(String hexString) {
		if (com.xidian.iot.common.util.StringUtil.isBlank(hexString)) {
			throw new IllegalArgumentException("Parameter 'hexString' can not null.");
		}
		return Integer.valueOf(hexString, 16);
	}

	public static void main(String[] args) {
		// System.out.println(Integer.decode("#0f"));
		// System.out.println(Integer.valueOf("", 16));
	}
}
