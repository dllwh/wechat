package com.cdeledu.util.application.log.dialect;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cdeledu.util.application.log.level.AbstractLog;

/**
 * @类描述: Apache Commons Logging
 * @创建者: 独泪了无痕
 * @创建日期: 2016年1月23日 上午12:19:00
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class ApacheCommonsLog extends AbstractLog {
	private static final long serialVersionUID = 1L;
	private final transient org.apache.commons.logging.Log logger;
	private final String name;

	// -------------------------------------------------------------------------
	// Constructor

	public ApacheCommonsLog(Log logger, String name) {
		this.logger = logger;
		this.name = name;
	}

	public ApacheCommonsLog(Class<?> clazz) {
		this(LogFactory.getLog(clazz), clazz.getName());
	}

	public ApacheCommonsLog(String name) {
		this(LogFactory.getLog(name), name);
	}

	public String getName() {
		return this.name;
	}

	// -------------------------------------------------------------------------
	// Trace

	public boolean isTraceEnabled() {
		return logger.isTraceEnabled();
	}

	public void trace(String format, Object... arguments) {
		if (isTraceEnabled()) {
			logger.trace(String.format(format, arguments));
		}
	}

	public void trace(Throwable t, String format, Object... arguments) {
		if (isTraceEnabled()) {
			logger.trace(String.format(format, arguments), t);
		}
	}

	// -------------------------------------------------------------------------
	// Debug

	public boolean isDebugEnabled() {
		return logger.isDebugEnabled();
	}

	public void debug(String format, Object... arguments) {
		if (isDebugEnabled()) {
			logger.debug(String.format(format, arguments));
		}
	}

	public void debug(Throwable t, String format, Object... arguments) {
		if (isDebugEnabled()) {
			logger.debug(String.format(format, arguments), t);
		}
	}

	// -------------------------------------------------------------------------
	// Info

	public boolean isInfoEnabled() {
		return logger.isInfoEnabled();
	}

	public void info(String format, Object... arguments) {
		if (isInfoEnabled()) {
			logger.info(String.format(format, arguments));
		}
	}

	public void info(Throwable t, String format, Object... arguments) {
		if (isInfoEnabled()) {
			logger.info(String.format(format, arguments), t);
		}
	}

	// -------------------------------------------------------------------------
	// Warn

	public boolean isWarnEnabled() {
		return logger.isWarnEnabled();
	}

	public void warn(String format, Object... arguments) {
		if (isWarnEnabled()) {
			logger.warn(String.format(format, arguments));
		}
	}

	public void warn(Throwable t, String format, Object... arguments) {
		if (isWarnEnabled()) {
			logger.warn(String.format(format, arguments), t);
		}
	}

	// -------------------------------------------------------------------------
	// Error

	public boolean isErrorEnabled() {
		return logger.isErrorEnabled();
	}

	public void error(String format, Object... arguments) {
		if (isErrorEnabled()) {
			logger.error(String.format(format, arguments));
		}
	}

	public void error(Throwable t, String format, Object... arguments) {
		if (isErrorEnabled()) {
			logger.warn(String.format(format, arguments), t);
		}
	}

	// -------------------------------------------------------------------------
	// Private method}
}