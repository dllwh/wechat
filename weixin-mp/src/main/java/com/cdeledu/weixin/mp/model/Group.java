package com.cdeledu.weixin.mp.model;

import java.io.Serializable;

/**
 * @ClassName: Group
 * @Description: 分组
 * @author: 独泪了无痕
 * @date: 2015年11月22日 下午3:15:46
 * @version: V1.0
 * @since: JDK 1.7
 */
public class Group implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 分组id，由微信分配
	 */
	private int id;
	/**
	 * 分组名
	 */
	private String name;
	/**
	 * 分组内用户数量
	 */
	private int count;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public Group() {
	}

	public Group(String name) {
		this.name = name;
	}

	public Group(int id, String name) {
		this.id = id;
		this.name = name;
	}

	/**
	 * 返回创建分组所需的json格式字符串
	 * 
	 * @return {"group": {"name": "test"}}
	 */
	public String toCreateJson() {
		return String.format("{\"group\":{\"name\":\"%s\"}}", name);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + count;
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	/**
	 * 返回修改分组所需的json格式字符串
	 * 
	 * @return {"group": {"id": 107, "name": "test"}}
	 */
	public String toModifyJson() {
		return String.format("{\"group\":{\"id\":%s,\"name\":\"%s\"}}", id,
				name);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Group) {
			return id == ((Group) obj).getId();
		}
		return super.equals(obj);
	}

}
