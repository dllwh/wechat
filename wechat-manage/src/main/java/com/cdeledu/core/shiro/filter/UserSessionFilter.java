package com.cdeledu.core.shiro.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.web.filter.AccessControlFilter;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 在需要用户登录权限的地方添加一个过滤器:
 * 
 *       <pre>
 * 判断如果用户是登录状态,并且session里的用户对象为空,则去数据库中查询用户对象放入session中
 *       </pre>
 * 
 * @创建者: 皇族灬战狼
 * @创建时间: 2017年11月29日 上午9:51:51
 * @版本: V1.0	
 * @since: JDK 1.7
 */

public class UserSessionFilter extends AccessControlFilter {
	/** ----------------------------------------------------- Fields start */
	/** ----------------------------------------------------- Fields end */
	@Override
	protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
		return Boolean.TRUE;
	}

	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response,
			Object mappedValue) throws Exception {
		return Boolean.TRUE;
	}

	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response)
			throws Exception {
		return Boolean.TRUE;
	}

}
