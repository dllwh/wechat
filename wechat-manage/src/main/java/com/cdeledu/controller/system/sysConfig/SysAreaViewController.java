package com.cdeledu.controller.system.sysConfig;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cdeledu.common.plugs.bootstrap.ZtreeNode;
import com.cdeledu.controller.BaseController;
import com.cdeledu.model.system.SysArea;
import com.cdeledu.service.sys.SysAreaService;
import com.google.common.collect.Maps;

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
	@Autowired
	SysAreaService sysAreaService;    
	/** ----------------------------------------------------- Fields end */

	/**
	 * @方法:菜单权限列表页面跳转
	 * @创建人:独泪了无痕
	 * @return
	 */
	@RequestMapping("")
	public ModelAndView index(ModelMap map) {
		ModelAndView mv = this.getModelAndView();
		mv.setViewName("system/area/areaInit");
		return mv;
	}

	/**
	 * @方法描述: 列表
	 * @param areaCode
	 * @return
	 */
	@ResponseBody
	@RequestMapping(params="getList")
	public Map<String, Object> getList(SysArea sysArea) {
		Map<String, Object> resultMap = Maps.newHashMap();
		try {
			resultMap.put("rows", sysAreaService.findForJdbcParam(sysArea));
			resultMap.put("total", sysAreaService.getCountForJdbcParam(sysArea));
		} catch (Exception e) {
			resultMap.put("rows", null);
			resultMap.put("total",0);
		}
		return resultMap;
	}

	/**
	 * @方法描述: 根据父级code查询子节点，树形目录
	 * @param areaCode
	 * @return
	 */
	@ResponseBody
	@RequestMapping(params="getTreeList")
	public List<ZtreeNode> select(@RequestParam(name="areaCode",defaultValue="100000") int  parentAreaId) {
		return sysAreaService.getSysAreaTree(parentAreaId);
	}
}
