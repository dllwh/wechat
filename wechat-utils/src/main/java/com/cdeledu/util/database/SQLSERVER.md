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


	SELECT
		a.COLUMN_NAME,
		a.DATA_TYPE,
		a.CHARACTER_MAXIMUM_LENGTH,
		b.value
	FROM information_schema.COLUMNS AS a
	LEFT JOIN sys.extended_properties AS b
		ON a.TABLE_NAME = OBJECT_NAME(b.major_id) AND a.ORDINAL_POSITION = b.minor_id

	SELECT
		a.TABLE_NAME,
		a.COLUMN_NAME,
		a.DATA_TYPE,
		a.CHARACTER_MAXIMUM_LENGTH,
		b.value,
		ROW_NUMBER() OVER (ORDER BY a.TABLE_NAME) AS rowNum
	FROM information_schema.COLUMNS AS a
	LEFT JOIN sys.extended_properties AS b
		ON a.TABLE_NAME = OBJECT_NAME(b.major_id) AND a.ORDINAL_POSITION = b.minor_id
