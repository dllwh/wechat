package com.cdeledu.util.openplatform.baidu.baiduMap;

import java.net.URLEncoder;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.cdeledu.common.constant.ConstantHelper;
import com.cdeledu.common.network.UrlHelper;
import com.cdeledu.util.network.tcp.HttpURLConnHelper;
import com.cdeledu.util.openplatform.baidu.baiduMap.model.RenderReverseResponse;
import com.cdeledu.util.openplatform.baidu.baiduMap.model.ShowLocationResponse;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 地理编码服务（又名Geocoding API）是一类Web API接口服务
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2018年4月20日 下午7:40:40
 * @版本: V1.0
 * @since: JDK 1.7
 * @see <a href=
 *      "lbsyun.baidu.com/index.php?title=webapi/guide/webservice-geocoding">简介
 *      </a>
 */
final class GeocodingAPIHelper {
	/** ----------------------------------------------------- Fields start */
	private static HttpURLConnHelper connHelper = null;
	private static Gson gsonHelper = null;
	private final static String charsetName = ConstantHelper.UTF_8.name();

	/** ----------------------------------------------------- Fields end */
	static {
		connHelper = HttpURLConnHelper.getInstance();
		gsonHelper = new Gson();
	}

	/**
	 * @方法:地理编码
	 * @创建人:独泪了无痕
	 * @param address
	 *            待解析的地址。最多支持84个字节
	 * @param city
	 *            地址所在的城市名
	 * @param ak
	 *            开发者的访问密钥
	 * @param callback
	 *            将json格式的返回值通过callback函数返回以实现jsonp功能
	 * @return
	 * @throws JsonSyntaxException
	 * @throws Exception
	 */
	public static ShowLocationResponse showLocation(String ak, String address, String city,
			String callback) throws JsonSyntaxException, Exception {
		if (StringUtils.isNotBlank(address)) {
			address = URLEncoder.encode(address, charsetName);
		} else {
			address = "";
		}

		if (StringUtils.isNotBlank(city)) {
			city = URLEncoder.encode(city, charsetName);
		} else {
			city = "";
		}

		if (StringUtils.isBlank(callback)) {
			callback = "";
		}

		String url = "http://api.map.baidu.com/geocoder/v2/?address=" + address + "&city" + city
				+ "&output=json&ret_coordtype=bd09mc&ak=" + ak + "&callback=" + callback;
		ShowLocationResponse response = gsonHelper.fromJson(connHelper.sendGetRequest(url),
				ShowLocationResponse.class);
		response.setResquestUrl(url);
		return response;
	}

	/**
	 * @方法:逆地理编码
	 * @创建人:独泪了无痕
	 * @param ak
	 * @param lat
	 *            纬度
	 * @param lng
	 *            经度
	 * @param pois
	 *            是否召回传入坐标周边的poi，0为不召回，1为召回。当值为1时，默认显示周边1000米内的poi。
	 * @param radius
	 *            poi召回半径，允许设置区间为0-1000米，超过1000米按1000米召回
	 * @param callback
	 *            将json格式的返回值通过callback函数返回以实现jsonp功能
	 * @param extensions_road
	 *            当取值为true时，召回坐标周围最近的3条道路数据
	 * @param extensions_town
	 *            当取值为true时，行政区划返回乡镇级数据（仅国内召回乡镇数据）。默认不访问
	 * @param language
	 *            指定召回的新政区划语言类型
	 * @param language_auto
	 *            是否自动填充行政区划。1填充，0不填充。
	 * @param latest_admin
	 *            是否访问最新版行政区划数据（仅对中国数据生效），1（访问），0（不访问）
	 * @throws Exception
	 */
	public static RenderReverseResponse renderReverse(String ak, float lat, float lng,
			String callback, boolean extensionsRoad, boolean extensionsTown, String language,
			boolean languageAuto, boolean latestAdmin) throws Exception {
		Map<String, Object> paramMap = Maps.newHashMap();
		paramMap.put("ak", ak);
		paramMap.put("location", lat + "," + lng);
		paramMap.put("pois", 1);
		paramMap.put("radius", 1000);
		paramMap.put("output", "json");
		if (StringUtils.isNotBlank(callback)) {
			paramMap.put("callback", callback);
		}
		paramMap.put("extensions_road", extensionsRoad);
		if (extensionsTown) {
			paramMap.put("extensions_town", extensionsTown);
		}
		if (StringUtils.isBlank(language)) {
			language = "local";
		}
		paramMap.put("language", language);
		if (languageAuto) {
			paramMap.put("language_auto", 1);
		} else {
			paramMap.put("language_auto", 0);
		}
		if (latestAdmin) {
			paramMap.put("latest_admin", 1);
		} else {
			paramMap.put("latest_admin", 0);
		}
		String url = "http://api.map.baidu.com/geocoder/v2/?"
				+ UrlHelper.formatParameters(paramMap);
		RenderReverseResponse response = gsonHelper.fromJson(connHelper.sendGetRequest(url),
				RenderReverseResponse.class);
		response.setResquestUrl(url);
		return response;
	}
}
