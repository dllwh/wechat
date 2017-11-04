package com.cdeledu.util;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;

import com.cdeledu.common.base.AjaxJson;
import com.cdeledu.common.constants.UserReturnCode;
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
			uae.printStackTrace();
			logMsg = "对用户[" + userName + "]进行登录验证..验证未通过,未知账户";
			resultMsg = UserReturnCode.user_not_exist.getMessage();
		} catch (IncorrectCredentialsException ice) {
			ice.printStackTrace();
			logMsg = "对用户[" + userName + "]进行登录验证..验证未通过,错误的凭证";
			resultMsg = UserReturnCode.wrong_password.getMessage();
		} catch (LockedAccountException lae) {
			lae.printStackTrace();
			logMsg = "对用户[" + userName + "]进行登录验证..验证未通过,账户已锁定";
			resultMsg = UserReturnCode.user_suspend.getMessage();
		} catch (ExcessiveAttemptsException eae) {
			eae.printStackTrace();
			logMsg = "对用户[" + userName + "]进行登录验证..验证未通过,用户名或密码错误次数过多";
			resultMsg = UserReturnCode.account_lock.getMessage();
		} catch (AuthenticationException ae) {
			ae.printStackTrace();
			// 通过处理Shiro的运行时AuthenticationException就可以控制用户登录失败或密码错误时的情景
			logMsg = "对用户[" + userName + "]进行登录验证..验证未通过," + ae.getMessage();
			resultMsg = ae.getMessage();
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
	 * @方法描述: 获取当前获取授权用户信息
	 * @return
	 */
	public static SysUser getPrincipal() {
		return (SysUser) SecurityUtils.getSubject().getPrincipal();
	}

	/**
	 * @方法描述: 获取当前获取授权用户id.
	 * @return
	 */
	public static Integer getCurrentUserId() {
		SysUser user = getPrincipal();
		if (user != null) {
			return user.getId();
		} else {
			return null;
		}
	}

	/**
	 * @方法描述: 获取当前获取授权用户用户名
	 * @return
	 */
	public static String getCurrentUserName() {
		SysUser user = getPrincipal();
		if (user != null) {
			return user.getUserName();
		} else {
			return "";
		}
	}

	/**
	 * @方法描述: 获取当前获取授权用户真实姓名
	 * @return
	 */
	public static String getCurrentRealName() {
		SysUser user = getPrincipal();
		if (user != null) {
			return user.getRealName();
		} else {
			return "";
		}
	}

	/**
	 * @方法描述: 获取当前获取授权用户昵称
	 * @return
	 */
	public static String getCurrentNickName() {
		SysUser user = getPrincipal();
		if (user != null) {
			return user.getNickName();
		} else {
			return "";
		}
	}
	
	/**
	 * 判断是否登录
	 * 
	 * @return
	 */
	public static boolean isLogin() {
		return null != SecurityUtils.getSubject().getPrincipal();
	}

	/**
	 * 退出登录
	 */
	public static void logout() {
		Subject subject = SecurityUtils.getSubject();
		if (subject.isAuthenticated()) {
			subject.logout();
		}
	}
}
