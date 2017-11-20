package com.cdeledu.service.impl.sys;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdeledu.common.constants.GlobalConstants;
import com.cdeledu.common.constants.SystemConstant.SysMenuType;
import com.cdeledu.dao.BaseDaoSupport;
import com.cdeledu.model.rbac.SysMenu;
import com.cdeledu.model.rbac.SysUser;
import com.cdeledu.service.sys.SysMenuService;
import com.cdeledu.util.apache.collection.ListUtilHelper;
import com.google.common.collect.Lists;

@Service("sysMenuService")
@Transactional(readOnly = false)
@SuppressWarnings("unchecked")
public class SysMenuServiceImpl implements SysMenuService {
	@Autowired
	private BaseDaoSupport<?> baseDao;
	private final static String prefix = "com.cdeledu.dao.SysMenuMapper.";

	@Override
	public Integer insert(SysMenu record) throws Exception {
		return baseDao.insert(prefix + "insertSelective", record);
	}

	@Override
	public Integer batchInsert(List<SysMenu> parameter) throws Exception {
		return null;
	}

	@Override
	public Integer delete(Object record) throws Exception {
		return baseDao.delete(prefix + "deleteByPrimaryKey", record);
	}

	@Override
	public Integer batchUpdate(List<SysMenu> parameter) throws Exception {
		return null;
	}

	@Override
	public Integer update(SysMenu record) throws Exception {
		return baseDao.update(prefix + "updateByPrimaryKeySelective", record);
	}

	@Override
	public Integer batchDelete(List<SysMenu> parameter) throws Exception {
		return null;
	}

	@Override
	public List<SysMenu> findForJdbcParam(SysMenu record) throws Exception {
		return (List<SysMenu>) baseDao.findListForJdbcParam(prefix + "findForJdbc", record);
	}

	@Override
	public Integer getCountForJdbcParam(SysMenu record) throws Exception {
		return baseDao.getCountForJdbcParam(prefix + "getCountForJdbcParam", record);
	}

	@Override
	public SysMenu findOneForJdbc(SysMenu record) throws Exception {
		return (SysMenu) baseDao.findOneForJdbcParam(prefix + "findOneForJdbc", record);
	}

	@Override
	public List<SysMenu> getUserMenu(SysUser sysUser) throws Exception {
		// 1. 获取用户下的所有菜单ID
		List<Integer> userMenuIdList = (List<Integer>) baseDao
				.findListForJdbcParam(prefix + "getAllMenuIdByUser", sysUser);
		return getAllMenuList(userMenuIdList);
	}

	/**
	 * @方法描述: 获取子菜单
	 * @param parentId
	 * @return
	 */
	public List<SysMenu> getMenuByParentCode(Integer parentId) throws Exception {
		return (List<SysMenu>) baseDao.findListForJdbcParam(prefix + "getMenuByParentCode",
				parentId);
	}

	/**
	 * @方法描述: 根据用户ID查询菜单权限（permission）
	 * @param sysUser
	 * @return
	 */
	public List<String> getMenuPermsByUserId(SysUser sysUser) throws Exception {
		return (List<String>) baseDao.findListForJdbcParam(prefix + "getPermissionByUserId",
				sysUser);
	}

	/**
	 * @方法描述: 是否有子菜单
	 * @return
	 */
	public boolean hasChildren(int id) throws Exception {
		return baseDao.getCountForJdbcParam(prefix + "countMenuChildren", id) > 0 ? true : false;
	}

	/**
	 * @方法描述: 根据用户ID查询操作按钮权限
	 * @return
	 */
	public List<String> getButtonPermsByUserId(SysUser sysUser) throws Exception {
		return (List<String>)baseDao.findListForJdbcParam(prefix+"getButtonPermsByUserId", sysUser);
	}

	/**
	 * ----------------------------------------------------- privateMethod start
	 */
	/**
	 * @方法描述: 获取所有菜单列表
	 * @param menuIdList
	 * @return
	 */
	private List<SysMenu> getAllMenuList(List<Integer> menuIdList) {
		try {
			// 2. 获取根菜单列表
			List<SysMenu> userMenuList = listParentId(-1, menuIdList);
			// 3. 递归获取子菜单
			List<SysMenu> resultList = getMenuTreeList(userMenuList, menuIdList);
			SysMenu homePage = new SysMenu();
			homePage.setOpen(false);
			homePage.setMenuUrl(GlobalConstants.getHomePageUrl());
			homePage.setMenuName("系统首页");
			homePage.setIconClass("icon-dashboard");
			resultList.add(0, homePage);
			return resultList;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private List<SysMenu> listParentId(Integer parentId, List<Integer> menuIdList) {
		List<SysMenu> rootMenuList = null;
		try {
			rootMenuList = getMenuByParentCode(parentId);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		if (menuIdList == null) {
			return rootMenuList;
		}

		List<SysMenu> userMenuList = Lists.newArrayList();
		for (SysMenu menu : rootMenuList) {
			if (menuIdList.contains(menu.getId())) {
				if(menu.getType() == SysMenuType.MENU.getValue()){// 是菜单
					if(StringUtils.isNotBlank(menu.getMenuUrl())){
						userMenuList.add(menu);
					}
				} else {
					userMenuList.add(menu);
				}
				// userMenuList.add(menu);
			}
		}
		return userMenuList;
	}

	private List<SysMenu> getMenuTreeList(List<SysMenu> menuList, List<Integer> menuIdList) {
		List<SysMenu> subMenuList = Lists.newArrayList();
		List<SysMenu> menuTreeList = null;
		List<SysMenu> rootMenuList = null;
		for (SysMenu entity : menuList) {
			if (entity.getType() == SysMenuType.CATALOG.getValue()) {// 目录
				rootMenuList = listParentId(entity.getId(), menuIdList);
				if (ListUtilHelper.isNotEmpty(rootMenuList)) {
					menuTreeList = getMenuTreeList(rootMenuList, menuIdList);
					if (ListUtilHelper.isNotEmpty(menuTreeList)) {
						entity.setChildrenList(menuTreeList);
						entity.setOpen(true);// 有子菜单
						subMenuList.add(entity);
					}
				}
			} else {
				subMenuList.add(entity);
			}

		}
		return subMenuList;
	}
	/**
	 * ----------------------------------------------------- privateMethod end
	 */
}
