package com.cdeledu.common.model;

/**
 * @类描述: $.ajax后需要接受的JSON
 * @创建者: 皇族灬战狼
 * @创建时间: 2016年8月8日 下午4:06:11
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class ValidForm {
	/** ----------------------------------------------------- Fields start */
	private String status = "y";// 是否成功
	private String info = "验证通过";// 提示信息

	/** ----------------------------------------------------- Fields end */
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}
}
