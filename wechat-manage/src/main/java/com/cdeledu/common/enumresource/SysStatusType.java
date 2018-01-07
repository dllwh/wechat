package com.cdeledu.common.enumresource;

import com.cdeledu.common.baseInterface.ReturnCode;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 系统通用状态
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2018年1月7日 下午7:13:16
 * @版本: V1.0
 * @since: JDK 1.7
 */
public enum SysStatusType implements ReturnCode {
	SHOW(1, "显示"), HIDDEN(0, "隐藏"), ENABLE(1, "启用"), DISABLE(0, "禁用");
	private final Integer code;
	private final String value;

	private SysStatusType(Integer code, String value) {
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
