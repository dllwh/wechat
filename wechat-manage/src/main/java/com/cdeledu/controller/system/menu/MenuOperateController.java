package com.cdeledu.controller.system.menu;

import java.awt.Menu;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cdeledu.common.annotation.SysLog;
import com.cdeledu.common.base.AjaxJson;
import com.cdeledu.common.constants.MessageConstant;
import com.cdeledu.controller.BaseController;
import com.cdeledu.model.rbac.SysMenu;

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
	private static final long serialVersionUID = 1L;

	/** ----------------------------------------------------- Fields start */

	/** ----------------------------------------------------- Fields end */
	/**
	 * @方法:保存权限菜单
	 * @创建人:独泪了无痕
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "createMenu")
	@ResponseBody
	public AjaxJson createMenu(HttpServletRequest request, HttpServletResponse response,
			SysMenu menu) {
		AjaxJson ajaxJson = new AjaxJson();
		return ajaxJson;
	}

	/**
	 * @方法:保存权限菜单
	 * @创建人:独泪了无痕
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "saveMenu")
	@ResponseBody
	public AjaxJson saveMenu(HttpServletRequest request, HttpServletResponse response, Menu menu) {
		AjaxJson ajaxJson = new AjaxJson();
		return ajaxJson;
	}

	/**
	 * @方法:删除权限菜单
	 * @创建人:独泪了无痕
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "del")
	@ResponseBody
	@SysLog(operationType = "del", tableName = "sys_role_menu", value = "删除权限菜单")
	public AjaxJson delMenu(HttpServletRequest request, HttpServletResponse response,
			SysMenu menu) {
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

}
