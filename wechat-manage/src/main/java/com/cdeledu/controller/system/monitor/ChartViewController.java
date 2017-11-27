package com.cdeledu.controller.system.monitor;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.cdeledu.controller.BaseController;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 图表
 * @创建者: 皇族灬战狼
 * @创建时间: 2017年11月21日 上午11:41:34
 * @版本: V1.0
 * @since: JDK 1.7
 */
@Controller
@RequestMapping("chartView")
public class ChartViewController extends BaseController {

	/** ----------------------------------------------------- Fields start */
	private static final long serialVersionUID = 1L;

	/** ----------------------------------------------------- Fields end */
	/**
	 * @方法描述:用户浏览器使用统计图
	 * @return
	 */
	@RequestMapping(params = "userBroswer")
	public ModelAndView userBroswer(@RequestParam(required = true) String reportType) {
		ModelAndView mv = this.getModelAndView();
		return mv;
	}

	/**
	 * @方法描述: 报表数据生成
	 */
	@RequestMapping(params = "getBroswerBar")
	public void getBroswerBar() {

	}
}
