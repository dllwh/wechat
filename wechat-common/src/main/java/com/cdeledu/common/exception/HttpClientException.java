package com.cdeledu.common.exception;

/**
 * @ClassName: HttpClientException
 * @Description: HttpClient 异常处理
 * @author: 独泪了无痕
 * @date: 2015年9月24日 下午2:14:56
 * @version: V1.0
 * @history:
 */
public class HttpClientException extends Exception {
	private static final long serialVersionUID = 6001992679273749155L;

	/** 构造新的异常实例 */
	public HttpClientException(String msg) {
		super(msg);
	}

	/** 构造新的异常实例 */
	public HttpClientException(String msg, Throwable ex) {
		super(msg, ex);
	}
}
