package com.cdeledu.common.base;

import java.util.List;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 通用缓存接口
 * @创建者: 皇族灬战狼
 * @联系方式: duleilewuhen@sina.com
 * @创建时间: 2017年12月23日 上午11:43:46
 * @版本: V1.0
 * @since: JDK 1.7
 */
public interface BaseCacheFactory {
	void put(String cacheName, Object key, Object value);

	<T> T get(String cacheName, Object key);

	<E> List<E> getKeys(String cacheName);

	void remove(String cacheName, Object key);

	void removeAll(String cacheName);

}
