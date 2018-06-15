package com.cdeledu.util.appConfig;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.cdeledu.util.apache.lang.RandomUtil;

/**
 * @描述: 项目参数工具类
 * @author: 独泪了无痕
 * @date: 2015年8月17日 上午11:26:57
 * @version: V1.0
 * @history:
 */
public class ConfigUtil {
	/** --------------------------私有属性 start------------------------------- */
	// 项目参数
	private static final ResourceBundle CONFIG = ResourceBundle.getBundle("properties/config");

	/** --------------------------私有属性 end------------------------------- */

	/** --------------------------私有方法 start------------------------------- */
	/** 获取配置文件参数 */
	protected static final String getConfigByName(ResourceBundle resource, String name) {
		String url = resource.getString(name);
		Pattern p = Pattern.compile("(\\{[^\\}]*\\})");
		Matcher m = p.matcher(url);
		StringBuffer sb = new StringBuffer();
		String sub = null;
		while (m.find()) {
			sub = m.group();
			m.appendReplacement(sb, getConfigByName(resource, sub.substring(1, sub.length() - 1)));
		}
		m.appendTail(sb);
		return sb.toString();
	}

	/**
	 * @Title: getConfigListByName
	 * @Description: 当获取的值是多个,此方法读取并将放置于List<String>中
	 * @author: 独泪了无痕
	 * @param resource
	 * @param name
	 * @param split
	 * @return
	 */
	private static List<String> getConfigListByName(ResourceBundle resource, String name,
			String split) {
		return Arrays.asList(getConfigByName(resource, name).split(split));
	}

	/** --------------------------私有方法 end------------------------------- */
	/** --------------------------公有方法 start------------------------------- */

	/**
	 * @Title: getSessionInfoName
	 * @Description: 获得sessionInfo名字
	 * @author: 独泪了无痕
	 * @return
	 */
	public static final String getSessionInfoName(String sessionName) {
		return getConfigByName(CONFIG, sessionName);
	}

	/**
	 * @Title: getUploadFieldName
	 * @Description: 获得上传表单域的名称
	 * @author: 独泪了无痕
	 * @return
	 */
	public static final String getUploadFieldName() {
		return getConfigByName(CONFIG, "uploadFieldName");
	}

	/**
	 * @Title: getUploadFileMaxSize
	 * @Description: 获得上传文件的最大大小限制
	 * @author: 独泪了无痕
	 * @return
	 */
	public static final long getUploadFileMaxSize() {
		return Long.valueOf(getConfigByName(CONFIG, "uploadFileMaxSize"));
	}

	/**
	 * @Title: getUploadFileExts
	 * @Description:获得允许上传文件的扩展名
	 * @author: 独泪了无痕
	 * @return
	 */
	public static final String getUploadFileExts() {
		return getConfigByName(CONFIG, "uploadFileExts");
	}

	/**
	 * @Title: getUploadDirectory
	 * @Description: 获得上传文件的存储目录
	 * @author: 独泪了无痕
	 * @return
	 */
	public static final String getUploadDirectory() {
		return getConfigByName(CONFIG, "uploadDirectory");
	}

	/**
	 * @方法描述: 百度地图接口密钥
	 * @说明 其限制根据客户端的IP进行限制的,也就是说不管你创建多少个KEY,只要你从一个IP发出的请求,那么超过限制次数之后都会调用失败
	 * @创建者: 皇族灬战狼
	 * @更新时间: 2016年10月14日 下午6:44:23
	 * @return
	 */
	public static final String getBaiduMapAkValue() {
		return getConfigByName(CONFIG, "baidu_map_apikey");
	}

	/**
	 * @Title: getBaiduTranslateAkValue
	 * @Description: 百度翻译接口密钥
	 * @author: 独泪了无痕
	 * @return
	 */
	public static final Map<String, String> getBaiduTrans() {
		List<String> resultList = Arrays
				.asList(getConfigByName(CONFIG, "baidu_trans_appId").split("-"));
		Map<String, String> resultMap = new HashMap<String, String>();
		resultMap.put("appid", resultList.get(0));
		resultMap.put("secretKey", resultList.get(1));
		return resultMap;

	}

	/**
	 * @Title: getBaiduWeatherAkValue
	 * @Description: 百度天气接口密钥
	 * @author: 独泪了无痕
	 * @return
	 */
	public static final String getBaiduWeatherAkValue() {
		List<String> resultList = getConfigListByName(CONFIG, "baidu_Weather_apikey", ",");
		return RandomUtil.randomEle(resultList);
	}

	/**
	 * @Title: getApiStoreAkValue
	 * @Description: APIStore 接口密钥
	 * @author: 独泪了无痕
	 * @return
	 */
	public static final String getApiStoreAkValue() {
		List<String> resultList = getConfigListByName(CONFIG, "baidu_apiStore_apikey", ",");
		return RandomUtil.randomEle(resultList);
	}

	/**
	 * @Description: 聚合数据 接口密钥
	 * @author: 独泪了无痕
	 * @return
	 */
	public static final String getJuHeAkValue() {
		return getConfigByName(CONFIG, "juhe_apikey");
	}
	/*--------------------------公有方法 end-------------------------------*/
}
