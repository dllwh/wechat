package com.cdeledu.util.openplatform.livevideo;

import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.lang3.StringUtils;

import com.cdeledu.common.constant.ConstantHelper;
import com.cdeledu.util.apache.collection.MapUtilHelper;
import com.cdeledu.util.apache.lang.DateUtilHelper;
import com.cdeledu.util.application.QvoConditionUtil;
import com.cdeledu.util.openplatform.livevideo.entity.BokeCcLiveRoomEntity;
import com.cdeledu.util.security.Md5Helper;
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
	/** 助教(管理员)直播自动登陆 */
	private String assistantAutoLogin = VIEW_BASE_URL
			+ "assistant/login?roomid=%s&userid=%s&autoLogin=true&viewername=%s&viewertoken=%s";
	/** 讲师端自动登陆 */
	private String lecturerAutoLogin = VIEW_BASE_URL
			+ "lecturer?roomid=%s&userid=%s&publishname=%s&publishpassword=%s";
	/** 主持人直播自动登陆 */
	private String manageAutoLogin = VIEW_BASE_URL
			+ "manage?roomid=%s&userid=%s&autoLogin=true&viewername=%s&viewertoken=%s";
	/** 观众端回放自动登陆 */
	private String recordAutoLogin = VIEW_BASE_URL
			+ "callback/login?recordid=%s&roomid=%s&userid=%s&autoLogin=true&viewername=%s&viewertoken=%s";

	/** ----------------------------------------------------- Fields end */
	/** 直播平台帐号对应的 API Key 值 */
	private String appKey;
	/** 平台账号 */
	private String platAccount;

	public BokeccHelper(String platAccount, String appKey) {
		this.platAccount = platAccount;
		this.appKey = appKey;
	}

	/**
	 * @方法描述 : 创建直播间
	 * @see <a href="http://doc.bokecc.com/live/dev/liveapi/#toc_11">创建直播间</a>
	 * @param bokeccLiveEntity
	 * @return
	 */
	public String createLiveRoom(BokeCcLiveRoomEntity bokeccLiveEntity) {
		Map<String, Object> paramMap = MapUtilHelper.beanToMapByLowerCase(bokeccLiveEntity);
		paramMap.put("userid", platAccount);
		return API_BASE_URL + "room/create?" + createHashedQueryString(paramMap);
	}

	/**
	 * @方法描述 : 编辑直播间的信息
	 * @see <a href="http://doc.bokecc.com/live/dev/liveapi/#toc_12">编辑直播间</a>
	 * @param roomid
	 * @param bokeccLiveEntity
	 * @return
	 */
	public String updateLiveRoom(BokeCcLiveRoomEntity bokeccLiveEntity) {
		Map<String, Object> paramMap = MapUtilHelper.beanToMapByLowerCase(bokeccLiveEntity);
		paramMap.put("userid", platAccount);
		paramMap.put("roomid", bokeccLiveEntity.getId());
		return API_BASE_URL + "room/update?" + createHashedQueryString(paramMap);
	}

	/**
	 * @方法描述 : 关闭直播间
	 * @see <a href="http://doc.bokecc.com/live/dev/liveapi/#toc_13">关闭直播间</a>
	 * @param roomId
	 *            直播间id
	 * @return
	 */
	public String closeLiveRoom(String roomId) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("roomid", roomId);
		paramMap.put("userid", platAccount);
		return API_BASE_URL + "room/close?" + createHashedQueryString(paramMap);
	}

	/**
	 * @方法描述 : 获取用户的直播间列表信息
	 * @see <a href="http://doc.bokecc.com/live/dev/liveapi/#toc_14">获取直播间列表</a>
	 * @param pageNum
	 *            每页显示的个数，系统默认值为50
	 * @param pageIndex
	 *            页码，系统默认值为1
	 */
	public String getLiveRoomList(Integer pageNum, Integer pageIndex) {
		if (!QvoConditionUtil.checkInteger(pageNum)) {
			pageNum = 50;
		}
		if (!QvoConditionUtil.checkInteger(pageIndex)) {
			pageIndex = 1;
		}

		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userid", platAccount);
		paramMap.put("pagenum", pageNum);
		paramMap.put("pageindex", pageIndex);
		return API_BASE_URL + "room/info?" + createHashedQueryString(paramMap);
	}

	/**
	 * @方法:获取直播间的信息
	 * @see <a href="http://doc.bokecc.com/live/dev/liveapi/#toc_15">获取直播间的信息
	 *      </a>
	 * @创建人:独泪了无痕
	 * @param roomId
	 *            直播间id
	 */
	public String getLiveRoomInfo(String roomId) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userid", platAccount);
		paramMap.put("roomid", roomId);
		return API_BASE_URL + "room/search?" + createHashedQueryString(paramMap);
	}

	/**
	 * @方法描述 : 获取指定直播间下历史直播信息
	 * @see <a href="http://doc.bokecc.com/live/dev/liveapi/#toc_16">获取直播列表</a>
	 * @param roomId
	 *            直播间id
	 * @param pageNum
	 *            每页显示的个数，系统默认值为50
	 * @param pageIndex
	 *            页码，系统默认值为1
	 * @param endTime
	 *            查询截止时间,精确到分钟，例："2015-01-02 12:30"
	 */
	public String getLiveVideoList(String roomId, Integer pageNum, Integer pageIndex,
			String endTime) {
		if (!QvoConditionUtil.checkInteger(pageNum)) {
			pageNum = 50;
		}
		if (!QvoConditionUtil.checkInteger(pageIndex)) {
			pageIndex = 1;
		}

		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userid", platAccount);
		paramMap.put("roomid", roomId);
		paramMap.put("pagenum", pageNum);
		paramMap.put("pageindex", pageIndex);
		if(StringUtils.isNotBlank(endTime)){
			paramMap.put("endtime", endTime);
		}
		
		return API_BASE_URL + "v2/live/info?" + createHashedQueryString(paramMap);
	}

	/**
	 * @方法:获取回放列表的信息
	 * @see <a href="http://doc.bokecc.com/live/dev/liveapi/#toc_17">查询回放列表</a>
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
	 *            直播id,若为空,则查询该直播下的回放信息
	 */
	public String getLiveRecordList(String roomId, Integer pageNum, Integer pageIndex,
			String endTime, String liveId) {
		if (!QvoConditionUtil.checkInteger(pageNum)) {
			pageNum = 50;
		}
		if (!QvoConditionUtil.checkInteger(pageIndex)) {
			pageIndex = 1;
		}

		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userid", platAccount);
		paramMap.put("roomid", roomId);
		paramMap.put("pagenum", pageNum);
		paramMap.put("pageindex", pageIndex);
		if(StringUtils.isNotBlank(endTime)){			
			paramMap.put("endtime", endTime);
		}
		if(StringUtils.isNotBlank(liveId)){
			paramMap.put("liveid", liveId);
		}
		return API_BASE_URL + "v2/record/info?" + createHashedQueryString(paramMap);
	}

	/**
	 * @方法:获取单个回放信息
	 * @see <a href="http://doc.bokecc.com/live/dev/liveapi/#toc_18">查询回放信息</a>
	 * @创建人:独泪了无痕
	 * @param recordid
	 *            回放id
	 * @return
	 */
	public String getLiveRecordInfo(String recordId) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userid", platAccount);
		paramMap.put("recordid", recordId);
		return API_BASE_URL + "v2/record/search?" + createHashedQueryString(paramMap);
	}

	/**
	 * @方法:获取用户账号下所有正在进行直播的直播间列表
	 * @see <a href="http://doc.bokecc.com/live/dev/liveapi/#toc_19">
	 *      获取正在直播的直播间列表</a>
	 * @创建人:独泪了无痕
	 * @return
	 */
	public String getLiveBroadcastingList() {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userid", platAccount);
		return API_BASE_URL + "rooms/broadcasting?" + createHashedQueryString(paramMap);
	}

	/**
	 * @方法:获取直播间直播状态
	 * @see <a href="http://doc.bokecc.com/live/dev/liveapi/#toc_20">获取直播间直播状态
	 *      </a>
	 * @创建人:独泪了无痕
	 * @param roomids
	 *            直播间id（以英文逗号,区分)，批量查询直播间数量不能超过100个
	 * @return
	 */
	public String getLiveRoomPublish(String roomids) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userid", platAccount);
		paramMap.put("roomids", roomids);
		return API_BASE_URL + "rooms/publishing?" + createHashedQueryString(paramMap);
	}

	/**
	 * 
	 * @方法:获取直播间的连接数统计信息
	 * @see <a href="http://doc.bokecc.com/live/dev/liveapi/#toc_21">获取直播间连接数
	 *      </a>
	 * @创建人:独泪了无痕
	 * @param roomid
	 *            直播间id
	 * @param starttime
	 *            查询起始时间,精确到分钟，例："2015-01-01 12:30"
	 * @param endtime
	 *            查询截止时间, 精确到分钟，例："2015-01-02 12:30"
	 * @return
	 */
	public String getLiveRoomConnections(String roomId, String startTime, String endTime) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userid", platAccount);
		paramMap.put("roomid", roomId);
		if(StringUtils.isNotBlank(startTime)){
			paramMap.put("starttime", startTime);
		}
		if(StringUtils.isNotBlank(endTime)){
			paramMap.put("endtime", endTime);
		}
		return API_BASE_URL + "statis/connections?" + createHashedQueryString(paramMap);
	}

	/**
	 * @方法:获取直播间各模板信息
	 * @see <a href="http://doc.bokecc.com/live/dev/liveapi/#toc_22">获取直播间模板信息
	 *      </a>
	 * @创建人:独泪了无痕
	 * @return
	 */
	public String getLiveRoomViewtemplate() {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userid", platAccount);
		return API_BASE_URL + "viewtemplate/info?" + createHashedQueryString(paramMap);
	}

	/**
	 * @方法:获取直播间的代码信息，包括观看地址信息、客户端登陆地址、助教端登录地址、推流地址(只有第三方推流用户才可以获得)
	 * @see <a href="http://doc.bokecc.com/live/dev/liveapi/#toc_23">获取直播间代码</a>
	 * @创建人:独泪了无痕
	 * @param roomid
	 *            直播间id
	 * @return
	 */
	public String getLiveRoomCode(String roomId) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userid", platAccount);
		paramMap.put("roomid", roomId);
		return API_BASE_URL + "room/code?" + createHashedQueryString(paramMap);
	}

	/**
	 * @方法:获取直播间内用户进出信息
	 * @see <a href="http://doc.bokecc.com/live/dev/liveapi/#toc_24">
	 *      获取直播间内用户进出信息</a>
	 * @创建人:独泪了无痕
	 * @param roomid
	 *            直播间id
	 * @param starttime
	 *            查询起始时间,精确到分钟，例："2015-01-01 12:30"
	 * @param endtime
	 *            查询截止时间, 精确到分钟，例："2015-01-02 12:30"
	 * @return
	 */
	public String getLiveRoomUseraction(String roomId, String startTime, String endTime) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userid", platAccount);
		paramMap.put("roomid", roomId);
		paramMap.put("starttime", startTime);
		paramMap.put("endtime", endTime);
		return API_BASE_URL + "statis/useraction?" + createHashedQueryString(paramMap);
	}

	/**
	 * @方法:获取观看直播的统计信息
	 * @see <a href="http://doc.bokecc.com/live/dev/liveapi/#toc_25">获取观看直播的统计信息
	 *      </a>
	 * @创建人:独泪了无痕
	 * @param liveid
	 *            直播id
	 * @return
	 */
	public String getLiveRoomUserView(String liveid) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userid", platAccount);
		paramMap.put("liveid", liveid);
		return API_BASE_URL + "statis/userview?" + createHashedQueryString(paramMap);
	}

	/**
	 * @方法:获取单个直播观看回放的用户登录，退出行为统计
	 * @see <a href="http://doc.bokecc.com/live/dev/liveapi/#toc_26">
	 *      获取单个直播回放的观看统计信息</a>
	 * @创建人:独泪了无痕
	 * @param recordId
	 *            录制id
	 * @param pageNum
	 *            每页显示的个数，系统默认值为50
	 * @param pageIndex
	 *            页码，系统默认值为1
	 * @return
	 */
	public String getLiveReplayUserAction(String recordId, Integer pageIndex, Integer pageNum) {
		if (!QvoConditionUtil.checkInteger(pageNum)) {
			pageNum = 50;
		}
		if (!QvoConditionUtil.checkInteger(pageIndex)) {
			pageIndex = 1;
		}

		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userid", platAccount);
		paramMap.put("recordid", recordId);
		paramMap.put("pagenum", pageNum);
		paramMap.put("pageindex", pageIndex);
		return API_BASE_URL + "v2/statis/replay/useraction?" + createHashedQueryString(paramMap);
	}

	/**
	 * @方法:获取观看直播回放的用户登录，退出行为统计
	 * @see <a href="http://doc.bokecc.com/live/dev/liveapi/#toc_27">
	 *      获取所有直播回放的观看统计信息</a>
	 * @创建人:独泪了无痕
	 * @param recordId
	 *            录制id
	 * @param pageNum
	 *            每页显示的个数，系统默认值为50
	 * @param pageIndex
	 *            页码，系统默认值为1
	 * @return
	 */
	public String getLiveReplayList(Date startTime, Date endTime, Integer pageIndex,
			Integer pageNum) {
		if (!QvoConditionUtil.checkInteger(pageNum)) {
			pageNum = 50;
		}
		if (!QvoConditionUtil.checkInteger(pageIndex)) {
			pageIndex = 1;
		}

		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userid", platAccount);
		paramMap.put("starttime", DateUtilHelper.formatDate(startTime, "yyyy-MM-dd HH:mm"));
		paramMap.put("endtime", DateUtilHelper.formatDate(startTime, "yyyy-MM-dd HH:mm"));
		paramMap.put("pagenum", pageNum);
		paramMap.put("pageindex", pageIndex);
		return API_BASE_URL + "v2/statis/replay?" + createHashedQueryString(paramMap);
	}

	/**
	 * @方法:分页获取直播的聊天信息
	 * @see <a href="http://doc.bokecc.com/live/dev/liveapi/#toc_28">获取聊天信息</a>
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
	public String getLiveRoomChatmsg(String roomId, String liveid, int pageNum, int pageIndex) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		if (!QvoConditionUtil.checkInteger(pageNum)) {
			pageNum = 50;
		}
		if (!QvoConditionUtil.checkInteger(pageIndex)) {
			pageIndex = 1;
		}

		paramMap.put("userid", platAccount);
		paramMap.put("roomid", roomId);
		paramMap.put("liveid", liveid);
		paramMap.put("pagenum", pageNum);
		paramMap.put("pageindex", pageIndex);
		return API_BASE_URL + "live/chatmsg?" + createHashedQueryString(paramMap);
	}

	/**
	 * @方法:获取抽奖信息
	 * @see <a href="http://doc.bokecc.com/live/dev/liveapi/#toc_29">获取抽奖信息</a>
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
	public String getLiveRoomLotterys(String roomId, String liveid, int pageNum, int pageIndex) {
		if (!QvoConditionUtil.checkInteger(pageNum)) {
			pageNum = 50;
		}
		if (!QvoConditionUtil.checkInteger(pageIndex)) {
			pageIndex = 1;
		}

		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userid", platAccount);
		paramMap.put("roomid", roomId);
		paramMap.put("liveid", liveid);
		paramMap.put("pagenum", pageNum);
		paramMap.put("pageindex", pageIndex);
		return API_BASE_URL + "live/lotterys?" + createHashedQueryString(paramMap);
	}

	/**
	 * @方法:获取问答信息
	 * @see <a href="http://doc.bokecc.com/live/dev/liveapi/#toc_30">获取问答信息</a>
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
	public String getLiveRoomQas(String roomId, String liveid, int pageNum, int pageIndex) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		if (!QvoConditionUtil.checkInteger(pageNum)) {
			pageNum = 50;
		}
		if (!QvoConditionUtil.checkInteger(pageIndex)) {
			pageIndex = 1;
		}
		paramMap.put("userid", platAccount);
		paramMap.put("roomid", roomId);
		paramMap.put("liveid", liveid);
		paramMap.put("pagenum", pageNum);
		paramMap.put("pageindex", pageIndex);
		return API_BASE_URL + "live/qas?" + createHashedQueryString(paramMap);
	}

	/**
	 * @方法:获取签到信息
	 * @see <a href="http://doc.bokecc.com/live/dev/liveapi/#toc_31">获取签到信息</a>
	 * @创建人:独泪了无痕
	 * @param roomid
	 *            直播间id
	 * @param liveid
	 *            直播id
	 * @return
	 */
	public String getLiveRoomRollcall(String roomid, String liveid) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userid", platAccount);
		paramMap.put("roomid", roomid);
		paramMap.put("liveid", liveid);
		return API_BASE_URL + "live/rollcall?" + createHashedQueryString(paramMap);
	}

	/**
	 * @方法:获取签到用户信息
	 * @see <a href="http://doc.bokecc.com/live/dev/liveapi/#toc_32">获取签到用户信息
	 *      </a>
	 * @创建人:独泪了无痕
	 * @param roomid
	 *            直播间id
	 * @param liveid
	 *            直播id
	 * @param rollcallid
	 *            签到ID
	 * @return
	 */
	public String getLiveRoomRollcallviewers(String roomId, String liveId, String rollcallId) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userid", platAccount);
		paramMap.put("roomid", roomId);
		paramMap.put("liveid", liveId);
		paramMap.put("rollcallid", rollcallId);
		return API_BASE_URL + "live/rollcall/viewers?" + createHashedQueryString(paramMap);
	}

	/**
	 * @方法:获取问卷信息
	 * @see <a href="http://doc.bokecc.com/live/dev/liveapi/#toc_33">获取问卷信息</a>
	 * @创建人:独泪了无痕
	 * @param liveid
	 *            直播id
	 * @return
	 */
	public String getLiveRoomQuestionnaires(String liveId) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userid", platAccount);
		paramMap.put("liveid", liveId);
		return API_BASE_URL + "live/questionnaires?" + createHashedQueryString(paramMap);
	}

	/**
	 * @方法:获取用户答卷信息
	 * @see <a href="http://doc.bokecc.com/live/dev/liveapi/#toc_34">获取用户答卷信息
	 *      </a>
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

		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userid", platAccount);
		paramMap.put("liveid", liveid);
		paramMap.put("questionnaireid", questionnaireid);
		paramMap.put("pagenum", pageNum);
		paramMap.put("pageindex", pageIndex);
		return API_BASE_URL + "live/questionnaire/viewers" + createHashedQueryString(paramMap);
	}

	/**
	 * @方法描述 : 直播自动登录方式
	 * @see <a href="http://doc.bokecc.com/live/dev/liveapi/#toc_36">观众端自动登陆</a>
	 * @see <a href="http://doc.bokecc.com/live/dev/liveapi/#toc_37">助教端自动登陆</a>
	 * @see <a href="http://doc.bokecc.com/live/dev/liveapi/#toc_38">主持人自动登陆</a>
	 * @see <a href="http://doc.bokecc.com/live/dev/liveapi/#toc_39">讲师端自动登陆</a>
	 * @param visitorType
	 *            用户类型：1:讲师;2:助教;3:主持人;4:游客(观众或学员);默认是4
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
	public String getLiveAutoLogin(int visitorType, String roomId, String viewerName,
			String viewerToken) {
		if (StringUtils.isBlank(roomId)) {
			return "";
		}
		String result = "";

		if (StringUtils.isBlank(viewerToken)) {
			viewerToken = "";
		}

		if (visitorType == 1) {// 讲师端自动登陆
			result = String.format(lecturerAutoLogin, roomId, platAccount, viewerName, viewerToken);
		} else if (visitorType == 2) { // 助教端自动登陆
			result = String.format(assistantAutoLogin, roomId, platAccount, viewerName,
					viewerToken);
		} else if (visitorType == 3) { // 主持人自动登陆
			result = String.format(manageAutoLogin, roomId, platAccount, viewerName, viewerToken);
		} else if (visitorType == 4) { // 观众端自动登陆
			result = String.format(visitorAutoLogin, roomId, platAccount, viewerName, viewerToken);
		} else {
			result = String.format(visitorAutoLogin, roomId, platAccount, viewerName, viewerToken);
		}
		return result;
	}

	/**
	 * @方法描述 : 录播自动登录方式
	 * @see <a href="http://doc.bokecc.com/live/dev/liveapi/#toc_36">观众端自动登陆</a>
	 * @param roomId
	 *            直播间id
	 * @param recordid
	 *            回放id
	 * @param viewerName
	 *            登陆用户名（自定义即可）
	 * @param viewerToken
	 *            登录检验码
	 * @return 如果roomId 或者 userId 为空，返回结果 空
	 */
	public String getRecordAutoLogin(String roomId, String recordTd, String viewerName,
			String viewerToken) {
		String result = "";
		result = String.format(recordAutoLogin, recordTd, roomId, platAccount, viewerName,
				viewerToken);
		return result;
	}

	/** ----------------------------------------------- [私有方法] */
	/**
	 * @方法描述 : HTTP通信加密算法
	 * @see <a href="http://doc.bokecc.com/live/dev/liveapi/#toc_40">加密算法</a>
	 * @param queryMap
	 * @param time
	 *            当前时间的 Unix 时间戳,秒数
	 * @param salt
	 * 
	 * @return
	 */
	private String createHashedQueryString(Map<String, Object> paramMap) {
		Map<String, Object> map = new TreeMap<String, Object>(paramMap);
		Map<String, Object> queryMap = createQueryString(map);
		String result = "", hash = "", qs = "";
		qs = Joiner.on("&").withKeyValueSeparator("=").join(queryMap);
		if (StringUtils.isBlank(qs)) {
			return "";
		}
		
		long time = System.currentTimeMillis() / 1000;

		hash = Md5Helper.md5(String.format("%s&time=%d&salt=%s", qs, time, appKey), 32)
				.toUpperCase();
		result = String.format("%s&time=%d&hash=%s", qs, time, hash);

		return result;
	}

	/**
	 * @方法描述 : 每个键值对按照键的字母顺序升序排序,并且 value 值都需要以 UTF-8 格式进行 URL Encode
	 * @param paramMap
	 * @return
	 */
	private Map<String, Object> createQueryString(Map<String, Object> paramMap) {
		Map<String, Object> sortedMap = new LinkedHashMap<String, Object>();
		Iterator<Map.Entry<String, Object>> iter = paramMap.entrySet().iterator();
		Map.Entry<String, Object> tmpEntry = null;
		String keyOfValue = "";
		while (iter.hasNext()) {
			tmpEntry = iter.next();
			try {
				keyOfValue = String.valueOf(tmpEntry.getValue());
				sortedMap.put(tmpEntry.getKey(),
						URLEncoder.encode(keyOfValue, ConstantHelper.UTF_8.name()));
			} catch (Exception e) {
				sortedMap.put(tmpEntry.getKey(), keyOfValue);
			}

		}
		return sortedMap;
	}

	/**
	 * ----------------------------------------------- [私有方法]
	 * 
	 * @throws Exception
	 */

	public static void main(String[] args) throws Exception {
		// String platAccount = "3796854BCEEBFF20";
		// String appKey = "cjWFUuZ4rZtCbRT2tcZJB1CUbX8B07zj";
	}
}
