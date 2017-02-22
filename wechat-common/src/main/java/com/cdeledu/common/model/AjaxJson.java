package com.cdeledu.common.model;

import java.util.Map;

/**
 * @类描述: $.ajax后需要接受的JSON:封装Ajax结果,所有Ajax请求返回类型   
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建日期: 2016年4月4日 下午7:05:53
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class AjaxJson {
	/** ----------------------------------------------------- Fields start */
	private boolean success = true;// 是否成功
	private String msg = "操作成功";// 提示信息
	private Object obj = null;// 其他信息
	private Map<String, Object> attributes;// 其他参数

	/** ----------------------------------------------------- Fields end */
	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
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
}
