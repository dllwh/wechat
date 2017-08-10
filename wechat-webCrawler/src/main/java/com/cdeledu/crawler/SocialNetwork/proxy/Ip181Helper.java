package com.cdeledu.crawler.SocialNetwork.proxy;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.beust.jcommander.internal.Lists;
import com.cdeledu.common.mapper.JsonMapper;
import com.cdeledu.crawler.SocialNetwork.proxy.common.ProxyPool;
import com.cdeledu.util.apache.collection.MapUtilHelper;

public class Ip181Helper {
	/** ----------------------------------------------------- Fields start */
	private static final String BASE_URL = "http://ip181.com/";
	private static final String PAGE_URL = BASE_URL + "daili/%s.html";

	/** ----------------------------------------------------- Fields end */
	/**
	 * @方法描述: 获取精选代理IP
	 * @return
	 * @throws Exception
	 */
	public static String getProxyIP() throws Exception {
		return JsonMapper.toJsonString(getProxyIPByPage(BASE_URL));
	}

	/**
	 * @方法描述: 每日免费代理ip列表
	 * @param pageNum
	 * @return
	 * @throws Exception
	 */
	public static String getProxyIP(int pageNum) throws Exception {
		if (pageNum < 1) {
			pageNum = 1;
		}
		return JsonMapper.toJsonString(getProxyIPByPage(String.format(PAGE_URL, pageNum)));
	}

	/**
	 * @方法描述: 获取总的代理IP的总数，用来分页
	 * @return
	 */
	public static int getProxyIPTotal() throws Exception {
		Document document = Jsoup.connect(String.format(PAGE_URL, 1)).get();
		String total = document.select("div.page").first().getElementsByTag("font").first().text();
		return Integer.valueOf(total);
	}

	private static String getProxyIPByPage(String url) throws Exception {
		List<Map<String, Object>> resultList = Lists.newArrayList();
		Document document = Jsoup.connect(url).get();
		Elements dataTable = document.select("div.panel-body").first().getElementsByTag("table")
				.first().getElementsByTag("tr");
		for (int i = 1; i < dataTable.size(); i++) {
			resultList.add(MapUtilHelper.BeanToMap(getDetailInfo(dataTable.get(i))));
		}
		return JsonMapper.toJsonString(resultList);
	}

	/**
	 * @方法描述: 获取详细信息
	 * @param data
	 * @return
	 */
	private static ProxyPool getDetailInfo(Element data) {
		ProxyPool proxyIP = new ProxyPool();
		Elements tdData = data.getElementsByTag("td");
		proxyIP.setIp(tdData.get(0).text());
		if (StringUtils.isNoneBlank(tdData.get(1).text())) {
			proxyIP.setPort(Integer.valueOf(tdData.get(1).text()));
		}
		proxyIP.setType(tdData.get(3).text().split(","));
		proxyIP.setPosition(tdData.get(5).text());
		return proxyIP;
	}
}
