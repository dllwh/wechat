package com.cdeledu.model.system;

import com.cdeledu.common.base.DataEntity;

/**
 * @类描述: 数据字典
 * @创建者: 皇族灬战狼
 * @创建时间: 2016年10月28日 下午1:07:01
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class SysDict extends DataEntity<SysDict> {
	private static final long serialVersionUID = 1L;
	/** 父ID */
	private Integer parentId;
	/** 字典名称 */
	private String itemName;
	/** 字典值 */
	private String itemCode;

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

}
