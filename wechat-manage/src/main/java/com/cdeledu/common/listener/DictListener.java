package com.cdeledu.common.listener;

import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.cdeledu.common.base.BaseClass;
import com.cdeledu.model.system.Dict;
import com.google.common.collect.Lists;

/**
 * @类描述: 数据字典监听器，保证在项目启动的时候，加载其数据
 * @创建者: 皇族灬战狼
 * @创建时间: 2017年3月12日 下午2:24:54
 * @版本: V1.0
 * @since: JDK 1.7
 */
@WebListener
public class DictListener extends BaseClass implements ServletContextListener {

	private static final long serialVersionUID = 1L;
	public static List<Dict> dictsList = null;

	/** ----------------------------------------------------- Fields start */
	/** ----------------------------------------------------- Fields end */

	/** ----------------------------------------------- [私有方法] */
	/** ----------------------------------------------- [私有方法] */
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		getAllDics();
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		dictsList = null;
	}

	public static void getAllDics() {
//		SqlSession sqlSession = SqlSessionFactoryUtil.getSqlSession();
//		try {
//			dictsList = sqlSession.selectList("sysDictDaoImpl.findAllList");
//			sqlSession.commit();
//			sqlSession.close();
//		} catch (Exception e) {
//			dictsList = Lists.newArrayList();
//			sqlSession.rollback();
//		}
		dictsList = Lists.newArrayList();
	}

}
