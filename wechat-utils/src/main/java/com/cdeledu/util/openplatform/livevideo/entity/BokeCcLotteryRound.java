package com.cdeledu.util.openplatform.livevideo.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 直播的抽奖信息
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2018年3月30日 下午10:27:22
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class BokeCcLotteryRound implements Serializable {
	private static final long serialVersionUID = 1L;
	/** 抽奖ID */
	private String lotteryId;
	/** 中奖人数 */
	private Integer lotteryCount;
	/** 中奖用户信息 */
	private List<BokeCcLottery> lotteries;

	public String getLotteryId() {
		return lotteryId;
	}

	public void setLotteryId(String lotteryId) {
		this.lotteryId = lotteryId;
	}

	public Integer getLotteryCount() {
		return lotteryCount;
	}

	public void setLotteryCount(Integer lotteryCount) {
		this.lotteryCount = lotteryCount;
	}

	public List<BokeCcLottery> getLotteries() {
		return lotteries;
	}

	public void setLotteries(List<BokeCcLottery> lotteries) {
		this.lotteries = lotteries;
	}

	@Override
	public String toString() {
		return "BokeCcLotteryRound [lotteryId=" + lotteryId + ", lotteryCount=" + lotteryCount
				+ ", lotteries=" + lotteries + "]";
	}
}
