package com.cdeledu.util.openplatform.livevideo.model.bokecc;

import java.util.List;

import com.cdeledu.util.openplatform.livevideo.entity.bokecc.LiveUserAction;
import com.cdeledu.util.openplatform.livevideo.model.BoKeCCApiResult;

public class LiveUserActionResponse extends BoKeCCApiResult {
	private static final long serialVersionUID = 1L;
	/** 直播间id */
	private String roomId;
	/** 用户登录、退出行为记录 */
	private List<LiveUserAction> userActions;

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
