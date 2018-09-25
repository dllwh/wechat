package com.cdeledu.core.factory;

import com.cdeledu.core.shiro.service.ShiroService;
import com.cdeledu.service.log.LoginLogService;
import com.cdeledu.service.log.OperateLogService;
import com.cdeledu.service.sys.CountService;
import com.cdeledu.service.sys.DictService;
import com.cdeledu.service.sys.ManagerUserService;
import com.cdeledu.service.sys.RoleService;
import com.cdeledu.service.sys.ScheduleJobLogService;
import com.cdeledu.service.sys.SysAreaService;
import com.cdeledu.service.sys.SysMenuService;
import com.cdeledu.service.sys.SystemService;
import com.cdeledu.util.SpringContextUtil;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 常量的生产工厂
 * @创建者: 皇族灬战狼
 * @创建时间: 2017年12月13日 上午9:15:10
 * @版本: V1.0
 * @since: JDK 1.7
 */
public final class ConstantFactory {
	/** 管理用户业务处理层 */
	public static ManagerUserService userService = SpringContextUtil.getBean("managerUserService");
	/** 菜单业务处理层 */
	public static SysMenuService sysMenuService = SpringContextUtil.getBean("sysMenuService");
	/** 系统业务处理 */
	public static SystemService systemService = SpringContextUtil.getBean("systemService");
	/** 行政区域业务处理 */
	public static SysAreaService sysAreaService = SpringContextUtil.getBean("sysAreaService");
	/** 授权认证服务处理层 */
	public static ShiroService shiroService = SpringContextUtil.getBean("shiroService");
	/** 定时任务日志 */
	public static ScheduleJobLogService scheduleJobLogService = SpringContextUtil.getBean("scheduleJobLogService");
	/** 系统角色业务处理层 */
	public static RoleService roleService = SpringContextUtil.getBean("roleService");
	/** 系统统计业务处理层 */
	public static CountService countService = SpringContextUtil.getBean("countService");
	/** 操作日志处理层 */
	public static OperateLogService operateLogService = SpringContextUtil.getBean("operateLogService");
	/** 登录、退出日志处理层 */
	public static LoginLogService loginLogService = SpringContextUtil.getBean("loginLogService");
	/** 数据字典 */
	public static DictService dictService = SpringContextUtil.getBean("dictService");
}
