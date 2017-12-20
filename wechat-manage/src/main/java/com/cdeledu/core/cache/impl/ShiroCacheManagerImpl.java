package com.cdeledu.core.cache.impl;

import org.apache.shiro.cache.Cache;

import com.cdeledu.core.cache.ShiroCacheManager;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: JRedis管理
 * @创建者: 皇族灬战狼
 * @创建时间: 2017年11月10日 下午11:24:52
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class ShiroCacheManagerImpl implements ShiroCacheManager {
	/** ----------------------------------------------------- Fields start */
	/** ----------------------------------------------------- Fields end */
	@Override
	public <K, V> Cache<K, V> getCache(String name) {
		return null;
	}

	@Override
	public void destroy() {

	}
}
