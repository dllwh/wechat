package com.cdeledu.service.sys;

import java.util.List;

import com.cdeledu.common.base.BaseService;
import com.cdeledu.model.rbac.SysMenu;
import com.cdeledu.model.rbac.SysUser;

public interface SysMenuService extends BaseService<SysMenu> {
	/** 获取用户的菜单 */
	List<SysMenu> getUserMenu(SysUser sysUser) throws Exception;

	/** 获取子菜单 */
	List<SysMenu> getMenuByParentCode(Integer parentId) throws Exception;

	/** 根据用户ID查询权限（permission） */
	List<String> getPermissionByUserId(SysUser sysUser) throws Exception;
}
