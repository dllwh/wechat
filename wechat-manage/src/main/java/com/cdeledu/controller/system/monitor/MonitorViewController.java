package com.cdeledu.controller.system.monitor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cdeledu.controller.BaseController;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 监控系统管理
 * @创建者: 皇族灬战狼
 * @联系方式: duleilewuhen@sina.com
 * @创建时间: 2017年3月5日 下午5:07:29
 * @版本: V1.0
 * @since: JDK 1.7
 */
@Controller
@RequestMapping("monitor")
public class MonitorViewController extends BaseController {
	private static final long serialVersionUID = 1L;

	/** ----------------------------------------------------- Fields start */
	/** ----------------------------------------------------- Fields end */

		/**
		 * @方法描述 : 
		 * @param request
		 * @param response
		 * @return
		 */
	@RequestMapping(value = "serverInfo")
	public ModelAndView serverInfo(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = this.getModelAndView();
		mv.setViewName("system/monitor/server/serverInfo");
		return mv;
	}

	@RequestMapping(value = "cache")
	public ModelAndView cacheInfo(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = this.getModelAndView();
		mv.setViewName("system/monitor/cache/cacheInit");
		return mv;
	}

	/** ----------------------------------------------- [私有方法] */
	/** ----------------------------------------------- [私有方法] */
}
