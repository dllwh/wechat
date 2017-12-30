package com.cdeledu.controller.system.sysConfig;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cdeledu.common.base.AjaxJson;
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
	public AjaxJson add(String dictName, String dictValues) {
		AjaxJson result = new AjaxJson();
		return result;
	}

	@RequestMapping("edit")
	@SystemLog(desc = "编辑字典记录", opType = SysOpType.UPDATE, tableName = "sys_dict")
	public AjaxJson edit() {
		AjaxJson result = new AjaxJson();
		return result;
	}

	@RequestMapping("delete")
	@SystemLog(desc = "删除字典记录", opType = SysOpType.DEL, tableName = "sys_dict")
	public AjaxJson delete() {
		AjaxJson result = new AjaxJson();
		return result;
	}

	@RequestMapping("disable")
	@SystemLog(desc = "禁用字典记录", opType = SysOpType.UPDATE, tableName = "sys_dict")
	public AjaxJson disable() {
		AjaxJson result = new AjaxJson();
		return result;
	}

	@RequestMapping("enable")
	@SystemLog(desc = "启用字典记录", opType = SysOpType.UPDATE, tableName = "sys_dict")
	public AjaxJson enable(String dictName, String dictValues) {
		AjaxJson result = new AjaxJson();
		return result;
	}
}
