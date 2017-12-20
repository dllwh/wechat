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
public class RedisKeys {
	public static String getSysConfigKey(String key) {
		return "sys:config:" + key;
	}
}
