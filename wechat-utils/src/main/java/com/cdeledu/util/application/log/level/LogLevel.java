package com.cdeledu.util.application.log.level;

/**
 * @类描述: 日志等级
 * @创建者: 独泪了无痕
 * @创建日期: 2016年1月20日 下午10:57:48
 * @版本: V1.0
 * @since: JDK 1.7
 */
public enum LogLevel {
	/**
	 * FATAL level指出每个严重的错误事件将会导致应用程序的退出
	 */
	TRACE,
	/**
	 * DEBUG Level指出细粒度信息事件对调试应用程序是非常有帮助的
	 */
	DEBUG,
	/**
	 * INFO level表明 消息在粗粒度级别上突出强调应用程序的运行过程
	 */
	INFO,
	/**
	 * WARN level表明会出现潜在错误的情形
	 */
	WARN,
	/**
	 * ERROR level指出虽然发生错误事件，但仍然不影响系统的继续运行
	 */
	ERROR,
	/**
	 * ALL Level是最低等级的，用于打开所有日志记录
	 */
	ALL,
	/**
	 * OFF Level是最高等级的，用于关闭所有日志记录
	 */
	OFF
}
