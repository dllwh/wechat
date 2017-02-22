package com.cdeledu.common.browser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

public class BrowserUtils {
	/*--------------------------私有属性 start -------------------------------*/
	private final static String IE11 = "rv:11.0";
	private final static String IE10 = "MSIE 10.0";
	private final static String IE9 = "MSIE 9.0";
	private final static String IE8 = "MSIE 8.0";
	private final static String IE7 = "MSIE 7.0";
	private final static String IE6 = "MSIE 6.0";
	private final static String MAXTHON = "Maxthon";
	private final static String QQ = "QQBrowser";
	private final static String GREEN = "GreenBrowser";
	private final static String SE360 = "360SE";
	private final static String FIREFOX = "Firefox";
	private final static String OPERA = "Opera";
	private final static String CHROME = "Chrome";
	private final static String SAFARI = "Safari";
	private final static String OTHER = "其它";

	/*--------------------------私有属性 end   -------------------------------*/
	/*--------------------------私有方法 start -------------------------------*/
	private static boolean getBrowserType(HttpServletRequest request,
			String brosertype) {
		String userAgent = request.getHeader("USER-AGENT").toLowerCase();
		return userAgent.indexOf(brosertype) > 0 ? true : false;
	}

	private static boolean regex(String regex, String str) {
		Pattern p = Pattern.compile(regex, Pattern.MULTILINE);
		Matcher m = p.matcher(str);
		return m.find();
	}

	/*--------------------------私有方法 end   -------------------------------*/
	/*--------------------------公有方法 start -------------------------------*/
	/*--------------------------公有方法 end   -------------------------------*/
	/**
	 * 
	 * @Title：isIE
	 * @Description：判断是否是IE浏览器
	 * @param request
	 * @return
	 * @return：boolean 返回类型
	 */
	public static boolean isIE(HttpServletRequest request) {
		String userAgent = request.getHeader("USER-AGENT").toLowerCase();
		return (userAgent.indexOf("msie") > 0 || userAgent.indexOf("rv:11.0") > 0) ? true
				: false;
	}

	/**
	 * 
	 * @Title：getIEversion
	 * @Description：获取IE版本号
	 * @param request
	 * @return
	 * @return：Double 返回类型
	 */
	public static Double getIEversion(HttpServletRequest request) {
		Double version = 0.0;
		if (getBrowserType(request, IE11)) {
			version = 11.0;
		} else if (getBrowserType(request, IE10)) {
			version = 10.0;
		} else if (getBrowserType(request, IE9)) {
			version = 9.0;
		} else if (getBrowserType(request, IE8)) {
			version = 8.0;
		} else if (getBrowserType(request, IE7)) {
			version = 7.0;
		} else if (getBrowserType(request, IE6)) {
			version = 6.0;
		}
		return version;
	}

	/**
	 * 
	 * @Title：getBrowserType
	 * @Description：获取浏览器类型
	 * @param request
	 * @return
	 * @return：BrowserType 返回类型
	 */
	public static BrowserType getBrowserType(HttpServletRequest request) {
		BrowserType browserType = null;
		if (getBrowserType(request, IE11)) {
			browserType = BrowserType.IE11;
		}
		if (getBrowserType(request, IE10)) {
			browserType = BrowserType.IE10;
		}
		if (getBrowserType(request, IE9)) {
			browserType = BrowserType.IE9;
		}
		if (getBrowserType(request, IE8)) {
			browserType = BrowserType.IE8;
		}
		if (getBrowserType(request, IE7)) {
			browserType = BrowserType.IE7;
		}
		if (getBrowserType(request, IE6)) {
			browserType = BrowserType.IE6;
		}
		if (getBrowserType(request, FIREFOX)) {
			browserType = BrowserType.Firefox;
		}
		if (getBrowserType(request, SAFARI)) {
			browserType = BrowserType.Safari;
		}
		if (getBrowserType(request, CHROME)) {
			browserType = BrowserType.Chrome;
		}
		if (getBrowserType(request, OPERA)) {
			browserType = BrowserType.Opera;
		}
		if (getBrowserType(request, "Camino")) {
			browserType = BrowserType.Camino;
		}
		return browserType;
	}

	/**
	 * 
	 * @方法名称: checkBrowse
	 * @方法描述: 获取浏览器类型
	 * 
	 * @param request
	 * @return
	 */
	public static String checkBrowse(HttpServletRequest request) {
		String userAgent = request.getHeader("USER-AGENT");
		if (regex(OPERA, userAgent))
			return OPERA;
		if (regex(CHROME, userAgent))
			return CHROME;
		if (regex(FIREFOX, userAgent))
			return FIREFOX;
		if (regex(SAFARI, userAgent))
			return SAFARI;
		if (regex(SE360, userAgent))
			return SE360;
		if (regex(GREEN, userAgent))
			return GREEN;
		if (regex(QQ, userAgent))
			return QQ;
		if (regex(MAXTHON, userAgent))
			return MAXTHON;
		if (regex(IE11, userAgent))
			return IE11;
		if (regex(IE10, userAgent))
			return IE10;
		if (regex(IE9, userAgent))
			return IE9;
		if (regex(IE8, userAgent))
			return IE8;
		if (regex(IE7, userAgent))
			return IE7;
		if (regex(IE6, userAgent))
			return IE6;
		return OTHER;
	}
	
	/**
	 * 判断是否是Chrome浏览器
	 * 
	 * @param userAgent
	 *            request.getHeader("USER-AGENT")
	 * @return
	 */
	public static boolean isChrome(String userAgent) {
		userAgent = userAgent.toLowerCase();
		return StringUtils.contains(userAgent, "chrome");
	}

	/**
	 * 判断是否是Firefox浏览器
	 * 
	 * @param userAgent
	 *            request.getHeader("USER-AGENT")
	 * @return
	 */
	public static boolean isFirefox(String userAgent) {
		userAgent = userAgent.toLowerCase();
		return StringUtils.contains(userAgent, "firefox");
	}
}
