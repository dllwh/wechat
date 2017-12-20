package com.cdeledu.core.config;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;

import com.alibaba.druid.pool.DruidDataSource;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 数据源配置
 * @创建者: 皇族灬战狼
 * @创建时间: 2017年12月16日 下午6:03:39
 * @版本: V1.0
 * @since: JDK 1.7
 */
// @Configuration
// @PropertySource("classpath:properties/dbConfig.properties")
public class DruidDataSourceConfig implements EnvironmentAware {
	/** ----------------------------------------------------- Fields start */
	private RelaxedPropertyResolver propertyResolver;

	/** ----------------------------------------------------- Fields end */
	@Override
	public void setEnvironment(Environment env) {
		this.propertyResolver = new RelaxedPropertyResolver(env, "database.");
	}
	
	// @Bean
	public DataSource dataSource() {
		DruidDataSource datasource = new DruidDataSource();
		System.out.println(propertyResolver.getProperty("dbUrl"));
		datasource.setUrl(propertyResolver.getProperty("dbUrl"));
		datasource.setDriverClassName(propertyResolver.getProperty("jdbcName"));
		datasource.setUsername(propertyResolver.getProperty("dbUserName"));
		datasource.setPassword(propertyResolver.getProperty("dbPassword"));
		datasource.setInitialSize(0);
		datasource.setMinIdle(0);
		datasource.setMaxWait(10000);
		datasource.setMaxActive(20);
		datasource.setPoolPreparedStatements(false);
		datasource.setMaxOpenPreparedStatements(-1);
		datasource.setValidationQuery("select 1");
		datasource.setTestOnBorrow(true);
		datasource.setTestOnReturn(false);
		datasource.setTestWhileIdle(true);
		// 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒
		datasource.setTimeBetweenEvictionRunsMillis(60000);
		//  配置一个连接在池中最小生存的时间，单位是毫秒
		datasource.setMinEvictableIdleTimeMillis(25200000);
		// 超过时间限制是否回收
		datasource.setRemoveAbandoned(true);
		//  1800秒，也就是30分钟
		datasource.setRemoveAbandonedTimeout(1800);
		//  关闭abanded连接时输出错误日志
		datasource.setLogAbandoned(true);
		try {
			// 开启Druid的监控统计功能,去掉后监控界面sql无法统计 
			datasource.setFilters("stat,wall");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return datasource;
	}
}
