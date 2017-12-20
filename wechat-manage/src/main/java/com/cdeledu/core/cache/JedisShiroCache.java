package com.cdeledu.core.cache;

import java.util.Collection;
import java.util.Set;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;

import com.cdeledu.common.base.BaseClass;

public class JedisShiroCache<V, K> extends BaseClass implements Cache<K, V> {
	/** ----------------------------------------------------- Fields start */
	private static final long serialVersionUID = 1L;

	/** ----------------------------------------------------- Fields end */

	@Override
	public V get(K key) throws CacheException {
		if (key != null) {

		}
		return null;
	}

	@Override
	public V put(K key, V value) throws CacheException {
		return null;
	}

	@Override
	public V remove(K key) throws CacheException {
		return null;
	}

	@Override
	public void clear() throws CacheException {
	}

	@Override
	public int size() {
		return 0;
	}

	@Override
	public Set<K> keys() {
		return null;
	}

	@Override
	public Collection<V> values() {
		return null;
	}
}
