package com.cdeledu.util.openplatform.livevideo.entity.bokecc;

import java.io.Serializable;

public class LiveUserAction implements Serializable {
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
		return "LiveUserAction [userId=" + userId + ", userName=" + userName + ", userIp=" + userIp
				+ ", enterTime=" + enterTime + ", leaveTime=" + leaveTime + "]";
	}

}
