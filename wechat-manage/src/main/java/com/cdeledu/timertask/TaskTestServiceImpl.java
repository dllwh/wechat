package com.cdeledu.timertask;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TaskTestServiceImpl {
	private static Logger log = Logger.getLogger(TaskTestServiceImpl.class);
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日  HH时mm分ss秒");

	// 从左到右依次是：秒、分、小时、日、月、周、年
	@Scheduled(cron = "*/10 * * * * *")
	public void task10() {
		try {
			log.info("处理任务开始>........");
			// 业务逻辑代码调用
			log.info("时间[" + dateFormat.format(new Date()) + "]----->大家好啊！");
			log.info("处理任务结束!");
		} catch (Exception e) {
			log.error("处理任务出现异常", e);
		}
	}
}
