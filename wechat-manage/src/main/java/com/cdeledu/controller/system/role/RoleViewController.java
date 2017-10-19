package com.cdeledu.controller.system.role;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cdeledu.controller.BaseController;
import com.cdeledu.model.rbac.SysRole;

/**
 * @类描述: 角色数据
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建日期: 2016年4月4日 下午8:13:20
 * @版本: V1.0
 * @since: JDK 1.7
 */
@Controller
@RequestMapping("/roleView")
public class RoleViewController extends BaseController {
	private static final long serialVersionUID = 1L;

	/**
	 * @方法:角色列表页面跳转
	 * @创建人:独泪了无痕
	 * @return
	 */
	@RequestMapping(params = "init")
	public ModelAndView role() {
		ModelAndView mv = this.getModelAndView();
		mv.setViewName("system/sysmanRole/roleInit");
		return mv;
	}

	/**
	 * @方法描述: easyuiAJAX请求数据
	 * @创建者: 皇族灬战狼
	 * @创建时间: 2016年8月8日 下午3:15:19
	 * @param role
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "getList")
	@ResponseBody
	public void getList(SysRole role, HttpServletRequest request) {

	}
}
