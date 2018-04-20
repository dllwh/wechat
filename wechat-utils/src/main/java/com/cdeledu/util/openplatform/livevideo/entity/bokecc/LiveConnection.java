package com.cdeledu.util.openplatform.livevideo.entity.bokecc;

import java.io.Serializable;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 连接统计信息
 * @创建者: 皇族灬战狼
 * @联系方式: duleilewuhen@sina.com
 * @创建时间: 2018年4月20日 上午10:35:45
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class LiveConnection implements Serializable {
	private static final long serialVersionUID = 1L;
	private String time;
	private Integer count;
	private Integer replayCount;

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Integer getReplayCount() {
		return replayCount;
	}

	public void setReplayCount(Integer replayCount) {
		this.replayCount = replayCount;
	}
}
