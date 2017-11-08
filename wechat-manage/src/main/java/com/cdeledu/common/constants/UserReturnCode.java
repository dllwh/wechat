package com.cdeledu.common.constants;

import com.cdeledu.common.baseInterface.ReturnCode;

public enum UserReturnCode implements ReturnCode {
	/** ----------------------------------------------------- Fields start */
	user_not_exist(1000, "该账号不存在!"), 
	user_locked(10001, "帐号已被锁定"), 
	wrong_password(10002, "用户名或密码错误,请重新登录!"), 
	Logon_fail_account_lock(10003, "登录失败次数过多"), 
	register_code_error(10004, "验证码错误，请重新输入"), 
	password_authentication_error(10005, "密码长度8~16位，其中数字，字母和符号至少包含两种!"), //
	account_error(10006, "该用户名已被使用!"),
	user_disable(10007, "帐号已被锁定"),
	user_overdue(10008, "帐号已被锁定"),
	Unauthorized(10009, "帐号已被锁定"); 
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
