package com.cdeledu.common.exception;

/**
 * @ClassName: ConnException
 * @Description: 数据库连接异常
 * @author: 独泪了无痕
 * @date: 2015年9月10日 下午5:17:58
 * @version: V1.0
 * @history:
 */
public class ConnException extends Exception {

	private static final long serialVersionUID = 7017096142583925612L;

	public ConnException(Throwable e) {
		super(e);
	}

	public ConnException(String message) {
		super(message);
	}

	public ConnException(String message, Throwable throwable) {
		super(message, throwable);
	}
}
