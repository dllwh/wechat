package com.cdeledu.common.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.cdeledu.common.constants.FilterHelper;
import com.cdeledu.common.constants.GlobalConstants;
import com.cdeledu.model.SessionInfo;
import com.cdeledu.util.WebUtilHelper;

/**
 * @类描述: 登陆拦截器实现登陆控制
 * @创建者: 皇族灬战狼
 * @创建时间: 2017年1月5日 下午8:00:36
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class LoginIntercepter extends HandlerInterceptorAdapter {
	private static Logger logger = Logger.getLogger(LoginIntercepter.class);

	/**
	 * 在业务处理器处理请求之前被调用 如果返回false 从当前的拦截器往回执行所有拦截器的afterCompletion(),再退出拦截器链
	 * 如果返回true 执行下一个拦截器,直到所有的拦截器都执行完毕 再执行被拦截的Controller 然后进入拦截器链,
	 * 从最后一个拦截器往回执行所有的postHandle() 接着再从最后一个拦截器往回执行所有的afterCompletion()
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object handler) throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("Access Auth Interceptor - 进入拦截器");
		}
		// 记录访问开始时间
		request.setAttribute("access_begin_time", System.currentTimeMillis());
		HttpServletRequest httpRequest = (HttpServletRequest) request;

		HttpSession session = WebUtilHelper.getSession();
		SessionInfo sessioninfo = (SessionInfo) session.getAttribute(GlobalConstants.USER_SESSION);
		if ((null == sessioninfo || null == sessioninfo.getManagerUser())
				&& FilterHelper.isURILogin(httpRequest)) {
			request.getRequestDispatcher(request.getContextPath() + GlobalConstants.LOGIN)
					.forward(request, response);
			return false;
		} else {
			return true;
		}

	}

	/**
	 * 在业务处理器处理请求执行完成后,生成视图之前执行的动作 可在modelAndView中加入数据，比如当前时间
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug(
					"Access Auth Interceptor - interceptor auth execution time 拦截器验证执行时间："
							+ (System.currentTimeMillis() - Long
									.valueOf(request.getAttribute("access_begin_time").toString()))
							+ "ms");
		}
	}

	/**
	 * 在DispatcherServlet完全处理完请求后被调用,可用于清理资源等
	 * 
	 * 当有拦截器抛出异常时,会从当前拦截器往回执行所有的拦截器的afterCompletion()
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
			Object handler, Exception ex) throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug(
					"Access Auth Interceptor - controller execution time 控制器方法执行时间："
							+ (System.currentTimeMillis() - Long
									.valueOf(request.getAttribute("access_begin_time").toString()))
							+ "ms");
		}
	}
}
