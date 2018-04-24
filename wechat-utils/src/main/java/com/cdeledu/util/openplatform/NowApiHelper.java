package com.cdeledu.util.openplatform;

import org.apache.commons.lang3.StringUtils;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: NowAPI 专业第三方数据接口服务商
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2018年4月24日 上午9:45:17
 * @版本: V1.0
 * @since: JDK 1.7
 * @see <a href="https://www.nowapi.com"></a>
 */
public final class NowApiHelper {
	private static String baseUrl = "http://api.k780.com/";
	/** 使用API的唯一凭证 */
	private String appkey;
	/** md5后的32位密文,登陆用 */
	private String sign;

	public NowApiHelper(String appkey, String sign) {
		this.appkey = appkey;
		this.sign = sign;
	}

	/**
	 * @方法:IP地址归属查询
	 * @创建人:独泪了无痕
	 */
	public String getIp(String ip, String format, String jsoncallback) {
		if (StringUtils.isBlank(ip)) {
			ip = "";
		}
		if (StringUtils.isBlank(format)) {
			format = "";
		}
		if (StringUtils.isBlank(jsoncallback)) {
			jsoncallback = "";
		}
		return String.format(
				baseUrl + "?app=ip.get&ip=%s&appkey=%s&sign=%s&format=%s&jsoncallback=%s", ip,
				appkey, sign, format, jsoncallback);
	}
}
