package com.cdeledu.model.system;

import com.cdeledu.common.base.BaseEntity;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 行政区域字典表
 * @创建者: 皇族灬战狼
 * @创建时间: 2017年12月11日 下午8:38:06
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class SysArea extends BaseEntity {

	private static final long serialVersionUID = 1L;
	/** 行政区划代码 */
	private String areaCode;
	/** 所辖行政区 */
	private String areaName;
	private Integer parentId;
	/** 行政区级别(类型 0：国家 1：省、直辖市 2：市 3：区 4：街道 、办事处 5：村委会、村) */
	private Integer areaLevel;
	/** 简称 */
	private String shortName;

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public Integer getAreaLevel() {
		return areaLevel;
	}

	public void setAreaLevel(Integer areaLevel) {
		this.areaLevel = areaLevel;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	@Override
	public String toString() {
		return super.toString() + "\n\r SysArea [areaCode=" + areaCode + ", areaName=" + areaName
				+ ", parentId=" + parentId + ", areaLevel=" + areaLevel + ", shortName=" + shortName
				+ "]";
	}
}
