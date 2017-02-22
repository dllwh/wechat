package com.cdeledu.util.network.common;

import com.cdeledu.common.constant.ConstantHelper;

/**
 * @描述 : 键值对参数
 * @author: 独泪了无痕
 * @date: 2015-10-15 下午08:07:03
 * @version: V1.0
 */
public class URLParameter extends NameValue {
	private static final long serialVersionUID = 1L;

	public URLParameter(String name, String value) {
		super(name, value);
	}

	public String encoding() {
		return String.format("%s=%s", URLEncodingUtil.encoding(getName(), ConstantHelper.UTF_8),
				URLEncodingUtil.encoding(getValue(), ConstantHelper.UTF_8));
	}

	@Override
	public String toString() {
		return String.format("【URLParameter name = %s,value=%S】", getName(), getValue());
	}

}
