数据库表说明:
------------------------------
* **获取表名和注释映射**
	
	String sql = "select table_name tname,comments from all_tab_comments where comments is not null ";
	StringBuilder sqlBuilder = new StringBuilder(sql);
	sqlBuilder.append("order by tname ");
	
* **获取获取表字段注释**

	String sql = "select table_name tname,column_name cname,comments from all_col_comments ";
	StringBuilder sqlBuilder = new StringBuilder(sql);
	// 只查询有注释的字段
	sqlBuilder.append("where comments is not null ");
	sqlBuilder.append("order by table_name,column_name ");