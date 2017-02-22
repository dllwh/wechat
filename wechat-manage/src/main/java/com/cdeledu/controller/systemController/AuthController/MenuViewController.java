package com.cdeledu.controller.systemController.AuthController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.cdeledu.controller.BaseController;

/**
 * @类描述: 菜单数据控制类
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建日期: 2016年4月24日 下午3:28:51
 * @版本: V1.0
 * @since: JDK 1.7
 */
@Controller
@RequestMapping("/MenuView")
@SessionAttributes("managerUser")
public class MenuViewController extends BaseController {
	/** ----------------------------------------------------- Fields start */

	/** ----------------------------------------------------- Fields end */
	/**
	 * @方法:菜单权限列表页面跳转
	 * @创建人:独泪了无痕
	 * @return
	 */
	@RequestMapping(params = "init")
	public ModelAndView init() {
		return new ModelAndView("system/menu/menuInit");
	}

	@RequestMapping(params = "menuList")
	public ModelAndView menuList() {
		return new ModelAndView("system/menu/menuInit");
	}
}
