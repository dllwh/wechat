package com.cdeledu.controller.system.sysConfig;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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
@RequestMapping(value = "dictView")
@Transactional(readOnly = true)
public class DictViewController extends BaseController {
	private static final long serialVersionUID = 1L;

	/**
	 * @方法描述: 类型字典列表页面跳转
	 * @创建者: 皇族灬战狼
	 * @创建时间: 2016年8月8日 下午3:30:15
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "")
	public ModelAndView index(HttpServletRequest request) {
		ModelAndView mv = this.getModelAndView();
		mv.setViewName("system/dict/index");
		return mv;
	}

	/**
	 * @方法:获取数据字典全部数据
	 * @创建人:独泪了无痕
	 * @return
	 */
	@ResponseBody
	@RequestMapping("list")
	public void list() {
	}
}
