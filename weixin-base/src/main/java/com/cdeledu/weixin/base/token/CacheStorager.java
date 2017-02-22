package com.cdeledu.weixin.base.token;

import com.cdeledu.weixin.base.exception.WeixinException;

/**
 * @类描述: cache存储
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建日期: 2016年4月10日 上午10:26:36
 * @版本: V1.0
 * @since: JDK 1.7
 */
public interface CacheStorager<T> {
	/**
	 * @方法:查找缓存中的对象
	 * @创建人:独泪了无痕
	 * @param cacheKey
	 *            缓存key
	 * @return 缓存对象
	 * @throws WeixinException
	 */
	T lookup(String cacheKey) throws WeixinException;

	/**
	 * @方法:缓存新的对象
	 * @创建人:独泪了无痕
	 * @param cacheKey
	 *            缓存key
	 * @param t
	 *            将要缓存的对象
	 * @throws WeixinException
	 */
	void caching(String cacheKey, T t) throws WeixinException;

	/**
	 * @方法:移除缓存对象
	 * @创建人:独泪了无痕
	 * @param cacheKey
	 *            缓存key
	 * @return 移除的对象
	 * @throws WeixinException
	 */
	T evict(String cacheKey) throws WeixinException;

	/**
	 * @方法: 清除所有缓存对象(<font color="red">请慎重</font>)
	 * @创建人:独泪了无痕
	 * @throws WeixinException
	 */
	void clear() throws WeixinException;
}
