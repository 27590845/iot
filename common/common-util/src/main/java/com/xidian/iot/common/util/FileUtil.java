package com.xidian.iot.common.util;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 此类提供了关于File类的一些常用方法。
 * <p>
 * 这个类的实现包括创建文件、删除文件、移动文件、读取文件、写入文件的方法。通过对JavaAPI中的File类进行操作实现对操作系统目录和文件的操作。
 * 
 * @author zhengrunjin
 * @since 0.1
 */
public class FileUtil {

	/**
	 * 新建一个文件，分别指定文件目录、文件名称。
	 * 
	 * @param fileDir
	 *            新文件所在目录。
	 * @param fileName
	 *            文件名称。
	 * @return 当创建成功时返回File实例，其他情况返回null。
	 * @throws IOException
	 */
	public static File createFile(String fileDir, String fileName) throws IOException {
		return createFile(new File(fileDir), fileName);
	}

	/**
	 * 新建一个文件，分别指定文件目录、文件名称。
	 * 
	 * @param fileDir
	 *            新文件所在目录。
	 * @param fileName
	 *            文件名称。
	 * @return 当创建成功时返回File实例，其他情况返回null。
	 * @throws IOException
	 */
	public static File createFile(File fileDir, String fileName) throws IOException {
		if (!fileDir.exists()) {
			fileDir.mkdirs();
		}
		File file = new File(fileDir, fileName);
		if (file.exists()) {
			return file;
		} else {
			if (file.createNewFile()) {
				return file;
			} else {
				return null;
			}
		}
	}

	/**
	 * 新建一个文件，通过文件路径。
	 * <p>
	 * 如果文件父目录不存在，则会自动创建。
	 * 
	 * @param filePath
	 *            文件路径。
	 * @return 当创建成功时返回File实例，其他情况返回null。
	 * @throws IOException
	 */
	public static File createFile(String filePath) throws IOException {
		return createFile(new File(filePath));
	}

	/**
	 * 新建一个文件,通过文件路径的File实例。
	 * <p>
	 * 如果文件父目录不存在，则会自动创建。
	 * 
	 * @param filePath
	 *            文件路径。
	 * @return 当创建成功时返回File实例，其他情况返回null。
	 * @throws IOException
	 */
	public static File createFile(File file) throws IOException {
		File fileDir = file.getParentFile();
		return createFile(fileDir, file.getName());
	}

	/**
	 * 新建一个目录,通过目录路径。
	 * 
	 * @param dirPath
	 *            目录路径。
	 * @return 当创建成功时返回File实例，其他情况返回null。
	 * @throws IOException
	 */
	public static File createDirectory(String dirPath) throws IOException {
		return createDirectory(new File(dirPath));
	}

	/**
	 * 新建一个目录,通过File实例。
	 * 
	 * @param dir
	 *            目录路径。
	 * @return 当创建成功时返回File实例，其他情况返回null。
	 * @throws IOException
	 */
	public static File createDirectory(File dir) throws IOException {
		if (!dir.exists()) {
			dir.mkdirs();
		}
		return dir;
	}

	/**
	 * 删除文件,通过准备删除文件的File实例。
	 * 
	 * @param file
	 *            准备删除文件。
	 * @throws IOException
	 */
	public static void deleteFile(File file) throws IOException {
		FileUtils.forceDelete(file);
	}

	/**
	 * 删除文件,通过文件路径的字符串。
	 * 
	 * @param filePath
	 *            准备删除文件路径。
	 * @throws IOException
	 */
	public static void deleteFile(String filePath) throws IOException {
		deleteFile(new File(filePath));
	}

	/**
	 * 删除目录,通过准备删除目录的File实例。
	 * 
	 * @param directory
	 *            准备删除的目录。
	 * @throws IOException
	 */
	public static void deleteDirectory(File directory) throws IOException {
		FileUtils.deleteDirectory(directory);
	}

	/**
	 * 删除目录,通过准备删除的目录路径的字符串。
	 * 
	 * @param directoryPath
	 *            准备删除的目录路径。
	 * @throws IOException
	 */
	public static void deleteDirectory(String directoryPath) throws IOException {
		deleteDirectory(new File(directoryPath));
	}

	/**
	 * 清空目录，通过准备清空的目录的File实例。
	 * 
	 * @param directory
	 *            准备清空的目录。
	 * @throws IOException
	 */
	public static void cleanDirectory(File directory) throws IOException {
		FileUtils.cleanDirectory(directory);
	}

	/**
	 * 拷贝文件,保持最后修改日期不变。
	 * 
	 * @param srcFile
	 *            源文件。
	 * @param destFile
	 *            目标文件。
	 * @throws IOException
	 */
	public static void copyFile(String srcFile, String destFile) throws IOException {
		FileUtils.copyFile(new File(srcFile), new File(destFile));
	}

	/**
	 * 拷贝文件,保持最后修改日期不变。
	 * 
	 * @param srcFile
	 *            源文件。
	 * @param destFile
	 *            目标文件。
	 * @throws IOException
	 */
	public static void copyFile(File srcFile, File destFile) throws IOException {
		FileUtils.copyFile(srcFile, destFile);
	}

	/**
	 * 拷贝文件，可选择是否保持最后修改日期。
	 * 
	 * @param srcFile
	 *            源文件。
	 * @param destFile
	 *            目标文件。
	 * @param preserveFileDate
	 *            是否保存文件日期。
	 * @throws IOException
	 */
	public static void copyFile(String srcFile, String destFile, boolean preserveFileDate) throws IOException {
		FileUtils.copyFile(new File(srcFile), new File(destFile), preserveFileDate);
	}

	/**
	 * 拷贝文件，可选择是否保持最后修改日期。
	 * 
	 * @param srcFile
	 *            源文件。
	 * @param destFile
	 *            目标文件。
	 * @param preserveFileDate
	 *            是否保存文件日期。
	 * @throws IOException
	 */
	public static void copyFile(File srcFile, File destFile, boolean preserveFileDate) throws IOException {
		FileUtils.copyFile(srcFile, destFile, preserveFileDate);
	}

	/**
	 * 拷贝文件到目录,保持最后修改日期不变。
	 * 
	 * @param srcFile
	 *            源文件。
	 * @param destDir
	 *            目标目录。
	 * @throws IOException
	 */
	public static void copyFileToDirectory(File srcFile, File destDir) throws IOException {
		FileUtils.copyFileToDirectory(srcFile, destDir);
	}

	/**
	 * 拷贝文件到目录，可选择是否保持最后修改日期。
	 * 
	 * @param srcFile
	 *            源文件。
	 * @param destDir
	 *            目标目录。
	 * @param preserveFileDate
	 *            是否保存文件日期。
	 * @throws IOException
	 */
	public static void copyFileToDirectory(File srcFile, File destDir, boolean preserveFileDate) throws IOException {
		FileUtils.copyFileToDirectory(srcFile, destDir, preserveFileDate);
	}

	/**
	 * 拷贝目录保持最后修改日期不变。
	 * 
	 * @param srcDir
	 *            源目录。
	 * @param destDir
	 *            目标目录。
	 * @throws IOException
	 */
	public static void copyDirectory(File srcDir, File destDir) throws IOException {
		FileUtils.copyDirectory(srcDir, destDir);
	}

	/**
	 * 拷贝目录保持最后修改日期不变。
	 * 
	 * @param srcDir
	 *            源目录。
	 * @param destDir
	 *            目标目录。
	 * @throws IOException
	 */
	public static void copyDirectory(String srcDir, String destDir) throws IOException {
		FileUtils.copyDirectory(new File(srcDir), new File(destDir));
	}

	/**
	 * 拷贝目录，可选择是否保持最后修改日期。
	 * 
	 * @param srcDir
	 *            源目录。
	 * @param destDir
	 *            目标目录。
	 * @param preserveFileDate
	 *            是否保存文件日期。
	 * @throws IOException
	 */
	public static void copyDirectory(String srcDir, String destDir, boolean preserveFileDate) throws IOException {
		FileUtils.copyDirectory(new File(srcDir), new File(destDir), preserveFileDate);
	}

	/**
	 * 拷贝目录，可选择是否保持最后修改日期。
	 * 
	 * @param srcDir
	 *            源目录。
	 * @param destDir
	 *            目标目录。
	 * @param preserveFileDate
	 *            是否保存文件日期。
	 * @throws IOException
	 */
	public static void copyDirectory(File srcDir, File destDir, boolean preserveFileDate) throws IOException {
		FileUtils.copyDirectory(srcDir, destDir, preserveFileDate);
	}

	/**
	 * 移动文件。
	 * 
	 * @param srcFile
	 *            源文件。
	 * @param destFile
	 *            目标文件。
	 * @throws IOException
	 */
	public static void moveFile(String srcFile, String destFile) throws IOException {
		moveFile(new File(srcFile), new File(destFile));
	}

	/**
	 * 移动文件。
	 * 
	 * @param srcFile
	 *            源文件。
	 * @param destFile
	 *            目标文件。
	 * @throws IOException
	 */
	public static void moveFile(File srcFile, File destFile) throws IOException {
		FileUtils.moveFile(srcFile, destFile);
	}

	/**
	 * 移动目录。
	 * 
	 * @param srcDir
	 *            源目录。
	 * @param destDir
	 *            目标目录。
	 * @throws IOException
	 */
	public static void moveDirectory(String srcDir, String destDir) throws IOException {
		moveDirectory(new File(srcDir), new File(destDir));
	}

	/**
	 * 移动目录。
	 * 
	 * @param srcDir
	 *            源目录。
	 * @param destDir
	 *            目标目录。
	 * @throws IOException
	 */
	public static void moveDirectory(File srcDir, File destDir) throws IOException {
		FileUtils.moveDirectory(srcDir, destDir);
	}

	/**
	 * 读取文件到字符串。
	 * <p>
	 * 注意此方法在读取文本文件时，将采用系统字符集。
	 * 
	 * @param file
	 *            准备读取的文件。
	 * @return 返回读取后的字符串。
	 * @throws IOException
	 */
	public static String readFileToString(File file) throws IOException {
		return FileUtils.readFileToString(file);
	}

	/**
	 * 读取文件到字符串，并设定文件的编码格式。
	 * 
	 * @param file
	 *            准备读取的文件。
	 * @param encoding
	 *            读取时使用的编码方式。
	 * @return 返回读取后的字符串。
	 * @throws IOException
	 */
	public static String readFileToString(File file, String encoding) throws IOException {
		return FileUtils.readFileToString(file, encoding);
	}

	/**
	 * 读取文件到字节数组。
	 * 
	 * @param file
	 *            准备读取的文件。
	 * @return 返回读取后的字符串。
	 * @throws IOException
	 */
	public static byte[] readFileToByteArray(File file) throws IOException {
		return FileUtils.readFileToByteArray(file);
	}

	/**
	 * 读取文件到输入流。
	 * 
	 * @param file
	 *            准备读取的文件。
	 * @return 返回输入流。
	 * @throws IOException
	 */
	public static InputStream readFileToInputStream(File file) throws IOException {
		return new FileInputStream(file);
	}

	/**
	 * 读取文件到输入流。
	 * 
	 * @param filePath
	 *            准备读取的文件的路径。
	 * @return 返回输入流。
	 * @throws IOException
	 */
	public static InputStream readFileToInputStream(String filePath) throws IOException {
		return new FileInputStream(new File(filePath));
	}

	/**
	 * 写入字符串到文件。
	 * <p>
	 * 注意此方法在读取文本文件时，将采用系统字符集。
	 * 
	 * @param file
	 *            准备写入的文件。
	 * @param data
	 *            准备写入的数据。
	 * @throws IOException
	 */
	public static void writeStringToFile(String file, String data) throws IOException {
		FileUtils.writeStringToFile(new File(file), data);
	}

	/**
	 * 写入字符串到文件。
	 * <p>
	 * 注意此方法在读取文本文件时，将采用系统字符集。
	 * 
	 * @param file
	 *            准备写入的文件。
	 * @param data
	 *            准备写入的数据。
	 * @throws IOException
	 */
	public static void writeStringToFile(File file, String data) throws IOException {
		FileUtils.writeStringToFile(file, data);
	}

	/**
	 * 写入字符串到文件,并根据制定字符编码写入到文件中。
	 * 
	 * @param file
	 *            准备写入的文件。
	 * @param data
	 *            准备写入的数据。
	 * @param encoding
	 *            准备写入数据的编码格式。
	 * @throws IOException
	 */
	public static void writeStringToFile(File file, String data, String encoding) throws IOException {
		FileUtils.writeStringToFile(file, data, encoding);
	}

	/**
	 * 写入字节数组到文件。
	 * 
	 * @param file
	 *            准备写入的文件。
	 * @param data
	 *            准备写入的数据。
	 * @throws IOException
	 */
	public static void writeByteArrayToFile(File file, byte[] data) throws IOException {
		FileUtils.writeByteArrayToFile(file, data);
	}
}
