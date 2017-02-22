package com.cdeledu.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.cdeledu.common.constants.GlobalConstants;
import com.cdeledu.model.SessionInfo;
import com.cdeledu.model.rbac.ManagerUser;

/**
 * @类描述: 上下文工具类:Web常用工具集，用于获取当前登录用户，请求信息等
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建日期: 2016年4月4日 下午6:31:51
 * @版本: V1.2
 * @since: JDK 1.7
 */
public class WebUtilHelper {
	private static HttpSession session = null;

	/**
	 * @方法:SpringMvc下获取request,尝试获取当前请求的HttpServletRequest实例
	 * @创建人:独泪了无痕
	 * @return
	 */
	public static HttpServletRequest getHttpServletRequest() {
		try {
			return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
					.getRequest();
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * @方法:SpringMvc下获取session
	 * @创建人:独泪了无痕
	 * @return
	 */
	public static HttpSession getSession() {
		if (null == session) {
			session = getHttpServletRequest().getSession();
		}
		return session;
	}

	/**
	 * @方法:获取session里的用户对象
	 * @创建人:独泪了无痕
	 * @return
	 */
	public static final ManagerUser getCurrenLoginUser() {
		HttpSession session = getSession();
		session.setMaxInactiveInterval(-1);
		SessionInfo sessionInfo = null;
		if (session.getAttributeNames().hasMoreElements()) {
			sessionInfo = (SessionInfo) session.getAttribute(GlobalConstants.USER_SESSION);
			if (null != sessionInfo) {
				if (null != sessionInfo.getManagerUser()) {
					return sessionInfo.getManagerUser();
				}
			}
		}
		return null;
	}

	/**
	 * @方法描述: 在HttpSession中设置当前登录的用户
	 * @param user
	 * @return 当前登录的用户
	 */
	public static ManagerUser setCurrentLoginUser(ManagerUser user) {
		HttpSession session = getSession();
		SessionInfo sessionInfo = new SessionInfo();
		sessionInfo.setManagerUser(user);
		session.setMaxInactiveInterval(60 * 30);
		session.setAttribute(GlobalConstants.USER_SESSION, sessionInfo);
		return user;
	}

	/**
	 * @方法描述: 在HttpSession中移除当前登录的用户
	 * @param user
	 * @return
	 */
	public static void removeCurrentLoginUser() {

	}

	/**
	 * @方法:获得请求路径
	 * @创建人:独泪了无痕
	 * @param request
	 * @return
	 */
	public static String getRequestPath(HttpServletRequest request) {
		String requestPath = request.getRequestURI() + "?" + request.getQueryString();
		if (requestPath.indexOf("&") > -1) {// 去掉其他参数
			requestPath = requestPath.substring(0, requestPath.indexOf("&"));
		}
		requestPath = requestPath.substring(request.getContextPath().length() + 1);// 去掉项目路径
		return requestPath;
	}

	/**
	 * web应用绝对路径
	 *
	 * @param request
	 *            请求对象
	 * @return 绝对路径
	 */
	public static String getBasePath(HttpServletRequest request) {
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName() + ":"
				+ request.getServerPort() + path + "/";
		return basePath;
	}
}
