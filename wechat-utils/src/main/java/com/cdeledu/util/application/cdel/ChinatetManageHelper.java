package com.cdeledu.util.application.cdel;

import java.util.HashMap;

import org.apache.commons.lang3.StringUtils;

import com.cdeledu.util.network.tcp.HttpURLConnHelper;
import com.google.common.collect.Maps;

import net.sf.json.JSONObject;

/**
 * 
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 开放课堂常用操作
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2018年6月27日 上午12:30:25
 * @版本: V1.0
 * @since: JDK 1.7
 */
public final class ChinatetManageHelper {
	/** ----------------------------------------------------- Fields start */
	private static HttpURLConnHelper urlConnHelper;

	/** ----------------------------------------------------- Fields end */
	static {
		urlConnHelper = HttpURLConnHelper.getInstance();
	}

	/**
	 * @方法描述 : 正常退课-结算前退课操作
	 * @param userId
	 *            用户ID
	 * @param tfMoney
	 *            退课金额
	 * @param wareId
	 *            课程ID
	 * @param cancelReason
	 *            退课原因
	 * @param pKey
	 *            密钥
	 * @return
	 * @throws Exception
	 */
	public static boolean cancelCourse(int userId, Double tfMoney, int wareId, String cancelReason,
			String personKey) throws Exception {
		boolean result = false;
		if (StringUtils.isBlank(cancelReason)) {
			cancelReason = "正常退课-结算前退课操作";
		}
		String cancelCourseUrl = "";
		String params = "";
		String responseResult = urlConnHelper.sendPostRequest(cancelCourseUrl, params);
		JSONObject cancelResult = JSONObject.fromObject(responseResult);
		if (cancelResult.has("code") && ("200".equals(cancelResult.get("code").toString()))) {
			result = true;
		}
		return result;
	}

	/**
	 * @方法描述 : 关课-特殊退课-结算后退课操作
	 * @param detailID
	 *            详单ID
	 * @param orderID
	 *            订单ID
	 * @param wareID
	 *            课程ID
	 * @param userID
	 *            用户ID
	 * @param cancelReason
	 *            退课原因
	 * @param opUserName
	 *            操作者
	 * @param tfMoney
	 *            退课金额
	 * @param cancelWayType
	 *            退课类型：2：退全额, 1:退指定金额
	 * @return
	 */
	public static boolean cancelCourseAfterConfirmed(int detailID, int orderID, int wareID,
			int userID, String cancelReason, String opUserName, String tfMoney, int cancelWayType)
					throws Exception {
		if (StringUtils.isBlank(cancelReason)) {
			cancelReason = "特殊退课-结算后退课-线下退款";
		}
		return false;
	}

	/**
	 * @方法描述 : 同步上传视频的转换状态
	 */
	public static boolean synchronizationVideoConvertState() throws Exception {
		boolean result = false;
		String VideoConvertUrl = "";
		String responseResult;
		try {
			responseResult = urlConnHelper.sendPostRequest(VideoConvertUrl);
			JSONObject cancelResult = JSONObject.fromObject(responseResult);
			if (cancelResult.has("msg") && ("ok".equalsIgnoreCase(cancelResult.getString("msg")))) {
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * @方法:鉴权认证接口接
	 * @创建人:独泪了无痕
	 * @param userID
	 * @param cwareID
	 * @param videoID
	 * @return
	 * @throws Exception
	 */
	public static boolean videoCheck(String userID, String cwareID, String videoID)
			throws Exception {
		boolean result = false;
		String url = "";
		HashMap<String, Object> paramsMap = Maps.newHashMap();
		String responseResult = "";
		try {
			responseResult = urlConnHelper.sendPostRequest(url, paramsMap).trim();
			if (StringUtils.isNotBlank(responseResult)) {
				JSONObject _result = JSONObject.fromObject(responseResult);
				if (_result.has("ret") && "1".equals(_result.getString("ret"))) {
					result = true;
				}
			}
		} catch (Exception videoCheckExp) {
			videoCheckExp.printStackTrace();
		}
		return result;
	}
}