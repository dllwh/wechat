package com.cdeledu.core.shiro.token;

import javax.servlet.http.HttpServletRequest;

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

import com.cdeledu.common.base.ResponseBean;
import com.cdeledu.common.constants.MessageConstant;
import com.cdeledu.model.rbac.SysUser;

public class ShiroHelper {
	/** ----------------------------------------------------- Fields start */
	/** 定界符 */
	public static final String NAMES_DELIMETER = ",";

	/** ----------------------------------------------------- Fields end */

	public static ResponseBean login(String userName, String passWord) {
		// 用户名密码令牌
		UsernamePasswordToken token = new UsernamePasswordToken(userName, passWord);
		token.setRememberMe(false);
		String logMsg = "", resultMsg = "";
		ResponseBean responseBean = new ResponseBean();
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

		responseBean.setSuccess(suc);
		responseBean.setMsg(resultMsg);
		responseBean.setObj(logMsg);
		return responseBean;
	}

	/**
	 * 
	 * @方法:获取访问请求地址(不带参数)
	 * @创建人:独泪了无痕
	 * @param httpRequest
	 * @return
	 */
	public static String getAccessAddress(HttpServletRequest httpRequest) {
		// 获取URI
		String accessAddress = httpRequest.getRequestURI();
		// 获取basePath
		String basePath = httpRequest.getContextPath();
		if (null != accessAddress && accessAddress.startsWith(basePath)) {
			accessAddress = accessAddress.replaceFirst(basePath, "");
			if (accessAddress.startsWith("/")) {
				accessAddress = accessAddress.substring(1);
			}
		}
		return accessAddress;
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
	 * @方法描述: 获取当前获取授权用户id.
	 * @return
	 */
	public static Integer getCurrentUserId() {
		SysUser user = ShiroHelper.getPrincipal();
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
		SysUser user = ShiroHelper.getPrincipal();
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
		SysUser user = ShiroHelper.getPrincipal();
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
		SysUser user = ShiroHelper.getPrincipal();
		if (user != null) {
			return user.getNickName();
		} else {
			return "";
		}
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

	/**
	 * @方法:验证当前用户是否属于该角色？,使用时与lacksRole 搭配使用
	 * @创建人:独泪了无痕
	 * @param roleCode
	 *            角色名代码
	 * @return 属于该角色：true，否则false
	 */
	public static boolean hasRole(String roleCode) {
		return getSubject() != null && roleCode != null && roleCode.length() > 0
				&& getSubject().hasRole(roleCode);
	}

	/**
	 * @方法:与hasRole标签逻辑相反，当用户不属于该角色时验证通过。
	 * @创建人:独泪了无痕
	 * @param roleName
	 *            角色名代码
	 * @return 不属于该角色：true，否则false
	 */
	public static boolean lacksRole(String roleCode) {
		return !hasRole(roleCode);
	}

	/**
	 * @方法:验证当前用户是否属于以下任意一个角色。
	 * @创建人:独泪了无痕
	 * @param roleCodes
	 *            角色列表
	 * @return 属于:true,否则false
	 */
	public static boolean hasAnyRole(String roleCodes) {
		boolean hasAnyRole = false;
		Subject subject = getSubject();
		if (subject != null && roleCodes != null && roleCodes.length() > 0) {
			for (String role : roleCodes.split(NAMES_DELIMETER)) {
				if (subject.hasRole(role.trim())) {
					hasAnyRole = false;
					break;
				}
			}
		}
		return hasAnyRole;
	}

	/**
	 * @方法:验证当前用户是否属于以下所有角色
	 * @创建人:独泪了无痕
	 * @param roleNames
	 *            角色列表
	 * @return 属于:true,否则false
	 */
	public static boolean hasAllRoles(String roleNames) {
		boolean hasAllRole = true;
		Subject subject = getSubject();
		if (subject != null && roleNames != null && roleNames.length() > 0) {
			for (String role : roleNames.split(NAMES_DELIMETER)) {
				if (!subject.hasRole(role.trim())) {
					hasAllRole = false;
					break;
				}
			}
		}
		return hasAllRole;
	}

	/**
	 * @方法:验证当前用户是否拥有指定权限,使用时与lacksPermission 搭配使用
	 * @创建人:独泪了无痕
	 * @param permission
	 *            权限名
	 * @return 拥有权限：true，否则false
	 */
	public static boolean hasPermission(String permissionCode) {
		return getSubject() != null && permissionCode != null && permissionCode.length() > 0
				&& getSubject().isPermitted(permissionCode);
	}

	/**
	 * @方法:与hasPermission标签逻辑相反，当前用户没有制定权限时，验证通过
	 * @创建人:独泪了无痕
	 * @param permission
	 * @return
	 */
	public static boolean lacksPermission(String permission) {
		return !hasPermission(permission);
	}

	/**
	 * @方法:已认证通过的用户。不包含已记住的用户，这是与user标签的区别所在。与notAuthenticated搭配使用
	 * @创建人:独泪了无痕
	 * @return 通过身份验证：true，否则false
	 */
	public static boolean isAuthenticated() {
		return getSubject() != null && getSubject().isAuthenticated();
	}

	/**
	 * @方法:未认证通过用户，与authenticated标签相对应。与guest标签的区别是，该标签包含已记住用户。。
	 * @创建人:独泪了无痕
	 * @return 没有通过身份验证：true，否则false
	 */
	public static boolean notAuthenticated() {
		return !isAuthenticated();
	}
}
