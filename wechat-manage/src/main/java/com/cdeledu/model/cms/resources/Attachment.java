package com.cdeledu.model.cms.resources;

import java.util.Date;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 附件资源
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2018年6月17日 下午7:02:18
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class Attachment {
	/** */
	private Integer id;
	/** 类型 */
	private Integer type;
	/** 用途 */
	private String purpose;
	/** 附件存储路径 */
	private String path;
	/** 附件名称 */
	private String name;
	/** 附件大小 */
	private Double size;
	/** 格式 */
	private String format;
	/** 上传者 */
	private String uploadUser;
	/** 上传IP */
	private String uploadIp;
	/** 上传时间 */
	private Date uploadTime;
	/** 审核者 */
	private String auditUser;
	/** 审核时间 */
	private Date auditTime;
	/** 状态(0:未审核;1:已审核;2:审核未通过;-1:已删除;) */
	private Integer state;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getSize() {
		return size;
	}

	public void setSize(Double size) {
		this.size = size;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public String getUploadUser() {
		return uploadUser;
	}

	public void setUploadUser(String uploadUser) {
		this.uploadUser = uploadUser;
	}

	public String getUploadIp() {
		return uploadIp;
	}

	public void setUploadIp(String uploadIp) {
		this.uploadIp = uploadIp;
	}

	public Date getUploadTime() {
		return uploadTime;
	}

	public void setUploadTime(Date uploadTime) {
		this.uploadTime = uploadTime;
	}

	public String getAuditUser() {
		return auditUser;
	}

	public void setAuditUser(String auditUser) {
		this.auditUser = auditUser;
	}

	public Date getAuditTime() {
		return auditTime;
	}

	public void setAuditTime(Date auditTime) {
		this.auditTime = auditTime;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return "Attachment [id=" + id + ", type=" + type + ", purpose=" + purpose + ", path=" + path
				+ ", name=" + name + ", size=" + size + ", format=" + format + ", uploadUser="
				+ uploadUser + ", uploadIp=" + uploadIp + ", uploadTime=" + uploadTime
				+ ", auditUser=" + auditUser + ", auditTime=" + auditTime + ", state=" + state
				+ "]";
	}
}
