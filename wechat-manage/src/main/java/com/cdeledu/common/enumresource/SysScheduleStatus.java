package com.cdeledu.common.enumresource;

import com.cdeledu.common.baseInterface.ReturnCode;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 定时任务状态
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2018年1月7日 下午7:35:27
 * @版本: V1.0
 * @since: JDK 1.7
 */
public enum SysScheduleStatus implements ReturnCode {
	/** 定时任务状态 */
	NORMAL(1, "正常"), PAUSE(0, "暂停");

	private final Integer code;
	private final String value;

	private SysScheduleStatus(Integer code, String value) {
		this.code = code;
		this.value = value;
	}

	@Override
	public Integer getCode() {
		return code;
	}

	@Override
	public String getMessage() {
		return value;
	}
}
