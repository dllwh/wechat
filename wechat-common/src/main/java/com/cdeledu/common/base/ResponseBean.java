package com.cdeledu.common.base;

import java.util.Map;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 响应实体
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2018年9月5日 下午10:54:23
 * @版本: V1.0
 * @since: JDK 1.7
 */
public final class ResponseBean {
	private boolean success = true;// 是否成功
	private Integer resultCode = 200;// 状态码，默认是200
	private String msg = "操作成功";// 提示信息
	private Object obj = null;// 其他信息
	private Map<String, Object> attributes;// 其他参数

	public ResponseBean() {
		this.success = true;
		this.resultCode = 200;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public Integer getResultCode() {
		return resultCode;
	}

	public void setResultCode(Integer resultCode) {
		this.resultCode = resultCode;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}

	public Map<String, Object> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<String, Object> attributes) {
		this.attributes = attributes;
	}

	@Override
	public String toString() {
		return "AjaxJson [success=" + success + ", resultCode=" + resultCode + ", msg=" + msg
				+ ", obj=" + obj + ", attributes=" + attributes + "]";
	}
}
