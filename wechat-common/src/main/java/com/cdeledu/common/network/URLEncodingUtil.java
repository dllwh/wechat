package com.cdeledu.common.network;

import java.net.URLEncoder;
import java.nio.charset.Charset;

/**
 * @Description: 转码工具类
 * @author: 独泪了无痕
 * @date: 2015年11月1日 下午6:44:19
 * @version: V1.0
 * @since: JDK 1.7
 * @see <a href="">TODO(连接内容简介)</a>
 */
public class URLEncodingUtil {
	public static String encoding(final String content, final Charset charset) {
		String result = "";
		try {
			result = URLEncoder.encode(content, charset.name());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
