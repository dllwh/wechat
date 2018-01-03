package com.cdeledu.controller.system.upms.sysMenu;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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
@RequestMapping("/menuView")
public class MenuViewController extends BaseController {
	/** ----------------------------------------------------- Fields start */
	private static final long serialVersionUID = 1L;

	/** ----------------------------------------------------- Fields end */
	/**
	 * @方法:菜单权限列表页面跳转
	 * @创建人:独泪了无痕
	 * @return
	 */
	@RequestMapping("")
	public ModelAndView index(ModelMap map) {
		ModelAndView mv = this.getModelAndView();
		mv.setViewName("system/sysMenu/menuInit");
		return mv;
	}

	@ResponseBody
	@RequestMapping(value = "list")
	public void list(ModelMap map) {

	}

	/**
	 * @方法描述:获取菜单列表(首页用)
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "menuTreeList")
	public void menuTreeList(ModelMap map) {

	}

	/**
	 * @方法描述:获取菜单列表(选择父级菜单用)
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "selectMenuTreeList")
	public void selectMenuTreeList(ModelMap map) {

	}

	/**
	 * @方法:获取角色列表
	 * @创建人:独泪了无痕
	 * @param menuId
	 */
	@ResponseBody
	@RequestMapping(value = "menuTreeListByRoleId")
	public void menuTreeListByRoleId(@PathVariable Integer menuId) {

	}
}
