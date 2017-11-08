package com.cdeledu.common.interceptors;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.cdeledu.common.base.BaseClass;
import com.cdeledu.common.constants.FilterHelper;
import com.cdeledu.model.rbac.SysUser;
import com.cdeledu.service.sys.SystemService;
import com.cdeledu.util.ShiroHelper;
import com.cdeledu.util.WebUtilHelper;
import com.cdeledu.util.webpage.RequestHelper;

/**
 * @类描述: 权限拦截器
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建日期: 2016年4月24日 下午2:37:01
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class AuthInterceptor extends BaseClass implements HandlerInterceptor {
	private static final long serialVersionUID = 1L;
	/** ----------------------------------------------------- Fields start */
	private final Logger log = LoggerFactory.getLogger(AuthInterceptor.class);
	@Autowired
	private SystemService systemService;
	private List<String> excludeUrls;

	public List<String> getExcludeUrls() {
		return excludeUrls;
	}

	public void setExcludeUrls(List<String> excludeUrls) {
		this.excludeUrls = excludeUrls;
	}

	/** ----------------------------------------------------- Fields end */

	/** ----------------------------------------------- privateMethod start */
	/**
	 * @方法:查询用户是否有权限
	 * @创建人:独泪了无痕
	 * @param request
	 * @return
	 */
	private boolean hasMenuAuth(HttpServletRequest request) {
		// 用户访问的资源地址
		String requestPath = RequestHelper.getRequestPath(request);
		// update-start-for:菜单权限控制排除Ajax请求判断
		if (requestPath.indexOf("loginController.shtml") != -1) {
			return true;
		}

		Integer userId = ShiroHelper.getCurrentUserId();
		if (logger.isDebugEnabled()) {
			logger.info("查询当前登录用户是否具有当前访问地址");
		}
		Integer resultCount = 0;
		try {
			resultCount = systemService.getAuthByuserIdAndAuthPath(userId, requestPath);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (null != resultCount && resultCount > 0)
			return true;
		else
			return false;
	}

	/** ----------------------------------------------- privateMethod end */

	/** ----------------------------------------------- PubilcMethod start */
	/**
	 * 在controller后拦截
	 */
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
			Object object, Exception exception) throws Exception {
		log.info("==============执行顺序: 3、afterCompletion================");
	}

	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object object,
			ModelAndView modelAndView) throws Exception {
		log.info("==============执行顺序: 2、postHandle================");
	}

	/**
	 * 在controller前拦截
	 */
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object object) throws Exception {
		// String requestUri = request.getRequestURI();
		// String contextPath = request.getContextPath();
		// String url = requestUri.substring(contextPath.length());
		log.info("==============执行顺序: 1、preHandle================");
		// 用户访问的资源地址
		String requestPath = RequestHelper.getRequestPath(request);// 用户访问的资源地址
		HttpSession session = WebUtilHelper.getSession();
		SysUser sysUser = WebUtilHelper.getCurrenLoginUser();
		if (excludeUrls.contains(requestPath)) {
			return true;
		} else {
			// ----------------------------------------------------------------
			// 菜单权限控制
			// ----------------------------------------------------------------
			if (null != session && null != sysUser) {
				// 验证当前用户是否有权限访问此资源
				if (!hasMenuAuth(request)) {
					// "您没有【" + requestPath + "】权限，请联系管理员给您赋予相应权限！
					response.sendRedirect(
							FilterHelper.UNAUTHORIZED + "?requestPath=" + requestPath);
					return false;
				}
				return true;
				// ----------------------------------------------------------------
				// 按钮权限控制
				// ----------------------------------------------------------------
			} else {
				// 没有登录系统，或登录超时 forward("您没有登录或登录超时，请重新登录！", request, response);
				request.getRequestDispatcher("/webviews/login/timeout.jsp").forward(request,
						response);
				return false;
			}
		}
	}
	/** ----------------------------------------------- PubilcMethod end */
}
