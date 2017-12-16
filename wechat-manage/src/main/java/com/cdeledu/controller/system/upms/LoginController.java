package com.cdeledu.controller.system.upms;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cdeledu.common.base.AjaxJson;
import com.cdeledu.common.constants.FilterHelper;
import com.cdeledu.common.constants.GlobalConstants;
import com.cdeledu.controller.BaseController;
import com.cdeledu.core.factory.LogTaskFactory;
import com.cdeledu.core.log.LogManager;
import com.cdeledu.core.shiro.token.ShiroHelper;
import com.cdeledu.model.rbac.SysUser;
import com.cdeledu.util.WebUtilHelper;
import com.cdeledu.util.network.IpUtilHelper;
import com.cdeledu.util.security.PasswordUtil;

import nl.bitwalker.useragentutils.UserAgent;

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
		String userName = user.getUserName();
		try {
			String password = PasswordUtil.encrypt(userName, user.getPassword());
			AjaxJson loginResult = ShiroHelper.login(userName, password);
			int loginStatus = 0;
			if (loginResult.isSuccess()) {
				loginStatus = 1;
				session.removeAttribute(GlobalConstants.IMAGECAPTCHA);
			} else {
				logMsg = loginResult.getMsg();
				suc = false;
			}

			try {
				String ip = IpUtilHelper.getClientIP(request);
				String browser = UserAgent.parseUserAgentString(request.getHeader("User-Agent"))
						.getBrowser().getName();
				LogManager.getInstance().executeLog(LogTaskFactory.loginLog(userName,
						String.valueOf(loginResult.getObj()), loginStatus, ip, browser));
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
	public String doLogin(HttpServletRequest request) {
		SysUser managerUser = ShiroHelper.getPrincipal();
		try {
			if (null != managerUser) {
				return "main/center";
			} else {
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
	public ModelAndView register() {
		ModelAndView mv = this.getModelAndView();
		mv.setViewName("login/register");
		return mv;
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
			ShiroHelper.logout();
		}
		return FilterHelper.LOGIN_SHORT;
	}
}