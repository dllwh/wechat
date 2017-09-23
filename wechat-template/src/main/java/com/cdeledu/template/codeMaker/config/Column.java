package com.cdeledu.template.codeMaker.config;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.lang3.StringUtils;

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
	/** 名称 */
	private String name;
	/** 说明 */
	private String desc;
	/** 类型 */
	private String type;
	/** 是否允许为空 */
	private boolean nullable = false;
	/** 长度 */
	private Integer length;
	/** 是否主键 */
	private Boolean isPrikey = false;
	/** 是否自增 */
	private Boolean isAutoIncrement = false;

	/** ----------------------------------------------------- Fields end */

	public Column(ResultSet rs) throws Exception {
		this.name = rs.getString("name");
		String desc = rs.getString("description");
		if (StringUtils.isNotBlank(desc)) {
			this.desc = desc;
		} else {
			this.desc = rs.getString("name");
		}

		this.type = rs.getString("type");
		this.nullable = rs.getBoolean("nullable");

		if (isExistColumn(rs, "key")) {
			this.isPrikey = rs.getString("key").contains("PRI");
		} else if (isExistColumn(rs, "isPrikey")) {
			this.isPrikey = rs.getInt("isPrikey") == 1;
		}
		if (isExistColumn(rs, "extra")) {
			this.isAutoIncrement = rs.getString("extra").contains("auto_increment");
		} else if (isExistColumn(rs, "isAutoIncrement")) {
			this.isAutoIncrement = rs.getInt("isAutoIncrement") == 1;
		}

		String length = rs.getString("length");
		this.length = length != null ? Integer.parseInt(length) : 0;
	}

	public Column(String name, String desc, String type, boolean nullable, Integer length) {
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

	/**
	 * @方法描述: 获取引用
	 * @return
	 */
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

	/**
	 * @方法描述: 判断查询结果集中是否存在某列
	 * @param rs
	 *            查询结果集
	 * @param columnName
	 *            列名
	 * @returntrue 存在; false 不存在
	 */
	public boolean isExistColumn(ResultSet rs, String columnName) {
		try {
			if (rs.findColumn(columnName) > 0) {
				return true;
			}
		} catch (SQLException e) {
			return false;
		}

		return false;
	}
}
