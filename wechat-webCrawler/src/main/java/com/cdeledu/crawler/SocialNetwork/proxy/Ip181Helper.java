package com.cdeledu.crawler.SocialNetwork.proxy;

import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.google.common.collect.Maps;

public class Ip181Helper {
	/** ----------------------------------------------------- Fields start */
	private static final String BASE_URL = "http://ip181.com/";

	/** ----------------------------------------------------- Fields end */
	public static void getProxyIP() throws Exception {
		Document document = Jsoup.connect(BASE_URL).get();
		Elements dateTable = document.select("div.panel-body").first().getElementsByTag("table")
				.first().getElementsByTag("tr");
		for (int i = 1; i < dateTable.size(); i++) {
			getDetailInfo(dateTable.get(i));
			System.out.println();
		}
	}

	private static Map<String, Object> getDetailInfo(Element date) {
		Map<String, Object> resultMap = Maps.newConcurrentMap();
		Elements tdDate = date.getElementsByTag("td");
		resultMap.put("ip", tdDate.get(0).text());
		resultMap.put("port", tdDate.get(1).text());
		resultMap.put("type", tdDate.get(3).text().split(",").toString());
		resultMap.put("position", tdDate.get(5).text());
		System.out.println(resultMap);
		return resultMap;
	}

	public static void main(String[] args) {
		try {
			getProxyIP();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
