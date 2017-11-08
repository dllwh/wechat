package com.cdeledu.common.shiro;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
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
 * @类描述: 权限校验
 * @创建者: 皇族灬战狼
 * @创建时间: 2017年11月1日 上午11:05:29
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class PermissionFilter extends AccessControlFilter {

	/** ----------------------------------------------------- Fields start */
	/**
	 * 日志对象
	 */
	protected Logger logger = LoggerFactory.getLogger(getClass());
	/** ----------------------------------------------------- Fields end */
	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response,
			Object mappedValue) throws Exception {
		
		// 1.先判断带参数的权限判断
		Subject subject = getSubject(request, response);
		if(null != mappedValue){
			String[] arra = (String[])mappedValue;
			for (String permission : arra) {
				if(subject.isPermitted(permission)){
					return Boolean.TRUE;
				}
			}
		}
		
		// 2.取到请求的uri ，进行权限判断
		HttpServletRequest httpRequest = ((HttpServletRequest)request);
		//获取URI
		String uri = httpRequest.getRequestURI();
		//获取basePath
		String basePath = httpRequest.getContextPath();
		if(null != uri && uri.startsWith(basePath)){
			uri = uri.replaceFirst(basePath, "");
			if(uri.startsWith("/")){
				uri=uri.substring(1);
			}
		}
		if(subject.isPermitted(uri)){
			return Boolean.TRUE;
		}
		if(FilterHelper.isAjax(request)){
			if(logger.isDebugEnabled()){
				logger.debug("当前用户没有登录，并且是Ajax请求！");
			}
			
			Map<String,String> resultMap = new HashMap<String, String>();
			resultMap.put("login_status", "300");
			resultMap.put("message", "当前用户没有登录，并且是Ajax请求！");//当前用户没有登录！
			FilterHelper.out(response, resultMap);
		}
		
		return Boolean.FALSE;
	}

	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response)
			throws Exception {

		Subject subject = getSubject(request, response);  
		if (null == subject.getPrincipal()) {// 表示没有登录，重定向到登录页面
			saveRequest(request);
			WebUtils.issueRedirect(request, response, FilterHelper.LOGIN_ACTION);
		} else {
			if (StringUtils.hasText(FilterHelper.UNAUTHORIZED)) {// 如果有未授权页面跳转过去
				WebUtils.issueRedirect(request, response, FilterHelper.UNAUTHORIZED);
			} else {// 否则返回401未授权状态码
				WebUtils.toHttp(response).sendError(HttpServletResponse.SC_UNAUTHORIZED);
			}
		}
		return Boolean.FALSE;
	}
}
