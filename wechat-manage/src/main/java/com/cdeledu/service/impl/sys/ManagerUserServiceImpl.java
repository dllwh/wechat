package com.cdeledu.service.impl.sys;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdeledu.common.base.BaseClass;
import com.cdeledu.dao.BaseDaoSupport;
import com.cdeledu.model.rbac.SysUser;
import com.cdeledu.model.rbac.SysUserRole;
import com.cdeledu.service.sys.ManagerUserService;
import com.cdeledu.util.security.PasswordUtil;

/**
 * @类描述: 业务处理层-系统管理员
 * @创建者: 皇族灬战狼
 * @创建时间: 2016年9月26日 上午10:17:32
 * @版本: V1.0
 * @since: JDK 1.7
 */
@Service("managerUserService")
@Transactional(readOnly = false)
public class ManagerUserServiceImpl extends BaseClass implements ManagerUserService {
	private static final long serialVersionUID = 1L;
	private final static String prefix = "manageUserDaoImpl.";
	/** ----------------------------------------------------- Fields start */
	@Resource
	private BaseDaoSupport<?> baseDao;

	/** ----------------------------------------------------- Fields end */
	/**
	 * 保存用户基本信息
	 */
	@Transactional(readOnly = false)
	public Integer insert(SysUser record) throws Exception {
		logger.info("保存用户基本信息");
		String newPassWord = PasswordUtil.encrypt(record.getUserName(), record.getPassword());
		record.setPassword(newPassWord);
		return baseDao.insert(prefix + "insertSelective", record);
	}

	/**
	 * 批量保存
	 */
	@Transactional(readOnly = false)
	public Integer batchInsert(List<SysUser> record) throws Exception {
		return null;
	}

	/**
	 * 删除用户
	 */
	@Transactional(readOnly = false)
	public Integer delete(Object record) throws Exception {
		return null;
	}

	@Transactional(readOnly = false)
	public Integer batchDelete(List<SysUser> parameter) throws Exception {
		return null;
	}

	/**
	 * 更新用户信息
	 */
	@Transactional(readOnly = false)
	public Integer update(SysUser sysUser) throws Exception {
		return baseDao.update(prefix+"updateByPrimaryKey", sysUser);
	}

	@Transactional(readOnly = false)
	public Integer batchUpdate(List<SysUser> parameter) throws Exception {
		return null;
	}

	/**
	 * 返回用户列表
	 */
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<SysUser> findForJdbc(SysUser record) throws Exception {
		return ( List<SysUser>)baseDao.findListForJdbcParam(prefix+"findForJdbc", record);
	}

	/**
	 * 获取用户列表的个数
	 */
	@Transactional(readOnly = true)
	public Integer getCountForJdbcParam(SysUser record) throws Exception {
		return baseDao.getCountForJdbcParam(prefix+"getCountForJdbcParam", record);
	}

	/**
	 * 获取用户信息(单独一个)
	 */
	@Transactional(readOnly = true)
	public SysUser findOneForJdbc(SysUser record) throws Exception {
		return (SysUser)baseDao.findOneForJdbcParam(prefix+"findOneForJdbc", record);
	}

	/**
	 * 检查用户是否存在
	 */
	@Transactional(readOnly = true)
	public SysUser checkUserExits(SysUser sysUser) throws Exception {
		return (SysUser) baseDao.findOneForJdbcParam(prefix + "checkUserExits", sysUser);
	}
	/**
	 * 检查用户是否存在
	 */
	@Transactional(readOnly = true)
	public SysUser checkUserExits(String userName, String passWord) throws Exception {
		SysUser managerUser = new SysUser();
		managerUser.setUserName(userName);
		managerUser.setPassword(passWord);
		return checkUserExits(managerUser);
	}

	/**
	 * 获取用户的角色
	 */
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<SysUserRole> getUserRole(SysUser sysUser) throws Exception {
		return (List<SysUserRole>)baseDao.findListForJdbcParam(prefix+"getUserRole", sysUser);
	}

	/**
	 * 保存管理员登录信息
	 */
	@Transactional(readOnly = false)
	public void saveLoginInfo(SysUser sysUser) {

	}

	/**
	 * admin账户初始化
	 */
	@Transactional(readOnly = false)
	public void pwdInit(SysUser sysUser) {
	}

	/**
	 * 保存用户-角色关联关系
	 */
	@Transactional(readOnly = false)
	public void saveRoleUser(SysUserRole userRole) throws Exception{
		baseDao.insert(prefix+"saveRoleUser", userRole);
	}

	@Transactional(readOnly = true)
	public SysUser getUser(String userName) throws Exception {
		if (StringUtils.isNotBlank(userName)) {
			SysUser user = new SysUser();
			user.setUserName(userName);
			return (SysUser) baseDao.findOneForJdbcParam(prefix + "findOneForJdbc", user);
		} else {
			return null;
		}
	}
}
