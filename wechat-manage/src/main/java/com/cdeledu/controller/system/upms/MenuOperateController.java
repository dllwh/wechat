package com.cdeledu.controller.system.upms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cdeledu.common.base.AjaxJson;
import com.cdeledu.common.constants.MessageConstant;
import com.cdeledu.common.constants.SystemConstant.SysMenuType;
import com.cdeledu.common.constants.SystemConstant.SysOpType;
import com.cdeledu.controller.BaseController;
import com.cdeledu.core.annotation.SystemLog;
import com.cdeledu.model.rbac.SysMenu;
import com.cdeledu.service.sys.RoleService;
import com.cdeledu.service.sys.SysMenuService;

/**
 * @类描述: 菜单操作控制类
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建日期: 2016年4月24日 下午3:29:21
 * @版本: V2.2
 * @since: JDK 1.7
 */
@Controller
@RequestMapping("/sysMenuOperate")
public class MenuOperateController extends BaseController {
	/** ----------------------------------------------------- Fields start */
	private static final long serialVersionUID = 1L;
	@Autowired
	SysMenuService sysMenuService;
	@Autowired
	RoleService roleService;

	/** ----------------------------------------------------- Fields end */

	@ResponseBody
	@RequestMapping(value = "createMenu", method = RequestMethod.POST)
	@SystemLog(desc = "创建权限菜单", opType = SysOpType.INSERT, tableName = "sys_menu")
	public AjaxJson createMenu(ModelMap map, SysMenu menu) {
		AjaxJson ajaxJson = new AjaxJson();
		int resultCount = -1;
		try {
			if (menu.getType() == SysMenuType.BUTTON.getValue()) {
				SysMenu parentmMenu = new SysMenu();
				parentmMenu.setId(menu.getParentCode());
				if (sysMenuService.findOneForJdbc(parentmMenu).getType() == SysMenuType.MENU
						.getValue()) {
					resultCount = sysMenuService.insert(menu);
				} else {
					ajaxJson.setSuccess(false);
					ajaxJson.setMsg("禁止为菜单目录创建按钮权限");
				}
			} else {
				resultCount = sysMenuService.insert(menu);
			}
			if (resultCount == 1) {
				// 分配给超级管理员
				try {
					roleService.saveRoleAccess(1, menu.getId());
				} catch (Exception e) {
				}
			}
		} catch (Exception e) {
			ajaxJson.setSuccess(false);
			ajaxJson.setMsg(MessageConstant.MSG_OPERATION_SUCCESS);
		}
		return ajaxJson;
	}

	@ResponseBody
	@RequestMapping(value = "saveMenu")
	@SystemLog(desc = "更新权限菜单", opType = SysOpType.INSERT, tableName = "sys_menu")
	public AjaxJson saveMenu(SysMenu sysMenu) {
		AjaxJson ajaxJson = new AjaxJson();
		try {
			SysMenu menu = sysMenuService.findOneById(sysMenu.getId());
			if (menu != null && menu.getAllowEdit() == 1) {
				if (menu.getType() == sysMenu.getType()) {
					sysMenuService.update(sysMenu);
					ajaxJson.setMsg(MessageConstant.MSG_OPERATION_SUCCESS);
				} else {
					if (!sysMenuService.hasChildren(sysMenu.getId())) {
						sysMenuService.update(sysMenu);
						ajaxJson.setMsg(MessageConstant.MSG_OPERATION_SUCCESS);
					} else {
						ajaxJson.setSuccess(false);
						ajaxJson.setMsg(MessageConstant.MSG_HAS_CHILD);
					}
				}
			} else {
				ajaxJson.setSuccess(false);
				ajaxJson.setMsg("不允许进行更新操作");
			}
		} catch (Exception e) {
			ajaxJson.setMsg(MessageConstant.MSG_OPERATION_SUCCESS);
			ajaxJson.setSuccess(false);
		}
		return ajaxJson;
	}

	@ResponseBody
	@RequestMapping(value = "del")
	@SystemLog(desc = "删除权限菜单", opType = SysOpType.DEL, tableName = "sys_menu,sys_role_menu")
	public AjaxJson delMenu(SysMenu sysMenu) {
		AjaxJson ajaxJson = new AjaxJson();
		try {
			Integer id = sysMenu.getId();
			SysMenu menu = sysMenuService.findOneById(id);
			if (menu != null && menu.getAllowDelete() == 1) {
				// 删除权限菜单时先删除权限菜单与角色之间关联表信息
				if (sysMenuService.hasRole(id)) {// 查询是否有赋予给角色
					ajaxJson.setSuccess(false);
					ajaxJson.setMsg(MessageConstant.MSG_HAS_CHILD);
				} else {
					if (sysMenuService.hasChildren(id)) { // 子节点
						ajaxJson.setSuccess(false);
						ajaxJson.setMsg(MessageConstant.MSG_HAS_CHILD);
					} else {
						sysMenuService.delete(id);
						ajaxJson.setMsg(MessageConstant.SUCCESS_DELETE_MESSAGE);
					}
				}
			} else {
				ajaxJson.setSuccess(false);
				ajaxJson.setMsg("不允许进行删除操作");
			}
		} catch (Exception e) {
			ajaxJson.setSuccess(false);
			ajaxJson.setResultCode(500);
			ajaxJson.setMsg(MessageConstant.FAILURE_DELETE_MESSAGE);
		}
		return ajaxJson;
	}

	@ResponseBody
	@RequestMapping(value = "updateMenu", params = "editDisable")
	@SystemLog(desc = "修改菜单的编辑标识", opType = SysOpType.UPDATE, tableName = "sys_menu")
	public AjaxJson editDisable(SysMenu sysMenu) throws Exception {
		AjaxJson ajaxJson = new AjaxJson();
		// sysMenu.setAllowEdit(0);
		sysMenuService.update(sysMenu);
		ajaxJson.setMsg(MessageConstant.SUCCESS_ENABLE_FALSE);
		return ajaxJson;
	}

	@ResponseBody
	@RequestMapping(value = "updateMenu", params = "delDisable")
	@SystemLog(desc = "修改菜单的删除标识", opType = SysOpType.UPDATE, tableName = "sys_menu")
	public AjaxJson delDisable(SysMenu sysMenu) throws Exception {
		AjaxJson ajaxJson = new AjaxJson();
		// sysMenu.setAllowDelete(0);
		sysMenuService.update(sysMenu);
		ajaxJson.setMsg(MessageConstant.SUCCESS_ENABLE_FALSE);
		return ajaxJson;
	}

	@ResponseBody
	@RequestMapping(value = "updateMenu", params = "visibleState")
	@SystemLog(desc = "修改菜单的是否可见/有效标识", opType = SysOpType.UPDATE, tableName = "sys_menu")
	public AjaxJson visibleState(SysMenu sysMenu) {
		AjaxJson ajaxJson = new AjaxJson();
		try {
			SysMenu menu = sysMenuService.findOneById(sysMenu.getId());
			if (menu != null && menu.getAllowEdit() == 1) {
				if (!sysMenuService.hasChildren(sysMenu.getId())) {
					sysMenuService.update(sysMenu);
				} else {
					ajaxJson.setMsg(MessageConstant.MSG_HAS_CHILD);
					ajaxJson.setSuccess(false);
				}

			}
		} catch (Exception e) {
			ajaxJson.setSuccess(false);
			ajaxJson.setMsg(MessageConstant.MSG_OPERATION_FAILED);
		}
		return ajaxJson;
	}
}
