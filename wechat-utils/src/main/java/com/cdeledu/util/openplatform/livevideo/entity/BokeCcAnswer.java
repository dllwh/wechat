package com.cdeledu.util.openplatform.livevideo.entity;

import java.io.Serializable;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 直播的问答信息
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2018年3月30日 下午10:27:22
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class BokeCcAnswer implements Serializable {
	private static final long serialVersionUID = 1L;
	/** 学员id */
	private String viewerId;
	/** 学员名称 */
	private String viewerName;
	/** 回答者角色(0:未统计，1:主讲，2:助教，3:主持人，4:学员) */
	private Integer viewerRole;
	/** 操作时间 */
	private String time;
	/** 内容 */
	private String content;
	/** 是否私密回答 */
	private Integer isPrivate;

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

	public Integer getIsPrivate() {
		return isPrivate;
	}

	public void setIsPrivate(Integer isPrivate) {
		this.isPrivate = isPrivate;
	}

	@Override
	public String toString() {
		return "BokeCcAnswer [viewerId=" + viewerId + ", viewerName=" + viewerName + ", viewerRole="
				+ viewerRole + ", time=" + time + ", content=" + content + ", isPrivate="
				+ isPrivate + "]";
	}
}
