package com.cdeledu.util.application.log.level;

/**
 * @类描述: ERROR级别日志接口
 * @创建者: 独泪了无痕
 * @创建日期: 2016年1月20日 下午11:15:08
 * @版本: V1.0
 * @since: JDK 1.7
 */
public interface ErrorLog {
	/**
	 * @return ERROR 等级是否开启
	 */
	boolean isErrorEnabled();

	/**
	 * 打印 ERROR 等级的日志
	 * 
	 * @param t
	 *            错误对象
	 */
	void error(Throwable t);

	/**
	 * 打印 ERROR 等级的日志
	 * 
	 * @param format
	 *            消息模板
	 * @param arguments
	 *            参数
	 */
	void error(String format, Object... arguments);

	/**
	 * 打印 ERROR 等级的日志
	 * 
	 * @param t
	 *            错误对象
	 * @param format
	 *            消息模板
	 * @param arguments
	 *            参数
	 */
	void error(Throwable t, String format, Object... arguments);
}
