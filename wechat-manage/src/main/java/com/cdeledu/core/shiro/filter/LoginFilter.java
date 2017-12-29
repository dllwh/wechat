package com.cdeledu.core.shiro.filter;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.web.filter.AccessControlFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cdeledu.common.constants.FilterHelper;
import com.cdeledu.core.shiro.token.ShiroHelper;
import com.cdeledu.model.rbac.SysUser;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 登录判断
 * @创建者: 皇族灬战狼
 * @创建时间: 2017年11月1日 上午10:48:51
 * @版本: V1.1
 * @since: JDK 1.7
 */
public class LoginFilter extends AccessControlFilter {
	/** ----------------------------------------------------- Fields start */
	/** 日志对象 */
	protected Logger logger = LoggerFactory.getLogger(getClass());

	/** ----------------------------------------------------- Fields end */
	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response,
			Object mappedValue) throws Exception {
		SysUser token = ShiroHelper.getPrincipal();
		if (token != null || isLoginRequest(request, response)) {
			return Boolean.TRUE;
		}
		if (FilterHelper.isAjax(request)) {
			Map<String, Object> resultMap = new HashMap<String, Object>();
			if (logger.isDebugEnabled()) {
				logger.debug("当前用户没有登录，并且是Ajax请求！");
			}
			HttpServletResponse httpresponse = (HttpServletResponse) response; 
			httpresponse.setHeader("sessionstatus", "timeout"); 
			resultMap.put("success", false);
			resultMap.put("resultCode", 10001);
			resultMap.put("msg", "当前用户没有登录，需要重新登录");
			FilterHelper.out(response, resultMap);
			return Boolean.FALSE;

		}
		return Boolean.FALSE;
	}

	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response)
			throws Exception {
		// 保存Request和Response 到登录后的链接
		logger.error("*************************************保存Request和Response 到登录后的链接");
		if (!FilterHelper.isAjax(request)) {
			saveRequestAndRedirectToLogin(request, response);
		}
		return Boolean.FALSE;
	}
}
