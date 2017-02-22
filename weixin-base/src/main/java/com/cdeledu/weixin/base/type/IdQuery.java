package com.cdeledu.weixin.base.type;

import java.io.Serializable;

/**
 * @ClassName: IdQuery
 * @Description: ID查询
 * @author: 独泪了无痕
 * @date: 2015-10-13 下午10:19:09
 * @version: V1.0
 */
public class IdQuery implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * id值
	 */
	private String id;
	/**
	 * id类型
	 */
	private IdType type;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public IdType getType() {
		return type;
	}

	public void setType(IdType type) {
		this.type = type;
	}

	public IdQuery(String id, IdType idType) {
		this.id = id;
		this.type = idType;
	}

	@Override
	public String toString() {
		return String.format("%s=%s", type.getName(), id);
	}
}
