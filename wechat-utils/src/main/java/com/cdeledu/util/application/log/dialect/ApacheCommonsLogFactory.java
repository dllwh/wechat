package com.cdeledu.util.application.log.dialect;

import com.cdeledu.util.application.log.Log;
import com.cdeledu.util.application.log.LogFactory;

/**
 * @类描述: Apache Commons Logging
 * @创建者: 独泪了无痕
 * @创建日期: 2016年1月23日 上午12:20:58
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class ApacheCommonsLogFactory extends LogFactory {
	public ApacheCommonsLogFactory() {
		super("Apache Common Logging");
	}

	@Override
	public Log getLog(String name) {
		return new ApacheCommonsLog(name);
	}

	@Override
	public Log getLog(Class<?> clazz) {
		return new ApacheCommonsLog(clazz);
	}
}
