package com.cdeledu.controller.system.monitor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cdeledu.controller.BaseController;
import com.cdeledu.model.system.ScheduleJobLog;
import com.cdeledu.service.sys.ScheduleJobLogService;

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
	@Autowired
	private ScheduleJobLogService scheduleJobLogService;
	/** ----------------------------------------------------- Fields end */

	/**
	 * @方法:定时任务日志首页
	 * @创建人:独泪了无痕
	 * @return
	 */
	@RequestMapping(value = { "index.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	public String index() {
		return "/quartz/log/index";
	}

	/**
	 * 定时任务日志列表
	 */
	@RequestMapping(value = { "list" }, method = { RequestMethod.GET, RequestMethod.POST })
	public void list(ScheduleJobLog scheduleJobLog) {
		try {
			scheduleJobLogService.findForJdbcParam(scheduleJobLog);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
