package com.cdeledu.crawler.leisureTourism.mmPictures.taobao;

import java.io.Serializable;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 * 
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 淘女郎信息
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建日期: 2017年8月27日 下午10:30:51
 * @版本: V1.0
 * @since: JDK 1.7
 */

class TaobaoGirlInfo implements Serializable {
	private static final long serialVersionUID = 1L;
	/** 身高 .单位：cm */
	private Double height;
	/** 体重 .单位：KG */
	private Double weight;
	/** 姓名 */
	private String realName;
	/** 昵称 */
	private String nickName;
	/** 生日 */
	private String birthday;
	/** 职业 */
	private String occupation;
	/** 血型 */
	private String bloodType;
	/** id */
	private Integer userID;
	/** 妹子个人信息的封面图片地址 */
	private String avatarUrl;
	/** 封面图片地址 */
	private String cardUrl;
	/** 妹子所在城市 */
	private String city;
	/** */
	private String identityUrl;
	/** */
	private String modelUrl;
	/** 粉丝数 */
	private Long totalFanNum;
	/** 点赞数 */
	private Long totalFavorNum;
	/** */
	private String viewFlag;
	/** 个人主页地址 */
	private String girlsHURL;

	public Double getHeight() {
		return height;
	}

	public void setHeight(Double height) {
		this.height = height;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public String getBloodType() {
		return bloodType;
	}

	public void setBloodType(String bloodType) {
		this.bloodType = bloodType;
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

	public Long getTotalFanNum() {
		return totalFanNum;
	}

	public void setTotalFanNum(Long totalFanNum) {
		this.totalFanNum = totalFanNum;
	}

	public Long getTotalFavorNum() {
		return totalFavorNum;
	}

	public void setTotalFavorNum(Long totalFavorNum) {
		this.totalFavorNum = totalFavorNum;
	}

	public String getViewFlag() {
		return viewFlag;
	}

	public void setViewFlag(String viewFlag) {
		this.viewFlag = viewFlag;
	}

	public String getGirlsHURL() {
		return girlsHURL;
	}

	public void setGirlsHURL(String girlsHURL) {
		this.girlsHURL = girlsHURL;
	}
}