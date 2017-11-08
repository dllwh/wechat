package com.cdeledu.common.shiro;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.StringUtils;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cdeledu.common.constants.FilterHelper;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 角色判断校验
 * @创建者: 皇族灬战狼
 * @创建时间: 2017年11月1日 上午11:04:55
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class RoleFilter extends AccessControlFilter {
	/** ----------------------------------------------------- Fields start */
	/**
	 * 日志对象
	 */
	protected Logger logger = LoggerFactory.getLogger(getClass());

	/** ----------------------------------------------------- Fields end */
	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response,
			Object mappedValue) throws Exception {
		if (logger.isDebugEnabled()) {
			logger.error("角色判断校验:isAccessAllowed");
		}

		String[] arra = (String[]) mappedValue;
		Subject subject = getSubject(request, response);

			for (String role : arra) {
				System.out.println(role);
				if (subject.hasRole(role)) {
					System.out.println("我才有希望了!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
					return Boolean.TRUE;
				}
			}
		return Boolean.FALSE;
	}

	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response)
			throws Exception {
		if (logger.isDebugEnabled()) {
			logger.error("角色判断校验:onAccessDenied");
		}
		Subject subject = getSubject(request, response);
		if (subject.getPrincipal() == null) {// 表示没有登录，重定向到登录页面
			saveRequest(request);
			WebUtils.issueRedirect(request, response, FilterHelper.LOGIN_ACTION);
		} else {
			// 如果有未授权页面，则跳转
			if (StringUtils.hasLength(FilterHelper.UNAUTHORIZED)) {
				WebUtils.issueRedirect(request, response, FilterHelper.UNAUTHORIZED);
			} else {
				// 否则返回401未授权状态码
				WebUtils.toHttp(response).sendError(HttpServletResponse.SC_UNAUTHORIZED);
			}
		}
		return Boolean.FALSE;
	}
}
