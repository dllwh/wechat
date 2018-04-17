package com.cdeledu.util.openplatform.baidu.api;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cdeledu.common.constant.ConstantHelper;
import com.cdeledu.common.network.UrlHelper;
import com.cdeledu.util.appConfig.ConfigUtil;
import com.cdeledu.util.network.tcp.HttpURLConnHelper;
import com.cdeledu.util.openplatform.baidu.api.entity.OneDayWeatherInfo;
import com.cdeledu.util.openplatform.baidu.api.entity.WeatherInfo;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @描述: 百度天气工具类(调用车联网API之天气查询)
 * @author: 独泪了无痕
 * @date: 2015年7月17日 下午1:38:58
 * @version: V1.0
 * @since: JDK 1.7
 * @see <a href= "developer.baidu.com/map/index.php?title=car/api/weather">天气查询
 *      </a>
 */
public class BaiduWeatherHelper {
	/*-------------------------- 私有属性 begin -------------------------------*/
	private final static String WEATHER = "http://api.map.baidu.com/telematics/v3/weather";

	// 编码格式
	private final static String CHARSER = ConstantHelper.UTF_8.name();
	private static HttpURLConnHelper conn = null;

	static {
		conn = HttpURLConnHelper.getInstance(CHARSER);
	}
	/*-------------------------- 私有属性 end   -------------------------------*/

	/*-------------------------- 私有方法 begin -------------------------------*/
	/*-------------------------- 私有方法 end   -------------------------------*/
	/*-------------------------- 公有方法 begin -------------------------------*/
	@SuppressWarnings("deprecation")
	public static WeatherInfo getWeatherInfo(String cityName) throws Exception {
		Map<String, Object> paramsMap = new HashMap<String, Object>();

		// 保存全部的天气信息
		WeatherInfo weatherInfo = new WeatherInfo();

		try {
			paramsMap.put("ak", ConfigUtil.getBaiduWeatherAkValue());
			paramsMap.put("location", URLEncoder.encode(cityName, CHARSER));
			paramsMap.put("output", "json");

			String url = UrlHelper.formatParameters(WEATHER, paramsMap);
			String result = conn.sendGetRequest(url);

			JSONObject _res0 = JSONObject.fromObject(result);
			if (_res0.getInt("error") != 0) {
				return null;
			}
			/**
			 * 当前时间 (年-月-日 )
			 */
			String date = _res0.getString("date");
			int year = Integer.parseInt(date.substring(0, 4));
			int month = Integer.parseInt(date.substring(5, 7));
			int day = Integer.parseInt(date.substring(8, 10));
			Date today = new Date(year - 1900, month - 1, day);
			weatherInfo.setDate(date);

			JSONArray results = _res0.getJSONArray("results");
			JSONObject results0 = results.getJSONObject(0);

			/**
			 * 当前城市
			 */
			weatherInfo.setCurrentCity(results0.getString("currentCity"));

			/**
			 * pm25指数
			 */
			int pmTwoPointFive;
			if (results0.getString("pm25").isEmpty()) {
				pmTwoPointFive = 0;
			} else {
				pmTwoPointFive = results0.getInt("pm25");
			}
			weatherInfo.setPm(pmTwoPointFive);

			try {
				JSONArray index = results0.getJSONArray("index");
				/**
				 * 穿衣
				 */
				JSONObject index0 = index.getJSONObject(0);
				weatherInfo.setDressAdvise(index0.getString("title"));
				/**
				 * 洗车
				 */
				JSONObject index1 = index.getJSONObject(1);
				weatherInfo.setWashCarAdvise(index1.getString("des"));
				/**
				 * 感冒
				 */
				JSONObject index2 = index.getJSONObject(2);
				weatherInfo.setColdAdvise(index2.getString("des"));
				/**
				 * 运动
				 */
				JSONObject index3 = index.getJSONObject(3);
				weatherInfo.setSportsAdvise(index3.getString("des"));
				/**
				 * 紫外线强度
				 */
				JSONObject index4 = index.getJSONObject(4);
				weatherInfo.setUltravioletRaysAdvise(index4.getString("des"));
			} catch (Exception e) {
				weatherInfo.setDressAdvise("要温度，也要风度。天热缓减衣，天凉及添衣！");
				weatherInfo.setWashCarAdvise("你洗还是不洗，灰尘都在哪里，不增不减。");
				weatherInfo.setColdAdvise("一天一个苹果，感冒不来找我！多吃水果和蔬菜。");
				weatherInfo.setSportsAdvise("生命在于运动！不要总宅在家里哦！");
				weatherInfo.setUltravioletRaysAdvise("心灵可以永远年轻，皮肤也一样可以！");
			}

			OneDayWeatherInfo odwi = null;

			List<OneDayWeatherInfo> odwiList = new ArrayList<OneDayWeatherInfo>();

			JSONArray weather_data = results0.getJSONArray("weather_data");

			for (int i = 0; i < weather_data.size(); i++) {
				odwi = new OneDayWeatherInfo();
				JSONObject odwinfo = weather_data.getJSONObject(i);
				/**
				 * 天气预报时间
				 */
				String dayData = odwinfo.getString("date");
				odwi.setDate((today.getYear() + 1900) + "." + (today.getMonth() + 1) + "."
						+ today.getDate());
				today.setDate(today.getDate() + 1);// 增加一天

				/** 第一个，也就是当天的天气，在date字段中最后包含了实时天气 */
				if (i == 0) {
					int begin = dayData.indexOf(":");
					int end = dayData.indexOf(")");
					if (begin > -1) {
						// 实时天气
						odwi.setTempertureNow(dayData.substring(begin + 1, end));
						// 星期
						odwi.setWeek(dayData.substring(0, 2));
					} else {
						odwi.setTempertureNow("");
						odwi.setWeek(dayData.substring(0, 2));
					}
				} else {
					odwi.setWeek(dayData.substring(0, 2));
				}

				/**
				 * 白天的天气预报图片url
				 */
				odwi.setDayPictureUrl(odwinfo.getString("dayPictureUrl"));

				/**
				 * 晚上的天气预报图片url
				 */
				odwi.setNightPictureUrl(odwinfo.getString("nightPictureUrl"));

				/**
				 * 天气状况
				 */
				odwi.setWeather(odwinfo.getString("weather"));

				/**
				 * 风力
				 */
				odwi.setWind(odwinfo.getString("wind"));

				/**
				 * 温度范围
				 */
				odwi.setTemperature(odwinfo.getString("temperature"));

				odwiList.add(odwi);
			}

			weatherInfo.setWeather_data(odwiList);

			return weatherInfo;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void main(String[] args) {
		// System.out.println(getWeatherInfo("北京"));
		// System.out.println(URLDecoder.decode("%e5%98%89%e5%85%b4"));
	}

	/*-------------------------- 公有方法 end   -------------------------------*/
}
