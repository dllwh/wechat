package com.cdeledu.util.application.log.level;

/**
 * @类描述: TRACE级别日志接口
 * @创建者: 独泪了无痕
 * @创建日期: 2016年1月21日 下午7:45:31
 * @版本: V1.0
 * @since: JDK 1.7
 */
public interface TraceLog {
	/**
	 * @return TRACE 等级是否开启
	 */
	boolean isTraceEnabled();

	/**
	 * 打印 TRACE 等级的日志
	 * 
	 * @param t
	 *            错误对象
	 */
	void trace(Throwable t);

	/**
	 * 打印 TRACE 等级的日志
	 * 
	 * @param format
	 *            消息模板
	 * @param arguments
	 *            参数
	 */
	void trace(String format, Object... arguments);

	/**
	 * 打印 TRACE 等级的日志
	 * 
	 * @param t
	 *            错误对象
	 * @param format
	 *            消息模板
	 * @param arguments
	 *            参数
	 */
	void trace(Throwable t, String format, Object... arguments);
}
