package com.cdeledu.core.security.impl;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 使用MySQL存储Token
 * @创建者: 皇族灬战狼
 * @联系方式: duleilewuhen@sina.com
 * @创建时间: 2018年1月6日 下午4:47:10
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class MySQLTokenManager extends AbstractTokenManager {
	/** ----------------------------------------------------- Fields start */
	/** 存放Key的字段名 */
	protected String keyColumnName;

	/** 存放Token的字段名 */
	protected String tokenColumnName;

	/** 存放过期时间的字段名 */
	protected String expireAtColumnName;

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
	public void flushExpireAfterOperation(String key, String token) {

	}

	/** ----------------------------------------------------- Fields end */
}
