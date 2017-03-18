package com.cdeledu.common.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.log4j.Logger;

import com.cdeledu.common.constants.MongoConstants;
import com.cdeledu.common.task.BatchSaveTask;
import com.cdeledu.common.task.BatchTaskOption;

/**
 * @类描述:
 * 
 *       <pre>
 * 	1.操作日志的存储
 *       </pre>
 * 
 * @创建者: 皇族灬战狼
 * @创建时间: 2017年3月18日 下午5:05:07
 * @版本: V1.0
 * @since: JDK 1.7
 */

@WebListener
public class MongoInitListener implements ServletContextListener {
	/** ----------------------------------------------------- Fields start */
	private static Logger log = Logger.getLogger(MongoInitListener.class);

	/** ----------------------------------------------------- Fields end */

	/** ----------------------------------------------- [私有方法] */
	/** ----------------------------------------------- [私有方法] */
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		if (log.isDebugEnabled()) {
			log.info("mongodb 存储");
		}
		BatchTaskOption bts = new BatchTaskOption("operate_log", MongoConstants.mongoQueue, 100,
				2000, 2);
		new Thread(new BatchSaveTask(bts)).start();
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {

	}
}
