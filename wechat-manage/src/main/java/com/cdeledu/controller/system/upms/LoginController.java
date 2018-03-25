package com.cdeledu.controller.system.upms;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cdeledu.common.base.AjaxJson;
import com.cdeledu.common.constants.FilterHelper;
import com.cdeledu.common.constants.GlobalConstants;
import com.cdeledu.common.constants.MessageConstant;
import com.cdeledu.common.constants.SystemConstant.SysOpType;
import com.cdeledu.controller.BaseController;
import com.cdeledu.core.annotation.SystemLog;
import com.cdeledu.core.factory.LogTaskFactory;
import com.cdeledu.core.log.LogManager;
import com.cdeledu.core.shiro.token.ShiroHelper;
import com.cdeledu.model.rbac.SysUser;
import com.cdeledu.model.rbac.SysUserRole;
import com.cdeledu.service.sys.ManagerUserService;
import com.cdeledu.util.WebUtilHelper;
import com.cdeledu.util.security.PasswordUtil;

/**
 * @类描述: 登陆初始化控制器
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建日期: 2016年4月4日 下午5:55:06
 * @版本: V3.1.1
 * @since: JDK 1.7
 */
@Controller
@RequestMapping("/loginController")
public class LoginController extends BaseController {
	private static final long serialVersionUID = 1L;

	/** ----------------------------------------------------- Fields start */
	@Autowired
	private ManagerUserService userService;

	/** ----------------------------------------------------- Fields end */
	/**
	 * @方法:登陆验证
	 * @创建人:独泪了无痕
	 * @param user
	 * @return
	 */
	@RequestMapping(params = "checkuser")
	@ResponseBody
	public AjaxJson checkuser(HttpServletRequest request, SysUser user) {
		AjaxJson reslutMsg = new AjaxJson();
		HttpSession session = WebUtilHelper.getSession();
		// Session session = ShiroHelper.getSession();
		boolean suc = true;
		String logMsg = "";
		String userName = user.getUserName().trim();
		try {
			String password = PasswordUtil.encrypt(userName, user.getPassword().trim());
			AjaxJson loginResult = ShiroHelper.login(userName, password);
			int loginStatus = 2;
			if (loginResult.isSuccess()) {
				loginStatus = 1;
				session.removeAttribute(GlobalConstants.IMAGECAPTCHA);
			} else {
				logMsg = loginResult.getMsg();
				suc = false;
			}

			try {
				LogManager.getInstance()
						.executeLog(LogTaskFactory.loginLog(userName,
								String.valueOf(loginResult.getObj()), loginStatus, getIp(request),
								getBrowser(request)));
			} catch (Exception e) {
			}

		} catch (Exception e) {
			logMsg = "用户名或密码错误,请重新登录!";
			suc = false;
		}
		reslutMsg.setMsg(logMsg);
		reslutMsg.setSuccess(suc);
		return reslutMsg;
	}

	/***
	 * @方法:用户登录
	 * @创建人:独泪了无痕
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "doLogin")
	public String doLogin(Model model) {
		SysUser managerUser = ShiroHelper.getPrincipal();
		List<SysUserRole> roleList = null;
		try {
			if (null != managerUser) {
				// 获取菜单、角色列表
				roleList = userService.getUserRole(managerUser);
				// 如果没有角色，则不允许登录
				if (roleList != null && roleList.size() > 0) {
					// 用户头像
					// model.addAttribute("avatar", );
					return "main/center";
				} else {
					model.addAttribute("tips", "该用户没有角色，无法登录");
					return FilterHelper.LOGIN_SHORT;
				}
			} else {
				model.addAttribute("tips", "该用户长时间未操作，请重新登录");
				return FilterHelper.LOGIN_SHORT;
			}
		} catch (Exception e) {
			return FilterHelper.LOGIN_SHORT;
		}
	}

	/**
	 * 注册跳转
	 * 
	 * @return
	 */
	@RequestMapping(value = "register", method = RequestMethod.GET)
	public AjaxJson register(SysUser user) {
		AjaxJson result = new AjaxJson();
		boolean namestander = user.getUserName().trim().startsWith("qq") || user.getUserName().trim().startsWith("sina") || user.getUserName().trim().startsWith("github") || user.getUserName().trim().startsWith("baidu") || user.getUserName().trim().startsWith("weixin");
		if (namestander) {
			result.setSuccess(false);
			result.setMsg("不能以qq、sina、weixin、baidu、github 开头注册");
			return result;
		}
		
		return result;
	}

	/**
	 * @方法:退出系统
	 * @创建人:独泪了无痕
	 * @return
	 */
	@RequestMapping(params = "doLogout")
	public String doLogout(HttpServletRequest request) {
		SysUser currenLoginUser = ShiroHelper.getPrincipal();
		// 判断用户是否为空,不为空,则清空session中的用户object
		if (currenLoginUser != null) {
			// 保存退出日志
			HttpSession session = request.getSession();
			session.removeAttribute(GlobalConstants.USER_SESSION);
			String userName = currenLoginUser.getUserName();
			String ip = getIp(request);
			String browser = getBrowser(request);
			try {
				ShiroHelper.logout();
				LogManager.getInstance().executeLog(LogTaskFactory.loginLog(userName,"成功退出系统", -1, ip,browser));
			} catch (Exception e) {
				LogManager.getInstance().executeLog(LogTaskFactory.loginLog(userName,"退出失败，原因:"+e.getMessage(), -2, ip,browser));
			}
		}
		return FilterHelper.LOGIN_SHORT;
	}

	/**
	 * @方法:用户被提出\被挤掉
	 * @创建人:独泪了无痕
	 * @return
	 */
	@RequestMapping(params = "kickout")
	public String kickout(HttpServletRequest request) {
		SysUser currenLoginUser = ShiroHelper.getPrincipal();
		// 判断用户是否为空,不为空,则清空session中的用户object
		if (currenLoginUser != null) {
			HttpSession session = request.getSession();
			session.removeAttribute(GlobalConstants.USER_SESSION);
			ShiroHelper.logout();
		}
		return FilterHelper.LOGIN_SHORT;
	}

	@ResponseBody
	@RequestMapping(value = "updateUser")
	@SystemLog(desc = "更新当前登录用户用户", opType = SysOpType.UPDATE, tableName = "sys_user")
	public AjaxJson updateUser() {
		AjaxJson result = new AjaxJson();
		return result;
	}

	@ResponseBody
	@RequestMapping(value = "resetPwd")
	@SystemLog(desc = "重置密码", opType = SysOpType.UPDATE, tableName = "sys_user")
	public AjaxJson resetPwd(String oldPassWord, String newPassWord) {
		AjaxJson result = new AjaxJson();
		SysUser currenLoginUser = ShiroHelper.getPrincipal();
		// 判断用户是否为空,不为空,则清空session中的用户object
		if (currenLoginUser != null) {
			if (StringUtils.isNoneBlank(oldPassWord) && StringUtils.isNoneBlank(newPassWord)) {
				String password = PasswordUtil.encrypt(currenLoginUser.getUserName(),
						oldPassWord.trim());
				if (currenLoginUser.getPassword().equalsIgnoreCase(password)) {
					SysUser sysUser = new SysUser();
					sysUser.setId(currenLoginUser.getId());
					sysUser.setPassword(PasswordUtil.encrypt(currenLoginUser.getUserName(),
							newPassWord.trim()));
					try {
						userService.update(sysUser);
						result.setSuccess(true);
						result.setMsg(MessageConstant.MSG_OPERATION_SUCCESS);
					} catch (Exception e) {
						e.printStackTrace();
						result.setSuccess(false);
						result.setMsg(MessageConstant.MSG_OPERATION_FAILED);
					}
				} else {
					result.setSuccess(false);
					result.setMsg("错误提示:请输入正确的原密码");
				}
			} else {
				result.setSuccess(false);
				result.setMsg("错误提示:旧密码或新密码不能为空");
			}
		} else {
			result.setSuccess(false);
			result.setMsg("当前用户尚未登录，请重新登录");
		}
		return result;
	}

}