package com.cdeledu.controller.system.monitor;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cdeledu.common.base.AjaxJson;
import com.cdeledu.controller.BaseController;
import com.cdeledu.model.system.SysLoginLog;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 系统日志
 * @创建者: 皇族灬战狼
 * @创建时间: 2017年11月9日 上午8:44:43
 * @版本: V1.0
 * @since: JDK 1.7
 */
@Controller
@RequestMapping("/sysLog")
public class SysLogController extends BaseController {
	/** ----------------------------------------------------- Fields start */
	private static final long serialVersionUID = 1L;

	/** ----------------------------------------------------- Fields end */
	/**
	 * @方法描述: 登录日志列表
	 * @return
	 */
	@RequestMapping(value = "/loginLog")
	public String sysLoginLogIndex() {
		return null;
	}

	/**
	 * @方法描述: 登录日志列表
	 * @return
	 */
	@RequestMapping(value = "/loginLog", params = "list")
	public List<SysLoginLog> sysLoginLogList() {
		return null;
	}

	/**
	 * @方法描述: 删除登录日志
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/loginLog/remove")
	public AjaxJson sysLoginLogRemove() {
		AjaxJson resultMsg = new AjaxJson();
		return resultMsg;
	}

	/**
	 * @方法描述: 清空日志 （需要超级管理员才能删除）
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/loginLog/reset")
	public AjaxJson sysLoginLogReset() {
		AjaxJson resultMsg = new AjaxJson();
		return resultMsg;
	}

	/**
	 * @方法描述: 操作日志列表
	 * @return
	 */
	@RequestMapping(value = "/opLog")
	public ModelAndView sysOperateLogIndex() {
		ModelAndView mv = this.getModelAndView();
		mv.setViewName("system/sysLog/opLog/opLogInit");
		return mv;
	}

	/**
	 * @方法描述: 操作日志列表
	 * @return
	 */
	@RequestMapping(value = "/opLog", params = "list")
	public List<SysLoginLog> sysOperateLogList() {
		return null;
	}

	/**
	 * @方法描述: 删除操作日志
	 * @return
	 */
	@RequestMapping("/opLog/del")
	public List<SysLoginLog> sysOperateLogRemove() {
		return null;
	}

}
