package com.cdeledu.util.openplatform.livevideo.model.bokecc;

import java.util.List;

import com.cdeledu.util.openplatform.livevideo.model.BoKeCCApiResult;

public class ReplayUserActionsResponse extends BoKeCCApiResult {
	private static final long serialVersionUID = 1L;
	/** 数据总条数 */
	private Integer count;
	/** 当前页码 */
	private Integer pageIndex;
	/** 用户登录、退出行为记录 */
	private List<UserActions> userActions;

	class UserActions {
		/** 回放Id */
		private String recordId;
		/** 直播间Id */
		private String roomId;
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

		public String getRecordId() {
			return recordId;
		}

		public void setRecordId(String recordId) {
			this.recordId = recordId;
		}

		public String getRoomId() {
			return roomId;
		}

		public void setRoomId(String roomId) {
			this.roomId = roomId;
		}

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
			return "UserActions [recordId=" + recordId + ", roomId=" + roomId + ", userId=" + userId
					+ ", userName=" + userName + ", userIp=" + userIp + ", enterTime=" + enterTime
					+ ", leaveTime=" + leaveTime + "]";
		}
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Integer getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}

	public List<UserActions> getUserActions() {
		return userActions;
	}

	public void setUserActions(List<UserActions> userActions) {
		this.userActions = userActions;
	}

	@Override
	public String toString() {
		return super.toString() + "\r\n  UserActionsResponse [count=" + count + ", pageIndex="
				+ pageIndex + ", userActions=" + userActions + "]";
	}
}
