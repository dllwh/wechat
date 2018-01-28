package com.cdeledu.service.api.token.impl;

import java.util.Date;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.cdeledu.core.redis.RedisKeys;
import com.cdeledu.model.api.token.TokenEntity;
import com.cdeledu.service.api.token.TokenService;

@Service("tokenService")
public class TokenServiceImpl implements TokenService {
	/** ----------------------------------------------------- Fields start */
	/** 2小时后过期 */
	private final static int EXPIRE = 3600 * 2;

	/** ----------------------------------------------------- Fields end */
	
	@Override
	public TokenEntity queryByUser(Long userId) {
		RedisKeys.getTokenKey(Long.toString(userId));
		return null;
	}

	@Override
	public TokenEntity queryByToken(String token) {
		return null;
	}
	
	@Override
	public TokenEntity createToken(long userId) {
		// 当前时间
		Date now = new Date();
		// 过期时间
		Date expireTime = new Date(now.getTime() + EXPIRE * 1000);

		// 生成token
		String token = generateToken();

		// 保存或更新用户token
		TokenEntity tokenEntity = new TokenEntity();
		tokenEntity.setUserId(userId);
		tokenEntity.setToken(token);
		tokenEntity.setUpdateTime(now);
		tokenEntity.setExpireTime(expireTime);
		this.insertOrUpdate(tokenEntity);
		return tokenEntity;
	}

	@Override
	public void expireToken(long userId) {
		Date now = new Date();
		TokenEntity tokenEntity = new TokenEntity();
		tokenEntity.setUserId(userId);
		tokenEntity.setUpdateTime(now);
		tokenEntity.setExpireTime(now);
		this.insertOrUpdate(tokenEntity);
	}

	private void insertOrUpdate(TokenEntity tokenEntity) {
		RedisKeys.getTokenKey(Long.toString(tokenEntity.getUserId()));
	}

	private String generateToken() {
		return UUID.randomUUID().toString().replace("-", "");
	}
}
