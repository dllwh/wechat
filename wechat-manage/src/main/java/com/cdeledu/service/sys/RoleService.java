package com.cdeledu.service.sys;

import java.util.List;

import com.cdeledu.common.base.BaseService;
import com.cdeledu.model.rbac.SysRole;
import com.cdeledu.model.rbac.SysUser;
import com.cdeledu.model.rbac.SysUserRole;

public interface RoleService extends BaseService<SysRole> {
	/** 该角色下是否有菜单 */
	boolean hasMenuByRole(int roleID);

	/** 该角色是否有用户 */
	boolean hasUserByRole(int roleID);

	/** 通过RoleCode查询Role是否存在,存在则返回true,否则false； */
	boolean existRoleWithRoleCode(String roleCode) throws Exception;

	/** 通过id 获取信息 */
	SysRole getRoleById(Integer roleId) throws Exception;

	/** 通过角色获取用户列表 */
	List<SysUser> getUserByRole(SysRole role) throws Exception;

	Integer countUserByRole(SysRole role) throws Exception;

	/** 没有拥有特定角色的用户列表 */
	List<SysUser> getUserNoExistRoleByRole(SysRole role) throws Exception;

	Integer countUserNoExistRoleByRole(SysRole role) throws Exception;

	/** 角色授权 */
	Integer saveRoleAccess(Integer roleId, Integer menuID) throws Exception;

	/** 删除权限 */
	Integer delRoleAccess(Integer roleId);

	/** 保存角色用户 */
	boolean saveRoleUser(SysUserRole sysUserRole);

	/** 删除角色用户 */
	boolean delRoleUser(SysUserRole sysUserRole);
}
