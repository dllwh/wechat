package com.cdeledu.core.cache;

import org.apache.shiro.cache.Cache;

public interface ShiroCacheManager {

	<K, V> Cache<K, V> getCache(String name);

	void destroy();
}
