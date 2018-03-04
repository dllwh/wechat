package com.cdeledu.util.network.cookie;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

/**
 * @类描述: java对cookie的操作
 * @创建者: 皇族灬战狼
 * @创建时间: 2016年9月13日 上午8:18:18
 * @版本: V1.2
 * @since: JDK 1.7
 */
public class CookieUtilHelper {
	/** 设置cookie的有效路径 */
	private static String path = "/";
	/** 设置Cookie的有效期为1天 */
	private static int validAge = 24 * 60 * 60;
	private static Map<String, String> cookies = new ConcurrentHashMap<String, String>();

	/**
	 * @方法:获得某个网站的Cookie信息
	 * @创建人:独泪了无痕
	 * @param host
	 *            网站Host
	 * @return Cookie字符串
	 */
	public static String get(String host) {
		return cookies.get(host);
	}

	/**
	 * @方法:将某个网站的Cookie放入Cookie池
	 * @创建人:独泪了无痕
	 * @param host
	 *            网站Host
	 * @param cookie
	 *            Cookie字符串
	 */
	public static void put(String host, String cookie) {
		cookies.put(host, cookie);
	}

	/**
	 * @方法描述: 设置cookie
	 * @创建者: 皇族灬战狼
	 * @创建时间: 2016年9月13日 上午8:17:02
	 * @param response
	 * @param cookieName
	 *            cookie名字
	 * @param cookieValue
	 *            cookie值
	 * @param domain
	 * @param maxAge
	 *            cookie生命周期 以秒为单位
	 */
	public static void addCookie(HttpServletResponse response, String cookieName,
			String cookieValue) {
		// 创建一个cookie
		Cookie cookie = null;
		try {
			cookie = new Cookie(cookieName, URLEncoder.encode(cookieValue, "UTF-8"));
		} catch (Exception e) {
			cookie = new Cookie(cookieName, cookieValue);
		}
		// 设置路径，这个路径即该工程下都可以访问该cookie 如果不设置路径，那么只有设置该cookie路径及其子路径可以访问
		cookie.setPath(path);
		// 设置生命周期
		cookie.setMaxAge(validAge);
		// 将cookie对象添加到response对象中,这样服务器在输出response对象中的内容时就会把cookie也输出到客户端浏览器
		response.addCookie(cookie);
	}

	/**
	 * @方法描述: 修改cookie
	 *        <p>
	 *        修改、删除Cookie时,新建的Cookie除value、maxAge之外的所有属性,例如name、path、domain等,
	 *        都要与原Cookie完全一样。否则,浏览器将视为两个不同的Cookie不予覆盖，导致修改、删除失败。
	 *        </p>
	 * @创建者: 皇族灬战狼
	 * @创建时间: 2016年9月13日 上午9:12:44
	 * @param request
	 * @param response
	 * @param cookieName
	 * @param cookieValue
	 */
	public void editCookie(HttpServletRequest request, HttpServletResponse response,
			String cookieName, String cookieValue) {
		Cookie[] cookies = request.getCookies();
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals(cookieName)) {
				try {
					cookie.setValue(URLEncoder.encode(cookieValue, "UTF-8"));
				} catch (Exception e) {
					cookie.setValue(cookieValue);
				}
				cookie.setPath(path);
				cookie.setMaxAge(validAge);
				response.addCookie(cookie);
			}
		}
	}

	/**
	 * @方法描述: 删除Cookie
	 * @创建者: 皇族灬战狼
	 * @创建时间: 2016年9月13日 上午9:05:03
	 * @param response
	 * @param cookieName
	 */
	public static void delCookie(HttpServletRequest request, HttpServletResponse response,
			String cookieName) {
		Cookie[] cookies = request.getCookies();
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals(cookieName)) {
				cookie.setValue(null);
				// 设置为0为立即销毁cookie
				cookie.setMaxAge(0);
				// 删除指定路径上的Cookie，不设置该路径，默认为删除当前路径Cookie
				cookie.setPath(path);
				response.addCookie(cookie);
			}
		}
	}

	/**
	 * @方法描述: 读取cookie
	 *        <ul>
	 *        <li>从客户端读取Cookie时,浏览器提交Cookie时只会提交name与value属性,
	 *        包括maxAge在内的其他属性都是不可读的,也不会被提交</li>
	 *        <li>maxAge属性只被浏览器用来判断Cookie是否过期</li>
	 *        <li></li>
	 *        </ul>
	 * @创建者: 皇族灬战狼
	 * @创建时间: 2016年9月13日 上午9:08:05
	 * @param request
	 * @param cookieName
	 * @return Cookie 返回类型
	 */
	public static String getCookieByName(HttpServletRequest request, String cookieName) {
		if (StringUtils.isNotBlank(cookieName)) {
			// 获取浏览器访问访问服务器时传递过来的cookie数组
			Cookie[] cookies = request.getCookies();

			for (Cookie cookie : cookies) {
				if (cookie.getName().equals(cookieName)) {
					try {
						return URLDecoder.decode(cookie.getValue(), "UTF-8");
					} catch (Exception e) {
						return cookie.getValue();
					}
				}
			}
		}
		return "";
	}

	/**
	 * 
	 * @Title：buildCurrentURL
	 * @Description：生成当前URL
	 * @param request
	 * @return
	 * @return：String 返回类型
	 */
	public static String buildCurrentURL(HttpServletRequest request) {

		StringBuffer url = new StringBuffer("");
		if (request.getScheme().startsWith("http")) {
			url.append("http://");
		} else {
			url.append("https://");
		}
		url.append(request.getHeader("host"));

		if (request.getServerPort() != 80) {
			url.append(":" + request.getServerPort());
		}

		url.append(request.getRequestURI());

		if (StringUtils.isNotEmpty(request.getQueryString())) {
			url.append("?");
			url.append(request.getQueryString());
		}
		return url.toString();
	}
}
