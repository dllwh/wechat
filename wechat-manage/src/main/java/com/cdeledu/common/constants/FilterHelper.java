package com.cdeledu.common.constants;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

public class FilterHelper {
	/** ----------------------------------------------------- Fields start */

	//登录页面
	public final static String LOGIN_SHORT = "/login/login";							
	public final static String LOGIN = "/webviews/login/login.jsp";								
	public final static String LOGIN_ACTION = "/loginController.shtml?checkuser";				
	//踢出登录提示
	public final static String KICKED_OUT = "/open/kickedOut.shtml";
	//没有权限提醒
	public final static String UNAUTHORIZED = "/open/unauthorized.shtml";
	/** ----------------------------------------------------- Fields end */
	/**
	 * 是否是Ajax请求
	 * @param request
	 * @return
	 */
	public static boolean isAjax(ServletRequest request){
		return "XMLHttpRequest".equalsIgnoreCase(((HttpServletRequest) request).getHeader("X-Requested-With"));
	}

	/**
	 * 当前URI是否需要登录才能访问
	 * 
	 * @param request
	 * @return
	 */
	public static boolean isURILogin(HttpServletRequest request) {
		String path = request.getServletPath();
		if (path.matches(GlobalConstants.INHERENT_PATH)) {
			return true;
		}
		return false;
	}
}
