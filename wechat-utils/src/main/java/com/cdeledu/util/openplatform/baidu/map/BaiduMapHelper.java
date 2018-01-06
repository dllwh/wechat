package com.cdeledu.util.openplatform.baidu.map;

import java.util.Map;

import com.cdeledu.util.openplatform.baidu.model.LatitudeInfo;

/**
 * @类描述: 百度地图工具类(调用百度地图接口)
 * @创建者: 独泪了无痕
 * @创建时间: 2015年7月17日 下午1:37:25
 * @更新时间: 2016年10月17日 下午2:08:33
 * @版本: V1.3
 * @since: JDK 1.7
 * @see <a href="http://developer.baidu.com/map/index.php?title=webapi">
 *      百度地图Web服务API</a>
 */
public class BaiduMapHelper {
	/**
	 * @Title: SearchLocalByGeocoding_API
	 * @Description: 通过 GEocoding API 根据经纬度查询周边信息
	 * @author: 独泪了无痕
	 * @param ak
	 * @param lng
	 *            经度值
	 * @param lat
	 *            纬度值
	 * @return
	 */
	public static Map<String, Object> SearchLocalByGeocoding_API(String ak, float lng, float lat) {
		return GeocodingUtil.SearchLocalByGeocodingAPI(ak, lng, lat);
	}

	/**
	 * @Title: SearchLatitudeByGeocoding_API
	 * @Description: 发送一个地址请求，返回该地址对应的地理坐标(经纬度)
	 * @author: 独泪了无痕
	 * @param ak
	 * @param address
	 * @param city
	 * @return
	 */
	public static LatitudeInfo SearchLatitudeByGeocoding_API(String ak, String address,
			String city) {
		return GeocodingUtil.SearchLatitudeByGeocodingAPI(ak, address, city);
	}

	/**
	 * @Title: SearchInfoByQueryKeyword
	 * @Description: 城市内检索关键字
	 * @author: 独泪了无痕
	 * @param ak
	 * @param query
	 * @param page_num
	 * @param region
	 * @return
	 */
	public static Map<String, Object> SearchInfoByQueryKeyword(String ak, String query,
			Integer page_num, String region) {
		return PlaceUtil.SearchInfoByQueryKeyword(ak, query, page_num, region, 2);
	}

	/**
	 * @Title: SearchInfoByLocationAndRadius
	 * @Description: 根据经纬度查询周围半径1000米范围之内的信息<br>
	 *               每次最多查询20个
	 * @author: 独泪了无痕
	 * @param ak
	 * @param query
	 * @param page_num
	 * @param lng
	 * @param lat
	 * @param radius
	 * @return
	 */
	public static Map<String, Object> SearchInfoByLocationAndRadius(String ak, String query,
			Integer page_num, float lng, float lat, int radius) {
		return PlaceUtil.SearchInfoByLocationAndRadius(ak, query, page_num, lng, lat, radius, 2);
	}

	/**
	 * @Title: SearchInfoByPlaceDetail
	 * @Description: 查询POI唯一标识查询
	 * @author: 独泪了无痕
	 * @param ak
	 * @param uid
	 * @return
	 */
	public static Map<String, Object> SearchInfoByPlaceDetail(String ak, String uid) {
		return PlaceUtil.SearchInfoByPlaceDetail(ak, uid, 2);
	}

	/**
	 * @Title: SearchAuxiliaryInfoByQueryKeyword
	 * @Description: 匹配用户输入关键字辅助信息
	 * @author: 独泪了无痕
	 * @param ak
	 * @param query
	 * @param region
	 * @return
	 */
	public static Map<String, Object> SearchAuxiliaryInfoByQueryKeyword(String ak, String query,
			String region) {
		return PlaceSuggestionUtil.SearchInfoByQueryKeyword(ak, query, region);
	}
	/*-------------------------- 公有方法 end   -------------------------------*/
}
