package com.cdeledu.model.system;

import java.util.Date;

import com.cdeledu.common.base.BaseEntity;

/**
 * @类描述: 图标资源
 * @创建者: 皇族灬战狼
 * @创建时间: 2017年5月22日 下午3:33:25
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class SysIcon extends BaseEntity {
	private static final long serialVersionUID = 1L;
	/** 显示名称 */
	private String displayName;
	/** class 名字 */
	private String className;
	/** 来源 */
	private String sourceType;
	/** */
	private Date createTime;

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getSourceType() {
		return sourceType;
	}

	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "SysIcon [displayName=" + displayName + ", className=" + className + ", sourceType="
				+ sourceType + ", createTime=" + createTime + "]";
	}

}
