package com.cdeledu.util.application.log.dialect;

import com.cdeledu.util.application.log.Log;
import com.cdeledu.util.application.log.LogFactory;

/**
 * @类描述: TODO(这里用一句话描述这个类的作用)
 * @创建者: 独泪了无痕
 * @创建日期: 2016年1月23日 上午12:17:35
 * @版本: V1.0
 * @since: JDK 1.7
 * @see <a href="">TODO(连接内容简介)</a>
 */
public class Log4jLogFactory extends LogFactory {
	public Log4jLogFactory() {
		super("Log4j");
	}

	@Override
	public Log getLog(String name) {
		return new Log4jLog(name);
	}

	@Override
	public Log getLog(Class<?> clazz) {
		return new Log4jLog(clazz);
	}
}
