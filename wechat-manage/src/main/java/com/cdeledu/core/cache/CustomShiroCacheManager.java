package com.cdeledu.core.cache;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.util.Destroyable;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance  as tomorrow newest starter!
 *
 * @类描述: TODO(这里用一句话描述这个类的作用)
 * @创建者: 皇族灬战狼
 * @创建时间: 2017年11月29日 上午11:41:47
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class CustomShiroCacheManager implements CacheManager, Destroyable {
	/** ----------------------------------------------------- Fields start */
	private ShiroCacheManager shrioCacheManager;

	public ShiroCacheManager getShrioCacheManager() {
		return shrioCacheManager;
	}

	public void setShrioCacheManager(ShiroCacheManager shrioCacheManager) {
		this.shrioCacheManager = shrioCacheManager;
	}

	/** ----------------------------------------------------- Fields end */

	/** ----------------------------------------------- [私有方法] */
	/** ----------------------------------------------- [私有方法] */
	@Override
	public <K, V> Cache<K, V> getCache(String name) throws CacheException {
		return getShrioCacheManager().getCache(name);
	}

	@Override
	public void destroy() throws Exception {
		getShrioCacheManager().destroy();
	}
}
