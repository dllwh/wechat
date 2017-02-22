package com.cdeledu.controller.weixinApiController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @类描述: 微信菜单API
 * @创建者: 皇族灬战狼
 * @创建时间: 2016年7月12日 下午3:58:24
 * @版本: V1.0
 * @since: JDK 1.7
 */
@Controller
@RequestMapping(value = "menuApi")
public class MenuApiController {
	/** ----------------------------------------------------- Fields start */
	/** ----------------------------------------------------- Fields end */

	/** ----------------------------------------------- [私有方法] */
	/** ----------------------------------------------- [私有方法] */
	/**
	 * @方法描述: 获取公众号菜单
	 * @创建者: 皇族灬战狼
	 * @创建时间: 2016年7月12日 下午4:01:38
	 */
	@RequestMapping(value = "getMenu")
	public void getMenu() {

	}

	/**
	 * @方法描述: 创建菜单
	 * @创建者: 皇族灬战狼
	 * @创建时间: 2016年7月12日 下午4:02:42
	 */
	@RequestMapping(value = "createMenu")
	public void createMenu() {

	}
}
