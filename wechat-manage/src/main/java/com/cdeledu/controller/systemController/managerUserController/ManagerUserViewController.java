package com.cdeledu.controller.systemController.managerUserController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.cdeledu.controller.BaseController;
import com.cdeledu.model.rbac.ManagerUser;

@Controller
@RequestMapping("/managerUserView")
@SessionAttributes("managerUser")
public class ManagerUserViewController extends BaseController {
	/** ----------------------------------------------------- Fields start */

	/** ----------------------------------------------------- Fields end */
	/**
	 * @方法描述: 管理员页面跳转
	 * @创建者: 皇族灬战狼
	 * @创建时间: 2016年9月27日 下午4:43:12
	 * @return
	 */
	@RequestMapping(params = "init")
	public ModelAndView init() {
		return new ModelAndView("system/user/managerUserInit");
	}

	/**
	 * 
	 * @方法描述: easyuiAJAX请求数据
	 * @创建者: 皇族灬战狼
	 * @创建时间: 2016年9月27日 下午4:43:44
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "getList")
	public void getList(HttpServletRequest request, HttpServletResponse response, ModelMap model,
			ManagerUser managerUser) {

	}
}
