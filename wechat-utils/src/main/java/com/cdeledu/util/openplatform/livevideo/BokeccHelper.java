package com.cdeledu.util.openplatform.livevideo;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.cdeledu.common.constant.ConstantHelper;
import com.cdeledu.util.application.QvoConditionUtil;
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
	/** 基本地址 */
	private final static String API_BASE_URL = "http://api.csslcloud.net/api/";
	/** 观看基本地址 */
	private final static String VIEW_BASE_URL = "https://view.csslcloud.net/api/view/";
	/** 观众端直播自动登陆 */
	private String visitorAutoLogin = VIEW_BASE_URL
			+ "index?roomid=%s&userid=%s&autoLogin=true&viewername=%s&viewertoken=%s";
	/** 观众端回放自动登陆 */
	private String recordAutoLogin = VIEW_BASE_URL
			+ "callback/login?recordid=%s&roomid=%s&userid=%s&autoLogin=true&viewername=%s&viewertoken=%s";
	/** 助教(管理员)直播自动登陆 */
	private String assistantAutoLogin = VIEW_BASE_URL
			+ "assistant/login?roomid=%s&userid=%s&viewername=%s&viewertoken=%s";
	/** 观众端直播自动登陆 */
	private String lecturerAutoLogin = VIEW_BASE_URL
			+ "lecturer?roomid=%s&userid=%s&publishname=%s&publishpassword=%s";

	/** ----------------------------------------------------- Fields end */
	/** 直播平台帐号对应的 API Key 值 */
	private String appKey;
	/** 平台账号 */
	private String platAccount;

	public BokeccHelper(String appKey, String platAccount) {
		this.appKey = appKey;
		this.platAccount = platAccount;
	}

	/**
	 * @方法:创建直播间
	 * @创建人:独泪了无痕
	 * @param name
	 *            直播间名称
	 * @param desc
	 *            直播间描述
	 * @param templateType
	 *            直播模板类型，请求模板信息接口可获得模板类型的详细信息。
	 * @param authType
	 *            验证方式，0：接口验证，需要填写下面的checkurl；1：密码验证，需要填写下面的playpass；2：免密码验证
	 * @param publisherPass
	 *            推流端密码，即讲师密码
	 * @param assistantPass
	 *            助教端密码
	 * @param playPass
	 *            播放端密码
	 * @param checkUrl
	 *            验证地址
	 * @param barrage
	 *            是否开启弹幕。0：不开启；1：开启
	 * @param foreignPublish
	 *            是否开启第三方推流。0：不开启；1：开启
	 * @param openLowdelayMode
	 *            开启直播低延时模式。0为关闭；1为开启
	 * @param showUserCount
	 *            在页面显示当前在线人数。0表示不显示；1表示显示
	 * @param openHostMode
	 *            开启主持人模式，"0"表示不开启；"1"表示开启
	 * @param warmVideoId
	 *            插播暖场视频，填写同一账号下云点播视频vid
	 * @param appKey
	 */
	public String createLiveRoom(String name, String desc, int templateType, int authType,
			String publisherPass, String assistantPass, String playPass, String checkUrl,
			boolean barrage, boolean foreignPublish, boolean openLowdelayMode,
			boolean showUserCount, boolean openHostMode, String warmVideoId) {
		String param = setLiveRoomInfo(null, name, desc, templateType, authType, publisherPass,
				assistantPass, playPass, checkUrl, barrage, foreignPublish, openLowdelayMode,
				showUserCount, openHostMode, warmVideoId);
		String url = API_BASE_URL + "room/create?" + param;
		return url;
	}

	/**
	 * @方法:编辑直播间
	 * @创建人:独泪了无痕
	 * @param roomid
	 *            直播间id
	 * @param userId
	 *            用户id
	 * @param name
	 *            直播间名称
	 * @param desc
	 *            直播间描述
	 * @param templateType
	 *            直播模板类型，请求模板信息接口可获得模板类型的详细信息。
	 * @param authType
	 *            验证方式，0：接口验证，需要填写下面的checkurl；1：密码验证，需要填写下面的playpass；2：免密码验证
	 * @param publisherPass
	 *            推流端密码，即讲师密码
	 * @param assistantPass
	 *            助教端密码
	 * @param playPass
	 *            播放端密码
	 * @param checkUrl
	 *            验证地址
	 * @param barrage
	 *            是否开启弹幕。0：不开启；1：开启
	 * @param foreignPublish
	 *            是否开启第三方推流。0：不开启；1：开启
	 * @param openLowdelayMode
	 *            开启直播低延时模式。0为关闭；1为开启
	 * @param showUserCount
	 *            在页面显示当前在线人数。0表示不显示；1表示显示
	 * @param openHostMode
	 *            开启主持人模式，"0"表示不开启；"1"表示开启
	 * @param warmVideoId
	 *            插播暖场视频，填写同一账号下云点播视频vid
	 * @param appKey
	 */
	public String updateLiveRoom(String roomid, String name, String desc, int templateType,
			int authType, String publisherPass, String assistantPass, String playPass,
			String checkUrl, boolean barrage, boolean foreignPublish, boolean openLowdelayMode,
			boolean showUserCount, boolean openHostMode, String warmVideoId) {
		String param = setLiveRoomInfo(roomid, name, desc, templateType, authType, publisherPass,
				assistantPass, playPass, checkUrl, barrage, foreignPublish, openLowdelayMode,
				showUserCount, openHostMode, warmVideoId);
		String url = API_BASE_URL + "room/update?" + param;
		return url;
	}

	/**
	 * @方法:关闭直播间
	 * @创建人:独泪了无痕
	 * @param userId
	 *            用户id
	 */
	public String closeLiveRoom(String roomId) {
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("roomid", roomId);
		paramMap.put("userid", platAccount);
		String url = API_BASE_URL + "room/close?" + createHashedQueryString(paramMap);
		return url;
	}

	/**
	 * @方法描述 : 获取直播间列表
	 * @param userId
	 *            用户id
	 * @param pageNum
	 *            每页显示的个数，系统默认值为50
	 * @param pageIndex
	 *            页码，系统默认值为1
	 */
	public String getLiveRoomList(int pageNum, int pageIndex) {
		if (!QvoConditionUtil.checkInteger(pageNum)) {
			pageNum = 50;
		}
		if (!QvoConditionUtil.checkInteger(pageIndex)) {
			pageIndex = 1;
		}

		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("userid", platAccount);
		paramMap.put("pagenum", Integer.toString(pageNum));
		paramMap.put("pageindex", Integer.toString(pageIndex));
		String url = API_BASE_URL + "room/info?" + createHashedQueryString(paramMap);
		return url;
	}

	/**
	 * @方法:获取直播间信息
	 * @创建人:独泪了无痕
	 * @param roomId
	 *            直播间id
	 */
	public String searchLiveRoom(String roomId) {
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("userid", platAccount);
		paramMap.put("roomid", roomId);
		String url = API_BASE_URL + "room/search?" + createHashedQueryString(paramMap);
		return url;
	}

	/**
	 * @方法描述 : 获取直播列表
	 * @param roomid
	 * @param userId
	 * @param pageNum
	 *            每页显示的个数，系统默认值为50
	 * @param pageIndex
	 *            页码，系统默认值为1
	 * @param starttime
	 *            查询起始时间, 精确到分钟，例："2015-01-01 12:30"
	 * @param endtime
	 *            查询截止时间,精确到分钟，例："2015-01-02 12:30"
	 */
	public String getLiveInfoList(String roomid, int pageNum, int pageIndex, String startTime,
			String endTime) {
		if (!QvoConditionUtil.checkInteger(pageNum)) {
			pageNum = 50;
		}
		if (!QvoConditionUtil.checkInteger(pageIndex)) {
			pageIndex = 1;
		}

		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("userid", platAccount);
		paramMap.put("roomid", roomid);
		paramMap.put("pagenum", Integer.toString(pageNum));
		paramMap.put("pageindex", Integer.toString(pageIndex));
		paramMap.put("starttime", startTime);
		paramMap.put("endtime", endTime);
		String url = API_BASE_URL + "v2/live/info?" + createHashedQueryString(paramMap);
		return url;
	}

	/**
	 * @方法:获取回放列表的信息
	 * @创建人:独泪了无痕
	 * @param roomid
	 *            直播间id
	 * @param pageNum
	 *            每页显示的个数，系统默认值为50
	 * @param pageIndex
	 *            页码，系统默认值为1
	 * @param starttime
	 *            查询起始时间,精确到分钟，例："2015-01-01 12:30"
	 * @param endtime
	 *            查询截止时间, 精确到分钟，例："2015-01-02 12:30"
	 * @param liveid
	 *            直播id
	 */
	public String getLiveRecordList(String roomid, int pageNum, int pageIndex, String startTime,
			String endTime, String liveid) {
		if (!QvoConditionUtil.checkInteger(pageNum)) {
			pageNum = 50;
		}
		if (!QvoConditionUtil.checkInteger(pageIndex)) {
			pageIndex = 1;
		}

		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("userid", platAccount);
		paramMap.put("roomid", roomid);
		paramMap.put("pagenum", Integer.toString(pageNum));
		paramMap.put("pageindex", Integer.toString(pageIndex));
		paramMap.put("starttime", startTime);
		paramMap.put("endtime", endTime);
		paramMap.put("liveid", liveid);
		String url = API_BASE_URL + "v2/record/info?" + createHashedQueryString(paramMap);
		return url;
	}

	/**
	 * @方法:查询回放信息
	 * @创建人:独泪了无痕
	 * @param recordid
	 *            回放id
	 * @return
	 */
	public String getLiveRecordsearch(String recordid) {
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("userid", platAccount);
		paramMap.put("recordid", recordid);
		String url = API_BASE_URL + "v2/record/search?" + createHashedQueryString(paramMap);
		return url;
	}

	/**
	 * @方法:获取正在直播的直播间列表
	 * @创建人:独泪了无痕
	 * @return
	 */
	public String getBroadcastingLiveRoom() {
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("userid", platAccount);
		String url = API_BASE_URL + "rooms/broadcasting?" + createHashedQueryString(paramMap);
		return url;
	}

	/**
	 * @方法:获取直播间直播状态
	 * @创建人:独泪了无痕
	 * @param roomids
	 *            直播间id（以英文逗号,区分)，批量查询直播间数量不能超过100个
	 * @return
	 */
	public String getLiveRoomPublish(String roomids) {
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("userid", platAccount);
		paramMap.put("roomids", roomids);
		String url = API_BASE_URL + "rooms/publishing?" + createHashedQueryString(paramMap);
		return url;
	}

	/**
	 * 
	 * @方法:获取直播间的连接数统计信息
	 * @创建人:独泪了无痕
	 * @param roomid
	 *            直播间id
	 * @param starttime
	 *            查询起始时间,精确到分钟，例："2015-01-01 12:30"
	 * @param endtime
	 *            查询截止时间, 精确到分钟，例："2015-01-02 12:30"
	 * @return
	 */
	public String getLiveRoomConnections(String roomid) {
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("userid", platAccount);
		paramMap.put("roomid", roomid);
		String url = API_BASE_URL + "statis/connections?" + createHashedQueryString(paramMap);
		return url;
	}

	/**
	 * @方法:获取直播间各模板信息
	 * @创建人:独泪了无痕
	 * @return
	 */
	public String getLiveRoomViewtemplate() {
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("userid", platAccount);
		String url = API_BASE_URL + "viewtemplate/info?" + createHashedQueryString(paramMap);
		return url;
	}

	/**
	 * @方法:获取直播间代码
	 * @创建人:独泪了无痕
	 * @param roomid
	 *            直播间id
	 * @return
	 */
	public String getLiveRoomCode(String roomid) {
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("userid", platAccount);
		paramMap.put("roomid", roomid);
		String url = API_BASE_URL + "rooms/code?" + createHashedQueryString(paramMap);
		return url;
	}

	/**
	 * @方法:获取直播间内用户进出信息
	 * @创建人:独泪了无痕
	 * @param roomid
	 *            直播间id
	 * @param starttime
	 *            查询起始时间,精确到分钟，例："2015-01-01 12:30"
	 * @param endtime
	 *            查询截止时间, 精确到分钟，例："2015-01-02 12:30"
	 * @return
	 */
	public String getLiveRoomUseraction(String roomid, String startTime, String endTime) {
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("userid", platAccount);
		paramMap.put("starttime", startTime);
		paramMap.put("endtime", endTime);
		String url = API_BASE_URL + "statis/useraction?" + createHashedQueryString(paramMap);
		return url;
	}

	/**
	 * @方法:获取观看直播的统计信息
	 * @创建人:独泪了无痕
	 * @param liveid
	 *            直播id
	 * @return
	 */
	public String getLiveRoomUserView(String liveid) {
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("userid", platAccount);
		paramMap.put("liveid", liveid);
		String url = API_BASE_URL + "statis/userview?" + createHashedQueryString(paramMap);
		return url;
	}

	/**
	 * @方法:获取聊天信息
	 * @创建人:独泪了无痕
	 * @param roomid
	 *            直播间id
	 * @param liveid
	 *            直播id
	 * @param pageNum
	 *            每页显示的个数，系统默认值为50
	 * @param pageIndex
	 *            页码，系统默认值为1
	 * @return
	 */
	public String getLiveRoomChatmsg(String roomid, String liveid, int pageNum, int pageIndex) {
		Map<String, String> paramMap = new HashMap<String, String>();
		if (!QvoConditionUtil.checkInteger(pageNum)) {
			pageNum = 50;
		}
		if (!QvoConditionUtil.checkInteger(pageIndex)) {
			pageIndex = 1;
		}

		paramMap.put("userid", platAccount);
		paramMap.put("roomid", roomid);
		paramMap.put("liveid", liveid);
		paramMap.put("pagenum", Integer.toString(pageNum));
		paramMap.put("pageindex", Integer.toString(pageIndex));
		String url = API_BASE_URL + "live/chatmsg?" + createHashedQueryString(paramMap);
		return url;
	}

	/**
	 * @方法:获取抽奖信息
	 * @创建人:独泪了无痕
	 * @param roomid
	 *            直播间id
	 * @param liveid
	 *            直播id
	 * @param pageNum
	 *            每页显示的个数，系统默认值为50，最大值为100
	 * @param pageIndex
	 *            页码，系统默认值为1
	 * @return
	 */
	public String getLiveRoomLotterys(String roomid, String liveid, int pageNum, int pageIndex) {
		if (!QvoConditionUtil.checkInteger(pageNum)) {
			pageNum = 50;
		}
		if (!QvoConditionUtil.checkInteger(pageIndex)) {
			pageIndex = 1;
		}

		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("userid", platAccount);
		paramMap.put("roomid", roomid);
		paramMap.put("liveid", liveid);
		paramMap.put("pagenum", Integer.toString(pageNum));
		paramMap.put("pageindex", Integer.toString(pageIndex));
		String url = API_BASE_URL + "live/lotterys?" + createHashedQueryString(paramMap);
		return url;
	}

	/**
	 * @方法:获取问答信息
	 * @创建人:独泪了无痕
	 * @param roomid
	 *            直播间id
	 * @param liveid
	 *            直播id
	 * @param pageNum
	 *            每页显示的个数，系统默认值为50，最大值为100
	 * @param pageIndex
	 *            页码，系统默认值为1
	 * @return
	 */
	public String getLiveRoomQas(String roomid, String liveid, int pageNum, int pageIndex) {
		Map<String, String> paramMap = new HashMap<String, String>();
		if (!QvoConditionUtil.checkInteger(pageNum)) {
			pageNum = 50;
		}
		if (!QvoConditionUtil.checkInteger(pageIndex)) {
			pageIndex = 1;
		}
		paramMap.put("userid", platAccount);
		paramMap.put("roomid", roomid);
		paramMap.put("liveid", liveid);
		paramMap.put("pagenum", Integer.toString(pageNum));
		paramMap.put("pageindex", Integer.toString(pageIndex));
		String url = API_BASE_URL + "live/qas?" + createHashedQueryString(paramMap);
		return url;
	}

	/**
	 * @方法:获取签到信息
	 * @创建人:独泪了无痕
	 * @param roomid
	 *            直播间id
	 * @param liveid
	 *            直播id
	 * @return
	 */
	public String getLiveRoomRollcall(String roomid, String liveid) {
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("userid", platAccount);
		paramMap.put("roomid", roomid);
		paramMap.put("liveid", liveid);
		String url = API_BASE_URL + "live/rollcall?" + createHashedQueryString(paramMap);
		return url;
	}

	/**
	 * @方法:获取签到用户信息
	 * @创建人:独泪了无痕
	 * @param roomid
	 *            直播间id
	 * @param liveid
	 *            直播id
	 * @param rollcallid
	 *            签到ID
	 * @return
	 */
	public String getLiveRoomRollcallviewers(String roomid, String liveid, String rollcallid) {
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("userid", platAccount);
		paramMap.put("roomid", roomid);
		paramMap.put("liveid", liveid);
		paramMap.put("rollcallid", rollcallid);
		String url = API_BASE_URL + "live/rollcall/viewers?" + createHashedQueryString(paramMap);
		return url;
	}

	/**
	 * @方法:获取问卷信息
	 * @创建人:独泪了无痕
	 * @param liveid
	 *            直播id
	 * @return
	 */
	public String getLiveRoomQuestionnaires(String liveid) {
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("userid", platAccount);
		paramMap.put("liveid", liveid);
		String url = API_BASE_URL + "live/questionnaires?" + createHashedQueryString(paramMap);
		return url;
	}

	/**
	 * @方法:获取用户答卷信息
	 * @创建人:独泪了无痕
	 * @param liveid
	 *            直播id
	 * @param questionnaireid
	 *            问卷ID
	 * @param pageNum
	 *            每页显示的个数，系统默认值为50
	 * @param pageIndex
	 *            页码，系统默认值为1
	 * @return
	 */
	public String getLiveRoomQuestionnairesViewers(String liveid, String questionnaireid,
			int pageIndex, int pageNum) {
		if (!QvoConditionUtil.checkInteger(pageNum)) {
			pageNum = 50;
		}
		if (!QvoConditionUtil.checkInteger(pageIndex)) {
			pageIndex = 1;
		}

		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("userid", platAccount);
		paramMap.put("liveid", liveid);
		paramMap.put("questionnaireid", questionnaireid);
		paramMap.put("pagenum", Integer.toString(pageNum));
		paramMap.put("pageindex", Integer.toString(pageIndex));
		String url = API_BASE_URL + "live/questionnaire/viewers"
				+ createHashedQueryString(paramMap);
		return url;
	}

	/**
	 * @方法描述 : 直播自动登录方式
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
	public String getLiveAutoLogin(int visitorType, String roomId, String userId, String viewerName,
			String viewerToken) {
		String result = "";

		if (StringUtils.isBlank(viewerToken)) {
			viewerToken = "";
		}

		if (StringUtils.isBlank(userId) || StringUtils.isBlank(roomId)) {
			return "";
		}
		if (visitorType == 1) {// 讲师端自动登陆
			result = String.format(lecturerAutoLogin, roomId, userId, viewerName, viewerToken);
		} else if (visitorType == 2) { // 助教端自动登陆
			result = String.format(assistantAutoLogin, roomId, userId, viewerName, viewerToken);
		} else if (visitorType == 3) { // 观众端自动登陆
			result = String.format(visitorAutoLogin, roomId, userId, viewerName, viewerToken);
		} else {
			result = String.format(visitorAutoLogin, roomId, userId, viewerName, viewerToken);
		}
		return result;
	}

	/**
	 * @方法描述 : 录播自动登录方式
	 * @param roomId
	 *            直播间id
	 * @param userId
	 *            CC直播平台的账号ID
	 * @param recordid
	 *            回放id
	 * @param viewerName
	 *            登陆用户名（自定义即可）
	 * @param viewerToken
	 *            登录检验码
	 * @return 如果roomId 或者 userId 为空，返回结果 空
	 */
	public String getRecordAutoLogin(String roomId, String userId, String recordTd,
			String viewerName, String viewerToken) {
		String result = "";
		result = String.format(recordAutoLogin, recordTd, roomId, userId, viewerName, viewerToken);
		return result;
	}

	/** ----------------------------------------------- [私有方法] */
	private String setLiveRoomInfo(String roomid, String name, String desc, int templateType,
			int authType, String publisherPass, String assistantPass, String playPass,
			String checkUrl, boolean barrage, boolean foreignPublish, boolean openLowdelayMode,
			boolean showUserCount, boolean openHostMode, String warmVideoId) {
		Map<String, String> paramMap = new HashMap<String, String>();
		if (StringUtils.isNotBlank(roomid)) {
			paramMap.put("roomid", roomid);
		}
		paramMap.put("userid", platAccount);

		paramMap.put("name", name);
		paramMap.put("desc", desc);
		paramMap.put("templatetype", Integer.toString(templateType));
		paramMap.put("authtype", Integer.toString(authType));
		paramMap.put("publisherpass", publisherPass);
		paramMap.put("assistantpass", assistantPass);
		if (authType == 0) {
			paramMap.put("checkurl", checkUrl);
		}

		if (authType == 1) {
			paramMap.put("playpass", playPass);
		}

		if (barrage) {
			paramMap.put("barrage", Integer.toString(1));
		} else {
			paramMap.put("barrage", Integer.toString(0));
		}

		if (foreignPublish) {
			paramMap.put("foreignpublish", Integer.toString(1));
		} else {
			paramMap.put("foreignpublish", Integer.toString(0));
		}

		if (openLowdelayMode) {
			paramMap.put("openlowdelaymode", Integer.toString(1));
		} else {
			paramMap.put("openlowdelaymode", Integer.toString(0));
		}

		if (showUserCount) {
			paramMap.put("showusercount", Integer.toString(1));
		} else {
			paramMap.put("showusercount", Integer.toString(0));
		}

		if (openHostMode) {
			paramMap.put("openhostmode", Integer.toString(1));
		} else {
			paramMap.put("openhostmode", Integer.toString(0));
		}

		if (StringUtils.isNotBlank(warmVideoId)) {
			paramMap.put("warmvideoid", warmVideoId);
		} else {
			paramMap.put("warmvideoid", "");
		}

		return createHashedQueryString(paramMap);
	}

	/**
	 * @方法描述 : HTTP通信加密算法
	 * @param queryMap
	 * @param time
	 *            当前时间的 Unix 时间戳,秒数
	 * @param salt
	 * 
	 * @return
	 */
	private String createHashedQueryString(Map<String, String> paramMap) {
		Map<String, String> queryMap = sortMapByKey(paramMap);
		String result = "", hash = "", qs = "";
		long time = System.currentTimeMillis() / 1000;
		try {
			qs = Joiner.on("&").withKeyValueSeparator("=").join(queryMap);
			hash = SecureUtil.encrypt(String.format("%s&time=%d&salt=%s", qs, time, appKey), null,
					SecureUtil.MD5, null, 32).toUpperCase();
			result = String.format("%s&time=%d&hash=%s", qs, time, hash);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * @方法描述 : 每个键值对按照键的字母顺序升序排序,并且 value 值都需要以 UTF-8 格式进行 URL Encode
	 * @param paramMap
	 * @return
	 */
	private Map<String, String> sortMapByKey(Map<String, String> paramMap) {
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
