package com.cdeledu.controller.system.upms.sysUser;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cdeledu.common.constants.SystemConstant.SysOpType;
import com.cdeledu.controller.BaseController;
import com.cdeledu.core.annotation.SystemLog;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 用户会话管理
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2018年3月2日 下午10:31:58
 * @版本: V1.0
 * @since: JDK 1.7
 */
@Controller
@RequestMapping(value = "session")
public class SysSessionController extends BaseController {
	/** ----------------------------------------------------- Fields start */
	private static final long serialVersionUID = 1L;

	/** ----------------------------------------------------- Fields end */
	@SystemLog(desc = "查询会话", opType = SysOpType.SELECT, tableName = "sys_session")
	@RequestMapping(value = "getList")
	public void getList() {

	}

	@SystemLog(desc = "删除会话", opType = SysOpType.SELECT, tableName = "sys_session")
	@RequestMapping(value = "delete")
	public void delete() {

	}
}
