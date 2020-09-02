package com.xidian.iot.common.util;

import org.apache.commons.io.IOUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 此类提供了关于IO的一些常用方法。
 * <p>
 * 这个类包装了一部分对InputStream、OutputStream操作的方法。例如从输入流转换到字符串，省去了繁琐的字节读取过程。
 * 
 * @author zhengrunjin
 * @since 0.1
 */
public class IOUtil {

	/**
	 * 获得字节数组，从InputStream中获得。
	 * 
	 * @param input
	 *            输入流。
	 * @return 从input中读取出的字节数组。
	 * @throws IOException
	 *             发生了读写异常
	 */
	public static byte[] toByteArray(InputStream input) throws IOException {
		return IOUtils.toByteArray(input);
	}

	/**
	 * 获得字节数组,从String中获得。
	 * 
	 * @param string
	 *            输入字符串。
	 * @return 从string中读取出的字节数组。
	 * @throws IOException
	 *             发生了读写异常
	 */
	public static byte[] toByteArray(String string) {
		return string.getBytes();
	}

	/**
	 ** 获得字节数组,从String中获得，并指定读取的编码格式。
	 * 
	 * @param string
	 *            输入字符串。
	 * @param encoding
	 *            输入字符串的编码。
	 * @return 从string中读取出的字节数组。
	 * @throws UnsupportedEncodingException
	 */
	public static byte[] toByteArray(String string, String encoding) throws UnsupportedEncodingException {
		return string.getBytes(encoding);
	}

	/**
	 * 获得字符串，从InputStream中获得。
	 * 
	 * @param input
	 *            输入流。
	 * @return 得到的字符串。
	 * @throws IOException
	 *             发生了读写异常
	 */
	public static String toString(InputStream input) throws IOException {
		return IOUtils.toString(input);
	}

	/**
	 * 获得字符串，从InputStream中获得，并指定读取的编码格式。
	 * 
	 * @param input
	 *            输入流。
	 * @param encoding
	 *            输入流的编码。
	 * @return 得到的字符串。
	 * @throws IOException
	 *             发生了读写异常
	 */
	public static String toString(InputStream input, String encoding) throws IOException {
		return IOUtils.toString(input, encoding);
	}

	/**
	 * 获得字符串，从byte[]中获得。
	 * 
	 * @param input
	 *            字节数组。
	 * @return 得到的字符串。
	 * @throws IOException
	 *             发生了读写异常
	 */
	public static String toString(byte[] input) throws IOException {
		return new String(input);
	}

	/**
	 * 
	 * 获得字符串，从byte[]中获得，并指定读取的编码格式。
	 * 
	 * @param input
	 *            字节数组。
	 * @param encoding
	 *            字节数组的编码。
	 * @return 得到的字符串。
	 * @throws UnsupportedEncodingException
	 */
	public static String toString(byte[] input, String encoding) throws UnsupportedEncodingException {
		return new String(input, encoding);
	}

	/**
	 * 获得字符串，从Reader中获得。
	 * 
	 * @param input
	 *            Reader输入流。
	 * @return 得到的字符串。
	 * @throws IOException
	 *             发生了读写异常
	 */
	public static String toString(Reader input) throws IOException {
		return IOUtils.toString(input);
	}

	/**
	 * 将字节数组转化为16进制字符串。
	 * 
	 * @param byteArray
	 *            字节数组。
	 * @return 16进制字符串。
	 */
	public static String toHexString(byte[] byteArray) {

		return null;
	};

	/**
	 * 拷贝输入流到输出流。
	 * 
	 * @param input
	 *            输入流。
	 * @param output
	 *            输出流。
	 * @return 拷贝的字节数。
	 * @throws IOException
	 *             发生了读写异常
	 */
	public static long copy(InputStream input, OutputStream output) throws IOException {
		return IOUtils.copyLarge(input, output);
	}

	/**
	 * 拷贝输入流到输出流。
	 * 
	 * @param input
	 *            输入流。
	 * @param output
	 *            输出流。
	 * @throws IOException
	 *             发生了读写异常
	 */
	public static void copy(InputStream input, Writer output) throws IOException {
		IOUtils.copy(input, output);
	}

	/**
	 * 拷贝输入流到输出流，并指定输入流的编码格式。
	 * <p>
	 * 注意：如果你在使用此方法写入乱码时，请检测自己的Writer是否制定了编码格式。
	 * 
	 * @param input
	 *            输入流。
	 * @param output
	 *            输出流。
	 * @param encoding
	 *            输入流编码。
	 * @throws IOException
	 *             发生了读写异常
	 */
	public static void copy(InputStream input, Writer output, String encoding) throws IOException {
		IOUtils.copy(input, output, encoding);
	}

	/**
	 * 拷贝输入流到输出流。
	 * 
	 * @param reader
	 *            输入流。
	 * @param output
	 *            输出流。
	 * @throws IOException
	 *             发生了读写异常
	 */
	public static void copy(Reader reader, OutputStream output) throws IOException {
		IOUtils.copy(reader, output);
	}

	/**
	 * 拷贝输入流到输出流，并指定输出流的编码格式。
	 * 
	 * @param reader
	 *            输入流。
	 * @param output
	 *            输出流。
	 * @param encoding
	 *            输出流编码。
	 * @throws IOException
	 *             发生了读写异常
	 */
	public static void copy(Reader reader, OutputStream output, String encoding) throws IOException {
		IOUtils.copy(reader, output, encoding);
	}

	/**
	 * 写入输出流。
	 * 
	 * @param data
	 *            将要写入的byte数组。
	 * @param output
	 *            输出流。
	 * @throws IOException
	 *             发生了读写异常
	 */
	public static void write(byte[] data, OutputStream output) throws IOException {
		IOUtils.write(data, output);
	}

	/**
	 * 写入输出流。
	 * 
	 * @param data将要写入的byte数组
	 *            。
	 * @param writer
	 *            输出流。
	 * @throws IOException
	 *             发生了读写异常
	 */
	public static void write(byte[] data, Writer writer) throws IOException {
		IOUtils.write(data, writer);
	}

	/**
	 * 写入输出流，并指定byte的编码格式。
	 * 
	 * @param data
	 *            将要写入的byte数组。
	 * @param output
	 *            输出流。
	 * @param encoding
	 *            输出流编码。
	 * @throws IOException
	 *             发生了读写异常
	 */
	public static void write(byte[] data, Writer output, String encoding) throws IOException {
		IOUtils.write(data, output, encoding);
	}

	/**
	 * 写入输出流。
	 * 
	 * @param data
	 *            将要写入的String数据。
	 * @param writer
	 *            输出流。
	 * @throws IOException
	 *             发生了读写异常
	 */
	public static void write(String data, Writer writer) throws IOException {
		IOUtils.write(data, writer);
	}

	/**
	 * 写入输出流。
	 * 
	 * @param data
	 *            将要写入的String数据。
	 * @param output
	 *            输出流。
	 * @throws IOException
	 *             发生了读写异常
	 */
	public static void write(String data, OutputStream output) throws IOException {
		IOUtils.write(data, output);
	}

	/**
	 * 写入输出流，并指定String的编码格式。
	 * 
	 * @param data
	 *            将要写入的String数据。
	 * @param output
	 *            输出流。
	 * @param encoding
	 *            输出流编码。
	 * @throws IOException
	 *             发生了读写异常
	 */
	public static void write(String data, OutputStream output, String encoding) throws IOException {
		IOUtils.write(data, output, encoding);
	}

	/**
	 * 将输入流读成一个字符串列表。
	 * 
	 * @param input
	 *            输入流。
	 * @param encoding
	 *            字符编码。
	 * @return 字符串列表。
	 * @throws IOException
	 *             发生异常
	 */
	public static List<String> readLines(InputStream input, String encoding) throws IOException {
		if (encoding == null) {
			return readLines(input);
		} else {
			InputStreamReader reader = new InputStreamReader(input, encoding);
			return readLines(reader);
		}
	}

	/**
	 * 将输入流读成一个字符串列表。
	 * 
	 * @param input
	 *            输入流。
	 * @return 字符串列表。
	 * @throws IOException
	 *             发生异常
	 */
	public static List<String> readLines(InputStream input) throws IOException {
		InputStreamReader reader = new InputStreamReader(input);
		return readLines(reader);
	}

	/**
	 * 将阅读器读成一个字符串列表。
	 * 
	 * @param input
	 *            阅读器。
	 * @return 字符串列表。
	 * @throws IOException
	 *             发生异常
	 */
	public static List<String> readLines(Reader input) throws IOException {
		BufferedReader reader = new BufferedReader(input);
		List<String> list = new ArrayList<String>();
		String line = reader.readLine();
		while (line != null) {
			list.add(line);
			line = reader.readLine();
		}
		return list;
	}
}
