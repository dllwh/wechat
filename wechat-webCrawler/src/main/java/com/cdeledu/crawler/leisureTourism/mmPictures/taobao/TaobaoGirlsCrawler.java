package com.cdeledu.crawler.leisureTourism.mmPictures.taobao;

import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import com.cdeledu.common.network.UrlHelper;
import com.cdeledu.util.network.tcp.HttpClientHelper;
import com.cdeledu.util.network.tcp.HttpURLConnHelper;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 * 
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 爬取淘女郎的页面
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建日期: 2017年8月27日 下午9:28:17
 * @版本: V1.0
 * @since: JDK 1.7
 * @see <a href="https://www.taobao.com/markets/mm/mm2017">淘女郎</a>
 */
public class TaobaoGirlsCrawler {
	/** ----------------------------------------------------- Fields start */
	private static final String TSTARSEARCH = "https://mm.taobao.com/tstar/search/tstar_model.do";

	/** ----------------------------------------------------- Fields end */

	/**
	 * @方法:获取淘女郎的总页码
	 * @创建人:独泪了无痕
	 * @return
	 * @throws Exception
	 */
	public static int getGirlsTotalPage() throws Exception {
		String result = HttpURLConnHelper.getInstance().sendPostRequest(TSTARSEARCH);
		JSONObject _jResult = new JSONObject(result);
		int totalPage = 0;
		if (_jResult.has("status") && _jResult.getInt("status") == 1) {
			totalPage = _jResult.getJSONObject("data").getInt("totalPage");
		}
		return totalPage;
	}

	/**
	 * 
	 * @方法:抓取信息
	 * @创建人:独泪了无痕
	 * @param currentPage
	 * @throws Exception
	 */
	public static void getMmPictures(int currentPage) throws Exception {
		Map<String, Object> paramsMap = Maps.newConcurrentMap();
		paramsMap.put("_input_charset", "utf-8");
		if (currentPage < 1)
			currentPage = 1;
		paramsMap.put("currentPage", currentPage);
		paramsMap.put("pageSize", 100);
		paramsMap.put("sortType", "default");
		paramsMap.put("viewFlag", "A");
		List<Map<String, Object>> mmPicList = null;
		String url = UrlHelper.appendParaToUrl(TSTARSEARCH, UrlHelper.formatParameters(paramsMap));
		String result = HttpClientHelper.getInstance().sendPostRequest(url);
		JSONObject _jResult = new JSONObject(result);
		TaobaoGrilAlbumList grilAlbumList = null;
		if (_jResult.has("status") && _jResult.getInt("status") == 1) {
			JSONArray jsArray = _jResult.getJSONObject("data").getJSONArray("searchDOList");
			int userId = 0;
			for (int i = 0; i < jsArray.length(); i++) {
				try {
					mmPicList = Lists.newArrayList();
					JSONObject parseInfo = jsArray.getJSONObject(i);
					userId = parseInfo.getInt("userId");
					// realName = parseInfo.getString("realName");
					grilAlbumList = new TaobaoGrilAlbumList(userId);
					grilAlbumList.getGrilAlbumList();
					mmPicList.addAll(grilAlbumList.mmPicList);
				} catch (Exception e) {
					e.printStackTrace();
					continue;
				}
				break;
			}
		}
	}

	public static void main(String[] args) {
		try {
			getGirlsTotalPage();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}