package com.cdeledu.util.openplatform.douyutv.message;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.cdeledu.util.application.regex.RegexUtil;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 弹幕协议解析类
 * @创建者: 皇族灬战狼
 * @联系方式: duleilewuhen@sina.com
 * @创建时间: 2018年8月7日 下午7:32:32
 * @版本: V1.0
 * @since: JDK 1.7
 */
public final class MessageViewUtil {
	/**
	 * @方法描述 : 从消息中获取消息类型
	 * @param source
	 * @return
	 */
	public static String getMsgType(String source) {
		return RegexUtil.getKeyWords("type@=(.*?)/", source, 1);
	}

	/**
	 * @方法描述 : 从消息中获取弹幕内容
	 * @param source
	 * @return
	 */
	public static String getMsgText(String source) {
		return RegexUtil.getKeyWords("/txt@=(.*?)/", source, 1);
	}

	/**
	 * @方法描述 : 从消息中获取用户昵称
	 * @param source
	 * @return
	 */
	public static String getNickName(String source) {
		return RegexUtil.getKeyWords("/nn@=(.*?)/", source, 1);
	}

	/**
	 * @方法描述 : 解析弹幕服务器接收到的协议数据
	 * @param data
	 * @return
	 */
	public static Map<String, Object> parseRespond(String data) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		// 处理数据字符串末尾的'/0字符'
		data = StringUtils.substringBeforeLast(data, "/");

		String[] buff = data.split("/");

		for (String tmp : buff) {
			String key = StringUtils.substringBefore(tmp, "@=");
			Object value = StringUtils.substringAfter(tmp, "@=");
			// 如果value值中包含子序列化值，则进行递归分析
			if (StringUtils.contains((String) value, "@A")) {
				value = ((String) value).replaceAll("@S", "/").replaceAll("@A", "@");
				value = parseRespond((String) value);
			}
			resultMap.put(key, value);
		}
		return resultMap;
	}
}
