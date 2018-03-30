package com.cdeledu.util.openplatform.livevideo.entity;

import java.io.Serializable;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 直播间内用户进出信息
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2018年3月30日 下午10:27:22
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class BokeCcUserView implements Serializable {
	private static final long serialVersionUID = 1L;
	/** 用户Id，此Id为登录、退出行为的用户Id */
	private String userId;
	/** 用户登录名称 */
	private String userName;
	/** 用户IP地址 */
	private String userIp;
	/** 用户进入时间 */
	private String enterTime;
	/** 用户离开时间 */
	private String leaveTime;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserIp() {
		return userIp;
	}

	public void setUserIp(String userIp) {
		this.userIp = userIp;
	}

	public String getEnterTime() {
		return enterTime;
	}

	public void setEnterTime(String enterTime) {
		this.enterTime = enterTime;
	}

	public String getLeaveTime() {
		return leaveTime;
	}

	public void setLeaveTime(String leaveTime) {
		this.leaveTime = leaveTime;
	}

	@Override
	public String toString() {
		return "BokeCcUserAction [userId=" + userId + ", userName=" + userName + ", userIp="
				+ userIp + ", enterTime=" + enterTime + ", leaveTime=" + leaveTime + "]";
	}
}
