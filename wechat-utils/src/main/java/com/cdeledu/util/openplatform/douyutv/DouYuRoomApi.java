package com.cdeledu.util.openplatform.douyutv;

import org.apache.commons.lang3.StringUtils;

import com.cdeledu.util.network.tcp.HttpURLConnHelper;

public final class DouYuRoomApi {
	/** ----------------------------------------------------- Fields start */

	private static HttpURLConnHelper urlConnHelper;
	/** ----------------------------------------------------- Fields end */

	static {
		urlConnHelper = HttpURLConnHelper.getInstance();
	}

	/**
	 * @方法描述 : 获取直播房间列表信息
	 * @param classId
	 *            分类 ID
	 * @param className
	 *            分类别名
	 * @param offset
	 *            翻页偏移量,默认值是0
	 * @param limit
	 *            每次获取数量(limit<1 或者 limit>100 时，limit =30)
	 * @throws Exception
	 */
	public static void getRoomList(Integer classId, String className, Integer offset, Integer limit)
			throws Exception {
		String url = "http://open.douyucdn.cn/api/RoomApi/live";
		if (classId != null) { // 根据分类 ID获取房间列表
			url = url + "/" + classId;
		} else if (StringUtils.isNotBlank(className)) {// 根据分类简称获取房间列表
			url = url + "/" + className;
		} else {// 后面不跟任何参数,则获取所有的直播房间列表

		}
		String requestUrl = String.format(url + "?=offset%s&limit=%s", offset, limit);
		System.out.println(urlConnHelper.sendGetRequest(requestUrl));
	}

	/**
	 * @方法描述 : 获取所有游戏分类
	 * @throws Exception
	 */
	public static void getGameType() throws Exception {
		urlConnHelper.sendGetRequest("http://open.douyucdn.cn/api/RoomApi/game");
	}

	/**
	 * @方法描述 : 获取直播房间详情信息
	 * @param roomId
	 *            房间 Id
	 * @throws Exception
	 */
	public static void getLiveRoom(Integer roomId) throws Exception {
		urlConnHelper.sendGetRequest("http://open.douyucdn.cn/api/RoomApi/room/" + roomId);
	}
}
