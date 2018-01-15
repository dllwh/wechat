package com.cdeledu.core.security.impl;

import com.cdeledu.core.security.TokenManager;

public abstract class AbstractTokenManager implements TokenManager {
	protected int tokenExpireSeconds = 7 * 24 * 3600;

	protected boolean singleTokenWithUser = true;

	protected boolean flushExpireAfterOperation = true;

	public void setTokenExpireSeconds(int tokenExpireSeconds) {
		this.tokenExpireSeconds = tokenExpireSeconds;
	}

	public void setSingleTokenWithUser(boolean singleTokenWithUser) {
		this.singleTokenWithUser = singleTokenWithUser;
	}

	public void setFlushExpireAfterOperation(boolean flushExpireAfterOperation) {
		this.flushExpireAfterOperation = flushExpireAfterOperation;
	}

	/**
	 * @方法描述 : 在操作后刷新Token的过期时间
	 * @param key
	 * @param token
	 */
	protected abstract void flushExpireAfterOperation(String key, String token);
}
