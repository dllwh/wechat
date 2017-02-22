package com.cdeledu.util.database.model;

/**
 * @类描述: mongodb的查询操作符
 * @创建者: 皇族灬战狼
 * @创建时间: 2016年3月12日 下午2:44:40
 * @版本: V1.0
 * @since: JDK 1.7
 */
public enum MongoCondition {
	/**
	 * 比较查询操作符
	 */

	// 匹配那些指定键的键值中包含数组，而且该数组包含条件指定数组的所有元素的文档
	ALL("all", "$all"),
	// 匹配键值大于指定值的所有文档
	GT("gt", "$gt"),
	// 匹配键值不小于指定值的所有文档
	GTE("gte", "$gte"),
	// 匹配键值小于指定值的所有文档
	LT("lt", "$lt"),
	// 匹配键值不大于指定值的所有文档
	LTE("lte", "$lte"),
	// 匹配键值等于指定数组中任意值的文档。类似sql中in.
	IN("in", "$in"),
	// 匹配键不存在或者键值不等于指定数组的任意值的文档
	NIN("nin", "$nin"),
	// 匹配键值不等于指定值的文档
	NE("ne", "$ne"),

	/**
	 * 逻辑查询操作符
	 */

	// $and操作符使用短路操作，若第一个表达式的值为“false”,余下的表达式将不会执行
	AND("and", "$and"),
	// $nor执行逻辑NOR运算,指定一个至少包含两个表达式的数组，选择出都不满足该数组中所有表达式的文档
	NOR("nor", "$nor"),
	// $not执行逻辑NOT运算，选择出不能匹配表达式的文档 ，包括没有指定键的文档。
	// $not操作符不能独立使用，必须跟其他操作一起使用（除$regex）
	NOT("not", "$not"),
	// $or执行逻辑OR运算,指定一个至少包含两个表达式的数组，选择出至少满足数组中一条表达式的文档
	OR("or", "$or"),

	/**
	 * 元素查询操作符
	 */

	// 如果$exists的值为true,选择存在该字段的文档；若值为false则选择不包含该字段的文档
	EXISTS("exists", "$exists"),
	// 匹配字段值对（divisor）取模，值等于（remainder）的文档
	MOD("mod", "$mod"),
	//
	TYPE("type", "$type"),
	// $regex操作符查询中可以对字符串的执行正则匹配
	REGEX("regex", "$regex"),
	//
	WHERE("where", "$where");

	// 操作符名字
	private String name;
	// 操作符的值
	private String val;

	private MongoCondition(String name, String val) {
		this.name = name;
		this.val = val;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVal() {
		return val;
	}

	public void setVal(String val) {
		this.val = val;
	}

	public static MongoCondition getByName(String name) {
		for (MongoCondition mongo : MongoCondition.values()) {
			if (mongo.getName().equals(name)) {
				return mongo;
			}
		}
		return null;
	}

	public static MongoCondition getByVal(String val) {
		for (MongoCondition mongo : MongoCondition.values()) {
			if (mongo.getVal().equals(val)) {
				return mongo;
			}
		}
		return null;
	}
}
