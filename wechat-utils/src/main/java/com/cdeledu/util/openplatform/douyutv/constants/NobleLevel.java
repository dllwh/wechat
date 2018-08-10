package com.cdeledu.util.openplatform.douyutv.constants;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 用户等级(贵族等级)
 * 
 *       <pre>
 *       目前斗鱼共有7中类型的贵族
 *       </pre>
 * 
 * @创建者: 皇族灬战狼
 * @联系方式: duleilewuhen@sina.com
 * @创建时间: 2018年8月10日 下午8:48:52
 * @版本: V1.0
 * @since: JDK 1.7
 */
public enum NobleLevel {
	LEVEL_0(0, "游侠"),
	LEVEL_1(1, "骑士"),
	LEVEL_2(2, "子爵"),
	LEVEL_3(3, "伯爵"),
	LEVEL_4(4,"公爵"),
	LEVEL_5(5, "国外"),
	LEVEL_6(6, "皇帝");
	private Integer code;
	private String message;

	NobleLevel(Integer code, String message) {
		this.code = code;
		this.message = message;
	}

	public Integer getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}
}
