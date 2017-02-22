package com.cdeledu.crawler.common.imp;

import com.cdeledu.crawler.common.bean.CrawlParameter;
import com.cdeledu.crawler.common.bean.ProxyBean;

/**
 * @类描述:
 * @创建者: 皇族灬战狼
 * @创建时间: 2016年10月29日 上午11:57:26
 * @版本: V1.0
 * @since: JDK 1.7
 */
public abstract class CrawlHandler {
	/** ----------------------------------------------------- Fields start */
	/** 代理 */
	public ProxyBean proxyBean;
	public boolean isUseProxy;

	/**
	 * @方法描述: 抓取主方法
	 * @param url
	 *            请求地址
	 * @param crawlParam
	 *            网络爬虫使用的元素
	 * @return
	 */
	public abstract String crawl(String url, CrawlParameter crawlParam);

	/** ----------------------------------------------------- Fields end */

	public ProxyBean getProxyBean() {
		return proxyBean;
	}

	public void setProxyBean(ProxyBean proxyBean) {
		this.proxyBean = proxyBean;
	}

	public boolean isUseProxy() {
		return isUseProxy;
	}

	public void setUseProxy(boolean isUseProxy) {
		this.isUseProxy = isUseProxy;
	}
}
