package com.cdeledu.util.application.email;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import com.cdeledu.util.apache.lang.StringUtilHelper;

/**
 * 
 * @类名称：EmailUtils
 * @类描述：有邮箱相关的操作,包括读、写邮件,验证邮箱等
 * 
 * @创建人：dell
 *
 */
public class EmailUtil {
	/*--------------------------私有属性 start -------------------------------*/
	private static Map<String, String> hostMap = new HashMap<String, String>();
	static {
		/** 126 */
		hostMap.put("smtp.126", "smtp.126.com");
		/** qq */
		hostMap.put("smtp.qq", "smtp.qq.com");
		/** 163 */
		hostMap.put("smtp.163", "smtp.163.com");
		/** sina */
		hostMap.put("smtp.sina", "smtp.sina.com.cn");
		/** tom */
		hostMap.put("smtp.tom", "smtp.tom.com");
		/** 263 */
		hostMap.put("smtp.263", "smtp.263.net");
		/** yahoo */
		hostMap.put("smtp.yahoo", "smtp.mail.yahoo.com");
		/** hotmail */
		hostMap.put("smtp.hotmail", "smtp.live.com");
		/** gmail */
		hostMap.put("smtp.gmail", "smtp.gmail.com");
		hostMap.put("smtp.port.gmail", "465");
	}

	/*--------------------------私有属性 end   -------------------------------*/
	/*--------------------------私有方法 start -------------------------------*/
	/*--------------------------私有方法 end   -------------------------------*/
	/*--------------------------公有方法 start -------------------------------*/
	/**
	 * 
	 * @Title：getHideEmailPrefix
	 * @Description：隐藏邮件地址前缀。
	 * @param email
	 *            - EMail邮箱地址 例如: linwenguo@koubei.com 等等...
	 * @return 返回已隐藏前缀邮件地址, 如 *********@koubei.com.
	 * @return：String 返回类型
	 */
	public static String getHideEmailPrefix(String email) {
		if (StringUtils.isNotBlank(email)) {
			int index = email.lastIndexOf('@');
			if (index > 0) {
				email = StringUtilHelper.repeat("*", index).concat(
						email.substring(index));
			}
		}
		return email;
	}

	/**
	 * 
	 * @Title：getHost
	 * @Description：通过邮箱地址获取【发件服务器SMTP地址】
	 * @param email
	 * @return
	 * @throws Exception
	 * @return：String 返回类型
	 */
	public static String getHost(String email) throws Exception {
		Pattern pattern = Pattern.compile("\\w+@(\\w+)(\\.\\w+){1,2}");
		Matcher matcher = pattern.matcher(email);
		String key = "unSupportEmail";
		if (matcher.find()) {
			key = "smtp." + matcher.group(1);
		}
		if (hostMap.containsKey(key)) {
			return hostMap.get(key);
		} else {
			throw new Exception("unSupportEmail");
		}
	}

	/**
	 * 
	 * @Title：getSmtpPort
	 * @Description：通过邮箱地址获取【发件服务器SSL端口号,默认是25】
	 * @param email
	 * @return
	 * @throws Exception
	 * @return：int 返回类型
	 */
	public static int getSmtpPort(String email) throws Exception {
		Pattern pattern = Pattern.compile("\\w+@(\\w+)(\\.\\w+){1,2}");
		Matcher matcher = pattern.matcher(email);
		String key = "unSupportEmail";
		if (matcher.find()) {
			key = "smtp.port." + matcher.group(1);
		}
		if (hostMap.containsKey(key)) {
			return Integer.parseInt(hostMap.get(key));
		} else {
			return 25;
		}
	}
	/*--------------------------公有方法 end   -------------------------------*/
}
