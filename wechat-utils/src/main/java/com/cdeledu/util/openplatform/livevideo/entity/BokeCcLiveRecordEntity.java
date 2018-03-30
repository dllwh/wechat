package com.cdeledu.util.openplatform.livevideo.entity;

import java.io.Serializable;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: CC视频 直播回放实体类
 * @创建者: 皇族灬战狼
 * @联系方式: duleilewuhen@sina.com
 * @创建时间: 2018年3月30日 下午2:20:40
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class BokeCcLiveRecordEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	/** 回放id */
	private String id;
	/** 直播id */
	private String liveId;
	/** 开始时间 */
	private String startTime;
	/** 结束时间 */
	private String stopTime;
	/** 录制状态，0表示录制未结束，1表示录制完成 */
	private String recordStatus;
	/**  */
	private String recordVideoStatus;
	/** 录制视频id，如果recordStatus为0则返回-1 */
	private String recordVideoId;
	/** 回放地址，当recordStatus为0时返回"" */
	private String replayUrl;
	/** 离线包下载地址，注：只有开通离线播放权限才会返回该参数 */
	private String offlinePackageUrl;
	/** 离线包md5，注：只有开通离线播放权限才会返回该参数 */
	private String offlinePackageMd5;
	/** */
	private String templateType;
	/** 回放视频下载地址,该下载地址具有时效性，有效时间为2小时 */
	private String downloadUrl;

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

	public String getRecordVideoStatus() {
		return recordVideoStatus;
	}

	public void setRecordVideoStatus(String recordVideoStatus) {
		this.recordVideoStatus = recordVideoStatus;
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

	public String getTemplateType() {
		return templateType;
	}

	public void setTemplateType(String templateType) {
		this.templateType = templateType;
	}

	public String getDownloadUrl() {
		return downloadUrl;
	}

	public void setDownloadUrl(String downloadUrl) {
		this.downloadUrl = downloadUrl;
	}

	@Override
	public String toString() {
		return "BokeccLiveRecordEntity [id=" + id + ", liveId=" + liveId + ", startTime="
				+ startTime + ", stopTime=" + stopTime + ", recordStatus=" + recordStatus
				+ ", recordVideoStatus=" + recordVideoStatus + ", recordVideoId=" + recordVideoId
				+ ", replayUrl=" + replayUrl + ", offlinePackageUrl=" + offlinePackageUrl
				+ ", offlinePackageMd5=" + offlinePackageMd5 + ", templateType=" + templateType
				+ ", downloadUrl=" + downloadUrl + "]";
	}
}