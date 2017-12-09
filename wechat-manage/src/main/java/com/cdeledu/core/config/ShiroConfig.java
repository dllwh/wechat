package com.cdeledu.core.config;

import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;

import com.cdeledu.core.shiro.ShiroRealm;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: shiro权限管理的配置
 * @创建者: 皇族灬战狼
 * @创建时间: 2017年12月9日 下午2:12:16
 * @版本: V1.0
 * @since: JDK 1.7
 */
// @Configuration
public class ShiroConfig {
	/** ----------------------------------------------------- Fields start */
	/** ----------------------------------------------------- Fields end */

	/** ----------------------------------------------- [公共方法] */
	/**
	 * @方法描述 : 安全管理器
	 * @return
	 */
	public DefaultWebSecurityManager SecurityManager(CookieRememberMeManager rememberMeManager,
			CacheManager cacheShiroManager, SessionManager sessionManager) {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		securityManager.setRealm(this.shiroRealm());
        securityManager.setCacheManager(cacheShiroManager);
        securityManager.setRememberMeManager(rememberMeManager);
        securityManager.setSessionManager(sessionManager);
		return securityManager;
	}

	/**
	 * @方法描述 : session管理器
	 * @return
	 */
	public DefaultWebSessionManager defaultWebSessionManager() {
		DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
		return sessionManager;
	}

	/**
	 * @方法描述 : 缓存管理器 使用Ehcache实现
	 * @param ehcache
	 * @return
	 */
	public CacheManager getCacheShiroManager(EhCacheManagerFactoryBean ehcache) {
		EhCacheManager ehCacheManager = new EhCacheManager();
		ehCacheManager.setCacheManager(ehcache.getObject());
		return ehCacheManager;
	}

	/**
	 * @方法描述 : 项目自定义的Realm
	 * @return
	 */
	public ShiroRealm shiroRealm() {
		return new ShiroRealm();
	}

	/**
	 * @方法描述 : rememberMe管理器
	 * @param rememberMeCookie
	 * @return
	 */
	public CookieRememberMeManager rememberMeManager(SimpleCookie rememberMeCookie) {
		CookieRememberMeManager manager = new CookieRememberMeManager();
		return manager;
	}

	/**
	 * @方法描述 : Shiro的过滤器链
	 * @param securityManager
	 * @return
	 */
	public ShiroFilterFactoryBean shiroFilter(DefaultWebSecurityManager securityManager) {
		ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
		return shiroFilter;
	}

	/**
	 * @方法描述 : Shiro生命周期处理器: 用于在实现了Initializable接口的Shiro
	 *       bean初始化时调用Initializable接口回调(例如:UserRealm) 在实现了Destroyable接口的Shiro
	 *       bean销毁时调用 Destroyable接口回调(例如:DefaultSecurityManager)
	 * @return
	 */
	public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
		return new LifecycleBeanPostProcessor();
	}

	/**
	 * 启用shrio授权注解拦截方式，AOP式方法级权限检查
	 */
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(
			DefaultWebSecurityManager securityManager) {
		AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
		authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
		return authorizationAttributeSourceAdvisor;
	}
	/** ----------------------------------------------- [公共方法] */

	/** ----------------------------------------------- [私有方法] */
	/** ----------------------------------------------- [私有方法] */

	/** ----------------------------------------------- [测试方法] */
	/** ----------------------------------------------- [测试方法] */
}
