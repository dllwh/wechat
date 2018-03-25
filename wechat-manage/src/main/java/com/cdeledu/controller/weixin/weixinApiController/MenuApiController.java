package com.cdeledu.controller.weixin.weixinApiController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cdeledu.common.base.AjaxJson;
import com.cdeledu.common.constants.SystemConstant.SysOpType;
import com.cdeledu.core.annotation.SystemLog;

/**
 * @类描述: 微信菜单API
 * @创建者: 皇族灬战狼
 * @创建时间: 2016年7月12日 下午3:58:24
 * @版本: V1.0
 * @since: JDK 1.7
 */
@Controller
@RequestMapping(value = "weixin/menuApi")
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
	@RequestMapping(value = "getMenu", method = { RequestMethod.GET, RequestMethod.POST })
	public void getMenu() {

	}

	@ResponseBody
	@SystemLog(desc="创建菜单",opType = SysOpType.INSERT,tableName="")
	@RequestMapping(value = "create", method = { RequestMethod.GET, RequestMethod.POST })
	public AjaxJson createMenu() {
		AjaxJson result = new AjaxJson();
		return result;
	}
	
	@ResponseBody
	@RequestMapping(value = "remove",method = {RequestMethod.DELETE})
	@SystemLog(desc="删除菜单",opType = SysOpType.DEL,tableName="")
	public AjaxJson remove() {
		AjaxJson result = new AjaxJson();
		return result;
	}
	
	@ResponseBody
	@RequestMapping(value = "update",method = {RequestMethod.DELETE})
	@SystemLog(desc="更新菜单",opType = SysOpType.UPDATE,tableName="")
	public void update() {
	}
}