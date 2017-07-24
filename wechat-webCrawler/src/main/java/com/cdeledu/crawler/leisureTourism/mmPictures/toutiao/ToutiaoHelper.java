package com.cdeledu.crawler.leisureTourism.mmPictures.toutiao;

import org.apache.commons.lang3.StringUtils;

import com.cdeledu.util.network.tcp.HttpURLConnHelper;

/**
 * @类描述: 今日头条
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建日期: 2017年7月24日 下午9:07:29
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class ToutiaoHelper {
	/** ----------------------------------------------------- Fields start */
	private static final String BASE_URL = "http://www.toutiao.com/";
	private static final String SEARCH_URL = BASE_URL
			+ "search_content/?format=json&autoload=true&keyword=%s&count=%s&offset=%s&cur_tab=%s&_=%s";
	private static final String FAILMSG = "{\"message\": \"fail\",\"data\": {}}";
	private static HttpURLConnHelper httpRequest = HttpURLConnHelper.getInstance();
	/** ----------------------------------------------------- Fields end */

	/**
	 * @方法:获取天气信息
	 * @创建人:独泪了无痕
	 * @param city
	 * @return
	 */
	public static String getWeather(String city) {
		String url = String.format(BASE_URL + "stream/widget/local_weather/data/?city=%s", city);
		try {
			return httpRequest.sendGetRequest(url);
		} catch (Exception e) {
			e.printStackTrace();
			return FAILMSG;
		}
	}

	/**
	 * @方法:热门搜索排行
	 * @创建人:独泪了无痕
	 * @return
	 */
	public static String getHotwords() {
		try {
			return httpRequest.sendGetRequest(BASE_URL + "c/hot_words/");
		} catch (Exception e) {
			e.printStackTrace();
			return FAILMSG;
		}
	}

	/**
	 * @方法:热门视频
	 * @创建人:独泪了无痕
	 * @return
	 */
	public static String getPCHotVideo() {
		try {
			return httpRequest.sendGetRequest(BASE_URL + "api/pc/hot_video/?widen=1");
		} catch (Exception e) {
			e.printStackTrace();
			return FAILMSG;
		}
	}

	/**
	 * @方法:精彩图片
	 * @创建人:独泪了无痕
	 * @return
	 */
	public static String getPCHotGallery() {
		try {
			return httpRequest.sendGetRequest(BASE_URL + "api/pc/hot_gallery/?widen=1");
		} catch (Exception e) {
			e.printStackTrace();
			return FAILMSG;
		}
	}

	/**
	 * @方法:综合
	 * @创建人:独泪了无痕
	 * @param keyword
	 *            是我们的搜索关键字
	 * @param count
	 *            请求的新文章数量
	 * @param offset
	 *            表示偏移量，即已经请求的文章数
	 * @return
	 */
	public static String getAllSearchContent(String keyword, int count, int offset) {
		return getSearchContent(keyword, count, offset, 1);
	}

	/**
	 * @方法:视频
	 * @创建人:独泪了无痕
	 * @param keyword
	 *            是我们的搜索关键字
	 * @param count
	 *            请求的新文章数量
	 * @param offset
	 *            表示偏移量，即已经请求的文章数
	 * @return
	 */
	public static String getVideoSearchContent(String keyword, int count, int offset) {
		return getSearchContent(keyword, count, offset, 2);
	}

	/**
	 * @方法:图集
	 * @创建人:独泪了无痕
	 * @param keyword
	 *            是我们的搜索关键字
	 * @param count
	 *            请求的新文章数量
	 * @param offset
	 *            表示偏移量，即已经请求的文章数
	 * @return
	 */
	public static String getImageSearchContent(String keyword, int count, int offset) {
		return getSearchContent(keyword, count, offset, 3);
	}

	/**
	 * @方法:综合
	 * @创建人:独泪了无痕
	 * @param keyword
	 *            是我们的搜索关键字
	 * @param count
	 *            请求的新文章数量
	 * @param offset
	 *            表示偏移量，即已经请求的文章数
	 * @return
	 */
	public static String getPgcSearchContent(String keyword, int count, int offset) {
		return getSearchContent(keyword, count, offset, 4);
	}

	/**
	 * @方法:用户
	 * @创建人:独泪了无痕
	 * @param keyword
	 *            是我们的搜索关键字
	 * @param count
	 *            请求的新文章数量
	 * @param offset
	 *            表示偏移量，即已经请求的文章数
	 * @return
	 */
	public static String getWendaSearchContent(String keyword, int count, int offset) {
		return getSearchContent(keyword, count, offset, 5);
	}

	/**
	 * @方法:问答
	 * @创建人:独泪了无痕
	 * @param keyword
	 *            是我们的搜索关键字
	 * @param count
	 *            请求的新文章数量。不大于20的正整数
	 * @param offset
	 *            表示偏移量，即已经请求的文章数,正整数
	 * @param curTab
	 *            类型
	 * @return
	 */
	private static String getSearchContent(String keyword, int count, int offset, int curTab) {
		if (StringUtils.isEmpty(keyword)) {
			return "{\"message\": \"fail\",\"msg\": \"No keywords \"}";
		}
		if (count <= 0 || count >= 20) {
			count = 20;
		}
		if (offset <= 0) {
			offset = 0;
		}
		if (curTab <= 1 || curTab >= 5) {
			curTab = 1;
		}
		try {
			return httpRequest.sendGetRequest(String.format(SEARCH_URL, keyword, count, offset,
					curTab, System.currentTimeMillis()));
		} catch (Exception e) {
			e.printStackTrace();
			return FAILMSG;
		}
	}
}
