package com.cdeledu.model.api.token;

import java.io.Serializable;
import java.util.Date;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 用户Token
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2018年1月28日 下午5:01:06
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class TokenEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long userId;
	private String token;
	/** 过期时间 */
	private Date expireTime;
	/** 更新时间 */
	private Date updateTime;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Date getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(Date expireTime) {
		this.expireTime = expireTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
}
