package com.cdeledu.util.swing.util;

import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import com.cdeledu.util.swing.CrawlMain;

/**
 * @ClassName: GuiUtils
 * @Description: 工具类
 * @author: 独泪了无痕
 * @date: 2015年9月18日 下午4:23:24
 * @version: V1.0
 * @history:
 */
public class GuiUtils {
	/** -------------------------- 属性 begin ------------------------------- */

	/**************************** 字体 ****************************/

	/**
	 * 所有字体.
	 */
	public static Map<String, Font> availableFontsMap = availableFontsMap();

	/**
	 * 中文字体集.
	 */
	public static String[] fontStyles_cn;
	/** 中文字体 */
	public static String fontStyle_cn;
	/** 英文字体集 */
	public static String[] fontStyles;
	/** 英文字体 */
	public static String fontStyle;
	/** 支持Unicode的字体集 */
	public static String[] fontStyles_un;
	/** 支持Unicode的字体 */
	public static String fontStyle_un;

	public static Font font12_cn;
	public static Font font13;
	public static Font font13_cn;
	public static Font font14_cn;
	public static Font font14_un;
	public static Font font14b;
	public static Font font14b_cn;

	public static Font font16;

	/** -------------------------- 属性 end ------------------------------- */
	/** -------------------------- 私有方法 begin ------------------------------- */
	/** 获取Jar lib或classes目录(前提是类被编译在其下)的父路径 */
	private static String getLibParentPath() {
		String classResource = GuiUtils.class.getName().replace(".", "/")
				+ ".class";
		String path = "";
		try {
			path = GuiUtils.class.getClassLoader().getResource(classResource)
					.getPath();
			path = path.replace("+", "%2b"); // "+"号decode后为空格" "，"%2b"号decode后为"+"号
			path = URLDecoder.decode(path, "UTF-8");
			path = path.substring(0, path.length() - classResource.length());
			if (path.contains(".jar!")) {
				path = path.substring(5, path.indexOf(".jar!") + 4);
				path = path.substring(0, path.lastIndexOf("/"));
				path = path.substring(0, path.length() - "lib".length());
			} else {
				int beginIndex = path.length();
				@SuppressWarnings("unused")
				int endIndex = "classes".length() - 1;
				path = path.substring(0, beginIndex);
				// path = path .substring(0, beginIndex - endIndex);
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return path;
	}

	/** 支持的字体 */
	private static Map<String, Font> availableFontsMap() {
		Font[] fonts = availableFonts();
		Map<String, Font> fontsMap = new HashMap<String, Font>();
		for (Font font : fonts) {
			fontsMap.put(font.getFontName(), font);
		}
		return fontsMap;
	}

	/** 支持的字体 */
	private static Font[] availableFonts() {
		GraphicsEnvironment environment = GraphicsEnvironment
				.getLocalGraphicsEnvironment();
		return environment.getAllFonts();
	}

	/** -------------------------- 私有方法 end ------------------------------- */
	/** -------------------------- 公有方法 begin ------------------------------- */
	/** 初始化字体 */
	public static void initFont() {
		font12_cn = new Font(fontStyle_cn, Font.PLAIN, 12);

		font13 = new Font(fontStyle, Font.PLAIN, 13);
		font13_cn = new Font(fontStyle_cn, Font.PLAIN, 13);

		font14_cn = new Font(fontStyle_cn, Font.PLAIN, 14);
		font14_un = new Font(fontStyle_un, Font.PLAIN, 14);
		font14b = new Font(fontStyle, Font.BOLD, 14);
		font14b_cn = new Font(fontStyle_cn, Font.BOLD, 14);

		font16 = new Font(fontStyle, Font.PLAIN, 16);
	}

	/** 优先使用前面的字体，如果不存在，则一个一个向后查找.. */
	public static String getAvailableFont(String[] fontStyles) {
		String fontName = "";
		for (String fontStyle : fontStyles) {
			if (availableFontsMap.containsKey(fontStyle)) {
				fontName = fontStyle;
				break;
			}
		}
		return fontName;
	}

	/** 获取与Jar lib或classes目录(前提是类被编译在其下)同级的文件(夹)或同级文件夹的子文件(夹)的绝对路径. */
	public static String getActualPath(String path) {
		return getLibParentPath() + path;
	}

	/**
	 * 
	 * @Title: getImage
	 * @Description: 获取目录img/icon目录下的图片
	 * @author: 独泪了无痕
	 * @param path
	 * @param kit
	 * @return
	 */
	public static Image getImage(String path, Toolkit kit) {
		URL imgURL = null;
		try {
			imgURL = new File(getActualPath(path)).toURI().toURL();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return kit.getImage(imgURL);
	}

	/**
	 * 获取目录img/icon目录下的图标.
	 */
	public static Icon getIcon(String path, Toolkit kit) {
		return new ImageIcon(getImage(path, kit));
	}

	/** 将消息输出到控制台. */
	public static void log(String msg) {
		try {
			CrawlMain.msgTextArea.append(msg + "\n");
		} catch (Exception uee) {
			log(uee);
		}
	}

	/** 将异常输出到控制台. */
	public static void log(Exception ex) {
		StackTraceElement[] ste = ex.getStackTrace();
		StringBuilder esb = new StringBuilder();
		esb.append("Exception: ").append(ex.getClass().getName()).append(": ")
				.append(ex.getMessage()).append("\n");
		for (int i = 0; i < 5 && i < ste.length; i++) {
			esb.append("                    ").append(ste[i].toString())
					.append("\n");
		}
		CrawlMain.msgTextArea.append(esb.toString());
	}

	/** 消息、异常输出到控制台. */
	public static void log(String msg, Exception e) {
		log(msg);
		log(e.getMessage());
	}

	/** 加载配置是异常输出 */
	public static void logLoadPropertiesException(String filePath, Exception e) {
		GuiUtils.log("加载配置\"" + filePath + "\"出错！", e);
	}
	/** -------------------------- 公有方法 end ------------------------------- */
}
