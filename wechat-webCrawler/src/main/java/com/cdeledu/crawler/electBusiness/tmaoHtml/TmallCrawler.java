package com.cdeledu.crawler.electBusiness.tmaoHtml;

import java.net.URLEncoder;

import org.apache.log4j.Logger;

/**
 * @类描述: 天猫 采集数据
 * @创建者: 皇族灬战狼
 * @创建时间: 2016年11月22日 下午8:04:06
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class TmallCrawler {
	/** ----------------------------------------------------- Fields start */
	private static Logger logger = Logger.getLogger(TmallCrawler.class);
	private static TmallCrawler crawler = null;

	/** ----------------------------------------------------- Fields end */
	/** ----------------------------------------------- [私有方法] */
	private TmallCrawler() {
	}

	/** ----------------------------------------------- [私有方法] */

	public static TmallCrawler getInstance() {
		if (null == crawler) {
			crawler = new TmallCrawler();
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
		keywords = URLEncoder.encode(keywords.replaceAll(" ", ""), "gbk");
		return "http://list.tmall.com//search_product.htm?q=" + keywords + "&type=p";
	}
}
