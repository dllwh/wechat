package com.cdeledu.model.cms;

import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.cdeledu.common.base.BaseEntity;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 通知公告表
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2018年3月2日 下午5:20:31
 * @版本: V1.0
 * @since: JDK 1.7
 */
@TableName("sys_notice")
public class SysNotice extends BaseEntity {
	private static final long serialVersionUID = 1L;
	/** 公告标题 */
	@TableField(value = "notice_title")
	private String noticeTitle;
	/** 公告类型 */
	@TableField(value = "notice_type")
	private String noticeType;
	/** 发布时间 */
	@TableField(value = "send_time")
	private Date sendTime;
	/** 信息来源 */
	@TableField(value = "info_sources")
	private String infoSources;
	/** 来源地址 */
	@TableField(value = "sources_url")
	private String sourcesUrl;
	/** 阅读次数 */
	@TableField(value = "reader_times")
	private String readerTimes;
	/** 发布状态 */
	@TableField(value = "status")
	private String status;
	/** 内容 */
	@TableField(value = "content")
	private String content;

	public String getNoticeTitle() {
		return noticeTitle;
	}

	public void setNoticeTitle(String noticeTitle) {
		this.noticeTitle = noticeTitle;
	}

	public String getNoticeType() {
		return noticeType;
	}

	public void setNoticeType(String noticeType) {
		this.noticeType = noticeType;
	}

	public Date getSendTime() {
		return sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

	public String getInfoSources() {
		return infoSources;
	}

	public void setInfoSources(String infoSources) {
		this.infoSources = infoSources;
	}

	public String getSourcesUrl() {
		return sourcesUrl;
	}

	public void setSourcesUrl(String sourcesUrl) {
		this.sourcesUrl = sourcesUrl;
	}

	public String getReaderTimes() {
		return readerTimes;
	}

	public void setReaderTimes(String readerTimes) {
		this.readerTimes = readerTimes;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
