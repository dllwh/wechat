package com.cdeledu.controller.system.upms;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cdeledu.common.base.ResponseBean;
import com.cdeledu.common.constants.MessageConstant;
import com.cdeledu.common.constants.SystemConstant.SysOpType;
import com.cdeledu.controller.BaseController;
import com.cdeledu.core.annotation.SystemLog;
import com.cdeledu.model.rbac.SysRole;
import com.cdeledu.model.rbac.SysUserRole;
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
	public ResponseBean addRole(SysRole role) {
		ResponseBean responseBean = new ResponseBean();
		try {
			if (roleService.existRoleWithRoleCode(role.getRoleCode())) {
				responseBean.setSuccess(false);
				responseBean.setMsg(MessageConstant.EXISTED);
				responseBean.setResultCode(201);
			} else {
				roleService.insert(role);
			}
		} catch (Exception e) {
			e.printStackTrace();
			responseBean.setSuccess(false);
			responseBean.setMsg(MessageConstant.FAILURE_SAVE_MESSAGE);
			responseBean.setResultCode(500);
		}
		return responseBean;
	}

	@ResponseBody
	@RequestMapping(value = "saveRole")
	@SystemLog(desc = "角色更新", opType = SysOpType.UPDATE, tableName = "sys_role")
	public ResponseBean saveRole(HttpServletRequest request, HttpServletResponse response,
			SysRole role) {
		ResponseBean responseBean = new ResponseBean();
		String msg = "";
		try {
			if (null != role) {
				String roleCode = role.getRoleCode();
				SysRole seacherRole = roleService.getRoleById(role.getId());
				if (seacherRole != null && seacherRole.getAllowEdit() == 1) {// 角色允许编辑
					if (seacherRole.getRoleCode().equalsIgnoreCase(roleCode)) {
						if (seacherRole.getId() == 1) {
							role.setRoleCode("");// 超级管理员不允许更新角色代码
						}
						roleService.update(role);
					} else {
						if (roleService.existRoleWithRoleCode(roleCode)) {
							responseBean.setSuccess(false);
							msg = MessageConstant.EXISTED;
						} else {
							roleService.update(role);
						}
					}
				} else {
					responseBean.setSuccess(false);
					responseBean.setResultCode(201);
					responseBean.setMsg("该角色不允许更新");
				}
			}
		} catch (Exception e) {
			responseBean.setSuccess(false);
			responseBean.setResultCode(500);
			msg = MessageConstant.MSG_OPERATION_FAILED;
		}
		responseBean.setMsg(msg);
		return responseBean;
	}

	/**
	 * @方法描述: 删除角色，根据ID，但是删除角色的时候，需要查询是否有赋予给用户，如果有用户在使用，那么就不能删除
	 * @param role
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "delRole")
	@SystemLog(desc = "角色删除", opType = SysOpType.DEL, tableName = "sys_role")
	public ResponseBean delRole(
			@RequestParam(name = "delId", required = true, defaultValue = "-1") int roleId) {
		ResponseBean responseBean = new ResponseBean();
		try {
			if (roleId != 1) {
				SysRole seacherRole = roleService.getRoleById(roleId);
				if (seacherRole != null && seacherRole.getAllowDelete() == 1) { // 角色允许删除
					if (roleService.hasMenuByRole(roleId)) {
						responseBean.setSuccess(false);
						responseBean.setResultCode(201);
						responseBean.setMsg("该角色已经含有权限,无法直接删除!");
					} else {
						if (roleService.hasUserByRole(roleId)) {
							responseBean.setSuccess(false);
							responseBean.setResultCode(201);
							responseBean.setMsg("该角色已被使用，无法直接删除！");
						} else {
							roleService.delete(roleId);
							responseBean.setSuccess(true);
							responseBean.setResultCode(200);
							responseBean.setMsg(MessageConstant.MSG_OPERATION_SUCCESS);
						}
					}
				} else {
					responseBean.setSuccess(false);
					responseBean.setResultCode(201);
					responseBean.setMsg("该角色不允许删除");
				}
			} else {
				responseBean.setSuccess(false);
				responseBean.setResultCode(201);
				responseBean.setMsg("无法删除超级管理员");
			}
		} catch (Exception e) {
			responseBean.setSuccess(false);
			responseBean.setResultCode(500);
			responseBean.setMsg(MessageConstant.MSG_OPERATION_FAILED);
		}
		return responseBean;
	}

	@ResponseBody
	@RequestMapping(value = "updateMenuByRole")
	@SystemLog(desc = "更新角色的权限", opType = SysOpType.UPDATE, tableName = "sys_role_menu")
	public ResponseBean updateMenuByRole(Integer roleId, String ids) {
		ResponseBean ResponseBean = new ResponseBean();
		if (roleId != null && StringUtils.isNoneBlank(ids)) {
			if (roleId != 1) {
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
				ResponseBean.setSuccess(false);
				ResponseBean.setResultCode(10005);
				ResponseBean.setMsg("该资源需要appkey拥有授权");
			}
		} else {
			ResponseBean.setSuccess(false);
			ResponseBean.setResultCode(10006);
			ResponseBean.setMsg("缺少source (appkey) 参数");
		}
		return ResponseBean;
	}

	@ResponseBody
	@RequestMapping("roleVisibleButton")
	@SystemLog(desc = "启用、禁用账户", opType = SysOpType.UPDATE, tableName = "sys_role_menu")
	public ResponseBean roleVisibleButton(
			@RequestParam(name = "id", required = true, defaultValue = "") int id,
			@RequestParam(name = "visible", required = true, defaultValue = "1") int visible) {
		ResponseBean responseBean = new ResponseBean();
		SysRole role = new SysRole();
		role.setId(id);
		role.setIfVisible(visible);
		try {
			if (!roleService.hasMenuByRole(id) && !roleService.hasUserByRole(id) && id != 1) {
				roleService.update(role);
				responseBean.setMsg(MessageConstant.MSG_OPERATION_SUCCESS);
			} else {
				responseBean.setSuccess(false);
				responseBean.setMsg("错误提示：该角色下尚有用户未解除！");
			}
		} catch (Exception e) {
			responseBean.setSuccess(false);
			responseBean.setMsg(MessageConstant.MSG_OPERATION_FAILED);
		}
		return responseBean;
	}

	@ResponseBody
	@RequestMapping("updateRoleUser")
	@SystemLog(desc = "角色用户管理", opType = SysOpType.UPDATE, tableName = "sys_user_role")
	public ResponseBean updateRoleUser(SysUserRole sysUserRole,
			@RequestParam(name = "opType", required = true, defaultValue = "0") int opType) {
		ResponseBean responseBean = new ResponseBean();
		int userCode = sysUserRole.getUserId();
		boolean result = false;
		String msg = "";
		SysRole sysRole = new SysRole(sysUserRole.getRoleId());
		SysRole sysRole2 = null;
		try {
			sysRole2  = roleService.findOneForJdbc(sysRole);
		} catch (Exception e) {
			
		}
		if(sysRole2 != null && sysRole2.getIfVisible() == 1){
			if ((opType == 1 || opType == -1) && (userCode > 0)) {
				if (opType == 1) {
					if (roleService.saveRoleUser(sysUserRole)) {
						result = true;
						msg = MessageConstant.MSG_OPERATION_SUCCESS;
					} else {
						msg = MessageConstant.MSG_OPERATION_FAILED;
					}
				} else {
					if (sysUserRole.getRoleId() == 1 && sysUserRole.getUserId() == 1) {
						msg = "错误提示：超级管理员账号不能删除";
					} else {
						if (roleService.delRoleUser(sysUserRole)) {
							result = true;
							msg = MessageConstant.MSG_OPERATION_SUCCESS;
						} else {
							msg = MessageConstant.MSG_OPERATION_FAILED;
						}
					}
				}
			}
		} else {
			msg = "错误提示：该角色尚未使用，无法操作";
		}
		

		responseBean.setSuccess(result);
		responseBean.setMsg(msg);

		return responseBean;
	}
}
