package com.cdeledu.util.openplatform.livevideo.entity;

import java.io.Serializable;
import java.util.List;

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
public class BokeCcQuestion implements Serializable {
	private static final long serialVersionUID = 1L;
	/** 学员id */
	private String viewerId;
	/** 学员名称 */
	private String viewerName;
	/** 操作时间 */
	private String time;
	/** 内容 */
	private String content;
	/** 回答列表 */
	private List<BokeCcAnswer> answers;

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

	public List<BokeCcAnswer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<BokeCcAnswer> answers) {
		this.answers = answers;
	}

	@Override
	public String toString() {
		return "BokeCcQuestion [viewerId=" + viewerId + ", viewerName=" + viewerName + ", time="
				+ time + ", content=" + content + ", answers=" + answers + "]";
	}

}
