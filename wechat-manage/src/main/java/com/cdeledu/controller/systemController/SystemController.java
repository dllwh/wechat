package com.cdeledu.controller.systemController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cdeledu.controller.BaseController;

@Controller
@RequestMapping("/system")
public class SystemController extends BaseController {
	/**
	 * @方法描述: 浏览器配置检查
	 * @创建者: 皇族灬战狼
	 * @创建时间: 2016年6月13日 下午6:08:10
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(params = "checkBrowser")
	@ResponseBody
	public ModelAndView browser(HttpServletRequest request, HttpServletResponse response) {
		return new ModelAndView("system/browser/checkBrowser");
	}
}
