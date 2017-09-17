package com.cdeledu.template.codeMaker.config;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;

import org.apache.commons.lang3.StringUtils;

import com.cdeledu.common.constant.ConstantHelper;
import com.cdeledu.template.codeMaker.CodeHelper;

public class CodeMakerUtil {
	/** ----------------------------------------------------- Fields start */

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

	/**
	 * @方法:读取配置信息
	 * @创建人:独泪了无痕
	 * @param file
	 * @return
	 * @throws Exception
	 */
	public static String read(String file) throws Exception {
		InputStream in = null;
		InputStreamReader reader = null;
		try {
			in = CodeHelper.class.getResourceAsStream(file);
			reader = new InputStreamReader(in, ConstantHelper.UTF_8);
			StringWriter writer = new StringWriter();
			int len = -1;
			char[] buffer = new char[128];
			while ((len = reader.read(buffer)) != -1) {
				writer.write(buffer, 0, len);
			}
			writer.flush();
			return writer.toString();
		} finally {
			if (reader != null)
				reader.close();
		}
	}
}
