数据库表说明:
------------------------------
* **获取表名和注释映射**
		
	SELECT
		st.name,
		ISNULL(sep.value,st.name)   table_desc
	FROM sys.tables st
	LEFT JOIN sys.extended_properties sep ON st.object_id = sep.major_id AND sep.minor_id = 0
	ORDER BY st.name
	
	
* **获取获取表字段注释**

	StringBuilder sqlBuilder = new StringBuilder("select a.name tname, b.name cname, c.value as comments ");
	sqlBuilder.append("from sys.tables a left join sys.columns b on a.object_id=b.object_id ");
	sqlBuilder.append("left join sys.extended_properties c on a.object_id=c.major_id ");
	sqlBuilder.append("where c.minor_id<>0 and b.column_id=c.minor_id ");
	sqlBuilder.append("and a.schema_id in (  ");
	sqlBuilder.append("  select schema_id from sys.schemas  ");
	sqlBuilder.append(")  and c.value is not null");

	