package com.cdeledu.core.shiro.filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.StringUtils;
import org.apache.shiro.web.filter.authz.RolesAuthorizationFilter;
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
 * @版本: V2.1
 * @since: JDK 1.7
 */
public class RoleFilter extends RolesAuthorizationFilter {
	/** ----------------------------------------------------- Fields start */
	/**
	 * 日志对象
	 */
	protected Logger logger = LoggerFactory.getLogger(getClass());

	/** ----------------------------------------------------- Fields end */
	public boolean isAccessAllowed(ServletRequest request, ServletResponse response,
			Object mappedValue) {
		if (logger.isDebugEnabled()) {
			logger.error("角色判断校验:isAccessAllowed");
		}

		String[] arra = (String[]) mappedValue;
		Subject subject = getSubject(request, response);

		for (String role : arra) {
			if (subject.hasRole(role)) {
				return Boolean.TRUE;
			}
		}
		
		if (FilterHelper.isAjax(request)) {
			Map<String, Object> resultMap = new HashMap<String, Object>();
			if (logger.isDebugEnabled()) {
				logger.debug("当前用户的角色没有操作权限，并且是Ajax请求！");
			}
			resultMap.put("success", false);
			resultMap.put("msg", "操作失败:您没有权限");
			FilterHelper.out(response, resultMap);
			return Boolean.FALSE;
		}
		return Boolean.FALSE;
	}

	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response)
			throws IOException {
		if (logger.isDebugEnabled()) {
			logger.error("角色判断校验:onAccessDenied");
		}
		Subject subject = getSubject(request, response);
		if (subject.getPrincipal() == null) {// 表示没有登录，重定向到登录页面
			saveRequest(request);
			WebUtils.issueRedirect(request, response, FilterHelper.LOGIN_ACTION);
		} else {
			// 如果有未授权页面，则跳转
			if (StringUtils.hasLength(getUnauthorizedUrl())) {
				WebUtils.issueRedirect(request, response, getUnauthorizedUrl());
			} else {
				// 否则返回401未授权状态码
				WebUtils.toHttp(response).sendError(HttpServletResponse.SC_UNAUTHORIZED);
			}
		}
		return Boolean.FALSE;
	}
}
