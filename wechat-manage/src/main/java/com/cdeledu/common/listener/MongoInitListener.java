package com.cdeledu.common.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;

import com.cdeledu.common.constants.MongoConstants;
import com.cdeledu.common.task.BatchSaveTask;
import com.cdeledu.common.task.BatchTaskOption;

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
		BatchTaskOption bts = new BatchTaskOption("zbapi_log", MongoConstants.mongoQueue, 100, 2000,
				2);
		new Thread(new BatchSaveTask(bts)).start();
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {

	}
}
