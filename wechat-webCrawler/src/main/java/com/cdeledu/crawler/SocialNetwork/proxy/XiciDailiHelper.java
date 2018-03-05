package com.cdeledu.crawler.SocialNetwork.proxy;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.beust.jcommander.internal.Lists;
import com.cdeledu.common.mapper.JsonMapper;
import com.cdeledu.common.webCrawler.CrawlType;
import com.cdeledu.crawler.SocialNetwork.proxy.entity.ProxyPool;
import com.cdeledu.crawler.common.bean.CrawlParameter;
import com.cdeledu.crawler.common.imp.JsoupHandler;
import com.cdeledu.util.apache.collection.MapUtilHelper;

/**
 * @类描述: API提取代理ip地址
 * @创建者: 皇族灬战狼
 * @创建时间: 2017年6月10日 下午3:59:17
 * @版本: V1.0
 * @since: JDK 1.7
 * @see <a href="http://www.xicidaili.com/">西刺免费代理IP</a>
 */
public class XiciDailiHelper {
	/** ----------------------------------------------------- Fields start */
	protected static Logger logger = Logger.getLogger(XiciDailiHelper.class);
	private final static String BASE_URL = "http://www.xicidaili.com/";
	private static CrawlParameter crawlPara = null;
	private static JsoupHandler webCrawler = null;
	static {
		crawlPara = new CrawlParameter();
		crawlPara.setType(CrawlType.jsoup);
		webCrawler = new JsoupHandler();
	}
	/** ----------------------------------------------------- Fields end */

	/** ----------------------------------------------- [私有方法] */
	/** ----------------------------------------------- [私有方法] */
	/**
	 * @方法描述: 国内高匿代理
	 * @return
	 */
	public static String getProxyListBySecret() throws Exception {
		return getProxyList(BASE_URL + "nn/");
	}

	/**
	 * @方法描述: 国内透明代理
	 * @return
	 */
	public static String getProxyListByTransparent() throws Exception {
		return getProxyList(BASE_URL + "nt/");
	}

	/**
	 * @方法描述: 国内HTTPS代理
	 * @return
	 */
	public static String getProxyListByHttps() throws Exception {
		return getProxyList(BASE_URL + "wn/");
	}

	/**
	 * @方法描述: 国内HTTP代理
	 * @return
	 */
	public static String getProxyListByHttp() throws Exception {
		return getProxyList(BASE_URL + "wt/");
	}

	private static String getProxyList(String url) throws Exception {
		List<Map<String, Object>> resultList = null;
		resultList = Lists.newArrayList();
		Document document = Jsoup.parse(webCrawler.crawl(url, crawlPara));
		Elements dataTable = document.body().select("table#ip_list").first().select("tr");
		try {
			ProxyPool proxyIP = null;
			for (int i = 1; i < dataTable.size(); i++) {
				try {
					proxyIP = new ProxyPool();
					Elements tdData = dataTable.get(i).getElementsByTag("td");
					proxyIP.setIp(tdData.get(1).text());
					if (StringUtils.isNoneBlank(tdData.get(2).text())) {
						proxyIP.setPort(Integer.valueOf(tdData.get(2).text()));
					}
					proxyIP.setPosition(tdData.get(3).text());
					proxyIP.setProtocolType(tdData.get(5).text().toLowerCase().split(","));
					resultList.add(MapUtilHelper.beanToMap(proxyIP));
				} catch (Exception e) {
					if (logger.isDebugEnabled()) {
						e.printStackTrace();
					}
					if (logger.isErrorEnabled()) {
						e.printStackTrace();
					}
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return JsonMapper.toJsonString(resultList);
	}
}
