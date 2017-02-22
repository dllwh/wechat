数据库表说明:
------------------------------
* **获取表名和注释映射**

	String sql = "SELECT table_name tname, TABLE_COMMENT comments FROM INFORMATION_SCHEMA. TABLES ";
	StringBuilder sqlBuilder = new StringBuilder(sql);
	sqlBuilder.append("WHERE TABLE_COMMENT is not NULL AND TABLE_COMMENT != '' AND TABLE_TYPE LIKE '%table%' ");
	sqlBuilder.append("table_schema = 'db_wechat'");
	sqlBuilder.append("order by tname ");
* **获取获取表字段注释**

	String sql = "SELECT TABLE_NAME tname, COLUMN_NAME cname, 	COLUMN_COMMENT comments FROM INFORMATION_SCHEMA. COLUMNS ";
	StringBuilder sqlBuilder = new StringBuilder(sql);
	// 只查询有注释的字段
	sqlBuilder.append(WHERE table_schema = 'db_wechat' ");
	sqlBuilder.append("order by table_name,column_name ");
	
information_schema说明:
------------------------------	
	SCHEMATA表：提供了当前mysql实例中所有数据库的信息。是show databases的结果取之此表。

	TABLES表：提供了关于数据库中的表的信息（包括视图）。详细表述了某个表属于哪个schema，表类型，表引擎，创建时间等信息。是show tables from schemaname的结果取之此表。

	COLUMNS表：提供了表中的列信息。详细表述了某张表的所有列以及每个列的信息。是show columns from schemaname.tablename的结果取之此表。

	STATISTICS表：提供了关于表索引的信息。是show index from schemaname.tablename的结果取之此表。

	USER_PRIVILEGES（用户权限）表：给出了关于全程权限的信息。该信息源自mysql.user授权表。是非标准表。

	SCHEMA_PRIVILEGES（方案权限）表：给出了关于方案（数据库）权限的信息。该信息来自mysql.db授权表。是非标准表。

	TABLE_PRIVILEGES（表权限）表：给出了关于表权限的信息。该信息源自mysql.tables_priv授权表。是非标准表。

	COLUMN_PRIVILEGES（列权限）表：给出了关于列权限的信息。该信息源自mysql.columns_priv授权表。是非标准表。

	CHARACTER_SETS（字符集）表：提供了mysql实例可用字符集的信息。是SHOW CHARACTER SET结果集取之此表。

	COLLATIONS表：提供了关于各字符集的对照信息。

	COLLATION_CHARACTER_SET_APPLICABILITY表：指明了可用于校对的字符集。这些列等效于SHOW COLLATION的前两个显示字段。

	TABLE_CONSTRAINTS表：描述了存在约束的表。以及表的约束类型。

	KEY_COLUMN_USAGE表：描述了具有约束的键列。

	ROUTINES表：提供了关于存储子程序（存储程序和函数）的信息。此时，ROUTINES表不包含自定义函数（UDF）。名为“mysql.proc name”的列指明了对应于INFORMATION_SCHEMA.ROUTINES表的mysql.proc表列。

	VIEWS表：给出了关于数据库中的视图的信息。需要有show views权限，否则无法查看视图信息。

	TRIGGERS表：提供了关于触发程序的信息。必须有super权限才能查看该表