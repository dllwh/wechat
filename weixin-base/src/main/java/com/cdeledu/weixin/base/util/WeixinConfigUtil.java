package com.cdeledu.weixin.base.util;

import java.io.File;
import java.util.ResourceBundle;

import com.alibaba.fastjson.JSON;
import com.cdeledu.weixin.base.model.WeixinAccount;

/**
 * @ClassName: WeixinConfigUtil
 * @Description: 公众号配置信息 weixinMpAccount.properties文件
 * @author: 独泪了无痕
 * @date: 2015年10月10日 下午2:18:25
 * @version: V1.0
 * @history:
 */
public class WeixinConfigUtil {
	private final static String CLASSPATH_PREFIX = "classpath:";
	private final static String CLASSPATH_VALUE;
	private final static ResourceBundle weixinBundle;

	static {
		File file = null;
		weixinBundle = ResourceBundle.getBundle("properties/weixinMpAccount");
		CLASSPATH_VALUE = Thread.currentThread().getContextClassLoader().getResource("").getPath();
		for (String key : weixinBundle.keySet()) {
			if (!key.endsWith(".path")) {
				continue;
			}
			file = new File(getValue(key).replaceFirst(CLASSPATH_PREFIX, CLASSPATH_VALUE));
			if (!file.exists() && !file.mkdirs()) {
				System.err.append(String.format("%s create fail.%n", file.getAbsolutePath()));
			}
		}
	}

	/**
	 * 获取weixinMpAccount.properties文件中的key值
	 */
	public static String getValue(String key) {
		return weixinBundle.getString(key);
	}

	/**
	 * @方法描述：key不存在时则返回传入的默认值
	 * @param key
	 * @param defaultValue
	 * @return
	 * @返回类型：String
	 */
	public static String getValue(String key, String defaultValue) {
		String value = defaultValue;
		try {
			value = getValue(key);
		} catch (Exception e) {
		}
		return value;
	}

	/**
	 * 获取weixinMpAccount.properties文件中的账号信息
	 */
	public static WeixinAccount getWeixinAccount() {
		WeixinAccount account = null;
		try {
			account = JSON.parseObject(getValue("account"), WeixinAccount.class);
		} catch (Exception e) {
			System.err.println("'weixinMpAccount.account' key not found in weixinMpAccount.properties.");
		}
		return account;
	}
}
