package com.cdeledu.crawler.leisureTourism.mmPictures.taobao;

import java.io.Serializable;

/**
 * @类描述: 淘女郎信息
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建日期: 2017年8月27日 下午10:30:51
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class TaobaoGirlInfo implements Serializable {
	private static final long serialVersionUID = 1L;
	/** 妹子的身高 */
	private Float height;
	/** 妹子的 体重 */
	private Float weight;
	/** 妹子的姓名 */
	private String realName;
	/** 妹子的id */
	private Integer userID;
	/** 妹子的封面图片小图地址 */
	private String avatarUrl;
	/** 妹子的封面图片大图地址 */
	private String cardUrl;
	/** 妹子所在城市 */
	private String city;
	/** */
	private String identityUrl;
	/** */
	private String modelUrl;
	/** 粉丝数 */
	private Integer totalFanNum;
	/** 点赞数 */
	private Integer totalFavorNum;
	/** */
	private String viewFlag;

	public Float getHeight() {
		return height;
	}

	public void setHeight(Float height) {
		this.height = height;
	}

	public Float getWeight() {
		return weight;
	}

	public void setWeight(Float weight) {
		this.weight = weight;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public Integer getUserID() {
		return userID;
	}

	public void setUserID(Integer userID) {
		this.userID = userID;
	}

	public String getAvatarUrl() {
		return avatarUrl;
	}

	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}

	public String getCardUrl() {
		return cardUrl;
	}

	public void setCardUrl(String cardUrl) {
		this.cardUrl = cardUrl;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getIdentityUrl() {
		return identityUrl;
	}

	public void setIdentityUrl(String identityUrl) {
		this.identityUrl = identityUrl;
	}

	public String getModelUrl() {
		return modelUrl;
	}

	public void setModelUrl(String modelUrl) {
		this.modelUrl = modelUrl;
	}

	public Integer getTotalFanNum() {
		return totalFanNum;
	}

	public void setTotalFanNum(Integer totalFanNum) {
		this.totalFanNum = totalFanNum;
	}

	public Integer getTotalFavorNum() {
		return totalFavorNum;
	}

	public void setTotalFavorNum(Integer totalFavorNum) {
		this.totalFavorNum = totalFavorNum;
	}

	public String getViewFlag() {
		return viewFlag;
	}

	public void setViewFlag(String viewFlag) {
		this.viewFlag = viewFlag;
	}
}