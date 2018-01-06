package com.cdeledu.controller.system.upms.sysUser;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cdeledu.controller.BaseController;
import com.cdeledu.model.rbac.SysUser;
import com.google.common.collect.Maps;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 用户操作
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2017年10月22日 下午6:07:21
 * @版本: V1.0
 * @since: JDK 1.7
 */
@Controller
@RequestMapping("sysUserView")
public class SysUserViewController extends BaseController {
	/** ----------------------------------------------------- Fields start */
	private static final long serialVersionUID = 1L;

	/** ----------------------------------------------------- Fields end */
	/**
	 * @方法描述: 管理员列表
	 * @创建者: 皇族灬战狼
	 * @创建时间: 2016年9月27日 下午4:43:12
	 * @return
	 */
	@RequestMapping(value = "")
	public ModelAndView index(ModelMap modelMap) {
		ModelAndView mv = this.getModelAndView();
		mv.setViewName("system/sysUser/sysUserInit");
		return mv;
	}

	/**
	 * @方法描述: 管理员页面跳转
	 * @创建者: 皇族灬战狼
	 * @创建时间: 2016年9月27日 下午4:43:12
	 * @return
	 */
	@RequestMapping(value = "adminInfo")
	public ModelAndView adminInfo(ModelMap modelMap) {
		ModelAndView mv = this.getModelAndView();
		mv.setViewName("system/sysUser/adminInfo");
		return mv;
	}

	/**
	 * 
	 * @方法描述: easyuiAJAX请求数据
	 * @创建者: 皇族灬战狼
	 * @创建时间: 2016年9月27日 下午4:43:44
	 * @param request
	 * @param response
	 */
	@ResponseBody
	@RequestMapping(value = "getList")
	public Map<String, Object> getList(ModelMap modelMap, @RequestBody SysUser managerUser) {
		Map<String, Object> resultMap = Maps.newConcurrentMap();
		return resultMap;
	}

	/**
	 * @方法描述: 在线用户管理
	 * @return
	 */
	@RequestMapping(value = "online")
	public ModelAndView online(ModelMap modelMap) {
		ModelAndView mv = this.getModelAndView();
		mv.setViewName("system/sysUser/onlineUser");
		return mv;
	}

	/**
	 * 在线用户详情
	 * 
	 * @return
	 */
	@RequestMapping(value = "onlineDetails/{sessionId}", method = RequestMethod.GET)
	public ModelAndView onlineDetails(@PathVariable("sessionId") String sessionId) {
		ModelAndView mv = this.getModelAndView();
		return mv;
	}

	/**
	 * @方法描述: 用户角色权限分配
	 * @return
	 */
	@RequestMapping(value = "allocation")
	public ModelAndView allocation() {
		ModelAndView mv = this.getModelAndView();
		return mv;
	}

	/**
	 * @方法描述 : 获取授权的角色
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "selectRoleByUserId")
	public Map<String, Object> selectRoleByUserId() {
		Map<String, Object> resultMap = Maps.newConcurrentMap();
		return resultMap;
	}

	/**
	 * @方法描述 :获取未授权的角色
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "getNotMyRoles")
	public Map<String, Object> getNotMyRoles() {
		Map<String, Object> resultMap = Maps.newConcurrentMap();
		return resultMap;
	}
}
