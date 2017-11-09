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
	 * 
	 * 把今天最好的表现当作明天最新的起点．．～
	 *
	 * Today the best performance as tomorrow newest starter!
	 *
	 * @类描述: 菜单类型
	 * @创建者: 皇族灬战狼
	 * @创建时间: 2017年11月8日 上午11:15:05
	 * @版本: V1.0
	 * @since: JDK 1.7
	 */
	public enum SysMenuType {
		/**
		 * 目录
		 */
		CATALOG(0),
		/**
		 * 菜单
		 */
		MENU(1),
		/**
		 * 按钮
		 */
		BUTTON(2);

		private int value;

		private SysMenuType(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}
	}

	/**
	 * 
	 * 把今天最好的表现当作明天最新的起点．．～
	 *
	 * Today the best performance as tomorrow newest starter!
	 *
	 * @类描述: 定时任务状态
	 * @创建者: 皇族灬战狼
	 * @创建时间: 2017年11月8日 上午11:15:27
	 * @版本: V1.0
	 * @since: JDK 1.7
	 */
	public enum SysScheduleStatus {
		/**
		 * 正常
		 */
		NORMAL(1),
		/**
		 * 暂停
		 */
		PAUSE(0);

		private int value;

		private SysScheduleStatus(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}
	}

	/**
	 * 
	 * 把今天最好的表现当作明天最新的起点．．～
	 *
	 * Today the best performance as tomorrow newest starter!
	 *
	 * @类描述: 系统通用状态
	 * @创建者: 皇族灬战狼
	 * @创建时间: 2017年11月8日 上午11:16:19
	 * @版本: V1.0
	 * @since: JDK 1.7
	 */
	public enum SysStatusType {

		/**
		 * 禁用，隐藏
		 */
		DISABLE(0),

		/**
		 * 可用，显示
		 */
		ENABLE(1),

		/**
		 * 显示
		 */
		SHOW(1),

		/**
		 * 隐藏
		 */
		HIDDEN(0);

		private int value;

		private SysStatusType(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}
	}
	
	/**
	 * 
	 * 把今天最好的表现当作明天最新的起点．．～
	 *
	 * Today the best performance as tomorrow newest starter!
	 *
	 * @类描述: 日志类型
	 * @创建者: 皇族灬战狼
	 * @创建时间: 2017年11月9日 下午5:33:11
	 * @版本: V1.0
	 * @since: JDK 1.7
	 */
	public enum SyslogType {
		Log_Type_LOGIN("login"),
		Log_Type_EXIT("exit"),
		Log_Type_INSERT("insert"),
		Log_Type_DEL("delete"),
		Log_Type_UPDATE("update"),
		Log_Type_UPLOAD("upload"),
		LOG_TYPE_SELECT("select"),
		LOG_TYPE_DOWNLOAD("download"),
		Log_Type_OTHER("other");
		private String value;

		private SyslogType(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}
	}
}
