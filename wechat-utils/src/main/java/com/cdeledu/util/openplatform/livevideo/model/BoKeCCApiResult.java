package com.cdeledu.util.openplatform.livevideo.model;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 接口的返回格式
 * @创建者: 皇族灬战狼
 * @联系方式: duleilewuhen@sina.com
 * @创建时间: 2018年4月13日 上午11:25:30
 * @版本: V1.0
 * @since: JDK 1.7
 * @see <a href="doc.bokecc.com/live/dev/liveapi/#toc_5">接口的返回格式</a>
 */
public class BoKeCCApiResult implements Serializable {
	private static final long serialVersionUID = 1L;
	/** 状态码 */
	private String result;
	/** 错误原因 */
	private String reason;

	public boolean ifSuccess() {
		if (StringUtils.isNotBlank(result) && "ok".equalsIgnoreCase(result)) {
			return Boolean.TRUE;
		} else {
			return Boolean.FALSE;
		}
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	@Override
	public String toString() {
		return "BoKeCCApiResult [result=" + result + ", reason=" + reason + "]";
	}

}
