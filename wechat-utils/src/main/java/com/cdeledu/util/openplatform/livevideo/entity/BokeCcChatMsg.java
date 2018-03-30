package com.cdeledu.util.openplatform.livevideo.entity;

import java.io.Serializable;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 直播的聊天信息
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2018年3月30日 下午10:27:22
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class BokeCcChatMsg implements Serializable {
	private static final long serialVersionUID = 1L;
	/** 观众id */
	private String viewerId;
	/** 观众名称 */
	private String viewerName;
	/** 观众角色(0:未统计，1:主讲，2:助教，3:主持人，4:学员) */
	private Integer viewerRole;
	/** 聊天时间 */
	private String time;
	/** 聊天内容 */
	private String content;

	public String getViewerId() {
		return viewerId;
	}

	public void setViewerId(String viewerId) {
		this.viewerId = viewerId;
	}

	public String getViewerName() {
		return viewerName;
	}

	public void setViewerName(String viewerName) {
		this.viewerName = viewerName;
	}

	public Integer getViewerRole() {
		return viewerRole;
	}

	public void setViewerRole(Integer viewerRole) {
		this.viewerRole = viewerRole;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "BokeCcChatMsg [viewerId=" + viewerId + ", viewerName=" + viewerName
				+ ", viewerRole=" + viewerRole + ", time=" + time + ", content=" + content + "]";
	}
}
