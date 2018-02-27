package com.cdeledu.model.rbac;

import com.cdeledu.common.base.BaseEntity;

/**
 * @类描述: 用户{@link SysUser}角色{@link SysRole}实体类
 * @创建者: 独泪了无痕
 * @创建时间: 2015-8-22 上午01:20:34
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class SysUserRole extends BaseEntity {
	private static final long serialVersionUID = 1L;
	/** 管理员id */
	private Integer userId;
	/** 登录名 */
	private String userName;
	/** 用户个数 */
	private Integer userCount;
	/** 角色id */
	private Integer roleId;
	/** 角色名称 */
	private String roleName;
	/** 角色编码 */
	private String roleCode;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getUserCount() {
		return userCount;
	}

	public void setUserCount(Integer userCount) {
		this.userCount = userCount;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
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

}
