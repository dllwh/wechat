package com.cdeledu.util.webpage;

import java.util.Map;

/**
 * @类描述: Web相关的工具类
 * @创建者: 皇族灬战狼
 * @创建时间: 2017年2月14日 下午10:43:26
 * @版本: V1.0
 * @since: JDK 1.7
 */
public interface WebHelper {
	/** ----------------------------------------------------- Fields start */
	/** ----------------------------------------------------- Fields end */

	/** ----------------------------------------------- [私有方法] */
	/** ----------------------------------------------- [私有方法] */
	public Map<String, Object> getParam(String url);

	public String getParamValue(String url, String paramName);

	public String removeParam(String url, String paramName);

	public String removeParam(String url, String... paramNames);

	public String setParam(String url, String paramNames, String paramValue);
}
