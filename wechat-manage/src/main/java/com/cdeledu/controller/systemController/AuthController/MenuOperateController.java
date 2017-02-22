package com.cdeledu.controller.systemController.AuthController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.cdeledu.common.constants.GlobalConstants;
import com.cdeledu.common.model.AjaxJson;
import com.cdeledu.model.rbac.AuthBean;
import com.cdeledu.service.SystemService;

/**
 * @类描述: 菜单操作控制类
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建日期: 2016年4月24日 下午3:29:21
 * @版本: V1.0
 * @since: JDK 1.7
 */
@Controller
@RequestMapping("/MenuOperate")
@SessionAttributes("managerUser")
public class MenuOperateController {
	/** ----------------------------------------------------- Fields start */
	// private static final Logger logger =
	// Logger.getLogger(MenuOperateController.class);
	@Autowired
	private SystemService systemService;
	private String message = null;

	/** ----------------------------------------------------- Fields end */
	/**
	 * @方法:保存权限菜单
	 * @创建人:独泪了无痕
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(params = "createMenu")
	@ResponseBody
	public AjaxJson createMenu(HttpServletRequest request, HttpServletResponse response,
			AuthBean authBean) {
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
	@RequestMapping(params = "saveMenu")
	@ResponseBody
	public AjaxJson saveMenu(HttpServletRequest request, HttpServletResponse response,
			AuthBean authBean) {
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
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson delMenu(HttpServletRequest request, HttpServletResponse response,
			AuthBean authBean) {
		AjaxJson ajaxJson = new AjaxJson();
		message = "权限: " + authBean.getMenuName() + "被删除成功";
		// 删除权限菜单时先删除权限菜单与角色之间关联表信息
		Integer roleMenuCount = 0;
		if (roleMenuCount > 0) {
			ajaxJson.setMsg("菜单已分配无法删除");
		} else {
			// 删除
			// 操作日志
			systemService.addLog(message, GlobalConstants.Log_Type_DEL,
					GlobalConstants.Log_Leavel_INFO);
		}
		return ajaxJson;
	}

}
