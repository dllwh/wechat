package com.cdeledu.controller.system.monitor;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cdeledu.common.property.SystemHelper;
import com.cdeledu.controller.BaseController;
import com.cdeledu.core.shiro.token.ShiroHelper;
import com.cdeledu.util.apache.lang.DateUtilHelper;

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
		// 最大内存
		mv.addObject("JVMmaxMem", SystemHelper.JVMmaxMem());
		// 已用内存
		mv.addObject("JVMtotalMem", SystemHelper.JVMtotalMem());
		// 可用内存
		mv.addObject("JVMfreeMem", SystemHelper.JVMfreeMem());
		// 语言环境名
		mv.addObject("Language", Locale.getDefault().getDisplayName());
		RuntimeMXBean runtimeMXBean =  ManagementFactory.getRuntimeMXBean();
		// 程序运行时间
		long runningTime =  System.currentTimeMillis()-runtimeMXBean.getStartTime();
		mv.addObject("runningTime", DateUtilHelper.formatSeconds(runningTime/1000));
		mv.addObject("hostIP", SystemHelper.getHostIP());
		mv.addObject("SessionID",ShiroHelper.getSession().getId());
		mv.addObject("osName",SystemHelper.OS_NAME);
		mv.addObject("osVersion",SystemHelper.OS_VERSION);
		mv.addObject("osType",SystemHelper.SUN_DESKTOP);
		mv.addObject("currentTime",DateUtilHelper.getCurrentTime());
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
