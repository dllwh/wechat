package com.cdeledu.crawler.common.bean;

/**
 * @类描述: 搜集系统按照URL抓取器对应的网页，网页信息保存在Page类
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建日期: 2016年9月10日 上午11:10:13
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class CrawlerPage {
	/** ----------------------------------------------------- Fields start */
	/**网页的url */ 
	public String sUrl;
	/**
	 * 网页头信息
	 */
	public String sHeader;
	public int nLenHeader;
	// 转状态码
	public int nStatusCode;
	//网页体长度
	public int nContentLength;
	//转向信息 
	public String nLocation;
	//连接状态.如果连接关闭,是false,否则为true
	public boolean connectionState;
	// 网页体编码()
	public String nContentEncoding; 
	//网页类型
	public String nContentType;
	// 网页体字符集
	public String charset;
	//网页传输编码方式
	public String transferEncoding;
	
	/**
	 * 网页体信息
	 */
	public String nContent;
	public int nLenContent;
	public String nContentNoTags;
	public String sContentLinInfo;
	// 为搜索引擎准备的链接
	public String sContentLinInfo4SE;
	public int nLenLinInfo4SE;
	// 为历史存档准备的链接
	public String sContentLinInfo4History;
	public int nLenLinInfo4History;
	/** ----------------------------------------------------- Fields end */
	// public void parseHeaderInfo(String header);// 解析网页头信息
	// public void parseHyperLinks(String header);//从网页体中解析链接信息
	/**
	 * 过滤到无用的链接
	 * 如果要过滤,回true;否则返回false
	 */
	// public boolean isFilterLink(String pLink);
	
	/**
	 * 解析网页头信息
	 */
	
	// public void getStatus(String header);
	// public void getContentLength(String header);
	// public void getConnectionStatus(String header);
	// public void getLocation(String header);
	// public void getCharse(String header);
	// public void getContentEncoding(String header);
	// public void getContentType(String header);
	// public void getTransferEncoding(String header);
	
	/**
	 * 从网页体体解析链接信息
	 */
	// public boolean getContentLinkInfo();
	// public boolean getLinkInfo4SE();
	// public boolean getLinkInfo4History();
}
