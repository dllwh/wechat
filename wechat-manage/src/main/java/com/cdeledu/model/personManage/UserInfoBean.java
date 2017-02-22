package com.cdeledu.model.personManage;

import java.io.Serializable;
import java.util.Date;

import org.springmodules.validation.bean.conf.loader.annotation.handler.Email;

/**
 * 
 * @类描述: 系统用户基本信息表
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建日期: 2015-8-23 下午08:34:19
 * @版本: V1.2
 * @since: JDK 1.7
 */
public class UserInfoBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	// 登录用户名(数字与字母组成)
	private String loginName;
	// 登录密码(真正的密码与用户名MD5加密)
	private String password;
	// 昵称
	private String nickName;
	// 真实名称
	private String realName;
	// 身份证号
	private String idCard;
	// 性别(0:男;1：女)
	private Integer sex;
	// 头像
	private String headImage;
	// 市
	private Integer city;
	// 省(自治区)
	private Integer province;
	// 国家
	private Integer country;
	// 民族
	private Integer nation;
	// 电子邮箱
	@Email
	private String email;
	// 学历
	private String education;
	// 工作经验
	private String experience;
	// 出生日期(生日)
	private Date birthday;
	// 地址
	private String address;
	// 电话号码
	private String tel;
	// 手机号码
	private String mobilePhone;
	/**
	 * 是否删除<br>
	 * 只有超级管理员才能够物理删除数据,而一般管理员只能逻辑删除<br>
	 * -1:被删除;-2:被锁定;0:正常用户
	 */
	private int delState = Integer.valueOf(0);
	// 创建时间
	private Date createTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getHeadImage() {
		return headImage;
	}

	public void setHeadImage(String headImage) {
		this.headImage = headImage;
	}

	public Integer getCity() {
		return city;
	}

	public void setCity(Integer city) {
		this.city = city;
	}

	public Integer getProvince() {
		return province;
	}

	public void setProvince(Integer province) {
		this.province = province;
	}

	public Integer getCountry() {
		return country;
	}

	public void setCountry(Integer country) {
		this.country = country;
	}

	public Integer getNation() {
		return nation;
	}

	public void setNation(Integer nation) {
		this.nation = nation;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getExperience() {
		return experience;
	}

	public void setExperience(String experience) {
		this.experience = experience;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public int getDelState() {
		return delState;
	}

	public void setDelState(int delState) {
		this.delState = delState;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}
