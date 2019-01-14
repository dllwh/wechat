package com.cdeledu.util.openplatform.livevideo.entity.bokecc;

import java.io.Serializable;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: CC视频 直播回放实体类
 * @创建者: 皇族灬战狼
 * @联系方式: duleilewuhen@sina.com
 * @创建时间: 2018年4月13日 下午1:00:48
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class LiveVideoInfo implements Serializable {
	private static final long	serialVersionUID	= 1L;
	/** 回放id */
	private String				id;
	/** 直播id */
	private String				liveId;
	/** 开始时间 , 格式为"yyyy-MM-dd HH:mm:ss */
	private String				startTime;
	/** 结束时间, 格式为"yyyy-MM-dd HH:mm:ss", 如果直播未结束，该值则为"" */
	private String				stopTime;
	/** 录制状态，0表示录制未结束，1表示录制完成 */
	private String				recordStatus;
	/** 录制视频id，如果recordStatus为0则返回-1 */
	private String				recordVideoId;
	/** 回放地址，当recordStatus为0时返回"" */
	private String				replayUrl;
	/** 离线包下载地址，注：只有开通离线播放权限才会返回该参数 */
	private String				offlinePackageUrl;
	/** 离线包md5，注：只有开通离线播放权限才会返回该参数 */
	private String				offlinePackageMd5;
	/** 回放视频下载地址,该下载地址具有时效性，有效时间为2小时 */
	private String				downloadUrl;
	/** 直播模板类型 */
	private Integer				templateType;
	/** 回放来源，0：录制； 1：合并； 2：迁移； 3：上传 */
	private Integer				sourceType;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLiveId() {
		return liveId;
	}

	public void setLiveId(String liveId) {
		this.liveId = liveId;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getStopTime() {
		return stopTime;
	}

	public void setStopTime(String stopTime) {
		this.stopTime = stopTime;
	}

	public String getRecordStatus() {
		return recordStatus;
	}

	public void setRecordStatus(String recordStatus) {
		this.recordStatus = recordStatus;
	}

	public String getRecordVideoId() {
		return recordVideoId;
	}

	public void setRecordVideoId(String recordVideoId) {
		this.recordVideoId = recordVideoId;
	}

	public String getReplayUrl() {
		return replayUrl;
	}

	public void setReplayUrl(String replayUrl) {
		this.replayUrl = replayUrl;
	}

	public String getOfflinePackageUrl() {
		return offlinePackageUrl;
	}

	public void setOfflinePackageUrl(String offlinePackageUrl) {
		this.offlinePackageUrl = offlinePackageUrl;
	}

	public String getOfflinePackageMd5() {
		return offlinePackageMd5;
	}

	public void setOfflinePackageMd5(String offlinePackageMd5) {
		this.offlinePackageMd5 = offlinePackageMd5;
	}

	public String getDownloadUrl() {
		return downloadUrl;
	}

	public void setDownloadUrl(String downloadUrl) {
		this.downloadUrl = downloadUrl;
	}

	public Integer getTemplateType() {
		return templateType;
	}

	public void setTemplateType(Integer templateType) {
		this.templateType = templateType;
	}

	public Integer getSourceType() {
		return sourceType;
	}

	public void setSourceType(Integer sourceType) {
		this.sourceType = sourceType;
	}

	@Override
	public String toString() {
		return "LiveVideoInfo [id=" + id + ", liveId=" + liveId + ", startTime=" + startTime + ", stopTime="
				+ stopTime + ", recordStatus=" + recordStatus + ", recordVideoId=" + recordVideoId
				+ ", replayUrl=" + replayUrl + ", offlinePackageUrl=" + offlinePackageUrl
				+ ", offlinePackageMd5=" + offlinePackageMd5 + ", downloadUrl=" + downloadUrl
				+ ", templateType=" + templateType + ", sourceType=" + sourceType + "]";
	}
}
