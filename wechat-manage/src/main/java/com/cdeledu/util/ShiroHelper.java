package com.cdeledu.util;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import com.cdeledu.common.constants.GlobalConstants;
import com.cdeledu.model.rbac.SysUser;

public class ShiroHelper {
	/** ----------------------------------------------------- Fields start */
	/** ----------------------------------------------------- Fields end */

	public void login(String userName, String passWord) {
		// 用户名密码令牌
		UsernamePasswordToken token = new UsernamePasswordToken(userName, passWord);
		token.setRememberMe(true);
		// 获得当前登录用户对象Subject，现在状态为 “未认证”
		Subject subject = SecurityUtils.getSubject();
		try {
			subject.login(token);
		} catch (UnknownAccountException uae) {
			System.out.println("对用户[" + userName + "]进行登录验证..验证未通过,未知账户");
		} catch (IncorrectCredentialsException ice) {
			System.out.println("对用户[" + userName + "]进行登录验证..验证未通过,错误的凭证");
		} catch (LockedAccountException lae) {
			System.out.println("对用户[" + userName + "]进行登录验证..验证未通过,账户已锁定");
		} catch (ExcessiveAttemptsException eae) {
			System.out.println("对用户[" + userName + "]进行登录验证..验证未通过,用户名或密码错误次数过多");
		} catch (AuthenticationException ae) {
			// 通过处理Shiro的运行时AuthenticationException就可以控制用户登录失败或密码错误时的情景
			System.out.println("对用户[" + userName + "]进行登录验证..验证未通过," + ae.getMessage());
		}

		if (subject.isAuthenticated()) {
			System.out.println("对用户[" + userName + "]进行登录验证..验证通过");
		} else {
			token.clear();
		}
	}

	public void logout() {
		SecurityUtils.getSubject().logout();
	}

	/**
	 * @方法描述: 获取当前用户session
	 * @return
	 */
	public static Session getSession() {
		Session session = SecurityUtils.getSubject().getSession();
		return session;
	}

	/**
	 * @方法描述: 初始化用户session
	 * @param sysUser
	 */
	public static void initSession(SysUser sysUser) {
		Session session = getSession();
		session.setTimeout(1000 * 60 * 30); // timeout:-1000ms 永不超时
		session.setAttribute(GlobalConstants.USER_SESSION, sysUser);
	}

	/**
	 * @方法描述: 获取当前用户对象
	 * @return
	 */
	public static SysUser getCurrentUser() {
		Session session = SecurityUtils.getSubject().getSession();
		if (session != null) {
			return (SysUser) session.getAttribute(GlobalConstants.USER_SESSION);
		} else {
			return null;
		}
	}

	/**
	 * @方法描述: 获取当前登录用户id.
	 * @return
	 */
	public static Integer getCurrentUserId() {
		SysUser user = getCurrentUser();
		if (user != null) {
			return user.getId();
		} else {
			return null;
		}
	}

	/**
	 * @方法描述: 获取当前登录用户名
	 * @return
	 */
	public static String getCurrentUserName() {
		SysUser user = getCurrentUser();
		if (user != null) {
			return user.getUserName();
		} else {
			return "";
		}
	}
}
