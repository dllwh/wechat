package com.cdeledu.template.codeMaker.config;

import org.apache.commons.lang3.StringUtils;

public class CodeMakerUtil {
	/** ----------------------------------------------------- Fields start */
	/** 字符编码 */
	public static final String CHARSET = "UTF-8";
	/** ----------------------------------------------------- Fields end */

	/**
	 * @方法:首字母大写
	 * @创建人:独泪了无痕
	 * @param value
	 * @return
	 */
	public static String firstCharUpperCase(String value) {
		if (isValid(value)) {
			return value.substring(0, 1).toUpperCase() + value.substring(1);
		}
		return value;
	}

	/**
	 * @方法:首字母小写
	 * @创建人:独泪了无痕
	 * @param value
	 * @return
	 */
	public static String firstCharLowerCase(String value) {
		if (isValid(value)) {
			return value.substring(0, 1).toLowerCase() + value.substring(1);
		}
		return value;
	}

	/**
	 * @方法:判断对象是否有效
	 * @创建人:独泪了无痕
	 * @param value
	 * @return
	 */
	public static boolean isValid(Object value) {
		if (value == null) {
			return true;
		} else {
			return StringUtils.isNotBlank(value.toString());
		}
	}

	/**
	 * @方法:转换格式
	 * @创建人:独泪了无痕
	 * @param name
	 * @return
	 */
	public static String toFieldName(String name) {
		if (name == null) {
			return null;
		}
		String field = name.toLowerCase();
		String[] values = field.split("\\_");
		StringBuffer b = new StringBuffer(name.length());
		for (int i = 0; i < values.length; i++) {
			if (i == 0)
				b.append(values[i]);
			else
				b.append(firstCharUpperCase(values[i]));
		}

		return b.toString();
	}
}
