package com.cdeledu.controller.system.sysConfig;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.cdeledu.controller.BaseController;

/**
 * 
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 行政区域
 * @创建者: 皇族灬战狼
 * @创建时间: 2017年11月9日 上午9:25:06
 * @版本: V1.0
 * @since: JDK 1.7
 */
@Controller
@RequestMapping("sysAreaView")
public class SysAreaViewController extends BaseController {
	/** ----------------------------------------------------- Fields start */
	private static final long serialVersionUID = 1L;

	/** ----------------------------------------------------- Fields end */

	/**
	 * @方法:菜单权限列表页面跳转
	 * @创建人:独泪了无痕
	 * @return
	 */
	@RequestMapping("")
	public ModelAndView index(ModelMap map) {
		ModelAndView mv = this.getModelAndView();
		mv.setViewName("system/area/init");
		return mv;
	}

	/**
	 * @方法描述: 根据父级code查询子节点，子区域列表
	 * @param areaCode
	 * @return
	 */
	@RequestMapping("list")
	public void list() {
	}

	/**
	 * @方法描述: 根据父级code查询子节点，树形目录
	 * @param areaCode
	 * @return
	 */
	@RequestMapping("select")
	public List<Map<String, Object>> select(@RequestParam String areaCode) {
		return null;
	}
	/** ----------------------------------------------- [私有方法] */
	/** ----------------------------------------------- [私有方法] */

	/** ----------------------------------------------- [测试方法] */
	/** ----------------------------------------------- [测试方法] */
}
