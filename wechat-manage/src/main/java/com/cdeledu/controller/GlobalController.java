package com.cdeledu.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cdeledu.common.base.AjaxJson;

/**
 * @类描述: 全局的控制器
 * @创建者: 皇族灬战狼
 * @创建时间: 2017年5月19日 上午9:22:14
 * @版本: V1.0
 * @since: JDK 1.7
 */
@Controller
@RequestMapping("/globalController")
public class GlobalController extends BaseController {
	/** ----------------------------------------------------- Fields start */
	private static final long serialVersionUID = 1L;

	/** ----------------------------------------------------- Fields end */
	/** ----------------------------------------------- [私有方法] */
	/** ----------------------------------------------- [私有方法] */
	/**
	 * @方法描述: 401 请求出错
	 * @param request
	 * @return
	 */
	@RequestMapping("401")
	public ModelAndView _401(HttpServletRequest request) {
		ModelAndView view = this.getModelAndView();
		view.setViewName("common/no/noAuth");
		return view;
	}

	/**
	 * @方法描述: 403 请求出错
	 * @param request
	 * @return
	 */
	@RequestMapping("403")
	public ModelAndView _403(HttpServletRequest request) {
		ModelAndView view = this.getModelAndView();
		view.setViewName("common/errorPage/403");
		return view;
	}

	/**
	 * 404错误
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("404")
	public ModelAndView _404(HttpServletRequest request) {
		ModelAndView view = this.getModelAndView();
		view.setViewName("common/errorPage/404");
		return view;
	}

	/**
	 * 405错误
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("405")
	public ModelAndView _405(HttpServletRequest request) {
		ModelAndView view = this.getModelAndView();
		view.setViewName("common/errorPage/405");
		return view;
	}

	/**
	 * @方法描述: 500
	 * @param request
	 * @return
	 */
	@RequestMapping("500")
	public ModelAndView _500(HttpServletRequest request) {
		ModelAndView view = this.getModelAndView();
		view.setViewName("common/errorPage/500");
		return view;
	}

	/**
	 * @方法描述: 503
	 * @param request
	 * @return
	 */
	@RequestMapping("503")
	public ModelAndView _503(HttpServletRequest request) {
		ModelAndView view = this.getModelAndView();
		view.setViewName("common/errorPage/503");
		return view;
	}

	/**
	 * 没有权限提示页面
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "unauthorized")
	public ModelAndView unauthorized(
			@RequestParam(value = "requestPath", required = false) String requestPath) {
		ModelAndView view = this.getModelAndView();
		AjaxJson reslutMsg = new AjaxJson();
		reslutMsg.setMsg("您没有【" + requestPath + "】权限，请联系管理员给您赋予相应权限！");
		reslutMsg.setSuccess(false);
		view.setViewName("common/no/noAuth");
		return view;
	}
}
