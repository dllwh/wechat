package com.cdeledu.controller.system.menu;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cdeledu.controller.BaseController;
import com.cdeledu.model.rbac.SysMenu;
import com.google.common.collect.Lists;

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
	@RequestMapping(value = "index")
	public ModelAndView index(ModelMap map) {
		ModelAndView mv = this.getModelAndView();
		mv.setViewName("system/sysMenu/menuInit");
		return mv;
	}

	
	/**
	 * @方法描述: 根据角色ID查询权限
	 * @param roleId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="selectPermissionById")
	public List<SysMenu> selectPermissionById(int roleId){
		List<SysMenu> sysMenuList = Lists.newArrayList();
		return sysMenuList;
	}

}
