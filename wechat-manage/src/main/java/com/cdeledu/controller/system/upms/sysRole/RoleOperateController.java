package com.cdeledu.controller.system.upms.sysRole;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
			if (roleService.existRoleWithRoleCode(role.getRoleCode())) {
				resultMsg.setSuccess(false);
				resultMsg.setMsg(MessageConstant.EXISTED);
				resultMsg.setResultCode(201);
			} else {
				roleService.insert(role);
			}
		} catch (Exception e) {
			e.printStackTrace();
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
		String msg = "";
		try {
			if (null != role) {
				String roleCode = role.getRoleCode();
				SysRole seacherRole = roleService.getRoleById(role.getId());
				if (seacherRole != null && seacherRole.getAllowEdit() == 1) {// 角色允许编辑
					if (seacherRole.getRoleCode().equalsIgnoreCase(roleCode)) {
						if(seacherRole.getId() == 1){
							role.setRoleCode("");//超级管理员不允许更新角色代码
						}
						roleService.update(role);
					} else {
						if (roleService.existRoleWithRoleCode(roleCode)) {
							resultMsg.setSuccess(false);
							msg = MessageConstant.EXISTED;
						} else {
							roleService.update(role);
						}
					}
				} else {
					resultMsg.setSuccess(false);
					resultMsg.setResultCode(201);
					resultMsg.setMsg("该角色不允许更新");
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
	public AjaxJson delRole(@RequestParam(name="delId",required=true,defaultValue="-1") int roleId) {
		AjaxJson resultMsg = new AjaxJson();
		try {
			if(roleId !=1 ){
				SysRole seacherRole = roleService.getRoleById(roleId);
				if(seacherRole!= null && seacherRole.getAllowDelete() == 1){ // 角色允许删除
					if (roleService.hasMenuByRole(roleId)) {
						resultMsg.setSuccess(false);
						resultMsg.setResultCode(201);
						resultMsg.setMsg("该角色已经含有权限,无法直接删除!");
					} else {
						if (roleService.hasUserByRole(roleId)) {
							resultMsg.setSuccess(false);
							resultMsg.setResultCode(201);
							resultMsg.setMsg("该角色已被使用，无法直接删除！");
						} else {
							roleService.delete(roleId);
							resultMsg.setSuccess(true);
							resultMsg.setResultCode(200);
							resultMsg.setMsg(MessageConstant.MSG_OPERATION_SUCCESS);
						}
					}
				} else {
					resultMsg.setSuccess(false);
					resultMsg.setResultCode(201);
					resultMsg.setMsg("该角色不允许删除");
				}
			} else {
				resultMsg.setSuccess(false);
				resultMsg.setResultCode(201);
				resultMsg.setMsg("无法删除超级管理员");
			}
		} catch (Exception e) {
			resultMsg.setSuccess(false);
			resultMsg.setResultCode(500);
			resultMsg.setMsg(MessageConstant.MSG_OPERATION_FAILED);
		}
		return resultMsg;
	}

	@ResponseBody
	@RequestMapping(value = "updateMenuByRole")
	@SystemLog(desc = "更新角色的权限", opType = SysOpType.UPDATE, tableName = "sys_role_menu")
	public AjaxJson updateMenuByRole(Integer roleId, String ids) {
		AjaxJson ajaxJson = new AjaxJson();
		if(roleId != null && StringUtils.isNoneBlank(ids)){
			if(roleId != 1){
				roleService.delRoleAccess(roleId);	
				String str[] = ids.split(",");
				for (int i = 0; i < str.length; i++) {
					try {
						roleService.saveRoleAccess(roleId, Integer.parseInt(str[i]));
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				
			} else {
				ajaxJson.setSuccess(false);
				ajaxJson.setResultCode(10005);
				ajaxJson.setMsg("该资源需要appkey拥有授权");
			}
		} else {
			ajaxJson.setSuccess(false);
			ajaxJson.setResultCode(10006);
			ajaxJson.setMsg("缺少source (appkey) 参数");
		}
		return ajaxJson;
	}
	/**
	 * 
	 * @方法描述: 启用、禁用账户
	 * @return
	 */
	@ResponseBody
	@RequestMapping("roleVisibleButton")
	@SystemLog(desc = "启用、禁用账户", opType = SysOpType.UPDATE, tableName = "sys_role_menu")
	public AjaxJson roleVisibleButton(
			@RequestParam(name = "id", required = true, defaultValue = "") int id,
			@RequestParam(name = "visible", required = true, defaultValue = "1") int visible) {
		AjaxJson resultMsg = new AjaxJson();
		SysRole role = new SysRole();
		role.setId(id);
		role.setIsVisible(visible);
		try {
			if(!roleService.hasMenuByRole(id) && ! roleService.hasUserByRole(id) && id != 1){				
				roleService.update(role);
				resultMsg.setMsg(MessageConstant.MSG_OPERATION_SUCCESS);
			} else {
				resultMsg.setSuccess(false);
				resultMsg.setMsg("当前所选数据有子节点数据！");
			}
		} catch (Exception e) {
			resultMsg.setSuccess(false);
			resultMsg.setMsg(MessageConstant.MSG_OPERATION_FAILED);
		}
		return resultMsg;
	}
}
