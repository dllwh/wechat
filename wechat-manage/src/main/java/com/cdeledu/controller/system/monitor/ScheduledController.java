package com.cdeledu.controller.system.monitor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cdeledu.common.base.AjaxJson;
import com.cdeledu.common.constants.SystemConstant.SysOpType;
import com.cdeledu.controller.BaseController;
import com.cdeledu.core.annotation.SystemLog;
import com.cdeledu.model.system.ScheduleJob;
import com.cdeledu.service.sys.ScheduleJobService;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 调度管理:主要针对的是定时任务
 * @创建者: 皇族灬战狼
 * @创建时间: 2017年12月1日 下午5:08:45
 * @版本: V1.0
 * @since: JDK 1.7
 */
@Controller
@RequestMapping("scheduledController")
public class ScheduledController extends BaseController {
	/** ----------------------------------------------------- Fields start */
	private static final long serialVersionUID = 1L;
	@Autowired
	private ScheduleJobService scheduleJobService;

	/** ----------------------------------------------------- Fields end */
	@RequestMapping("")
	public ModelAndView index() {
		ModelAndView mv = this.getModelAndView();
		mv.setViewName("system/monitor/scheduled/taskInit");
		return mv;
	}

	/**
	 * @方法描述 : 定时任务列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	public void list() {

	}

	/**
	 * @方法描述 :定时任务信息
	 */
	@ResponseBody
	@RequestMapping("info")
	public void info() {

	}

	@ResponseBody
	@RequestMapping("create")
	@SystemLog(desc = "创建定时任务", opType = SysOpType.INSERT, tableName = "sys_schedule_job")
	public AjaxJson create(@RequestBody ScheduleJob scheduleJob) {
		AjaxJson ajaxJson = new AjaxJson();
		scheduleJobService.save(scheduleJob);
		return ajaxJson;
	}

	@ResponseBody
	@RequestMapping("update")
	@SystemLog(desc = "修改定时任务", opType = SysOpType.UPDATE, tableName = "sys_schedule_job")
	public AjaxJson update(@RequestBody ScheduleJob scheduleJob) {
		AjaxJson ajaxJson = new AjaxJson();
		scheduleJobService.update(scheduleJob);
		return ajaxJson;
	}

	@ResponseBody
	@RequestMapping("delete")
	@SystemLog(desc = "删除定时任务", opType = SysOpType.DEL, tableName = "sys_schedule_job")
	public AjaxJson delete(@RequestBody Long[] jobIds) {
		AjaxJson ajaxJson = new AjaxJson();
		scheduleJobService.deleteBatch(jobIds);
		return ajaxJson;
	}

	@ResponseBody
	@RequestMapping("run")
	@SystemLog(desc = "创建定时任务", opType = SysOpType.UPDATE, tableName = "sys_schedule_job")
	public AjaxJson run(@RequestBody Long[] jobIds) {
		AjaxJson ajaxJson = new AjaxJson();
		scheduleJobService.run(jobIds);
		return ajaxJson;
	}

	@ResponseBody
	@RequestMapping("pause")
	@SystemLog(desc = "暂停定时任务", opType = SysOpType.UPDATE, tableName = "sys_schedule_job")
	public AjaxJson pause(@RequestBody Long[] jobIds) {
		AjaxJson ajaxJson = new AjaxJson();
		scheduleJobService.pause(jobIds);
		return ajaxJson;
	}

	@ResponseBody
	@RequestMapping("resume")
	@SystemLog(desc = "恢复定时任务", opType = SysOpType.UPDATE, tableName = "sys_schedule_job")
	public AjaxJson resume(@RequestBody Long[] jobIds) {
		AjaxJson ajaxJson = new AjaxJson();
		scheduleJobService.resume(jobIds);
		return ajaxJson;
	}

}
