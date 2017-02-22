package com.cdeledu.common.baseInterface;

public interface Cache<T> {
	/**
	 * @方法描述: 获取缓存中的数据
	 * @param key
	 * @return
	 */
	T get(String key);

	/**
	 * @方法描述: 把数据放入缓存 如果存在与key对应的值，则返回失败
	 * @param key
	 * @param value
	 * @return
	 */
	boolean add(String key, T value);

	/**
	 * @方法描述: 把数据放入缓存 如果存在与key对应的值，则覆盖原有的值
	 * @param key
	 * @param value
	 * @return
	 */
	boolean set(String key, T value);

	/**
	 * @方法描述: 缓存更新 如果不存在与key对应的缓存值，则不更新
	 * @param key
	 * @param value
	 * @return
	 */
	boolean update(String key, T value);

	/**
	 * @方法描述: 删除缓存
	 * @param key
	 * @return
	 */
	boolean delete(String key);
}
