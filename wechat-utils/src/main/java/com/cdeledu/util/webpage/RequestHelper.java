package com.cdeledu.util.webpage;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: request工具类
 * @创建者: 皇族灬战狼
 * @创建时间: 2017年10月16日 上午9:19:41
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class RequestHelper {
	/** ----------------------------------------------------- Fields start */
	/** ----------------------------------------------------- Fields end */
	/**
	 * @方法描述: 移除request指定参数
	 * @param request
	 * @param paramName
	 * @return
	 */
	public String removeParam(HttpServletRequest request, String paramName) {
		String queryString = "";
		Enumeration<String> keys = request.getParameterNames();
		while (keys.hasMoreElements()) {
			String key = (String) keys.nextElement();
			if (key.equals(paramName)) {
				continue;
			}
			if (queryString.equals("")) {
				queryString = key + "=" + request.getParameter(key);
			} else {
				queryString += "&" + key + "=" + request.getParameter(key);
			}
		}
		return queryString;
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
	 * @方法描述: 获取请求basePath
	 * @param request
	 * @return
	 */
	public static String getBasePath(HttpServletRequest request) {
		StringBuffer basePath = new StringBuffer();
		String scheme = request.getScheme();
		String domain = request.getServerName();
		int port = request.getServerPort();
		basePath.append(scheme);
		basePath.append("://");
		basePath.append(domain);
		if ("http".equalsIgnoreCase(scheme) && 80 != port) {
			basePath.append(":").append(String.valueOf(port));
		} else if ("https".equalsIgnoreCase(scheme) && port != 443) {
			basePath.append(":").append(String.valueOf(port));
		}
		return basePath.toString();
	}

	/**
	 * @方法描述: 获取ip工具类，除了getRemoteAddr，其他ip均可伪造
	 * @param request
	 * @return
	 */
	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("Cdn-Src-Ip"); // 网宿cdn的真实ip
		if (ip == null || ip.length() == 0 || " unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP"); // 蓝讯cdn的真实ip
		}
		if (ip == null || ip.length() == 0 || " unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("X-Forwarded-For"); // 获取代理ip
		}
		if (ip == null || ip.length() == 0 || " unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP"); // 获取代理ip
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP"); // 获取代理ip
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr(); // 获取真实ip
		}
		return ip;
	}

	/** ----------------------------------------------- [私有方法] */
	/** ----------------------------------------------- [私有方法] */

	/** ----------------------------------------------- [测试方法] */
	/** ----------------------------------------------- [测试方法] */
}
