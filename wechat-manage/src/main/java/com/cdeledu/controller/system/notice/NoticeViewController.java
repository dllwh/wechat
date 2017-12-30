package com.cdeledu.controller.system.notice;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cdeledu.controller.BaseController;

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
@RequestMapping("noticeView")
public class NoticeViewController extends BaseController {

	/** ----------------------------------------------------- Fields start */
	private static final long serialVersionUID = 1L;

	/** ----------------------------------------------------- Fields end */

	/** ----------------------------------------------- [公共方法] */
	@RequestMapping("")
	public ModelAndView index() {
		ModelAndView mv = this.getModelAndView();
		mv.setViewName("platform/notice/init");
		return mv;
	}
	/** ----------------------------------------------- [公共方法] */

	/** ----------------------------------------------- [私有方法] */
	/** ----------------------------------------------- [私有方法] */

	/** ----------------------------------------------- [测试方法] */
	/** ----------------------------------------------- [测试方法] */
}
