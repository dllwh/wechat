package com.cdeledu.util.database;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.cdeledu.util.database.model.ColumnProperty;
import com.cdeledu.util.database.model.DatabaseProperty;
import com.cdeledu.util.database.model.PrimaryKey;
import com.cdeledu.util.database.model.TableProperty;
import com.google.common.collect.Lists;
import com.mysql.jdbc.Statement;

/**
 * @类描述:
 * 		<ul>
 *       <li>是 Apache 组织提供的一个开源 JDBC工具类库,是轻量级的ORM工具</li>
 *       <li>提供了数据库操作的简单实现,包含增、删、改、查、批量以及事务等操作</li>
 *       <li>ResultSetHandler实现类介绍</li>
 *       <li>①、ArrayHandler:将查询结果的第一行数据,保存到Object数组中</li>
 *       <li>②、ArrayListHandler:将查询的结果,每一行先封装到Object数组中,然后将数据存入List集合</li>
 *       <li>③、BeanHandler:将查询结果的第一行数据,封装到user对象</li>
 *       <li>④、BeanListHandler:将查询结果的每一行封装到user对象,然后再存入List集合</li>
 *       <li>⑤、ColumnListHandler:将查询结果的指定列的数据封装到List集合中</li>
 *       <li>⑥、MapHandler:将查询结果的第一行数据封装到map集合(key==列名,value==列值)</li>
 *       <li>⑦、MapListHandler:将查询结果的每一行封装到map(key==列名,value==列值),再将map集合存入List集合
 *       </li>
 *       <li>⑧、BeanMapHandler:将查询结果的每一行数据,封装到User对象,再存入mao集合中(key==列名,value==列值)
 *       </li>
 *       <li>⑨、KeyedHandler:将查询的结果的每一行数据,封装到map1(key==列名,value==列值
 *       ),然后将map1集合(有多个)存入map2集合(只有一个)</li>
 *       <li>⑩、ScalarHandler:封装类似count、avg、max、min、sum......函数的执行结果,处理单行记录,
 *       只返回结果集第一行中的指定字段,如未指定字段,则返回第一个字段</li>
 *       </ul>
 * @创建者: 皇族灬战狼
 * @创建时间: 2015年6月25日 下午4:22:41
 * @版本: V2.0
 * @since: JDK 1.7
 */
public class DataTableHelper {
	/** ----------------------------------------------------- Fields start */
	private static Logger logger = Logger.getLogger(DataTableHelper.class);
	private String dbUrl = null;
	private String dbUserName = null;
	private String dbPassword = null;
	private String jdbcName = null;
	/** 获取数据源的接口 */
	private DataSource dataSource;
	/** SQL执行工具 :实例化查询接口 */
	private static DataTableHelper instance;
	/** 数据库连接 */
	private Connection myDbcon = null;

	/** ----------------------------------------------------- Fields end */
	/** ----------------------------------------------------- 私有方法 开始 */

	private DataTableHelper(String dbUrl, String dbUserName, String dbPassword, String jdbcName) {
		this.dbUrl = dbUrl;
		this.dbUserName = dbUserName;
		this.dbPassword = dbPassword;
		this.jdbcName = jdbcName;
	}

	/**
	 * @方法描述: 获取数据源并加载相关配置
	 * @return
	 */
	private synchronized DataSource getDataSource() {
		if (null == dataSource) {
			try {
				BasicDataSource dbcpDataSource = new BasicDataSource();
				dbcpDataSource.setUrl(dbUrl);
				dbcpDataSource.setDriverClassName(jdbcName);
				dbcpDataSource.setUsername(dbUserName);
				dbcpDataSource.setPassword(dbPassword);
				dbcpDataSource.setDefaultAutoCommit(true);
				dbcpDataSource.setMaxActive(100);
				dbcpDataSource.setMaxIdle(30);
				dbcpDataSource.setMaxWait(500L);
				dataSource = dbcpDataSource;
			} catch (Exception ex) {
				logger.info("dbcp数据源初始化失败:" + ex.getMessage(), ex);
			}
		}
		return dataSource;
	}

	/**
	 * @方法描述: 执行SQL
	 * @param sqlList
	 * @throws SQLException
	 */
	private void executeSql(Connection conn, List<String> sqlList) throws SQLException {
		Statement st = (Statement) conn.createStatement();
		for (String sql : sqlList) {
			st.executeUpdate(sql);
		}
		closeAll(conn, null, st, null);
	}

	/** ----------------------------------------------------- 私有方法 结束 */

	/**
	 * @方法描述: 根据数据库的连接参数，获取指定表的基本信息：字段名、字段类型、字段注释
	 * @param dbUrl
	 *            数据库连接url
	 * @param dbUserName
	 *            据库登陆用户名
	 * @param dbPassword
	 *            数据库登陆密码
	 * @param jdbcName
	 *            数据库连接驱动
	 * @return
	 */
	public static DataTableHelper getInstance(String dbUrl, String dbUserName, String dbPassword,
			String jdbcName) {
		if (instance == null) {
			synchronized (DataTableHelper.class) {
				if (instance == null) {
					instance = new DataTableHelper(dbUrl, dbUserName, dbPassword, jdbcName);
				}
			}
		}
		return instance;
	}

	/**
	 * @方法描述:获取数据源链接
	 * @return
	 * @throws SQLException
	 */
	public synchronized Connection getConnection() throws SQLException {
		if (null == myDbcon) {
			return getDataSource().getConnection();
		}
		return myDbcon;
	}

	/**
	 * @方法描述: 关闭数据库连接
	 * @param conn
	 * @param rs
	 * @param st
	 * @param ps
	 * @throws SQLException
	 */
	public void closeAll(Connection conn, ResultSet rs, Statement st, PreparedStatement ps)
			throws SQLException {
		if (rs != null) {
			rs.close();
		}
		if (st != null) {
			st.close();

		}
		if (ps != null) {
			ps.close();
		}
		if (conn != null) {
			conn.close();
		}
	}

	/**
	 * @方法描述: 开始事务
	 * @return
	 */
	public boolean beginTransaction() {
		try {
			this.myDbcon.setAutoCommit(false);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * @方法描述: 提交事务
	 * @return
	 */
	public boolean commitTransaction() {
		try {
			this.myDbcon.commit();
			this.myDbcon.setAutoCommit(true);
			return true;
		} catch (SQLException e) {
			return false;
		}
	}

	/**
	 * @方法描述: 回滚事物
	 * @return
	 */
	public boolean rollbackTransaction() {
		try {
			this.myDbcon.rollback();
			this.myDbcon.setAutoCommit(true);
			return true;
		} catch (SQLException e) {
			return false;
		}
	}

	/**
	 * 
	 * @方法描述: 获取连接
	 * @return
	 * @throws SQLException
	 */
	public QueryRunner getQueryRunner() throws SQLException {
		return new QueryRunner(getDataSource());
	}

	/**
	 * @方法描述: 得到查询记录的条数
	 * @说明 使用ScalarHandler处理单行记录,只返回结果集第一行中的指定字段,如未指定字段,则返回第一个字段
	 * @param sql
	 *            必须为select count(*) from tableName的格式
	 * @return
	 */
	public int getCount(QueryRunner runner, String sql) {
		try {
			return runner.query(sql, new ScalarHandler<Long>()).intValue();
		} catch (SQLException sqlEx) {
			logger.error("数据查询操作异常:", sqlEx);
		}
		return 0;
	}

	/**
	 * @方法描述: 插入新数据
	 * @param sql
	 * @return
	 */
	public int insert(QueryRunner runner, String sql) {
		try {
			return runner.insert(sql, new ScalarHandler<Long>()).intValue();
		} catch (SQLException sqlEx) {
			logger.error("数据保存异常:", sqlEx);
		}
		return 0;
	}

	/**
	 * @方法描述:
	 * 		<ul>
	 *        <li>根据已经存在SQL文件初始化数据库</li>
	 *        <li>从SQL文件中读取SQL语句，每行一条，末尾没有分号</li>
	 *        </ul>
	 * @param rootPath
	 * 
	 */
	public void initDataBase(Connection conn, String rootPath) {
		List<String> sqlList = new ArrayList<String>();
		FileInputStream in = null;
		BufferedReader br = null;
		try {
			in = new FileInputStream(rootPath);
			// 指定读取文件时以UTF-8的格式读取
			br = new BufferedReader(new InputStreamReader(in, "UTF-8"));
			String instring;
			while ((instring = br.readLine()) != null) {
				if (instring.length() != 0) {
					String line = instring.trim();
					sqlList.add(line);
				}
			}
			executeSql(conn, sqlList);
		} catch (Exception exp) {
			logger.error("数据异常:", exp);
		} finally {
			IOUtils.closeQuietly(br);
			IOUtils.closeQuietly(in);
		}
	}

	/**
	 * @方法描述: 获取数据库相关信息
	 * @throws SQLException
	 */
	public DatabaseProperty getDataBaseInfo(Connection conn) throws SQLException {
		DatabaseProperty database = new DatabaseProperty();
		DatabaseMetaData metadata = conn.getMetaData();
		database.setUserName(metadata.getUserName());// 数据库已知的用户
		database.setDataBaseUrl(metadata.getURL());// 数据库URL
		database.setReadOnly(metadata.isReadOnly());// 是否允许只读
		database.setProductName(metadata.getDatabaseProductName());// 当前数据库是什么数据库,比如oracle、access等
		database.setVersion(metadata.getDatabaseProductVersion()); // 当前数据库的版本
		database.setDriverName(metadata.getDriverName()); // 驱动程序的名称
		database.setDriverVersion(metadata.getDriverVersion()); // 当前驱动程序的版本

		return database;
	}

	/**
	 * @方法描述: 获取数据库中所有的表信息
	 * @returnParam tableName 列对应的表名
	 * @returnParam category 表类别(可为null)
	 * @returnParam tableSchem 表模式（可能为空）,在oracle中获取的是命名空间,其它数据库未知
	 * @returnParam remarks 表备注
	 * @returnParam tableType 数据库表的类型
	 * @throws SQLException
	 */
	public List<Map<String, Object>> getTablesList(Connection conn) throws SQLException {
		List<Map<String, Object>> tableList = Lists.newArrayList();
		Map<String, Object> resultMap;
		DatabaseMetaData metadata = conn.getMetaData();
		String[] types = { "TABLE" };
		/**
		 * 获取给定类别中使用的表的描述。 方法原型:ResultSet getTables(String catalog,String
		 * schemaPattern,String tableNamePattern,String[] types); catalog -
		 * 表所在的类别名称;""表示获取没有类别的列,null表示获取所有类别的列。 schema -
		 * 表所在的模式名称(oracle中对应于Tablespace);""表示获取没有模式的列,null标识获取所有模式的列;
		 * 可包含单字符通配符("_"),或多字符通配符("%"); tableNamePattern -
		 * 表名称;可包含单字符通配符("_"),或多字符通配符("%"); types - 表类型数组; "TABLE"、"VIEW"、
		 * "SYSTEM TABLE"、"GLOBAL TEMPORARY"、"LOCAL TEMPORARY"、"ALIAS" 和
		 * "SYNONYM";null表示包含所有的表类型;可包含单字符通配符("_"),或多字符通配符("%");
		 */
		ResultSet rs = metadata.getTables(null, null, null, types);
		while (rs.next()) {
			resultMap = new HashMap<>();
			resultMap.put("tableName", rs.getString("TABLE_NAME"));// 列对应的表名
			resultMap.put("category", rs.getString("TABLE_CAT")); // 表类别(可为null)
			resultMap.put("tableSchem", rs.getString("TABLE_SCHEM")); // 表模式（可能为空）,在oracle中获取的是命名空间,其它数据库未知
			resultMap.put("remarks", rs.getString("REMARKS")); // 表备注
			resultMap.put("tableType", rs.getString("TABLE_TYPE"));// 数据库表的类型
			// System.err.println(rs.getString("TYPE_CAT"));
			// System.err.println(rs.getString("TYPE_SCHEM"));
			// System.err.println(rs.getString("TYPE_NAME"));
			// System.err.println(rs.getString("SELF_REFERENCING_COL_NAME"));
			// System.err.println(rs.getString("REF_GENERATION"));
			tableList.add(resultMap);
		}
		return tableList;
	}

	/**
	 * @方法描述: 获取某表信息
	 * @throws SQLException
	 */
	public TableProperty getTablesInfo(Connection conn, String tableName) throws SQLException {
		DatabaseMetaData metadata = conn.getMetaData();
		TableProperty info = null;
		/**
		 * 获取给定类别中使用的表的描述。 方法原型:ResultSet getTables(String catalog,String
		 * schemaPattern,String tableNamePattern,String[] types); catalog -
		 * 表所在的类别名称;""表示获取没有类别的列,null表示获取所有类别的列。 schema -
		 * 表所在的模式名称(oracle中对应于Tablespace);""表示获取没有模式的列,null标识获取所有模式的列;
		 * 可包含单字符通配符("_"),或多字符通配符("%"); tableNamePattern -
		 * 表名称;可包含单字符通配符("_"),或多字符通配符("%"); types - 表类型数组; "TABLE"、"VIEW"、
		 * "SYSTEM TABLE"、"GLOBAL TEMPORARY"、"LOCAL TEMPORARY"、"ALIAS" 和
		 * "SYNONYM";null表示包含所有的表类型;可包含单字符通配符("_"),或多字符通配符("%");
		 */
		ResultSet rs = metadata.getTables(null, null, tableName, new String[] { "TABLE", "VIEW" });
		while (rs.next()) {
			info = new TableProperty();
			info.setTableName(rs.getString("TABLE_NAME"));// 列对应的表名
			info.setCategory(rs.getString("TABLE_CAT"));// 表类别(可为null)
			info.setTableName(rs.getString("TABLE_SCHEM")); // 表模式（可能为空）,在oracle中获取的是命名空间,其它数据库未知
			info.setTableComment(rs.getString("REMARKS")); // 表备注
			info.setTableType(rs.getString("TABLE_TYPE")); // 数据库表的类型
		}
		return info;
	}

	/**
	 * @方法描述: 获取表主键信息
	 * @throws SQLException
	 */
	public List<PrimaryKey> getPrimaryKeysInfo(Connection conn, String tableName)
			throws SQLException {
		DatabaseMetaData metadata = conn.getMetaData();
		List<PrimaryKey> resultList = Lists.newArrayList();
		PrimaryKey info = null;
		/**
		 * 获取对给定表的主键列的描述 方法原型:ResultSet getPrimaryKeys(String catalog,String
		 * schema,String table); catalog - 表所在的类别名称;""表示获取没有类别的列,null表示获取所有类别的列。
		 * schema - 表所在的模式名称(oracle中对应于Tablespace);""表示获取没有模式的列,null标识获取所有模式的列;
		 * 可包含单字符通配符("_"),或多字符通配符("%"); table - 表名称;可包含单字符通配符("_"),或多字符通配符("%");
		 */
		ResultSet rs = metadata.getPrimaryKeys(null, null, tableName);
		while (rs.next()) {
			info = new PrimaryKey();
			info.setTableName(rs.getString("TABLE_NAME "));// 列对应的表名
			info.setColumnName(rs.getString("COLUMN_NAME"));// 列名
			info.setPkName(rs.getString("PK_NAME")); // 主键名称
			info.setKeySeq(rs.getShort("KEY_SEQ")); // 序列号
			resultList.add(info);
		}
		return resultList;
	}

	/**
	 * @方法描述: 数据库表结构情况
	 * @param tableName
	 * @throws SQLException
	 */
	public List<ColumnProperty> getColumnsInfoByResult(Connection conn, String tableName)
			throws SQLException {
		List<ColumnProperty> resList = Lists.newArrayList();
		ColumnProperty resultMap = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String sql = "select * from " + tableName;
		ps = conn.prepareStatement(sql);
		rs = ps.executeQuery();
		ResultSetMetaData rsmd = rs.getMetaData();

		/** ResultSet对象中的列数 */
		int columnCount = rsmd.getColumnCount();
		for (int i = 1; i < columnCount; i++) {
			resultMap = new ColumnProperty();
			resultMap.setCatalogName(rsmd.getCatalogName(i));// 列所在的表目录名称
			resultMap.setColumnName(rsmd.getColumnName(i)); // 列所在的名称
			resultMap.setColumnType(rsmd.getColumnType(i));// 列所在的的数据类型,返回SqlType中的编号
			resultMap.setColumnTypeName(rsmd.getColumnTypeName(i)); // 列对应数据类型的类
			resultMap.setColumnClassName(rsmd.getColumnTypeName(i)); // 列对应数据类型的类
			resultMap.setColumnLabel(rsmd.getColumnLabel(i)); // 获取用于打印输出和显示的指定列的建议标题
			resultMap.setColumnDisplaySize(rsmd.getColumnDisplaySize(i)); // 列在数据库中类型的最大字符个数
			resultMap.setPrecision(rsmd.getPrecision(i)); // 列类型的精确度(类型的长度)
			resultMap.setScale(rsmd.getScale(i)); // 列小数点后的位数
			resultMap.setSchemaName(rsmd.getSchemaName(i)); // 列对应的模式的名称（应该用于Oracle）
			resultMap.setAutoIncrement(rsmd.isAutoIncrement(i)); // 列是否自动递增
			resultMap.setCurrency(rsmd.isCurrency(i)); // 列在数据库中是否为货币型
			resultMap.setNullable(rsmd.isNullable(i) == 0 ? true : false); // 列是否为空
			resultMap.setReadOnly(rsmd.isReadOnly(i)); // 列是否为只读
			// resultMap.put("remark", "");
			// resultMap.put("defaultValue", "");

			resList.add(resultMap);
		}
		return resList;
	}

	/**
	 * @方法描述: 获取表中列值信息
	 * @param conn
	 * @param tableName
	 * @return
	 * @returnParam catalogName 列所在的表目录名称
	 * @returnParam columnName 列所在的名称
	 * @returnParam columnType 列所在的的数据类型,返回SqlType中的编号
	 * @returnParam columnTypeName 列对应数据类型的类
	 * @returnParam columnDisplaySize 列在数据库中类型的最大字符个数
	 * @returnParam precision 列类型的精确度(类型的长度)
	 * @returnParam remark 数据库表字段的描述
	 * @returnParam defaultValue 默认值
	 * @throws SQLException
	 */
	public List<ColumnProperty> getColumnsInfoByDataBase(Connection conn, String tableName)
			throws SQLException {
		List<ColumnProperty> resList = Lists.newArrayList();
		DatabaseMetaData dbmd = conn.getMetaData();
		ColumnProperty resultMap = null;
		/**
		 * 获取可在指定类别中使用的表列的描述。 方法原型:ResultSet getColumns(String catalog,String
		 * schemaPattern,String tableNamePattern,String columnNamePattern)
		 * catalog - 表所在的类别名称;""表示获取没有类别的列,null表示获取所有类别的列。 schema -
		 * 表所在的模式名称(oracle中对应于Tablespace);""表示获取没有模式的列,null标识获取所有模式的列;
		 * 可包含单字符通配符("_"),或多字符通配符("%"); tableNamePattern -
		 * 表名称;可包含单字符通配符("_"),或多字符通配符("%"); columnNamePattern - 列名称;
		 * ""表示获取列名为""的列(当然获取不到);null表示获取所有的列;可包含单字符通配符("_"),或多字符通配符("%");
		 */
		ResultSet rs = dbmd.getColumns(null, null, tableName, null);
		while (rs.next()) {
			resultMap = new ColumnProperty();
			resultMap.setCatalogName(rs.getString("TABLE_CAT")); // 列所在的表目录名称
			resultMap.setColumnName(rs.getString("COLUMN_NAME"));// 列所在的名称
			resultMap.setColumnType(rs.getInt("DATA_TYPE")); // 列所在的的数据类型
			resultMap.setColumnTypeName(rs.getString("TYPE_NAME"));// 列对应数据类型的类
			resultMap.setColumnDisplaySize(rs.getInt("COLUMN_SIZE")); // 列在数据库中类型的最大字符个数
			resultMap.setPrecision(rs.getInt("DECIMAL_DIGITS"));// 列类型的精确度(类型的长度)
			/**
			 * NULLABLE int => 是否允许使用 NULL。 IS_NULLABLE String => ISO
			 * 规则用于确定列是否包括 null。
			 */
			if (rs.getInt("NULLABLE") == 0 && "0".equals(rs.getString("NULLABLE"))) {
				resultMap.setNullable(false);
			} else {
				resultMap.setNullable(true);
			}
			resultMap.setColumnComment(rs.getString("REMARKS")); // 列描述
			resultMap.setDefaultValue(rs.getString("COLUMN_DEF")); // 默认值
			/**
			 * 指示此列是否是自动递增 YES -- 该列是自动递增的 NO -- 该列不是自动递增 空字串--- 不能确定该列是否自动递增
			 */
			String isAutoIncrement = "";
			try {
				isAutoIncrement = rs.getString("IS_AUTOINCREMENT");
			} catch (Exception e) {
			}
			if (StringUtils.isNoneBlank(isAutoIncrement)
					&& !"null".equalsIgnoreCase(isAutoIncrement)) {

				if ("yes".equalsIgnoreCase(isAutoIncrement)) {
					resultMap.setAutoIncrement(true);
				} else {
					resultMap.setAutoIncrement(false);
				}
			}
			resList.add(resultMap);
		}
		return resList;
	}
}
