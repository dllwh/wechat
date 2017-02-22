package com.cdeledu.crawler.SocialNetwork.csdn.csdnHelper;

import java.util.UUID;

/**
 * @类描述: CSDN字符传工具类
 * @创建者: 皇族灬战狼
 * @创建时间: 2016年10月27日 下午3:49:38
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class CsdnStringUtils {
	/** ----------------------------------------------------- Fields start */
	/** ----------------------------------------------------- Fields end */

	/** ----------------------------------------------- [私有方法] */
	/** ----------------------------------------------- [私有方法] */
	/**
	 * @方法描述: 获取最后“/”后面的内容
	 * @param fullPath
	 * @return
	 */
	public static String getLastSlantContent(String fullPath) {
		int pos = fullPath.lastIndexOf("/");
		if (pos != -1) {
			return fullPath.substring(pos + 1);
		} else {
			return "";
		}
	}

	/**
	 * @方法描述: 获取“/”前面的字符
	 * @param str
	 * @return
	 */
	public static String getLastBeforeSprit(String str) {
		if (str.lastIndexOf("/") == -1)
			return str;
		return (String) str.substring(0, str.indexOf("|"));
	}

	/**
	 * @方法描述: 获取“|”前面的字符
	 * @param str
	 * @return
	 */
	public static String getBeforeVercitalLine(String str) {
		if (str.indexOf("|") == -1)
			return str;
		return (String) str.substring(0, str.indexOf("|"));
	}

	/**
	 * @方法描述: 获取“|”/后面面的字符
	 * @param str
	 * @return
	 */
	public static String getAfterVercitalLine(String str) {
		return (String) str.substring(str.indexOf("|") + 1);
	}

	/**
	 * @方法描述: 剔除空格
	 * @param str
	 * @return
	 */
	public static String getNoTrimStr(String str) {
		return str.replaceAll("\\s*", "");
	}

	/**
	 * @方法描述: 生成GUID
	 * @return
	 */
	public static String getGUID() {
		return UUID.randomUUID() + "";
	}
}
