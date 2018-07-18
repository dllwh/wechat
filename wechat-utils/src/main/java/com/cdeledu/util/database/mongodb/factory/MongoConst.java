package com.cdeledu.util.database.mongodb.factory;
/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: mongo用到的比较常量定义
 * @创建者: 皇族灬战狼
 * @联系方式: duleilewuhen@sina.com
 * @创建时间: 2018年7月17日 下午10:06:23
 * @版本: V1.0
 * @since: JDK 1.7
 */
public enum MongoConst {
	GT("$gt"), 
	LT("$lt"), 
	GTE("$gte"), 
	LTE("$lte"), 
	AND("and"), 
	OR("or"), 
	NOT("not");

	private String compareIdentify;

	MongoConst(String compareIdentify) {
		this.compareIdentify = compareIdentify;
	}

	public String getCompareIdentify() {
		return compareIdentify;
	}
}
