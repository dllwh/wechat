package com.cdeledu.controller.system.sysUser;

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
import com.cdeledu.controller.BaseController;
import com.cdeledu.model.rbac.SysUser;
import com.cdeledu.model.rbac.SysUserRole;
import com.cdeledu.service.sys.ManagerUserService;

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
@RequestMapping("/sysUser/operate")
public class SysUserOperateController extends BaseController {
	private static final long serialVersionUID = 1L;
	/** ----------------------------------------------------- Fields start */
	@Autowired
	private ManagerUserService manageruserService;
	private String logMsg = null;

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
		logMsg = "用户: " + managerUser.getUserName();
		try {
			manageruserService.insert(managerUser);
			logMsg += ",注册(创建)成功";

		} catch (Exception e) {
			e.printStackTrace();
			logMsg += ",在被创建之时出现异常,其原因是" + e.getMessage();
			resultMsg.setSuccess(false);
		}
		resultMsg.setMsg(logMsg);
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
	@RequestMapping(value = "saveRoleUser")
	public AjaxJson saveRoleUser(SysUser managerUser,
			@RequestParam(value = "roleID", defaultValue = "1", required = false) int roleID) {
		AjaxJson resultMsg = new AjaxJson();
		SysUser user = new SysUser();
		user.setUserName(managerUser.getUserName());
		logMsg = "用户: " + managerUser.getUserName();
		try {
			SysUser TSUser = manageruserService.findOneForJdbc(user);
			if (null != TSUser) {
				logMsg += " 不存在";
				resultMsg.setSuccess(false);
			} else {
				int userId = manageruserService.insert(managerUser);
				logMsg += " 添加成功";

				if (StringUtils.isNotEmpty(String.valueOf(roleID))) {
					SysUserRole managerUserRole = new SysUserRole();
					managerUserRole.setUserId(userId);
					managerUserRole.setRoleId(roleID);
					manageruserService.saveRoleUser(managerUserRole);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logMsg += " 分配角色时出现异常,其异常原因是" + e.getMessage();
			resultMsg.setSuccess(false);
		}
		resultMsg.setMsg(logMsg);
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
		logMsg = "用户: " + managerUser.getUserName();
		try {
			managerUser.setUserName(null);// 不能更新username
			manageruserService.update(managerUser);
			logMsg += "更新成功";
		} catch (Exception e) {
			e.printStackTrace();
			resultMsg.setSuccess(false);
			logMsg += "更新时出现异常,其异常原因时:" + e.getMessage();
		}
		resultMsg.setMsg(logMsg);
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
	public AjaxJson delUser(@RequestParam(value = "id", required = true) Integer id) {
		AjaxJson resultMsg = new AjaxJson();
		logMsg = "删除用户ID: " + id + " 的用户";
		try {
			SysUser managerUser = new SysUser();
			managerUser.setId(id);
			manageruserService.delete(managerUser);
			logMsg += ",删除成功";
		} catch (Exception e) {
			e.printStackTrace();
			resultMsg.setSuccess(false);
			logMsg += ",删除失败，其异常原因是:" + e.getMessage();
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
	public AjaxJson BathDelUser(@RequestParam(value = "delIds", required = true) String delIds) {
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

	/**
	 * 
	 * @方法描述: 启用账户
	 * @return
	 */
	@ResponseBody
	@RequestMapping("enable")
	public AjaxJson updateUserEnable(int userId) {
		AjaxJson resultMsg = new AjaxJson();
		return resultMsg;
	}

	/**
	 * 
	 * @方法描述: 禁用账户
	 * @return
	 */
	@ResponseBody
	@RequestMapping("disable")
	public AjaxJson updateUserDisable(int userId) {
		AjaxJson resultMsg = new AjaxJson();
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
	public AjaxJson forbidUserById(int userId, boolean status) {
		AjaxJson resultMsg = new AjaxJson();
		return resultMsg;
	}

	/**
	 * @方法描述: 重置密码
	 * @return
	 */
	@ResponseBody
	@RequestMapping("reset")
	public AjaxJson updatePswd(int userId,String pswd,String newPswd) {
		AjaxJson resultMsg = new AjaxJson();
		//根据当前登录的用户帐号 + 老密码，查询。
		
		// 管理员不准修改密码
		return resultMsg;
	}

	/**
	 * @方法描述: 操作用户的角色
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "addRoleByUser")
	public AjaxJson addRoleByUser(int userId, String ids) {
		AjaxJson resultMsg = new AjaxJson();
		return resultMsg;
	}

	/**
	 * @方法描述: 根据用户id清空角色
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "clearRoleByUserIds")
	public AjaxJson clearRoleByUserIds(String userIds) {
		AjaxJson resultMsg = new AjaxJson();
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
