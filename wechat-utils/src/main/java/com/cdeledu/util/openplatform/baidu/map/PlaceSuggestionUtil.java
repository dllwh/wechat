package com.cdeledu.util.openplatform.baidu.map;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import com.cdeledu.common.network.UrlHelper;
import com.cdeledu.util.openplatform.baidu.model.PlaceSuggestionAPI;

/**
 * 
 * @title : PlaceSuggestionUtil
 * 
 * @author : 独泪了无痕
 * 
 * @方法描述 :
 *       <p>
 *       Place suggestion API
 *       <p>
 *       是一套以HTTP形式提供的匹配用户输入关键字辅助信息、提示接口
 *       <p>
 */
class PlaceSuggestionUtil {

	private final static String PLACE = "http://api.map.baidu.com/place/v2/suggestion";

	/**
	 * 
	 * @Title：SearchInfoByQueryKeyword
	 * @Description：匹配用户输入的关键字的建议列表
	 * @param ak
	 *            匹配用户输入的关键字的建议列表
	 * @param query
	 *            输入建议关键字（支持拼音）
	 * @param region
	 *            所属城市/区域名称或代号
	 * @return
	 * @return：Map<String,Object> 返回类型
	 */
	public static Map<String, Object> SearchInfoByQueryKeyword(String ak,
			String query, String region) {
		BufferedReader l_reader = null;
		HttpURLConnection l_connection = null;
		String jsonString = "";
		String msg = null;
		Map<String, Object> resultmap = new HashMap<String, Object>();
		if (StringUtils.isBlank(region)) {
			region = "全国";
		}
		try {
			Map<String, Object> paramsMap = new HashMap<String, Object>();
			paramsMap.put("ak", ak);
			paramsMap.put("output", "json");
			paramsMap.put("query", query);
			paramsMap.put("page_size", "");
			paramsMap.put("region", region);

			String url = UrlHelper.formatParameters(PLACE, paramsMap);
			URL l_url = new URL(url);
			l_connection = (HttpURLConnection) l_url.openConnection();
			l_connection.connect();

			l_reader = new BufferedReader(new InputStreamReader(
					l_connection.getInputStream()));
			while ((msg = l_reader.readLine()) != null) {
				jsonString = jsonString + msg;
				jsonString = jsonString.replace(" ", "");
			}
			resultmap = JsonStringToMap(jsonString);
		} catch (Exception e) {
			resultmap.put("status", 1);
		} finally {
			try {
				if (l_reader != null) {
					l_reader.close();
				}
				if (l_connection != null) {
					l_connection.disconnect();
				}
			} catch (Exception e2) {
				resultmap.put("status", 1);
			}
		}
		return resultmap;
	}

	private static Map<String, Object> JsonStringToMap(String jsonString)
			throws Exception {
		Map<String, Object> resultmap = new HashMap<String, Object>();
		List<PlaceSuggestionAPI> resultList = new ArrayList<PlaceSuggestionAPI>();
		JSONObject json = new JSONObject(jsonString);
		if (json.get("status").equals(0)) {
			JSONArray j_result = (JSONArray) json.get("result");
			for (int i = 0; i < j_result.length(); i++) {
				String j_result_str = String.valueOf(j_result.get(i));
				PlaceSuggestionAPI info = baiduMapTag(j_result_str);
				resultList.add(info);
			}
			resultmap.put("status", 0);
			resultmap.put("result", resultList);
		} else {
			resultmap.put("status", 1);
		}
		return resultmap;
	}

	private static PlaceSuggestionAPI baiduMapTag(String j_result_str)
			throws Exception {
		JSONObject result = new JSONObject(j_result_str);
		PlaceSuggestionAPI info = new PlaceSuggestionAPI();
		info.setUid(result.getString("uid"));
		info.setName(result.getString("name"));
		info.setCity(result.getString("city"));
		info.setDistrict(result.getString("district"));
		info.setBusiness(result.getString("business"));
		info.setCityid(result.getString("cityid"));
		return info;
	}
}
