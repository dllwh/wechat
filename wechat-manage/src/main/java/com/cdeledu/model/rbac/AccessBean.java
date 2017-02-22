package com.cdeledu.model.rbac;

import java.io.Serializable;

/**
 * 
 * @ClassName: Power
 * @Description: <ul>
 *               <li>RBAC（Role-Based Access Control:基于角色的访问控制）</li>
 *               <li>权限实体类</li>
 *               <li>1.read : 查询/查看</li>
 *               <li>2.index ：默认模块</li>
 *               <li>3.list:列表</li>
 *               <li>4.resume : 恢复</li>
 *               <li>5.foebid : 禁用</li>
 *               <li>6.delete : 删除</li>
 *               <li>7.uptate : 更新</li>
 *               <li>8.edit: 更新</li>
 *               <li>9.insert : 写入</li>
 *               <li>10.add : 新增</li>
 *               <li>11.public : 公共模块</li>
 *               <li>12.form : 数据管理</li>
 *               <li>13.user : 后台用户</li>
 *               <li>14.role : 角色管理</li>
 *               <li>15.node : 节点管理</li>
 *               <li>16.</li>
 *               <li>1.</li>
 *               </ul
 * @author: 独泪了无痕
 * @date: 2015-8-22 上午01:20:10
 * @version: V1.0
 */
public class AccessBean implements Serializable {
	private static final long serialVersionUID = 1L;
	// 权限名称
	private String name;
	// 显示名称
	private String title;
	// 是否禁用(0:false;1:true;默认是0)
	private int status;
	// 排序
	private int sort;
	// 级别
	private int level;
	// 类型
	private int type;
	// 说明
	private String Desc;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getSort() {
		return sort;
	}
	public void setSort(int sort) {
		this.sort = sort;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getDesc() {
		return Desc;
	}
	public void setDesc(String desc) {
		Desc = desc;
	}
	
}
