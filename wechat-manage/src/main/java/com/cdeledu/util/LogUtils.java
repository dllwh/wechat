package com.cdeledu.util;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;

import com.cdeledu.util.myBatisUtils.SqlSessionFactoryUtil;

/**
 * @类描述: 日志工具类
 * @创建者: 皇族灬战狼
 * @创建时间: 2017年2月24日 下午12:15:48
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class LogUtils {
	/**
	 * 保存日志
	 */
	public static void saveLog(HttpServletRequest request, String title) {
		saveLog(request, null, null, title);
	}

	/**
	 * 保存日志
	 */
	public static void saveLog(HttpServletRequest request, Object handler, Exception ex,
			String title) {
		SqlSession sqlSession = SqlSessionFactoryUtil.getSqlSession();
		try {
			sqlSession.insert("");
			sqlSession.commit();
			sqlSession.close();
		} catch (Exception e) {
			sqlSession.rollback();
		}
	}
}
