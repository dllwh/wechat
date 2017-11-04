package com.cdeledu.common.shiro;

import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.cdeledu.model.rbac.SysUser;
import com.cdeledu.model.rbac.SysUserRole;
import com.cdeledu.service.sys.ManagerUserService;
import com.cdeledu.util.WebUtilHelper;
import com.google.common.collect.Sets;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 自定义的指定Shiro验证用户登录的类
 * @创建者: 皇族灬战狼
 * @创建时间: 2017年10月20日 下午4:18:04
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class ShiroRealm extends AuthorizingRealm {
	@Autowired
	private ManagerUserService userService;

	/**
	 * @方法描述: 为当前登录的Subject授予角色和权限
	 * @说明: 该方法的调用时机为需授权资源被访问时,:并且每次访问需授权资源时都会执行该方法中的逻辑
	 * @param principals
	 * @return
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		try {
			// ① 获取当前登录的用户名
			String currentUsername = (String) principals.fromRealm(getName()).iterator().next();
			if (StringUtils.isBlank(currentUsername)) {
				return null;// 自动跳转到unauthorizedUrl指定的地址
			}
			// ② 从数据库中获取当前登录用户的详细信息
			SysUser sysUser = new SysUser();
			sysUser.setUserName(currentUsername);
			// ③ 获取当前登录用户的角色
			Set<String> roleList = Sets.newConcurrentHashSet();
			Set<String> permissionList = Sets.newConcurrentHashSet();
			List<SysUserRole> sysUserRolelist = userService.getUserRole(sysUser);
			for (SysUserRole role : sysUserRolelist) {
				if (role != null) {
					roleList.add(role.getRoleCode());
				}
			}
			// ④ 获取权限

			SimpleAuthorizationInfo simpleAuthorInfo = new SimpleAuthorizationInfo();
			// ⑤ 1.为当前用户设置角色
			simpleAuthorInfo.addRoles(roleList);
			// ⑤ 2.为当前用户设置权限
			simpleAuthorInfo.addStringPermissions(permissionList);
			return simpleAuthorInfo;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * @方法描述: 验证当前登录的Subject
	 * @说明: 该方法的调用时机为LoginController.login()方法中执行Subject.login()时
	 * @param token
	 * @return
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authtoken)
			throws AuthenticationException {
		UsernamePasswordToken token = (UsernamePasswordToken) authtoken;
		// ① 获取当前登录的用户名
		String currentUsername = token.getUsername();
		String passWord = String.valueOf(token.getPassword());
		SysUser sysUser = new SysUser();
		SysUser currentUser = null;
		SimpleAuthenticationInfo authcInfo = null;
		try {
			sysUser.setUserName(currentUsername);
			int userCount = userService.getCountForJdbcParam(sysUser);
			if (userCount <= 0) {
				throw new UnknownAccountException();
			}
			currentUser = userService.checkUserExits(currentUsername, passWord);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (currentUser != null) {
			// 账号未通过审核
			if (currentUser.getIsEnabled() != 1) {
				throw new UnknownAccountException();
			}
			// 账号未通过审核
			if (currentUser.getIsVisible() != 1) {
				throw new AuthenticationException("账号未通过审核");
			}
			// 账号不允许登录
			if (currentUser.getLoginFlag() != 1) {
				throw new AuthenticationException("账号不允许登录");
			}

			// 账号被锁定
			if (currentUser.getIsLocked() != 1) {
				throw new LockedAccountException("账号被锁定");
			}

			WebUtilHelper.setCurrentLoginUser(currentUser);
			
			authcInfo = new SimpleAuthenticationInfo(currentUser,currentUser.getPassword(), getName());
		} else {
			throw new LockedAccountException("用户名或密码错误");
		}
		return authcInfo;
	}
}
