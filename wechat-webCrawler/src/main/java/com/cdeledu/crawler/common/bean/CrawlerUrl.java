package com.cdeledu.crawler.common.bean;

import com.cdeledu.common.webCrawler.SchemType;

/**
 * @类描述: TODO(这里用一句话描述这个类的作用)
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建日期: 2016年9月10日 上午11:05:19
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class CrawlerUrl {
	/** ----------------------------------------------------- Fields start */
	public String sUrl; // URL字符串
	public SchemType scheme; // URL字符串
	public String sHost; // 主机名
	public String sPort; // 端口号
	public String sPath; // 请求资源
	/** ----------------------------------------------------- Fields end */
}
