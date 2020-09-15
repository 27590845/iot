package com.xidian.iot.common.util;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * 反射工具类，提供了获得字段定义、获得字段值、获得方法、调用方法、设置字段值等操作。
 * <p>
 * 
 * @author zhengrunjin
 * @since 0.1
 */
@Slf4j
public class ReflectUtil {

	/**
	 * 获得指定对象的字段定义。
	 * 
	 * @param object
	 *            需要反射的对象。
	 * @param filedName
	 *            字段名称。
	 * @return 返回获得的字段对象。<br/>
	 *         有可能返回null。
	 */
	public static Field getField(Object object, String filedName) {
		if (object == null || StringUtil.isBlank(filedName)) {
			return null;
		}
		for (Class<?> superClass = object.getClass(); superClass != Object.class; superClass = superClass.getSuperclass()) {
			try {
				return superClass.getDeclaredField(filedName);
			} catch (NoSuchFieldException e) {
				// Field 不在当前类定义, 继续向上转型
			}
		}
		return null;
	}

	/**
	 * 获得指定对象的某一字段值。
	 * <p>
	 * 
	 * @param object
	 *            需要反射的对象。
	 * @param fieldName
	 *            字段名称。
	 * @return 获取到的字段值。<br/>
	 *         有可能返回null。
	 * @throws IllegalAccessException
	 * 
	 * @exception IllegalArgumentException
	 *                当无法找到字段时，抛出此异常。
	 */
	public static Object getFieldValue(Object object, String fieldName) throws IllegalAccessException {

		if (object == null) {
			throw new NullPointerException("Parameter 'object' can not null.");
		}
		if (fieldName == null) {
			throw new NullPointerException("Parameter 'fieldName' can not null.");
		}

		// 获得定义字段
		Field field = getField(object, fieldName);

		// 当无法获取字段时，抛出异常
		if (field == null)
			throw new IllegalArgumentException("Could not find field [" + fieldName + "] on target [" + object + "]");

		// 设定字段可访问
		setAccessible(field);

		// 获得字段值
		Object result = null;
		try {
			result = field.get(object);
		} catch (IllegalAccessException e) {
			log.error("get field value exception", e);
			throw e;
		}

		return result;
	}

	/**
	 * 设置指定对象的某一字段值。
	 * 
	 * @param object
	 *            需要反射的对象。
	 * @param fieldName
	 *            字段名称。
	 * @param fieldValue
	 *            字段值。
	 * @throws IllegalAccessException
	 *             当字段访问失败时。
	 * @exception IllegalArgumentException
	 *                当无法找到字段时、设值类型与目标字段不匹配时抛出此异常。
	 */
	public static void setFieldValue(Object object, String fieldName, Object fieldValue) throws IllegalAccessException {

		if (object == null) {
			throw new NullPointerException("Parameter 'object' can not null.");
		}
		if (fieldName == null) {
			throw new NullPointerException("Parameter 'fieldName' can not null.");
		}

		// 获得定义字段
		Field field = getField(object, fieldName);

		// 当无法获取字段时，抛出异常
		if (field == null)
			throw new IllegalArgumentException("Could not find field [" + fieldName + "] on target [" + object + "]");

		// 设定字段可访问
		setAccessible(field);

		// 设定值
		try {
			field.set(object, fieldValue);
		} catch (IllegalAccessException e) {
			log.error("set field value exception", e);
			throw e;
		}
	}

	/**
	 * 获得指定对象的方法定义。
	 * <p>
	 * 
	 * @param object
	 *            需要反射的对象。
	 * @param methodName
	 *            方法名称。
	 * @param parameterTypes
	 *            参数列表。
	 * @return 返回获得的方法定义。<br/>
	 *         有可能返回null。
	 */
	public static Method getMethod(Object object, String methodName, Class<?>[] parameterTypes) {
		if (object == null) {
			throw new NullPointerException("Parameter 'object' can not null.");
		}
		if (methodName == null) {
			throw new NullPointerException("Parameter 'methodName' can not null.");
		}
		for (Class<?> superClass = object.getClass(); superClass != Object.class; superClass = superClass.getSuperclass()) {
			try {
				return superClass.getDeclaredMethod(methodName, parameterTypes);
			} catch (NoSuchMethodException e) {
				// Method 不在当前类定义, 继续向上转型
			}
		}
		return null;
	}

	/**
	 * 调用某方法，并获得返回值。
	 * <p>
	 * 
	 * @param object
	 *            被调用对象。
	 * @param methodName
	 *            方法名字。
	 * @param parameterTypes
	 *            参数列表类型。
	 * @param parameters
	 *            参数列表值。
	 * @return 方法的返回值。
	 * 
	 * @throws IllegalAccessException
	 *             当无法访问方法时，抛出此错误。
	 * @throws InvocationTargetException
	 *             当调用方法时，抛出此错误。
	 * @throws IllegalArgumentException
	 *             当找不到方法是，抛出此错误。
	 */
	public static Object invokeMethod(Object object, String methodName, Class<?>[] parameterTypes, Object[] parameters) throws InvocationTargetException, IllegalAccessException {

		Method method = getMethod(object, methodName, parameterTypes);

		if (method == null) {
			throw new IllegalArgumentException("Could not find method [" + methodName + "] on target [" + object + "]");
		}

		method.setAccessible(true);

		try {
			return method.invoke(object, parameters);
		} catch (IllegalAccessException e) {
			throw e;
		}
	}

	/**
	 * 设置字段是否可访问。
	 * 
	 * @param field
	 *            字段。
	 */
	private static void setAccessible(Field field) {
		if (!Modifier.isPublic(field.getModifiers())) {
			field.setAccessible(true);
		}
	}
}
