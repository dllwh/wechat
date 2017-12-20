package com.cdeledu.core.session;

import java.io.Serializable;
import java.util.Collection;

import org.apache.shiro.session.Session;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: Session操作
 * @创建者: 皇族灬战狼
 * @创建时间: 2017年11月10日 下午11:32:59
 * @版本: V1.0
 * @since: JDK 1.7
 */
public interface ShiroSessionRepository {
	/**
	 * @方法描述: 存储Session
	 * @param session
	 */
	void saveSession(Session session);

	/**
	 * @方法描述: 删除session
	 * @param sessionId
	 */
	void deleteSession(Serializable sessionId);

	/**
	 * @方法描述: 获取session
	 * @param sessionId
	 * @return
	 */
	Session getSession(Serializable sessionId);

	/**
	 * @方法描述: 获取所有sessoin
	 * @return
	 */
	Collection<Session> getAllSessions();
}
