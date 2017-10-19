package com.cdeledu.model.rbac;

import java.util.Date;
import java.util.List;

import org.springmodules.validation.bean.conf.loader.annotation.handler.NotBlank;

import com.cdeledu.common.base.DataEntity;
import com.google.common.collect.Lists;

/**
 * 
 * @类描述: 系统管理员用户表
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建日期: 2015-8-23 下午08:34:19
 * @版本: V1.2
 * @since: JDK 1.7
 */
public class SysUser extends DataEntity<SysUser> {
	private static final long serialVersionUID = 1L;
	// 用户名(数字与字母组成)
	@NotBlank(message = "登陆账号不能为空")
	private String userName;
	// 密码(真正的密码与用户名加密之后的结果)
	@NotBlank(message = "登陆密码不能为空")
	private String password;
	// 用户类型(超级管理员、系统管理员、管理员)
	private Integer UserType;
	// 真实姓名
	private String realName;
	// 用户邮箱
	private String email;
	// email是否经过验证
	private Integer emailstatus;
	// 性别
	private Integer userSex;
	// 用户手机号码
	private String mobile;
	// 用户电话号码
	private String telephone;
	// 个性签名
	private String signature;
	// 最后登录时间
	private Date last_login_time;
	// 最后登录ip
	private String last_login_ip;
	// 登录次数(查询操作日志的结果数据)
	private Integer login_count;
	@NotBlank(message = "验证码")
	private String imageCaptcha;
	// 是否允许登陆;1:允许,默认值;0:不允许
	private Integer loginFlag;
	private SysRole role; // 根据角色查询用户条件
	private List<SysRole> roleList = Lists.newArrayList(); // 拥有角色列表

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getUserType() {
		return UserType;
	}

	public void setUserType(Integer userType) {
		UserType = userType;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getEmailstatus() {
		return emailstatus;
	}

	public void setEmailstatus(Integer emailstatus) {
		this.emailstatus = emailstatus;
	}

	public Integer getUserSex() {
		return userSex;
	}

	public void setUserSex(Integer userSex) {
		this.userSex = userSex;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public Date getLast_login_time() {
		return last_login_time;
	}

	public void setLast_login_time(Date last_login_time) {
		this.last_login_time = last_login_time;
	}

	public String getLast_login_ip() {
		return last_login_ip;
	}

	public void setLast_login_ip(String last_login_ip) {
		this.last_login_ip = last_login_ip;
	}

	public Integer getLogin_count() {
		return login_count;
	}

	public void setLogin_count(Integer login_count) {
		this.login_count = login_count;
	}

	public String getImageCaptcha() {
		return imageCaptcha;
	}

	public void setImageCaptcha(String imageCaptcha) {
		this.imageCaptcha = imageCaptcha;
	}

	public Integer getLoginFlag() {
		return loginFlag;
	}

	public void setLoginFlag(Integer loginFlag) {
		this.loginFlag = loginFlag;
	}

	public SysRole getRole() {
		return role;
	}

	public void setRole(SysRole role) {
		this.role = role;
	}

	public List<SysRole> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<SysRole> roleList) {
		this.roleList = roleList;
	}

	@Override
	public String toString() {
		return "ManagerUser [userName=" + userName + ", password=" + password + ", UserType="
				+ UserType + ", realName=" + realName + ", email=" + email + ", emailstatus="
				+ emailstatus + ", userSex=" + userSex + ", mobile=" + mobile + ", telephone="
				+ telephone + ", signature=" + signature + ", last_login_time=" + last_login_time
				+ ", last_login_ip=" + last_login_ip + ", login_count=" + login_count
				+ ", imageCaptcha=" + imageCaptcha + ", loginFlag=" + loginFlag + ", role=" + role
				+ ", roleList=" + roleList + "]";
	}
	
}