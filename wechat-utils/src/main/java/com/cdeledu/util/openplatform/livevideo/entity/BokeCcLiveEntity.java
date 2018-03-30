package com.cdeledu.util.openplatform.livevideo.entity;

import java.io.Serializable;

public class BokeCcLiveEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	/** 0：直播未开始，1：正在直播 */
	private String liveStatus;
	/** 直播开始时间，若直播未开始，不返回该参数 */
	private String startTime;
	/** 直播ID，若直播未开始，不返回该参数 */
	private String liveId;
	/** 直播间ID */
	private String roomId;

	public String getLiveStatus() {
		return liveStatus;
	}

	public void setLiveStatus(String liveStatus) {
		this.liveStatus = liveStatus;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getLiveId() {
		return liveId;
	}

	public void setLiveId(String liveId) {
		this.liveId = liveId;
	}

	public String getRoomId() {
		return roomId;
	}

	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

	@Override
	public String toString() {
		return "BokeccLiveEntity [liveStatus=" + liveStatus + ", startTime=" + startTime
				+ ", liveId=" + liveId + ", roomId=" + roomId + "]";
	}
}
