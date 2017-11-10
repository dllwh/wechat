package com.cdeledu.common.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * @类描述: 字符集拦截器
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建日期: 2016年4月4日 下午2:30:31
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class EncodingInterceptor implements HandlerInterceptor {
	/** ----------------------------------------------------- Fields start */

	/** ----------------------------------------------------- Fields end */
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object object) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		return true;
	}

	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object object,
			ModelAndView modelAndView) throws Exception {
	}

	public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
			Object object, Exception exception) throws Exception {
	}
}
