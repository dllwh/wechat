package com.cdeledu.util.openplatform.livevideo.entity;

import java.io.Serializable;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 直播的签到信息
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2018年3月30日 下午10:27:22
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class BokeCcRollcall implements Serializable {
	private static final long serialVersionUID = 1L;
	/** 总人数 */
	private Integer totalNum;
	/** 签到时长 */
	private Integer duration;
	/** 操作时间 */
	private String time;
	/** 签到ID */
	private String rollcallId;
	/** 签到人数 */
	private Integer checkedNum;

	public Integer getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(Integer totalNum) {
		this.totalNum = totalNum;
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getRollcallId() {
		return rollcallId;
	}

	public void setRollcallId(String rollcallId) {
		this.rollcallId = rollcallId;
	}

	public Integer getCheckedNum() {
		return checkedNum;
	}

	public void setCheckedNum(Integer checkedNum) {
		this.checkedNum = checkedNum;
	}

	@Override
	public String toString() {
		return "BokeCcRollcall [totalNum=" + totalNum + ", duration=" + duration + ", time=" + time
				+ ", rollcallId=" + rollcallId + ", checkedNum=" + checkedNum + "]";
	}
}
