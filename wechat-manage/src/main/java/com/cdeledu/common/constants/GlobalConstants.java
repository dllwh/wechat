package com.cdeledu.common.constants;

import org.springframework.context.ApplicationContext;

import com.cdeledu.util.WebUtilHelper;

/**
 * @类描述: 全局配置类、系统常量
 * @创建者: 皇族灬战狼
 * @创建时间: 2016年12月7日 下午7:44:50
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class GlobalConstants {
	/** 验证码 */
	public static final String IMAGECAPTCHA = "imageCaptcha";
	/** 全站在线人数 */
	public static final String ONLINEUSERNUMBER = "onLineUserNumber";
	/** 过滤标识，防止一次请求多次过滤 */
	public static final String FILTERED_REQUEST = "@@session_context_filtered_request";
	/** 保存用户到SESSION */
	public static final String USER_SESSION = "USER_SESSION";
	/** session status */
	public static final String SESSION_STATUS ="sojson-online-status";
	/** 人员类型 */
	public static final Short User_Normal = 1;// 正常
	public static final Short User_Forbidden = 0;// 禁用
	public static final Short User_ADMIN = -1;// 超级管理员
	/** 日志级别定义 */
	public static final Integer Log_Leavel_INFO = 1;
	public static final Integer Log_Leavel_WARRING = 2;
	public static final Integer Log_Leavel_ERROR = 3;

	/** 该值会在web容器启动时由WebContextListener初始化 */
	public static ApplicationContext WEB_APP_CONTEXT = null;
	/**
	 * 上传图片大小限制，单位byte
	 */
	public final static long MAX_UPLOAD_PIC_SIZE = 1000 * 1024 * 4;
	/**
	 * 上传文件大小限制，单位byte
	 */
	public final static long MAX_UPLOAD_FILE_SIZE = 10 * 1024 * 1024 * 8;

	/**
	 * 上传软件大小限制，单位byte
	 */
	public final static long MAX_UPLOAD_SOFT_SIZE = 100 * 1024 * 1024 * 8;

	/**
	 * 错误登录次数最多3次
	 */
	public final static int MAX_LOGIN_TIMES = 3;
	/**
	 * 错误登录3次后用户被锁10分钟
	 */
	public final static int LOCK_TIME = +10;

	/**
	 * 获取Key加载信息
	 */
	public static boolean printKeyLoadMessage() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(
				"\r\n            ================================================================================================\r\n");
		stringBuilder.append(
				"\r\n                  ___           ___           ___           ___                       ___           ___     ");
		stringBuilder.append(
				"\r\n                 /\\__\\         /\\  \\         /\\  \\         /\\__\\          ___        /\\__\\         /\\  \\    ");
		stringBuilder.append(
				"\r\n                /::|  |       /::\\  \\       /::\\  \\       /::|  |        /\\  \\      /::|  |       /::\\  \\   ");
		stringBuilder.append(
				"\r\n               /:|:|  |      /:/\\:\\  \\     /:/\\:\\  \\     /:|:|  |        \\:\\  \\    /:|:|  |      /:/\\:\\  \\  ");
		stringBuilder.append(
				"\r\n              /:/|:|__|__   /:/  \\:\\  \\   /::\\~\\:\\  \\   /:/|:|  |__      /::\\__\\  /:/|:|  |__   /:/  \\:\\  \\ ");
		stringBuilder.append(
				"\r\n             /:/ |::::\\__\\ /:/__/ \\:\\__\\ /:/\\:\\ \\:\\__\\ /:/ |:| /\\__\\  __/:/\\/__/ /:/ |:| /\\__\\ /:/__/_\\:\\__\\");
		stringBuilder.append(
				"\r\n             \\/__/~~/:/  / \\:\\  \\ /:/  / \\/_|::\\/:/  / \\/__|:|/:/  / /\\/:/  /    \\/__|:|/:/  / \\:\\  /\\ \\/__/");
		stringBuilder.append(
				"\r\n                   /:/  /   \\:\\  /:/  /     |:|::/  /      |:/:/  /  \\::/__/         |:/:/  /   \\:\\ \\:\\__\\  ");
		stringBuilder.append(
				"\r\n                  /:/  /     \\:\\/:/  /      |:|\\/__/       |::/  /    \\:\\__\\         |::/  /     \\:\\/:/  /  ");
		stringBuilder.append(
				"\r\n                 /:/  /       \\::/  /       |:|  |         /:/  /      \\/__/         /:/  /       \\::/  /   ");
		stringBuilder.append(
				"\r\n                 \\/__/         \\/__/         \\|__|         \\/__/                     \\/__/         \\/__/    \r\n");
		stringBuilder.append(
				"\r\n            ================================================================================================\r\n");
		System.out.println(stringBuilder.toString());
		return true;
	}

	/**
	 * 是否是演示模式，演示模式下不能修改用户、角色、密码、菜单、授权
	 */
	public static Boolean isDemoMode() {
		String dm = WebUtilHelper.getConfigByName("demoMode");
		return "true".equals(dm) || "1".equals(dm);
	}
	/** 系统登录后默认首页地址 */
	public static String getHomePageUrl() {
		return "homeController/home.shtml";
	}
	
	public static String getloginPageUrl() {
		return FilterHelper.LOGIN_ACTION;
	}
}
