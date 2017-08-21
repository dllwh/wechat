package com.cdeledu.crawler.SocialNetwork.proxy;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.cdeledu.common.mapper.JsonMapper;
import com.cdeledu.common.webCrawler.CrawlType;
import com.cdeledu.crawler.SocialNetwork.proxy.entity.ProxyPool;
import com.cdeledu.crawler.common.bean.CrawlParameter;
import com.cdeledu.crawler.common.imp.JsoupHandler;
import com.cdeledu.util.apache.collection.MapUtilHelper;
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
	protected static Logger logger = Logger.getLogger(Data5uHelper.class);
	private static final String BASE_URL = "http://www.data5u.com/free/";
	private static CrawlParameter crawlPara = null;
	private static JsoupHandler webCrawler = null;
	static {
		crawlPara = new CrawlParameter();
		crawlPara.setType(CrawlType.jsoup);
		webCrawler = new JsoupHandler();
	}

	/** ----------------------------------------------------- Fields end */
	/**
	 * 
	 * @方法描述: 普通代理IP
	 * @return
	 * @throws Exception
	 */
	public static String getProxyListByAll() throws Exception {
		return getProxyList(BASE_URL + "index.shtml");
	}

	/**
	 * 
	 * @方法描述: 普通国内高匿代理IP
	 * @return
	 * @throws Exception
	 */
	public static String getProxyListByGngn() throws Exception {
		return getProxyList(BASE_URL + "gngn/index.shtml");
	}

	/**
	 * 
	 * @方法描述: 普通国内代理IP
	 * @return
	 * @throws Exception
	 */
	public static String getProxyListByGnpt() throws Exception {
		return getProxyList(BASE_URL + "gnpt/index.shtml");
	}

	/**
	 * 
	 * @方法描述: 国外高匿代理IP
	 * @return
	 * @throws Exception
	 */
	public static String getProxyListByGwgn() throws Exception {
		return getProxyList(BASE_URL + "gwpt/index.shtml");
	}

	/**
	 * 
	 * @方法描述: 国外普通代理IP
	 * @return
	 * @throws Exception
	 */
	public static String getProxyListByGwpt() throws Exception {
		return getProxyList(BASE_URL + "gwpt/index.shtml");
	}

	private static String getProxyList(String url) throws Exception {
		crawlPara.setReqmethod("GET");
		String result = webCrawler.crawl(BASE_URL + "index.shtml", crawlPara);
		Document document = Jsoup.parse(result);
		Elements wList = document.select("body > div.wlist >ul > li:eq(1) > ul");
		List<Map<String, Object>> resultList = Lists.newArrayList();
		ProxyPool proxyIP = null;
		for (int i = 1; i < wList.size(); i++) {
			try {
				proxyIP = new ProxyPool();
				Elements spanData = wList.get(i).select("span");
				proxyIP.setIp(spanData.get(0).text());
				proxyIP.setPort(Integer.valueOf(spanData.get(1).text()));
				proxyIP.setProtocolType(spanData.get(3).text().split(","));
				proxyIP.setCountry(spanData.get(3).text());
				proxyIP.setPosition(spanData.get(4).text() + spanData.get(5).text());
				proxyIP.setIsp(spanData.get(6).text());
				resultList.add(MapUtilHelper.BeanToMap(proxyIP));
			} catch (Exception e) {
				if (logger.isDebugEnabled()) {
					e.printStackTrace();
				}
				if (logger.isErrorEnabled()) {
					e.printStackTrace();
				}
			}
		}
		return JsonMapper.toJsonString(resultList);
	}

}
