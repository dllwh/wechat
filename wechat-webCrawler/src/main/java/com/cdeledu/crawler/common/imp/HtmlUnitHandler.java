package com.cdeledu.crawler.common.imp;

import java.net.URL;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;

import com.cdeledu.crawler.common.bean.CrawlParameter;
import com.cdeledu.crawler.common.bean.ProxyBean;
import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.HttpMethod;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebRequest;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.util.NameValuePair;

/**
 * @类描述:
 *       <ul>
 *       <li>HtmlUnit是一款开源的可以模拟浏览器运行,抓取页面内容的Java框架,具有js解析引擎,可以解析页面的js脚本</li>
 *       <li>读取页面后,可以有效的使用 HtmlUnit 分析页面上的内容.</li>
 *       </ul>
 * @创建者: 皇族灬战狼
 * @创建时间: 2016年10月29日 下午12:00:46
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class HtmlUnitHandler extends CrawlHandler {

	/** ----------------------------------------------------- Fields start */

	/** ----------------------------------------------------- Fields end */

	/** ----------------------------------------------- [私有方法] */

	/**
	 * @方法描述: 模拟特定浏览器
	 * @param browser
	 * @param proxy
	 * @return
	 */
	private WebClient getWebClient(CrawlParameter crawlPara) {
		/**
		 * 模拟一个浏览器，可以选择IE、Chrome、Firefox等等
		 */
		WebClient webClient = null;
		BrowserVersion browser = crawlPara.getBrowse();

		if (null == crawlPara.getProxy()) {
			webClient = new WebClient(browser);
		} else {
			// 代理服务器的配置,代理的配置很简单,只需要配置好地址、端口、用户名与密码即可
			ProxyBean proxy = crawlPara.getProxy();
			webClient = new WebClient(browser, proxy.getProxyHost(), proxy.getProxyPort());
		}
		/**
		 * 设置webClient的相关参数
		 */
		// 启用JavaScript解释器,默认为true(对于某些动态页面,这是必须的)
		webClient.getOptions().setJavaScriptEnabled(crawlPara.isUseJs());
		// 禁用css支持,可避免自动二次请求CSS进行渲染(对于某些动态页面,这是必须的)
		webClient.getOptions().setCssEnabled(false);
		// 启动客户端重定向
		// webClient.getOptions().setRedirectEnabled(true);
		// 忽略ssl认证
		webClient.getOptions().setUseInsecureSSL(true);
		// JavaScript运行错误时，是否抛出异常
		webClient.getOptions().setThrowExceptionOnScriptError(false);
		// JavaScript运行错误时,是否抛出 response 的错误
		webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
		// 设置连接超时时间 ,这里是10S。如果为0，则无限期等待
		webClient.getOptions().setTimeout(10 * 1000);
		// 设置Ajax异步
		webClient.setAjaxController(new NicelyResynchronizingAjaxController());
		webClient.setJavaScriptTimeout(600 * 1000);
		webClient.getOptions().setActiveXNative(false);

		return webClient;
	}

	// 为了获取js执行的数据 线程开始沉睡等待
	public Page getPage() {
		return null;
	}

	/** ----------------------------------------------- [私有方法] */

	@Override
	public String crawl(String url, CrawlParameter crawlPara) {
		WebClient webClient = getWebClient(crawlPara);
		String reqtype = crawlPara.getReqmethod();
		Page page = null;
		String resource = "";
		try {
			if (StringUtils.isNotBlank(reqtype)) {
				WebRequest webRequest = new WebRequest(new URL(url));
				if ("post".equals(reqtype)) {
					webRequest.setHttpMethod(HttpMethod.POST);
				} else if ("get".equals(reqtype)) {
					webRequest.setHttpMethod(HttpMethod.GET);
				}
				Map<String, String> reqmap = crawlPara.getReqmap();
				if (MapUtils.isNotEmpty(reqmap)) {
					for (Entry<String, String> param : reqmap.entrySet()) {
						webRequest.getRequestParameters()
								.add(new NameValuePair(param.getKey(), param.getValue()));
					}
				}
				page = webClient.getPage(webRequest);
				resource = page.getWebResponse().getContentAsString();
			} else {
				page = webClient.getPage(url);
				resource = ((HtmlPage) page).asXml();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return resource;
	}

	public static void main(String[] args) {
		HtmlUnitHandler unit = new HtmlUnitHandler();
		CrawlParameter crawlPara = new CrawlParameter();
		String url = "http://you.ctrip.com/sight/jiuzhaigou25/77380-hotels.html#zhusu";
		String source = unit.crawl(url, crawlPara);
		System.out.println(source);
	}
}
