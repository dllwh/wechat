package com.cdeledu.model.rbac;

import com.cdeledu.common.base.BaseEntity;

/**
 * @类描述: 角色与权限中间表实体类
 * @创建者: 独泪了无痕
 * @创建时间: 2015-8-22 上午01:28:19
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class RoleMenuInfo extends BaseEntity<RoleMenuInfo> {
	private static final long serialVersionUID = 1L;
	// 角色id
	private Integer roleId;
	// 权限id
	private Integer powerId;
	// 操作权限代码
	private String operation;

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Integer getPowerId() {
		return powerId;
	}

	public void setPowerId(Integer powerId) {
		this.powerId = powerId;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}
}
