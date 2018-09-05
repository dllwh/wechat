package com.cdeledu.controller.system.sysConfig;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cdeledu.common.base.ResponseBean;
import com.cdeledu.controller.BaseController;

/**
 * 
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 行政区域
 * @创建者: 皇族灬战狼
 * @创建时间: 2017年11月9日 上午9:24:08
 * @版本: V1.0
 * @since: JDK 1.7
 */
@Controller
@RequestMapping("/sysAreaOperate/")
public class SysAreaOperatetController extends BaseController {

	/** ----------------------------------------------------- Fields start */
	private static final long serialVersionUID = 1L;

	/** ----------------------------------------------------- Fields end */

	@ResponseBody
	@RequestMapping("save")
	public ResponseBean save() {
		ResponseBean responseBean = new ResponseBean();
		return responseBean;
	}

	@ResponseBody
	@RequestMapping("update")
	public ResponseBean update() {
		ResponseBean responseBean = new ResponseBean();
		return responseBean;
	}

	@ResponseBody
	@RequestMapping("del")
	public ResponseBean del() {
		ResponseBean responseBean = new ResponseBean();
		return responseBean;
	}

}
