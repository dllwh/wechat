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

import com.cdeledu.common.base.ResponseBean;
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
	public ResponseBean singUp(SysUser managerUser) {
		ResponseBean responseBean = new ResponseBean();
		try {
			if (manageruserService.getUserByName(managerUser.getUserName()) == null) {
				manageruserService.insert(managerUser);
				responseBean.setMsg(MessageConstant.MSG_OPERATION_SUCCESS);
			} else {
				responseBean.setSuccess(false);
				responseBean.setMsg(MessageConstant.EXISTED);
			}

		} catch (Exception e) {
			responseBean.setMsg(MessageConstant.MSG_OPERATION_FAILED);
			responseBean.setSuccess(false);
		}
		return responseBean;
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
	public ResponseBean updateUser(SysUser managerUser) {
		ResponseBean responseBean = new ResponseBean();
		try {
			if (managerUser != null && managerUser.getId() != null) {
				managerUser.setUserName(null);// 不能更新username
				manageruserService.update(managerUser);
				responseBean.setMsg(MessageConstant.MSG_OPERATION_SUCCESS);
			} else {
				responseBean.setSuccess(false);
				responseBean.setResultCode(10017);
				responseBean.setMsg("缺失必选参数 (%s)");
			}
		} catch (Exception e) {
			responseBean.setSuccess(false);
			responseBean.setResultCode(500);
			responseBean.setMsg(MessageConstant.MSG_OPERATION_FAILED);
		}
		return responseBean;
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
	public ResponseBean delUser(
			@RequestParam(value = "id", required = true, defaultValue = "-1") Integer id) {
		ResponseBean responseBean = new ResponseBean();
		try {
			if (id != 1) {
				SysUser sysUser = new SysUser();
				sysUser.setId(id);
				List<SysUserRole> userRoleList = manageruserService.getUserRole(sysUser);
				if (userRoleList == null || userRoleList.isEmpty() || userRoleList.size() == 0) {
					manageruserService.delete(id);
					responseBean.setMsg(MessageConstant.MSG_OPERATION_SUCCESS);
				} else {
					responseBean.setSuccess(false);
					responseBean.setMsg("删除失败，该用户已分配角色");
				}
			} else {
				responseBean.setSuccess(false);
				responseBean.setResultCode(403);
				responseBean.setMsg("无法删除超级管理员账号");
			}

		} catch (Exception e) {
			responseBean.setSuccess(false);
			responseBean.setResultCode(10001);
			responseBean.setMsg(MessageConstant.MSG_OPERATION_SUCCESS);
		}
		return responseBean;
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
	public ResponseBean bathDelUser(
			@RequestParam(value = "delIds", required = true) String delIds) {
		ResponseBean reslutMsg = new ResponseBean();
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
	public ResponseBean updateUserEnable(int userId) {
		ResponseBean responseBean = new ResponseBean();
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
			responseBean.setMsg(MessageConstant.MSG_OPERATION_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			responseBean.setSuccess(false);
			responseBean.setResultCode(500);
			responseBean.setMsg(MessageConstant.MSG_OPERATION_FAILED);
		}
		return responseBean;
	}

	@ResponseBody
	@RequestMapping("disable")
	@SystemLog(desc = " 禁用账户", opType = SysOpType.UPDATE, tableName = { "sys_user" })
	public ResponseBean updateUserDisable(int userId) {
		ResponseBean responseBean = new ResponseBean();
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
			responseBean.setMsg(MessageConstant.MSG_OPERATION_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			responseBean.setSuccess(false);
			responseBean.setResultCode(500);
			responseBean.setMsg(MessageConstant.MSG_OPERATION_FAILED);
		}
		return responseBean;
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
	public ResponseBean forbidUserById(@RequestParam(name = "id", required = true) int userId,
			@RequestParam(name = "status", required = true, defaultValue = "1") int status) {
		ResponseBean responseBean = new ResponseBean();
		SysUser user = new SysUser();
		user.setId(userId);

		try {
			SysUser sysUser = manageruserService.findOneForJdbc(user);
			if (sysUser != null && sysUser.getUserType() != -1
					&& WebUtilHelper.getCurrentUserId() != userId) {
				sysUser.setLoginFlag(status);
				manageruserService.update(sysUser);
			}
			responseBean.setMsg(MessageConstant.MSG_OPERATION_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			responseBean.setSuccess(false);
			responseBean.setMsg(MessageConstant.MSG_OPERATION_FAILED);
		}
		return responseBean;
	}

	@ResponseBody
	@RequestMapping(value = "lockUser", method = RequestMethod.POST)
	@SystemLog(desc = "锁定用户", opType = SysOpType.UPDATE, tableName = { "sys_user" })
	public ResponseBean lockUser(@RequestParam(name = "id", required = true) int userId) {
		ResponseBean responseBean = new ResponseBean();
		SysUser user = new SysUser();
		user.setId(userId);

		try {
			SysUser sysUser = manageruserService.findOneForJdbc(user);
			if (sysUser != null && sysUser.getUserType() != -1
					&& WebUtilHelper.getCurrentUserId() != userId) {
				sysUser.setIfLocked(0);
				manageruserService.update(sysUser);
			}
			responseBean.setMsg(MessageConstant.MSG_OPERATION_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			responseBean.setSuccess(false);
			responseBean.setMsg(MessageConstant.MSG_OPERATION_FAILED);
		}
		return responseBean;
	}

	@ResponseBody
	@RequestMapping(value = "unlockUser")
	@SystemLog(desc = "解锁用户", opType = SysOpType.UPDATE, tableName = { "sys_user" })
	public ResponseBean unlockUser(@RequestParam(name = "id", required = true) int userId) {
		ResponseBean responseBean = new ResponseBean();
		SysUser user = new SysUser();
		user.setId(userId);

		try {
			SysUser sysUser = manageruserService.findOneForJdbc(user);
			if (sysUser != null && sysUser.getUserType() != -1
					&& WebUtilHelper.getCurrentUserId() != userId) {
				sysUser.setIfLocked(1);
				manageruserService.update(sysUser);
			}
			responseBean.setMsg(MessageConstant.MSG_OPERATION_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			responseBean.setSuccess(false);
			responseBean.setMsg(MessageConstant.MSG_OPERATION_FAILED);
		}
		return responseBean;
	}

	/**
	 * @方法描述: 重置密码
	 * @return
	 */
	@ResponseBody
	@RequestMapping("reset")
	public ResponseBean updatePswd(int userId, String pswd, String newPswd) {
		ResponseBean responseBean = new ResponseBean();
		// 根据当前登录的用户帐号 + 老密码，查询。

		// 管理员不准修改密码
		return responseBean;
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
	public ResponseBean saveRoleUser(@RequestParam(value = "userCode", required = true) int id,
			@RequestParam(value = "roleID", defaultValue = "1", required = false) int roleID) {
		ResponseBean responseBean = new ResponseBean();
		SysUser user = new SysUser();
		user.setId(id);
		try {
			if (roleID == 1) {// 超级管理员不参与分配
				responseBean.setSuccess(false);
				responseBean.setMsg("无法授予权限");
			}
			SysUser tSUser = manageruserService.findOneForJdbc(user);
			if (null == tSUser) {
				responseBean.setMsg("不存在");
				responseBean.setSuccess(false);
			} else {
				SysUserRole managerUserRole = new SysUserRole();
				managerUserRole.setUserId(tSUser.getId());
				managerUserRole.setRoleId(roleID);
				manageruserService.saveRoleUser(managerUserRole);
			}
		} catch (Exception e) {
			responseBean.setResultCode(500);
			responseBean.setMsg("分配角色时出现异常");
			responseBean.setSuccess(false);
		}
		return responseBean;
	}

	/**
	 * @方法描述: 根据用户id清空角色
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "roleAssign", params = "clearRoleUser")
	@SystemLog(desc = "用户-角色(解除授权)", opType = SysOpType.INSERT, tableName = "sys_user")
	public ResponseBean clearRoleByUserIds(SysUserRole sysUserRole) {
		ResponseBean responseBean = new ResponseBean();
		if (sysUserRole.getUserId() != null) {
			if (WebUtilHelper.getCurrentUserId() != sysUserRole.getUserId()) {
				try {
					manageruserService.deleteUserRole(sysUserRole);
				} catch (Exception e) {
					e.printStackTrace();
					responseBean.setSuccess(false);
					responseBean.setResultCode(500);
					responseBean.setMsg(MessageConstant.MSG_OPERATION_FAILED);
				}
			} else {
				responseBean.setSuccess(false);
				responseBean.setResultCode(500);
				responseBean.setMsg("不能解除当前用户权限");
			}
		}
		// 超级管理员不能删除
		return responseBean;
	}

	/**
	 * @方法描述: 改变Session状态
	 * @param userIds
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "changeSessionStatus", method = RequestMethod.POST)
	public ResponseBean changeSessionStatus(Boolean status, String sessionIds) {
		ResponseBean responseBean = new ResponseBean();
		return responseBean;
	}

	/**
	 * @方法描述: 个人资料修改
	 * @return
	 */
	@ResponseBody
	@RequestMapping("updateSelf")
	public ResponseBean updateSelf(SysUser sysUser) {
		ResponseBean responseBean = new ResponseBean();
		return responseBean;
	}
}
