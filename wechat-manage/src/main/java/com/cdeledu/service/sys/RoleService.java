package com.cdeledu.service.sys;

import com.cdeledu.common.base.BaseService;
import com.cdeledu.model.rbac.SysRole;

public interface RoleService extends BaseService<SysRole> {
	/** 该角色下是否有菜单 */
	boolean hasMenuByRole(int roleID);

	/** 该角色下是用户 */
	boolean hasUserByRole(int roleID);

	/** 通过RoleCode查询Role是否存在,存在则返回true,否则false； */
	boolean existRoleWithRoleCode(String roleCode) throws Exception;

	/** 通过id 获取信息 */
	SysRole getRoleById(Integer roleId) throws Exception;
}
