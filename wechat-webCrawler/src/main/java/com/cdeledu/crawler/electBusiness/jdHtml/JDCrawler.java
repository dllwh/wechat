package com.cdeledu.crawler.electBusiness.jdHtml;

import java.net.URLEncoder;

import org.apache.log4j.Logger;

/**
 * @类描述: 京东数据采集
 * @创建者: 皇族灬战狼
 * @创建时间: 2016年11月22日 下午8:26:19
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class JDCrawler {
	/** ----------------------------------------------------- Fields start */
	private static Logger logger = Logger.getLogger(JDCrawler.class);
	private static JDCrawler crawler = null;

	/** ----------------------------------------------------- Fields end */

	/** ----------------------------------------------- [私有方法] */
	private JDCrawler() {
	}

	/** ----------------------------------------------- [私有方法] */

	public static JDCrawler getInstance() {
		if (null == crawler) {
			crawler = new JDCrawler();
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
		return "http://search.jd.com/Search?keyword=" + keywords + "&enc=utf-8";
	}
}
