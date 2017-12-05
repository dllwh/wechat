package com.cdeledu.core.session;

import java.io.Serializable;

/**
 * 
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: Session 状态
 * @创建者: 皇族灬战狼
 * @创建时间: 2017年11月8日 下午4:31:12
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class SessionStatus implements Serializable {
	/** ----------------------------------------------------- Fields start */
	private static final long serialVersionUID = 1L;
	// 是否踢出 true:有效，false：踢出。
	private Boolean onlineStatus = Boolean.TRUE;

	/** ----------------------------------------------------- Fields end */
	public Boolean isOnlineStatus() {
		return onlineStatus;
	}

	public Boolean getOnlineStatus() {
		return onlineStatus;
	}

	public void setOnlineStatus(Boolean onlineStatus) {
		this.onlineStatus = onlineStatus;
	}
}
