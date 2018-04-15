package com.cdeledu.util.openplatform.livevideo.entity.bokecc;

import java.io.Serializable;

public class LiveHistory implements Serializable {
	private static final long serialVersionUID = 1L;
	/** 直播id */
	private String id;
	/** 开始时间 */
	private String startTime;
	/** 结束时间 */
	private String endTime;
	/** 录制状态，0表示录制未结束，1表示录制完成 */
	private Integer recordVideoStatus;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public Integer getRecordVideoStatus() {
		return recordVideoStatus;
	}

	public void setRecordVideoStatus(Integer recordVideoStatus) {
		this.recordVideoStatus = recordVideoStatus;
	}

	@Override
	public String toString() {
		return "LiveHistory [id=" + id + ", startTime=" + startTime + ", endTime=" + endTime
				+ ", recordVideoStatus=" + recordVideoStatus + "]";
	}

}
