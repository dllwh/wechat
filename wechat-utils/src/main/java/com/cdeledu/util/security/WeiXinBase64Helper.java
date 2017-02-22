package com.cdeledu.util.security;

import java.nio.charset.Charset;

import javax.xml.bind.DatatypeConverter;

import com.cdeledu.common.weixin.WeiXinConstants;

/**
 * @类描述: JDK6之后 Base64工具
 * @创建者: 皇族灬战狼
 * @创建时间: 2016年7月12日 下午3:27:21
 * @版本: V1.0
 * @since: JDK 1.7
 * @see 参考：http://www.importnew.com/14961.html
 */
public class  WeiXinBase64Helper {
	/** ----------------------------------------------------- Fields start */
	/** ----------------------------------------------------- Fields end */

	/** ----------------------------------------------- [私有方法] */
	/**
	 * @方法描述: 解码
	 * @创建者: 皇族灬战狼
	 * @创建时间: 2016年7月12日 下午3:29:34
	 * @param 字符串
	 * @return {byte[]}
	 */
	private static byte[] decodeBase64(String value) {
		return DatatypeConverter.parseBase64Binary(value);
	}

	/** ----------------------------------------------- [私有方法] */
	/**
	 * @方法描述: 编码
	 * @创建者: 皇族灬战狼
	 * @创建时间: 2016年7月12日 下午2:58:27
	 * @param value
	 *            字符串
	 * @return
	 */
	public static String encode(String value) {
		byte[] val = value.getBytes(WeiXinConstants.UTF_8);
		return encode(val);
	}

	/**
	 * @方法描述: 编码
	 * @创建者: 皇族灬战狼
	 * @创建时间: 2016年7月12日 下午2:58:43
	 * @param value
	 *            字符串
	 * @param charsetName
	 *            编码格式
	 * @return
	 */
	public static String encode(String value, String charsetName) {
		byte[] val = value.getBytes(Charset.forName(charsetName));
		return encode(val);
	}

	/**
	 * @方法描述: 编码
	 * @创建者: 皇族灬战狼
	 * @创建时间: 2016年7月12日 下午2:58:51
	 * @param value
	 *            byte数组
	 * @return
	 */
	public static String encode(byte[] value) {
		return DatatypeConverter.printBase64Binary(value);
	}

	/**
	 * @方法描述: 解码
	 * @创建者: 皇族灬战狼
	 * @创建时间: 2016年7月12日 下午3:18:26
	 * @param value
	 *            字符串
	 * @return
	 */
	public static String decode(String value) {
		byte[] decodedValue = decodeBase64(value);
		return new String(decodedValue, WeiXinConstants.UTF_8);
	}

	/**
	 * @方法描述: 解码
	 * @创建者: 皇族灬战狼
	 * @创建时间: 2016年7月12日 下午3:18:56
	 * @param value
	 *            字符串
	 * @param charsetName
	 *            编码格式
	 * @return
	 */
	public static String decode(String value, String charsetName) {
		byte[] decodedValue = decodeBase64(value);
		return new String(decodedValue, Charset.forName(charsetName));
	}
}
