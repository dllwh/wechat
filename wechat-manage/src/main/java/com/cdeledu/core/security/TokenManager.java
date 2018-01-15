package com.cdeledu.core.security;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: Token进行管理的接口
 * @创建者: 皇族灬战狼
 * @联系方式: duleilewuhen@sina.com
 * @创建时间: 2018年1月6日 下午4:46:34
 * @版本: V1.0
 * @since: JDK 1.7
 */
public interface TokenManager {
	/** 通过key删除关联关系 */
	void delRelationshipByKey(String key);

	/** 通过token删除关联关系 */
	void delRelationshipByToken(String token);

	/** 创建关联关系 */
	void createRelationship(String key, String token);

	/** 通过token获得对应的key */
	String getKeyByToken(String token);
}
