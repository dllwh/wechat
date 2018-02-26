package com.cdeledu.service.sys;

import java.util.List;
import java.util.Map;

import com.cdeledu.common.base.BaseService;
import com.cdeledu.model.easyui.EasyUITreeNode;
import com.cdeledu.model.rbac.SysMenu;
import com.cdeledu.model.rbac.SysUser;

public interface SysMenuService extends BaseService<SysMenu> {
	/** 获取用户的菜单 */
	List<SysMenu> getUserMenu(SysUser sysUser) throws Exception;

	/** 获取子菜单不包含 按钮 */
	List<SysMenu> getMenuByParentCode(Integer parentId) throws Exception;

	/** 获取子菜单以及按钮 */
	List<SysMenu> getMenuPermsByParentCode(Integer parentId) throws Exception;

	/** 根据用户ID查询权限菜单（permission） */
	List<String> getMenuPermsByUserId(SysUser sysUser) throws Exception;
	
	/** 根据用户ID查询权限按钮（permission） */
	List<String> getButtonPermsByUserId(SysUser sysUser) throws Exception;

	/** 是否有子菜单 */
	boolean hasChildren(Integer id) throws Exception;

	SysMenu findOneById(Integer id) throws Exception;

	/** 是否有角色 */
	boolean hasRole(Integer id) throws Exception;

	/** 获取全部菜单数据-EasyUITree */
	List<EasyUITreeNode> getMenuEasyUITree() throws Exception;

	/** 角色权限 */
	List<Map<String, Object>> getMenuZTree(Integer roleId) throws Exception;
}
