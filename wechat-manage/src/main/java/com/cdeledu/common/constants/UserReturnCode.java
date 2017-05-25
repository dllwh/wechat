package com.cdeledu.common.constants;

import com.cdeledu.common.baseInterface.ReturnCode;

public enum UserReturnCode implements ReturnCode {
	/** ----------------------------------------------------- Fields start */
	user_not_exist(1000, "该账号不存在!"), //
	user_suspend(10001, "该账号已被冻结!"), //
	wrong_password(10002, "用户名或密码错误,请重新登录!"), //
	account_lock(10003, "密码连续输入错误超过5次，锁定半小时!"), //
	register_code_error(10004, "验证码错误，请重新输入"), //
	password_authentication_error(10005, "密码长度8~16位，其中数字，字母和符号至少包含两种!"), //
	account_error(10006, "该用户名已被使用!");//
	/** ----------------------------------------------------- Fields end */

	/** ----------------------------------------------- [私有方法] */
	/** 返回状态码 */
	private Integer code;

	/** 返回消息 */
	private String message;

	private UserReturnCode(Integer code, String message) {
		this.code = code;
		this.message = message;
	}

	/** ----------------------------------------------- [私有方法] */

	public Integer getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

}
