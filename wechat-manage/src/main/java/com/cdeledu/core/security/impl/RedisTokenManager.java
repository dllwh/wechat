package com.cdeledu.core.security.impl;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 使用Redis存储Token
 * @创建者: 皇族灬战狼
 * @联系方式: duleilewuhen@sina.com
 * @创建时间: 2018年1月6日 下午4:47:38
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class RedisTokenManager extends AbstractTokenManager {
	/** ----------------------------------------------------- Fields start */
	/** Redis中Key的前缀 */
	protected static final String REDIS_KEY_PREFIX = "AUTHORIZATION_KEY_";
	/** Redis中Token的前缀 */
	protected static final String REDIS_TOKEN_PREFIX = "AUTHORIZATION_TOKEN_";

	/** ----------------------------------------------------- Fields end */

	@Override
	public void delRelationshipByKey(String key) {
	}

	@Override
	public void delRelationshipByToken(String token) {
	}

	@Override
	public void createRelationship(String key, String token) {

	}

	@Override
	public String getKeyByToken(String token) {
		return null;
	}

	@Override
	protected void flushExpireAfterOperation(String key, String token) {

	}

}
