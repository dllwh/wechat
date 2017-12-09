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
	/** 登录成功 */
	public static final String LOGIN_SUCCESS = "登录成功";
	/** 用户名或密码错误 */
	public static final String LOGIN_USER_REEOE = "用户名或密码错误";
	/** 用户被锁定 */
	public static final String LOGIN_USER_LOCK = "验证未通过,账户已锁定";
	/** 该账号不存在! */
	public static final String LOGIN_USER_UNKNOWN = "该账号不存在!";
	/** 帐号已被禁用 */
	public static final String LOGIN_USER_DISABLED = "验证未通过,帐号已被禁用";
	/** 密码错误次数已达上限 */
	public static final String LOGIN_USER_MORE = "验证未通过,用户名或密码错误次数过多";
	/** 用户审核 */
	public static final String LOGIN_USER_NOCHECK = "用户未通过审核";
	/** 用户未授权 */
	public static final String LOGIN_USER_UNAUTHORIZED = "验证未通过,您没有得到相应的授权！";
	/** 帐号已过期 */
	public static final String LOGIN_USER_EXPIRED = "验证未通过,帐号已过期";
	/** 进行登录验证..验证未通过 */
	public static final String LOGIN_ERROR = "进行登录验证..验证未通过";
	/** 操作成功 */
	public static final String MSG_OPERATION_SUCCESS = "操作成功！";
	/** 操作失败 */
	public static final String MSG_OPERATION_FAILED = "操作失败！";
	/** 删除时，提示有子节点无法删除 */
	public static final String MSG_HAS_CHILD = "操作失败，当前所选数据有子节点数据！";
	/** 加载表单数据错误提示 */
	public static final String MSG_INIT_FORM = "初始化表单数据失败，请重试！";
	/** 数据已存在 */
	public static final String EXISTED = "数据已经存在，请勿重复操作";
	/** 数据创建失败 */
	public static final String ERROR_CREATE = "初始化数据失败，请重试！";
	/** 不能删除超级管理员 */
	public static final String CANT_DELETE_ADMIN = "不能删除超级管理员";
	/** 不能冻结超级管理员 */
	public static final String CANT_FREEZE_ADMIN = "初始化数据失败，请重试！";
	/** 不能修改超级管理员角色 */
	public static final String CANT_CHANGE_ADMIN = "初始化数据失败，请重试！";

	/**
	 * @方法描述: 删除数据项不是全部所选
	 * @param total
	 * @param process
	 * @return
	 */
	public static String removeFailed(int total, int process) {
		return "本次共处理：" + String.valueOf(total) + "条，成功：" + String.valueOf(process) + "条！";
	}
}
