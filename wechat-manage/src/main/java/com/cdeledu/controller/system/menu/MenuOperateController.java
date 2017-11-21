package com.cdeledu.controller.system.menu;

import java.awt.Menu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cdeledu.common.base.AjaxJson;
import com.cdeledu.common.constants.MessageConstant;
import com.cdeledu.common.constants.SystemConstant.SysOpType;
import com.cdeledu.controller.BaseController;
import com.cdeledu.core.annotation.SystemLog;
import com.cdeledu.model.rbac.SysMenu;
import com.cdeledu.service.sys.SysMenuService;

/**
 * @类描述: 菜单操作控制类
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建日期: 2016年4月24日 下午3:29:21
 * @版本: V2.2
 * @since: JDK 1.7
 */
@Controller
@RequestMapping("/menuOperate")
public class MenuOperateController extends BaseController {
	/** ----------------------------------------------------- Fields start */
	private static final long serialVersionUID = 1L;
	@Autowired
	SysMenuService sysMenuService;

	/** ----------------------------------------------------- Fields end */

	@ResponseBody
	@RequestMapping(value = "createMenu", method = RequestMethod.POST)
	@SystemLog(desc = "创建权限菜单", opType = SysOpType.INSERT, tableName = "sys_menu")
	public AjaxJson createMenu(ModelMap map, SysMenu menu) {
		AjaxJson ajaxJson = new AjaxJson();
		try {
			sysMenuService.insert(menu);
			ajaxJson.setSuccess(true);
		} catch (Exception e) {
			ajaxJson.setSuccess(false);
			ajaxJson.setMsg(MessageConstant.MSG_OPERATION_SUCCESS);
		}
		return ajaxJson;
	}

	@ResponseBody
	@RequestMapping(value = "saveMenu")
	@SystemLog(desc = "更新权限菜单", opType = SysOpType.INSERT, tableName = "sys_menu")
	public AjaxJson saveMenu(Menu menu) {
		AjaxJson ajaxJson = new AjaxJson();
		return ajaxJson;
	}

	/**
	 * @方法描述: 删除菜单，根据ID，但是删除权限的时候，需要查询是否有赋予给角色，如果有角色在使用，那么就不能删除。
	 * @param menu
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "del")
	public AjaxJson delMenu(SysMenu menu) {
		AjaxJson ajaxJson = new AjaxJson();
		// 删除权限菜单时先删除权限菜单与角色之间关联表信息
		Integer roleMenuCount = 0;
		if (roleMenuCount > 0) {
			ajaxJson.setMsg(MessageConstant.MSG_HAS_CHILD);
		} else {
			ajaxJson.setMsg(MessageConstant.MSG_OPERATION_SUCCESS);
		}
		return ajaxJson;
	}

	@ResponseBody
	@RequestMapping(value = "addMenuByRole")
	@SystemLog(desc = "创建角色的权限", opType = SysOpType.INSERT, tableName = "sys_role_menu")
	public AjaxJson addMenuByRole(int roleId, String ids) {
		AjaxJson ajaxJson = new AjaxJson();
		return ajaxJson;
	}

	@ResponseBody
	@RequestMapping(value = "updateMenuByRole")
	@SystemLog(desc = "更新角色的权限", opType = SysOpType.UPDATE, tableName = "sys_role_menu")
	public AjaxJson updateMenuByRole(int roleId, String ids) {
		AjaxJson ajaxJson = new AjaxJson();
		return ajaxJson;
	}

	@ResponseBody
	@RequestMapping(value = "clearMenuByRoleIds")
	@SystemLog(desc = "根据角色id清空权限", opType = SysOpType.DEL, tableName = "sys_role_menu")
	public AjaxJson clearPermissionByRoleIds(String roleIds) {
		AjaxJson ajaxJson = new AjaxJson();
		return ajaxJson;
	}
}
