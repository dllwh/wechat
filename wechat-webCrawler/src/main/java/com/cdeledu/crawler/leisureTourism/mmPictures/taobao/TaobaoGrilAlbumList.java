package com.cdeledu.crawler.leisureTourism.mmPictures.taobao;

import java.util.List;
import java.util.Map;

import org.jsoup.Connection.Method;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.cdeledu.common.webCrawler.CrawlType;
import com.cdeledu.crawler.common.bean.CrawlParameter;
import com.cdeledu.crawler.common.imp.JsoupHandler;
import com.cdeledu.util.application.regex.RegexUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

class TaobaoGrilAlbumList {
	/** ----------------------------------------------------- Fields start */
	/** 相册列表 */
	private static String ALBUM_COUNT = "https://mm.taobao.com/self/album/open_album_list.htm?_charset=utf-8&user_id=%s";
	private static String ALBUM_LIST = "https://mm.taobao.com/self/album/open_album_list.htm?_charset=utf-8&user_id=%s&page=%s";
	/** 相册的总页数 */
	private static int albumTotalPage = 1;
	/** 相册内照片的总页数 */
	private static int totalPicPage = 1;
	private int userId;
	private String realName;
	List<Map<String, Object>> mmPicList = Lists.newArrayList();
	private static JsoupHandler webCrawler = null;
	private static CrawlParameter crawlParam = null;

	static {
		crawlParam = new CrawlParameter();
		crawlParam.setType(CrawlType.jsoup);
		crawlParam.setReqmethod(Method.GET.name());
		webCrawler = new JsoupHandler();
	}

	/** ----------------------------------------------------- Fields end */
	public TaobaoGrilAlbumList(int userId, String realName) {
		super();
		this.userId = userId;
		this.realName = realName;
	}

	/**
	 * @方法描述: 获取相册的总页数
	 * @param userId
	 */
	public int getGrilAlbumCount() throws Exception {
		if (userId > 0) {
			String crawHtml = webCrawler.crawl(String.format(ALBUM_COUNT, userId), crawlParam);
			Document document = Jsoup.parse(crawHtml);
			albumTotalPage = Integer.valueOf(document.select("input#J_Totalpage").attr("value"));
		}
		return albumTotalPage;
	}

	/**
	 * @方法描述: 相册列表
	 * @param userId
	 *            用户ID
	 */
	public void getGrilAlbumList() throws Exception {
		if (userId == 0) {
			return;
		}
		Document document = null;
		String crawHtml = "", albumUrl = "", pagelistSize = "", albumName = "", albumId = "";
		int picNum;
		GrilPhotoDetailPage detailPage = null;
		Map<String, Object> mmResultMap = null;
		for (int i = 1; i <= albumTotalPage; i++) {
			try {
				mmResultMap = Maps.newConcurrentMap();
				crawHtml = webCrawler.crawl(String.format(ALBUM_LIST, userId, i), crawlParam);
				document = Jsoup.parse(crawHtml);
				Elements photoList = document.select("html>body").select("div.mm-photo-cell");
				for (Element photo : photoList) {
					try {
						Element photoCell = photo.select("div.mm-photo-cell-middle").first();
						albumUrl = photoCell.select("a.mm-first").attr("href");
						albumId = RegexUtil.getKeyWords("album_id=(\\d+)", albumUrl, 1);
						pagelistSize = RegexUtil.getKeyWords("[^0-9]",
								photoCell.select("span.mm-pic-number").text(), 0);
						picNum = Integer.valueOf(pagelistSize);
						albumName = photoCell.getElementsByTag("h4").text();
						totalPicPage = (int) (picNum / 16) + 1;
						detailPage = new GrilPhotoDetailPage(userId, albumId, totalPicPage);
						detailPage.getGrilPhotoDetailPage();
						mmResultMap.put("title", realName + albumName);
						mmResultMap.put("mmImageUrl", detailPage.imageSrcs);
						mmPicList.add(mmResultMap);
					} catch (Exception e) {
						continue;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				continue;
			}
			if (i % 6 == 0) {
				try {
					Thread.sleep(1000);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		}
	}
}
