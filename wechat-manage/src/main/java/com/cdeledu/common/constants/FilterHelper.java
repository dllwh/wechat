package com.cdeledu.common.constants;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

public class FilterHelper {
	/** ----------------------------------------------------- Fields start */
	/** 登录请求 */
	public final static String LOGIN_ACTION = "loginController.shtml?doLogin";
	/** 登录页面 */
	public final static String LOGIN_SHORT = "/login/login";
	/** 登录页面位置 */
	public final static String LOGIN = "/webviews/login/login.jsp";
	/** 登录验证 */
	public final static String LOGIN_CHECK = "/loginController.shtml?checkuser";
	/** 踢出登录提示 */
	public final static String KICKED_OUT = "/loginController/kickedOut.shtml";
	/** 没有权限提醒 */
	public final static String UNAUTHORIZED = "/sysPage/unauthorized.shtml";
	/** 将登录前的URL放到Session中的键名称 */
	public static final String LOGIN_TO_URL = "toUrl";

	/** ----------------------------------------------------- Fields end */
	/**
	 * 是否是Ajax请求
	 * 
	 * @param request
	 * @return
	 */
	public static boolean isAjax(ServletRequest request) {
		return "XMLHttpRequest"
				.equalsIgnoreCase(((HttpServletRequest) request).getHeader("X-Requested-With"));
	}

	/**
	 * 当前URI是否需要登录才能访问
	 * 
	 * @param request
	 * @return
	 */
	public static boolean isURILogin(HttpServletRequest request) {
		// String requestPath = RequestHelper.getRequestPath(request);//
		// 用户访问的资源地址
		return true;
	}
	
	/**
	 * response 输出JSON
	 * @param hresponse
	 * @param resultMap
	 * @throws IOException
	 */
	public static void out(ServletResponse response, Map<String, String> resultMap){
		
		PrintWriter out = null;
		try {
			response.setCharacterEncoding("UTF-8");
			out = response.getWriter();
			out.println(JSONObject.fromObject(resultMap).toString());
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(null != out){
				out.flush();
				out.close();
			}
		}
	}
}
