package com.cdeledu.util.openplatform.livevideo.model.bokecc;

import java.util.List;

import com.cdeledu.util.openplatform.livevideo.model.BoKeCCApiResult;

public class LiveHistoryResponse extends BoKeCCApiResult {
	private static final long serialVersionUID = 1L;
	/** 页码 */
	private Integer pageIndex;
	/** 直播间总数 */
	private Integer count;
	/** 直播间列表信息 */
	private List<LiveHistory> lives;

	class LiveHistory {
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

	public Integer getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public List<LiveHistory> getLives() {
		return lives;
	}

	public void setLives(List<LiveHistory> lives) {
		this.lives = lives;
	}

	@Override
	public String toString() {
		return super.toString() + "\r\n LiveHistoryResponse [pageIndex=" + pageIndex + ", count="
				+ count + ", lives=" + lives + "]";
	}

}
