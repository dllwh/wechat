package com.cdeledu.util.application.log;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: Logger 工具类
 * @创建者: 皇族灬战狼
 * @创建时间: 2017年10月11日 上午9:55:29
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class LoggerHelper {
	/** ----------------------------------------------------- Fields start */
	/**
	 * 是否开启Debug
	 */
	public static boolean isDebug = Logger.getLogger(LoggerHelper.class).isDebugEnabled();

	/** ----------------------------------------------------- Fields end */

	/**
	 * Debug 输出
	 * 
	 * @param clazz
	 *            目标.Class
	 * @param message
	 *            输出信息
	 */
	public static void debug(Class<? extends Object> clazz, String message) {
		if (!isDebug)
			return;
		Logger logger = Logger.getLogger(clazz);
		logger.debug(message);
	}

	/**
	 * Debug 输出
	 * 
	 * @param clazz
	 *            目标.Class
	 * @param fmtString
	 *            输出信息key
	 * @param value
	 *            输出信息value
	 */
	public static void fmtDebug(Class<? extends Object> clazz, String fmtString, Object... value) {
		if (!isDebug)
			return;
		if (StringUtils.isBlank(fmtString)) {
			return;
		}
		if (null != value && value.length != 0) {
			fmtString = String.format(fmtString, value);
		}
		debug(clazz, fmtString);
	}

	/**
	 * Error 输出
	 * 
	 * @param clazz
	 *            目标.Class
	 * @param message
	 *            输出信息
	 * @param e
	 *            异常类
	 */
	public static void error(Class<? extends Object> clazz, String message, Exception e) {
		Logger logger = Logger.getLogger(clazz);
		if (null == e) {
			logger.error(message);
			return;
		}
		logger.error(message, e);
	}

	/**
	 * Error 输出
	 * 
	 * @param clazz
	 *            目标.Class
	 * @param message
	 *            输出信息
	 */
	public static void error(Class<? extends Object> clazz, String message) {
		error(clazz, message, null);
	}

	/**
	 * 异常填充值输出
	 * 
	 * @param clazz
	 *            目标.Class
	 * @param fmtString
	 *            输出信息key
	 * @param e
	 *            异常类
	 * @param value
	 *            输出信息value
	 */
	public static void fmtError(Class<? extends Object> clazz, Exception e, String fmtString,
			Object... value) {
		if (StringUtils.isBlank(fmtString)) {
			return;
		}
		if (null != value && value.length != 0) {
			fmtString = String.format(fmtString, value);
		}
		error(clazz, fmtString, e);
	}

	/**
	 * 异常填充值输出
	 * 
	 * @param clazz
	 *            目标.Class
	 * @param fmtString
	 *            输出信息key
	 * @param value
	 *            输出信息value
	 */
	public static void fmtError(Class<? extends Object> clazz, String fmtString, Object... value) {
		if (StringUtils.isBlank(fmtString)) {
			return;
		}
		if (null != value && value.length != 0) {
			fmtString = String.format(fmtString, value);
		}
		error(clazz, fmtString);
	}
}
