package com.cdeledu.model.rbac;

import java.util.Date;

import com.cdeledu.common.model.BaseModel;

/**
 * @类描述: 菜单表实体类
 * @创建者: 独泪了无痕
 * @创建时间: 2015年8月22日 下午5:16:57
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class AuthBean extends BaseModel {

	private static final long serialVersionUID = 1L;
	// 菜单名称
	private String menuName;
	// 菜单链接地址
	private String menuUrl;
	// 父菜单编号
	private Integer parentCode;
	// 备注
	private String authDesc;
	// 是否可见(启用/禁用),不为空(0:false;1:true;默认是0)
	private Integer isVisible;
	// 是否启用(启用/禁用),不为空(0:false;1:true;默认是0)
	private Integer isEnable;
	// 菜单图标样式
	private String iconClass;
	// 级别
	private Integer level;
	// 排序
	private Integer menuSeq;
	// 创建人
	private Integer creater;
	// 创建时间
	private Date createTime;
	// 修改人
	private Integer modifier;
	// 更新时间
	private Date modifyTime;
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public String getMenuUrl() {
		return menuUrl;
	}
	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}
	public Integer getParentCode() {
		return parentCode;
	}
	public void setParentCode(Integer parentCode) {
		this.parentCode = parentCode;
	}
	public String getAuthDesc() {
		return authDesc;
	}
	public void setAuthDesc(String authDesc) {
		this.authDesc = authDesc;
	}
	public Integer getIsVisible() {
		return isVisible;
	}
	public void setIsVisible(Integer isVisible) {
		this.isVisible = isVisible;
	}
	public Integer getIsEnable() {
		return isEnable;
	}
	public void setIsEnable(Integer isEnable) {
		this.isEnable = isEnable;
	}
	public String getIconClass() {
		return iconClass;
	}
	public void setIconClass(String iconClass) {
		this.iconClass = iconClass;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	public Integer getMenuSeq() {
		return menuSeq;
	}
	public void setMenuSeq(Integer menuSeq) {
		this.menuSeq = menuSeq;
	}
	public Integer getCreater() {
		return creater;
	}
	public void setCreater(Integer creater) {
		this.creater = creater;
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
	public Date getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}
}
