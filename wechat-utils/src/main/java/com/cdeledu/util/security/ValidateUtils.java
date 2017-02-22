package com.cdeledu.util.security;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

/**
 * @类描述: 验证过滤
 * @创建者: 皇族灬战狼
 * @创建时间: 2016年6月20日 下午3:00:51
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class ValidateUtils {
	// 特殊符号，需要转义的字符
	final static String[] specials = new String[] { "'", " ", "\\", "/", "@", "#", "(", ")", "\r",
			"\t", "|", "," };

	private static String filterSpecialString(String str) {
		for (String s : specials) {
			str.replace(s, "\\" + s);
		}
		return str;
	}

	/**
	 * @方法描述: 获取请求参数的值并转义
	 * @创建者: 皇族灬战狼
	 * @创建时间: 2016年6月20日 下午3:03:04
	 * @param request
	 *            请求
	 * @param param
	 *            请求参数的名称
	 * @param defaultstr
	 *            参数默认值
	 * @return 请求参数的实际值
	 */
	public static String getURLParamerer(HttpServletRequest request, String param,
			String defaultstr) {
		Object obj = request.getParameter(param);
		if (obj != null && !StringUtils.isEmpty(obj.toString())) {
			return filterSpecialString(request.getParameter(param).toString());
		} else {
			return defaultstr;
		}

	}
}
