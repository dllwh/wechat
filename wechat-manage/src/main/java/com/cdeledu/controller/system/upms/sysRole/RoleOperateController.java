package com.cdeledu.controller.system.upms.sysRole;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cdeledu.common.base.AjaxJson;
import com.cdeledu.common.constants.MessageConstant;
import com.cdeledu.common.constants.SystemConstant.SysOpType;
import com.cdeledu.controller.BaseController;
import com.cdeledu.core.annotation.SystemLog;
import com.cdeledu.model.rbac.SysRole;
import com.cdeledu.service.sys.RoleService;

/**
 * @类描述: 角色处理类
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建日期: 2016年4月4日 下午8:10:56
 * @版本: V2.0
 * @since: JDK 1.7
 */
@Controller
@RequestMapping("/roleOperate")
public class RoleOperateController extends BaseController {
	private static final long serialVersionUID = 1L;
	/** ----------------------------------------------------- Fields start */
	private String msg = null;
	@Autowired
	RoleService roleService;

	/** ----------------------------------------------------- Fields end */

	/**
	 * @方法描述 : 角色创建的时候，必须要自动添加到管理员下，保证管理员的权限是最大最全的
	 * @param role
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "addRole", method = RequestMethod.POST)
	@SystemLog(desc = "角色添加", opType = SysOpType.INSERT, tableName = "sys_role")
	public AjaxJson addRole(SysRole role) {
		AjaxJson resultMsg = new AjaxJson();
		try {
			roleService.insert(role);
		} catch (Exception e) {
			resultMsg.setSuccess(false);
			resultMsg.setMsg("添加失败，请刷新后再试！");
			resultMsg.setResultCode(500);
		}
		return resultMsg;
	}

	@ResponseBody
	@RequestMapping(value = "saveRole")
	@SystemLog(desc = "角色更新", opType = SysOpType.UPDATE, tableName = "sys_role")
	public AjaxJson saveRole(HttpServletRequest request, HttpServletResponse response,
			SysRole role) {
		AjaxJson resultMsg = new AjaxJson();
		try {
			if (null != role) {
				if (StringUtils.isNotEmpty(String.valueOf(role.getId()))) {
					msg = "角色: " + role.getRoleName() + "被更新成功";
				} else {
					msg = "角色: " + role.getRoleName() + "被添加成功";
				}
			}
		} catch (Exception e) {
			resultMsg.setSuccess(false);
			resultMsg.setResultCode(500);
			msg = MessageConstant.MSG_OPERATION_FAILED;
		}
		resultMsg.setMsg(msg);
		return resultMsg;
	}

	/**
	 * @方法描述: 删除角色，根据ID，但是删除角色的时候，需要查询是否有赋予给用户，如果有用户在使用，那么就不能删除
	 * @param role
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "delRole")
	@SystemLog(desc = "角色删除", opType = SysOpType.DEL, tableName = "sys_role")
	public AjaxJson delRole(SysRole role) {
		AjaxJson resultMsg = new AjaxJson();
		try {
			// 删除角色之前先删除角色权限关系
			delRoleFunction(role);
		} catch (Exception e) {
			resultMsg.setSuccess(false);
			resultMsg.setResultCode(500);
			msg = MessageConstant.MSG_OPERATION_FAILED;
		}
		resultMsg.setMsg(msg);
		return resultMsg;
	}

	@ResponseBody
	@RequestMapping(value = "addMenuByRole")
	@SystemLog(desc = "创建角色的权限", opType = SysOpType.INSERT, tableName = "sys_role_menu")
	public AjaxJson addMenuByRole(int roleId, String ids) {
		AjaxJson ajaxJson = new AjaxJson();
		return ajaxJson;
	}

	@ResponseBody
	@RequestMapping(value = "updateMenuByRole")
	@SystemLog(desc = "更新角色的权限", opType = SysOpType.UPDATE, tableName = "sys_role_menu")
	public AjaxJson updateMenuByRole(int roleId, String ids) {
		AjaxJson ajaxJson = new AjaxJson();
		return ajaxJson;
	}

	@ResponseBody
	@RequestMapping(value = "clearMenuByRoleIds")
	@SystemLog(desc = "根据角色id清空权限", opType = SysOpType.DEL, tableName = "sys_role_menu")
	public AjaxJson clearPermissionByRoleIds(String roleIds) {
		AjaxJson ajaxJson = new AjaxJson();
		return ajaxJson;
	}

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
}
