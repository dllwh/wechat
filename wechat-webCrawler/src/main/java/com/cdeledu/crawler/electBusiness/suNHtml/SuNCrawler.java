package com.cdeledu.crawler.electBusiness.suNHtml;

import java.net.URLEncoder;

import org.apache.log4j.Logger;

/**
 * @类描述: 苏宁 采集数据
 * @创建者: 皇族灬战狼
 * @创建时间: 2016年11月22日 下午8:21:08
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class SuNCrawler {
	/** ----------------------------------------------------- Fields start */
	private static Logger logger = Logger.getLogger(SuNCrawler.class);
	private static SuNCrawler crawler = null;

	/** ----------------------------------------------------- Fields end */

	/** ----------------------------------------------- [私有方法] */
	private SuNCrawler() {
	}

	/** ----------------------------------------------- [私有方法] */

	public static SuNCrawler getInstance() {
		if (null == crawler) {
			crawler = new SuNCrawler();
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
		return "http://search.suning.com/" + keywords + "/cityId=9173";
	}
}
