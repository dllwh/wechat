package com.cdeledu.crawler.leisureTourism.mmPictures.ku;

import java.util.Iterator;
import java.util.Map;

import com.google.common.collect.Maps;

/**
 * @类描述: 爬取 169美女图片网 里面的高清图片
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建日期: 2017年9月3日 下午8:32:31
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class KuGirlCralerHelper {
	/** ----------------------------------------------------- Fields start */
	private static final String BASEURL = "http://www.169ku.com/";
	private static final String XINGGANMEINV = BASEURL + "xingganmeinv/";
	private static final String WANGYOUZIPAI = BASEURL + "wangyouzipai/";
	private static final String GAOGENSIWA = BASEURL + "gaogensiwa/";
	private static final String XIYANGMEINV = BASEURL + "xiyangmeinv/";
	private static final String GUONEIMEINV = BASEURL + "guoneimeinv/";

	/** ----------------------------------------------------- Fields end */
	/**
	 * @方法:性感美女
	 * @创建人:独泪了无痕
	 */
	public static void getXingGanmeinv(int startPage, int endPage) {
		getMmPictures(XINGGANMEINV, 1, startPage, endPage);

	}

	/**
	 * @方法:网友自拍
	 * @创建人:独泪了无痕
	 */
	public static void getWangYouZiPai(int startPage, int endPage) {
		getMmPictures(WANGYOUZIPAI, 2, startPage, endPage);
	}

	/**
	 * @方法:高跟丝袜
	 * @创建人:独泪了无痕
	 */
	public static void getGaoGenSiWa(int startPage, int endPage) {
		getMmPictures(GAOGENSIWA, 3, startPage, endPage);
	}

	/**
	 * @方法:西洋美女
	 * @创建人:独泪了无痕
	 */
	public static void getXiYangMeiNv(int startPage, int endPage) {
		getMmPictures(XIYANGMEINV, 4, startPage, endPage);
	}

	/**
	 * @方法:国内美女
	 * @创建人:独泪了无痕
	 */
	public static void getGuoNeiMeiNv(int startPage, int endPage) {
		getMmPictures(GUONEIMEINV, 5, startPage, endPage);
	}

	private static void getMmPictures(String url, int crawType, int startPage, int endPage) {
		int tempPage;

		if (startPage < 1) { // 开始页码必须大于0
			startPage = 1;
		}
		if (startPage > endPage) {// 开始页码必须小于解释页码
			tempPage = startPage;
			startPage = endPage;
			endPage = tempPage;
		}

		CrawlerMMTask crawTask = new CrawlerMMTask(url, crawType, startPage, endPage);
		// 获取美女首页网址
		// 得到美女每一个页面的网址
		new Thread(crawTask).start();
	}

	public static void main(String[] args) {
		getGaoGenSiWa(1, 1);
	}

}

class CrawlerMMTask implements Runnable {
	/** 采集地址 */
	private String url;
	/** 采集类型 */
	private Integer crawType;
	/** 采集开始 */
	private Integer begin;
	/** 采集结束 */
	private Integer end;

	public CrawlerMMTask(String url, Integer crawType, Integer begin, Integer end) {
		super();
		this.url = url;
		this.crawType = crawType;
		this.begin = begin;
		this.end = end;
	}

	@Override
	public void run() {
		Map<String, Object> mmResultMap = Maps.newConcurrentMap();
		KuGirlWebsitList websitList = new KuGirlWebsitList(url, crawType, begin, end);
		try {
			websitList.initUrls();
		} catch (Exception e) {
			e.printStackTrace();
			// System.out.println(url + "已跳过");
		}
		Iterator<String> iterator = websitList.urls.keySet().iterator();
		String main, title;
		int i = 0;
		while (iterator.hasNext()) {
			i++;
			try {
				main = iterator.next();
				title = websitList.urls.get(main);
				KuGirlDetailPage detailPage = new KuGirlDetailPage(main);
				// 总数
				detailPage.getTotalPage();
				// 详情页
				detailPage.initPages();
				mmResultMap.put("title", title);
				mmResultMap.put("mmImageUrl", detailPage.imageSrcs);
			} catch (Exception e) {
				continue;
			}
			// 每下载完6个页面的图片休眠1秒，防止过于频繁访问断开连接
			if (i % 6 == 0) {
				for (int j = 0; j < 6; j++) {
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
