package com.cdeledu.controller.system;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cdeledu.controller.BaseController;

@Controller
@RequestMapping("/system")
public class SystemController extends BaseController {
	private static final long serialVersionUID = 1L;

	/**
	 * @方法描述: 浏览器配置检查
	 * @创建者: 皇族灬战狼
	 * @创建时间: 2016年6月13日 下午6:08:10
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "checkBrowser")
	@ResponseBody
	public ModelAndView browser(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = this.getModelAndView();
		mv.setViewName("system/browser/checkBrowser");
		return mv;
	}
	
	@RequiresUser
	@RequiresAuthentication
	@RequestMapping(value = "info")
	@ResponseBody
	public ModelAndView info(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = this.getModelAndView();
		mv.setViewName("/system/info");
		return mv;
	}
}
