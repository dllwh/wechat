package com.cdeledu.service.sys;

import com.cdeledu.common.base.BaseService;
import com.cdeledu.model.rbac.ManagerUser;
import com.cdeledu.model.rbac.ManagerUserRole;

public interface ManagerUserService extends BaseService<ManagerUser> {
	/** 检查用户是否存在 */
	public ManagerUser checkUserExits(ManagerUser managerUser) throws Exception;

	/** 获取用户的角色 */
	public ManagerUserRole getUserRole(ManagerUser managerUser) throws Exception;

	/** 保存管理员登录信息 */
	public void saveLoginInfo(ManagerUser managerUser) throws Exception;

	/** admin账户初始化 */
	public void pwdInit(ManagerUser managerUser) throws Exception;

	/** 保存用户-角色关联关系 */
	public void saveRoleUser(ManagerUserRole managerUserRole) throws Exception;

}
