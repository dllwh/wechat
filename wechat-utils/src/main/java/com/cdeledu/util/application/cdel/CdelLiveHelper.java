package com.cdeledu.util.application.cdel;

import com.cdeledu.util.network.tcp.HttpURLConnHelper;

/**
 * 
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 正保直播
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2018年6月27日 上午12:29:40
 * @版本: V1.0
 * @since: JDK 1.7
 */
public final class CdelLiveHelper {
	/** ----------------------------------------------------- Fields start */
	private static HttpURLConnHelper urlConnHelper;

	/** ----------------------------------------------------- Fields end */
	static {
		urlConnHelper = HttpURLConnHelper.getInstance();
	}

	/**
	 * @方法:获取服务器时间
	 * @创建人:独泪了无痕
	 * @return
	 * @throws Exception
	 */
	public static String getcurtime() throws Exception {
		return urlConnHelper
				.sendPostRequest("http://www.chinatet.com/zbapi/api/live/getcurtime.shtm").trim();
	}

	/**
	 * @方法:网站缓存刷新
	 * @创建人:独泪了无痕
	 * @throws Exception
	 */
	public static void refreshDomainCache() throws Exception {
		urlConnHelper
				.sendPostRequest("www.chinatet.com/zbapi/zbShare/refreshCache.shtm?key=website");
	}

	/** 获取用户绑定的手机号 */
	public static void ucGetUserBind(String privateKey, String ssouid) {
	}

	/** 根据uid获取用户相关信息 */
	public static void ucGetInfo(String privateKey, String ssouid) {
	}

	/** 查询手机号是否被绑定 */
	public static void chkMobileExist(String privateKey, String mobile) {
	}

	/** 查询用户名是否已注册 */
	public static void chkUserNameExist(String privateKey, String userName) {
	}

	/** 根据手机号查询被绑定的用户信息 */
	public static void ucGetInfoByMobile(String privateKey, String mobile) {
	}

	/** 密码检测 */
	public static void ucChkPwd(String privateKey, String userName, String passwd) {
	}

	/** 根据sid获取用户登录信息 */
	public static void ucGetUserId(String privateKey, String sid) {
	}

	/** 注册 */
	public static void ucRegister(String privateKey, String userName, String passwd) {
	}

	/** 解除绑定 */
	public static void ucUnBind(String privateKey, String ssouid) {
	}
}
