package com.cdeledu.controller.system.monitor;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
	@RequestMapping("/loginLog/index")
	public String sysLoginLogIndex() {
		return null;
	}
	/**
	 * @方法描述: 登录日志列表
	 * @return
	 */
	@RequestMapping("/loginLog/list")
	public List<SysLoginLog> sysLoginLogList() {
		return null;
	}

	/**
	 * @方法描述: 删除登录日志
	 * @return
	 */
	@RequestMapping("/loginLog/del")
	public List<SysLoginLog> sysLoginLogRemove() {
		return null;
	}

	
	/**
	 * @方法描述: 操作日志列表
	 * @return
	 */
	@RequestMapping("/opLog/index")
	public List<SysLoginLog> sysOperateLogIndex() {
		return null;
	}
	
	/**
	 * @方法描述: 操作日志列表
	 * @return
	 */
	@RequestMapping("/opLog/list")
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
