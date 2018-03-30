package com.cdeledu.util.openplatform.livevideo.entity;

import java.io.Serializable;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: CC视频 直播实体类
 * @创建者: 皇族灬战狼
 * @联系方式: duleilewuhen@sina.com
 * @创建时间: 2018年3月30日 上午8:45:50
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class BokeCcLiveRoomEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	/** 直播间id */
	private String id;
	/** 直播间名称 */
	private String name;
	/** 直播间描述 */
	private String desc;
	/** 直播间状态 */
	private String status;
	/** 直播模板类型，请求模板信息接口可获得模板类型的详细信息 */
	private Integer templateType;
	/** 验证方式，0：接口验证，需要填写下面的checkurl；1：密码验证，需要填写下面的playpass；2：免密码验证 */
	private Integer authType = 2;
	/** 推流端密码，即讲师密码 */
	private String publisherPass;
	/** 助教端密码 */
	private String assistantPass;
	/** 播放端密码 */
	private String palyPass;
	/** 验证地址 */
	private String checkUrl;
	/** 推流地址，第三方推流用户可以获取到此参数 */
	private String publishUrl;
	/** 是否开启弹幕。0：不开启；1：开启 ,默认为0 */
	private Integer barrage = 0;
	/** 是否开启第三方推流。0：不开启；1：开启，默认为0 */
	private Integer foreignpublish = 0;
	/** 开启直播低延时模式。0为关闭；1为开启，默认为关闭 */
	private Integer openLowDelayMode = 0;
	/** 在页面显示当前在线人数。0表示不显示；1表示显示，默认显示当前 */
	private Integer showUserCount = 1;
	/** 开启主持人模式，"0"表示不开启；"1"表示开启，默认不开启 */
	private Integer openHostMode = 0;
	/** 插播暖场视频，填写同一账号下云点播视频vid，默认关闭；参数值为空，表示关闭 */
	private String warmVideoId;
	/** 直播开始时间；格式；yyyy-MM-dd */
	private String liveStartTime;
	/** 播放器提示语。未直播时播放器将显示该提示语 可选，最多15个字符 */
	private String playerBackgroundHint;
	/** 手动录制模式。0：关闭；1：开启，默认关闭 */
	private Integer manuallyRecordMode = 0;
	/** 讲师文档权限。0：关闭；1：开启，默认关闭； */
	private Integer clientDocPermissions = 0;
	/** 重复登录设置；0：允许后进入者登录;1:禁止后进入者登录，对讲师端和观看端生效 ，默认0 */
	private Integer repeatedLoginSetting = 0;
	/** 直播间并发人数上限，默认为0，表示不做限制 */
	private String maxAudienceNum;
	/** 文档显示模式。1：适合窗口;2:适合宽度，默认1 */
	private Integer documentDisplayMode = 1;
	/** 倒计时功能。0：关闭；1：开启 可选，默认关闭 */
	private Integer openLiveCountdown = 0;
	/** 讲师端显示在线人数。0：不显示；1：显示，默认显示 */
	private Integer showLectuerOnlineNum = 0;
	/** 助教主持人端显示在线人数。0：不显示；1：显示 ，默认显示 */
	private Integer showAssistOnlineNum = 1;
	/** 延时时间，单位秒。低延时模式下该值为0 */
	private String delayTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getTemplateType() {
		return templateType;
	}

	public void setTemplateType(Integer templateType) {
		this.templateType = templateType;
	}

	public Integer getAuthType() {
		return authType;
	}

	public void setAuthType(Integer authType) {
		this.authType = authType;
	}

	public String getPublisherPass() {
		return publisherPass;
	}

	public void setPublisherPass(String publisherPass) {
		this.publisherPass = publisherPass;
	}

	public String getAssistantPass() {
		return assistantPass;
	}

	public void setAssistantPass(String assistantPass) {
		this.assistantPass = assistantPass;
	}

	public String getPalyPass() {
		return palyPass;
	}

	public void setPalyPass(String palyPass) {
		this.palyPass = palyPass;
	}

	public String getCheckUrl() {
		return checkUrl;
	}

	public void setCheckUrl(String checkUrl) {
		this.checkUrl = checkUrl;
	}

	public String getPublishUrl() {
		return publishUrl;
	}

	public void setPublishUrl(String publishUrl) {
		this.publishUrl = publishUrl;
	}

	public Integer getBarrage() {
		return barrage;
	}

	public void setBarrage(Integer barrage) {
		this.barrage = barrage;
	}

	public Integer getForeignpublish() {
		return foreignpublish;
	}

	public void setForeignpublish(Integer foreignpublish) {
		this.foreignpublish = foreignpublish;
	}

	public Integer getOpenLowDelayMode() {
		return openLowDelayMode;
	}

	public void setOpenLowDelayMode(Integer openLowDelayMode) {
		this.openLowDelayMode = openLowDelayMode;
	}

	public Integer getShowUserCount() {
		return showUserCount;
	}

	public void setShowUserCount(Integer showUserCount) {
		this.showUserCount = showUserCount;
	}

	public Integer getOpenHostMode() {
		return openHostMode;
	}

	public void setOpenHostMode(Integer openHostMode) {
		this.openHostMode = openHostMode;
	}

	public String getWarmVideoId() {
		return warmVideoId;
	}

	public void setWarmVideoId(String warmVideoId) {
		this.warmVideoId = warmVideoId;
	}

	public String getLiveStartTime() {
		return liveStartTime;
	}

	public void setLiveStartTime(String liveStartTime) {
		this.liveStartTime = liveStartTime;
	}

	public String getPlayerBackgroundHint() {
		return playerBackgroundHint;
	}

	public void setPlayerBackgroundHint(String playerBackgroundHint) {
		this.playerBackgroundHint = playerBackgroundHint;
	}

	public Integer getManuallyRecordMode() {
		return manuallyRecordMode;
	}

	public void setManuallyRecordMode(Integer manuallyRecordMode) {
		this.manuallyRecordMode = manuallyRecordMode;
	}

	public Integer getClientDocPermissions() {
		return clientDocPermissions;
	}

	public void setClientDocPermissions(Integer clientDocPermissions) {
		this.clientDocPermissions = clientDocPermissions;
	}

	public Integer getRepeatedLoginSetting() {
		return repeatedLoginSetting;
	}

	public void setRepeatedLoginSetting(Integer repeatedLoginSetting) {
		this.repeatedLoginSetting = repeatedLoginSetting;
	}

	public String getMaxAudienceNum() {
		return maxAudienceNum;
	}

	public void setMaxAudienceNum(String maxAudienceNum) {
		this.maxAudienceNum = maxAudienceNum;
	}

	public Integer getDocumentDisplayMode() {
		return documentDisplayMode;
	}

	public void setDocumentDisplayMode(Integer documentDisplayMode) {
		this.documentDisplayMode = documentDisplayMode;
	}

	public Integer getOpenLiveCountdown() {
		return openLiveCountdown;
	}

	public void setOpenLiveCountdown(Integer openLiveCountdown) {
		this.openLiveCountdown = openLiveCountdown;
	}

	public Integer getShowLectuerOnlineNum() {
		return showLectuerOnlineNum;
	}

	public void setShowLectuerOnlineNum(Integer showLectuerOnlineNum) {
		this.showLectuerOnlineNum = showLectuerOnlineNum;
	}

	public Integer getShowAssistOnlineNum() {
		return showAssistOnlineNum;
	}

	public void setShowAssistOnlineNum(Integer showAssistOnlineNum) {
		this.showAssistOnlineNum = showAssistOnlineNum;
	}

	public String getDelayTime() {
		return delayTime;
	}

	public void setDelayTime(String delayTime) {
		this.delayTime = delayTime;
	}

	@Override
	public String toString() {
		return "BokeccLiveEntity [id=" + id + ", name=" + name + ", desc=" + desc + ", status="
				+ status + ", templateType=" + templateType + ", authType=" + authType
				+ ", publisherPass=" + publisherPass + ", assistantPass=" + assistantPass
				+ ", palyPass=" + palyPass + ", checkUrl=" + checkUrl + ", publishUrl=" + publishUrl
				+ ", barrage=" + barrage + ", foreignpublish=" + foreignpublish
				+ ", openLowDelayMode=" + openLowDelayMode + ", showUserCount=" + showUserCount
				+ ", openHostMode=" + openHostMode + ", warmVideoId=" + warmVideoId
				+ ", liveStartTime=" + liveStartTime + ", playerBackgroundHint="
				+ playerBackgroundHint + ", manuallyRecordMode=" + manuallyRecordMode
				+ ", clientDocPermissions=" + clientDocPermissions + ", repeatedLoginSetting="
				+ repeatedLoginSetting + ", maxAudienceNum=" + maxAudienceNum
				+ ", documentDisplayMode=" + documentDisplayMode + ", openLiveCountdown="
				+ openLiveCountdown + ", showLectuerOnlineNum=" + showLectuerOnlineNum
				+ ", showAssistOnlineNum=" + showAssistOnlineNum + ", delayTime=" + delayTime + "]";
	}

}