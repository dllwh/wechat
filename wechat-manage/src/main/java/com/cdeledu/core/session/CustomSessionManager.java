package com.cdeledu.core.session;

import java.util.List;

import org.apache.shiro.session.Session;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.support.DefaultSubjectContext;

import com.cdeledu.common.base.AjaxJson;
import com.cdeledu.common.constants.CacheConstans;
import com.cdeledu.model.rbac.SysUser;
import com.cdeledu.model.system.OnlineUser;
import com.google.common.collect.Lists;

public class CustomSessionManager {
	/** ----------------------------------------------------- Fields start */
	private ShiroSessionRepository shiroSessionRepository;

	/** ----------------------------------------------------- Fields end */

	/**
	 * @方法描述: 获取所有的有效Session用户
	 * @return
	 */
	public List<OnlineUser> getAllUser() {
		List<OnlineUser> onlineUserList = Lists.newArrayList();
		return onlineUserList;
	}

	/**
	 * @方法描述: 获取单个Session
	 * @param sessionId
	 * @return
	 */
	public OnlineUser getSession(String sessionId) {
		Session session = shiroSessionRepository.getSession(sessionId);
		return getSessionEntity(session);
	}

	/**
	 * @方法描述: 改变Session状态
	 * @param sessionId
	 * @param status
	 * @return
	 */
	public AjaxJson changeSessionStatus(String sessionId, Boolean status) {
		AjaxJson result = new AjaxJson();
		try {

		} catch (Exception e) {
			result.setSuccess(false);
			result.setResultCode(500);
			result.setMsg("改变失败，有可能Session不存在，请刷新再试！");
		}
		return result;

	}

	/**
	 * @方法描述: 查询要禁用的用户是否在线。
	 * @param id
	 * @param status
	 */
	public void forbidUserById(Integer id, Long status) {
		for (OnlineUser bo : getAllUser()) {
			if (bo.getId().equals(id)) {// 匹配用户
				// 获取用户Session
				Session session = shiroSessionRepository.getSession(bo.getSessionId());
				// 标记用户Session
				SessionStatus sessionStatus = (SessionStatus) session
						.getAttribute(CacheConstans.SESSION_STATUS);
				// 是否踢出 true:有效，false：踢出。
				sessionStatus.setOnlineStatus(status.intValue() == 1);
			}
		}

	}

	/** ----------------------------------------------- [私有方法] */
	private OnlineUser getSessionEntity(Session session) {
		/** 获取登录信息 */
		Object obj = session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
		if (null == obj) {
			return null;
		}
		if (obj instanceof SimplePrincipalCollection) {
			SimplePrincipalCollection spc = (SimplePrincipalCollection) obj;
			obj = spc.getPrimaryPrincipal();
			if (null != obj && obj instanceof SysUser) {
				OnlineUser onlineUser = new OnlineUser((SysUser) obj);
				// 最后一次和系统交互的时间
				onlineUser.setLastAccess(session.getLastAccessTime());
				// 主机的ip地址
				onlineUser.setHost(session.getHost());
				// session ID
				onlineUser.setSessionId(session.getId().toString());
				// 会话到期
				onlineUser.setTimeout(session.getTimeout());
				// 会话创建
				onlineUser.setStartTime(session.getStartTimestamp());
				SessionStatus sessionStatus = (SessionStatus) session
						.getAttribute(CacheConstans.SESSION_STATUS);
				boolean status = Boolean.TRUE;
				if (null != sessionStatus) {
					status = sessionStatus.getOnlineStatus();
				}
				onlineUser.setSessionStatus(status);
				return onlineUser;
			}
		}
		return null;
	}
	/** ----------------------------------------------- [私有方法] */

	/** ----------------------------------------------- [测试方法] */
	/** ----------------------------------------------- [测试方法] */
}
