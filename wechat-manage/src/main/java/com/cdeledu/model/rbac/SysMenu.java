package com.cdeledu.model.rbac;

import com.cdeledu.common.base.DataEntity;

/**
 * @类描述: 菜单表实体类
 * @创建者: 独泪了无痕
 * @创建时间: 2015年8月22日 下午5:16:57
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class SysMenu extends DataEntity<SysMenu> {

	private static final long serialVersionUID = 1L;
	/** 菜单名称 */
	private String menuName;
	// 菜单链接地址
	private String menuUrl;
	/** 类型。1:目录，默认值；2：菜单；3：按钮； */
	private Integer type;
	/** 父菜单编号 */
	private Integer parentCode;
	/** 菜单图标样式 */
	private String iconClass;

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

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getParentCode() {
		return parentCode;
	}

	public void setParentCode(Integer parentCode) {
		this.parentCode = parentCode;
	}

	public String getIconClass() {
		return iconClass;
	}

	public void setIconClass(String iconClass) {
		this.iconClass = iconClass;
	}

	@Override
	public String toString() {
		return super.toString() + "SysMenu [menuName=" + menuName + ", menuUrl=" + menuUrl
				+ ", type=" + type + ", parentCode=" + parentCode + ", iconClass=" + iconClass
				+ "]";
	}

}
