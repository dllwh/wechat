package com.cdeledu.crawler.electBusiness.gmHtml;

import java.net.URLEncoder;

import org.apache.log4j.Logger;

public class GMCrawler {
	/** ----------------------------------------------------- Fields start */
	private static Logger logger = Logger.getLogger(GMCrawler.class);
	private static GMCrawler crawler = null;

	/** ----------------------------------------------------- Fields end */

	/** ----------------------------------------------- [私有方法] */
	private GMCrawler() {
	}

	/** ----------------------------------------------- [私有方法] */

	public static GMCrawler getInstance() {
		if (null == crawler) {
			crawler = new GMCrawler();
		}
		return crawler;
	}

	/**
	 * @方法描述: 根据关键词语获取请求地址
	 * @param keywords
	 * @return
	 */
	public String getSearchPage(String keywords) throws Exception {
		logger.info("根据关键词语获取请求地址");
		keywords = URLEncoder.encode(keywords.replaceAll(" ", ""), "utf-8");
		return "http://www.gome.com.cn/search?question=" + keywords;
	}
}
