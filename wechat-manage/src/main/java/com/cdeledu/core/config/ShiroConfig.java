package com.cdeledu.core.config;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.Filter;

import org.apache.shiro.session.SessionListener;
import org.apache.shiro.session.mgt.ExecutorServiceSessionValidationScheduler;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;

import com.beust.jcommander.internal.Lists;
import com.cdeledu.common.constants.FilterHelper;
import com.cdeledu.core.shiro.ShiroRealm;
import com.cdeledu.core.shiro.filter.KickoutSessionFilter;
import com.cdeledu.core.shiro.filter.LoginFilter;
import com.cdeledu.core.shiro.filter.PermissionFilter;
import com.cdeledu.core.shiro.filter.RoleFilter;
import com.cdeledu.core.shiro.filter.UserSessionFilter;
import com.cdeledu.core.shiro.listener.CustomSessionListener;

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
	/** session的失效时长，设置为2个小时 */
	private final static long TIMEOUT = 72000000L;
	/** 定时清理失效session,设置为半小时 */
	private final static long INTERVAL = 1800000L;

	/** ----------------------------------------------------- Fields end */

	/**
	 * @方法描述 :
	 * 
	 *       <pre>
	 *  Shiro主过滤器本身功能十分强大,其强大之处就在于它支持任何基于URL路径表达式的、自定义的过滤器的执行
	 *  Web应用中,Shiro可控制的Web请求必须经过Shiro主过滤器的拦截,Shiro对基于Spring的Web应用提供了完美的支持
	 *       </pre>
	 * 
	 * @return
	 */
	//@Bean("shiroFilter")
	public ShiroFilterFactoryBean shiroFilter() {
		ShiroFilterFactoryBean shiroFilterFactory = new ShiroFilterFactoryBean();
		// Shiro的核心安全接口,这个属性是必须的
		shiroFilterFactory.setSecurityManager(securityManager());
		// 要求登录时的链接(可根据项目的URL进行替换),非必须的属性,如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
		shiroFilterFactory.setLoginUrl(FilterHelper.LOGIN_ACTION);
		// 登录成功后要跳转的链接
		shiroFilterFactory.setSuccessUrl(FilterHelper.LOGIN_ACTION);
		// 用户访问未对其授权的资源时,所显示的连接;
		shiroFilterFactory.setUnauthorizedUrl(FilterHelper.UNAUTHORIZED);
		/**
		 * 自定义拦截器
		 */
		Map<String, Filter> filtersMap = new LinkedHashMap<String, Filter>();
		filtersMap.put("loginFilter", new LoginFilter());// 登录校验
		filtersMap.put("roleFilter", new RoleFilter());// 角色判断校验
		filtersMap.put("permissionFilter", new PermissionFilter());// 权限校验
		filtersMap.put("kickoutFilter", kickoutSessionFilter());
		filtersMap.put("userSessionFilter", new UserSessionFilter());// 用户session
		shiroFilterFactory.setFilters(filtersMap);
		/**
		 * Shiro连接约束配置,即权限控制map.
		 * 第一个'/'代表的路径是相对于HttpServletRequest.getContextPath()的值来的
		 */
		Map<String, String> filterMap = new LinkedHashMap<String, String>();
		filterMap.put("/loginController**", "anon");
		filterMap.put("/sysPage/**", "anon,kickoutFilter");
		filterMap.put("/homeController/**", "loginFilter");
		filterMap.put("/dataSourceController**", "authc,roleFilter[administrator]");
		filterMap.put("/**", "authc,loginFilter,permissionFilter,kickoutFilter");
		shiroFilterFactory.setFilterChainDefinitionMap(filterMap);
		return shiroFilterFactory;
	}

	/**
	 * @方法描述 : Shiro安全管理配置
	 * @return
	 */
	//@Bean("securityManager")
	public DefaultWebSecurityManager securityManager() {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		// 自定义realm
		securityManager.setRealm(shiroRealm());
		// 自定义缓存实现 使用redis
		// securityManager.setCacheManager(cacheManager());
		// securityManager.setRememberMeManager(rememberMeManager);
		// 自定义session管理 使用redis
		securityManager.setSessionManager(sessionManager());
		return securityManager;
	}

	/**
	 * @方法描述 : session管理器
	 * @return
	 */
	//@Bean("sessionManager")
	public DefaultWebSessionManager sessionManager() {
		DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
		// session的失效时长，默认是30 分钟((1800000)),该值以毫秒为时间单位
		sessionManager.setGlobalSessionTimeout(TIMEOUT);
		// 是否在会话过期后会调用SessionDAO的delete方法删除会话,默认true
		sessionManager.setDeleteInvalidSessions(true);
		// 间隔多少时间检查，不配置是60分钟
		sessionManager.setSessionValidationScheduler(sessionValidationScheduler());
		// 是否开启session验证检测
		sessionManager.setSessionValidationSchedulerEnabled(true);
		// 定时清理失效session , 清理用户直接关闭浏览器造成的孤立会话 :默认每小时检测一次
		sessionManager.setSessionValidationInterval(INTERVAL);
		// 是否启用/禁用，默认是启用的；如果禁用后将不会设置Session Id Cookie，即默认使用了Servlet容器的JSESSIONID
		sessionManager.setSessionIdCookieEnabled(true);
		sessionManager.setSessionIdCookie(sessionIdCookie());
		// sessionManager.setSessionIdUrlRewritingEnabled(false);
		// sessionManager.setSessionDAO(sessionDAO);
		// session 监听
		Collection<SessionListener> sessionListeners = Lists.newArrayList();
		sessionListeners.add(new CustomSessionListener());
		sessionManager.setSessionListeners(sessionListeners);
		return sessionManager;
	}

	/**
	 * @方法描述 :
	 * 
	 *       <pre>
	 * shiro于数据交互的类 ，继承自AuthorizingRealm的自定义Realm,即指定Shiro验证用户登录的类为自定义实现
	 *       </pre>
	 * 
	 * @return
	 */
	//@Bean(name="shiroRealm")
	public ShiroRealm shiroRealm() {
		return new ShiroRealm();
	}

	/**
	 * @方法描述 : rememberMe管理器
	 * @param rememberMeCookie
	 * @return
	 */
	//@Bean
	public CookieRememberMeManager rememberMeManager() {
		CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
		cookieRememberMeManager.setCookie(rememberMeCookie());
		// 加密的密钥
		// cookieRememberMeManager.setCipherKey();
		return cookieRememberMeManager;
	}

	/**
	 * @方法描述 : Shiro生命周期处理器: 用于在实现了Initializable接口的Shiro
	 *       bean初始化时调用Initializable接口回调(例如:UserRealm) 在实现了Destroyable接口的Shiro
	 *       bean销毁时调用 Destroyable接口回调(例如:DefaultSecurityManager)
	 * @return
	 */
	//@Bean("lifecycleBeanPostProcessor")
	public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
		return new LifecycleBeanPostProcessor();
	}

	//@Bean
	// @DependsOn(value = "lifecycleBeanPostProcessor") //依赖其他bean的初始化
	public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
		DefaultAdvisorAutoProxyCreator proxyCreator = new DefaultAdvisorAutoProxyCreator();
		proxyCreator.setProxyTargetClass(true);
		return proxyCreator;
	}

	/**
	 * 启用shrio授权注解拦截方式，AOP式方法级权限检查
	 */
	//@Bean
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(
			DefaultWebSecurityManager securityManager) {
		AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
		advisor.setSecurityManager(securityManager);
		return advisor;
	}

	/** ----------------------------------------------- [公共方法] */

	/** ----------------------------------------------- [私有方法] */
	/**
	 * @方法描述 : sessionIdCookie的实现,用于重写覆盖容器默认的JSESSIONID
	 * @return
	 */
	public SimpleCookie sessionIdCookie() {
		SimpleCookie cookie = new SimpleCookie();
		cookie.setName("shiro.sesssionCookie");
		// 设置Cookie的路径，默认空，即存储在域名根下
		cookie.setPath("/");
		// 设置Cookie的过期时间，秒为单位，默认-1表示关闭浏览器时过期Cookie
		cookie.setMaxAge(7200);
		// 如果设置为true，则客户端不会暴露给客户端脚本代码，使用HttpOnly cookie有助于减少某些类型的跨站点脚本攻击
		cookie.setHttpOnly(true);
		return cookie;
	}

	/**
	 * @方法描述 : 记住我
	 * @return
	 */
	public SimpleCookie rememberMeCookie() {
		// 这个参数是cookie的名称，对应前端的checkbox的name = rememberMe
		SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
		// 记住我cookie生效时间30天 ,单位秒;
		simpleCookie.setMaxAge(2592000);
		return simpleCookie;
	}

	/**
	 * @方法描述 : 限制同一账号登录同时登录人数控制
	 * @return
	 */
	public KickoutSessionFilter kickoutSessionFilter() {
		KickoutSessionFilter kickoutSessionFilter = new KickoutSessionFilter();
		// 使用cacheManager获取相应的cache来缓存用户登录的会话；用于保存用户—会话之间的关系的；
		// 这里我们还是用之前shiro使用的redisManager()实现的cacheManager()缓存管理
		// 也可以重新另写一个，重新配置缓存时间之类的自定义缓存属性
		// kickoutSessionFilter.setCacheManager(cacheManager());
		// 用于根据会话ID，获取会话进行踢出操作的；
		// kickoutSessionFilter.setSessionManager(sessionManager());
		// 是否踢出后来登录的，默认是false；即后者登录的用户踢出前者登录的用户；踢出顺序。
		kickoutSessionFilter.setKickoutAfter(false);
		// 同一个用户最大的会话数，默认1；比如2的意思是同一个用户允许最多同时两个人登录；
		kickoutSessionFilter.setMaxSession(1);
		// 被踢出后重定向到的地址；
		kickoutSessionFilter.setKickoutUrl(FilterHelper.KICKED_OUT);
		return kickoutSessionFilter;
	}

	/**
	 * @方法描述 : 会话验证调度器
	 * @return
	 */
	public ExecutorServiceSessionValidationScheduler sessionValidationScheduler() {
		ExecutorServiceSessionValidationScheduler scheduler = new ExecutorServiceSessionValidationScheduler();
		scheduler.setInterval(INTERVAL);
		scheduler.setSessionManager(sessionManager());
		return scheduler;
	}
}