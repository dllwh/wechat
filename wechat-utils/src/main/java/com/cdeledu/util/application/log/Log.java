package com.cdeledu.util.application.log;

import com.cdeledu.util.application.log.level.DebugLog;
import com.cdeledu.util.application.log.level.ErrorLog;
import com.cdeledu.util.application.log.level.InfoLog;
import com.cdeledu.util.application.log.level.LogLevel;
import com.cdeledu.util.application.log.level.TraceLog;
import com.cdeledu.util.application.log.level.WarnLog;

/**
 * @类描述: 日志统一接口
 * @创建者: 独泪了无痕
 * @创建日期: 2016年1月21日 下午7:49:24
 * @版本: V1.0
 * @since: JDK 1.7
 */
public interface Log extends TraceLog, DebugLog, InfoLog, WarnLog, ErrorLog {
	/**
	 * @return 日志对象的Name
	 */
	public String getName();

	/**
	 * 是否开启指定日志
	 * 
	 * @param level
	 *            日志级别
	 * @return 是否开启指定级别
	 */
	boolean isEnabled(LogLevel level);

	/**
	 * 打印指定级别的日志
	 * 
	 * @param level
	 *            级别
	 * @param format
	 *            消息模板
	 * @param arguments
	 *            参数
	 */
	void log(LogLevel level, String format, Object... arguments);

	/**
	 * 打印 指定级别的日志
	 * 
	 * @param level
	 *            级别
	 * @param t
	 *            错误对象
	 * @param format
	 *            消息模板
	 * @param arguments
	 *            参数
	 */
	void log(LogLevel level, Throwable t, String format, Object... arguments);
}
