package com.cdeledu.crawler.lifeServices.moivesSpider.dytt8Moive;

import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

import com.cdeledu.crawler.lifeServices.moivesSpider.Dytt8MoiveHelper;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 由一级链接 抓取 电影目录
 * @创建者: 皇族灬战狼
 * @联系方式: duleilewuhen@sina.com
 * @创建时间: 2018年4月20日 上午9:25:24
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class FloorWorkThread extends Thread {
	TaskQueue taskQueue;

	public FloorWorkThread(TaskQueue taskQueue) {
		this.taskQueue = taskQueue;
	}

	public void run() {
		// # 队列为空, 结束
		ConcurrentLinkedQueue<String> floorQueue = taskQueue.getFloorQueue();
		List<String> moivePageUrlList = null;
		while (!taskQueue.isFloorQueueEmpty()) {
			moivePageUrlList = Dytt8MoiveHelper.getMoivePageUrlList(floorQueue.poll());
			for (String item : moivePageUrlList) {
				taskQueue.putToMiddleQueue(item);
			}
			try {
				Thread.sleep(1200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			moivePageUrlList.clear();
		}
	}
}
