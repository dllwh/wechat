package com.cdeledu.util.openplatform.douyutv;

import org.apache.commons.lang3.StringUtils;

import com.cdeledu.util.security.Md5Helper;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 斗鱼TV Web API
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2018年8月19日 下午3:26:12
 * @版本: V0.1.1
 * @since: JDK 1.7
 * @see <a href="430.io/-xie-dou-yu-tv-web-api-some-douyutv-api/">转载自</a>
 */
public final class DouyoTVWebAPIHelper {
	/** ----------------------------------------------------- Fields start */

	/** ----------------------------------------------------- Fields end */

	/**
	 * @方法:获取当前全部直播
	 * @创建人:独泪了无痕
	 * @param limit
	 *            一次获取的个数
	 * @param offset
	 *            起始位置，比如要获取前20个之后的 offset=20
	 */
	public static String getAllLive(int limit, int offset) {
		String url = "http://capi.douyucdn.cn/api/v1/live?limit=%s&offset=%s";
		return String.format(url, limit, offset);
	}

	/**
	 * @方法:获取父频道
	 * @创建人:独泪了无痕
	 */
	public static String getColumnList() {
		String url = "http://capi.douyucdn.cn/api/v1/getColumnList";
		return url;
	}

	/**
	 * @方法:获取子频道
	 * @创建人:独泪了无痕
	 * @param gameName
	 *            父频道简称名字
	 */
	public static String getColumnDetail(String gameName) {
		String url = "http://capi.douyucdn.cn/api/v1/getColumnDetail?shortName=%s";
		return String.format(url, gameName);
	}

	/**
	 * @方法:获取父频道所有直播列表
	 * @创建人:独泪了无痕
	 * @param cate_id
	 *            父频道id
	 * @param limit
	 * @param offset
	 */
	public static String getColumnRoom(String cate_id, int limit, int offset) {
		String url = "http://capi.douyucdn.cn/api/v1/getColumnRoom/%s?limit=%s&offset=%s";
		return String.format(url, cate_id, limit, offset);
	}

	/**
	 * @方法:获取子频道直播列表
	 * @创建人:独泪了无痕
	 * @param tag_id
	 *            子频道id
	 * @param limit
	 * @param offset
	 */
	public static String getAllLive(String tag_id, int limit, int offset) {
		String url = "http://capi.douyucdn.cn/api/v1/live/%s?limit=%s&offset=%s";
		return String.format(url, tag_id, limit, offset);
	}

	/**
	 * @方法:获取房间信息
	 * @创建人:独泪了无痕
	 * @param roomid
	 *            房间ID
	 */
	public static String webroom(int roomid) {
		String url = "http://capi.douyucdn.cn/api/v1/room/%s/?aid=android&client_sys=android&time=%s&auth=%s";
		long time = System.currentTimeMillis() / 1000;
		String plainText = "room/" + roomid + "?aid=android&clientsys=android&time=" + time
				+ "1231";
		String auth = Md5Helper.md5(plainText, 32);
		return String.format(url, roomid, time, auth);
	}

	/**
	 * @方法:获取登陆token
	 * @创建人:独泪了无痕
	 * @param username
	 *            用户名
	 * @param password
	 *            密码
	 */
	public static String getToken(String userName, String passWord) {
		String url = "http://capi.douyucdn.cn/api/v1/login?username=%s&password=%s";
		if (StringUtils.isNotBlank(passWord)) {
			return String.format(url, userName, Md5Helper.md5(passWord, 32));
		} else {
			return String.format(url, userName, passWord, 32);
		}
	}

	/**
	 * @方法:获取个人信息
	 * @创建人:独泪了无痕
	 * @param token
	 */
	public static String getUserInfo(String token) {
		String url = "http://capi.douyucdn.cn/api/v1/my_info?token=%s";
		return String.format(url, token);
	}

	/**
	 * @方法:获取关注列表
	 * @创建人:独泪了无痕
	 * @param token
	 *            token
	 * @param limit
	 * @param offset
	 */
	public static String getRemindList(String token, int limit, int offset) {
		String url = "http://capi.douyucdn.cn/api/v1/remind_list?token=%s&limit=%s&offset=%s";
		return String.format(url, token, limit, offset);
	}

	/**
	 * @方法:获取关注列表
	 * @创建人:独泪了无痕
	 * @param token
	 *            token
	 * @param live
	 *            是否在直播(1是正在直播的，0是没开直播的)
	 * @param limit
	 * @param offset
	 */
	public static String getRemindList(String token, boolean live) {
		String url = "http://capi.douyucdn.cn/api/v1/followRoom?token=%s&live=%s";
		if (live) {
			return String.format(url, token, 1);
		} else {
			return String.format(url, token, 0);
		}
	}

	/**
	 * @方法:取消关注
	 * @创建人:独泪了无痕
	 * @param ids
	 *            要取消关注的列表
	 */
	@Deprecated
	public static void cancelRemindList(String... ids) {
		String url = "http://capi.douyucdn.cn/api/v1/follow/del";
	}

	/**
	 * @方法:获取观看历史
	 * @创建人:独泪了无痕
	 * @param token
	 */
	public static String getHistory(String token) {
		String url = "http://capi.douyucdn.cn/api/v1/history?token=%s";
		return String.format(url, token);
	}

	public static void main(String[] args) {
		// System.out.println(webroom(99999));
		System.out.println(getToken("dllwh", "dllwh"));
	}
}
