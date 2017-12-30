package com.cdeledu.controller.ucenter;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cdeledu.controller.BaseController;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 用户中心
 * @创建者: 皇族灬战狼
 * @联系方式: duleilewuhen@sina.com
 * @创建时间: 2017年12月30日 下午4:29:01
 * @版本: V1.0
 * @since: JDK 1.7
 */
@Controller
@RequestMapping("memberView")
public class MemberOperateController extends BaseController {
	private static final long serialVersionUID = 1L;

	/** ----------------------------------------------------- Fields start */
	/** ----------------------------------------------------- Fields end */

	@RequestMapping("")
	public ModelAndView index() {
		ModelAndView mv = this.getModelAndView();
		mv.setViewName("ucenter/member/init");
		return mv;
	}
	/** ----------------------------------------------- [私有方法] */
	/** ----------------------------------------------- [私有方法] */

	/** ----------------------------------------------- [测试方法] */
	/** ----------------------------------------------- [测试方法] */
}
