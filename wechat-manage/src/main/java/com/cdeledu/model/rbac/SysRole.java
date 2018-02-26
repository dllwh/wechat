package com.cdeledu.model.rbac;

import com.cdeledu.common.base.DataEntity;

/**
 * @类描述: 角色表实体类
 * @创建者: 独泪了无痕
 * @创建时间: 2015-8-22 上午01:21:17
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class SysRole extends DataEntity {
	private static final long serialVersionUID = 1L;
	// 角色名称
	private String roleName;
	// 角色编码
	private String roleCode;
	// 角色分类
	private Integer categoryCode;

	public SysRole() {
		super();
	}

	public SysRole(Integer id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public Integer getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(Integer categoryCode) {
		this.categoryCode = categoryCode;
	}
}
