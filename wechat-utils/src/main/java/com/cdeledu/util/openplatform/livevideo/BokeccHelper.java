package com.cdeledu.util.openplatform.livevideo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.cdeledu.util.security.SecureUtil;
import com.google.common.base.Joiner;

/**
 * 
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: CC 视频相关
 * @创建者: 皇族灬战狼
 * @联系方式: duleilewuhen@sina.com
 * @创建时间: 2018年3月19日 下午7:15:27
 * @版本: V1.0
 * @since: JDK 1.7
 * @see <a href="http://www.bokecc.com/">CC视频</a>
 */
public class BokeccHelper {
	/** ----------------------------------------------------- Fields start */
	/** ----------------------------------------------------- Fields end */
	/**
	 * @方法描述 : HTTP通信加密算法
	 * @param queryMap
	 * @param time
	 *            当前时间的 Unix 时间戳,秒数
	 * @param salt
	 *            直播平台帐号对应的 API Key 值
	 * @return
	 */
	public static String createHashedQueryString(Map<String, String> paramMap, String salt) {
		Map<String, String> queryMap = sortMapByKey(paramMap);
		String result = "", hash = "", qs = "";
		long time = System.currentTimeMillis() / 1000;
		try {
			qs = Joiner.on("&").withKeyValueSeparator("=").join(queryMap);
			hash = SecureUtil.encrypt(String.format("%s&time=%d&salt=%s", qs, time, salt), null,
					SecureUtil.MD5, null, 32).toUpperCase();
			result = String.format("%s&time=%d&hash=%s", qs, time, hash);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * @方法描述 : 每个键值对按照键的字母顺序升序排序
	 * @param paramMap
	 * @return
	 */
	private static Map<String, String> sortMapByKey(Map<String, String> paramMap) {
		List<Map.Entry<String, String>> entryList = new ArrayList<Map.Entry<String, String>>(
				paramMap.entrySet());

		// 排序方法
		Collections.sort(entryList, new Comparator<Map.Entry<String, String>>() {
			@Override
			public int compare(Map.Entry<String, String> o1, Map.Entry<String, String> o2) {
				return (o1.getKey()).toString().compareTo(o2.getKey());
			}
		});

		Map<String, String> sortedMap = new LinkedHashMap<String, String>();
		Iterator<Map.Entry<String, String>> iter = entryList.iterator();
		Map.Entry<String, String> tmpEntry = null;
		while (iter.hasNext()) {
			tmpEntry = iter.next();
			sortedMap.put(tmpEntry.getKey(), tmpEntry.getValue());
		}
		return sortedMap;
	}
}
