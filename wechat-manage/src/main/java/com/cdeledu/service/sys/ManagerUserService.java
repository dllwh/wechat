package com.cdeledu.service.sys;

import java.util.List;

import com.cdeledu.common.base.BaseService;
import com.cdeledu.model.rbac.SysUser;
import com.cdeledu.model.rbac.SysUserRole;

public interface ManagerUserService extends BaseService<SysUser> {
	/** 检查用户是否存在 */
	SysUser checkUserExits(SysUser sysUser) throws Exception;

	SysUser checkUserExits(String userName, String passWord) throws Exception;

	/** 保存管理员登录信息 */
	void saveLoginInfo(SysUser sysUser) throws Exception;

	/** admin账户初始化 */
	void pwdInit(SysUser sysUser) throws Exception;

	/** 获取用户的角色 */
	List<SysUserRole> getUserRole(SysUser sysUser) throws Exception;

	/** 尚未分配的角色 */
	List<SysUserRole> GetNotMyRoles(Integer userCode) throws Exception;

	/** 保存用户-角色关联关系 */
	void saveRoleUser(SysUserRole sysUserRole) throws Exception;

	/** 删除用户-角色关联关系 */
	void deleteUserRole(SysUserRole sysUserRole) throws Exception;

	SysUser getUserByName(String userName) throws Exception;

}
