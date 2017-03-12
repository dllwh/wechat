package com.cdeledu.service.impl.sys;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdeledu.common.base.BaseClass;
import com.cdeledu.dao.ManagerUserDao;
import com.cdeledu.model.rbac.ManagerUser;
import com.cdeledu.model.rbac.ManagerUserRole;
import com.cdeledu.service.sys.ManagerUserService;
import com.cdeledu.util.security.PasswordUtil;

/**
 * @类描述: 业务处理层-系统管理员
 * @创建者: 皇族灬战狼
 * @创建时间: 2016年9月26日 上午10:17:32
 * @版本: V1.0
 * @since: JDK 1.7
 */
@Service
@Transactional(readOnly = true)
public class ManagerUserServiceImpl extends BaseClass implements ManagerUserService {
	private static final long serialVersionUID = 1L;
	/** ----------------------------------------------------- Fields start */
	@Resource
	private ManagerUserDao managerUserDao;

	/** ----------------------------------------------------- Fields end */
	/**
	 * 保存用户基本信息
	 */
	public int insertSelective(ManagerUser record) throws Exception {
		logger.info("保存用户基本信息");
		String newPassWord = PasswordUtil.encrypt(record.getUserName(), record.getPassword());
		record.setPassword(newPassWord);
		return managerUserDao.insertSelective(record);
	}

	/**
	 * 批量保存
	 */
	public int batchInsert(List<ManagerUser> record) throws Exception {
		return managerUserDao.batchInsert(record);
	}

	/**
	 * 删除用户
	 */
	public int delete(ManagerUser record) throws Exception {
		return managerUserDao.delete(record);
	}

	/**
	 * 删除用户通过主键
	 */
	public int deleteByPrimaryKey(Object key) throws Exception {
		return managerUserDao.deleteByPrimaryKey(key);
	}

	/**
	 * 更新用户信息
	 */
	public int updateByPrimaryKey(ManagerUser record) throws Exception {
		return managerUserDao.updateByPrimaryKey(record);
	}

	/**
	 * 返回用户列表
	 */
	public List<ManagerUser> findForJdbc(ManagerUser record) throws Exception {
		return managerUserDao.findForJdbc(record);
	}

	/**
	 * 获取用户列表的个数
	 */
	public int getCountForJdbcParam(ManagerUser record) throws Exception {
		return managerUserDao.getCountForJdbcParam(record);
	}

	/**
	 * 获取用户信息(单独一个)
	 */
	public ManagerUser findOneForJdbc(ManagerUser record) throws Exception {
		return managerUserDao.findOneForJdbc(record);
	}

	/**
	 * 检查用户是否存在
	 */
	public ManagerUser checkUserExits(ManagerUser user) throws Exception {
		// 密码加密
		String password = PasswordUtil.encrypt(user.getUserName(), user.getPassword());
		ManagerUser managerUser = new ManagerUser();
		managerUser.setUserName(user.getUserName());
		managerUser.setPassword(password);

		return managerUserDao.checkUserExits(managerUser);
	}

	/**
	 * 获取用户的角色
	 */
	public ManagerUserRole getUserRole(ManagerUser managerUser) throws Exception {
		return managerUserDao.getUserRole(managerUser);
	}

	/**
	 * 保存管理员登录信息
	 */
	public void saveLoginInfo(ManagerUser managerUser) {

	}

	/**
	 * admin账户初始化
	 */
	public void pwdInit(ManagerUser managerUser) {
	}

	/**
	 * 保存用户-角色关联关系
	 */
	public void saveRoleUser(ManagerUserRole managerUserRole) {
	}

}
