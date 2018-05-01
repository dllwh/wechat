package com.cdeledu.common.base;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * @类描述: 数据Entity类
 * @创建者: 皇族灬战狼
 * @创建时间: 2017年2月24日 上午10:54:55
 * @版本: V1.2
 * @since: JDK 1.7
 */
public abstract class DataEntity extends BaseEntity {
	private static final long serialVersionUID = 1L;
	// 最初创建者
	protected Integer creator;
	// 数据创建时间
	@DateTimeFormat(pattern= "yyyy-MM-dd HH:mm:ss")
	protected Date createTime;
	// 最后修改人
	protected Integer modifier;
	// 数据最后更新时间
	@DateTimeFormat(pattern= "yyyy-MM-dd HH:mm:ss")
	protected Date updateTime;

	public Integer getCreator() {
		return creator;
	}

	public void setCreate(Integer creator) {
		this.creator = creator;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getModifier() {
		return modifier;
	}

	public void setModifier(Integer modifier) {
		this.modifier = modifier;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Override
	public String toString() {
		return super.toString()+ "\n DataEntity [creator=" + creator + ", createTime=" + createTime + ", modifier="
				+ modifier + ", updateTime=" + updateTime + "]";
	}
}
