package com.cdeledu.core.redis;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: Redis所有Keys
 * @创建者: 皇族灬战狼
 * @创建时间: 2017年12月18日 上午10:25:19
 * @版本: V1.0
 * @since: JDK 1.7
 */
public final class RedisKeys {	
	/**
	 * @方法:获取Redis配置的key
	 * @创建人:独泪了无痕
	 * @param key
	 * @return
	 */
	public static String getSysConfigKey(String key) {
		return "sys:config:" + key;
	}
	
	public static String getTokenKey(String key) {
		return "sys:token:" + key;
	}

	/**
	 * @方法:获取Redis Shiro配置的key
	 * @创建人:独泪了无痕
	 * @param key
	 * @return
	 */
	public static String getShiroSessionKey(String key) {
		return "sessionid:" + key;
	}
}
