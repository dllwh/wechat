package com.cdeledu.model.system;

import java.io.Serializable;

/**
 * @类描述: 上传以及下载图标实体类
 * @创建者: 皇族灬战狼
 * @创建时间: 2016年9月24日 上午10:53:34
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class IconResource implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String iconName;// 图标的名字
	private Short iconType; // 图标的类型
	private String iconPath;// 图标的路径
	private String iconClsName; // 图标CLASS名
	private String extend; // // 图标的扩展,主要是PNG
	private String iconDesc; // 资源描述

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getIconName() {
		return iconName;
	}

	public void setIconName(String iconName) {
		this.iconName = iconName;
	}

	public Short getIconType() {
		return iconType;
	}

	public void setIconType(Short iconType) {
		this.iconType = iconType;
	}

	public String getIconPath() {
		return iconPath;
	}

	public void setIconPath(String iconPath) {
		this.iconPath = iconPath;
	}

	public String getIconClsName() {
		return iconClsName;
	}

	public void setIconClsName(String iconClsName) {
		this.iconClsName = iconClsName;
	}

	public String getExtend() {
		return extend;
	}

	public void setExtend(String extend) {
		this.extend = extend;
	}

	public String getIconDesc() {
		return iconDesc;
	}

	public void setIconDesc(String iconDesc) {
		this.iconDesc = iconDesc;
	}
}
