package com.cdeledu.common.base;

import java.io.Serializable;

import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @类描述: 基类
 * @创建者: 皇族灬战狼
 * @创建时间: 2017年2月24日 下午12:57:08
 * @版本: V1.0
 * @since: JDK 1.7
 */
public abstract class BaseClass extends TagSupport implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 日志对象
	 */
	protected Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * @方法描述: Debug 输出
	 * @param clazz
	 *            目标.class
	 * @param message
	 *            输出信息
	 */
	public void debug(Class<? extends Object> clazz, String message) {
		if (!logger.isDebugEnabled())
			return;
		Logger logger = LoggerFactory.getLogger(clazz);
		logger.debug(message);
	}

	/**
	 * @方法描述: Debug 输出
	 * @param clazz
	 *            目标.Class
	 * @param fmtString
	 *            输出信息key
	 * @param value
	 *            输出信息value
	 */
	public void fmtDebug(Class<? extends Object> clazz, String fmtString, Object... value) {
		if (!logger.isDebugEnabled())
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
	 * @方法描述: Error 输出
	 * @param clazz
	 *            目标.class
	 * @param message
	 *            输出信息
	 * @param e
	 *            异常类
	 */
	public void error(Class<? extends Object> clazz, String message, Exception e) {
		Logger logger = LoggerFactory.getLogger(clazz);
		if (null == e) {
			logger.error(message);
			return;
		}
		logger.error(message, e);
	}

	/**
	 * @方法描述: Error 输出
	 * @param clazz
	 *            目标.class
	 * @param message
	 *            输出信息
	 */
	public void error(Class<? extends Object> clazz, String message) {
		error(clazz, message, null);
	}

	/**
	 * @方法描述: Error 输出
	 * @param fmtString
	 *            输出信息key
	 * @param e
	 *            异常类
	 * @param value
	 *            输出信息value
	 */
	public void fmtError(Class<? extends Object> clazz, Exception e, String fmtString,
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
	 * @方法描述: 异常填充值输出
	 * @param clazz
	 *            目标.Class
	 * @param fmtString
	 *            输出信息key
	 * @param value
	 *            输出信息value
	 */
	public void fmtError(Class<? extends Object> clazz, String fmtString, Object... value) {
		if (StringUtils.isBlank(fmtString)) {
			return;
		}
		if (null != value && value.length != 0) {
			fmtString = String.format(fmtString, value);
		}
		error(clazz, fmtString);
	}
}
