package com.cdeledu.controller.system;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.cdeledu.common.constants.GlobalConstants;
import com.cdeledu.common.model.AjaxJson;
import com.cdeledu.controller.BaseController;
import com.cdeledu.model.rbac.ManagerUser;
import com.cdeledu.model.system.LoginLog;
import com.cdeledu.service.sys.ManagerUserService;
import com.cdeledu.service.sys.SystemService;
import com.cdeledu.util.WebUtilHelper;

/**
 * @类描述: 登陆初始化控制器
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建日期: 2016年4月4日 下午5:55:06
 * @版本: V1.0
 * @since: JDK 1.7
 */
@Controller
@RequestMapping("/loginController")
public class LoginController extends BaseController {
	private static final long serialVersionUID = 1L;
	/** ----------------------------------------------------- Fields start */
	@Autowired
	private ManagerUserService manageruserService;
	@Autowired
	private SystemService systemService;

	private String logMsg = null;

	/** ----------------------------------------------------- Fields end */
	/**
	 * @方法:登陆验证
	 * @创建人:独泪了无痕
	 * @param request
	 * @param response
	 * @param user
	 * @return
	 */
	@RequestMapping(params = "checkuser")
	@ResponseBody
	public AjaxJson checkuser(HttpServletRequest request, HttpServletResponse response,
			ManagerUser user) {
		AjaxJson reslutMsg = new AjaxJson();
		HttpSession session = WebUtilHelper.getSession();
		String imageCaptcha = (String) session.getAttribute(GlobalConstants.IMAGECAPTCHA);
		boolean suc = true;
		int loginStatus = 0;
		int logLeavel = 0;
		LoginLog loginLog = new LoginLog();
		ManagerUser managerUser = null;

		if (StringUtils.isEmpty(imageCaptcha) || !imageCaptcha.equalsIgnoreCase(user.getImageCaptcha())) {
			logMsg = "验证码错误，请重新输入";
			suc = false;
		} else {
			try {
				managerUser = manageruserService.checkUserExits(user);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (null != managerUser && null != managerUser.getIsEnabled()) {
				if (managerUser.getIsEnabled() == 1) {
					logMsg = "用户: " + user.getUserName() + "登录成功";
					logLeavel = GlobalConstants.Log_Leavel_INFO;
					loginStatus = 1;
				} else {
					logMsg = "用户: " + user.getUserName() + "登录失败。原因:账号未通过审核";
					loginStatus = 0;
					logLeavel = GlobalConstants.Log_Leavel_WARRING;
				}

				// 保存当前登录用户信息
				WebUtilHelper.setCurrentLoginUser(managerUser);

				// 添加登陆日志
				loginLog.setUserCode(managerUser.getId());
				loginLog.setLogContent(logMsg);
				loginLog.setLoginStatus(loginStatus);
				loginLog.setLogLeavel(logLeavel);
				loginLog.setOpType(GlobalConstants.Log_Type_LOGIN);
				// loginLog.setIpAddress(""); // 登录的IP地址
				// loginLog.setBrower(""); // 登录的浏览器
				systemService.addLoginLog(loginLog);
			} else {
				logMsg = "用户名或密码错误,请重新登录!";
				suc = false;
			}
		}
		reslutMsg.setMsg(logMsg);
		reslutMsg.setSuccess(suc);
		return reslutMsg;
	}

	/***
	 * @方法:用户登录
	 * @创建人:独泪了无痕
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(params = "doLogin")
	public String doLogin(HttpServletRequest request, HttpServletResponse response) {
		ManagerUser managerUser = WebUtilHelper.getCurrenLoginUser();
		try {
			if (null != managerUser) {
				// String roleName =manageruserService.getUserRole(managerUser).getRoleName(); 
				// request.setAttribute("roleName",roleName);
				request.setAttribute(GlobalConstants.USER_SESSION, managerUser);
				return "main/center";
			} else {
				return "login/login";
			}
		} catch (Exception e) {
			return "login/login";
		}
	}

	/**
	 * @方法:退出系统
	 * @创建人:独泪了无痕
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(params = "doLogout")
	public ModelAndView doLogout(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = WebUtilHelper.getSession();
		ManagerUser managerUser = WebUtilHelper.getCurrenLoginUser();
		// 判断用户是否为空,不为空,则清空session中的用户object
		if (null != managerUser) {
			// 注销该操作用户
			session.removeAttribute(GlobalConstants.USER_SESSION);
			logMsg = "用户" + managerUser.getUserName() + "已退出";
			// 添加登陆日志
			LoginLog loginLog = new LoginLog();
			loginLog.setUserCode(managerUser.getId());
			loginLog.setLogContent(logMsg);
			loginLog.setLoginStatus(1);
			loginLog.setLogLeavel(GlobalConstants.Log_Type_EXIT);
			loginLog.setOpType(GlobalConstants.Log_Type_LOGIN);
			// loginLog.setIpAddress(""); // 登录的IP地址
			// loginLog.setBrower(""); // 登录的浏览器
			systemService.addLoginLog(loginLog);
		}
		return new ModelAndView(new RedirectView("loginController.shtml?doLogin"));
	}

	/**
	 * @方法:菜单跳转
	 * @创建人:独泪了无痕
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(params = "left")
	public ModelAndView left(HttpServletRequest request, HttpServletResponse response) {
		ManagerUser managerUser = WebUtilHelper.getCurrenLoginUser();
		HttpSession session = WebUtilHelper.getSession();
		// 登陆者的权限
		if (null == managerUser) {
			session.removeAttribute(GlobalConstants.USER_SESSION);
			return new ModelAndView(new RedirectView("loginController.do?login"));
		}

		// ManagerUserRole currentUser =
		// manageruserService.getUserRole(managerUser);

		return new ModelAndView("main/left");
	}

	/**
	 * @方法:首页跳转
	 * @创建人:独泪了无痕
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "home")
	public ModelAndView home(HttpServletRequest request, HttpServletResponse response) {
		return new ModelAndView("main/home");
	}

	/**
	 * @方法:首页跳转
	 * @创建人:独泪了无痕
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "maintabs")
	public ModelAndView maintabs(HttpServletRequest request, HttpServletResponse response) {
		return new ModelAndView("main/mainTabs");
	}

	/**
	 * @方法: 菜单权限控制:无权限页面提示跳转
	 * @创建人:独泪了无痕
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "noAuth")
	@ResponseBody
	public ModelAndView noAuth(HttpServletRequest request,
			@RequestParam(value = "requestPath", required = false) String requestPath) {
		AjaxJson reslutMsg = new AjaxJson();
		reslutMsg.setMsg("您没有【" + requestPath + "】权限，请联系管理员给您赋予相应权限！");
		reslutMsg.setSuccess(false);
		return new ModelAndView("common/noAuth");
	}
}
