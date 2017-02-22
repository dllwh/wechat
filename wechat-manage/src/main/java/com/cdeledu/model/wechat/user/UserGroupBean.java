package com.cdeledu.model.wechat.user;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 
 * @ClassName: UserGroupBean
 * @Description: 微信 -- 用户组服务实体类
 * @author: 独泪了无痕
 * @date: 2015-8-25 上午02:12:09
 * @version: V1.0
 */
public class UserGroupBean implements Serializable{
	private static final long serialVersionUID = 1L;
	// 组名
	private String title;
	// 创建时间
	private Timestamp createTime;
	// 是否删除;默认值是0,可以删除
	private int idDel = 0;
	// 用户id
	private Integer userCode;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public int getIdDel() {
		return idDel;
	}
	public void setIdDel(int idDel) {
		this.idDel = idDel;
	}
	public Integer getUserCode() {
		return userCode;
	}
	public void setUserCode(Integer userCode) {
		this.userCode = userCode;
	}
}
