package com.cdeledu.crawler.transport.traffic;

import java.util.List;

import com.cdeledu.common.constant.ConstantHelper;
import com.cdeledu.util.application.regex.RegexUtil;
import com.cdeledu.util.network.tcp.HttpURLConnHelper;
import com.google.common.collect.Lists;

public class TrainHelper {
	/** ----------------------------------------------------- Fields start */
	// 编码格式
	private final static String CHARSER = ConstantHelper.UTF_8.name();
	private static HttpURLConnHelper conn = null;
	static {
		conn = HttpURLConnHelper.getInstance(CHARSER);
	}

	/** ----------------------------------------------------- Fields end */

	/** ----------------------------------------------- [私有方法] */
	/** ----------------------------------------------- [私有方法] */
	/**
	 * @方法描述: 城市数据来源
	 * @return
	 * @throws Exception
	 */
	public List<String> getStationInfo() throws Exception {
		/** 列车站点列表 */
		String url = "https://kyfw.12306.cn/otn/resources/js/framework/station_name.js?station_version=1.8968";
		String result = conn.sendGetRequest(url);
		List<String> resultList = Lists.newArrayList();
		/** 简称|车站名字|车站代码|城市拼音|简称|排序 */
		// String regex =
		// "(@([a-z]+))\\|(([\u4e00-\u9fa5\uF900-\uFA2D\u0020])+)\\|([A-Z]+)\\|([a-z]+)\\|([a-z]+)\\|(\\d*)";
		String regex = "(([\u4e00-\u9fa5\uF900-\uFA2D\u0020])+)\\|([A-Z]+)\\|([a-z]+)\\|([a-z]+)\\|(\\d*)";
		RegexUtil.getList(regex, result, 0, resultList);
		return resultList;
	}
}
