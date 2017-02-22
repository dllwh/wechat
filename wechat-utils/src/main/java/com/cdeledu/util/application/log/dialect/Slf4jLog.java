package com.cdeledu.util.application.log.dialect;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cdeledu.util.application.log.level.AbstractLog;

/**
 * @类描述:
 * @创建者: 独泪了无痕
 * @创建日期: 2016年1月23日 上午12:28:56
 * @版本: V1.0
 * @since: JDK 1.7
 * @see * <a href="http://www.slf4j.org/">SLF4J</a> log.<br>
 *      同样无缝支持 <a href="http://logback.qos.ch/">LogBack</a>
 */
public class Slf4jLog extends AbstractLog {
	private static final long serialVersionUID = 1L;
	private final transient Logger logger;

	// -------------------------------------------------------------------------
	// Constructor

	public Slf4jLog(Logger logger) {
		this.logger = logger;
	}

	public Slf4jLog(Class<?> clazz) {
		this(LoggerFactory.getLogger(clazz));
	}

	public Slf4jLog(String name) {
		this(LoggerFactory.getLogger(name));
	}

	@Override
	public String getName() {
		return logger.getName();
	}

	// -------------------------------------------------------------------------
	// Trace

	@Override
	public boolean isTraceEnabled() {
		return logger.isTraceEnabled();
	}

	@Override
	public void trace(String format, Object... arguments) {
		logger.trace(format, arguments);
	}

	@Override
	public void trace(Throwable t, String format, Object... arguments) {
		logger.trace(String.format(format, arguments), t);
	}

	// -------------------------------------------------------------------------
	// Debug

	@Override
	public boolean isDebugEnabled() {
		return logger.isDebugEnabled();
	}

	@Override
	public void debug(String format, Object... arguments) {
		logger.debug(format, arguments);
	}

	@Override
	public void debug(Throwable t, String format, Object... arguments) {
		logger.debug(String.format(format, arguments), t);
	}

	// -------------------------------------------------------------------------
	// Info

	@Override
	public boolean isInfoEnabled() {
		return logger.isInfoEnabled();
	}

	@Override
	public void info(String format, Object... arguments) {
		logger.info(format, arguments);
	}

	@Override
	public void info(Throwable t, String format, Object... arguments) {
		logger.info(String.format(format, arguments), t);
	}

	// -------------------------------------------------------------------------
	// Warn

	@Override
	public boolean isWarnEnabled() {
		return logger.isWarnEnabled();
	}

	@Override
	public void warn(String format, Object... arguments) {
		logger.warn(format, arguments);
	}

	@Override
	public void warn(Throwable t, String format, Object... arguments) {
		logger.warn(String.format(format, arguments), t);
	}

	// -------------------------------------------------------------------------
	// Error

	@Override
	public boolean isErrorEnabled() {
		return logger.isErrorEnabled();
	}

	@Override
	public void error(String format, Object... arguments) {
		logger.error(format, arguments);
	}

	@Override
	public void error(Throwable t, String format, Object... arguments) {
		logger.error(String.format(format, arguments), t);
	}
}
