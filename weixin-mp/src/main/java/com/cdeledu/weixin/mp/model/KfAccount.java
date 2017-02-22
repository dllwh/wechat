package com.cdeledu.weixin.mp.model;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @类描述: 多客服账号信息
 * @创建者: 皇族灬战狼
 * @创建时间: 2016年8月5日 下午4:10:46
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class KfAccount {
	/** ----------------------------------------------------- Fields start */
	/**
	 * 完整客服账号，格式为：账号前缀@公众号微信号
	 */
	@JSONField(name = "kf_account")
	private String account;
	/**
	 * 客服昵称
	 */
	@JSONField(name = "kf_nick")
	private String nickName;
	/**
	 * 客服工号
	 */
	@JSONField(name = "kf_id")
	private String id;
	/**
	 * 客服头像
	 */
	@JSONField(name = "kf_headimgurl")
	private String headimgurl;

	/** ----------------------------------------------------- Fields end */
	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getHeadimgurl() {
		return headimgurl;
	}

	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}
}
