package com.cdeledu.service.sys;

import com.cdeledu.common.base.BaseService;
import com.cdeledu.model.rbac.SysUser;
import com.cdeledu.model.rbac.SysUserRole;

public interface ManagerUserService extends BaseService<SysUser> {
	/** 检查用户是否存在 */
	public SysUser checkUserExits(SysUser managerUser) throws Exception;

	/** 获取用户的角色 */
	public SysUserRole getUserRole(SysUser managerUser) throws Exception;

	/** 保存管理员登录信息 */
	public void saveLoginInfo(SysUser managerUser) throws Exception;

	/** admin账户初始化 */
	public void pwdInit(SysUser managerUser) throws Exception;

	/** 保存用户-角色关联关系 */
	public void saveRoleUser(SysUserRole managerUserRole) throws Exception;

}
