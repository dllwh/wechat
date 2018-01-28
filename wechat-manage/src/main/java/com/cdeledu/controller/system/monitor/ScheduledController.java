package com.cdeledu.controller.system.monitor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cdeledu.common.base.AjaxJson;
import com.cdeledu.controller.BaseController;
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
	@RequestMapping("/list")
	public void list() {

	}

	/**
	 * @方法描述 :定时任务信息
	 */
	@RequestMapping("/info/{jobId}")
	public void info() {

	}

	/**
	 * @方法描述 :保存定时任务
	 */
	@RequestMapping("save")
	public AjaxJson save(@RequestBody ScheduleJob scheduleJob) {
		AjaxJson ajaxJson = new AjaxJson();
		scheduleJobService.save(scheduleJob);
		return ajaxJson;
	}

	/**
	 * @方法描述 :修改定时任务
	 */
	@RequestMapping("update")
	public AjaxJson update(@RequestBody ScheduleJob scheduleJob) {
		AjaxJson ajaxJson = new AjaxJson();
		scheduleJobService.update(scheduleJob);
		return ajaxJson;
	}

	/**
	 * @方法描述 :删除定时任务
	 */
	@RequestMapping("delete")
	public AjaxJson delete(@RequestBody Long[] jobIds) {
		AjaxJson ajaxJson = new AjaxJson();
		scheduleJobService.deleteBatch(jobIds);
		return ajaxJson;
	}

	/**
	 * @方法描述 : 立即执行任务
	 */
	@RequestMapping("run")
	public AjaxJson run(@RequestBody Long[] jobIds) {
		AjaxJson ajaxJson = new AjaxJson();
		scheduleJobService.run(jobIds);
		return ajaxJson;
	}

	/**
	 * @方法描述 : 暂停定时任务
	 */
	@RequestMapping("pause")
	public AjaxJson pause(@RequestBody Long[] jobIds) {
		AjaxJson ajaxJson = new AjaxJson();
		scheduleJobService.pause(jobIds);
		return ajaxJson;
	}

	/**
	 * @方法描述 :恢复定时任务
	 */
	@RequestMapping("resume")
	public AjaxJson resume(@RequestBody Long[] jobIds) {
		AjaxJson ajaxJson = new AjaxJson();
		scheduleJobService.resume(jobIds);
		return ajaxJson;
	}
	
}
