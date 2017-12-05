package com.cdeledu.core.shiro.listener;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;

import com.cdeledu.common.base.BaseClass;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: shiro 会话 监听,用于监听会话创建、过期及停止事件
 * @创建者: 皇族灬战狼
 * @创建时间: 2017年11月10日 下午11:35:59
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class CustomSessionListener extends BaseClass implements SessionListener {
	/** ----------------------------------------------------- Fields start */
	private static final long serialVersionUID = 1L;
	// private ShiroSessionRepository shiroSessionRepository;

	/** ----------------------------------------------------- Fields end */
	/**
	 * 一个回话的生命周期开始
	 */
	@Override
	public void onStart(Session session) {
		logger.info("----会话创建----：" + session.getId());
	}

	/**
	 * 一个回话的生命周期结束
	 */
	@Override
	public void onStop(Session session) {
		logger.info("----会话停止----：" + session.getId());
		// shiroSessionRepository.deleteSession(session.getId());
	}

	/**
	 * 会话过期时触发
	 */
	@Override
	public void onExpiration(Session session) {
		logger.info("----会话过期：----" + session.getId());
		// shiroSessionRepository.deleteSession(session.getId());
	}

	/*public void setShiroSessionRepository(ShiroSessionRepository shiroSessionRepository) {
		this.shiroSessionRepository = shiroSessionRepository;
	}
*/
}
