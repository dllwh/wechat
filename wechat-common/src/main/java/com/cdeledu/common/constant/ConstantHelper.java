package com.cdeledu.common.constant;

import java.nio.charset.Charset;

/**
 * @ClassName: ConstantHelper
 * @Description: 常量工具
 * @author: 独泪了无痕
 * @date: 2015年11月1日 下午3:04:50
 * @version: V1.0
 * @since: JDK 1.7
 */
public final class ConstantHelper {
	// /////////////////////
	// 编码常量引用
	// /////////////////////
	public static final Charset UTF_8 = Charset.forName("UTF-8");
	public static final Charset US_ASCII = Charset.forName("US-ASCII");
	public static final Charset GBK = Charset.forName("GBK");
	public static final Charset ISO8859_1 = Charset.forName("ISO-8859-1");
	/** Class文件扩展名 */
	public static final String CLASS_EXT = ".class";
	/** Jar文件扩展名 */
	public static final String JAR_FILE_EXT = ".jar";
	/** 在Jar中的路径jar的扩展名形式 */
	public static final String JAR_PATH_EXT = ".jar!";
	/** 当Path为文件形式时, path会加入一个表示文件的前缀 */
	public static final String PATH_FILE_PRE = "file:";

	/**
	 * @方法:默认编码
	 * @创建人:独泪了无痕
	 * @return
	 */
	public static String getDefaultEncoding() {
		return Charset.defaultCharset().name();
	}
}