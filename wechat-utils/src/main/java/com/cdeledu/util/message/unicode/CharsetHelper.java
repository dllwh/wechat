package com.cdeledu.util.message.unicode;

import java.io.UnsupportedEncodingException;

import com.cdeledu.common.constant.ConstantHelper;

/**
 * @类描述: 编码相关的封装类
 * @创建者: 皇族灬战狼
 * @创建时间: 2017年2月14日 下午10:51:04
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class CharsetHelper {
	/** ----------------------------------------------------- Fields start */

	/**
	 * 7位ASCII字符，也叫作ISO646-US、Unicode字符集的基本拉丁块
	 */
	public static final String US_ASCII = ConstantHelper.US_ASCII.name();

	/**
	 * ISO 拉丁字母表 No.1，也叫作 ISO-LATIN-1
	 */
	public static final String ISO_8859_1 = ConstantHelper.ISO8859_1.name();

	/**
	 * 8 位 UCS 转换格式
	 */
	public static final String UTF_8 = ConstantHelper.UTF_8.name();

	/**
	 * 中文超大字符集
	 */
	public static final String GBK = ConstantHelper.GBK.name();;

	/** ----------------------------------------------------- Fields end */

	/** ----------------------------------------------- [私有方法] */
	public final static String changeCharset(String str, String newCharset)
			throws UnsupportedEncodingException {
		if (str != null) {
			// 用默认字符编码解码字符串。
			byte[] bs = str.getBytes();
			// 用新的字符编码生成字符串
			return new String(bs, newCharset);
		}
		return null;
	}

	/** ----------------------------------------------- [私有方法] */

	/**
	 * 将字符编码转换成US-ASCII码
	 */
	public final static String toASCII(String str) throws UnsupportedEncodingException {
		return changeCharset(str, US_ASCII);
	}

	/**
	 * 将字符编码转换成ISO-8859-1码
	 */
	public final static String toISO_8859_1(String str) throws UnsupportedEncodingException {
		return changeCharset(str, ISO_8859_1);
	}

	/**
	 * 将字符编码转换成UTF-8码
	 */
	public static String toUTF_8(String str) throws UnsupportedEncodingException {
		return changeCharset(str, UTF_8);
	}

	/**
	 * 将字符编码转换成GBK码
	 */
	public final static String toGBK(String str) throws UnsupportedEncodingException {
		return changeCharset(str, GBK);
	}
}
