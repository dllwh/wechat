package com.cdeledu.util.openplatform.douyutv.factory;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.cdeledu.util.application.regex.RegexUtil;
import com.cdeledu.util.openplatform.douyutv.constants.MsgType;

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
public final class DouyuTCPDecoder {
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
	 * @方法描述 : 是否是错误返回
	 * @return
	 */
	public static boolean ifErrorRespond(String data) {
		if (MsgType.ERROR.equalsIgnoreCase(getMsgType(data))) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * @方法:获取错误状态码
	 * @创建人:独泪了无痕
	 * @param source
	 * @return
	 */
	public static String getErrorCode(String source) {
		return RegexUtil.getKeyWords("/code@=(.*?)/", source, 1);
	}

	/**
	 * @方法描述 : 是否登陆成功
	 * @return
	 */
	public static boolean ifLoginSucc(String data) {
		if (MsgType.LOGIN_RES.equalsIgnoreCase(getMsgType(data))) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * @方法描述 : 解析登录请求返回结果
	 * @return
	 */
	@Deprecated
	public static void parseLoginRespond(String data) {

	}

	/**
	 * @方法描述 : 解析弹幕服务器接收到的协议数据 *
	 *       <p>
	 *       为增强兼容性、可读性斗鱼后台通讯协议采用文本形式的明文数据。同时针 对本平台数据特点，斗鱼自创序列化、反序列化算法。即 STT
	 *       序列化。下面详 细介绍 STT 序列化和反序列化。STT 序列化支持键值对类型、数组类型。规定<br>
	 *       如下：<br>
	 *       1. 键 key 和值 value 直接采用‘@=’分割<br>
	 *       2. 数组采用‘/’分割<br>
	 *       3. 如果 key 或者 value 中含有字符‘/’，则使用‘@S’转义<br>
	 *       4. 如果 key 或者 value 中含有字符‘@’，使用‘@A’转义<br>
	 * @param data
	 * @return
	 */
	public static Map<String, Object> parseRespond(String data) {
		Map<String, Object> resultMap = new HashMap<String, Object>();

		// 处理数据字符串末尾的'/0字符'
		// data = StringUtils.substringBeforeLast(data, "/");

		// 对数据字符串进行拆分
		String[] buff = data.split("/");

		// 解析协议字段中的key和value值
		for (String tmp : buff) {
			if (StringUtils.isBlank(tmp)) {
				continue;
			}

			String key = StringUtils.substringBefore(tmp, "@=");
			Object value = StringUtils.substringAfter(tmp, "@=");

			if (value != null) {
				// if(value instanceof String){
				// 如果value值中包含子序列化值，则进行递归解析
				if (StringUtils.contains((String) value, "@A")) {
					value = deFilterStr((String) value);
					value = parseRespond((String) value);
				}
			}
			// 将解析后的键值对添加到信息列表中
			resultMap.put(key, value);
		}
		return resultMap;
	}

	public static String deFilterStr(String str) {
		if (StringUtils.isBlank(str)) {
			return str;
		}
		return str.replaceAll("@S", "/").replaceAll("@A", "@");
	}
}