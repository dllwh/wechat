package com.cdeledu.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cdeledu.common.constant.ConstantHelper;

/**
 * @类描述: Cookie工具类
 * @创建者: 皇族灬战狼
 * @创建时间: 2017年3月15日 下午7:16:51
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class CookieUtils {
	/** ----------------------------------------------------- Fields start */
	/** ----------------------------------------------------- Fields end */

	/** ----------------------------------------------- [私有方法] */
	/**
	 * @方法描述: 设置 Cookie
	 * @param response
	 * @param name
	 *            名称
	 * @param value
	 *            值
	 * @param path
	 *            路径
	 * @param maxAge
	 *            生存时间（单位秒）
	 */
	private static void setCookie(HttpServletResponse response, String name, String value,
			String path, int maxAge) {
		Cookie cookie = new Cookie(name, null);
		cookie.setPath(path);
		cookie.setMaxAge(maxAge);
		try {
			cookie.setValue(URLEncoder.encode(value, "utf-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		response.addCookie(cookie);

	}

	/** ----------------------------------------------- [私有方法] */
	/**
	 * @方法描述: 设置 Cookie（生成时间为1天）
	 * @param response
	 * @param name
	 *            名称
	 * @param value
	 *            值
	 */
	public static void setCookie(HttpServletResponse response, String name, String value) {
		setCookie(response, name, value, 60 * 60 * 24);
	}

	/**
	 * @方法描述: 设置 Cookie
	 * @param response
	 * @param name
	 *            名称
	 * @param value
	 *            值
	 * @param path
	 *            路径
	 */
	public static void setCookie(HttpServletResponse response, String name, String value,
			String path) {
		setCookie(response, name, value, path, 60 * 60 * 24);
	}

	/**
	 * @方法描述: 设置 Cookie
	 * @param response
	 * @param name
	 *            名称
	 * @param value
	 *            值
	 * @param maxAge
	 *            生存时间（单位秒）
	 */
	public static void setCookie(HttpServletResponse response, String name, String value,
			int maxAge) {
		setCookie(response, name, value, "/", maxAge);
	}

	/**
	 * @方法描述: 获得指定Cookie的值
	 * @param request
	 *            请求对象
	 * @param name
	 *            名称
	 * @return
	 */
	public static String getCookie(HttpServletRequest request, String name) {
		return getCookie(request, null, name, false);
	}

	/**
	 * @方法描述: 获得指定Cookie的值,，并删除
	 * @param request
	 *            请求对象
	 * @param name
	 *            名称
	 * @return
	 */
	public static String getCookie(HttpServletRequest request, HttpServletResponse response,
			String name) {
		return getCookie(request, response, name, true);
	}

	/**
	 * @方法描述:获得指定Cookie的值
	 * @param request
	 *            请求对象
	 * @param response
	 *            响应对象
	 * @param name
	 *            名字
	 * @param isRemove
	 *            是否移除
	 * @return
	 */
	public static String getCookie(HttpServletRequest request, HttpServletResponse response,
			String name, boolean isRemove) {
		String value = "";
		Cookie[] cookies = request.getCookies();
		if (null != cookies) {
			for (Cookie cookie : cookies) {
				try {
					value = URLDecoder.decode(cookie.getValue(), ConstantHelper.UTF_8.name());
				} catch (Exception de) {
					de.printStackTrace();
				}
				if (isRemove) {
					cookie.setMaxAge(0);
					response.addCookie(cookie);
				}
			}
		}
		return value;
	}
}
