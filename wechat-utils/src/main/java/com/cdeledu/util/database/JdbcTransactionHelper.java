package com.cdeledu.util.database;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * 
 * @ClassName: JdbcTransactionHelper
 * @Description: JDBC事务管理(代码事务)
 *               <ul>
 *               <li></li>
 *               </ul>
 * @author: 独泪了无痕
 * @date: 2015年7月16日 下午6:42:28
 * @version: V1.0
 */
public class JdbcTransactionHelper {
	/*-------------------------- 私有属性 begin -------------------------------*/
	private static DataSource ds = new ComboPooledDataSource();
	private static ThreadLocal<Connection> tl = new ThreadLocal<Connection>();

	public static DataSource getDataSource() {
		return ds;
	}

	/*-------------------------- 私有属性 end   -------------------------------*/
	/**
	 * 
	 * @Title：getConnection
	 * @Description：dao使用本方法来获取连接 =
	 *                            <ul>
	 *                            <li>如果有事务的话,返回当前事务的conn</li>
	 *                            <li>如果没有事务,通过连接池返回新的con</li>
	 *                            </ul>
	 * @return
	 * @throws SQLException
	 * @return：Connection 返回类型
	 */
	public static Connection getConnection() throws SQLException {
		// 获取当前线程的事务连接
		Connection conn = tl.get();
		if (conn != null)
			return conn;
		return ds.getConnection();
	}

	/**
	 * 
	 * @Title：beginTransaction
	 * @Description：开启事务
	 * @throws SQLException
	 * @return：void 返回类型
	 */
	public static void beginTransaction() throws SQLException {
		// 获取当前线程的事务连接
		Connection conn = tl.get();
		if (conn != null) {
			throw new SQLException("已经开启了事务，不能重复开启！");
		}
		// 赋值,开启事务
		conn = ds.getConnection();
		// 设置为手动提交
		conn.setAutoCommit(false);
		// 将当前事务链接到tl中
		tl.set(conn);
	}

	/**
	 * 
	 * @Title：commitTransaction
	 * @Description：提交事务
	 * @throws SQLException
	 * @return：void 返回类型
	 */
	public static void commitTransaction() throws SQLException {
		// 获取当前线程的事务连接
		Connection conn = tl.get();
		if (conn == null)
			throw new SQLException("没有事务不能提交！");
		// 提交事务
		conn.commit();
		// 关闭连接
		conn.close();

		conn = null;
		tl.remove();
	}

	/**
	 * 
	 * @Title：rollbackTransaction
	 * @Description：回滚事务
	 * @throws SQLException
	 * @return：void 返回类型
	 */
	public static void rollbackTransaction() throws SQLException {
		// 获取当前线程的事务连接
		Connection conn = tl.get();
		if (conn == null)
			throw new SQLException("没有事务不能回滚！");
		// 回滚事务
		conn.rollback();
		// 关闭连接
		conn.close();

		conn = null;
		tl.remove();

	}

	/**
	 * 
	 * @Title：releaseConnection
	 * @Description：释放Connection
	 * @param connection
	 * @throws SQLException
	 * @return：void 返回类型
	 */
	public static void releaseConnection(Connection connection)
			throws SQLException {
		// 获取当前线程的事务连接
		Connection conn = tl.get();
		/** 如果参数连接，与当前事务连接不同，说明这个连接不是当前事务，可以关闭！ */
		if (connection != conn) {
			/** 如果参数连接没有关闭的话,则关闭 */
			if (connection != null && !connection.isClosed()) {
				connection.close();
			}
		}
	}

}
