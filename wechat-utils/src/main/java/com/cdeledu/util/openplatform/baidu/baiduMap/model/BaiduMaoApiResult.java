package com.cdeledu.util.openplatform.baidu.baiduMap.model;

import java.io.Serializable;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 百度地址响应结果
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2018年4月20日 下午7:55:02
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class BaiduMaoApiResult implements Serializable {
	private static final long serialVersionUID = 1L;
	/** 结果状态返回码 */
	private Integer status;
	/** 错误原因 */
	private String message;

	public boolean ifSuccess() {
		if (null != status && 0 == status) {
			return Boolean.TRUE;
		} else {
			return Boolean.FALSE;
		}
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "BaiduMaoApiResult [status=" + status + ", message=" + message + "]";
	}
}
