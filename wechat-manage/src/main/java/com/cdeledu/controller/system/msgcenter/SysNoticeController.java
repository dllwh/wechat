package com.cdeledu.controller.system.msgcenter;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cdeledu.common.base.ResponseBean;
import com.cdeledu.common.constants.SystemConstant.SysOpType;
import com.cdeledu.controller.BaseController;
import com.cdeledu.core.annotation.SystemLog;
import com.cdeledu.model.cms.SysNotice;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 通知中心
 * @创建者: 皇族灬战狼
 * @联系方式: duleilewuhen@sina.com
 * @创建时间: 2017年12月30日 下午4:36:12
 * @版本: V1.0
 * @since: JDK 1.7
 */
@Controller
@RequestMapping("sysNotice")
public class SysNoticeController extends BaseController {
	/** ----------------------------------------------------- Fields start */
	private static final long serialVersionUID = 1L;

	/** ----------------------------------------------------- Fields end */

	@RequestMapping("")
	public ModelAndView index() {
		ModelAndView mv = this.getModelAndView();
		mv.setViewName("platform/notice/init");
		return mv;
	}

	/**
	 * 获取通知列表
	 */
	@ResponseBody
	@RequestMapping(value = "list")
	public void list(@RequestBody SysNotice param) {
	}

	/**
	 * @方法:通知详情
	 * @创建人:独泪了无痕
	 * @param param
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "detail")
	public SysNotice detail(@RequestBody SysNotice param) {
		return null;
	}

	/**
	 * @方法:新增通知
	 * @创建人:独泪了无痕
	 * @param notice
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "create")
	public ResponseBean create(SysNotice notice) {
		ResponseBean responseBean = new ResponseBean();
		return responseBean;
	}

	@ResponseBody
	@RequestMapping(value = "update")
	@SystemLog(desc = "修改通知", opType = SysOpType.UPDATE, tableName = "sys_notice")
	public ResponseBean update(@RequestBody SysNotice param) {
		ResponseBean responseBean = new ResponseBean();
		return responseBean;
	}

	@ResponseBody
	@RequestMapping(value = "delete")
	@SystemLog(desc = "删除通知", opType = SysOpType.DEL, tableName = "sys_notice")
	public ResponseBean delete(@RequestBody SysNotice param) {
		ResponseBean responseBean  = new ResponseBean();
		return responseBean ;
	}
}
