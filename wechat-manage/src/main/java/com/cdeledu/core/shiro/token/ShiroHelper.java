package com.cdeledu.core.shiro.token;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.ExpiredCredentialsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import com.cdeledu.common.base.AjaxJson;
import com.cdeledu.common.constants.MessageConstant;
import com.cdeledu.model.rbac.SysUser;

public class ShiroHelper {
	/** ----------------------------------------------------- Fields start */
	/** ----------------------------------------------------- Fields end */

	public static AjaxJson login(String userName, String passWord) {
		// 用户名密码令牌
		UsernamePasswordToken token = new UsernamePasswordToken(userName, passWord);
		token.setRememberMe(false);
		String logMsg = "", resultMsg = "";
		AjaxJson ajaxJson = new AjaxJson();
		boolean suc = false;

		// 获得当前登录用户对象Subject，现在状态为 “未认证”
		Subject subject = SecurityUtils.getSubject();
		try {
			subject.login(token);
		} catch (UnknownAccountException uae) {
			logMsg = "对用户[" + userName + "]进行登录验证..验证未通过,未知账户";
			resultMsg = MessageConstant.LOGIN_USER_UNKNOWN;
		} catch (IncorrectCredentialsException ice) {
			logMsg = "对用户[" + userName + "]进行登录验证..验证未通过,错误的凭证";
			resultMsg = MessageConstant.LOGIN_USER_REEOE;
		} catch (LockedAccountException lae) {
			logMsg = "对用户[" + userName + "]进行登录验证..验证未通过,账户已锁定";
			resultMsg = MessageConstant.LOGIN_USER_LOCK;
		} catch (DisabledAccountException dae) {
			logMsg = "对用户[" + userName + "]进行登录验证..验证未通过,帐号已被禁用";
			resultMsg = MessageConstant.LOGIN_USER_DISABLED;
		} catch (ExpiredCredentialsException ece) {
			logMsg = "对用户[" + userName + "]进行登录验证..验证未通过,帐号已过期";
			resultMsg = MessageConstant.LOGIN_USER_EXPIRED;
		} catch (ExcessiveAttemptsException eae) {
			logMsg = "对用户[" + userName + "]进行登录验证..验证未通过,用户名或密码错误次数过多";
			resultMsg = MessageConstant.LOGIN_USER_MORE;
		} catch (UnauthorizedException e) {
			logMsg = "对用户[" + userName + "]进行登录验证..验证未通过,您没有得到相应的授权！";
			resultMsg = MessageConstant.LOGIN_USER_UNAUTHORIZED;
		} catch (AuthenticationException ae) {
			logMsg = "对用户[" + userName + "]进行登录验证..验证未通过," + ae.getMessage();
			resultMsg = MessageConstant.LOGIN_ERROR;
		}

		if (subject.isAuthenticated()) {
			logMsg = "对用户[" + userName + "]进行登录验证..验证通过";
			suc = true;
		} else {
			token.clear();
		}

		ajaxJson.setSuccess(suc);
		ajaxJson.setMsg(resultMsg);
		ajaxJson.setObj(logMsg);
		return ajaxJson;
	}

	/**
	 * @方法描述:获取认证授权组件Subject,其为我们提供了当前用户、角色和授权的相关信息
	 * @return
	 */
	public static Subject getSubject() {
		return SecurityUtils.getSubject();
	}

	public static Session getSession() {
		return getSubject().getSession();
	}

	/**
	 * @方法描述: 获取当前获取授权用户信息
	 * @return
	 */
	public static SysUser getPrincipal() {
		return (SysUser) ShiroHelper.getSubject().getPrincipal();
	}

	/**
	 * @方法描述 :认证通过或已记住的用户。
	 * @return 用户：true，否则 false
	 */
	public boolean isUser() {
		return isLogin();
	}

	/**
	 * @方法描述 : 验证当前用户是否为“访客”，即未认证（包含未记住）的用户。
	 * @return 访客：true，否则false
	 */
	public boolean isGuest() {
		return !isUser();
	}

	/**
	 * 判断是否登录
	 * 
	 * @return
	 */
	public static boolean isLogin() {
		return null != getSubject().getPrincipal();
	}

	/**
	 * 退出登录
	 */
	public static void logout() {
		Subject subject = getSubject();
		if (subject.isAuthenticated()) {
			subject.logout();
		}
	}

}
