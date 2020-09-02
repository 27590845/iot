/**
 * 51diancai.com Inc.
 * Copyright (c) 2011-2012 All Rights Reserved.
 */
package com.xidian.iot.common.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 移动电话工具类，提供对移动电话号码的合法性检查、判定隶属于的移动电话公司等方法。
 * <p>
 * 对移动电话提供的一些工具。
 * 
 * @author zhengrunjin
 * @since 0.1
 */
public class MobileUtil {

	/**
	 * 判定是否为合法的移动电话号码。
	 * <p>
	 * 中国移动：134、135、136、137、138、139、147,150、151、152,157(TD)、158、159、182,183,187、
	 * 188<br/>
	 * 中国联通：130、131、132、155、156、185、186<br/>
	 * 中国电信：133、153、180、189、（1349卫通）
	 * 
	 * @param mobile
	 *            需要判定的移动电话号码
	 * @return true 合法号码 <br/>
	 *         false 不合法号码
	 */
	public static boolean isMobile(String mobile) {
		Pattern p = Pattern.compile("^0?[1][3458][0-9]{9}$");
		Matcher m = p.matcher(mobile);
		return m.matches();
	}

}
