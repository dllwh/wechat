package com.cdeledu.core.config;

import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.core.io.ClassPathResource;

import net.sf.ehcache.CacheManager;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: EhCache配置
 * @创建者: 皇族灬战狼
 * @创建时间: 2017年12月9日 下午2:13:32
 * @版本: V1.0
 * @since: JDK 1.7
 */
// @Configuration
// @EnableCaching
public class EhCacheConfig {
	/** ----------------------------------------------------- Fields start */
	/** 缓存配置文件的路径 */
	private static String ehcache_path = "/systemConfig/applicationContext-cache.xml";

	/** ----------------------------------------------------- Fields end */

	/** ----------------------------------------------- [公共方法] */
	/**
	 * @方法描述 : EhCache的配置
	 * @param cacheManager
	 * @return
	 */
	public EhCacheCacheManager cacheManager(CacheManager cacheManager) {
		return new EhCacheCacheManager(cacheManager);
	}

	/**
	 * @方法描述 : EhCache的配置
	 * @return
	 */
	public EhCacheManagerFactoryBean ehcache() {
		EhCacheManagerFactoryBean ehCacheManagerFactoryBean = new EhCacheManagerFactoryBean();
		ehCacheManagerFactoryBean.setConfigLocation(new ClassPathResource(ehcache_path));
		return ehCacheManagerFactoryBean;
	}
	/** ----------------------------------------------- [公共方法] */

	/** ----------------------------------------------- [私有方法] */
	/** ----------------------------------------------- [私有方法] */

	/** ----------------------------------------------- [测试方法] */
	/** ----------------------------------------------- [测试方法] */
}
