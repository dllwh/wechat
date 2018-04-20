package com.cdeledu.util.openplatform.baidu.baiduMap;

import org.apache.commons.lang3.StringUtils;

import com.cdeledu.util.network.tcp.HttpURLConnHelper;
import com.cdeledu.util.openplatform.baidu.baiduMap.model.IpResponse;
import com.google.gson.Gson;

/**
 * 
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: IP定位
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2018年4月20日 下午8:13:35
 * @版本: V1.0
 * @since: JDK 1.7
 * @see <a href="lbsyun.baidu.com/index.php?title=webapi/ip-api"></a>
 */
final class BaiduMapIPHelper {
	private static HttpURLConnHelper connHelper = null;
	private static Gson gsonHelper = null;

	static {
		connHelper = HttpURLConnHelper.getInstance();
		gsonHelper = new Gson();
	}

	/**
	 * 
	 * @方法:利用IP获取大致位置
	 * @创建人:独泪了无痕
	 * @param ak
	 *            开发者密钥
	 * @param ip
	 *            用户上网的IP地址，请求中如果不出现、或为空，会针对发来请求的IP进行定位
	 * @return
	 * @throws Exception
	 */
	public static IpResponse getlocationByIP(String ak, String ip) throws Exception {
		if (StringUtils.isBlank(ip)) {
			ip = "";
		}
		String url = "http://api.map.baidu.com/location/ip?ip=" + ip + "&ak=" + ak;
		IpResponse result = gsonHelper.fromJson(connHelper.sendGetRequest(url), IpResponse.class);
		result.setResquestUrl(url);
		return result;
	}
}
