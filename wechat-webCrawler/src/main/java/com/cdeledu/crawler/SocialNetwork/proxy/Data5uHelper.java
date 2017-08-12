package com.cdeledu.crawler.SocialNetwork.proxy;

import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.cdeledu.common.browser.UserAgentType;
import com.cdeledu.common.mapper.JsonMapper;
import com.google.common.collect.Lists;

/**
 * @类描述: 无忧代理IP
 * @创建者: 皇族灬战狼
 * @创建时间: 2017年8月11日 上午7:56:12
 * @版本: V1.0
 * @since: JDK 1.7
 * @see <a href="http://www.data5u.com">无忧代理IP</a>
 */
public class Data5uHelper {
	/** ----------------------------------------------------- Fields start */
	/** ----------------------------------------------------- Fields end */

	/** ----------------------------------------------- [私有方法] */
	/** ----------------------------------------------- [私有方法] */

	public static void main(String[] args) throws Exception {
		Document document = Jsoup.connect("http://www.data5u.com/free/index.shtml")
				.header("User-Agent", UserAgentType.Mobile_Firefox.name()).get();
		Elements wList = document.select("body > div.wlist >li:eq(1) > ul");
		List<Map<String, Object>> resultList = Lists.newArrayList();
		for (int i = 1; i < wList.size(); i++) {
			//	ProxyPool proxyIP = new ProxyPool();
			// System.out.println(wList.get(i).select("span > li").html());
			// proxyIP.setIp(wList.get(0).text());
			// proxyIP.setPort(Integer.valueOf(wList.get(1).text()));
			//proxyIP.setProtocolType(wList.get(3).text().split(","));
			// proxyIP.setPosition(wList.get(4).text()+wList.get(5).text());
			// proxyIP.setIsp(wList.get(6).text());
			// resultList.add(MapUtilHelper.BeanToMap(proxyIP));
		}
		System.out.println(JsonMapper.toJsonString(resultList));
	}
}
