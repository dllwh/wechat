package com.cdeledu.crawler.SocialNetwork.proxy;

import com.cdeledu.util.network.tcp.HttpClientHelper;

/**
 * @类描述: API提取代理ip地址
 * @创建者: 皇族灬战狼
 * @创建时间: 2017年6月10日 下午3:59:17
 * @版本: V1.0
 * @since: JDK 1.7
 * @see <a href="http://www.xicidaili.com/">西刺免费代理IP</a>
 */
public class XiciDailiHelper {
	/** ----------------------------------------------------- Fields start */
	private final static String BASE_URL = "http://api.xicidaili.com/";
	private final static String FREE_URL = BASE_URL + "free2016.txt";

	/** ----------------------------------------------------- Fields end */

	/** ----------------------------------------------- [私有方法] */
	/** ----------------------------------------------- [私有方法] */
	/**
	 * @方法描述:
	 * 
	 *        <pre>
	 * 免费API提取地址
	 * API链接中的IP地址每15分钟更新一次，因此不建议频繁读取API，15分钟来读取一次即可。
	 *        </pre>
	 * 
	 * @return
	 */
	public static String[] getProxyIpByFreeApi() {
		String[] ip = null;
		try {
			String result = new HttpClientHelper().sendGetRequest(FREE_URL);
			ip = result.split("\r\n");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return ip;
	}
}
