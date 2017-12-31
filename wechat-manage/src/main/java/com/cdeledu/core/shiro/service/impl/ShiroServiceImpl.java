package com.cdeledu.core.shiro.service.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

import com.cdeledu.core.shiro.service.ShiroService;
import com.cdeledu.core.shiro.token.ShiroHelper;
import com.cdeledu.model.rbac.SysUser;
import com.cdeledu.util.WebUtilHelper;

@Service("shiroService")
@DependsOn("springContextHolder")
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

	@Override
	public List<String> findPermissionsByRoleId(Integer roleId) {
		return null;
	}

	@Override
	public String findRoleNameByRoleId(Integer roleId) {
		return null;
	}

	@Override
	public boolean check(Object[] permissions) {
		SysUser user = ShiroHelper.getPrincipal();
		if (user == null) {
			return false;
		}
		String join = StringUtils.join(permissions, ShiroHelper.NAMES_DELIMETER);
		if (ShiroHelper.hasAnyRole(join)) {
			return true;
		}
		return false;
	}

	@Override
	public boolean checkAll() {
		SysUser user = ShiroHelper.getPrincipal();
		if (user == null) {
			return false;
		}
		String requestURI = ShiroHelper.getAccessAddress(WebUtilHelper.getHttpServletRequest());
		if (ShiroHelper.hasPermission(requestURI)) {
			return true;
		}
		return false;
	}
}
