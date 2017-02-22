package com.cdeledu.controller.systemController.resourceController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cdeledu.common.model.AjaxJson;
import com.cdeledu.controller.BaseController;

/**
 * @类描述: 图标信息处理类
 * @创建者: 皇族灬战狼
 * @创建时间: 2016年9月22日 下午2:08:11
 * @版本: V1.0
 * @since: JDK 1.7
 */
@Controller
@RequestMapping("/IconController")
public class IconController  extends BaseController{
	/** ----------------------------------------------------- Fields start */
	private String message;// 操作提示语
	/** ----------------------------------------------------- Fields end */
	/**
	 * @方法描述: 图标列表页面跳转
	 * @创建者: 皇族灬战狼
	 * @创建时间: 2016年9月22日 下午2:16:53
	 * @return
	 */
	@RequestMapping(params = "init")
	public ModelAndView icon() {
		return new ModelAndView("system/icon/iconInit");
	}

	/**
	 * @方法描述: easyuiAJAX请求数据
	 * @创建者: 皇族灬战狼
	 * @创建时间: 2016年9月22日 下午2:16:40
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "getList")
	public void getList(HttpServletRequest request, HttpServletResponse response) {
	}

	/**
	 * @方法描述: 上传图标
	 * @创建者: 皇族灬战狼
	 * @创建时间: 2016年9月22日 下午2:18:32
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "saveIcon")
	@ResponseBody
	public AjaxJson saveIcon(HttpServletRequest request) throws Exception {
		AjaxJson result = new AjaxJson();
		message = "上传成功";
		result.setMsg(message);
		// 图标的css样式
		// String css = ".back{background:url('../images/back.png') no-repeat;}";
		return result;
	}

	/**
	 * @方法描述: 删除图标
	 * @创建者: 皇族灬战狼
	 * @创建时间: 2016年9月22日 下午2:25:30
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "delIcon")
	@ResponseBody
	public AjaxJson del(HttpServletRequest request) {
		AjaxJson result = new AjaxJson();
		// 删除图标时先检查该图标是否正在使用
		boolean isPermit = false;
		if(isPermit){
			
		}
		return result;
	}
}
