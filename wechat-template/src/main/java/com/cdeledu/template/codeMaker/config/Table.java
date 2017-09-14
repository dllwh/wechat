package com.cdeledu.template.codeMaker.config;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述:表定义
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2017年9月14日 下午10:47:36
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class Table {
	/** ----------------------------------------------------- Fields start */
	private String name;
	private String desc;

	/** ----------------------------------------------------- Fields end */

	public Table(String name, String desc) {
		this.name = name;
		this.desc = desc;
	}

	public String getName() {
		return name;
	}

	public String getDesc() {
		return desc;
	}

	@Override
	public String toString() {
		return "Table [name=" + name + ", desc=" + desc + "]";
	}
}
