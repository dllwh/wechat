package com.cdeledu.util.openplatform.livevideo.model.bokecc;

import java.util.List;

import com.cdeledu.util.openplatform.livevideo.model.BoKeCCApiResult;

public class LiveUserActionResponse extends BoKeCCApiResult {
	private static final long serialVersionUID = 1L;
	/** 直播间id */
	private String roomId;
	/** 用户登录、退出行为记录 */
	private List<LiveUserAction> userActions;

	class LiveUserAction {
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
			return "LiveUserAction [userId=" + userId + ", userName=" + userName + ", userIp="
					+ userIp + ", enterTime=" + enterTime + ", leaveTime=" + leaveTime + "]";
		}

	}

	public String getRoomId() {
		return roomId;
	}

	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

	public List<LiveUserAction> getUserActions() {
		return userActions;
	}

	public void setUserActions(List<LiveUserAction> userActions) {
		this.userActions = userActions;
	}

	@Override
	public String toString() {
		return super.toString() + "\r\n LiveUserActionResponse [roomId=" + roomId + ", userActions="
				+ userActions + "]";
	}

}
