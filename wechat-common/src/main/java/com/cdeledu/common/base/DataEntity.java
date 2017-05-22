package com.cdeledu.common.base;

import java.util.Date;

/**
 * @类描述: 数据Entity类
 * @创建者: 皇族灬战狼
 * @创建时间: 2017年2月24日 上午10:54:55
 * @版本: V1.2
 * @since: JDK 1.7
 */
public abstract class DataEntity<T> extends BaseEntity<T> {
	private static final long serialVersionUID = 1L;
	// 最初创建者
	private Integer create;
	// 数据创建时间
	private Date createTime;
	// 最后修改人
	private Integer modifier;
	// 数据最后更新时间
	private Date updateTime;

	public Integer getCreate() {
		return create;
	}

	public void setCreate(Integer create) {
		this.create = create;
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
}
