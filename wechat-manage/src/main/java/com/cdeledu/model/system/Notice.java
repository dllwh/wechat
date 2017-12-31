package com.cdeledu.model.system;

import java.io.Serializable;
import java.util.Date;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 通知表
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2017年12月31日 下午9:04:06
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class Notice implements Serializable {
	/** ----------------------------------------------------- Fields start */
	private static final long serialVersionUID = 1L;
	/** ----------------------------------------------------- Fields end */
	/** 主键 */
	private Integer id;
	/** 标题 */
	private String title;
	/** 类型 */
	private Integer type;
	/** 内容 */
	private String content;
	/** 创建时间 */
	private Date createTime;
	/** 创建人 */
	private Integer creator;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getCreator() {
		return creator;
	}

	public void setCreator(Integer creator) {
		this.creator = creator;
	}
}
