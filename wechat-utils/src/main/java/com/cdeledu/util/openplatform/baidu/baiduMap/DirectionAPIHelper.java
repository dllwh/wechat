package com.cdeledu.util.openplatform.baidu.baiduMap;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.cdeledu.common.network.UrlHelper;
import com.cdeledu.util.application.QvoConditionUtil;
import com.google.common.collect.Maps;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 路线规划服务（又名Direction API）是一套REST风格的Web服务API
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2018年4月20日 下午7:42:06
 * @版本: V1.0
 * @since: JDK 1.7
 * @see <a href="lbsyun.baidu.com/index.php?title=webapi/direction-api">简介</a>
 */
class DirectionAPIHelper {
	/** ----------------------------------------------------- Fields start */
	private final static String basePath = "http://api.map.baidu.com/direction/v2/";

	/** ----------------------------------------------------- Fields end */
	/**
	 * 
	 * @方法:公交路线规划
	 * @创建人:独泪了无痕
	 * @param ak
	 *            开发者密钥
	 * @param startLat
	 *            起点纬度
	 * @param startLng
	 *            起点
	 * @param endLat
	 *            终点纬度
	 * @param endLng
	 *            终点
	 * @param tacticsIncity
	 *            市内公交换乘策略,0 推荐；1 少换乘；2 少步行；3 不坐地铁；4 时间短；5 地铁优先，默认为0
	 * @param tacticsIntercity
	 *            跨城公交换乘策略.0:时间短；1 出发早；2 价格低,默认为0
	 * @param transTypeIntercity
	 *            跨城交通方式策略 0:火车优先；1:飞机优先；2:大巴优先；默认为0
	 * @param pageSize
	 *            返回每页几条路线, 默认为10
	 * @param pageIndex
	 *            返回第几页 默认为1
	 * @param callback
	 *            回调函数，用于解决浏览器请求跨域问题
	 */
	public static void transit(String ak, double startLat, double startLng, double endLat,
			double endLng, Integer tacticsIncity, Integer tacticsIntercity,
			Integer transTypeIntercity, Integer pageSize, Integer pageIndex, String callback) {
		Map<String, Object> paramsMap = Maps.newHashMap();
		paramsMap.put("ak", ak);
		paramsMap.put("origin", startLat + "," + startLng);
		paramsMap.put("destination", endLat + "," + endLng);
		if (!QvoConditionUtil.checkInteger(tacticsIncity)) {
			tacticsIncity = 0;
		}
		paramsMap.put("tactics_incity", tacticsIncity);
		if (!QvoConditionUtil.checkInteger(tacticsIntercity)) {
			tacticsIntercity = 0;
		}
		paramsMap.put("tactics_intercity", tacticsIntercity);
		if (!QvoConditionUtil.checkInteger(transTypeIntercity)) {
			transTypeIntercity = 0;
		}
		paramsMap.put("trans_type_intercity", transTypeIntercity);
		paramsMap.put("output", "json");
		if (!QvoConditionUtil.checkInteger(pageSize)) {
			pageSize = 10;
		}
		paramsMap.put("page_size", pageSize);
		if (!QvoConditionUtil.checkInteger(pageIndex)) {
			pageIndex = 1;
		}
		paramsMap.put("page_index", pageIndex);
		if (StringUtils.isBlank(callback)) {
			callback = "";
		}
		paramsMap.put("callback", callback);
		String url = basePath + "transit?" + UrlHelper.formatParameters(paramsMap);
		System.out.println(url);
	}

	/**
	 * 
	 * @方法:骑行路线规划
	 * @创建人:独泪了无痕
	 * @param ak
	 *            开发者密钥
	 * @param startLat
	 *            起点纬度
	 * @param startLng
	 *            起点
	 * @param endLat
	 *            终点纬度
	 * @param endLng
	 *            终点
	 * @param ridingType
	 *            骑行类型 默认0：0-普通 1-电动车
	 * @param callback
	 */
	public static void riding(String ak, double startLat, double startLng, double endLat,
			double endLng, Integer ridingType, String callback) {
		Map<String, Object> paramsMap = Maps.newHashMap();
		paramsMap.put("ak", ak);
		paramsMap.put("origin", startLat + "," + startLng);
		paramsMap.put("destination", endLat + "," + endLng);
		if (!QvoConditionUtil.checkInteger(ridingType)) {
			ridingType = 0;
		}
		paramsMap.put("riding_type", ridingType);

		paramsMap.put("output", "json");

		if (StringUtils.isBlank(callback)) {
			callback = "";
		}
		paramsMap.put("callback", callback);
		String url = basePath + "riding?" + UrlHelper.formatParameters(paramsMap);
		System.out.println(url);
	}

	/**
	 * @方法:驾车路线规划
	 * @创建人:独泪了无痕
	 * @param ak
	 *            开发者密钥
	 * @param startLat
	 *            起点纬度
	 * @param startLng
	 *            起点
	 * @param endLat
	 *            终点纬度
	 * @param endLng
	 *            终点
	 * @param tactics
	 *            0：默认 3：不走高速 4：高速优先 5：躲避拥堵 6：少收费 7：躲避拥堵&高速优先 8：躲避拥堵&不走高速
	 *            9：躲避拥堵&少收费 10：躲避拥堵 &不走高速&少收费 11：不走高速&少收费
	 * @param alternatives
	 *            是否返回备选路线
	 * @param plateNumber
	 *            车牌号，如 京A00022,用于规避车牌号限行路段
	 * @param callback
	 *            回调函数
	 */
	public static void driving(String ak, double startLat, double startLng, double endLat,
			double endLng, Integer tactics, boolean alternatives, String plateNumber,
			String callback) {
		Map<String, Object> paramsMap = Maps.newHashMap();
		paramsMap.put("ak", ak);
		paramsMap.put("origin", startLat + "," + startLng);
		paramsMap.put("destination", endLat + "," + endLng);
		if (!QvoConditionUtil.checkInteger(tactics)) {
			tactics = 0;
		}
		paramsMap.put("riding_type", tactics);
		if (alternatives) {
			paramsMap.put("alternatives", 1);
		} else {
			paramsMap.put("alternatives", 0);
		}
		paramsMap.put("output", "json");
		if (StringUtils.isNotBlank(plateNumber)) {
			paramsMap.put("plate_number", plateNumber);
		}
		if (StringUtils.isBlank(callback)) {
			callback = "";
		}
		paramsMap.put("callback", callback);
		String url = basePath + "driving?" + UrlHelper.formatParameters(paramsMap);
		System.out.println(url);
	}
}
