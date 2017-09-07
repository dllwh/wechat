package com.cdeledu.crawler.leisureTourism.mmPictures.ku;

import java.util.LinkedList;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Connection.Method;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.cdeledu.common.webCrawler.CrawlType;
import com.cdeledu.crawler.common.bean.CrawlParameter;
import com.cdeledu.crawler.common.imp.JsoupHandler;
import com.cdeledu.util.application.regex.RegexUtil;

/**
 * @类描述: 详情页面
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建日期: 2017年9月3日 下午8:52:18
 * @版本: V1.0
 * @since: JDK 1.7
 */
class KuGirlDetailPage {
	/** ----------------------------------------------------- Fields start */
	private String pre_url;
	private Integer totalPage = 0;
	private static CrawlParameter crawlParam = null;
	private static JsoupHandler webCrawler = null;
	/** 单页美女图片的链接地址 */
	LinkedList<String> imageSrcs = new LinkedList<String>();

	static {
		crawlParam = new CrawlParameter();
		crawlParam.setType(CrawlType.jsoup);
		crawlParam.setReqmethod(Method.GET.name());
		webCrawler = new JsoupHandler();
	}

	/** ----------------------------------------------------- Fields end */

	public KuGirlDetailPage(String pre_url) {
		super();
		this.pre_url = pre_url;
	}

	public void getTotalPage() {
		String crawHtml = webCrawler.crawl(pre_url, crawlParam);
		Document document = Jsoup.parse(crawHtml);
		Elements pagelist = document.select("html>body").select("div.dede_pages>ul.pagelist>li>a");
		String pagelistSize = RegexUtil.getKeyWords("[^0-9]", pagelist.first().text(), 0);
		if (StringUtils.isNotBlank(pagelistSize)) {
			totalPage = Integer.valueOf(pagelistSize);
		}
	}

	public void initPages() {
		for (int i = 1; i <= totalPage; i++) {
			try {
				String url_str = "";
				if (i != 1) {
					url_str = pre_url.substring(0, pre_url.indexOf(".html")) + "_" + i + ".html";
				} else {
					url_str = pre_url;
				}

				matchAll(url_str);
			} catch (Exception e) {
				e.printStackTrace();
				continue;
			}
		}
	}

	private void matchAll(String url) {
		String crawHtml = webCrawler.crawl(url, crawlParam);
		Document document = Jsoup.parse(crawHtml);
		Elements pagelist = document.select("html>body").select("div.big-pic>div.big_img>p");
		for (Element element : pagelist) {
			try {
				imageSrcs.add(element.getElementsByTag("img").first().absUrl("src"));
			} catch (Exception e) {
				System.out.println("已跳过" + url);
				continue;
			}
		}
	}
}
