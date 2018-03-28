package com.cdeledu.util.openplatform.livevideo.entity;

import java.io.Serializable;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 教育培训请求实体类
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2018年3月28日 下午9:16:27
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class TrainingRoomRequest implements Serializable {
	private static final long serialVersionUID = 1L;
	/** 实时课堂主题ID */
	private String roomId;
	/** 实时课堂主题（长度：1-250） */
	private String subject;
	/** 老师加入口令（长度：6-15）（会自动生成随机数） */
	private String teacherToken;
	/** Web端学生加入口令（长度：最大15） */
	private String studentToken;
	/** 客户端学生加入口令 */
	private String studentClientToken;
	/** 开始日期,时间格式：yyyy-MM-dd HH:mm:ss或者为long型 (毫秒数) */
	private String startDate;
	/** 失效时间,时间格式：yyyy-MM-dd HH:mm:ss或者为long型 (毫秒数) */
	private String invalidDate;
	/** 助教加入口令（长度：6-15）（会自动生成随机数） */
	private String assistantToken;
	/** 老师介绍 */
	private String speakerInfo;
	/** 课程介绍 */
	private String scheduleInfo;
	/** 是否支持Web端学生加入,值为true或者false。默认值为true */
	private boolean webJoin = Boolean.TRUE;
	/** 是否支持客户端端学生加入,值为true或者false。默认值为true */
	private boolean clientJoin = Boolean.TRUE;
	/** 课堂介绍 */
	private String description;
	/** 课堂持续时长（单位为分钟） */
	private Integer duration;
	/** Web端学生界面设置(1是三分屏，2是文档/视频为主，3是两分屏，4：互动增加) */
	private Integer uiMode;
	/** 三分屏颜色选择（blue, default, green），默认是default */
	private String uiColor;
	/** 0:大讲堂，1：小班课，默认值：0。 */
	private Integer scene = 0;
	/** uiMode等于2时候，设置是否显示小窗口。默认为false */
	private boolean uiWindow = Boolean.FALSE;
	/** uiMode等于2时候，设置是否视频为主。默认为false */
	private boolean uiVideo = Boolean.FALSE;
	/** 是否允许web升级到客户端 */
	private boolean upgrade;
	/** true:表示实时的，false：表示非实时的，默认是false */
	private boolean realtime = Boolean.FALSE;
	/** 课堂最大并发数。只有站点开启指定并发数功能，才能够设置。 */
	private Integer maxAttendees;

	public String getRoomId() {
		return roomId;
	}

	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getTeacherToken() {
		return teacherToken;
	}

	public void setTeacherToken(String teacherToken) {
		this.teacherToken = teacherToken;
	}

	public String getStudentToken() {
		return studentToken;
	}

	public void setStudentToken(String studentToken) {
		this.studentToken = studentToken;
	}

	public String getStudentClientToken() {
		return studentClientToken;
	}

	public void setStudentClientToken(String studentClientToken) {
		this.studentClientToken = studentClientToken;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getInvalidDate() {
		return invalidDate;
	}

	public void setInvalidDate(String invalidDate) {
		this.invalidDate = invalidDate;
	}

	public String getAssistantToken() {
		return assistantToken;
	}

	public void setAssistantToken(String assistantToken) {
		this.assistantToken = assistantToken;
	}

	public String getSpeakerInfo() {
		return speakerInfo;
	}

	public void setSpeakerInfo(String speakerInfo) {
		this.speakerInfo = speakerInfo;
	}

	public String getScheduleInfo() {
		return scheduleInfo;
	}

	public void setScheduleInfo(String scheduleInfo) {
		this.scheduleInfo = scheduleInfo;
	}

	public boolean isWebJoin() {
		return webJoin;
	}

	public void setWebJoin(boolean webJoin) {
		this.webJoin = webJoin;
	}

	public boolean isClientJoin() {
		return clientJoin;
	}

	public void setClientJoin(boolean clientJoin) {
		this.clientJoin = clientJoin;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public Integer getUiMode() {
		return uiMode;
	}

	public void setUiMode(Integer uiMode) {
		this.uiMode = uiMode;
	}

	public String getUiColor() {
		return uiColor;
	}

	public void setUiColor(String uiColor) {
		this.uiColor = uiColor;
	}

	public Integer getScene() {
		return scene;
	}

	public void setScene(Integer scene) {
		this.scene = scene;
	}

	public boolean isUiWindow() {
		return uiWindow;
	}

	public void setUiWindow(boolean uiWindow) {
		this.uiWindow = uiWindow;
	}

	public boolean isUiVideo() {
		return uiVideo;
	}

	public void setUiVideo(boolean uiVideo) {
		this.uiVideo = uiVideo;
	}

	public boolean isUpgrade() {
		return upgrade;
	}

	public void setUpgrade(boolean upgrade) {
		this.upgrade = upgrade;
	}

	public boolean isRealtime() {
		return realtime;
	}

	public void setRealtime(boolean realtime) {
		this.realtime = realtime;
	}

	public Integer getMaxAttendees() {
		return maxAttendees;
	}

	public void setMaxAttendees(Integer maxAttendees) {
		this.maxAttendees = maxAttendees;
	}
}
