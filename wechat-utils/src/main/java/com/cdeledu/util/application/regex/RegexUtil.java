package com.cdeledu.util.application.regex;

import java.util.Collection;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import com.cdeledu.common.property.PropertyHelper;

/**
 * @描述: 对字符串按照常用规则进行验证的工具类(正则表达式)
 *      <ul>
 *      <li>1.匹配 email 地址</li>
 *      <li></li>
 *      </ul>
 * @author: 独泪了无痕
 * @date: 2015年8月27日 下午5:03:11
 * @version: V1.0
 * @history:
 */
public class RegexUtil {
	/** --------------------------私有属性 start------------------------------- */
	static Map<String, String> expMap = null;

	static {
		expMap = PropertyHelper.getMapByProperties("properties/expression/expression.properties");

	}

	/** --------------------------私有属性 end------------------------------- */

	/** --------------------------私有方法 start------------------------------- */
	/**
	 * @Title：checkByFind
	 * @Description：正则表达式 匹配
	 * @param regex
	 *            正则表达式
	 * @param content
	 *            要验证的内容
	 * @return
	 * @return：boolean 返回类型
	 */
	private static boolean checkByFind(String regex, String content) {
		boolean flag = false;
		try {
			Pattern patter = Pattern.compile(regex);
			Matcher matcher = patter.matcher(content);
			flag = matcher.find();
		} catch (Exception e) {
		}
		return flag;
	}

	/**
	 * @Title: getKeyWords
	 * @Description: 获得匹配的字符串
	 * @param regex
	 *            匹配的正则
	 * @param content
	 *            被匹配的内容
	 * @param groupIndex
	 *            匹配正则的分组序号:第几个分组, 从1开始, 0代表不分组
	 * @return 匹配后得到的字符串，未匹配返回null
	 * @return:String 返回类型
	 */
	public static String getKeyWords(String regex, String content, int groupIndex) {
		Matcher matcher = Pattern.compile(regex, Pattern.MULTILINE).matcher(content);
		if (matcher.find()) {
			if (groupIndex == 0) {
				return matcher.replaceAll("").trim();
			} else {
				return matcher.group(groupIndex);
			}
		}
		return null; // 必须是null,否则会进入死循环
	}

	/**
	 * @方法描述: 提取 关键字
	 * @param sInput
	 *            输入内容
	 * @param sRegex
	 *            表达式字符串
	 * @return 若是没有匹配内容，则返回空
	 */
	public static String getKeyWords(String regex, String sInput) {
		Matcher matcher = Pattern.compile(regex).matcher(sInput);

		if (matcher.find()) {
			// return matcher.group(1);
			return matcher.replaceAll("").trim();
		}
		return "";
	}

	/**
	 * 
	 * @Title: isRegexMatch
	 * @Description: 判断字符串是否匹配了正则表达式
	 * @param str
	 *            字符串
	 * @param regex
	 *            正则表达式
	 * @return true/false
	 */
	public static boolean isRegexMatch(String str, String regex) {
		// return str != null && str.matches(regex);
		Pattern pattern = Pattern.compile(str);
		Matcher isNum = pattern.matcher(str);
		return isNum.matches();
	}

	/** --------------------------私有方法 end------------------------------- */

	/** --------------------------公有方法 start------------------------------- */
	/** 是否为中国移动手机号码 */
	public static boolean isChinaMobile(String str) {
		return isRegexMatch(str, expMap.get("4_移动手机号码"));
	}

	/** 是否为中国联通手机号码 */
	public static boolean isChinaUnicom(String str) {
		return isRegexMatch(str, expMap.get("5_联通手机号码"));
	}

	/** 判断是否为电信手机 */
	public static boolean isChinaTelecom(String str) {
		return isRegexMatch(str, expMap.get("6_电信手机号码"));
	}

	/** 判断是否为小灵通手机 */
	public static boolean isChinaPAS(String str) {
		return isChinaTelecom(str);
	}

	/** 是否为手机号码:包括移动、联通、小灵通等手机号码 */
	public static boolean isMobile(String str) {
		return isChinaMobile(str) || isChinaUnicom(str) || isChinaPAS(str);
	}

	/** 是否为正确合法的电话号码 */
	public static boolean isPhoneNumber(String str) {
		return isRegexMatch(str, expMap.get("8_电话号码"));
	}

	/** 是否是合法的身份证号 */
	public static boolean isIdCardNumber(String str) {
		// 15位或18数字, 14数字加x(X)字符或17数字加x(X)字符才是合法的
		return isRegexMatch(str, expMap.get("7_身份证号"));
	}

	/**
	 * @方法描述: 验证邮箱
	 *        <ul>
	 *        <li>匹配 email 地址</li>
	 *        <li>① 可用字母、数字、英文句点、减号、下划线</li>
	 *        <li>② 不能全部是数字</li>
	 *        <li>③ 长度为3到18</li>
	 *        <li>④ 不能以点、减号或下划线结尾</li>
	 *        <li>⑤不能出现连续两个或两个以上的点、减号或下划线</li>
	 *        </ul>
	 * @param email
	 *            待验证的字符串
	 * @return 如果是符合的字符串,返回 <b>true </b>,否则为 <b>false </b>
	 */
	public static boolean isEmail(String email) {
		return isRegexMatch(email, expMap.get("9_Email地址"));
	}

	/** 验证身份证 */
	public static boolean isUserIdentity(String str) {
		return isRegexMatch(str, expMap.get("13_userIdentity"));
	}

	/** 判断是否为正确IP */
	public static boolean isIp(String ipAddress) {
		if (ipAddress.length() < 7 || ipAddress.length() > 15 || "".equals(ipAddress)) {
			return false;
		}
		return checkByFind(ipAddress, expMap.get("11_ip"));
	}

	/** 判断该字符串是否是IPV4地址 */
	public static boolean isIpv4(String ip) {
		if (StringUtils.isNotBlank(ip)) {
			return false;
		}
		return Pattern.matches(ip, expMap.get("12_IPV4"));
	}

	/** 是否是简体中文字符串 */
	public static boolean isSimpleChinese(String str) {
		return isRegexMatch(str, expMap.get("3_简体中文"));
	}

	/** 是否全是有英文字母(中文的拼音)组成 */
	public static boolean isEnglist(String content) {
		String regex = "^[A-Za-z]+$";
		return Pattern.matches(regex, content);
	}

	/** 验证网址Url */
	public static boolean isUrl(String str) {
		return isRegexMatch(str, expMap.get("14_url"));
	}

	/** 验证输入邮政编号 */
	public static boolean isPostalcode(String str) {
		return isRegexMatch(str, expMap.get("15_postalCode"));
	}

	/** 验证输入两位小数 */
	public static boolean isDecimal(String str) {
		return isRegexMatch(str, expMap.get("16_isDecimal"));
	}

	/** 验证输入一年的12个月 */
	public static boolean isMonth(String str) {
		return isRegexMatch(str, expMap.get("17_isMonth"));
	}

	/** 验证输入一个月的31天 */
	public static boolean isDay(String str) {
		return isRegexMatch(str, expMap.get("18_isDay"));
	}

	/** 验证日期时间 */
	public static boolean isDate(String str) {
		return isRegexMatch(str, expMap.get("19_isDate"));
	}

	/**
	 * @Title: delFirst
	 * @Description: 删除匹配的内容
	 * @param regex
	 *            正则
	 * @param content
	 *            被匹配的内容
	 * @return 删除后剩余的内容
	 * @return:String 返回类型
	 */
	public static String delFirst(String regex, String content) {
		return content.replaceFirst(regex, "");
	}

	/**
	 * @Title: delPreLocation
	 * @Description: 删除正则匹配到的内容之前的字符 如果没有找到，则返回原文
	 * @param regex
	 *            定位正则
	 * @param content
	 *            被查找的内容
	 * @return 删除前缀后的新内容
	 * @return:String 返回类型
	 */
	public static String delPreLocation(String regex, String content) {
		Matcher matcher = Pattern.compile(regex, Pattern.MULTILINE).matcher(content);
		if (matcher.find()) {
			return content.substring(matcher.end(), content.length());
		}
		return content;
	}

	/**
	 * @Title: gets
	 * @Description: 取得内容中匹配的所有结果
	 * @param regex
	 *            正则
	 * @param content
	 *            被查找的内容
	 * @param group
	 *            正则的分组
	 * @param collection
	 *            返回的集合类型
	 * @return
	 * @return:T 返回类型
	 */
	public static <T extends Collection<String>> T getList(String regex, String content, int group,
			T collection) {
		while (true) {
			String result = getKeyWords(regex, content, group);
			if (result == null) {
				break;
			}
			collection.add(result);
			content = delPreLocation(regex, content);
		}
		return collection;
	}
	/*--------------------------公有方法 end-------------------------------*/
}
