package com.cdeledu.util.openplatform.livevideo.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 课件
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2018年3月28日 下午10:19:54
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class TrainingCourseware implements Serializable {
	private static final long serialVersionUID = 1L;
	/** 课件ID */
	private String id;
	/** 编号 */
	private String number;
	/** 课件的URL */
	private String url;
	/** 课件名字 */
	private String subject;
	/** 所属课堂名称 */
	private String roomId;
	/** 文档的截屏 */
	private String screenshot;
	/** 资源ID */
	private long recordId;
	/** 描述 */
	private String description;
	/** 课件口令 */
	private String token;
	/** 创建时间 */
	private Date createdTime;
	/** 创建该课件的用户ID */
	private long creator;
	/** Web端学生界面设置(1是三分屏，2是文档/视频为主，3是两分屏，4：互动增加) */
	private Integer uiMode;
	/** 三分屏颜色选择 */
	private String uiColor;
	/** uiMode等于2时候，设置是否显示小窗口。默认为false */
	private boolean uiWindow = Boolean.FALSE;
	/** uiMode等于2时候，设置是否视频为主。默认为false */
	private boolean uiVideo = Boolean.FALSE;
	/** uiMode等于2时候，设置是否自动播放。默认为true */
	private boolean autoPlay = Boolean.TRUE;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getRoomId() {
		return roomId;
	}

	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

	public String getScreenshot() {
		return screenshot;
	}

	public void setScreenshot(String screenshot) {
		this.screenshot = screenshot;
	}

	public long getRecordId() {
		return recordId;
	}

	public void setRecordId(long recordId) {
		this.recordId = recordId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public long getCreator() {
		return creator;
	}

	public void setCreator(long creator) {
		this.creator = creator;
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

	public boolean isAutoPlay() {
		return autoPlay;
	}

	public void setAutoPlay(boolean autoPlay) {
		this.autoPlay = autoPlay;
	}
}