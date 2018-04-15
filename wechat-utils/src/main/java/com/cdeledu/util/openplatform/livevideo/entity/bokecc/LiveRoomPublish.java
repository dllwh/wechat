package com.cdeledu.util.openplatform.livevideo.entity.bokecc;

import java.io.Serializable;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 直播间的直播状态
 * @创建者: 皇族灬战狼
 * @联系方式: duleilewuhen@sina.com
 * @创建时间: 2018年4月13日 下午5:36:32
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class LiveRoomPublish implements Serializable {
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
		return "LiveRoomPublish [liveStatus=" + liveStatus + ", startTime=" + startTime
				+ ", liveId=" + liveId + ", roomId=" + roomId + "]";
	}
}
