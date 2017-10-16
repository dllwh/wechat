package com.cdeledu.util;

import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.cdeledu.common.constants.GlobalConstants;
import com.cdeledu.model.SessionInfo;
import com.cdeledu.model.rbac.ManagerUser;
import com.cdeledu.model.rbac.Menu;
import com.cdeledu.model.rbac.Role;
import com.google.common.collect.Lists;

/**
 * @类描述: 上下文工具类:Web常用工具集，用于获取当前登录用户，请求信息等
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建日期: 2016年4月4日 下午6:31:51
 * @版本: V1.2
 * @since: JDK 1.7
 */
public class WebUtilHelper {
	/** ----------------------------------------------------- Fields start */
	private static Logger logger = LoggerFactory.getLogger(WebUtilHelper.class);
	private static final ResourceBundle sysConfig = ResourceBundle.getBundle("properties/sysConfig");
	// private static final ResourceBundle dbConfig = ResourceBundle.getBundle("properties/dbConfig");
	
	// private static ManagerUserDao userDao = SpringContextHolder.getBean(ManagerUserDao.class);
	// private static RoleDao roleDao = SpringContextHolder.getBean(RoleDao.class);
	public static final String USER_CACHE = "userCache";
	public static final String USER_CACHE_ID_ = "id_";
	private static HttpSession session = null;
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
			return null;
		}
	}

	/**
	 * @方法:SpringMvc下获取session
	 * @创建人:独泪了无痕
	 * @return
	 */
	public static HttpSession getSession() {
		if (null == session) {
			session = getHttpServletRequest().getSession();
		}
		return session;
	}

	/**
	 * @方法:获取session里的用户对象
	 * @创建人:独泪了无痕
	 * @return
	 */
	public static final ManagerUser getCurrenLoginUser() {
		HttpSession session = getSession();
		session.setMaxInactiveInterval(-1);
		SessionInfo sessionInfo = null;
		if (session.getAttributeNames().hasMoreElements()) {
			sessionInfo = (SessionInfo) session.getAttribute(GlobalConstants.USER_SESSION);
			if (null != sessionInfo) {
				if (null != sessionInfo.getManagerUser()) {
					return sessionInfo.getManagerUser();
				}
			}
		}
		return null;
	}

	/**
	 * @方法描述: 在HttpSession中设置当前登录的用户
	 * @param user
	 * @return 当前登录的用户
	 */
	public static ManagerUser setCurrentLoginUser(ManagerUser user) {
		HttpSession session = getSession();
		SessionInfo sessionInfo = new SessionInfo();
		sessionInfo.setManagerUser(user);
		session.setMaxInactiveInterval(60 * 30);
		session.setAttribute(GlobalConstants.USER_SESSION, sessionInfo);
		return user;
	}

	/**
	 * @方法描述: 在HttpSession中移除当前登录的用户
	 * @param user
	 * @return
	 */
	public static void removeCurrentLoginUser() {
		if(logger.isDebugEnabled()){
			
		}
	}

	/**
	 * 获取当前用户角色列表
	 */
	public static List<Role> getRoleList() {
		List<Role> roleList = Lists.newArrayList();
		return roleList;
	}

	/**
	 * 获取当前用户授权菜单
	 */
	public static List<Menu> getMenuList() {
		List<Menu> menuList = Lists.newArrayList();
		return menuList;
	}
	
	/**
	 * @方法描述:
	 * 
	 * <pre>
	 * 访问权限及初始化按钮权限(控制按钮的显示)
	 * 判断是否拥有当前点击菜单的权限（内部过滤,防止通过url进入跳过菜单权限）
	 * 根据点击的菜单的xxx.do去菜单中的URL去匹配，当匹配到了此菜单，判断是否有此菜单的权限，没有的话跳转到404页面
	 * 根据按钮权限，授权按钮(当前点的菜单和角色中各按钮的权限匹对)
	 * </pre>
	 * 
	 * @param menuUrl
	 *            菜单路径
	 * @return
	 */
	public static boolean hasJurisdiction(String menuUrl) {
		return false;
	}
	/**
	 * @方法描述: <pre>
	 *  按钮权限(方法中校验)
	 *  判断是否拥有当前点击菜单的权限（内部过滤,防止通过url进入跳过菜单权限）
	 *  根据点击的菜单的xxx.do去菜单中的URL去匹配，当匹配到了此菜单，判断是否有此菜单的权限，没有的话跳转到404页面
	 *  根据按钮权限，授权按钮(当前点的菜单和角色中各按钮的权限匹对)
	 * </pre>
	 * @param menuUrl
	 * @param type
	 * @return
	 */
	public static boolean buttonJurisdiction(String menuUrl, String type){
		return true;	
	}
}
