package com.cdeledu.util.openplatform.livevideo.entity;

import java.io.Serializable;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 直播的中奖用户信息
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2018年3月30日 下午10:46:13
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class BokeCcLottery implements Serializable {
	private static final long serialVersionUID = 1L;
	/** 中奖时间 */
	private String time;
	/** 中奖顺序 */
	private Integer lotteryIndex;
	/** 中奖人名称 */
	private String viewerName;
	/** 中奖人Id */
	private String viewerId;
	/** 中奖码 */
	private String lotteryCode;

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public Integer getLotteryIndex() {
		return lotteryIndex;
	}

	public void setLotteryIndex(Integer lotteryIndex) {
		this.lotteryIndex = lotteryIndex;
	}

	public String getViewerName() {
		return viewerName;
	}

	public void setViewerName(String viewerName) {
		this.viewerName = viewerName;
	}

	public String getViewerId() {
		return viewerId;
	}

	public void setViewerId(String viewerId) {
		this.viewerId = viewerId;
	}

	public String getLotteryCode() {
		return lotteryCode;
	}

	public void setLotteryCode(String lotteryCode) {
		this.lotteryCode = lotteryCode;
	}

	@Override
	public String toString() {
		return "BokeCcLottery [time=" + time + ", lotteryIndex=" + lotteryIndex + ", viewerName="
				+ viewerName + ", viewerId=" + viewerId + ", lotteryCode=" + lotteryCode + "]";
	}
}
