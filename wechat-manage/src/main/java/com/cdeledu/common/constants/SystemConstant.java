package com.cdeledu.common.constants;

/**
 * 
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 系统级静态变量
 * @创建者: 独泪了无痕
 * @创建时间: 2017年11月8日 上午11:13:42
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class SystemConstant {

	/**
	 * Ajax操作没有权限的响应头key
	 */
	public static final String HEAD_NO_PERMISSION_KEY = "X-No-Permission";

	/**
	 * Ajax操作没有权限的响应头value
	 */
	public static final String HEAD_NO_PERMISSION_VALUE = "No-Permission";

	/**
	 * Ajax操作登陆超时的响应头key
	 */
	public static final String HEAD_SESSION_STATUS_KEY = "X-Session-Status";

	/**
	 * Ajax操作登陆超时的响应头value
	 */
	public static final String HEAD_SESSION_STATUS_VALUE = "Session-Timeout";

	/** 菜单类型 */
	public enum SysMenuType {
		/** 目录 */
		CATALOG(0),
		/** 菜单 */
		MENU(1),
		/** 按钮 */
		BUTTON(2);
		int value;

		SysMenuType(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}
	}

	/** 日志类型 */
	public enum SysOpType {
		/** 登录日志 */
		LOGIN("登录日志"),
		/** 登录失败日志 */
		LOGIN_FAIL("登录失败日志"),
		/** 退出日志 */
		EXIT("退出成功"),
		EXIT_FAIL("退出失败"),
		/** 异常日志 */
		EXCEPTION("异常日志"),
		/** 业务日志 */
		BUSSINESS("业务日志"),
		/** 插入 */
		INSERT("insert"),
		/** 删除 */
		DEL("delete"),
		/** 更新 */
		UPDATE("update"),
		/** 上传 */
		UPLOAD("upload"),
		/** 选择、查询 */
		SELECT("select"),
		/** 重置 */
		RESET("reset"),
		/** 下载 */
		DOWNLOAD("download"),
		/** 其他 */
		OTHER("other"),
		/** 未知 */
		UNKNOWN("Unknown");
		String value;

		SysOpType(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}
	}

	public enum SysLogLeavel {
		/** 日志级别 */
		info, warn, error;
	}
}
