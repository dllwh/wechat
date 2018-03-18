package com.cdeledu.controller.system.upms;

import java.util.Arrays;
import java.util.List;

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
import com.cdeledu.model.rbac.SysUser;
import com.cdeledu.model.rbac.SysUserRole;
import com.cdeledu.service.sys.ManagerUserService;
import com.cdeledu.util.WebUtilHelper;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述:
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2017年10月22日 下午6:08:19
 * @版本: V1.0
 * @since: JDK 1.7
 */
@Controller
@RequestMapping("sysUserOperate")
public class SysUserOperateController extends BaseController {
	private static final long serialVersionUID = 1L;
	/** ----------------------------------------------------- Fields start */
	@Autowired
	private ManagerUserService manageruserService;

	/** ----------------------------------------------------- Fields end */
	/**
	 * @方法描述: 用户注册
	 * @param managerUser
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "singUp")
	public AjaxJson singUp(SysUser managerUser) {
		AjaxJson resultMsg = new AjaxJson();
		try {
			if (manageruserService.getUserByName(managerUser.getUserName()) == null) {
				manageruserService.insert(managerUser);
				resultMsg.setMsg(MessageConstant.MSG_OPERATION_SUCCESS);
			} else {
				resultMsg.setSuccess(false);
				resultMsg.setMsg(MessageConstant.EXISTED);
			}

		} catch (Exception e) {
			resultMsg.setMsg(MessageConstant.MSG_OPERATION_FAILED);
			resultMsg.setSuccess(false);
		}
		return resultMsg;
	}

	/**
	 * @方法描述: 用户更新
	 * @创建者: 皇族灬战狼
	 * @创建时间: 2016年9月27日 下午4:51:23
	 * @param managerUser
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "updateUser")
	public AjaxJson updateUser(SysUser managerUser) {
		AjaxJson resultMsg = new AjaxJson();
		try {
			if (managerUser != null && managerUser.getId() != null) {
				managerUser.setUserName(null);// 不能更新username
				manageruserService.update(managerUser);
				resultMsg.setMsg(MessageConstant.MSG_OPERATION_SUCCESS);
			} else {
				resultMsg.setSuccess(false);
				resultMsg.setResultCode(10017);
				resultMsg.setMsg("缺失必选参数 (%s)");
			}
		} catch (Exception e) {
			resultMsg.setSuccess(false);
			resultMsg.setResultCode(500);
			resultMsg.setMsg(MessageConstant.MSG_OPERATION_FAILED);
		}
		return resultMsg;
	}

	/**
	 * @方法描述: 用户删除
	 * @创建者: 皇族灬战狼
	 * @创建时间: 2016年9月27日 下午4:51:19
	 * @param managerUser
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "deleteUser")
	public AjaxJson delUser(@RequestParam(value = "id", required = true,defaultValue = "-1") Integer id) {
		AjaxJson resultMsg = new AjaxJson();
		try {
			if(id != 1 ){
				SysUser sysUser = new SysUser();
				sysUser.setId(id);
				List<SysUserRole> userRoleList = manageruserService.getUserRole(sysUser);
				if (userRoleList == null || userRoleList.isEmpty() || userRoleList.size() == 0) {
					manageruserService.delete(id);
					resultMsg.setMsg(MessageConstant.MSG_OPERATION_SUCCESS);
				} else {
					resultMsg.setSuccess(false);
					resultMsg.setMsg("删除失败，该用户已分配角色");
				}
			} else {
				resultMsg.setSuccess(false);
				resultMsg.setResultCode(403);
				resultMsg.setMsg("无法删除超级管理员账号");
			}
			
		} catch (Exception e) {
			resultMsg.setSuccess(false);
			resultMsg.setResultCode(10001);
			resultMsg.setMsg(MessageConstant.MSG_OPERATION_SUCCESS);
		}
		return resultMsg;
	}

	/**
	 * @方法描述: 用户删除(批量)
	 * @创建者: 皇族灬战狼
	 * @创建时间: 2016年9月27日 下午4:51:19
	 * @param delIds
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "bathDelUser")
	public AjaxJson bathDelUser(@RequestParam(value = "delIds", required = true) String delIds) {
		AjaxJson reslutMsg = new AjaxJson();
		if (StringUtils.isNotBlank(delIds)) {
			List<Integer> ids = Arrays.asList();
			for (String id : delIds.split(",")) {
				try {
					ids.add(Integer.parseInt(id));
				} catch (Exception e) {
					continue;
				}
			}
			try {
				// manageruserService.deleteByPrimaryKey(ids);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		return reslutMsg;
	}

	@ResponseBody
	@RequestMapping("enable")
	@SystemLog(desc = " 启用账户", opType = SysOpType.UPDATE, tableName = { "sys_user" })
	public AjaxJson updateUserEnable(int userId) {
		AjaxJson resultMsg = new AjaxJson();
		SysUser user = new SysUser();
		user.setId(userId);

		try {
			SysUser sysUser = manageruserService.findOneForJdbc(user);
			// 超级管理员不能操作
			if (sysUser != null && sysUser.getUserType() != -1
					&& WebUtilHelper.getCurrentUserId() != userId) {
				sysUser.setIfVisible(1);
				manageruserService.update(sysUser);
			}
			resultMsg.setMsg(MessageConstant.MSG_OPERATION_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			resultMsg.setSuccess(false);
			resultMsg.setResultCode(500);
			resultMsg.setMsg(MessageConstant.MSG_OPERATION_FAILED);
		}
		return resultMsg;
	}

	@ResponseBody
	@RequestMapping("disable")
	@SystemLog(desc = " 禁用账户", opType = SysOpType.UPDATE, tableName = { "sys_user" })
	public AjaxJson updateUserDisable(int userId) {
		AjaxJson resultMsg = new AjaxJson();
		SysUser user = new SysUser();
		user.setId(userId);

		try {
			SysUser sysUser = manageruserService.findOneForJdbc(user);
			// 超级管理员不能操作
			if (sysUser != null && sysUser.getUserType() != -1
					&& WebUtilHelper.getCurrentUserId() != userId) {
				sysUser.setIfVisible(0);
				manageruserService.update(sysUser);
			}
			resultMsg.setMsg(MessageConstant.MSG_OPERATION_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			resultMsg.setSuccess(false);
			resultMsg.setResultCode(500);
			resultMsg.setMsg(MessageConstant.MSG_OPERATION_FAILED);
		}
		return resultMsg;
	}

	/**
	 * 
	 * @方法描述: 禁止登录
	 * @param status
	 *            1:有效，0:禁止登录
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "forbidUserById", method = RequestMethod.POST)
	@SystemLog(desc = "禁止登录/允许登录", opType = SysOpType.UPDATE, tableName = { "sys_user" })
	public AjaxJson forbidUserById(@RequestParam(name = "id", required = true) int userId,
			@RequestParam(name = "status", required = true, defaultValue = "1") int status) {
		AjaxJson resultMsg = new AjaxJson();
		SysUser user = new SysUser();
		user.setId(userId);

		try {
			SysUser sysUser = manageruserService.findOneForJdbc(user);
			if (sysUser != null && sysUser.getUserType() != -1
					&& WebUtilHelper.getCurrentUserId() != userId) {
				sysUser.setLoginFlag(status);
				manageruserService.update(sysUser);
			}
			resultMsg.setMsg(MessageConstant.MSG_OPERATION_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			resultMsg.setSuccess(false);
			resultMsg.setMsg(MessageConstant.MSG_OPERATION_FAILED);
		}
		return resultMsg;
	}

	
	@ResponseBody
	@RequestMapping(value = "lockUser", method = RequestMethod.POST)
	@SystemLog(desc = "锁定用户", opType = SysOpType.UPDATE, tableName = { "sys_user" })
	public AjaxJson lockUser(@RequestParam(name = "id", required = true) int userId) {
		AjaxJson resultMsg = new AjaxJson();
		SysUser user = new SysUser();
		user.setId(userId);

		try {
			SysUser sysUser = manageruserService.findOneForJdbc(user);
			if (sysUser != null && sysUser.getUserType() != -1
					&& WebUtilHelper.getCurrentUserId() != userId) {
				sysUser.setIfLocked(0);
				manageruserService.update(sysUser);
			}
			resultMsg.setMsg(MessageConstant.MSG_OPERATION_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			resultMsg.setSuccess(false);
			resultMsg.setMsg(MessageConstant.MSG_OPERATION_FAILED);
		}
		return resultMsg;
	}

	@ResponseBody
	@RequestMapping(value = "unlockUser")
	@SystemLog(desc = "解锁用户", opType = SysOpType.UPDATE, tableName = { "sys_user" })
	public AjaxJson unlockUser(@RequestParam(name = "id", required = true) int userId) {
		AjaxJson resultMsg = new AjaxJson();
		SysUser user = new SysUser();
		user.setId(userId);

		try {
			SysUser sysUser = manageruserService.findOneForJdbc(user);
			if (sysUser != null && sysUser.getUserType() != -1
					&& WebUtilHelper.getCurrentUserId() != userId) {
				sysUser.setIfLocked(1);
				manageruserService.update(sysUser);
			}
			resultMsg.setMsg(MessageConstant.MSG_OPERATION_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			resultMsg.setSuccess(false);
			resultMsg.setMsg(MessageConstant.MSG_OPERATION_FAILED);
		}
		return resultMsg;
	}
	
	/**
	 * @方法描述: 重置密码
	 * @return
	 */
	@ResponseBody
	@RequestMapping("reset")
	public AjaxJson updatePswd(int userId, String pswd, String newPswd) {
		AjaxJson resultMsg = new AjaxJson();
		// 根据当前登录的用户帐号 + 老密码，查询。

		// 管理员不准修改密码
		return resultMsg;
	}

	/**
	 * @方法描述: 用户-角色录入
	 * @创建者: 皇族灬战狼
	 * @创建时间: 2016年9月27日 下午4:51:27
	 * @param managerUser
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "roleAssign", params = "saveRoleUser")
	@SystemLog(desc = "用户-角色录入(授权)", opType = SysOpType.INSERT, tableName = "sys_user_role")
	public AjaxJson saveRoleUser(@RequestParam(value = "userCode", required = true) int id,
			@RequestParam(value = "roleID", defaultValue = "1", required = false) int roleID) {
		AjaxJson resultMsg = new AjaxJson();
		SysUser user = new SysUser();
		user.setId(id);
		try {
			if(roleID == 1){//超级管理员不参与分配
				resultMsg.setSuccess(false);
				resultMsg.setMsg("无法授予权限");
			}
			SysUser tSUser = manageruserService.findOneForJdbc(user);
			if (null == tSUser) {
				resultMsg.setMsg("不存在");
				resultMsg.setSuccess(false);
			} else {
				SysUserRole managerUserRole = new SysUserRole();
				managerUserRole.setUserId(tSUser.getId());
				managerUserRole.setRoleId(roleID);
				manageruserService.saveRoleUser(managerUserRole);
			}
		} catch (Exception e) {
			resultMsg.setResultCode(500);
			resultMsg.setMsg("分配角色时出现异常");
			resultMsg.setSuccess(false);
		}
		return resultMsg;
	}

	/**
	 * @方法描述: 根据用户id清空角色
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "roleAssign", params = "clearRoleUser")
	@SystemLog(desc = "用户-角色(解除授权)", opType = SysOpType.INSERT, tableName = "sys_user")
	public AjaxJson clearRoleByUserIds(SysUserRole sysUserRole) {
		AjaxJson resultMsg = new AjaxJson();
		if(sysUserRole.getUserId() != null){
			if(WebUtilHelper.getCurrentUserId() != sysUserRole.getUserId()){
				try {
					manageruserService.deleteUserRole(sysUserRole);
				} catch (Exception e) {
					e.printStackTrace();
					resultMsg.setSuccess(false);
					resultMsg.setResultCode(500);
					resultMsg.setMsg(MessageConstant.MSG_OPERATION_FAILED);
				}
			} else {
				resultMsg.setSuccess(false);
				resultMsg.setResultCode(500);
				resultMsg.setMsg("不能解除当前用户权限");
			}
		}
		// 超级管理员不能删除
		return resultMsg;
	}

	/**
	 * @方法描述: 改变Session状态
	 * @param userIds
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "changeSessionStatus", method = RequestMethod.POST)
	public AjaxJson changeSessionStatus(Boolean status, String sessionIds) {
		AjaxJson resultMsg = new AjaxJson();
		return resultMsg;
	}

	/**
	 * @方法描述: 个人资料修改
	 * @return
	 */
	@ResponseBody
	@RequestMapping("updateSelf")
	public AjaxJson updateSelf(SysUser sysUser) {
		AjaxJson resultMsg = new AjaxJson();
		return resultMsg;
	}
}
