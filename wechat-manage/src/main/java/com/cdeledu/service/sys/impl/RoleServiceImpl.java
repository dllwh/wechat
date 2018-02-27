package com.cdeledu.service.sys.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.cdeledu.common.base.BaseClass;
import com.cdeledu.dao.BaseDaoSupport;
import com.cdeledu.model.rbac.SysRole;
import com.cdeledu.model.rbac.SysRoleMenu;
import com.cdeledu.model.rbac.SysUser;
import com.cdeledu.model.rbac.SysUserRole;
import com.cdeledu.service.sys.RoleService;
import com.cdeledu.util.WebUtilHelper;

@Service("roleService")
@SuppressWarnings("unchecked")
public class RoleServiceImpl extends BaseClass implements RoleService {
	/** ----------------------------------------------------- Fields start */
	private static final long serialVersionUID = 1L;
	private final static String prefix = "com.cdeledu.dao.SysRoleMapper.";
	@Resource
	private BaseDaoSupport<?> baseDao;

	/** ----------------------------------------------------- Fields end */

	public Integer insert(SysRole record) throws Exception {
		record.setCreate(WebUtilHelper.getCurrentUserId());
		record.setModifier(WebUtilHelper.getCurrentUserId());
		return baseDao.insert(prefix + "insertSelective", record);
	}

	public Integer batchInsert(List<SysRole> parameter) throws Exception {
		return null;
	}

	public Integer delete(Object record) throws Exception {
		return baseDao.delete(prefix + "deleteByPrimaryKey", record);
	}

	public Integer batchDelete(List<Object> parameter) throws Exception {
		return null;
	}

	public Integer update(SysRole record) throws Exception {
		record.setModifier(WebUtilHelper.getCurrentUserId());
		return baseDao.update(prefix + "updateByPrimaryKey", record);
	}

	public Integer batchUpdate(List<SysRole> parameter) throws Exception {
		return null;
	}

	public List<SysRole> findForJdbcParam(SysRole record) throws Exception {
		return (List<SysRole>) baseDao.findListForJdbcParam(prefix + "findForJdbcParam", record);
	}

	public Integer getCountForJdbcParam(SysRole record) throws Exception {
		return baseDao.getCountForJdbcParam(prefix + "getCountForJdbcParam", record);
	}

	public SysRole findOneForJdbc(SysRole record) throws Exception {
		return (SysRole) baseDao.findOneForJdbcParam(prefix + "selectByPrimaryKey", record.getId());
	}

	@Override
	public boolean hasMenuByRole(int roleID) {
		try {
			return baseDao.getCountForJdbcParam(prefix + "hasMenuByRole", roleID) > 1 ? true
					: false;
		} catch (Exception e) {
			e.printStackTrace();
			return true;
		}
	}

	@Override
	public boolean hasUserByRole(int roleID) {
		try {
			return baseDao.getCountForJdbcParam(prefix + "hasUserByRole", roleID) > 1 ? true
					: false;
		} catch (Exception e) {
			e.printStackTrace();
			return true;
		}
	}

	@Override
	public boolean existRoleWithRoleCode(String roleCode) throws Exception {
		if (StringUtils.isBlank(roleCode)) {
			return true;
		}
		return baseDao.getCountForJdbcParam(prefix + "selectByRocode", roleCode) > 0 ? true : false;
	}

	public SysRole getRoleById(Integer roleId) throws Exception {
		SysRole sysRole = new SysRole();
		if (roleId != null) {
			sysRole.setId(roleId);
			return findOneForJdbc(sysRole);
		}
		return sysRole;
	}
	
	@Override
	public List<SysUser> getUserByRole(SysRole role) throws Exception {
		if(role != null && role.getId() != null){
			return (List<SysUser>) baseDao.findListForJdbcParam(prefix+"getUserByRole", role);
		}
		return null;
	}
	
	@Override
	public Integer countUserByRole(SysRole role) throws Exception {
		if(role != null && role.getId() != null){
			return (Integer) baseDao.getCountForJdbcParam(prefix+"countUserByRole", role);
		}
		return 0;
	}
	
	@Override
	public List<SysUser> getUserNoExistRoleByRole(SysRole role) throws Exception {
		if(role != null && role.getId() != null){
			return (List<SysUser>) baseDao.findListForJdbcParam(prefix+"getUserNoExistRoleByRole", role);
		}
		return null;
	}
	@Override
	public Integer countUserNoExistRoleByRole(SysRole role) throws Exception {
		if(role != null && role.getId() != null){
			return (Integer) baseDao.getCountForJdbcParam(prefix+"countUserNoExistRoleByRole", role);
		}
		return 0;
	}
	
	@Override
	public Integer saveRoleAccess(Integer roleId, Integer menuID) throws Exception {
		SysRoleMenu sysRoleMenu = new SysRoleMenu();
		sysRoleMenu.setRoleId(roleId);
		sysRoleMenu.setPowerId(menuID);
		return baseDao.insert(prefix + "saveRoleAccess", sysRoleMenu);
	}

	@Override
	public Integer delRoleAccess(Integer roleId) {
		try {
			return baseDao.delete(prefix + "delRoleAccess", roleId);
		} catch (Exception e) {
			return -1;
		}
	}

	@Override
	public boolean saveRoleUser(SysUserRole sysUserRole) {
		try {
			baseDao.insert(prefix + "saveRoleUser", sysUserRole);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delRoleUser(SysUserRole sysUserRole) {
		try {
			baseDao.delete(prefix + "delRoleUser", sysUserRole);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	@Override
	public List<SysUserRole> countRoleUser() throws Exception{
		return (List<SysUserRole>) baseDao.findListForJdbcParam(prefix+"countRoleUser");
	}
}
