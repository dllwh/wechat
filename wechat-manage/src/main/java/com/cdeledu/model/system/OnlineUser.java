package com.cdeledu.model.system;

import java.util.Date;

import com.cdeledu.model.rbac.SysUser;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 在线用户
 * @创建者: 皇族灬战狼
 * @创建时间: 2017年12月4日 下午3:43:04
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class OnlineUser extends SysUser {
	/** ----------------------------------------------------- Fields start */
	private static final long serialVersionUID = 1L;
	private String sessionId;
	private String host;
	/** Session创建时间 */
	private Date startTime;
	/** Session最后交互时间 */
	private Date lastAccess;
	/** Session timeout */
	private long timeout;
	/** session 是否踢出 */
	private boolean sessionStatus = Boolean.TRUE;

	/** ----------------------------------------------------- Fields end */

	public OnlineUser() {
	}

	public OnlineUser(SysUser sysUser) {
		super(sysUser);
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getLastAccess() {
		return lastAccess;
	}

	public void setLastAccess(Date lastAccess) {
		this.lastAccess = lastAccess;
	}

	public long getTimeout() {
		return timeout;
	}

	public void setTimeout(long timeout) {
		this.timeout = timeout;
	}

	public boolean isSessionStatus() {
		return sessionStatus;
	}

	public void setSessionStatus(boolean sessionStatus) {
		this.sessionStatus = sessionStatus;
	}
}
