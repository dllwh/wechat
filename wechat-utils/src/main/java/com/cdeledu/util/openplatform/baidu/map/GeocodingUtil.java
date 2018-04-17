package com.cdeledu.util.openplatform.baidu.map;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.cdeledu.common.network.UrlHelper;
import com.cdeledu.util.openplatform.baidu.map.entity.LatitudeInfo;
import com.cdeledu.util.openplatform.baidu.map.entity.LocalInfo;

class GeocodingUtil {
	private final static String GEOCODER = "http://api.map.baidu.com/geocoder/v2/";

	/**
	 * 
	 * @title : SearchLatitudeByGeocoding_API
	 * 
	 * @author : 独泪了无痕
	 * 
	 * @描述 :发送一个地址请求，返回该地址对应的地理坐标
	 * 
	 * @param ak
	 * @param address
	 *            <p>
	 *            该参数是地理编码的必填项，可以输入三种样式的值，分别是：
	 *            <p>
	 *            • 标准的地址信息，如北京市海淀区上地十街十号;
	 *            <p>
	 *            • 名胜古迹、标志性建筑物，如天安门，百度大厦;
	 *            <p>
	 *            • 支持 “*路与*路交叉口”描述方式，如北一环路和阜阳路的交叉路口
	 *            <p>
	 *            注意：后两种方式并不总是有返回结果，只有当地址库中存在该地址描述时才有返回。
	 * @param city
	 *            <p>
	 *            该参数是可选项，用于指定上述地址所在的城市，
	 *            <p>
	 *            当多个城市都有上述地址时，该参数起到过滤作用。
	 *            <p>
	 * @return
	 */
	public static LatitudeInfo SearchLatitudeByGeocodingAPI(String ak, String address,
			String city) {
		BufferedReader l_reader = null;
		HttpURLConnection l_connection = null;
		JSONObject json;
		String jsonString = "";
		if (StringUtils.isNotBlank(city)) {
			boolean flag = StringUtils.endsWith(city, "市");
			if (!flag) {
				city = city + "市";
			}
		}
		LatitudeInfo info = new LatitudeInfo();

		if (StringUtils.isNotBlank(address)) {
			Map<String, Object> paramsMap = new HashMap<String, Object>();
			paramsMap.put("ak", ak);
			paramsMap.put("output", "json");
			paramsMap.put("address", address.trim().replace(" ", ""));
			if (StringUtils.isNotBlank(city)) {
				paramsMap.put("city", city);
			}

			String url = UrlHelper.formatParameters(GEOCODER, paramsMap);

			try {
				URL l_url = new URL(url);
				l_connection = (HttpURLConnection) l_url.openConnection();
				l_connection.connect();
				l_reader = new BufferedReader(new InputStreamReader(l_connection.getInputStream()));

				jsonString = l_reader.readLine();
				json = new JSONObject(jsonString);

				int flag = json.getInt("status");
				if (flag == 0) {
					JSONObject jresult = (JSONObject) json.get("result");

					JSONObject jlocation = (JSONObject) jresult.get("location");
					String lng = String.valueOf(jlocation.getDouble("lng"));
					String lat = String.valueOf(jlocation.getDouble("lat"));
					info.setLng(Float.parseFloat(lng));
					info.setLat(Float.parseFloat(lat));
					info.setStatus(0);
				} else {
					info.setStatus(1);
				}
			} catch (Exception e) {
				// e.printStackTrace();
				info.setStatus(1);
			} finally {
				try {
					if (l_reader != null) {
						l_reader.close();
					}
					if (l_connection != null) {
						l_connection.disconnect();
					}
				} catch (Exception e2) {
					info.setStatus(1);
				}
			}
		} else {
			info.setStatus(1);
		}

		return info;
	}

	/**
	 * 
	 * @title : SearchLocalByLatitude
	 * 
	 * @author : 独泪了无痕
	 * 
	 * @描述 :
	 *     <p>
	 *     通过 GEocoding API 根据经纬度查询周边信息
	 *     <p>
	 *     是否显示指定位置周边的POI，0为不显示，1为显示。
	 *     <p>
	 *     当值为1时，显示周边100米内的poi。
	 * 
	 * @param lng
	 *            : 经度值
	 * @param lat
	 *            : 纬度值
	 * @return
	 */
	public static Map<String, Object> SearchLocalByGeocodingAPI(String ak, float lng, float lat) {
		BufferedReader l_reader = null;
		HttpURLConnection l_connection = null;
		JSONObject json;
		String jsonString = "";
		Map<String, Object> resultmap = new HashMap<String, Object>();
		List<LocalInfo> resultList = new ArrayList<LocalInfo>();
		try {
			Map<String, Object> paramsMap = new HashMap<String, Object>();
			paramsMap.put("ak", ak);
			paramsMap.put("pois", "1");
			paramsMap.put("output", "json");
			paramsMap.put("location", lat + "," + lng);

			String url = UrlHelper.formatParameters(GEOCODER, paramsMap);

			URL l_url = new URL(url);
			l_connection = (HttpURLConnection) l_url.openConnection();
			l_connection.connect();

			l_reader = new BufferedReader(new InputStreamReader(l_connection.getInputStream()));
			jsonString = l_reader.readLine();
			json = new JSONObject(jsonString);
			int status = json.getInt("status");
			if (status == 0) {
				JSONObject j_result = (JSONObject) json.get("result");
				JSONArray js_pois = (JSONArray) j_result.get("pois");
				for (int i = 0; i < js_pois.length(); i++) {
					String pois = String.valueOf(js_pois.get(i));
					LocalInfo info = geocodingTag(pois);
					resultList.add(info);
				}
				resultmap.put("status", 0);
				resultmap.put("result", resultList);
			} else {
				resultmap.put("status", 1);
			}
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

	private static LocalInfo geocodingTag(String pois) throws JSONException {
		JSONObject pois_json = new JSONObject(pois);
		// POI坐标{x,y}
		JSONObject j_point = new JSONObject(pois_json.get("point").toString());
		LocalInfo info = new LocalInfo();
		// 地址信息
		info.setAddr(String.valueOf(pois_json.get("addr")));
		// 数据来源
		info.setCp(String.valueOf(pois_json.get("cp")));
		// 离坐标点距离
		info.setDistance(String.valueOf(pois_json.get("distance")));
		// POI名称
		info.setName(String.valueOf(pois_json.get("name")));
		// POI类型，如’ 办公大厦,商务大厦’
		info.setPoiType(String.valueOf(pois_json.get("poiType")));

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("lng", j_point.get("x"));
		map.put("lat", j_point.get("y"));
		// 电话
		info.setTel(String.valueOf(pois_json.get("tel")));
		// POI唯一标识
		info.setUid(String.valueOf(pois_json.get("uid")));
		// 邮编
		info.setZip(String.valueOf(pois_json.get("zip")));
		return info;
	}
}
