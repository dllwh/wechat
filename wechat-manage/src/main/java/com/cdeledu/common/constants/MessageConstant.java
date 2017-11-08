package com.cdeledu.common.constants;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 常量提示语
 * @创建者: 皇族灬战狼
 * @创建时间: 2017年10月9日 下午1:44:02
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class MessageConstant {
	/**
	 * 登录成功
	 */
	public static final String LOGIN_SUCCESS = "LOGIN_SUCCESS";
	/**
	 * 用户名或密码错误
	 */
	public static final String LOGIN_USER_REEOE = "LOGIN_USER_REEOE";
	/**
	 * 用户被锁定
	 */
	public static final String LOGIN_USER_LOCK = "LOGIN_USER_LOCK";
	/**
	 * 密码错误次数已达上限
	 */
	public static final String LOGIN_USER_MORE = "LOGIN_USER_MORE";
	/**
	 * 用户审核
	 */
	public static final String LOGIN_USER_NOCHECK = "LOGIN_USER_NOCHECK";

	/**
	 * 操作成功
	 */
	public static final String MSG_OPERATION_SUCCESS = "操作成功！";

	/**
	 * 操作失败
	 */
	public static final String MSG_OPERATION_FAILED = "操作失败！";

	/**
	 * 删除时，提示有子节点无法删除
	 */
	public static final String MSG_HAS_CHILD = "操作失败，当前所选数据有子节点数据！";

	/**
	 * 加载表单数据错误提示
	 */
	public static final String MSG_INIT_FORM = "初始化表单数据失败，请重试！";

	/**
	 * 
	 * @方法描述: 删除数据项不是全部所选
	 * @param total
	 * @param process
	 * @return
	 */
	public static String removeFailed(int total, int process) {
		return "本次共处理：" + String.valueOf(total) + "条，成功：" + String.valueOf(process) + "条！";
	}
}
