package com.xidian.iot.common.util;

import org.apache.commons.lang3.RandomUtils;

import java.text.DecimalFormat;
import java.util.Random;

/**
 * 随机工具类，提供数字随机、数字范围随机、字符串随机等方法。
 * 包括了随机数，随机串等工具。
 * @since 0.1
 */
public class RandomUtil {

	/**
	 * 所有字母
	 */
	public static final String ALL_LETTER = "abcdefghjiklmnopqrstuvwxyz";
	/**
	 * 所有数字
	 */
	public static final String ALL_NUMBER = "0123456789";
	/**
	 * 所有字母与数字
	 */
	public static final String ALL_LETTER_NUMBER = "abcdefghjiklmnopqrstuvwxyz0123456789";

	/**
	 * 获得一个随机数值，此数值大于等于零
	 * @return 返回得到的随机数值。
	 */
	public static int nextInt() {
		return RandomUtils.nextInt();
	}

	/**
	 * 获得一个随机数值，此数值介于零到最大数值
	 * @param max 最大数值。
	 * @return 返回得到的随机数值。
	 */
	public static int nextInt(int max) {
		return nextInt(0, max);
	}

	/**
	 * 获得一个随机数值，此数值介于最小数值和最大数值
	 * @param min 最小数值
	 * @param max 最大数值
	 * @return 返回得到的随机数值。
	 */
	public static int nextInt(int min, int max) {
		if (min > max) {
			throw new IllegalArgumentException("Prameter 'min' can not > 'max'.");
		}
		//次运算为的是包含max最大数值的范围，例如5-10，即出现5也出现10，若不执行此操作则，随机数总在10以下不包括10出现
		max++;
		return (int) (Math.random() * (max - min) + min);
	}

	/**
	 * 获得一个随机数值，此数值大于等于零
	 * @return 返回得到的随机数值。
	 */
	public static long nextLong() {
		return RandomUtils.nextLong();
	}

	/**
	 * 获得一个随机数值，此数值介于零到最大数值
	 * @param max 最大数值
	 * @return 返回得到的随机数值。
	 */
	public static long nextLong(long max) {
		return nextLong(0, max);
	}

	/**
	 * 获得一个随机数值，此数值介于最小数值到最大数值
	 * @param min 最小数值。
	 * @param max 最大数值。
	 * @return 返回得到的随机数值。
	 */
	public static long nextLong(long min, long max) {
		if (min > max) {
			throw new IllegalArgumentException("Prameter 'min' can not > 'max'.");
		}
		max++;// 次运算为的是包含max最大数值的范围，例如5-10，即出现5也出现10，若不执行此操作则，随机数总在10以下不包括10出现
		return (long) (Math.random() * (max - min) + min);
	}

	public static double nextDouble(double min, double max){
		if (min > max) {
			throw new IllegalArgumentException("Prameter 'min' can not > 'max'.");
		}
		DecimalFormat df = new DecimalFormat("#.00");
		return  Double.parseDouble(df.format(min + ((max - min) * new  Random().nextDouble())));
	}

	/**
	 * 获得一个指定长度的随机字符串
	 * 采用{{@link #ALL_LETTER}作为随机字符串的字符取值范围
	 * @param length  字符串长度。
	 * @return 返回得到的随机字符串。
	 */
	public static String nextString(int length) {
		return nextString(length, ALL_LETTER);
	}

	/**
	 * 获得一个指定长度的随机字符串。
	 * 可通过指定chars决定随机字符串的字符取值范围。
	 * @param length 字符串长度，长度若小于0则返回""。
	 * @param chars  随机字符取值范围。
	 * @return 返回得到的随机字符串，当chars为null或""是，返回""。
	 */
	public static String nextString(int length, String chars) {

		if (length <= 0) {
			return StringUtil.EMPTY;
		}

		if (StringUtil.isEmpty(chars)) {
			return StringUtil.EMPTY;
		}

		int indexMax = chars.length() - 1;
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < length; i++) {
			sb.append(chars.charAt(nextInt(0, indexMax)));
		}

		return sb.toString();
	}

	/**
	 * 获得指定风格的随机串
	 *
	 * @param length 随机串长度
	 * @param seed 产生的种子
	 * @return 随机串
	 */
    public static String nextString(int length,long... seed) {
        Random rand = null; // 设置随机种子
        if(seed.length>0){
            rand = new Random(seed[0]);
        } else {
            rand = new Random();
        }

        int size = ALL_LETTER_NUMBER.length();
        StringBuffer str = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int start = rand.nextInt(size);
            str.append(ALL_LETTER_NUMBER.charAt(start));
        }

        return str.toString();
    }

	/**
	 * 生成验证码
	 *
	 * @param id 待生成用户的唯一标识
	 * @param minutes 有效时间，输入几分钟
	 * @return 6位数字验证码
	 */
	public static String getSignature(String id, int minutes){
        long seed = 0;
        long minuteSeed = System.currentTimeMillis() / (1000 * 60 * minutes);
        try {
            seed = tran(id);
        } catch (Exception e) {
        	seed = 90;
        }
        String code = RandomUtil.nextString(6, seed + minuteSeed);

        return code;
	}

	/**
	 * 生成验证码
	 * @param id 待生成用户的唯一标识
	 * @param minutes 有效时间，输入几分钟
	 * @return 6位数字验证码
	 */
	public static String getSignature(String id, int minutes,String rand){
        long seed = 0;
        long minuteSeed = System.currentTimeMillis() / (1000 * 60 * minutes);
        try {
            seed = tran(id);
        } catch (Exception e) {
        	seed = 90;
        }
        String code = RandomUtil.nextString(6, seed + minuteSeed);

        return EncryptUtil.md5Hex(code + rand);
	}

	/**
	 * 生成验证码
	 * @param id 待生成用户的唯一标识
	 * @param rand 随机码
	 * @return 6位数字验证码
	 */
	public static String getSignature(String id, String rand){
		return getSignature(id, 5, rand);
	}

	/**
	 * 验证验证码是否正确
	 * @param id 待生成用户的唯一标识
	 * @param minutes 有效时间，输入几分钟
	 * @param sig 验证码
	 * @return 成功，失败
	 */
	public static boolean verity(String id, int minutes,String sig){
		if(StringUtil.isEmpty(sig)){
			return false;
		}

		long seed = 0;
        long minuteSeed = System.currentTimeMillis() / (1000 * 60 * minutes);
        try {
            seed = tran(id);
        } catch (Exception e) {
        	seed = 90;
        }
        String gencode = RandomUtil.nextString(6, seed + minuteSeed);
        return gencode.equals(sig)?true: RandomUtil.nextString(6, seed + minuteSeed -1).equals(sig);
	}

	/**
	 * 验证验证码是否正确
	 * @param id 待生成用户的唯一标识
	 * @param minutes 有效时间，输入几分钟
	 * @param sig 验证码
	 * @return 成功，失败
	 */
	public static boolean verityStick(String id, int minutes,String sig){
		if(StringUtil.isEmpty(sig)){
			return false;
		}

		long seed = 0;
        long minuteSeed = System.currentTimeMillis() / (1000 * 60 * minutes);
        try {
            seed = tran(id);
        } catch (Exception e) {
        	seed = 90;
        }
        String gencode = RandomUtil.nextString(6, seed + minuteSeed);
        return gencode.equals(sig);
	}

	/**
	 * 验证验证码是否正确
	 * @param id 待生成用户的唯一标识
	 * @param minutes 有效时间，输入几分钟
	 * @param sig 验证码
	 * @param rand 随机码
	 * @return 成功，失败
	 */
	public static boolean verity(String id, int minutes,String sig, String rand){
		if(StringUtil.isEmpty(sig) || StringUtil.isEmpty(rand)){
			return false;
		}
        long seed = 0;
        long minuteSeed = System.currentTimeMillis() / (1000 * 60 * minutes);
        try {
            seed = tran(id);
        } catch (Exception e) {
        	seed = 90;
        }
        String gencode = RandomUtil.nextString(6, seed + minuteSeed);
        String realcode = EncryptUtil.md5Hex(gencode + rand);

        if(realcode.equals(sig)) {
        	return true;
        } else {
        	gencode = RandomUtil.nextString(6, seed + minuteSeed - 1);
        	realcode = EncryptUtil.md5Hex(gencode + rand);
        	return realcode.equals(sig);
        }
	}

	/**
	 * 验证验证码是否正确
	 * @param id 待验证用户的ID，或者手机号
	 * @param sig 验证码
	 * @param rand 随机码
	 * @return 成功，失败
	 */
	public static boolean verity(String id, String sig, String rand){
		return verity(id, 5, sig, rand);
	}

	/**
	 * 将字符串转换成数字
	 *
	 * @param str
	 * @return
	 */
	private static long tran(String str) {
		long num = 0 ;
		for (int i = 0; i < str.length() - 1; i++) {
			num += str.charAt(i) ;
		}
		return num;
	}

    /**
     * 普通正态随机分布
     * @param u 均值
     * @param v 方差
     * @return
     */
	public static double NormalDistribution(double u,float v){
		java.util.Random random = new java.util.Random();
		return Math.sqrt(v)*random.nextGaussian()+u;
	}


}
