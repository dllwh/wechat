package com.cdeledu.util.database;

import org.springframework.jdbc.core.JdbcTemplate;

import com.alibaba.druid.pool.DruidDataSource;

public class JdbcTemplateHelper {
	/** ----------------------------------------------------- Fields start */
	private String dbUrl = null;
	private String dbUserName = null;
	private String dbPassword = null;
	private String jdbcName = null;
	private static JdbcTemplate jdbcTemplate;

	/** ----------------------------------------------------- Fields end */
	public JdbcTemplateHelper(String dbUrl, String dbUserName, String dbPassword, String jdbcName) {
		super();
		this.dbUrl = dbUrl;
		this.dbUserName = dbUserName;
		this.dbPassword = dbPassword;
		this.jdbcName = jdbcName;
	}

	public synchronized JdbcTemplate jdbcTemplate() {
		if (jdbcTemplate == null) {
			jdbcTemplate = createJdbcTemplate();
		}
		return jdbcTemplate;
	}

	/** ----------------------------------------------- [私有方法] */
	private synchronized JdbcTemplate createJdbcTemplate() {
		DruidDataSource ds = new DruidDataSource();
		ds.setDriverClassName(jdbcName);
		ds.setUrl(dbUrl);
		ds.setUsername(dbUserName);
		ds.setPassword(dbPassword);
		return new JdbcTemplate(ds);
	}
	/** ----------------------------------------------- [私有方法] */

	/** ----------------------------------------------- [测试方法] */
	/** ----------------------------------------------- [测试方法] */
}
