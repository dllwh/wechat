package com.cdeledu.common.shiro;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

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

	/**
	 * @方法描述: 为当前登录的Subject授予角色和权限
	 * @说明: 该方法的调用时机为需授权资源被访问时,:并且每次访问需授权资源时都会执行该方法中的逻辑
	 * @param principals
	 * @return
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		// ① 获取当前登录的用户名
		String currentUsername = (String) principals.fromRealm(getName()).iterator().next();
		// ② 从数据库中获取当前登录用户的详细信息
		// ③ 获取当前登录用户的角色
		// ④ 获取权限
		// ⑤ 为当前用户设置角色和权限

		if (StringUtils.isNotBlank(currentUsername)) {
			List<String> roleList = new ArrayList<String>();
			List<String> permissionList = new ArrayList<String>();
			SimpleAuthorizationInfo simpleAuthorInfo = new SimpleAuthorizationInfo();
			simpleAuthorInfo.addRoles(roleList);
			simpleAuthorInfo.addStringPermissions(permissionList);
			return simpleAuthorInfo;
		} else {
			// 自动跳转到unauthorizedUrl指定的地址
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
		if (StringUtils.isNotBlank(currentUsername)) {
			AuthenticationInfo authcInfo = null;
			return authcInfo;
		} else {
			return null;
		}
	}

}
