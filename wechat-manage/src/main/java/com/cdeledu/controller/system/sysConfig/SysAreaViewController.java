package com.cdeledu.controller.system.sysConfig;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cdeledu.controller.BaseController;

/**
 * 
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 行政区域
 * @创建者: 皇族灬战狼
 * @创建时间: 2017年11月9日 上午9:25:06
 * @版本: V1.0
 * @since: JDK 1.7
 */
@Controller
@RequestMapping("sysAreaView")
public class SysAreaViewController extends BaseController {
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
		mv.setViewName("system/area/index");
		return mv;
	}
	
	/** ----------------------------------------------- [私有方法] */
	/** ----------------------------------------------- [私有方法] */

	/** ----------------------------------------------- [测试方法] */
	/** ----------------------------------------------- [测试方法] */
}
