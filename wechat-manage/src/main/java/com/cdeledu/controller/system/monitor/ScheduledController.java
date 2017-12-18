package com.cdeledu.controller.system.monitor;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cdeledu.controller.BaseController;

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
@RequestMapping("scheduled")
public class ScheduledController extends BaseController {
	/** ----------------------------------------------------- Fields start */
	private static final long serialVersionUID = 1L;

	/** ----------------------------------------------------- Fields end */
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
	@RequestMapping("/save")
	public void save() {

	}

	/**
	 * @方法描述 :修改定时任务
	 */
	@RequestMapping("/update")
	public void update() {

	}

	/**
	 * @方法描述 :删除定时任务
	 */
	@RequestMapping("/delete")
	public void delete() {

	}

	/**
	 * @方法描述 : 立即执行任务
	 */
	@RequestMapping("/run")
	public void run() {

	}

	/**
	 * @方法描述 : 暂停定时任务
	 */
	@RequestMapping("/pause")
	public void pause() {

	}

	/**
	 * @方法描述 :恢复定时任务
	 */
	@RequestMapping("/resume")
	public void resume() {

	}
	/** ----------------------------------------------- [私有方法] */
	/** ----------------------------------------------- [私有方法] */

	/** ----------------------------------------------- [测试方法] */
	/** ----------------------------------------------- [测试方法] */
}
