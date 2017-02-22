package com.cdeledu.service;

import java.util.Set;

import com.cdeledu.model.system.LoginLog;

/**
 * @类描述: 系统业务处理接口
 * @创建者: 皇族灬战狼
 * @创建时间: 2016年6月1日 下午4:58:15
 * @版本: V1.0
 * @since: JDK 1.7
 */
public interface SystemService {
	/**
	 * @方法: 日志添加
	 * @创建人:独泪了无痕
	 * @param LogContent
	 *            内容
	 * @param loglevel
	 *            级别
	 * @param operatetype
	 *            类型
	 */
	public void addLog(String LogContent, Integer loglevel, Integer operatetype);
	/**
	 * @方法: 登录\退出日志
	 * @创建人:独泪了无痕
	 * @param LogContent
	 *            内容
	 * @param loglevel
	 *            级别
	 * @param operatetype
	 *            类型
	 */
	public void addLoginLog(LoginLog loginLog);

	/**
	 * @方法:根据用户ID 和 菜单Id 获取 具有操作权限的按钮Codes
	 * @创建人:独泪了无痕
	 * @param userId
	 * @param functionId
	 * @return
	 */
	public Set<String> getOperationCodesByUserIdAndFunctionId(Integer userId, Integer functionId);

	/**
	 * @方法: 根据角色ID 和 菜单Id 获取 具有操作权限的按钮Codes
	 * @创建人:独泪了无痕
	 * @param roleId
	 * @param functionId
	 * @return
	 */
	public Set<String> getOperationCodesByRoleIdAndFunctionId(Integer roleId, Integer functionId);

	/**
	 * @方法描述: 根据当前登录用户与请求资源地址判断是否有访问权限
	 * @创建者: 皇族灬战狼
	 * @创建时间: 2016年6月8日 上午9:19:34
	 * @param userId
	 * @param requestPath
	 * @return
	 */
	public Integer getAuthByuserIdAndAuthPath(Integer userId, String requestPath);
}
