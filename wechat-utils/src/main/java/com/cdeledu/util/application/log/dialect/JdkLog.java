package com.cdeledu.util.application.log.dialect;

import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

import com.cdeledu.util.application.log.level.AbstractLog;

/**
 * @类描述: TODO(这里用一句话描述这个类的作用)
 * @创建者: 独泪了无痕
 * @创建日期: 2016年1月21日 下午8:11:44
 * @版本: V1.0
 * @since: JDK 1.7
 * @see <a href="http://java.sun.com/javase/7/docs/technotes/guides/logging/index.html">java.util.logging</a>
 */
public class JdkLog extends AbstractLog {
	private static final long serialVersionUID = 1L;
	private final transient Logger logger;

	// ------------------------------------------------------------------------- Constructor
	public JdkLog(Logger logger) {
		this.logger = logger;
	}

	public JdkLog(Class<?> clazz) {
		this(clazz.getName());
	}

	public JdkLog(String name) {
		this(Logger.getLogger(name));
	}

	@Override
	public String getName() {
		return logger.getName();
	}
	// ------------------------------------------------------------------------- Trace


	@Override
	public boolean isTraceEnabled() {
		return logger.isLoggable(Level.FINEST);
	}

	@Override
	public void trace(String format, Object... arguments) {
		if (isTraceEnabled()) {
			log(SELF, Level.FINEST, String.format(format, arguments), null);
		}
	}

	@Override
	public void trace(Throwable t, String format, Object... arguments) {
		if (isTraceEnabled()) {
			log(SELF, Level.FINEST, String.format(format, arguments), t);
		}
	}

	// ------------------------------------------------------------------------- Debug


	@Override
	public boolean isDebugEnabled() {
		return logger.isLoggable(Level.FINE);
	}

	@Override
	public void debug(String format, Object... arguments) {
		if (isDebugEnabled()) {
			log(SELF, Level.FINE, String.format(format, arguments), null);
		}
	}

	@Override
	public void debug(Throwable t, String format, Object... arguments) {
		if (isDebugEnabled()) {
			log(SELF, Level.FINE, String.format(format, arguments), t);
		}
	}

	// ------------------------------------------------------------------------- Info


	@Override
	public boolean isInfoEnabled() {
		return logger.isLoggable(Level.INFO);
	}

	@Override
	public void info(String format, Object... arguments) {
		if (isInfoEnabled()) {
			log(SELF, Level.INFO, String.format(format, arguments), null);
		}
	}

	@Override
	public void info(Throwable t, String format, Object... arguments) {
		if (isInfoEnabled()) {
			log(SELF, Level.INFO, String.format(format, arguments), null);
		}
	}

	// ------------------------------------------------------------------------- Warn


	@Override
	public boolean isWarnEnabled() {
		return logger.isLoggable(Level.WARNING);
	}

	@Override
	public void warn(String format, Object... arguments) {
		if (isWarnEnabled()) {
			log(SELF, Level.WARNING, String.format(format, arguments), null);
		}
	}

	@Override
	public void warn(Throwable t, String format, Object... arguments) {
		if (isWarnEnabled()) {
			log(SELF, Level.WARNING, String.format(format, arguments), t);
		}
	}

	// ------------------------------------------------------------------------- Error


	@Override
	public boolean isErrorEnabled() {
		return logger.isLoggable(Level.SEVERE);
	}

	@Override
	public void error(String format, Object... arguments) {
		if (isErrorEnabled()) {
			log(SELF, Level.SEVERE, String.format(format, arguments), null);
		}
	}

	@Override
	public void error(Throwable t, String format, Object... arguments) {
		if (isErrorEnabled()) {
			log(SELF, Level.SEVERE, String.format(format, arguments), t);
		}
	}
	// ------------------------------------------------------------------------- Private method


	/**
	 * 打印对应等级的日志
	 * 
	 * @param callerFQCN
	 * @param level 等级
	 * @param msg 消息
	 * @param t
	 */
	private void log(String callerFQCN, Level level, String msg, Throwable t) {
		LogRecord record = new LogRecord(level, msg);
		record.setLoggerName(getName());
		record.setThrown(t);
		fillCallerData(callerFQCN, record);
		logger.log(record);
	}

	private static final String SELF = JdkLog.class.getName();
	private static final String SUPER = JdkLog.class.getSuperclass().getName();

	/**
	 * Fill in caller data if possible.
	 *
	 * @param callerFQCN 调用者
	 * @param record The record to update
	 */
	private static void fillCallerData(String callerFQCN, LogRecord record) {
		StackTraceElement[] steArray = new Throwable().getStackTrace();

		int selfIndex = -1;
		for (int i = 0; i < steArray.length; i++) {
			final String className = steArray[i].getClassName();
			if (className.equals(callerFQCN) || className.equals(SUPER)) {
				selfIndex = i;
				break;
			}
		}

		int found = -1;
		for (int i = selfIndex + 1; i < steArray.length; i++) {
			final String className = steArray[i].getClassName();
			if (!(className.equals(callerFQCN) || className.equals(SUPER))) {
				found = i;
				break;
			}
		}

		if (found != -1) {
			StackTraceElement ste = steArray[found];
			record.setSourceClassName(ste.getClassName());
			record.setSourceMethodName(ste.getMethodName());
		}
	}
}
