package com.cdeledu.util.application.log.dialect;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.cdeledu.util.application.log.level.AbstractLog;

/**
 * @类描述:
 * @创建者: 独泪了无痕
 * @创建日期: 2016年1月23日 上午12:13:53
 * @版本: V1.0
 * @since: JDK 1.7
 * @see <a href="http://logging.apache.org/log4j/1.2/index.html">Apache
 *      Log4JF</a>
 */
public class Log4jLog extends AbstractLog {
	private static final long serialVersionUID = 1L;
	private final transient Logger logger;

	// -------------------------------------------------------------------------
	// Constructor

	public Log4jLog(Logger logger) {
		this.logger = logger;
	}

	public Log4jLog(Class<?> clazz) {
		this(Logger.getLogger(clazz));
	}

	public Log4jLog(String name) {
		this(Logger.getLogger(name));
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
		if (isTraceEnabled()) {
			logger.trace(String.format(format, arguments));
		}
	}

	@Override
	public void trace(Throwable t, String format, Object... arguments) {
		if (isTraceEnabled()) {
			logger.trace(String.format(format, arguments), t);
		}
	}

	// -------------------------------------------------------------------------
	// Debug

	@Override
	public boolean isDebugEnabled() {
		return logger.isDebugEnabled();
	}

	@Override
	public void debug(String format, Object... arguments) {
		if (isDebugEnabled()) {
			logger.debug(String.format(format, arguments));
		}
	}

	@Override
	public void debug(Throwable t, String format, Object... arguments) {
		if (isDebugEnabled()) {
			logger.debug(String.format(format, arguments), t);
		}
	}

	// -------------------------------------------------------------------------
	// Info

	@Override
	public boolean isInfoEnabled() {
		return logger.isInfoEnabled();
	}

	@Override
	public void info(String format, Object... arguments) {
		if (isInfoEnabled()) {
			logger.info(String.format(format, arguments));
		}
	}

	@Override
	public void info(Throwable t, String format, Object... arguments) {
		if (isInfoEnabled()) {
			logger.info(String.format(format, arguments), t);
		}
	}

	// -------------------------------------------------------------------------
	// Warn

	@Override
	public boolean isWarnEnabled() {
		return logger.isEnabledFor(Level.WARN);
	}

	@Override
	public void warn(String format, Object... arguments) {
		if (isWarnEnabled()) {
			logger.warn(String.format(format, arguments));
		}
	}

	@Override
	public void warn(Throwable t, String format, Object... arguments) {
		if (isWarnEnabled()) {
			logger.warn(String.format(format, arguments), t);
		}
	}

	// -------------------------------------------------------------------------
	// Error

	@Override
	public boolean isErrorEnabled() {
		return logger.isEnabledFor(Level.ERROR);
	}

	@Override
	public void error(String format, Object... arguments) {
		if (isErrorEnabled()) {
			logger.error(String.format(format, arguments));
		}
	}

	@Override
	public void error(Throwable t, String format, Object... arguments) {
		if (isErrorEnabled()) {
			logger.warn(String.format(format, arguments), t);
		}
	}

	// -------------------------------------------------------------------------
	// Private method
}
