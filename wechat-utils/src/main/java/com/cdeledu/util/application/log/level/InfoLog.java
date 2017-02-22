package com.cdeledu.util.application.log.level;

/**
 * @类描述: INFO级别日志接口
 * @创建者: 独泪了无痕
 * @创建日期: 2016年1月20日 下午11:16:16
 * @版本: V1.0
 * @since: JDK 1.7
 */
public interface InfoLog {
	/**
	 * @return INFO 等级是否开启
	 */
	boolean isInfoEnabled();

	/**
	 * 打印 INFO 等级的日志
	 * 
	 * @param t
	 *            错误对象
	 */
	void info(Throwable t);

	/**
	 * 打印 INFO 等级的日志
	 * 
	 * @param format
	 *            消息模板
	 * @param arguments
	 *            参数
	 */
	void info(String format, Object... arguments);

	/**
	 * 打印 INFO 等级的日志
	 * 
	 * @param t
	 *            错误对象
	 * @param format
	 *            消息模板
	 * @param arguments
	 *            参数
	 */
	void info(Throwable t, String format, Object... arguments);
}
