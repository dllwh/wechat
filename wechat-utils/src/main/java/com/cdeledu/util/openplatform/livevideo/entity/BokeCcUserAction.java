package com.cdeledu.util.openplatform.livevideo.entity;

import java.io.Serializable;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 直播的统计信息
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2018年3月30日 下午10:27:22
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class BokeCcUserAction implements Serializable {
	private static final long serialVersionUID = 1L;
	/** 查询直播ID */
	private String liveId;
	/** 0：统计未完成，1：统计完成 */
	private Integer status;
	/** 直播最大并发人数 */
	private Integer maxConcurrent;
	/** 总观看数 */
	private Integer totalCount;
	/** 默认ua统计信息 */
	private Integer uaCount;
	/** 默认ua统计PC观看总数 */
	private Integer pc;
	/** 默认ua统计Mobile观看总数 */
	private Integer mobile;
	/** 用户自定义uatype统计观看数 */
	private String customUaCount;

	public String getLiveId() {
		return liveId;
	}

	public void setLiveId(String liveId) {
		this.liveId = liveId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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

	public Integer getUaCount() {
		return uaCount;
	}

	public void setUaCount(Integer uaCount) {
		this.uaCount = uaCount;
	}

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

	public String getCustomUaCount() {
		return customUaCount;
	}

	public void setCustomUaCount(String customUaCount) {
		this.customUaCount = customUaCount;
	}

	@Override
	public String toString() {
		return "BokeCcUserAction [liveId=" + liveId + ", status=" + status + ", maxConcurrent="
				+ maxConcurrent + ", totalCount=" + totalCount + ", uaCount=" + uaCount + ", pc="
				+ pc + ", mobile=" + mobile + ", customUaCount=" + customUaCount + "]";
	}
}
