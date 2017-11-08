package com.cdeledu.controller.system.role;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cdeledu.common.base.AjaxJson;
import com.cdeledu.common.constants.GlobalConstants;
import com.cdeledu.controller.BaseController;
import com.cdeledu.model.rbac.SysRole;
import com.cdeledu.model.rbac.SysUser;
import com.cdeledu.service.sys.SystemService;
import com.cdeledu.util.ShiroHelper;

/**
 * @类描述: 角色处理类
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建日期: 2016年4月4日 下午8:10:56
 * @版本: V1.0
 * @since: JDK 1.7
 */
@Controller
@RequestMapping("/roleOperate")
public class RoleOperateController extends BaseController {
	private static final long serialVersionUID = 1L;
	/** ----------------------------------------------------- Fields start */
	@Autowired
	private SystemService systemService;
	// private RoleService roleService;
	private String msg = null;

	/** ----------------------------------------------------- Fields end */

	/**
	 * @方法:删除角色权限
	 *            <ul>
	 *            <li>1.删除角色具有的权限、菜单</li>
	 *            <li>2.删除角色与用户的之间的关联</li>
	 *            </ul>
	 * @创建人:独泪了无痕
	 * @param role
	 */
	protected void delRoleFunction(SysRole role) {

	}

	/**
	 * @方法:角色录入
	 * @创建人:独泪了无痕
	 * @param request
	 * @param response
	 * @param role
	 * @return
	 */
	@RequestMapping(value = "saveRole")
	@ResponseBody
	public AjaxJson saveRole(HttpServletRequest request, HttpServletResponse response, SysRole role) {
		AjaxJson resultMsg = new AjaxJson();
		try {
			if (null != role) {
				if (StringUtils.isNotEmpty(String.valueOf(role.getId()))) {
					msg = "角色: " + role.getRoleName() + "被更新成功";
					systemService.addLog(msg, GlobalConstants.Log_Type_UPDATE,
							GlobalConstants.Log_Leavel_INFO);
				} else {
					msg = "角色: " + role.getRoleName() + "被添加成功";
					systemService.addLog(msg, GlobalConstants.Log_Type_INSERT,
							GlobalConstants.Log_Leavel_INFO);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultMsg.setSuccess(false);
			msg = "出现操作错误,其异常原因如下所示:" + e.getMessage();
		}
		resultMsg.setMsg(msg);
		return resultMsg;
	}

	/**
	 * @方法:删除角色
	 * @创建人:独泪了无痕
	 * @param request
	 * @param response
	 * @param role
	 * @return
	 */
	@RequestMapping(value = "delRole")
	@ResponseBody
	public AjaxJson delRole(SysRole role) {
		AjaxJson resultMsg = new AjaxJson();
		SysUser managerUser = ShiroHelper.getPrincipal();
		try {
			// 删除角色之前先删除角色权限关系
			delRoleFunction(role);

			msg = "角色: " + role.getRoleName() + "被【" + managerUser.getId() + "】删除成功";
			systemService.addLog(msg, GlobalConstants.Log_Type_DEL,
					GlobalConstants.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			resultMsg.setSuccess(false);
			msg = "角色: " + role.getRoleName() + "被【" + managerUser.getId() + "】删除事出现异常,其异常原因是" + e;
		}
		resultMsg.setMsg(msg);
		return resultMsg;
	}

}
