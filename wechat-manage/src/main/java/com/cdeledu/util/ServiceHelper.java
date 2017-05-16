package com.cdeledu.util;

import com.cdeledu.common.constants.GlobalConstants;

/**
 * @类描述: 获取Spring容器中的service bean
 * @创建者: 皇族灬战狼
 * @创建时间: 2017年5月16日 下午5:11:50
 * @版本: V1.0
 * @since: JDK 1.7
 */
public final class ServiceHelper {
	/** ----------------------------------------------------- Fields start */
	/** ----------------------------------------------------- Fields end */

	/** ----------------------------------------------- [私有方法] */
	public static Object getService(String serviceName) {
		return GlobalConstants.WEB_APP_CONTEXT.getBean(serviceName);
	}
	/** ----------------------------------------------- [私有方法] */
}
