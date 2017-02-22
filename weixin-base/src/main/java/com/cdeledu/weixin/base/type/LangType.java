package com.cdeledu.weixin.base.type;

/**
 * 
 * @ClassName: Lang
 * @Description: 国家地区语言版本
 * @author: 独泪了无痕
 * @date: 2015-10-13 下午10:58:48
 * @version: V1.0
 */
public enum LangType {
	/**
	 * 简体
	 */
	zh_CN("简体"),
	/**
	 * 繁体
	 */
	zh_TW("繁体"),
	/**
	 * 英语
	 */
	en("英语");

	private String desc;

	LangType(String desc) {
		this.desc = desc;
	}

	public String getDesc() {
		return desc;
	}
}
