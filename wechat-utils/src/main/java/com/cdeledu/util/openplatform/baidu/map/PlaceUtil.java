package com.cdeledu.util.openplatform.baidu.map;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import com.cdeledu.common.constant.ConstantHelper;
import com.cdeledu.common.network.UrlHelper;
import com.cdeledu.util.network.tcp.HttpURLConnHelper;
import com.cdeledu.util.openplatform.baidu.map.entity.PlaceApi;

/**
 * 
 * @类描述:
 * 		<ul>
 *       <li>Place API 是一套免费使用的API接口，调用次数限制默认为2000次/天</li>
 *       <li>用于返回查询某个区域的某类POI数据且提供单个POI的详情查询服务,提供区域检索POI服务、POI详情服务</li>
 *       </ul>
 * @创建者: 皇族灬战狼
 * @创建时间: 2014年10月02日 下午4:26:24
 * @更新时间: 2016年10月14日 下午8:40:54
 * @版本: V2.0
 * @since: JDK 1.7
 * @see <a href=
 *      "http://lbsyun.baidu.com/index.php?title=webapi/guide/webservice-placeapi">
 *      Place API Web服务API </a>
 */
class PlaceUtil {
	/** ----------------------------------------------------- Fields start */
	/** place区域检索POI服务 */
	private final static String PLACE_SEARCH = "http://api.map.baidu.com/place/v2/search";
	/** POI详情服务 */
	private final static String PLACE_DETAIL = " http://api.map.baidu.com/place/v2/detail";
	/** 请求地址 */
	private static String realUrl = "";
	/** 请求接口返回结果 */
	private static String reslut = "";
	/** 请求参数 */
	private static Map<String, Object> paramsMap = null;
	/** function返回结果 */
	private static Map<String, Object> resultmap = null;
	private static HttpURLConnHelper conn = null;

	static {
		conn = HttpURLConnHelper.getInstance(ConstantHelper.UTF_8.name());
	}

	/** ----------------------------------------------------- Fields end */

	/** ----------------------------------------------------- [私有方法] */
	/**
	 * Place区域检索通用接口参数
	 */
	private static Map<String, Object> searchCondition(String ak, String query, Integer page_num,
			Integer scope) {
		if (null == page_num) {
			page_num = 0;
		}

		if (null == scope || 1 != scope || 2 != scope) {
			scope = 1;
		}
		Map<String, Object> searchMap = new HashMap<String, Object>();
		/** 用户的访问密钥 */
		searchMap.put("ak", ak);
		/** 检索关键字:周边检索和矩形区域内检索支持多个关键字并集检索,不同关键字间以$符号分隔,最多支持10个关键字检索 */
		searchMap.put("query", query);
		/** 输出格式为json或者xml */
		searchMap.put("output", "json");
		/** 范围记录数量,默认为10条记录,最大返回20条.多关键字检索时,返回的记录数为关键字个数*page_size */
		searchMap.put("page_size", "20");
		/** 分页页码，默认为0,0代表第一页，1代表第二页 */
		searchMap.put("page_num", Integer.toString(page_num));
		/** 检索结果详细程度--1 或空:则返回基本信息;2:返回检索POI详细信息 */
		searchMap.put("scope", Integer.toString(scope));
		return searchMap;
	}

	/**
	 * 将结果解析
	 */
	private static Map<String, Object> JsonStringToMap(String jsonString) throws Exception {
		List<PlaceApi> resultList = new ArrayList<PlaceApi>();
		JSONObject json = new JSONObject(jsonString);
		if (json.get("status").equals(0)) {
			if (json.has("results")) {
				JSONArray j_result = (JSONArray) json.get("results");
				for (int i = 0; i < j_result.length(); i++) {
					String j_result_str = String.valueOf(j_result.get(i));
					PlaceApi info = baiduMapTag(j_result_str);
					resultList.add(info);
				}
			} else if (json.has("result")) {
				String j_result_str = json.get("result").toString();
				PlaceApi info = baiduMapTag(j_result_str);
				resultList.add(info);
			}
			if (json.has("total")) {
				resultmap.put("total", json.getInt("total"));
			}
			resultmap.put("status", 0);
			resultmap.put("result", resultList);
		} else {
			resultmap.put("status", 1);
		}
		return resultmap;
	}

	private static PlaceApi baiduMapTag(String j_result_str) throws Exception {
		JSONObject result = new JSONObject(j_result_str);
		PlaceApi info = new PlaceApi();
		info.setName(String.valueOf(result.get("name")));
		String location = String.valueOf(result.get("location"));
		JSONObject j_location = new JSONObject(location);
		info.setLng(Float.parseFloat(j_location.get("lng").toString()));
		info.setLat(Float.parseFloat(j_location.get("lat").toString()));

		info.setAddress(String.valueOf(result.get("address")));

		if (result.has("telephone")) {
			info.setTelephone(String.valueOf(result.get("telephone")));
		}
		if (result.has("street_id")) {
			info.setStreet_id(String.valueOf(result.get("street_id")));
		}

		info.setUid(String.valueOf(result.get("uid")));
		String detail_info = String.valueOf(result.get("detail_info"));
		JSONObject j_detail_info = new JSONObject(detail_info);
		// 距离中心点的距离
		if (j_detail_info.has("distance")) {
			info.setDistance(j_detail_info.getInt("distance"));
		}
		// 所属分类，如’hotel’、’cater’
		if (j_detail_info.has("type")) {
			info.setType(j_detail_info.getString("type"));
		}
		// 标签
		if (j_detail_info.has("tag")) {
			info.setTag(j_detail_info.getString("tag"));
		}
		// POI的详情页
		if (j_detail_info.has("detail_url")) {
			info.setDetail_url(j_detail_info.getString("detail_url"));
		}
		// POI的商户的价格
		if (j_detail_info.has("price")) {
			info.setPrice(j_detail_info.getString("price"));
		}
		// 营业时间
		if (j_detail_info.has("shop_hours")) {
			info.setShop_hours(j_detail_info.getString("shop_hours"));
		}
		// 总体评分
		if (j_detail_info.has("overall_rating")) {
			String overall = j_detail_info.getString("overall_rating");
			info.setOverall_rating(overall);
		}
		// 口味评分
		if (j_detail_info.has("taste_rating")) {
			String taste = j_detail_info.getString("taste_rating");
			info.setTaste_rating(taste);
		}
		// 服务评分
		if (j_detail_info.has("service_rating")) {
			String service = j_detail_info.getString("service_rating");
			info.setService_rating(service);
		}
		// 环境评分
		if (j_detail_info.has("environment_rating")) {
			String env = j_detail_info.getString("environment_rating");
			info.setEnvironment_rating(env);
		}
		// 星级（设备）评分
		if (j_detail_info.has("facility_rating")) {
			String fac = j_detail_info.getString("facility_rating");
			info.setFacility_rating(fac);
		}
		// 卫生评分
		if (j_detail_info.has("hygiene_rating")) {
			String hyg = j_detail_info.getString("hygiene_rating");
			info.setHygiene_rating(hyg);
		}
		// 技术评分
		if (j_detail_info.has("technology_rating")) {
			String tec = j_detail_info.getString("technology_rating");
			info.setTechnology_rating(tec);
		}
		// 图片数
		if (j_detail_info.has("image_num")) {
			info.setImage_num(j_detail_info.getString("image_num"));
		}
		// 团购数
		if (j_detail_info.has("groupon_num")) {
			info.setGroupon_num(j_detail_info.getInt("groupon_num"));
		}
		// 优惠数
		if (j_detail_info.has("discount_num")) {
			info.setDiscount_num(j_detail_info.getInt("discount_num"));
		}
		// 评论数
		if (j_detail_info.has("comment_num")) {
			info.setComment_num(j_detail_info.getString("comment_num"));
		}
		// 收藏数
		if (j_detail_info.has("favorite_num")) {
			String fav = j_detail_info.getString("favorite_num");
			info.setFavorite_num(fav);
		}
		// 签到数
		if (j_detail_info.has("checkin_num")) {
			info.setCheckin_num(j_detail_info.getString("checkin_num"));
		}
		return info;
	}

	/** ----------------------------------------------------- [私有方法] */

	/**
	 * @方法描述: 区域检索POI服务只之城市内检索
	 * @param ak
	 *            用户的访问密钥，必填项
	 * @param query
	 *            <ul>
	 *            <li>检索关键字。是必须的，不能为空</li>
	 *            <li>周边检索和矩形区域内检索支持多个关键字并集检索</li>
	 *            <li>不同关键字间以$符号分隔，最多支持10个关键字检索</li>
	 *            </ul>
	 * @param page_num
	 *            分页页码，默认为0,0代表第一页，1代表第二页，以此类推
	 * @param region
	 *            检索区域，如果取值为“全国”或某省份，则返回指定区域的POI
	 * @param scope
	 *            检索结果详细程度:取值为1 或空,则返回基本信息;取值为2,返回检索POI详细信息
	 * @return
	 */
	public static Map<String, Object> SearchInfoByQueryKeyword(String ak, String query,
			Integer page_num, String region, Integer scope) {
		resultmap = new HashMap<String, Object>();
		paramsMap = new HashMap<String, Object>();

		if (StringUtils.isBlank(region)) {
			region = "全国";
		}

		paramsMap = searchCondition(ak, query, page_num, scope);
		try {
			paramsMap.put("region", URLEncoder.encode(region, "UTF-8"));
		} catch (Exception e) {
		}
		// paramsMap.put("city_limit", URLEncoder.encode(region, "UTF-8"));
		realUrl = UrlHelper.formatParameters(PLACE_SEARCH, paramsMap);
		try {
			reslut = conn.sendPostRequest(realUrl);
			resultmap = JsonStringToMap(reslut);
		} catch (Exception e) {
			resultmap.put("status", 1);
		}

		return resultmap;
	}

	/**
	 * 
	 * @Title：SearchInfoByLocationAndRadius
	 * @Description：区域检索POI服务只之圆形区域检索
	 * @param ak
	 *            用户的访问密钥，必填项
	 * @param query
	 *            <ul>
	 *            <li>检索关键字。是必须的，不能为空</li>
	 *            <li>周边检索和矩形区域内检索支持多个关键字并集检索</li>
	 *            <li>不同关键字间以$符号分隔，最多支持10个关键字检索</li>
	 *            </ul>
	 * @param page_num
	 *            分页页码，默认为0,0代表第一页，1代表第二页，以此类推
	 * @param lng
	 *            经度 (必须)
	 * @param lat
	 *            纬度 (必须)
	 * @param radius
	 *            周边检索半径，单位为米
	 * @return
	 * @return：Map<String,Object> 返回类型
	 */
	public static Map<String, Object> SearchInfoByLocationAndRadius(String ak, String query,
			Integer page_num, float lng, float lat, int radius, Integer scope) {
		resultmap = new HashMap<String, Object>();
		paramsMap = new HashMap<String, Object>();
		String location = lat + "," + lng;

		if (radius == 0) {
			radius = 1000;
		}

		paramsMap = searchCondition(ak, query, page_num, scope);
		paramsMap.put("location", location);
		paramsMap.put("radius", String.valueOf(radius));

		realUrl = UrlHelper.formatParameters(PLACE_SEARCH, paramsMap);
		try {
			reslut = conn.sendPostRequest(realUrl);
			resultmap = JsonStringToMap(reslut);
		} catch (Exception e) {
			resultmap.put("status", 1);
		}
		return resultmap;
	}

	/**
	 * 
	 * @Title：SearchInfoByPlaceDetail
	 * @Description：提供查询某个POI点的详情信息，如好评，评价等。 @param ak 用户的访问密钥，必填项。
	 * @param uid
	 *            poi的uid
	 * @return
	 * @return：Map<String,Object> 返回类型
	 */
	public static Map<String, Object> SearchInfoByPlaceDetail(String ak, String uid,
			Integer scope) {
		resultmap = new HashMap<String, Object>();
		paramsMap = new HashMap<String, Object>();
		paramsMap = searchCondition(ak, null, null, scope);
		paramsMap.put("uid", uid);

		realUrl = UrlHelper.formatParameters(PLACE_DETAIL, paramsMap);

		try {
			reslut = conn.sendPostRequest(realUrl);
			resultmap = JsonStringToMap(reslut);
		} catch (Exception e) {
			resultmap.put("status", 0);
		}

		return resultmap;
	}
}
