package com.cdeledu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 系统页面视图
 * @创建者: 皇族灬战狼
 * @创建时间: 2017年11月17日 下午2:05:08
 * @版本: V1.0
 * @since: JDK 1.7
 */
@Controller
@RequestMapping(value = "sysPage")
public class SysPageController extends BaseController{
	private static final long serialVersionUID = 1L;
	/** ----------------------------------------------------- Fields start */

	@RequestMapping("{module}/{function}/{url}")
	public String page(@PathVariable("module") String module,
			@PathVariable("function") String function, @PathVariable("url") String url) {
		return module + "/" + function + "/" + url;
	}

	@RequestMapping("{module}/{url}")
	public String page(@PathVariable("module") String module, @PathVariable("url") String url) {
		return module + "/" + url;
	}

	

	@RequestMapping("{url}")
	public String page(@PathVariable("url") String url) {
		// ModelAndView view = this.getModelAndView();
		// view.setViewName(url);
		return url;
	}
	
	/** ----------------------------------------------------- Fields end */
}
