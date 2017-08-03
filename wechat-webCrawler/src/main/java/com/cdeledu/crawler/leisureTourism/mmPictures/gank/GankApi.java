package com.cdeledu.crawler.leisureTourism.mmPictures.gank;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import com.cdeledu.util.apache.lang.DateUtilHelper;
import com.cdeledu.util.network.tcp.HttpURLConnHelper;
import com.google.common.collect.Lists;

public class GankApi {
	/** ----------------------------------------------------- Fields start */

	private final static String BASE_SHARE_URL = "http://gank.io/api/data/%s/%s/%s";
	private static HttpURLConnHelper httpRequest = HttpURLConnHelper.getInstance();

	/** ----------------------------------------------------- Fields end */

	/** ----------------------------------------------- [私有方法] */
	/** ----------------------------------------------- [私有方法] */

	/**
	 * @方法描述: 分类数据
	 * @param type
	 *            数据类型： 福利 | Android | iOS | 休息视频 | 拓展资源 | 前端 | all
	 * @param num
	 *            请求个数： 数字，大于0
	 * @param page
	 *            第几页：数字，大于0
	 */
	public static List<Gank> getGanSharekData(String type, Integer num, Integer page)
			throws Exception {
		String result = "";
		List<Gank> resultList = Lists.newArrayList();
		Gank gank = null;
		try {
			result = httpRequest.sendGetRequest(String.format(BASE_SHARE_URL, type, num, page));

		} catch (Exception e) {
			e.printStackTrace();
		}
		if (StringUtils.isNoneBlank(result)) {
			JSONObject json_1 = new JSONObject(result);
			if (json_1.has("error") && !json_1.getBoolean("error")) {
				JSONArray json_2 = json_1.getJSONArray("results");
				JSONObject json_3 = null;
				Integer size = json_2.length();
				for (int i = 0; i < size; i++) {
					gank = new Gank();
					json_3 = new JSONObject(json_2.get(i).toString());
					gank.setDesc(json_3.getString("desc"));
					gank.setPublishedAt(DateUtilHelper.parseDateTime(json_3.getString("publishedAt"), false));
					gank.setUrl(json_3.getString("url"));
					resultList.add(gank);
				}
			}
		}
		return resultList;
	}

}
