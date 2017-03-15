package com.cdeledu.util.network;

import javax.servlet.http.HttpServletRequest;

import nl.bitwalker.useragentutils.Browser;
import nl.bitwalker.useragentutils.DeviceType;
import nl.bitwalker.useragentutils.UserAgent;

/**
 * @类描述: 用户代理字符串识别工具
 * @创建者: 皇族灬战狼
 * @创建时间: 2017年2月25日 下午4:56:20
 * @版本: V1.0
 * @since: JDK 1.7
 * @see <a href=
 *      "http://baike.baidu.com/link?url=LUsnCjgTwcxxWL1SSPZYWWnzB81Wh_XO1OoM-K4ZNu8E3WHRLk_-mtNjVL1Wu7_rstEdorXm6Sy4fGtEoKjhVit8RpmEPsssj0J3Tnuhob0uVVvz-lKlhWpnY17s-zvFAfshOVIC8x1iOY_jkC0OI_tQCllgjNaJsYZTSKmNbxa">
 *      useragent_百度百科</a>
 */
public final class UserAgentUtils {
	/** ----------------------------------------------------- Fields start */
	/** ----------------------------------------------------- Fields end */

	/** ----------------------------------------------- [私有方法] */
	/** ----------------------------------------------- [私有方法] */

	/**
	 * @方法描述: 获取用户代理对象
	 */
	public static UserAgent getUserAgent(HttpServletRequest request) {
		return UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
	}

	/**
	 * @方法描述: 获取设备类型
	 */
	public static DeviceType getDeviceType(HttpServletRequest request) {
		return getUserAgent(request).getOperatingSystem().getDeviceType();
	}

	/**
	 * @方法描述: 是否是PC
	 */
	public static boolean isComputer(HttpServletRequest request) {
		return DeviceType.COMPUTER.equals(getDeviceType(request));
	}

	/**
	 * @方法描述: 是否是手机
	 */
	public static boolean isMobile(HttpServletRequest request) {
		return DeviceType.MOBILE.equals(getDeviceType(request));
	}

	/**
	 * @方法描述: 是否是平板
	 */
	public static boolean isTablet(HttpServletRequest request) {
		return DeviceType.TABLET.equals(getDeviceType(request));
	}

	/**
	 * @方法描述: 获取浏览类型
	 */
	public static Browser getBrowser(HttpServletRequest request) {
		return getUserAgent(request).getBrowser();
	}
}
