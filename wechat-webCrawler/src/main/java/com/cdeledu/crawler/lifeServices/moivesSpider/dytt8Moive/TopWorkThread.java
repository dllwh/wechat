package com.cdeledu.crawler.lifeServices.moivesSpider.dytt8Moive;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;

import com.cdeledu.crawler.lifeServices.moivesSpider.Dytt8MoiveHelper;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 从电影详细信息页面中抓取目标内容
 * @创建者: 皇族灬战狼
 * @联系方式: duleilewuhen@sina.com
 * @创建时间: 2018年4月20日 上午9:24:47
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class TopWorkThread extends Thread {
	TaskQueue taskQueue;

	public TopWorkThread(TaskQueue taskQueue) {
		this.taskQueue = taskQueue;
	}

	public void run() {
		// # 队列为空, 结束
		ConcurrentLinkedQueue<String> temp = taskQueue.getMiddleQueue();
		List<Map<String, Object>> moiveInfolList = null;
		while (!taskQueue.isMiddleQueueEmpty()) {
			moiveInfolList = Dytt8MoiveHelper.getMoiveInforms(temp.poll());
			for (Map<String, Object> item : moiveInfolList) {
				taskQueue.getContentQueue().offer(item);
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			moiveInfolList.clear();
		}

	}
}
