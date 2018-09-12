package com.cdeledu.util.database.redis.redisServer;

import com.cdeledu.util.database.redis.model.RedisServerResponseModel;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: TODO(这里用一句话描述这个类的作用)
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2018年9月13日 上午12:06:41
 @版本: V0.0.1
 * @since: JDK 1.7
 */
public final class RedisServerHelp {
	/** ----------------------------------------------------- Fields start */

	/** ----------------------------------------------------- Fields end */
	/**
	 * @方法:获取服务器响应状态
	 * @创建人:独泪了无痕
	 * @param serverId
	 */
	public static RedisServerResponseModel getRedisServerResponse(String serverId) {
		RedisServerResponseModel result = new RedisServerResponseModel(serverId);
		try {
			result.setIfConnection(true);
			result.setStatus("连接成功");
			// result.ResponseTime;
		} catch (Exception ex) {
			result.setIfConnection(true);
			result.setStatus("连接失败，原因:{ex.Message}");
		}
		return result;
	}

	/**
	 * @方法:获取服务器信息
	 * @创建人:独泪了无痕
	 * @param serverId
	 */
	public static void getRedisInfo(String serverId) {
		try {

		} catch (Exception e) {
			// "GetRedisInfo方法异常", ex
		}
	}

	/**
	 * @方法:获取服务器原始内容信息
	 * @创建人:独泪了无痕
	 * @param serverId
	 */
	public static void getRedisInfoRow(String serverId) {
		try {

		} catch (Exception e) {
			// "请求失败,原因：{ex.Message}"
		}
	}

	/**
	 * @方法:获取客户端信息
	 * @创建人:独泪了无痕
	 * @param serverId
	 */
	public static void getClients(String serverId) {
		try {
			// ConvertClientInfoModel
		} catch (Exception e) {
			// "GetClients方法异常", ex
		}
	}

	private static String withInfoRaw(String infoResponse) {
		return infoResponse.replace("\r\n", "<br/>");
	}
}
