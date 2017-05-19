package com.cdeledu.util.myBatisUtils;

import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @类描述: mybatis sqlSession管理工具类
 * @创建者: 皇族灬战狼
 * @创建时间: 2017年5月17日 上午9:04:22
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class SqlSessionFactoryUtil {
	/** ----------------------------------------------------- Fields start */
	private static Logger logger = LoggerFactory.getLogger(SqlSessionFactoryUtil.class);
	/** 配置文件的路径 */
	private static final String CFG_FILE_PATH = "systemConfig/mybatis-config.xml";
	/** 会话工厂 */
	private static SqlSessionFactory sqlSessionFactory;
	private static String message = null;
	/** 使用ThreadLocal管理Mybatis中SqlSession对象 */
	private static final ThreadLocal<SqlSession> threadLocal = new ThreadLocal<SqlSession>();

	/** ----------------------------------------------------- Fields end */

	/** ----------------------------------------------- [私有方法] */
	/**
	 * 加载到内存时第一个被初始化，且只初始化一次。
	 */
	static {
		buildSessionFactory();
	}

	/** ----------------------------------------------- [私有方法] */

	/**
	 * @方法描述: 获取连接
	 * @return
	 */
	public static SqlSession getSqlSession() {
		SqlSession sqlSession = threadLocal.get();
		if (sqlSession == null) { // 没有连接信息
			if (sqlSessionFactory == null) {// 没有打开连接
				if (logger.isDebugEnabled()) {
					System.out.println("执行构建新的连接操作。。。");
				}
				rebuildSqlSessionFactory();
			}
			sqlSession = sqlSessionFactory.openSession();
			// 保存会话信息
			threadLocal.set(sqlSession);
		}
		return sqlSession;
	}

	/**
	 * @方法描述: 关闭连接信息
	 */
	public static void closeSqlSession() {
		SqlSession sqlSession = threadLocal.get();
		// 取消保存会话信息
		threadLocal.set(null);
		if (null != sqlSession) {
			if (logger.isDebugEnabled()) {
				System.out.println("执行关闭会话操作。。。");
			}

			sqlSession.close();
			threadLocal.remove();
		}
	}

	/**
	 * @方法描述: 获取工厂会话对象
	 * @return
	 */
	public static SqlSessionFactory getSqlSessionFactory() {
		if (sqlSessionFactory == null) {
			rebuildSqlSessionFactory();
		}
		return sqlSessionFactory;
	}

	/**
	 * @方法描述: 构建数据库全局会话工厂对象
	 */
	public static synchronized void buildSessionFactory() {
		// 检查sf不为null才去构建sf对象
		if (sqlSessionFactory == null) { // true 为null
			try {
				InputStream inputStream = Resources.getResourceAsStream(CFG_FILE_PATH);
				sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

			} catch (Exception e) {
				if (logger.isDebugEnabled()) {
					message = "Create sessionFactory Exception! " + e.getMessage();
					System.out.println(message);
				}

			}
		}
	}

	public static void rebuildSqlSessionFactory() {
		try {
			InputStream inputStream = Resources.getResourceAsStream(CFG_FILE_PATH);
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
