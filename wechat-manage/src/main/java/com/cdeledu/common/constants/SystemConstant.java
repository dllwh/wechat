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

	/** 定时任务状态 */
	public enum SysScheduleStatus {
		/** 正常 */
		NORMAL(1),
		/** 暂停 */
		PAUSE(0);
		int value;

		SysScheduleStatus(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}
	}

	/** 系统通用状态 */
	public enum SysStatusType {
		/** 禁用，隐藏 */
		DISABLE(0),
		/** 可用，显示 */
		ENABLE(1),
		/** 显示 */
		SHOW(1),
		/** 隐藏 */
		HIDDEN(0);

		int value;

		SysStatusType(int value) {
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
		EXIT("退出日志"),
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
		/** 下载 */
		DOWNLOAD("download"),
		/** 其他 */
		OTHER("other");
		String value;

		SysOpType(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}
	}

	/** 日志级别 */
	public enum SysLogLeavel {
		info, warn, error;
	}

}
