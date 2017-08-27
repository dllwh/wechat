package com.cdeledu.crawler.common.bean;

import java.io.Serializable;
import java.util.Map;

import org.jsoup.Connection.Method;

import com.cdeledu.common.webCrawler.CrawlType;
import com.gargoylesoftware.htmlunit.BrowserVersion;

/**
 * @类描述: 网络爬虫使用的元素
 * @创建者: 皇族灬战狼
 * @创建时间: 2016年10月29日 上午11:51:56
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class CrawlParameter implements Serializable {
	private static final long serialVersionUID = 1L;
	/** ----------------------------------------------------- Fields start */
	// 爬虫工具
	private CrawlType type = CrawlType.jsoup;
	private ProxyBean proxy;
	// 编码格式
	private String encode = "utf-8";
	// 是否使用JS
	private boolean isUseJs = true;
	// 模板
	private String template;
	// 模拟浏览器以及版本
	private BrowserVersion browse = BrowserVersion.FIREFOX_38;
	// 文件路径
	private String proFilePath;
	// 浏览器路径
	private String browserPath;
	// 请求方法
	private String reqmethod = Method.POST.name();
	// 请求参数
	private Map<String, String> reqmap;
	// 设置链接超时为60秒
	private long conntime = 60 * 1000;
	// 设置读取数据超时为60秒
	private long readtime = 60 * 1000;

	/** ----------------------------------------------------- Fields end */
	public CrawlType getType() {
		return type;
	}

	public void setType(CrawlType type) {
		this.type = type;
	}

	public ProxyBean getProxy() {
		return proxy;
	}

	public void setProxy(ProxyBean proxy) {
		this.proxy = proxy;
	}

	public String getEncode() {
		return encode;
	}

	public void setEncode(String encode) {
		this.encode = encode;
	}

	public boolean isUseJs() {
		return isUseJs;
	}

	public void setUseJs(boolean isUseJs) {
		this.isUseJs = isUseJs;
	}

	public String getTemplate() {
		return template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}

	public BrowserVersion getBrowse() {
		return browse;
	}

	public void setBrowse(BrowserVersion browse) {
		this.browse = browse;
	}

	public String getProFilePath() {
		return proFilePath;
	}

	public void setProFilePath(String proFilePath) {
		this.proFilePath = proFilePath;
	}

	public String getBrowserPath() {
		return browserPath;
	}

	public void setBrowserPath(String browserPath) {
		this.browserPath = browserPath;
	}

	public String getReqmethod() {
		return reqmethod;
	}

	public void setReqmethod(String reqmethod) {
		this.reqmethod = reqmethod;
	}

	public Map<String, String> getReqmap() {
		return reqmap;
	}

	public void setReqmap(Map<String, String> reqmap) {
		this.reqmap = reqmap;
	}

	public long getConntime() {
		return conntime;
	}

	public void setConntime(long conntime) {
		this.conntime = conntime;
	}

	public long getReadtime() {
		return readtime;
	}

	public void setReadtime(long readtime) {
		this.readtime = readtime;
	}
}
