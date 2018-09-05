package com.cdeledu.controller.system.sysConfig;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cdeledu.common.base.ResponseBean;
import com.cdeledu.common.constants.SystemConstant.SysOpType;
import com.cdeledu.controller.BaseController;
import com.cdeledu.core.annotation.SystemLog;

/**
 * @类描述: 数据字典控制类:数据操作层(对数据的增、删、改)
 * @创建者: 皇族灬战狼
 * @创建时间: 2016年3月12日 上午10:36:33
 * @版本: V1.0
 * @since: JDK 1.7
 */
@Controller
@RequestMapping(value = "dictOperate")
@Transactional(readOnly = false)
public class DictOperateController extends BaseController {
	private static final long serialVersionUID = 1L;

	@RequestMapping("add")
	@SystemLog(desc = "添加字典记录", opType = SysOpType.INSERT, tableName = "sys_dict")
	public ResponseBean add(String dictName, String dictValues) {
		ResponseBean responseBean = new ResponseBean();
		return responseBean;
	}

	@RequestMapping("edit")
	@SystemLog(desc = "编辑字典记录", opType = SysOpType.UPDATE, tableName = "sys_dict")
	public ResponseBean edit() {
		ResponseBean responseBean = new ResponseBean();
		return responseBean;
	}

	@RequestMapping("delete")
	@SystemLog(desc = "删除字典记录", opType = SysOpType.DEL, tableName = "sys_dict")
	public ResponseBean delete() {
		ResponseBean responseBean = new ResponseBean();
		return responseBean;
	}

	@RequestMapping("disable")
	@SystemLog(desc = "禁用字典记录", opType = SysOpType.UPDATE, tableName = "sys_dict")
	public ResponseBean disable() {
		ResponseBean responseBean = new ResponseBean();
		return responseBean;
	}

	@RequestMapping("enable")
	@SystemLog(desc = "启用字典记录", opType = SysOpType.UPDATE, tableName = "sys_dict")
	public ResponseBean enable(String dictName, String dictValues) {
		ResponseBean responseBean = new ResponseBean();
		return responseBean;
	}
}
