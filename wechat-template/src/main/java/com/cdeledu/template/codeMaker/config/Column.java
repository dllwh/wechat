package com.cdeledu.template.codeMaker.config;

import java.sql.ResultSet;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 数据列定义
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2017年9月14日 下午10:48:53
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class Column {
	/** ----------------------------------------------------- Fields start */
	private String name;
	private String desc;
	private String type;
	private boolean nullable;
	private int length;
	private Boolean isPrikey;
	private Boolean isAutoIncrement;

	/** ----------------------------------------------------- Fields end */

	public Column(ResultSet rs) throws Exception {
		this.name = rs.getString("name");
		this.desc = rs.getString("desc");
		this.type = rs.getString("type");
		this.nullable = rs.getBoolean("nullable");
		String length = rs.getString("length");
		this.isPrikey = rs.getString("key").contains("PRI");
		this.isAutoIncrement = rs.getString("extra").contains("auto_increment");
		this.length = length != null ? Integer.parseInt(length) : 0;
	}

	public Column(String name, String desc, String type, boolean nullable, int length) {
		this.name = name;
		this.desc = desc;
		this.type = type;
		this.nullable = nullable;
		this.length = length;
	}

	public Boolean IsPrikey() {
		return isPrikey;
	}

	public Boolean IsAutoIncrement() {
		return isAutoIncrement;
	}

	public String getName() {
		return name;
	}

	public String getDesc() {
		return desc;
	}

	public String getType() {
		return type;
	}

	public boolean isNullable() {
		return nullable;
	}

	public int getLength() {
		return length;
	}

	public String getField() {
		return CodeMakerUtil.toFieldName(name);
	}

	public String getFieldType() {
		type = type.toLowerCase();
		if (type.contains("varchar") || type.contains("text") || type.contains("char")) {
			return "String";
		} else if (type.equals("int") || type.equals("tinyint")) {
			return "Integer";
		} else if (type.contains("bigint") || type.contains("long") || type.contains("number")) {
			return "Long";
		} else if (type.contains("double")) {
			return "Double";
		} else if (type.contains("date") || type.contains("time")) {
			return "Date";
		} else if (type.contains("decimal")) {
			return "BigDecimal";
		}
		return "unknown";
	}

	public String getImport() {
		if (type.contains("date") || type.contains("time")) {
			return "java.util.Date";
		} else if (type.contains("decimal")) {
			return "java.math.BigDecimal";
		} else {
			return null;
		}
	}

	@Override
	public String toString() {
		return "Column [name=" + name + ", desc=" + desc + ", type=" + type + ", nullable="
				+ nullable + ", length=" + length + ", isPrikey=" + isPrikey + ", isAutoIncrement="
				+ isAutoIncrement + "]";
	}
}
