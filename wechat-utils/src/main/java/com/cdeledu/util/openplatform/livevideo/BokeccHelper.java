package com.cdeledu.util.openplatform.livevideo;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.cdeledu.common.constant.ConstantHelper;
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
	/** 观看基本地址 */
	private static final String API_BASE_URL = "http://api.csslcloud.net/api/";
	/** 观看基本地址 */
	private static final String VIEW_BASE_URL = "https://view.csslcloud.net/api/view/";
	/** 观众端直播自动登陆 */
	private static String visitorAutoLogin = VIEW_BASE_URL
			+ "index?roomid=%s&userid=%s&autoLogin=true&viewername=%s&viewertoken=%s";
	/** 观众端回放自动登陆 */
	private static String recordAutoLogin = VIEW_BASE_URL
			+ "callback/login?recordid=%s&roomid=%s&userid=%s&autoLogin=true&viewername=%s&viewertoken=%s";
	/** 助教(管理员)直播自动登陆 */
	private static String assistantAutoLogin = VIEW_BASE_URL
			+ "assistant/login?roomid=%s&userid=%s&viewername=%s&viewertoken=%s";
	/** 观众端直播自动登陆 */
	private static String lecturerAutoLogin = VIEW_BASE_URL
			+ "lecturer?roomid=%s&userid=%s&publishname=%s&publishpassword=%s";

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
	 * @方法描述 : 直播间自动登录方式
	 * @param visitorType
	 *            用户类型：1:讲师;2:助教;3:游客(观众或学员);默认是3
	 * @param roomId
	 *            直播间id
	 * @param userId
	 *            CC直播平台的账号ID
	 * @param viewerName
	 *            登陆用户名（自定义即可）
	 * @param viewerToken
	 *            登录检验码
	 * @return 如果roomId 或者 userId 为空，返回结果 空
	 */
	public static String getAutoLogin(int visitorType, String roomId, String userId,
			String viewerName, String viewerToken) {
		String result = "";

		if (StringUtils.isBlank(viewerToken)) {
			viewerToken = "";
		}

		if (StringUtils.isBlank(userId) || StringUtils.isBlank(roomId)) {
			return "";
		}
		if (visitorType == 1) {// 讲师端自动登陆
			if (StringUtils.isNotBlank(viewerName) && StringUtils.isNotBlank(viewerToken)) {
				result = String.format(lecturerAutoLogin, roomId, userId, viewerName, viewerToken);
			}

		} else if (visitorType == 2) { // 助教端自动登陆
			if (StringUtils.isNotBlank(viewerName) && StringUtils.isNotBlank(viewerToken)) {
				result = String.format(assistantAutoLogin, roomId, userId, viewerName, viewerToken);
			}
		} else if (visitorType == 3) { // 观众端自动登陆
			result = String.format(visitorAutoLogin, roomId, userId, viewerName, viewerToken);
		} else {
			result = String.format(visitorAutoLogin, roomId, userId, viewerName, viewerToken);
		}
		return result;
	}

	/** ----------------------------------------------- [私有方法] */
	/**
	 * @方法描述 : 每个键值对按照键的字母顺序升序排序,并且 value 值都需要以 UTF-8 格式进行 URL Encode
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
			try {
				sortedMap.put(tmpEntry.getKey(),
						URLEncoder.encode(tmpEntry.getValue(), ConstantHelper.UTF_8.name()));
			} catch (Exception e) {
				sortedMap.put(tmpEntry.getKey(), tmpEntry.getValue());
			}

		}
		return sortedMap;
	}

	/** ----------------------------------------------- [私有方法] */
}
