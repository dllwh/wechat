package com.cdeledu.service.sys.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdeledu.common.constants.GlobalConstants;
import com.cdeledu.common.constants.SystemConstant.SysMenuType;
import com.cdeledu.dao.BaseDaoSupport;
import com.cdeledu.model.easyui.EasyUITreeNode;
import com.cdeledu.model.rbac.SysMenu;
import com.cdeledu.model.rbac.SysUser;
import com.cdeledu.service.sys.SysMenuService;
import com.cdeledu.util.WebUtilHelper;
import com.cdeledu.util.apache.collection.ListUtilHelper;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

@Service("sysMenuService")
@Transactional(readOnly = false)
@SuppressWarnings("unchecked")
public class SysMenuServiceImpl implements SysMenuService {
	@Autowired
	private BaseDaoSupport<?> baseDao;
	private final static String prefix = "com.cdeledu.dao.SysMenuMapper.";

	@Override
	public Integer insert(SysMenu record) throws Exception {
		String accessAddress = record.getMenuUrl();
		if (StringUtils.isNoneEmpty(accessAddress)) {
			if (accessAddress.startsWith("/")) {
				accessAddress = accessAddress.substring(1);
			}
		}
		record.setMenuUrl(accessAddress);
		record.setCreate(WebUtilHelper.getCurrentUserId());
		record.setModifier(WebUtilHelper.getCurrentUserId());
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
		record.setModifier(WebUtilHelper.getCurrentUserId());
		return baseDao.update(prefix + "updateByPrimaryKeySelective", record);
	}

	@Override
	public Integer batchDelete(List<Object> parameter) throws Exception {
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
	public boolean hasChildren(Integer id) throws Exception {
		return baseDao.getCountForJdbcParam(prefix + "countMenuChildren", id) > 0 ? true : false;
	}

	/**
	 * @方法描述: 根据用户ID查询操作按钮权限
	 * @return
	 */
	public List<String> getButtonPermsByUserId(SysUser sysUser) throws Exception {
		return (List<String>) baseDao.findListForJdbcParam(prefix + "getButtonPermsByUserId",
				sysUser);
	}
	
	@Override
	public List<SysMenu> getMenuPermsByParentCode(Integer parentId) throws Exception {
		return (List<SysMenu>) baseDao.findListForJdbcParam(prefix + "getMenuPermsByParentCode",
				parentId);
	}

	@Override
	public SysMenu findOneById(Integer id) throws Exception {
		SysMenu sysMenu = new SysMenu();
		sysMenu.setId(id);
		return findOneForJdbc(sysMenu);
	}

	@Override
	public boolean hasRole(Integer id) throws Exception {
		return baseDao.getCountForJdbcParam(prefix + "hasRole", id) > 0 ? true : false;
	}

	@Override
	public List<EasyUITreeNode> getMenuEasyUITree() throws Exception {
		List<EasyUITreeNode> results = null;
		try {
			results = getMenuEasyUITreeByParentId(-1);
			EasyUITreeNode rootNode = new EasyUITreeNode();
			rootNode.setId(-1);
			rootNode.setText("无");
			results.add(0, rootNode);
		} catch (Exception ex) {

		}
		return results;
	}

	@Override
	public List<Map<String, Object>> getMenuZTree(Integer roleId) throws Exception {
		List<Integer> roleList = (List<Integer>) baseDao
				.findListForJdbcParam(prefix + "getAllMenuIdByRole", roleId);
		return getMenuZTreeByParentId(-1, roleList);
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
				if (menu.getType() == SysMenuType.MENU.getValue()) {// 是菜单
					if (StringUtils.isNotBlank(menu.getMenuUrl())) {
						userMenuList.add(menu);
					}
				} else {
					userMenuList.add(menu);
				}
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
	
	private List<EasyUITreeNode> getMenuEasyUITreeByParentId(int parentId) {
		List<EasyUITreeNode> results = Lists.newArrayList();
		try {
			List<SysMenu> rootMenuList = getMenuByParentCode(parentId);
			EasyUITreeNode treeNode = null;
			for (SysMenu sysMenu : rootMenuList) {
				int treeId = sysMenu.getId();
				treeNode = new EasyUITreeNode();
				treeNode.setId(treeId);
				treeNode.setText(sysMenu.getMenuName());
				if (hasChildren(treeId)) {

					if (sysMenu.getType() == SysMenuType.CATALOG.getValue()) {
						treeNode.setState("closed");
						treeNode.setChildren(getMenuEasyUITreeByParentId(treeId));
					} else {
						treeNode.setState("open");
					}

				} else {
					treeNode.setState("open");
				}
				results.add(treeNode);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return results;
	}
	
	private List<Map<String, Object>> getMenuZTreeByParentId(int parentId, List<Integer> authIds) {
		List<Map<String, Object>> results = Lists.newArrayList();
		try {
			List<SysMenu> rootMenuList = getMenuPermsByParentCode(parentId);
			Map<String, Object> resultMap = null;
			Integer authId = null; 
			for (SysMenu sysMenu : rootMenuList) {
				authId = sysMenu.getId();
				resultMap = Maps.newConcurrentMap();
				resultMap.put("id", authId);
				resultMap.put("name", sysMenu.getMenuName());
				if (authIds.indexOf(authId) > -1) {
					resultMap.put("checked", true);
				}
				if (hasChildren(authId)) {
					resultMap.put("children", getMenuZTreeByParentId(authId, authIds));
				} else {
					resultMap.put("open", true);
				}
				
				results.add(resultMap);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return results;
	}
	/**
	 * ----------------------------------------------------- privateMethod end
	 */
}
