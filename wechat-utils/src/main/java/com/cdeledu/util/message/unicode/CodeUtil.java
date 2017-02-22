package com.cdeledu.util.message.unicode;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

public class CodeUtil {
	/*--------------------------私有属性 start -------------------------------*/
	/*--------------------------私有属性 end   -------------------------------*/
	/*--------------------------私有方法 start -------------------------------*/
	/*--------------------------私有方法 end   -------------------------------*/
	/*--------------------------公有方法 start -------------------------------*/
	/**
	 * 
	 * @方法名称: 以utf-8格式编码
	 * @方法描述:转码
	 * 
	 * @param str
	 * @return
	 */
	public static String utf8Encode(String str) {
		if (StringUtils.isNotEmpty(str)
				&& str.getBytes().length != str.length()) {
			try {
				return URLEncoder.encode(str, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				throw new RuntimeException(
						"UnsupportedEncodingException occurred. ", e);
			}
		}
		return str;
	}

	/**
	 * 
	 * @方法名称: capitalizeFirstLetter
	 * @方法描述: 首字母大写
	 * 
	 * @param str
	 * @return
	 */
	public static String capitalizeFirstLetter(String str) {
		if (StringUtils.isEmpty(str)) {
			return str;
		}

		char c = str.charAt(0);
		return (!Character.isLetter(c) || Character.isUpperCase(c)) ? str
				: new StringBuilder(str.length())
						.append(Character.toUpperCase(c))
						.append(str.substring(1)).toString();
	}

	/**
	 * 
	 * @方法名称: UnicodeToString
	 * @方法描述: Unicode 字符 转换成String字符串
	 * 
	 * @param str
	 * @return
	 */
	public static String UnicodeToString(String str) {
		Pattern pattern = Pattern.compile("(\\\\u(\\p{XDigit}{4}))");
		Matcher matcher = pattern.matcher(str);
		while (matcher.find()) {
			char ch = (char) Integer.parseInt(matcher.group(2), 16);
			str = str.replace(matcher.group(1), ch + "");
		}
		return str;
	}
	/*--------------------------公有方法 end   -------------------------------*/
}
