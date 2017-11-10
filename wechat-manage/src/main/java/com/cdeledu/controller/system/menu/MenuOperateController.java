package com.cdeledu.controller.system.menu;

import java.awt.Menu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.annotation.SysLog;
import com.cdeledu.common.base.AjaxJson;
import com.cdeledu.common.constants.MessageConstant;
import com.cdeledu.controller.BaseController;
import com.cdeledu.model.rbac.SysMenu;
import com.cdeledu.service.sys.SysMenuService;

/**
 * @类描述: 菜单操作控制类
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建日期: 2016年4月24日 下午3:29:21
 * @版本: V1.0
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
	/**
	 * @方法:保存权限菜单
	 * @创建人:独泪了无痕
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "createMenu", method = RequestMethod.POST)
	public AjaxJson createMenu(ModelMap map,SysMenu menu) {
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

	/**
	 * @方法:保存权限菜单
	 * @创建人:独泪了无痕
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "saveMenu")
	public AjaxJson saveMenu(Menu menu) {
		AjaxJson ajaxJson = new AjaxJson();
		return ajaxJson;
	}

	/**
	 * @方法描述: 删除菜单，根据ID，但是删除权限的时候，需要查询是否有赋予给角色，如果有角色在使用，那么就不能删除。
	 * @param menu
	 * @return
	 */
	@RequestMapping(value = "del")
	@ResponseBody
	@SysLog(operationType = "del", tableName = "sys_user", value = "删除权限菜单")
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

	/**
	 * @方法描述: 操作角色的权限
	 * @param roleId
	 * @param ids
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "addMenuByRole")
	public AjaxJson addMenuByRole(int roleId, String ids) {
		AjaxJson ajaxJson = new AjaxJson();
		return ajaxJson;
	}

	/**
	 * @方法描述: 根据角色id清空权限
	 * @param roleIds
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "clearMenuByRoleIds")
	public AjaxJson clearPermissionByRoleIds(String roleIds) {
		AjaxJson ajaxJson = new AjaxJson();
		return ajaxJson;
	}
}
