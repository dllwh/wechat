package com.cdeledu.core.schedule;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 调度任务状态
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2018年7月21日 下午6:52:08
 * @版本: V1.0
 * @since: JDK 1.7
 */
public enum ScheduleRunState {
	// 未运行
	NORUN("0"),
	// 运行中
	RUNNING("1"),
	// 暂停
	PAUSE("2"),
	// 结束
	END("3"),
	// 停止
	STOP("4");
	String value = "";

	private ScheduleRunState(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
