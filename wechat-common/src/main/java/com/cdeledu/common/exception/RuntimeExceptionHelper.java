package com.cdeledu.common.exception;

/**
 * @类描述: TODO(这里用一句话描述这个类的作用)
 * @创建者: 独泪了无痕
 * @创建日期: 2016年1月27日 下午11:06:23
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class RuntimeExceptionHelper extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public RuntimeExceptionHelper(Throwable e) {
		super(e.getMessage(), e);
	}

	public RuntimeExceptionHelper(String message) {
		super(message);
	}

	public RuntimeExceptionHelper(String messageTemplate, Object... params) {
		super(String.format(messageTemplate, params));
	}

	public RuntimeExceptionHelper(String message, Throwable throwable) {
		super(message, throwable);
	}

	public RuntimeExceptionHelper(Throwable throwable, String messageTemplate, Object... params) {
		super(String.format(messageTemplate, params), throwable);
	}
}
