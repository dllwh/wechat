package com.cdeledu.util;

import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.subject.Subject;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.cdeledu.common.constants.FilterHelper;
import com.cdeledu.common.constants.GlobalConstants;
import com.cdeledu.core.shiro.token.ShiroHelper;
import com.cdeledu.model.rbac.SysMenu;
import com.cdeledu.model.rbac.SysUser;
import com.cdeledu.model.rbac.SysUserRole;
import com.cdeledu.service.sys.ManagerUserService;
import com.cdeledu.service.sys.SysMenuService;

/**
 * @类描述: 上下文工具类:Web常用工具集，用于获取当前登录用户，请求信息等
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建日期: 2016年4月4日 下午6:31:51
 * @版本: V1.2
 * @since: JDK 1.7
 */
public class WebUtilHelper {
	/** ----------------------------------------------------- Fields start */
	private static final ResourceBundle sysConfig = ResourceBundle
			.getBundle("properties/sysConfig");
	private static ManagerUserService userService = SpringContextUtil.getBean("managerUserService");
	private static SysMenuService sysMenuService = SpringContextUtil.getBean("sysMenuService");

	/** ----------------------------------------------------- Fields end */

	/**
	 * @方法:获取项目配置文件参数
	 * @创建人:独泪了无痕
	 * @param name
	 * @return
	 */
	public static final String getConfigByName(String name) {
		return sysConfig.getString(name);
	}

	/**
	 * @方法:SpringMvc下获取request,尝试获取当前请求的HttpServletRequest实例
	 * @创建人:独泪了无痕
	 * @return
	 */
	public static HttpServletRequest getHttpServletRequest() {
		try {
			return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
					.getRequest();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * @方法:SpringMvc下获取session
	 * @创建人:独泪了无痕
	 * @return
	 */
	public static HttpSession getSession() {
		return getHttpServletRequest().getSession();
	}

	/**
	 * @方法:获取session里的用户对象
	 * @创建人:独泪了无痕
	 * @return
	 */
	public static final SysUser getCurrenLoginUser() {
		HttpSession session = getSession();
		SysUser sysUser = null;
		if (session.getAttributeNames().hasMoreElements()) {
			sysUser = (SysUser) session.getAttribute(GlobalConstants.USER_SESSION);
			if (sysUser != null) {
				return sysUser;
			}
		}
		return sysUser;
	}

	/**
	 * @方法描述: 在HttpSession中设置当前登录的用户
	 * @param user
	 * @return 当前登录的用户
	 */
	public static void setCurrentLoginUser(SysUser user) {
		HttpSession session = getSession();
		session.setMaxInactiveInterval(60 * 30);
		session.setAttribute(GlobalConstants.USER_SESSION, user);
	}

	/**
	 * 获取当前用户角色列表
	 */
	public static List<SysUserRole> getRoleList() {
		List<SysUserRole> roleList = null;
		try {
			roleList = userService.getUserRole(getCurrenLoginUser());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return roleList;
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
	 * @方法描述:验证当前用户是否拥有该角色
	 * @param roleCode
	 * @return
	 */
	public static boolean hasRole(String roleCode) {
		Subject subject = ShiroHelper.getSubject();
		return subject != null && roleCode != null && roleCode.length() > 0
				&& subject.hasRole(roleCode);
	}

	/**
	 * 是否拥有该权限
	 * 
	 * @param permission
	 *            权限标识
	 * @return
	 */
	public boolean hasPermission(String permission) {
		Subject subject = ShiroHelper.getSubject();
		return subject != null && permission != null && permission.length() > 0
				&& subject.isPermitted(permission);
	}

	/**
	 * @方法描述 : 已认证通过的用户。
	 * @return
	 */
	public static boolean isAuthenticated() {
		Subject subject = ShiroHelper.getSubject();
		return subject != null && subject.isAuthenticated();
	}

	/**
	 * @方法描述 : 未认证通过用户
	 * @return
	 */
	public static boolean notAuthenticated() {
		return !isAuthenticated();
	}

	/**
	 * @方法描述 : 判断当前用户是否是超级管理员
	 * @return
	 */
	public static boolean isAdmin() {
		List<SysUserRole> roleList = getRoleList();
		for (SysUserRole sysUserRole : roleList) {
			if (FilterHelper.ADMIN_ROLE_CODE.equals(sysUserRole.getRoleCode())) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 获取当前用户授权菜单
	 */
	public static List<SysMenu> getMenuList() {
		List<SysMenu> menuList = null;
		try {
			menuList = sysMenuService.getUserMenu(getCurrenLoginUser());
		} catch (Exception e) {

		}
		return menuList;
	}
}
