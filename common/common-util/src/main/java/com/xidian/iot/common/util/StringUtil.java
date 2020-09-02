package com.xidian.iot.common.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.regex.Pattern;

/**
 * 此类提供了关于String类型的一些常用方法，例如字符串为空判断、是否是数字判断、字符串连接、字符串分割等。
 * <p>
 * 
 * @author zhengrunjin
 * @since 0.1
 */
public class StringUtil {

	/**
	 * 默认分隔符
	 */
	public static final String DEFAULT_SEPARATOR = ",";
	/**
	 * 空字符串
	 */
	public static final String EMPTY = "";

	/**
	 * 将一个对象转成字符串。
	 * <p>
	 * 当对象为null则返回{@link com.xidian.iot.common.util.StringUtil#EMPTY}
	 * ，不为null，如果时String则强制转化，如果是非String对象，调用toString()方法。
	 * 
	 * <pre>
	 * StringUtil.toString(null); // return &quot;&quot;;
	 * StringUtil.toString(new String(&quot;123&quot;)); // return &quot;123&quot;;
	 * StringUtil.toString(new Integer(1)); // return &quot;1&quot;;
	 * </pre>
	 * 
	 * @param obj
	 *            对象。
	 * @return 字符串。
	 */
	public static String toString(Object obj) {
		if (obj == null) {
			return EMPTY;
		} else {
			if (obj instanceof String) {
				return (String) obj;
			} else {
				return obj.toString();
			}
		}
	}

	/**
	 * 判断字符串是否是Email格式。
	 * <p>
	 * 
	 * @param email
	 *            email字符串。
	 * @return true 此字符串为Email格式。 <br/>
	 *         false 不是Email格式。
	 */
	public static boolean isEmail(String email) {
		if (!email.matches("[\\w\\.\\-+_\\w]+@([\\w\\-_\\w]+\\.)+[\\w\\-_\\w]+")) {
			return false;
		}
		return true;
	}

	/**
	 * 判断一个目标字符串是否为空。
	 * <p>
	 * 当且仅当目标字符串string==null||string.equals("")时返回true,其他情况返回false。<br/>
	 * 
	 * <pre>
	 * 示例如下：
	 * 	StringUtils.isEmpty(null);		//return true;
	 * 	StringUtils.isEmpty("");	//return true;
	 * 	StringUtils.isEmpty(" ");		//return false;
	 * 	StringUtils.isEmpty("123");		//return false;
	 * </pre>
	 * 
	 * @param string
	 *            目标字符串。
	 * @return <li>true 字符串为空。</li> <li>false 字符串不为空。</li>
	 */
	public static boolean isEmpty(String string) {
		if (string == null) {
			return true;
		}
		if (string.equals(EMPTY)) {
			return true;
		}
		return false;
	}

	/**
	 * 判断一个目标字符串是否不为空。
	 * <p>
	 * 当且仅当目标字符串string!=null||!string.equals("")时返回true,其他情况返回false。<br/>
	 * 
	 * <pre>
	 * 示例如下：
	 * 	StringUtils.isNotEmpty(null);		//return false;
	 * 	StringUtils.isNotEmpty("");		//return false;
	 * 	StringUtils.isNotEmpty(" ");		//return true;
	 * 	StringUtils.isNotEmpty("123");		//return true;
	 * </pre>
	 * 
	 * @param string
	 *            目标字符串。
	 * @return <li>true 字符串不为空。</li> <li>false 字符串为空。</li>
	 * 
	 */
	public static boolean isNotEmpty(String string) {
		return !isEmpty(string);
	}

	/**
	 * 判断一个目标字符串是否为空白字符。
	 * <p>
	 * 当且仅当目标字符串string==null||string.equals("")||string.trim().equals("")时返回true
	 * ,其他情况返回false。<br/>
	 * 
	 * <pre>
	 * 示例如下：
	 * 	StringUtils.isBlank(null);		//return true;
	 * 	StringUtils.isBlank("");	//return true;
	 * 	StringUtils.isBlank(" ");		//return true;
	 * 	StringUtils.isBlank("123");		//return false;
	 * </pre>
	 * 
	 * @param string
	 *            目标字符串。
	 * @return <li>true 字符串为空。</li> <li>false 字符串不为空。</li>
	 * 
	 */
	public static boolean isBlank(String string) {
		if (string != null && !string.trim().equals("")) {
			return false;
		}
		return true;
	}

	/**
	 * 判断一个目标字符串是否为空。
	 * <p>
	 * 当且仅当目标字符串string!=null||!string.equals("")||!string.trim().equals("")
	 * 时返回true,其他情况返回false。<br/>
	 * 
	 * <pre>
	 * 示例如下：
	 * 	StringUtils.isNotBlank(null);		//return false;
	 * 	StringUtils.isNotBlank("");	//return false;
	 * 	StringUtils.isNotBlank(" ");		//return false;
	 * 	StringUtils.isNotBlank("123");		//return true;
	 * </pre>
	 * 
	 * @param string
	 *            目标字符串。
	 * @return <li>true 字符串为空。</li> <li>false 字符串不为空。</li>
	 * 
	 */
	public static boolean isNotBlank(String string) {
		return !isBlank(string);
	}

	/**
	 * 判断一个目标字符串是否为空。
	 * <p>
	 * 如果为空则返回默认字符串，否则返回字符串本身。
	 * 
	 * <pre>
	 * 示例如下：
	 * 	StringUtil.isBlankToDefault(null,"1");		//return "1";
	 * 	StringUtil.isBlankToDefault("","1");		//return "1";
	 * 	StringUtil.isBlankToDefault(" ","1");		//return "1";
	 * 	StringUtil.isBlankToDefault("123","1");		//return "123";
	 * </pre>
	 * 
	 * @param string
	 *            目标字符串。
	 * @param defaultStr
	 *            默认字符串。
	 * @return 目标字符串本身或默认字符串。
	 * 
	 */
	public static String isBlankToDefault(String string, String defaultStr) {
		if (isBlank(string)) {
			return defaultStr;
		} else {
			return string;
		}
	}

	/**
	 * 判断一个目标字符串是否为空。
	 * <p>
	 * 如果为空则返回默认字符串，否则返回字符串本身。
	 * 
	 * <pre>
	 * 示例如下：
	 * 	StringUtil.isEmptyToDefault(null,"1");		//return "1";
	 * 	StringUtil.isEmptyToDefault("","1");		//return "1";
	 * 	StringUtil.isEmptyToDefault(" ","1");		//return " ";
	 * 	StringUtil.isEmptyToDefault("123","1");		//return "123";
	 * </pre>
	 * 
	 * @param string
	 *            目标字符串。
	 * @param defaultStr
	 *            默认字符串。
	 * @return 目标字符串本身或默认字符串。
	 * 
	 */
	public static String isEmptyToDefault(String string, String defaultStr) {
		if (isEmpty(string)) {
			return defaultStr;
		} else {
			return string;
		}
	}

	/**
	 * 判断一个目标字符串是否为空。
	 * <p>
	 * 如果为空则返回 null，否则返回字符串本身。
	 * 
	 * <pre>
	 * 示例如下：
	 * 	StringUtils.isEmptyToNull(null);		//return null;
	 * 	StringUtils.isEmptyToNull("");		//return null;
	 * 	StringUtils.isEmptyToNull(" ");		//return null;
	 * 	StringUtils.isEmptyToNull("123");		//return "123";
	 * </pre>
	 * 
	 * @param string
	 *            目标字符串。
	 * @return 目标字符串本身或null。
	 * 
	 */
	public static String isEmptyToNull(String string) {
		if (isEmpty(string)) {
			return null;
		} else {
			return string;
		}
	}

	/**
	 * 判断一个目标字符串是否为空。
	 * <p>
	 * 如果为空则返回 StringUtils.EMPTY，否则返回字符串本身。
	 * 
	 * <pre>
	 * 示例如下：
	 * 	StringUtils.isEmptyToEmpty(null);		//return "";
	 * 	StringUtils.isEmptyToEmpty("");		//return "";
	 * 	StringUtils.isEmptyToEmpty(" ");		//return "";
	 * 	StringUtils.isEmptyToEmpty("123");		//return "123";
	 * </pre>
	 * 
	 * @param string
	 *            目标字符串。
	 * @return 目标字符串本身或StringUtils.EMPTY。
	 * 
	 */
	public static String isEmptyToEmpty(String string) {
		if (isEmpty(string)) {
			return com.xidian.iot.common.util.StringUtil.EMPTY;
		} else {
			return string;
		}
	}

	/**
	 * 判断一个目标字符串是否是数字类型。
	 * <p>
	 * 当且仅当目标字符串符合数字格式时返回true，其他情况返回false。
	 * 
	 * <pre>
	 * 示例如下：
	 * 	StringUtils.isNumeric(null);		//return false;
	 * 	StringUtils.isNumeric("");			//return false;
	 * 	StringUtils.isNumeric(" ");			//return false;
	 * 	StringUtils.isNumeric("abc");		//return false;
	 * 	StringUtils.isNumeric("123");		//return true;
	 * 	StringUtils.isNumeric("12.3");		//return true;
	 * </pre>
	 * 
	 * @param string
	 *            目标字符串。
	 * @return <li>true 字符串是数字格式。</li> <li>false 字符串不是数字格式。</li>
	 */
	public static boolean isNumeric(String string) {
		if (isEmpty(string)) {
			return false;
		}
		Pattern p = Pattern.compile("-?\\d+(\\.\\d+)?");
		return p.matcher(string).matches();
	}

	/**
	 * 判断一个目标字符串是否是整型。
	 * <p>
	 * 当且仅当目标字符串是整型时返回true，其他情况返回false。
	 * 
	 * <pre>
	 * 示例如下：
	 * 	StringUtils.isInteger(null);		//return false;
	 * 	StringUtils.isInteger("");			//return false;
	 * 	StringUtils.isInteger(" ");			//return false;
	 * 	StringUtils.isInteger("abc");		//return false;
	 * 	StringUtils.isInteger("123");		//return true;
	 * 	StringUtils.isInteger("12.3");		//return false;
	 * </pre>
	 * 
	 * @param string
	 *            目标字符串。
	 * @return <li>true 字符串是数字格式。</li> <li>false 字符串不是数字格式。</li>
	 * 
	 */
	public static boolean isInteger(String string) {
		boolean flag = false;
		try {
			Integer.parseInt(string);
			flag = true;
		} catch (Exception e) {

		}

		return flag;
	}

	/**
	 * 判断一个目标字符串是否是整型。
	 * <p>
	 * 当且仅当目标字符串是整型时转换并返回整型对象，其他情况返回null。
	 * 
	 * <pre>
	 * 示例如下：
	 * 	StringUtils.isIntegerToInteger(null);		//return null;
	 * 	StringUtils.isIntegerToInteger("");			//return null;
	 * 	StringUtils.isIntegerToInteger(" ");			//return null;
	 * 	StringUtils.isIntegerToInteger("abc");		//return null;
	 * 	StringUtils.isIntegerToInteger("123");		//return new Integer("123");
	 * 	StringUtils.isIntegerToInteger("12.3");		//return null;
	 * </pre>
	 * 
	 * @param string
	 *            目标字符串。
	 * @return 目标字符串转换后的Integer对象或null。
	 * 
	 */
	public static Integer isIntegerToInteger(String string) {
		try {
			return Integer.parseInt(string);
		} catch (Exception e) {
		}
		return null;
	}

	/**
	 * 判断一个目标字符串是否是浮点值。
	 * <p>
	 * 当且仅当目标字符串是浮点值时返回true，其他情况返回false。
	 * 
	 * <pre>
	 * 示例如下：
	 * 	StringUtils.isDouble(null);		//return false;
	 * 	StringUtils.isDouble("");			//return false;
	 * 	StringUtils.isDouble(" ");			//return false;
	 * 	StringUtils.isDouble("abc");		//return false;
	 * 	StringUtils.isDouble("123");		//return true;
	 * 	StringUtils.isDouble("12.3");		//return true;
	 * </pre>
	 * 
	 * @param string
	 *            目标字符串。
	 * @return <li>true 字符串是浮点值。</li> <li>false 字符串不是浮点值。</li>
	 * 
	 */
	public static boolean isDouble(String string) {
		boolean flag = false;
		try {
			Double.parseDouble(string);
			flag = true;
		} catch (Exception e) {

		}
		return flag;
	}

	/**
	 * 判断一个目标字符串是否是浮点值。
	 * <p>
	 * 当且仅当目标字符串是浮点值时转换并返回Double对象，其他情况返回null。
	 * 
	 * <pre>
	 * 示例如下：
	 * 	StringUtils.isDoubleToDouble(null);		//return null;
	 * 	StringUtils.isDoubleToDouble("");			//return null;
	 * 	StringUtils.isDoubleToDouble(" ");			//return null;
	 * 	StringUtils.isDoubleToDouble("abc");		//return null;
	 * 	StringUtils.isDoubleToDouble("123");		//return new Double("123");
	 * 	StringUtils.isDoubleToDouble("12.3");		//return new Double("12.3");
	 * </pre>
	 * 
	 * @param string
	 *            目标字符串。
	 * @return 目标字符串转换后的Double对象或null。
	 * 
	 */
	public static Double isDoubleToDouble(String string) {
		try {
			return Double.parseDouble(string);
		} catch (Exception e) {
		}
		return null;
	}

	/**
	 * 判断一个目标字符串是否符合传入的正则表达式。
	 * <p>
	 * 当且仅当参数string匹配参数regex定义的正则表达式规则时返回true，否则返回false，调用此方法，等同于
	 * Pattern.matches(regex, string) 。
	 * 
	 * @param regex
	 *            正则表达式。
	 * @param string
	 *            目标字符串。
	 * @return <li>true 字符串匹配正则表达式。</li> <li>false 字符串不匹配正则表达式。</li>
	 */
	public static boolean match(String regex, String string) {
		if (regex == null) {
			throw new NullPointerException("'regex' is null");
		}
		if (string == null) { // 如果为null,则不能匹配成功
			return false;
		}
		return Pattern.matches(regex, string);
	}

	/**
	 * 截断一个目标字符串到保留长度。
	 * <p>
	 * 该方法将丢弃字符超出保留长度的字符，如果目标字符串长度小于保留长度，则不作任何处理返回目标字符串本身。
	 * 
	 * <pre>
	 * 示例如下：
	 * 	StringUtils.truncate("123456",2);	//return "12";
	 * 	StringUtils.truncate("123456",10);	//return "123456";
	 * 	StringUtils.truncate("123456",-1);	//return "123456";
	 * 	StringUtils.truncate("",10);		//return "";
	 * 	StringUtils.truncate(null,10);		//return "";
	 * </pre>
	 * 
	 * @param string
	 *            目标字符串。
	 * @param length
	 *            保留长度。
	 * @return 截断后的字符串。
	 */
	public static String truncate(String string, int length) {
		if (string == null) {
			return EMPTY;
		}
		if (length < 0) {
			return string;
		}

		int len = string.length();
		if (len <= length) {
			return string;
		}
		return string.substring(0, length);
	}

	/**
	 * 截断一个目标字符串到保留长度。
	 * <p>
	 * 截断一个目标字符串到保留长度，并在末尾叠加一段字符串返回。
	 * <p>
	 * 此方法首先调用{@link #truncate(String, int)}方法对目标字符串进行截断；之后调用
	 * {@link #isEmpty(String)}方法对append参数进行判断； 如果返回true，则在末尾叠加 {@link #EMPTY}
	 * ；如果返回false，则叠加append字符串本身。
	 * 
	 * <pre>
	 * 示例如下：
	 * 	StringUtils.truncate("longtext",4,"...");	//return "long...";
	 * 	StringUtils.truncate("longtext",4,null);	//return "long";
	 * 	StringUtils.truncate("longtext",-1,"...");	//return "longtext...";
	 * 	StringUtils.truncate("longtext",10,"...");	//return "longtext";
	 * </pre>
	 * 
	 * @param string
	 *            目标字符串。
	 * @param length
	 *            保留长度。
	 * @param append
	 *            叠加字符串。
	 * @return 返回截断并叠加的字符串。
	 */
	public static String truncate(String string, int length, String append) {
		if (string.length() > length) {
			return truncate(string, length) + (isEmpty(append) ? EMPTY : append);
		} else {
			return string;
		}

	}

	/**
	 * 以传入的分隔符，分割一个字符串，并转换到整型列表返回。
	 * 
	 * @param string
	 *            被分割字符串。
	 * @param separator
	 *            分隔符。
	 * 
	 * @return 整型列表。
	 */
	public static List<Integer> splitToInteger(String string, String separator) {
		List<Integer> list = new ArrayList<Integer>();
		if (isNotEmpty(string) && isNotEmpty(separator)) {
			for (String str : string.split(separator)) {
				if (isBlank(str)) {
					continue;
				}
				if (isInteger(str)) {
					list.add(Integer.parseInt(str));
				}
			}
		}
		return list;
	}

	/**
	 * 以传入的分隔符，分割一个字符串，并转换到双精度列表返回。
	 * 
	 * @param string
	 *            被分割字符串。
	 * @param separator
	 *            分隔符。
	 * 
	 * @return 双精度列表。
	 */
	public static List<Double> splitToDouble(String string, String separator) {
		List<Double> list = new ArrayList<Double>();
		if (isNotEmpty(string) && isNotEmpty(separator)) {
			for (String str : string.split(separator)) {
				if (isBlank(str)) {
					continue;
				}
				if (isDouble(str)) {
					list.add(Double.parseDouble(str));
				}
			}
		}
		return list;
	}

	/**
	 * 以传入的分隔符，分割一个字符串，并转换到字符串列表返回。
	 * 
	 * @param string
	 *            被分割字符串。
	 * @param separator
	 *            分隔符。
	 * 
	 * @return 字符串列表。
	 */
	public static List<String> splitToString(String string, String separator) {
		List<String> list = new ArrayList<String>();
		if (isNotEmpty(string)) {
			if (isEmpty(separator)) {
				list.add(string);
				return list;
			}
			for (String str : string.split(separator)) {
				if (isEmpty(str)) {
					continue;
				}
				list.add(str);
			}
		}
		return list;
	}

	/**
	 * 连接一个目标数组到字符串。
	 * <p>
	 * 连接一个目标数组到字符串，并以分隔符进行连接。如果数组为空或者长度不大于0，则返回{@link #EMPTY}
	 * 。如果数组不为空并且长度大于0，则调用数组元素的toString()方法并以分隔符进行连接。
	 * 
	 * <pre>
	 * 		Example1:
	 * 		Integer [] ids=new Integer[]{222,333,444};
	 * 		StringUtils.join(ids,",");		//return "222,333,444";
	 * 		
	 * 		
	 * 		Example2:
	 * 		public class Person{
	 * 			private String name;
	 * 			private String job;
	 * 
	 * 			public Person(String name,String job){
	 * 				this.name=name;
	 * 				this.job=job;
	 * 			}
	 * 
	 * 			public String toString(){
	 * 				return name+"&"+job;
	 * 			} 
	 * 		} 
	 * 
	 * 
	 * 		Person p1=new Person("John","Developer");
	 * 		Person p2=new Person("David","Doctor");
	 * 		Person p3=new Person("Ben","Student");
	 * 
	 * 		Person [] persons=new Person[3];
	 * 		persons[0]=p1;
	 * 		persons[1]=p2;
	 * 		persons[2]=p3;
	 * 
	 * 		StringUtils.join(persons,"、"); 	//return "John&Developer、David&Doctor、Ben&Student";
	 * </pre>
	 * 
	 * @param array
	 *            目标数组。
	 * @param separator
	 *            分隔符。
	 * @return 连接后的字符串。
	 */
	public static String join(Object[] array, String separator) {

		if (separator == null) {
			throw new NullPointerException("'separator' is null");
		}

		StringBuffer sb = new StringBuffer(EMPTY);

		if (array != null && array.length > 0) {
			for (int i = 0; i < array.length; i++) {
				// 跳过为空项
				if (array[i] == null) {
					continue;
				}
				if (i > 0) {
					sb.append(separator);
				}
				sb.append(array[i].toString());
			}
		}
		return sb.toString();
	}

	/**
	 * 连接一个目标数组到字符串。
	 * <p>
	 * 连接一个目标数组到字符串，并以默认分隔符{@link #DEFAULT_SEPARATOR}进行连接。 如果集合为空或者长度不大于0，则返回
	 * {@link #EMPTY}。
	 * 
	 * <pre>
	 * 与以下调用的行为完全相同： 
	 * 	StringUtils.join(array,DEFAULT_SEPARATOR);
	 * </pre>
	 * 
	 * @param array
	 *            目标数组。
	 * @return 连接后的字符串。
	 */
	public static String join(Object[] array) {
		return join(array, DEFAULT_SEPARATOR);
	}

	/**
	 * 连接一个目标数组到字符串。
	 * <p>
	 * 连接一个集合成员到字符串，并以分隔符进行连接。 如果集合为空或者长度不大于0，则返回{@link #EMPTY}。
	 * 
	 * <pre>
	 * 与以下调用的行为完全相同： 
	 * 	StringUtils.join(collections.toArray(),separator);
	 * </pre>
	 * 
	 * @param collections
	 *            集合。
	 * @param separator
	 *            分隔符。
	 * @return 连接后的字符串。
	 */
	public static String join(Collection<?> collections, String separator) {

		if (collections != null && collections.size() > 0) {
			return join(collections.toArray(), separator);
		}

		return EMPTY;
	}

	/**
	 * 连接一个目标数组到字符串。
	 * <p>
	 * 连接一个集合成员到字符串，并以默认分隔符{@link #DEFAULT_SEPARATOR}进行连接。
	 * 
	 * <pre>
	 * 与以下调用的行为完全相同： 
	 * StringUtils.join(collections,DEFAULT_SEPARATOR);
	 * </pre>
	 * 
	 * @param collections
	 *            集合。
	 * @return 连接后的字符串。
	 */
	public static String join(Collection<?> collections) {
		return join(collections, DEFAULT_SEPARATOR);
	}

	/**
	 * 左边填充。
	 * 
	 * @param count
	 *            填充数量，填充指定数量的填充字符
	 * @param paddingText
	 *            填充字符
	 * @param original
	 *            原始字符
	 * @return 填充后的字符
	 */
	public static String leftPadding(int count, String paddingText, String original) {
		if (original == null) {
			return null;
		}
		StringBuffer sb = new StringBuffer();
		if (isNotEmpty(paddingText)) {
			for (int i = 0; i < count; i++) {
				sb.append(paddingText);
			}
		}
		sb.append(original);
		return sb.toString();
	}

	/**
	 * 右边填充。
	 * 
	 * @param count
	 *            填充数量，填充指定数量的填充字符
	 * @param paddingText
	 *            填充字符
	 * @param original
	 *            原始字符
	 * @return 填充后的字符
	 */
	public static String rightPadding(int count, String paddingText, String original) {
		if (original == null) {
			return null;
		}
		StringBuffer sb = new StringBuffer();
		sb.append(original);
		if (isNotEmpty(paddingText)) {
			for (int i = 0; i < count; i++) {
				sb.append(paddingText);
			}
		}
		return sb.toString();
	}
}
