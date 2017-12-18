package com.cdeledu.controller.system.monitor;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cdeledu.controller.BaseController;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 定时任务日志
 * @创建者: 皇族灬战狼
 * @创建时间: 2017年12月18日 上午11:01:22
 * @版本: V1.0
 * @since: JDK 1.7
 */
@Controller
@RequestMapping("scheduleLog")
public class ScheduleJobLogController extends BaseController {
	/** ----------------------------------------------------- Fields start */
	private static final long serialVersionUID = 1L;
	/** ----------------------------------------------------- Fields end */

	/** ----------------------------------------------- [公共方法] */
	/**
	 * 定时任务日志列表
	 */
	@RequestMapping("/list")
	public void list(){
		
	}
	/**
	 * 定时任务日志信息
	 */
	@RequestMapping("/info/{logId}")
	public void info(@PathVariable("logId") Long logId){
		
	}
	/** ----------------------------------------------- [公共方法] */

	/** ----------------------------------------------- [私有方法] */
	/** ----------------------------------------------- [私有方法] */

	/** ----------------------------------------------- [测试方法] */
	/** ----------------------------------------------- [测试方法] */
}
