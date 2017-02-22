package com.cdeledu.util.application.log.level;

import java.io.Serializable;

import com.cdeledu.util.application.log.Log;


/**
 * @类描述: 抽象日志类
 * @创建者: 独泪了无痕
 * @创建日期: 2016年1月21日 下午7:55:31
 * @版本: V1.0
 * @since: JDK 1.7
 */
public abstract class AbstractLog implements Log, Serializable {
	private static final long serialVersionUID = 1L;

	public boolean isEnabled(LogLevel level) {
		switch (level) {
		case TRACE:
			return isTraceEnabled();
		case DEBUG:
			return isDebugEnabled();
		case INFO:
			return isInfoEnabled();
		case WARN:
			return isWarnEnabled();
		case ERROR:
			return isErrorEnabled();
		default:
			throw new Error();
		}
	}

	@Override
	public void log(LogLevel level, String format, Object... arguments) {
		switch (level) {
		case TRACE:
			trace(format, arguments);
			break;
		case DEBUG:
			debug(format, arguments);
			break;
		case INFO:
			info(format, arguments);
			break;
		case WARN:
			warn(format, arguments);
			break;
		case ERROR:
			error(format, arguments);
			break;
		default:
			throw new Error();
		}
	}

	@Override
	public void log(LogLevel level, Throwable t, String format, Object... arguments) {
		switch (level) {
		case TRACE:
			trace(t, format, arguments);
			break;
		case DEBUG:
			debug(t, format, arguments);
			break;
		case INFO:
			info(t, format, arguments);
			break;
		case WARN:
			warn(t, format, arguments);
			break;
		case ERROR:
			error(t, format, arguments);
			break;
		default:
			throw new Error();
		}
	}

	@Override
	public void trace(Throwable t) {
		this.trace(t.getMessage(), t);
	}

	@Override
	public void debug(Throwable t) {
		this.debug(t.getMessage(), t);
	}

	@Override
	public void info(Throwable t) {
		this.info(t.getMessage(), t);
	}

	@Override
	public void warn(Throwable t) {
		this.warn(t.getMessage(), t);
	}

	@Override
	public void error(Throwable t) {
		this.error(t.getMessage(), t);
	}
}
