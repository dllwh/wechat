package com.cdeledu.controller.dictController;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cdeledu.controller.BaseController;

/**
 * @类描述: 数据字典控制类:数据获取层(对数据的查询)
 * @创建者: 皇族灬战狼
 * @创建时间: 2016年3月12日 上午10:36:54
 * @版本: V1.0
 * @since: JDK 1.7
 */
@Controller
@RequestMapping(value = "DictView")
public class DictViewController extends BaseController {
	/**
	 * @方法描述: 类型字典列表页面跳转
	 * @创建者: 皇族灬战狼
	 * @创建时间: 2016年8月8日 下午3:30:15
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "init")
	public ModelAndView druid(HttpServletRequest request) {
		return new ModelAndView("system/dict/dictInit");
	}
}
