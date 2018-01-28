package com.cdeledu.service.api.token;

import com.cdeledu.model.api.token.TokenEntity;

public interface TokenService {
	/**
	 * @方法:查询token
	 * @param userId
	 * @return
	 */
	TokenEntity queryByUser(Long userId);
	/**
	 * @方法:查询token
	 * @param token
	 * @return
	 */
	TokenEntity queryByToken(String token);
	/**
	 * 生成token
	 * @param userId  用户ID
	 * @return        返回token信息
	 */
	TokenEntity createToken(long userId);

	/**
	 * 设置token过期
	 * @param userId 用户ID
	 */
	void expireToken(long userId);
}
