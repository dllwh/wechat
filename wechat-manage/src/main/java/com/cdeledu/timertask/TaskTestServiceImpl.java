package com.cdeledu.timertask;

import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.cdeledu.util.apache.lang.DateUtilHelper;

@Component
public class TaskTestServiceImpl {
	private static Logger log = Logger.getLogger(TaskTestServiceImpl.class);

	// 从左到右依次是：秒、分、小时、日、月、周、年
	@Scheduled(cron = "0 0/1 * * * *")
	public void task10() {
		try {
			log.info("处理任务开始>........");
			// 业务逻辑代码调用
			log.info("时间[" + DateUtilHelper.getCurrentDate("yyyy年MM月dd日  HH时mm分ss秒") + "]----->大家好啊！");
			log.info("处理任务结束!");
		} catch (Exception e) {
			log.error("处理任务出现异常", e);
		}
	}
}
