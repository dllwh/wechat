package com.cdeledu.weixin.mp.model;

import java.io.Serializable;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * @ClassName: Following
 * @Description: 公众号关注者信息
 * @author: 独泪了无痕
 * @date: 2015年10月30日 下午6:04:36
 * @version: V1.0
 */
public class Following implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 关注该公众账号的总用户数
	 */
	private int total;
	/**
	 * 拉取的OPENID个数，最大值为10000
	 */
	private int count;
	/**
	 * 列表数据，OPENID的列表
	 */
	@JSONField(name = "data")
	private JSONObject dataJson;
	/**
	 * 拉取列表的后一个用户的OPENID
	 */
	@JSONField(name = "next_openid")
	private String nextOpenId;
	/**
	 * 用户详情列表
	 */
	private List<User> userList;

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public JSONObject getDataJson() {
		return dataJson;
	}

	public void setDataJson(JSONObject dataJson) {
		this.dataJson = dataJson;
	}

	public String getNextOpenId() {
		return nextOpenId;
	}

	public void setNextOpenId(String nextOpenId) {
		this.nextOpenId = nextOpenId;
	}

	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}
}
