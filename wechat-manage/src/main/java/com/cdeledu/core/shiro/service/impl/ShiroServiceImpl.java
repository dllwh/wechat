package com.cdeledu.core.shiro.service.impl;

import java.util.LinkedHashMap;
import java.util.Map;

import com.cdeledu.core.shiro.service.ShiroService;

public class ShiroServiceImpl implements ShiroService {
	/** ----------------------------------------------------- Fields start */
	/** ----------------------------------------------------- Fields end */
	@Override
	public Map<String, String> loadFilterChainDefinitions() {
		// 权限控制map.从数据库获取
		Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
		return filterChainDefinitionMap;
	}

	@Override
	public void updatePermission() {

	}

}
