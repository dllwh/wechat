package com.cdeledu.controller.system.msgcenter;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cdeledu.common.base.ResponseBean;
import com.cdeledu.common.constants.SystemConstant.SysOpType;
import com.cdeledu.controller.BaseController;
import com.cdeledu.core.annotation.SystemLog;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 邮件配置管理控制类
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2018年3月2日 下午7:58:32
 * @版本: V1.0
 * @since: JDK 1.7
 */
@Controller
@RequestMapping(value = "emailConfig")
public class SysEmailConfigController extends BaseController {
	/** ----------------------------------------------------- Fields start */
	private static final long serialVersionUID = 1L;

	/** ----------------------------------------------------- Fields end */
	@ResponseBody
	@RequestMapping(value = "create")
	@SystemLog(desc = "创建邮件配置", opType = SysOpType.UPDATE, tableName = "sys_email_config")
	public ResponseBean create() {
		ResponseBean responseBean = new ResponseBean();
		return responseBean;
	}

	@ResponseBody
	@RequestMapping(value = "update")
	@SystemLog(desc = "修改邮件配置", opType = SysOpType.UPDATE, tableName = "sys_email_config")
	public ResponseBean update() {
		ResponseBean responseBean = new ResponseBean();
		return responseBean;
	}

	@ResponseBody
	@RequestMapping(value = "delete")
	@SystemLog(desc = "删除邮件配置", opType = SysOpType.DEL, tableName = "sys_email_config")
	public ResponseBean delete() {
		ResponseBean responseBean = new ResponseBean();
		return responseBean;
	}
}
