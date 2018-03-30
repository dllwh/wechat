package com.cdeledu.util.openplatform.livevideo.entity;

import java.io.Serializable;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 直播的签到用户信息
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2018年3月30日 下午10:27:22
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class BokeCcRollcallViewer implements Serializable {
	private static final long serialVersionUID = 1L;
	/** 观看者ID */
	private String viewerName;
	/** 观看者名称 */
	private String viewerId;
	/** 是否签到，1：签到，0：未签到 */
	private Integer checked;

	public String getViewerName() {
		return viewerName;
	}

	public void setViewerName(String viewerName) {
		this.viewerName = viewerName;
	}

	public String getViewerId() {
		return viewerId;
	}

	public void setViewerId(String viewerId) {
		this.viewerId = viewerId;
	}

	public Integer getChecked() {
		return checked;
	}

	public void setChecked(Integer checked) {
		this.checked = checked;
	}

	@Override
	public String toString() {
		return "BokeCcRollcallViewer [viewerName=" + viewerName + ", viewerId=" + viewerId
				+ ", checked=" + checked + "]";
	}
}
