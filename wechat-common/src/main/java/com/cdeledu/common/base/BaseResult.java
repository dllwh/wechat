package com.cdeledu.common.base;

import java.io.Serializable;

/**
 * @类描述: BaseResult 统一返回结果类
 * @创建者: 皇族灬战狼
 * @创建时间: 2017年5月20日 下午5:24:11
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class BaseResult implements Serializable {

	private static final long serialVersionUID = 1L;
	/** 返回状态码 */
	private Integer code;

	/** 返回信息 */
	private String message;

	/** 返回数据 */
	private Object data;

	public BaseResult(Integer code) {
		this.code = code;
	}

	public BaseResult(Integer code, String message) {
		this.code = code;
		this.message = message;
	}

	public BaseResult(Integer code, String message, Object data) {
		this.code = code;
		this.message = message;
		this.data = data;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
}
