package com.cdeledu.crawler.common.imp;

import org.apache.log4j.Logger;
import org.jsoup.Connection;
import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;
import org.jsoup.safety.Whitelist;

import com.cdeledu.crawler.common.bean.CrawlParameter;

/**
 * @类描述: 使用JSOUP实现网络爬虫
 * @创建者: 皇族灬战狼
 * @创建时间: 2016年11月21日 下午4:21:48
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class JsoupHandler extends CrawlHandler {
	/** ----------------------------------------------------- Fields start */
	protected static Logger logger = Logger.getLogger(JsoupHandler.class);

	/** ----------------------------------------------------- Fields end */

	/** ----------------------------------------------- [私有方法] */
	/** ----------------------------------------------- [私有方法] */
	/**
	 * @方法描述: 通过地址得到document对象
	 * @param url
	 * @return
	 */
	private static Document getDocument(String url, CrawlParameter crawlParam) {
		Connection conn = null;
		Document document = null;
		String reqtype = crawlParam.getReqmethod();
		try {
			conn = Jsoup.connect(url)// 获取连接
					.userAgent(crawlParam.getBrowse().getUserAgent())// 配置模拟浏览器
					//.cookie("auth", "token")// 设置 cookie
					.ignoreContentType(true).ignoreHttpErrors(true)
					.timeout(10000); // 设置连接超时时间
			if ("post".equals(reqtype.toLowerCase())) {
				conn.method(Method.POST);
			} else if ("get".equals(reqtype.toLowerCase())) {
				conn.method(Method.GET);
			} else {
				conn.method(Method.POST);
			}
			document = conn.execute().parse();
			
		} catch (Exception e) {
			e.printStackTrace();
			// logger.error("通过JSoup方式抓取数据出现异常,异常信息如下:", e);
		}
		return document;
	}

	public String crawl(String url, CrawlParameter crawlParam) {
		String resource = "";
		Document document = JsoupHandler.getDocument(url, crawlParam);
		if (null != document && !"".equals(document.toString())) {
			resource = document.html();
		}
		return resource;
	}

	public static void main(String[] args) {
		// JsoupHandler jsoup = new JsoupHandler();
		// CrawlParameter crawlpara = new CrawlParameter();
		// crawlpara.setType(CrawlType.jsoup);
		// String source = jsoup.crawl("http://www.ctrip.com/",crawlpara);
		// System.out.println(source);
		Whitelist.basic();
	}
}
