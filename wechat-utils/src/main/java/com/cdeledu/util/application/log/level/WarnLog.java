package com.cdeledu.util.application.log.level;

/**
 * @类描述: TODO(这里用一句话描述这个类的作用)
 * @创建者: 独泪了无痕
 * @创建日期: 2016年1月21日 下午7:46:28
 * @版本: V1.0
 * @since: JDK 1.7
 */
public interface WarnLog {
	/**
	 * @return WARN 等级是否开启
	 */
	boolean isWarnEnabled();

	/**
	 * 打印 WARN 等级的日志
	 * 
	 * @param t
	 *            错误对象
	 */
	void warn(Throwable t);

	/**
	 * 打印 WARN 等级的日志
	 * 
	 * @param format
	 *            消息模板
	 * @param arguments
	 *            参数
	 */
	void warn(String format, Object... arguments);

	/**
	 * 打印 WARN 等级的日志
	 * 
	 * @param t
	 *            错误对象
	 * @param format
	 *            消息模板
	 * @param arguments
	 *            参数
	 */
	void warn(Throwable t, String format, Object... arguments);
}
