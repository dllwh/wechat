package com.cdeledu.common.enumresource;

import com.cdeledu.common.baseInterface.EnumMessage;

public enum SexEnum implements EnumMessage {
	/** 性别 */
	MEN("1", "男"), WOMEN("2", "女"), UNKNOWN("0", "保密");

	private final String code;
	private final String value;

	private SexEnum(String code, String value) {
		this.code = code;
		this.value = value;
	}

	@Override
	public String getCode() {
		return code;
	}

	@Override
	public String getValue() {
		return value;
	}

}
