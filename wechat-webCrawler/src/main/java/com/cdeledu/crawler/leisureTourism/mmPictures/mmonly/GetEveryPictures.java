package com.cdeledu.crawler.leisureTourism.mmPictures.mmonly;

import java.io.Serializable;
import java.util.Iterator;

/**
 * @类描述: 数据采集：需要线程
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建日期: 2017年7月23日 下午3:32:58
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class GetEveryPictures implements Serializable {
	private static final long serialVersionUID = 1L;

	/** ----------------------------------------------------- Fields start */
	/** 唯一图库 */
	private final static String mmonly = "http://www.mmonly.cc/";
	/** 唯一图库-性感美女 */
	public final static String mmonly_xgmn = mmonly + "mmtp/xgmn/";
	/** 唯一图库-丝袜美女 */
	public final static String mmonly_swmn = mmonly + "mmtp/swmn/";
	/** 唯一图库-韩国美女 */
	public final static String mmonly_hgmn = mmonly + "mmtp/hgmn/";
	/** 唯一图库-外国美女 */
	public final static String mmonly_wgmv = mmonly + "mmtp/wgmv/";
	/** 唯一图库-比基尼美女 */
	public final static String mmonly_bjnmn = mmonly + "mmtp/bjnmn/";
	/** 唯一图库-内衣美女 */
	public final static String mmonly_nymn = mmonly + "mmtp/nymn/";
	/** 唯一图库-清纯美女 */
	public final static String mmonly_qcmn = mmonly + "mmtp/qcmn/";
	/** 唯一图库-长腿美女 */
	public final static String mmonly_ctmn = mmonly + "mmtp/ctmn/";
	/** 唯一图库-美女明星 */
	public final static String mmonly_mnmx = mmonly + "mmtp/mnmx/";
	/** 唯一图库-街拍美女 */
	public final static String mmonly_jpmn = mmonly + "mmtp/jpmn/";
	/** 唯一图库-唯美写真 */
	public final static String mmonly_wmxz = mmonly + "wmtp/wmxz/";
	/** 唯一图库-美女壁纸 */
	public final static String mmonly_mnbz = mmonly + "gqbz/mnbz/";

	/** 1.详情页面的地址 */
	public static String[] base_url_regex = {
			"http://www\\.mmonly\\.cc/[a-zA-Z]+/[a-zA-Z]+/\\d+\\.html\"><img",
			"http://www\\.mmonly\\.cc/[a-zA-Z]+/[a-z]+/\\d+" };
	public static String[] title_regex = { "alt=\"[\\u4E00-\\u9FA5\\w\\s\\-]+\"\\ssrc=\"",
			"[\\u4e00-\\u9fa5\\w\\s\\-]*[\\u4e00-\\u9fa5][\\u4e00-\\u9fa5\\w\\s\\-]*" };
	public static String[] picture_regex = {
			"src=\"http://t1\\.mmonly\\.cc/uploads/.+\\.jpg\" /></a></p>",
			"http://t1\\.mmonly\\.cc/uploads/.+\\.jpg" };

	/** ----------------------------------------------------- Fields end */

	public static void main(String[] args) throws Exception {

		getMmPictures(mmonly_xgmn, 1, 1);
	}

	/**
	 * @方法:获取图片地址
	 * @创建人:独泪了无痕
	 * @param mmUrl
	 * @param startPage
	 * @param endPage
	 */
	public static void getMmPictures(String url, int startPage, int endPage) {
		int tempPage;

		if (startPage < 1) { // 开始页码必须大于0
			startPage = 1;
		}
		if (startPage > endPage) {// 开始页码必须小于解释页码
			tempPage = startPage;
			startPage = endPage;
			endPage = tempPage;
		}
		CrawlerTask task = new CrawlerTask(url, getNum(url), startPage, endPage);
		new Thread(task).start();
	}

	private static Integer getNum(String url) {
		String baseUrl = "";
		String[] mmtpIndexName = { "xgmn", "swmn", "hgmn", "wgmv", "bjnmn", "nymn", "qcmn", "ctmn",
				"mnmx", "jpmn" };
		Integer no = 9;
		baseUrl = url.substring(0, url.lastIndexOf("/"));
		for (int i = 0; i < mmtpIndexName.length; i++) {
			if (baseUrl.endsWith(mmtpIndexName[i])) {
				no = 10 + i;
				break;
			}
		}
		if (baseUrl.endsWith("wmxz")) {
			return 27;
		}
		if (baseUrl.endsWith("mnbz")) {
			return 43;
		}
		return no;
	}
}

/**
 * @类描述:数据采集线程任务
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建日期: 2017年7月23日 下午3:37:05
 * @版本: V1.0
 * @since: JDK 1.7
 */

class CrawlerTask implements Runnable {
	/** ----------------------------------------------------- Fields start */
	/** 采集地址 */
	private String url;
	/** 采集开始 */
	private Integer num;
	/** 采集开始 */
	private Integer begin;
	/** 采集结束 */
	private Integer end;

	/** ----------------------------------------------------- Fields end */
	public CrawlerTask(String url, Integer num, Integer begin, Integer end) {
		this.url = url;
		this.begin = begin;
		this.end = end;
	}

	@SuppressWarnings("unused")
	@Override
	public void run() {
		WebsitList websitList = new WebsitList(url, num, begin, end);
		try {
			websitList.initUrls();
		} catch (Exception e) {
			System.out.println(url + "已跳过");
		}
		Iterator<String> iterator = websitList.urls.keySet().iterator();
		int i = 0;
		String main,title;
		while (iterator.hasNext()) {
			i++;
			try {
				main = iterator.next();
				title = websitList.urls.get(main);
				DetailPage detailPage = new DetailPage(main);
				// 总数 
				detailPage.getTotal();
				// 详情页 
				detailPage.initSrcs();
			} catch (Exception e) {
				continue;
			}
			// 每下载完6个页面的图片休眠10秒，防止过于频繁访问断开连接
			if (i % 6 == 0) {
				for (int j = 0; j < 2; j++) {
					try {
						Thread.sleep(1000);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
}
