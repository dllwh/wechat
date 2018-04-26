package com.cdeledu.controller.system.sysConfig;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cdeledu.common.base.AjaxJson;
import com.cdeledu.controller.BaseController;
import com.cdeledu.model.system.SysIcon;
import com.cdeledu.service.sys.SysIconService;
import com.cdeledu.util.webpage.BootstrapHelper;
import com.google.common.collect.Maps;

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
	private static final long serialVersionUID = 1L;
	/** ----------------------------------------------------- Fields start */
	private String message;// 操作提示语
	@Autowired
	private SysIconService sysIconService;
	/** ----------------------------------------------------- Fields end */
	/**
	 * @方法描述: 图标列表页面跳转
	 * @创建者: 皇族灬战狼
	 * @创建时间: 2016年9月22日 下午2:16:53
	 * @return
	 */
	@RequestMapping(params = "init")
	public ModelAndView init() {
		ModelAndView mv = this.getModelAndView();
		mv.setViewName("system/icon/iconInit");
		return mv;
	}
	
	@RequestMapping(value = "initIcon")
	@ResponseBody
	public AjaxJson initIcon() {
		AjaxJson result = new AjaxJson();

		try {
			result.setMsg("数据初始化成功");
			Map<String, String> filePathMap = Maps.newConcurrentMap();
			ClassLoader baseUrl = Thread.currentThread().getContextClassLoader();
			filePathMap.put("bootstrap", baseUrl.getResource("bootstrapIconInfo/bootstrap.css").getPath());
			filePathMap.put("fontAwesome", baseUrl.getResource("bootstrapIconInfo/font-awesome.css").getPath());
			filePathMap.put("simpleLine", baseUrl.getResource("bootstrapIconInfo/simple-line-icons.css").getPath());
			List<Map<String, String>> resultList=BootstrapHelper.getBootstrapIconInfo(filePathMap);
			SysIcon sysIcon;
			for (Map<String, String> map : resultList) {
				try {
					sysIcon = new SysIcon();
					sysIcon.setDisplayName(map.get("displayName"));
					sysIcon.setSourceType(map.get("sourceType"));
					if(sysIconService.findOneForJdbc(sysIcon) != null){
						sysIcon.setClassName(map.get("className"));
						sysIconService.insert(sysIcon);
					}
				} catch (Exception e) {
					continue;
				}
			}
			
		} catch (Exception e) {
			result.setMsg("数据初始化失败，原因"+e.getMessage());
			result.setSuccess(false);
		}
		return result;
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
