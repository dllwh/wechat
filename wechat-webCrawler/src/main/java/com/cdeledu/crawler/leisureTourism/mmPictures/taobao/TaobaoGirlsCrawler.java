package com.cdeledu.crawler.leisureTourism.mmPictures.taobao;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.cdeledu.common.network.UrlHelper;
import com.cdeledu.util.network.tcp.HttpURLConnHelper;
import com.google.common.collect.Maps;

/**
 * @类描述: 爬取淘女郎的页面
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建日期: 2017年8月27日 下午9:28:17
 * @版本: V1.0
 * @since: JDK 1.7
 * @see <a href="https://www.taobao.com/markets/mm/mm2017">淘女郎</a>
 */
public class TaobaoGirlsCrawler {
	/** ----------------------------------------------------- Fields start */
	private static final String baseUrl = "https://mm.taobao.com/";
	private static final String tstarSearch = baseUrl + "tstar/search/tstar_model.do";

	public int totalCount, totalPage = 1;

	/** ----------------------------------------------------- Fields end */

	public static void get(int currentPage, String city, String searchStyle) throws Exception {
		Map<String, Object> paramsMap = Maps.newConcurrentMap();
		paramsMap.put("_input_charset", "utf-8");
		if (currentPage < 1)
			currentPage = 1;
		paramsMap.put("currentPage", currentPage);
		paramsMap.put("pageSize", 100);
		if (StringUtils.isNoneBlank(city))
			paramsMap.put("searchRegion", "city:" + city);
		if (StringUtils.isNoneBlank(searchStyle))
			paramsMap.put("searchStyle", searchStyle);
		paramsMap.put("sortType", "default");
		paramsMap.put("viewFlag", "A");

		String url = UrlHelper.appendParaToUrl(tstarSearch, UrlHelper.formatParameters(paramsMap));
		String result = HttpURLConnHelper.getInstance().sendPostRequest(url);
		System.out.println(result);
	}

	public static void main(String[] args) {
		try {
			get(1, null, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
