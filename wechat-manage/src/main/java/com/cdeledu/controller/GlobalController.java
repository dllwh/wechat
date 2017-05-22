package com.cdeledu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @类描述: 全局的控制器
 * @创建者: 皇族灬战狼
 * @创建时间: 2017年5月19日 上午9:22:14
 * @版本: V1.0
 * @since: JDK 1.7
 */
@Controller
@RequestMapping("/global")
public class GlobalController extends BaseController {
	/** ----------------------------------------------------- Fields start */
	private static final long serialVersionUID = 1L;

	/** ----------------------------------------------------- Fields end */
	/** ----------------------------------------------- [私有方法] */
	/** ----------------------------------------------- [私有方法] */
	/**
	 * 跳转到404页面
	 */
	@RequestMapping(name = "/error")
	public String errorPage() {
		return "/404.html";
	}
}
