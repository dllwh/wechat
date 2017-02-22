package com.cdeledu.weixin.base.exception;

import org.apache.commons.lang3.StringUtils;

/**
 * 
 * @ClassName: WeixinException
 * @Description: 调用微信接口抛出的异常(微信异常处理类)
 * @author: 独泪了无痕
 * @date: 2015年8月20日 上午8:01:35
 * @version: V1.0
 * @history:
 */
public class WeixinException extends Exception {

	private static final long serialVersionUID = 3930955660335614049L;
	private String errorCode;
	private String errorMsg;

	public WeixinException(String errorCode, String errorMsg) {
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}

	public WeixinException(String errorMsg) {
		this.errorCode = "-1";
		this.errorMsg = errorMsg;
	}

	public WeixinException(Throwable e) {
		super(e);
	}

	public WeixinException(String message, Throwable cause) {
		super(message, cause);
	}

	public String getErrorCode() {
		return errorCode;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	@Override
	public String getMessage() {
		StringBuilder buf = new StringBuilder();
		if (StringUtils.isNotBlank(errorCode)) {
			buf.append(errorCode);
		}
		if (StringUtils.isNotBlank(errorMsg)) {
			buf.append(" ").append(errorMsg);
		}
		if (buf.length() == 0) {
			return super.getMessage();
		}
		return buf.toString();
	}
}
