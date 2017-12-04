package com.cdeledu.common.base;

/**
 * @类描述: Entity支持类
 * @创建者: 皇族灬战狼
 * @创建时间: 2017年2月24日 上午11:25:43
 * @版本: V1.0
 * @since: JDK 1.7
 */
public abstract class BaseEntity<T> extends PageEntity<T> {

	private static final long serialVersionUID = 1L;
	/**
	 * 实体编号（唯一标识）
	 */
	protected Integer id;
	/**
	 * 是否可见;1:可见,默认值;0:不可见
	 */
	protected Integer isVisible = 1;
	/**
	 * 是否有效;-1:删除;0:不可用,默认值;1:可用
	 */
	protected Integer isEnabled = 1;
	/**
	 * 是否允许编辑;1:允许,默认值;0:不允许
	 */
	protected Integer allowEdit = 1;
	/**
	 * 是否允许删除;1:允许删除,默认值,0:不允许删除
	 */
	protected Integer allowDelete = 1;
	/**
	 * 排序,默认从1开始
	 */
	protected Integer sequence = 1;
	/**
	 * 备注、说明
	 */
	protected String remark;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIsVisible() {
		return isVisible;
	}

	public void setIsVisible(Integer isVisible) {
		this.isVisible = isVisible;
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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return super.toString() + "\n BaseEntity [id=" + id + ", isVisible=" + isVisible
				+ ", isEnabled=" + isEnabled + ", allowEdit=" + allowEdit + ", allowDelete="
				+ allowDelete + ", sequence=" + sequence + ", remark=" + remark + "]";
	}

}