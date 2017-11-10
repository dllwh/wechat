package com.cdeledu.controller.ucenter;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cdeledu.controller.BaseController;

/**
 * 
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 用户会员管理
 * @创建者: 皇族灬战狼
 * @创建时间: 2017年11月11日 上午12:18:29
 * @版本: V1.0
 * @since: JDK 1.7
 */
@Controller
@RequestMapping("memberView")
public class MemberViewController extends BaseController {

	private static final long serialVersionUID = 1L;

	/** ----------------------------------------------------- Fields start */
	/** ----------------------------------------------------- Fields end */

	/**
	 * @方法描述: 用户列表管理
	 * @return
	 */
	@RequestMapping(value = "list")
	public ModelAndView list(ModelMap modelMap) {
		ModelAndView mv = this.getModelAndView();
		return mv;
	}
	
	
	/** ----------------------------------------------- [私有方法] */
	/** ----------------------------------------------- [私有方法] */

	/** ----------------------------------------------- [测试方法] */
	/** ----------------------------------------------- [测试方法] */
}
