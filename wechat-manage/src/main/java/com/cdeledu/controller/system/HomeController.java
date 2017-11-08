package com.cdeledu.controller.system;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cdeledu.controller.BaseController;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance  as tomorrow newest starter!
 *
 * @类描述: 系管理统登录后首页控制
 * @创建者: 独泪了无痕
 * @创建时间: 2017年11月7日 上午8:16:20
 * @版本: V1.0
 * @since: JDK 1.7
 */
@Controller
@RequestMapping("/homeController")
public class HomeController extends BaseController{
	private static final long serialVersionUID = 1L;
	
	
	/**
	 * @方法:首页跳转
	 * @创建人:独泪了无痕
	 * @return
	 */
	@RequestMapping(value="home")
	public ModelAndView home() {
		ModelAndView mv = this.getModelAndView();
		mv.setViewName("main/home");
		return mv;
	}
	
}
