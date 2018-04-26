package com.cdeledu.util.openplatform.livevideo.model.bokecc;

import com.cdeledu.util.openplatform.livevideo.model.BoKeCCApiResult;

public class LiveUserViewResponse extends BoKeCCApiResult {
	private static final long serialVersionUID = 1L;

	/** 0：统计未完成，1：统计完成 */
	private Integer status;
	/** 查询直播ID */
	private String liveId;
	/** 直播最大并发人数 */
	private Integer maxConcurrent;
	/** 总观看数 */
	private Integer totalCount;
	/** 默认ua统计信息 */
	private UaCount uaCount;

	class UaCount {
		/** 默认ua统计PC观看总数 */
		private Integer pc;
		/** 默认ua统计Mobile观看总数 */
		private Integer mobile;

		public Integer getPc() {
			return pc;
		}

		public void setPc(Integer pc) {
			this.pc = pc;
		}

		public Integer getMobile() {
			return mobile;
		}

		public void setMobile(Integer mobile) {
			this.mobile = mobile;
		}

		@Override
		public String toString() {
			return "UaCount [pc=" + pc + ", mobile=" + mobile + "]";
		}
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getLiveId() {
		return liveId;
	}

	public void setLiveId(String liveId) {
		this.liveId = liveId;
	}

	public Integer getMaxConcurrent() {
		return maxConcurrent;
	}

	public void setMaxConcurrent(Integer maxConcurrent) {
		this.maxConcurrent = maxConcurrent;
	}

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	public UaCount getUaCount() {
		return uaCount;
	}

	public void setUaCount(UaCount uaCount) {
		this.uaCount = uaCount;
	}

	@Override
	public String toString() {
		return super.toString() + "\r\n LiveUserViewResponse [status=" + status + ", liveId="
				+ liveId + ", maxConcurrent=" + maxConcurrent + ", totalCount=" + totalCount
				+ ", uaCount=" + uaCount + "]";
	}

}
