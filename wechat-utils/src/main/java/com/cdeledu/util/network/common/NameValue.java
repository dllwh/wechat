package com.cdeledu.util.network.common;

import java.io.Serializable;

public class NameValue implements Serializable {
	private static final long serialVersionUID = 1L;
	private String name;
	private String value;

	public NameValue(String name, String value) {
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
