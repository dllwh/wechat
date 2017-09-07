package com.cdeledu.crawler.leisureTourism.mmPictures.ku;

import java.util.LinkedHashMap;

import org.jsoup.Connection.Method;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.cdeledu.common.webCrawler.CrawlType;
import com.cdeledu.crawler.common.bean.CrawlParameter;
import com.cdeledu.crawler.common.imp.JsoupHandler;

class KuGirlWebsitList {
	/** ----------------------------------------------------- Fields start */
	private String prepareUrl;
	private Integer crawType, beginPage, endPage;
	LinkedHashMap<String, String> urls = new LinkedHashMap<String, String>();

	private static CrawlParameter crawlParam = null;
	private static JsoupHandler webCrawler = null;

	static {
		crawlParam = new CrawlParameter();
		crawlParam.setType(CrawlType.jsoup);
		crawlParam.setReqmethod(Method.GET.name());
		webCrawler = new JsoupHandler();
	}

	/** ----------------------------------------------------- Fields end */
	public KuGirlWebsitList(String url, Integer crawType, Integer begin, Integer end) {
		super();
		this.prepareUrl = url;
		this.crawType = crawType;
		this.beginPage = begin;
		this.endPage = end;
	}

	public void initUrls() throws Exception {
		String preUrl = "";
		for (int i = beginPage; i <= endPage; i++) {
			try {
				if (i != 1) {
					preUrl = prepareUrl + "list_" + crawType + "_" + i + ".html";
				} else {
					preUrl = prepareUrl;
				}
				matchAll(preUrl);
			} catch (Exception e) {
				e.printStackTrace();
				continue;
			}
		}
	}

	private void matchAll(String url) throws Exception {
		String homePagePath = "", title = "";
		String crawHtml = webCrawler.crawl(url, crawlParam);
		Document document = Jsoup.parse(crawHtml);
		Elements product = document.select("html>body").select("ul.product01>li");
		for (int i = 0; i < product.size(); i++) {
			try {
				homePagePath = product.get(i).select("a").attr("abs:href");
				title = product.get(i).select("p").text();
				urls.put(homePagePath, title);
			} catch (Exception e) {
				e.printStackTrace();
				continue;
			}
		}
	}
}
