package com.cdeledu.weixin.base.exception;

/**
 * @Description: 调用微信支付抛出的异常
 * @author: 独泪了无痕
 * @date: 2015年9月16日 下午7:45:21
 * @version: V1.0
 * @history:
 */
public class PayException extends WeixinException {

	private static final long serialVersionUID = 1L;

	public PayException(String errorMsg) {
		super(errorMsg);
	}

	public PayException(String errorCode, String errorMsg) {
		super(errorCode, errorMsg);
	}
}
