package com.cdeledu.util.openplatform.baidu;

import java.util.List;

import com.cdeledu.util.openplatform.baidu.model.BaiduMusic;
import com.cdeledu.util.openplatform.baidu.model.WeatherInfo;

/**
 * @描述: 百度开放平台调用
 * @author: 独泪了无痕
 * @date: 2015年12月13日 上午11:38:56
 * @version: V1.0
 * @since: JDK 1.7
 */
public class BaiduHelperUtils {

	/**
	 * @Title: getMusicInfo
	 * @Description: 获取百度音乐信息
	 * @author: 独泪了无痕
	 * @param titile
	 *            音乐名称
	 * @param musicAuthor
	 *            作者
	 * @return
	 */
	public static BaiduMusic getMusicInfo(String titile, String musicAuthor) throws Exception {
		return BaiduMusicService.searchMusic(titile, musicAuthor);
	}

	/**
	 * @Title: getWeatherInfo
	 * @Description: 获取天气信息
	 * @author: 独泪了无痕
	 * @param cityName
	 *            城市名称
	 * @return
	 */
	public static WeatherInfo getWeatherInfo(String cityName) throws Exception {
		return BaiduWeather.getWeatherInfo(cityName);
	}

	/**
	 * @Title: getTransInfo
	 * @Description: 获取翻译结果
	 * @author: 独泪了无痕
	 * @param from
	 *            源语言语种
	 * @param to
	 *            目标语言语种
	 * @param source
	 *            待翻译内容
	 * @return
	 */
	public static List<String> getTransInfo(String from, String to, String source)
			throws Exception {
		return BaiduTranslate.translate(from, to, source);
	}
}
