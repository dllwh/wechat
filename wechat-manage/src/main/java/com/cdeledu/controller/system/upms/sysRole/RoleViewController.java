package com.cdeledu.controller.system.upms.sysRole;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cdeledu.common.constants.GlobalConstants;
import com.cdeledu.controller.BaseController;
import com.cdeledu.model.rbac.SysRole;
import com.cdeledu.model.rbac.SysUser;
import com.cdeledu.service.sys.RoleService;
import com.cdeledu.service.sys.SysMenuService;
import com.google.common.collect.Maps;

/**
 * @类描述: 角色数据
 * <pre>
 * 数据访问权限不在验证，即只要配置对应的访问权限，其数据访问权限除非特别配置，均不再验证。
 * </pre>
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建日期: 2016年4月4日 下午8:13:20
 * @版本: V2.0
 * @since: JDK 1.7
 */
@Controller
@RequestMapping("/roleView")
public class RoleViewController extends BaseController {
	private static final long serialVersionUID = 1L;
	@Autowired
	RoleService roleService;
	@Autowired
	SysMenuService sysMenuService;

	/**
	 * @方法:角色列表页面跳转
	 * @创建人:独泪了无痕
	 * @return
	 */
	@RequestMapping(value = "")
	public ModelAndView index(ModelMap modelMap) {
		ModelAndView mv = this.getModelAndView();
		mv.setViewName("system/sysRole/roleInit");
		return mv;
	}

	/**
	 * @方法描述: easyuiAJAX请求数据
	 * @创建者: 皇族灬战狼
	 * @创建时间: 2016年8月8日 下午3:15:19
	 * @param role
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(params = "getList")
	public Map<String, Object> getList(SysRole role, ModelMap modelMap) {
		Map<String, Object> resultMap = Maps.newConcurrentMap();
		try {
			resultMap.put("rows", roleService.findForJdbcParam(role));
			resultMap.put("total", roleService.getCountForJdbcParam(role));
		} catch (Exception e) {
			resultMap.put("rows", null);
			resultMap.put("total", 0);
		}
		return resultMap;
	}

	/**
	 * @方法描述 : 角色权限
	 * @return
	 */
	@RequestMapping(value = "roleAccessConfig", params = "roleMenu")
	public ModelAndView roleMenu(
			@RequestParam(name = "roleCode", defaultValue = "-1", required = true) int roleCode) {
		ModelAndView mv = this.getModelAndView();
		mv.addObject("roleCode", roleCode);
		mv.setViewName("system/sysRole/roleMenu");
		return mv;
	}

	/**
	 * @方法描述 : 角色权限列表数据
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "roleAccessConfig", params = "roleMenuList")
	public List<Map<String, Object>> roleMenuList(
			@RequestParam(name = "roleCode", defaultValue = "-1", required = true) int roleCode) {
		try {
			return sysMenuService.getMenuZTree(roleCode);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * @方法描述 : 角色用户
	 * @return
	 */
	@RequestMapping(value = "roleAccessConfig", params = "roleUser")
	public ModelAndView roleUser(
			@RequestParam(name = "roleCode", defaultValue = "-1", required = true) int roleCode) {
		ModelAndView mv = this.getModelAndView();
		try {
			mv.addObject("roleCode", roleCode);
			mv.addObject("roleName", roleService.getRoleById(roleCode).getRoleName());
			mv.setViewName("system/sysRole/roleUser");
		} catch (Exception e) {
			mv.setViewName(GlobalConstants.ERROR_PAGE_404);
		}
		
		return mv;
	}

	/**
	 * @方法描述 : 角色用户列表
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "roleAccessConfig", params = "roleUserList")
	public Map<String, Object> roleUserList(
			@RequestParam(name = "roleId", required = true, defaultValue = "-1") int roleId) {
		Map<String, Object> resultMap = Maps.newConcurrentMap();
		try {
			List<SysUser> sysUserList = roleService.getUserByRole(roleId);
			resultMap.put("rows", sysUserList);
			resultMap.put("total", sysUserList.size());
		} catch (Exception ex) {
			ex.printStackTrace();
			resultMap.put("rows", null);
			resultMap.put("total", 0);
		}
		return resultMap;
	}
}
