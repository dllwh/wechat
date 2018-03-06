package com.cdeledu.controller.system.upms;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cdeledu.common.plugs.easyui.TreeNode;
import com.cdeledu.controller.BaseController;
import com.cdeledu.model.rbac.SysMenu;
import com.cdeledu.service.sys.SysMenuService;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

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
	@Autowired
	SysMenuService sysMenuService;
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
	@RequestMapping(params = "getList")
	public Map<String, Object> list(ModelMap map,SysMenu sysMenu) {
		Map<String, Object> resultMap = Maps.newConcurrentMap();
		try {
			int count  = sysMenuService.getCountForJdbcParam(sysMenu);
			if(count > 0){
				resultMap.put("rows", sysMenuService.findForJdbcParam(sysMenu));
			}else {
				resultMap.put("rows", "");
			}
			resultMap.put("total", count);
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("rows", "");
			resultMap.put("total", 0);
		}
		return resultMap;

	}

	/**
	 * @方法描述:获取菜单列表(首页用)
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping(params = "menuTreeList")
	public List<TreeNode> menuTreeList(ModelMap map) {
		List<TreeNode> resultList = Lists.newArrayList();
		try {
			resultList =  sysMenuService.getMenuEasyUITree();
		} catch (Exception e) {
			
		}
		return resultList;
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
