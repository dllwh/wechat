package com.cdeledu.core.shiro.service;

import java.util.List;
import java.util.Map;

public interface ShiroService {
	/** 初始化权限 */
	public Map<String, String> loadFilterChainDefinitions();

	/** 重新加载权限 */
	public void updatePermission();

	/** 通过角色id获取权限列表 */
	List<String> findPermissionsByRoleId(Integer roleId);

	/** 根据角色id获取角色名称 */
	String findRoleNameByRoleId(Integer roleId);

	/** 检查指定角色 */
	boolean check(Object[] permissions);

	/** 检查全体角色 */
	boolean checkAll();
}
