package com.cdeledu.util.application;

/**
 * @类描述: 命令行工具方法类
 * @创建者: 皇族灬战狼
 * @创建时间: 2017年4月7日 下午7:29:18
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class ConsoleHelper {
	/** ----------------------------------------------------- Fields start */
	/** ----------------------------------------------------- Fields end */

	/** ----------------------------------------------- [私有方法] */
	/** ----------------------------------------------- [私有方法] */
	/**
	 * @方法描述: 同 System.out.println()方法，打印控制台日志
	 * @param template
	 *            template 文本模板，被替换的部分用 {} 表示
	 * @param values
	 */
	public static void log(String template, Object... values) {
		System.out.println(String.format(template, values));
	}

	/**
	 * @方法描述: 同 System.err.println()方法，打印控制台日志
	 * @param template
	 *            文本模板，被替换的部分用 {} 表示
	 * @param values
	 */
	public static void error(String template, Object... values) {
		System.err.println(String.format(template, values));
	}
}
