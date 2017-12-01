package com.cdeledu.core.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.cdeledu.common.base.BaseClass;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: mongodb
 * @创建者: 皇族灬战狼
 * @创建时间: 2017年3月18日 下午5:05:07
 * @版本: V1.2
 * @since: JDK 1.7
 */
@WebListener
public class MongoInitListener extends BaseClass implements ServletContextListener {
	/** ----------------------------------------------------- Fields start */
	private static final long serialVersionUID = 1L;

	/** ----------------------------------------------------- Fields end */

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		if (logger.isDebugEnabled()) {
			logger.info("mongodb 存储");
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {

	}
}
