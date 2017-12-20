package com.cdeledu.core.shiro.service;

import java.util.Map;

public interface ShiroService {
	/** 初始化权限 */
	public Map<String, String> loadFilterChainDefinitions();

	/** 重新加载权限 */
	public void updatePermission();
}
