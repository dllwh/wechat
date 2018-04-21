package com.cdeledu.crawler.lifeServices.moivesSpider.dytt8Moive;

import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 任务队列管理类(高性能无阻塞无界队列)
 * @创建者: 皇族灬战狼
 * @联系方式: duleilewuhen@sina.com
 * @创建时间: 2018年4月20日 上午8:59:22
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class TaskQueue {
	/** floorQueue 存放一级目录 url 链接的队列 */
	private ConcurrentLinkedQueue<String> floorQueue = null;
	/** middleQueue 存放二级目录 url 链接的队列 */
	private ConcurrentLinkedQueue<String> middleQueue = null;
	/** contentQueue 存放获取电影信息(名称、导演、主角、下载地址等)的队列,方便后续持久化 */
	private ConcurrentLinkedQueue<Map<String, Object>> contentQueue = null;

	public TaskQueue() {
		this.floorQueue = new ConcurrentLinkedQueue<>();
		this.middleQueue = new ConcurrentLinkedQueue<>();
		this.contentQueue = new ConcurrentLinkedQueue<>();
	}

	public ConcurrentLinkedQueue<String> getFloorQueue() {
		return floorQueue;
	}

	public ConcurrentLinkedQueue<String> getMiddleQueue() {
		return middleQueue;
	}

	public ConcurrentLinkedQueue<Map<String, Object>> getContentQueue() {
		return contentQueue;
	}

	public boolean isFloorQueueEmpty() {
		return floorQueue.isEmpty();
	}

	public boolean isMiddleQueueEmpty() {
		return middleQueue.isEmpty();
	}

	public boolean isContentQueueEmpty() {
		return contentQueue.isEmpty();
	}

	public void putToFloorQueue(String task) {
		floorQueue.offer(task);
	}

	public void putToMiddleQueue(String task) {
		middleQueue.offer(task);
	}

	public void putToContentQueue(Map<String, Object> task) {
		contentQueue.offer(task);
	}
}
