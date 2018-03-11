package com.cdeledu.controller.system.monitor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cdeledu.common.base.AjaxJson;
import com.cdeledu.common.constants.SystemConstant.SysOpType;
import com.cdeledu.controller.BaseController;
import com.cdeledu.core.annotation.SystemLog;
import com.cdeledu.model.system.SysLoginLog;
import com.cdeledu.service.log.LoginLogService;
import com.cdeledu.util.WebUtilHelper;

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
@RequestMapping("sysLog")
public class SysLogController extends BaseController {
	/** ----------------------------------------------------- Fields start */
	private static final long serialVersionUID = 1L;
	@Autowired
	private LoginLogService loginLogService;

	/** ----------------------------------------------------- Fields end */
	/**
	 * @方法描述: 登录日志列表
	 * @return
	 */
	@RequestMapping(value = "loginLog")
	public String sysLoginLogIndex() {
		return "system/sysLog/logInit";
	}

	/**
	 * @方法:用户浏览器使用统计图
	 * @创建人:独泪了无痕
	 * @param request
	 * @param response
	 * @param reportType
	 *            统计图类型
	 * @return
	 */
	@RequestMapping(params = "userBroswer")
	public ModelAndView userBroswer(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "reportType", required = false) String reportType) {
		ModelAndView mv = this.getModelAndView();
		mv.addObject("reportType", reportType);
		mv.setViewName("system/log/userBroswer/userBroswerInit");
		return mv;
	}

	/**
	 * @方法描述: 登录日志列表
	 * @return
	 */
	@RequestMapping(value = "loginLog", params = "getList")
	public List<SysLoginLog> sysLoginLogList() {
		try {
			return loginLogService.findForJdbcParam(null);
		} catch (Exception e) {
			return null;
		}
	}

	@ResponseBody
	@RequestMapping(value = "loginLog/remove")
	@SystemLog(desc = "删除登录日志", opType = SysOpType.DEL, tableName = "sys_log_loning")
	public AjaxJson sysLoginLogRemove() {
		AjaxJson resultMsg = new AjaxJson();
		return resultMsg;
	}

	@ResponseBody
	@SystemLog(desc = "清空日志", opType = SysOpType.RESET, tableName = "sys_log_loning")
	@RequestMapping(value = "loginLog/reset")
	public AjaxJson sysLoginLogReset() {
		AjaxJson resultMsg = new AjaxJson();

		// 需要超级管理员才能删除
		if (WebUtilHelper.getCurrentUserId() == 1) {

		}
		return resultMsg;
	}

	/**
	 * @方法描述: 操作日志列表
	 * @return
	 */
	@RequestMapping(value = "opLog")
	public ModelAndView sysOperateLogIndex() {
		ModelAndView mv = this.getModelAndView();
		mv.setViewName("system/sysLog/opLog/opLogInit");
		return mv;
	}

	/**
	 * @方法描述: 操作日志列表
	 * @return
	 */
	@RequestMapping(value = "opLog", params = "list")
	public List<SysLoginLog> sysOperateLogList() {
		return null;
	}

	/**
	 * @方法描述: 删除操作日志
	 * @return
	 */
	@RequestMapping(value = "opLog/del")
	public List<SysLoginLog> sysOperateLogRemove() {
		return null;
	}

}
