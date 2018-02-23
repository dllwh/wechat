package com.cdeledu.crawler.lifeServices;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;

import com.cdeledu.util.application.regex.RegexUtil;
import com.cdeledu.util.network.tcp.HttpURLConnHelper;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * @类描述: 爬取花瓣网图片数据
 * @创建者: 皇族灬战狼
 * @创建时间: 2017年6月3日 下午4:45:31
 * @版本: V2.0
 * @since: JDK 1.7
 */
public class HuabanHelper {
	/** ----------------------------------------------------- Fields start */
	private final static String BASE_URL = "http://huaban.com/";
	private final static String BASE_IMG_URL = "http://img.hb.aicdn.com/";
	private final static String BEAUTY_IMG_URL = BASE_URL + "%s/?max=%s&limit=%s&wfl=1";
	private final static String REGEX_SETTINGS = "\\{\"id\":(.*?), \"name\":(.*?), \"nav_link\":(.*?)}";
	private final static String REGEX_IMG = "\\{\"pin_id\":(\\d+),.+?\"board_id\":(\\d+),.+?\"key\":\"(.*?)\",.\"type\":\"image/(.*?)\",";
	private final static String source = "huaban.com/";

	/** ----------------------------------------------------- Fields end */

	/** ----------------------------------------------- [私有方法] */
	/**
	 * @方法描述: 类别信息
	 * @return code 编码
	 * @return name 名称
	 * @return nav_link 链接
	 * @throws Exception
	 */
	public static List<Map<String, Object>> getCategoryInfo() throws Exception {
		String result = HttpURLConnHelper.getInstance().sendGetRequest(BASE_URL);
		List<Map<String, Object>> resultList = Lists.newArrayList();
		Map<String, Object> catMap = null;
		JSONObject jsRes = null;
		List<String> tempList = Lists.newArrayList();
		RegexUtil.getList(REGEX_SETTINGS, result, 0, tempList);
		for (String categories : tempList) {
			catMap = Maps.newConcurrentMap();
			jsRes = new JSONObject(categories);
			catMap.put("code", jsRes.getString("id"));
			catMap.put("name", jsRes.getString("name"));
			catMap.put("url", jsRes.getString("nav_link"));
			resultList.add(catMap);
		}
		return resultList;
	}

	/**
	 * @方法描述: 获取图片信息
	 * @param code
	 * @param pid
	 * @param isSave
	 *            是否保存
	 * @return
	 * @throws Exception
	 */
	public static List<Map<String, Object>> getImageInfoByCode(String code, String pid,
			Integer limit, boolean isSave) throws Exception {
		return analyze(getRealPath("/favorite/" + code, pid, limit), isSave);
	}

	/**
	 * @方法描述: 获取图片信息
	 * @param category
	 *            类别
	 * @param pid
	 * @param limit
	 *            个数
	 * @param isSave
	 *            是否保存
	 * @return
	 * @throws Exception
	 */
	public static List<Map<String, Object>> getImageInfoByCategpry(String category, String pid,
			Integer limit, boolean isSave) throws Exception {
		return analyze(getRealPath(category, pid, limit), isSave);
	}

	/**
	 * @方法描述: 从人物面板获取图片数据
	 * @param bid
	 *            人物面板ID
	 * @param pid
	 *            人物ID
	 * @param limit
	 *            个数
	 * @param isSave
	 *            是否保存
	 * @return
	 * @throws Exception
	 */
	public static List<Map<String, Object>> getImageInfoByBoard(String bid, String pid,
			Integer limit, boolean isSave) throws Exception {
		return analyze(getRealPath("/boards/" + bid, pid, limit), isSave);
	}

	/** ----------------------------------------------- [私有方法] */
	private static String getRealPath(String category, String pid, Integer limit) {
		if (StringUtils.isNoneBlank(category)) {
			if (limit < 1 || limit > 100) {
				limit = 20;
			}
			while (category.startsWith("/")) {
				category = category.substring(category.indexOf("/") + 1);
			}

			while (category.endsWith("/")) {
				category = category.substring(0, category.lastIndexOf("/"));
			}
			return String.format(BEAUTY_IMG_URL, category, pid, limit);
		}
		return "";
	}

	/**
	 * @方法描述: 解析数据
	 * @param url
	 * @return
	 * @throws Exception
	 */
	private static List<Map<String, Object>> analyze(String url, boolean isSave) throws Exception {
		List<Map<String, Object>> resultList = Lists.newArrayList();
		// 创建 Pattern 对象
		Pattern r = Pattern.compile(REGEX_IMG);
		Matcher m = r.matcher(HttpURLConnHelper.getInstance().sendGetRequest(url));
		Map<String, Object> beautyMap = null;
		String pinId, boardId, imgPath, imgType = "";
		while (m.find()) {
			imgPath = BASE_IMG_URL + m.group(3);
			if (!isExist(imgPath)) {
				beautyMap = Maps.newConcurrentMap();
				pinId = m.group(1);
				boardId = m.group(2);
				imgType = m.group(4);
				beautyMap.put("pinId", pinId);// 用户ID
				beautyMap.put("boardId", boardId);// 画板ID
				/** 图片地址后面带有类型_fw554、fw658,无实际意义，猜测是避免缓存 */
				beautyMap.put("imgPath", imgPath);// 图片地址
				beautyMap.put("imgType", imgType); // 图片类型
				beautyMap.put("source", source);
				resultList.add(beautyMap);
			}
		}
		return resultList;
	}

	/**
	 * @方法描述: 查询是否已经该条数据
	 * @param val
	 * @return
	 */
	private static boolean isExist(String val) {
		return false;
	}

	/** ----------------------------------------------- [私有方法] */
}