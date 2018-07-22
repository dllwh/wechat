package com.cdeledu.controller.system.monitor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cdeledu.common.base.AjaxJson;
import com.cdeledu.common.base.LayuiResponse;
import com.cdeledu.common.constants.SystemConstant.SysOpType;
import com.cdeledu.controller.BaseController;
import com.cdeledu.core.annotation.SystemLog;
import com.cdeledu.core.schedule.ScheduleRunState;
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
	@RequestMapping(value = "")
	public ModelAndView index() {
		ModelAndView mv = this.getModelAndView();
		mv.setViewName("system/monitor/scheduled/taskInit");
		return mv;
	}

	/**
	 * @方法描述 : 定时任务列表
	 */
	@ResponseBody
	@RequestMapping(params = "getList")
	public LayuiResponse getList(ScheduleJob scheduleJob) {
		LayuiResponse response = new LayuiResponse();
		try {
			response.setCount(scheduleJobService.getCountForJdbcParam(scheduleJob));
			response.setData(scheduleJobService.findForJdbcParam(scheduleJob));
		} catch (Exception e) {
			response.setMsg(e.getMessage());
			response.setCount(0);
		}
		return response;
	}

	@ResponseBody
	@RequestMapping(value = "createJob")
	@SystemLog(desc = "创建定时任务", opType = SysOpType.INSERT, tableName = "sys_schedule_job")
	public AjaxJson createJob(ScheduleJob scheduleJob) {
		AjaxJson ajaxJson = new AjaxJson();
		scheduleJobService.save(scheduleJob);
		return ajaxJson;
	}

	/**
	 * @方法:打开创建调度任务页面
	 * @创建人:独泪了无痕
	 * @return
	 */
	@RequestMapping(value = "createJob", params = "taskAdd")
	public ModelAndView taskAdd() {
		ModelAndView mv = this.getModelAndView();
		mv.setViewName("system/monitor/scheduled/taskAdd");
		return mv;
	}

	@ResponseBody
	@RequestMapping(value = "updateJob")
	@SystemLog(desc = "更新定时任务", opType = SysOpType.UPDATE, tableName = "sys_schedule_job")
	public AjaxJson updateJob(ScheduleJob scheduleJob) {
		AjaxJson ajaxJson = new AjaxJson();
		scheduleJobService.update(scheduleJob);
		return ajaxJson;
	}

	/**
	 * @方法:打开编辑调度任务页面
	 * @创建人:独泪了无痕
	 * @return
	 */
	@RequestMapping(value = "updateJob", params = "taskEdit")
	public ModelAndView taskEdit() {
		ModelAndView mv = this.getModelAndView();
		mv.setViewName("system/monitor/scheduled/taskEdit");
		return mv;
	}

	@ResponseBody
	@RequestMapping(value = "deleteJob")
	@SystemLog(desc = "删除定时任务", opType = SysOpType.DEL, tableName = "sys_schedule_job")
	public AjaxJson deleteJob(@RequestBody int jobId) {
		AjaxJson ajaxJson = new AjaxJson();
		scheduleJobService.delete(jobId);
		// ScheduleUtils.deleteScheduleJob(scheduler, beanName);
		return ajaxJson;
	}

	@ResponseBody
	@RequestMapping(value = "runJob")
	@SystemLog(desc = "立即执行任务", opType = SysOpType.UPDATE, tableName = "sys_schedule_job")
	public AjaxJson runJob(@RequestBody int jobId) {
		AjaxJson ajaxJson = new AjaxJson();
		scheduleJobService.updateStatus(jobId, ScheduleRunState.RUNNING.getValue());
		// ScheduleUtils.run(null, beanName);
		return ajaxJson;
	}

	@ResponseBody
	@RequestMapping(value = "pauseJob")
	@SystemLog(desc = "暂停定时任务", opType = SysOpType.UPDATE, tableName = "sys_schedule_job")
	public AjaxJson pauseJob(@RequestBody int jobId) {
		AjaxJson ajaxJson = new AjaxJson();
		scheduleJobService.updateStatus(jobId, ScheduleRunState.PAUSE.getValue());
		// ScheduleUtils.pauseJob(scheduler, beanName);
		return ajaxJson;
	}

	@ResponseBody
	@RequestMapping(value = "startJob")
	@SystemLog(desc = "启动一个定时任务", opType = SysOpType.UPDATE, tableName = "sys_schedule_job")
	public AjaxJson startJob(@RequestBody int jobId) {
		AjaxJson ajaxJson = new AjaxJson();
		scheduleJobService.updateStatus(jobId, ScheduleRunState.RUNNING.getValue());
		// ScheduleUtils.startTask(scheduler, scheduleJob);
		return ajaxJson;
	}

	@ResponseBody
	@RequestMapping(value = "startJobs")
	@SystemLog(desc = "启动所有定时任务", opType = SysOpType.UPDATE, tableName = "sys_schedule_job")
	public AjaxJson startJobs() {
		AjaxJson ajaxJson = new AjaxJson();
		// scheduleJobService.updateStatus(null,
		// ScheduleRunState.RUNNING.getValue());
		// ScheduleUtils.startJobs(scheduler);
		return ajaxJson;
	}

	@ResponseBody
	@RequestMapping(value = "resumeJob")
	@SystemLog(desc = "恢复定时任务", opType = SysOpType.UPDATE, tableName = "sys_schedule_job")
	public AjaxJson resumeJob(@RequestBody int jobId) {
		AjaxJson ajaxJson = new AjaxJson();
		scheduleJobService.updateStatus(jobId, ScheduleRunState.RUNNING.getValue());
		// ScheduleUtils.resumeJob(scheduler, beanName);
		return ajaxJson;
	}

	@ResponseBody
	@RequestMapping(value = "shutdownJob")
	@SystemLog(desc = "关闭定时任务", opType = SysOpType.UPDATE, tableName = "sys_schedule_job")
	public AjaxJson shutdownJob(@RequestBody int jobId) {
		AjaxJson ajaxJson = new AjaxJson();
		scheduleJobService.updateStatus(jobId, ScheduleRunState.STOP.getValue());
		// ScheduleUtils.deleteScheduleJob(scheduler, beanName);
		return ajaxJson;
	}

	@ResponseBody
	@RequestMapping(value = "shutdownJobs")
	@SystemLog(desc = "关闭所有定时任务", opType = SysOpType.UPDATE, tableName = "sys_schedule_job")
	public AjaxJson shutdownJobs(@RequestBody int jobId) {
		AjaxJson ajaxJson = new AjaxJson();
		scheduleJobService.updateStatus(jobId, ScheduleRunState.STOP.getValue());
		// ScheduleUtils.shutdownJobs(scheduler);
		return ajaxJson;
	}
}
