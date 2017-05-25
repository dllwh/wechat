package com.cdeledu.common.constants;

import com.cdeledu.common.baseInterface.ReturnCode;

public enum CommonReturnCode implements ReturnCode {
	/** ----------------------------------------------------- Fields start */

	/** 请求失败 */
	failed(0, "failed"), //
	/** 请求成功 */
	success(1, "success"), /// ** 200请求成功 */
	ok(200, "请求成功"), //
	/** 207频繁操作 */
	multi_status(207, "频繁操作"), //

	/** 303登录失败 */
	login_fail(303, "登录失败 "), //

	/** 400请求参数出错 */
	bad_request(400, "请求参数出错"), //
	/** 401没有登录 */
	unauthorized(401, "您未登录或者登录已超时,请先登录!"), //
	/** 403没有权限 */
	forbidden(403, "没有权限"), //
	/** 404找不到页面 */
	not_found(404, "找不到页面"), //
	/** 408请求超时 */
	request_timeout(408, "请求超时"), //

	/** 500服务器出错 */
	internal_server_error(500, "服务器出错");
	/** ----------------------------------------------------- Fields end */

	/** ----------------------------------------------- [私有方法] */
	/** 返回状态码 */
	private Integer code;

	/** 返回消息 */
	private String message;

	private CommonReturnCode(Integer code, String message) {
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
