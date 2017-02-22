package com.cdeledu.common.model;

import java.util.Date;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BaseModel extends PageModel {
	private static final long serialVersionUID = 1L;
	/** ----------------------------------------------------- Fields start */
	// 是否可见;1:可见,默认值;0:不可见
	private Integer isVisible = 0;
	// 是否有效;-1:删除;0:不可用,默认值;1:可用
	private Integer isEnabled = 0;
	// 是否允许编辑;1:允许,默认值;0:不允许
	private Integer allowEdit = 1;
	// 是否允许删除;1:允许删除,默认值,0:不允许删除
	private Integer allowDelete = 1;
	// 最初创建者
	private Integer create;
	// 数据创建时间
	private Date createTime;
	// 最后修改人
	private Integer modifier;
	// 数据最后更新时间
	private Date updateTime;

	// 排序,默认从1开始
	private Integer sequence = 1;

	// 备注、说明
	private String remark;

	/** ----------------------------------------------------- Fields end */
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

	public Integer getIsEnabled() {
		return isEnabled;
	}

	public void setIsEnabled(Integer isEnabled) {
		this.isEnabled = isEnabled;
	}

	public Integer getAllowEdit() {
		return allowEdit;
	}

	public void setAllowEdit(Integer allowEdit) {
		this.allowEdit = allowEdit;
	}

	public Integer getAllowDelete() {
		return allowDelete;
	}

	public void setAllowDelete(Integer allowDelete) {
		this.allowDelete = allowDelete;
	}

	public Integer getSequence() {
		return sequence;
	}

	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}

	public Integer getIsVisible() {
		return isVisible;
	}

	public void setIsVisible(Integer isVisible) {
		this.isVisible = isVisible;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}
