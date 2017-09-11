package com.cdeledu.common.constant;

/**
 * 
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 状态码
 * @创建者: 皇族灬战狼
 * @创建时间: 2017年9月11日 下午3:41:50
 * @版本: V1.0
 * @since: JDK 1.7
 */
public enum CodeConst {
	SUCCESS(200, "成功"), 
	NOT_FOUNT(404, "找不到"), 
	REPEAT(992, "数据重复"), 
	CodeOR(993,"系统错误"), 
	ADMIN_USER_REPEAT(994, "后台用户名重复"), 
	NULL_DATA(995, "没有数据"), 
	TIME_PASSED(996,"时间己过期"),
	USER_NOT_FOUND(997,"找不到用户"), 
	USER_REPEAT(998, "用户重复"), 
	AUTH_FAILED(999, "用户名或密码错误");

	private int resultCode;
	private String message;

	private CodeConst(int resultCode, String message) {
		this.resultCode = resultCode;
		this.message = message;
	}

	public int getResultCode() {
		return resultCode;
	}

	public String getMessage() {
		return message;
	}
}
