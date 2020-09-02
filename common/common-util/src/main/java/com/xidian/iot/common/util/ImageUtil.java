package com.xidian.iot.common.util;

import org.apache.log4j.Logger;

import java.io.File;

/**
 * 图片工具类，提供等比缩放图片、加水印、剪裁等操作。
 * <p>
 * 此类包括图片压缩大小等。<br/>
 * 此类依赖于gm命令，程序路径应指定在{@link com.xidian.iot.common.util.ImageUtil#GM_PATH} ，若系统丢失此命令则会导致部分功能失效。gm命令官网<a
 * href="http://www.graphicsmagick.org/">http://www.graphicsmagick.org/<a>
 * 
 * @author zhengrunjin
 * @since 0.1
 */
public class ImageUtil {

	private static Logger log = Logger.getLogger(com.xidian.iot.common.util.ImageUtil.class);

	/**
	 * gm命令路径。
	 */
	public static final String GM_PATH = "/usr/local/bin/gm";

	/**
	 * 将源图片等比缩略，并存放到指定路径。
	 * <p>
	 * 指定高宽不会引起图片变形，即等比缩放。
	 * 
	 * @param sourcePath
	 *            源图片路径。
	 * @param targetPath
	 *            缩略图路径。
	 * @param width
	 *            设定宽。
	 * @param height
	 *            设定长。
	 * @return 当返回-1则转换失败。
	 */
	public static int zoomOut(String sourcePath, String targetPath, int width, int height) {
		int r = -1;
		try {
			Process process = Runtime.getRuntime().exec(GM_PATH + " convert " + sourcePath + " -resize " + width + "x" + height + " " + targetPath);
			// gm convert input.jpg -thumbnail '100x100!' output_2.jpg 会变形
			r = process.waitFor();
			if (r == 0) {
				File resultFile = new File(targetPath + ".0");
				if (resultFile.exists()) {
					resultFile.renameTo(new File(targetPath));
					if (resultFile.getParent() != null) {
						if (new File(resultFile.getParent()).isDirectory()) {
							for (File file : new File(resultFile.getParent()).listFiles()) {
								if (file.getPath().startsWith(targetPath + ".")) {
									file.delete();
								}
							}
						}
					}
				}
			} else {
				log.error("CHANGE SIZE ERROR [-filepath:" + sourcePath + " -toPath:" + targetPath + "]");
			}
		} catch (Exception e) {
			log.error("convert image size error", e);
		}
		return r;
	}

	/**
	 * 在源图片中添加水印(图片log)。
	 * 
	 * @param sourcePath
	 *            源图片路径。
	 * @param targetPath
	 *            缩略图路径。
	 * @param logoPath
	 *            logo图路径。
	 * @return 当返回-1则失败。
	 */
	public static int addWatermark(String sourcePath, String targetPath, String logoPath) {
		try {
			Process process = Runtime.getRuntime().exec(GM_PATH + " -composite -gravity NorthWest -dissolve 50 " + logoPath + " " + sourcePath + " " + targetPath);
			process.waitFor();
			return 1;
		} catch (Exception e) {
			log.error("add watermark error ", e);
		}
		return -1;
	}

	/**
	 * 将源图片从居中剪裁一张新图片。
	 * 
	 * @param sourcePath
	 *            源图片路径。
	 * @param targetPath
	 *            缩略图路径。
	 * @param width
	 *            剪裁宽度。
	 * @param height
	 *            剪裁高度。
	 * @return 当返回-1则失败。
	 */
	public static int clip(String sourcePath, String targetPath, int width, int height) {
		try {
			Process process = Runtime.getRuntime().exec(GM_PATH + " convert " + sourcePath + "  -gravity center -extent " + width + "x" + height + " " + targetPath);
			process.waitFor();
			return 1;
		} catch (Exception e) {
			log.error("add watermark error ", e);
		}
		return -1;
	}
}
