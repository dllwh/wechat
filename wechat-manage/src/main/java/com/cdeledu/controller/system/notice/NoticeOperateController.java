package com.cdeledu.controller.system.notice;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cdeledu.common.base.AjaxJson;
import com.cdeledu.controller.BaseController;
import com.cdeledu.model.system.Notice;

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
@RequestMapping("noticeOperate")
public class NoticeOperateController extends BaseController {
	/** ----------------------------------------------------- Fields start */
	private static final long serialVersionUID = 1L;
	/** ----------------------------------------------------- Fields end */

	/** ----------------------------------------------- [公共方法] */
	/**
	 * @方法:新增通知
	 * @创建人:独泪了无痕
	 * @param notice
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "add")
	public AjaxJson add(Notice notice) {
		AjaxJson result = new AjaxJson();
		return result;
	}

	/**
	 * 
	 * @方法:修改通知
	 * @创建人:独泪了无痕
	 * @param noticeId
	 * @return
	 */
	@RequestMapping(value = "update")
	@ResponseBody
	public AjaxJson update(@RequestParam Integer noticeId) {
		AjaxJson result = new AjaxJson();
		return result;
	}

	/**
	 * 
	 * @方法:删除通知
	 * @创建人:独泪了无痕
	 * @param noticeId
	 * @return
	 */
	@RequestMapping(value = "delete")
	@ResponseBody
	public AjaxJson delete(@RequestParam Integer noticeId) {
		AjaxJson result = new AjaxJson();
		return result;
	}
	/** ----------------------------------------------- [公共方法] */

	/** ----------------------------------------------- [私有方法] */
	/** ----------------------------------------------- [私有方法] */

	/** ----------------------------------------------- [测试方法] */
	/** ----------------------------------------------- [测试方法] */
}
