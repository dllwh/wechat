package com.cdeledu.model.cms.notice;

import java.util.Date;

import com.cdeledu.common.base.PageEntity;

/**
 * 
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 文章实体类
 * @创建者: 皇族灬战狼
 * @联系方式: duleilewuhen@sina.com
 * @创建时间: 2018年7月18日 下午8:38:01
 * @版本: V1.0
 * @since: JDK 1.7
 */
public final class Article extends PageEntity {
	private static final long serialVersionUID = 1L;
	private int id;
	private String title;
	private int author;
	private Integer status;
	private Integer lookType;
	private Integer ifShow;
	private Date createTime;
	private Date updateTime;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getAuthor() {
		return author;
	}

	public void setAuthor(int author) {
		this.author = author;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getLookType() {
		return lookType;
	}

	public void setLookType(Integer lookType) {
		this.lookType = lookType;
	}

	public Integer getIfShow() {
		return ifShow;
	}

	public void setIfShow(Integer ifShow) {
		this.ifShow = ifShow;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Override
	public String toString() {
		return "Article [id=" + id + ", title=" + title + ", author=" + author + ", status="
				+ status + ", lookType=" + lookType + ", ifShow=" + ifShow + ", createTime="
				+ createTime + ", updateTime=" + updateTime + "]";
	}
}
