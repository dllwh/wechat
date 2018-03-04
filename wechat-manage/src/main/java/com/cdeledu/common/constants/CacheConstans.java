package com.cdeledu.common.constants;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 与缓存相关的常量:为了不和其他的缓存混淆，采用追加前缀方式以作区分
 * @创建者: 皇族灬战狼
 * @创建时间: 2017年11月29日 下午12:02:40
 * @版本: V1.0
 * @since: JDK 1.7
 */
public final class CacheConstans {
	/** shiro 缓存的前缀 */
	public static final String REDIS_SHIRO_CACHE = "redis_shiro-cache:";
	/** Redis session Key 的前缀 */
	public static final String REDIS_SHIRO_SESSION = "redis_shiro-session:";
	/** session status */
	public static final String SESSION_STATUS = "session-online-status";
	/** Redis cache 名 */
	public static final String SHIRO_CACHE = "shiro-cache";
	public static final String CACHE_MANAGER = "cacheManager";
}
