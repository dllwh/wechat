package com.cdeledu.common.task;

import java.util.concurrent.BlockingQueue;

import com.mongodb.DBObject;

public class BatchTaskOption {
	/**
	 * collectionName:表名
	 */
	private String collectionName;
	/**
	 * queue:消息队列
	 */
	private BlockingQueue<DBObject> queue;

	/**
	 * batchSize:每次写入最大条数
	 */
	private int batchSize;
	/**
	 * frequency:写入间隔，单位毫秒
	 */
	private long frequency;
	/**
	 * timeout:从消息队列中取出消息的等待时间
	 */
	private int timeout;

	public BatchTaskOption(String collectionName, BlockingQueue<DBObject> queue) {
		this.collectionName = collectionName;
		this.queue = queue;
		this.batchSize = 50;
		this.frequency = 1000;
		this.timeout = 3;
	}

	public BatchTaskOption(String collectionName, BlockingQueue<DBObject> queue, int batchSize,
			long frequency, int timeout) {
		this.collectionName = collectionName;
		this.queue = queue;
		this.batchSize = batchSize;
		this.frequency = frequency;
		this.timeout = timeout;
	}

	public String getCollectionName() {
		return this.collectionName;
	}

	public void setCollectionName(String collectionName) {
		this.collectionName = collectionName;
	}

	public BlockingQueue<DBObject> getQueue() {
		return this.queue;
	}

	public void setQueue(BlockingQueue<DBObject> queue) {
		this.queue = queue;
	}

	public int getBatchSize() {
		return this.batchSize;
	}

	public void setBatchSize(int batchSize) {
		this.batchSize = batchSize;
	}

	public long getFrequency() {
		return this.frequency;
	}

	public void setFrequency(long frequency) {
		this.frequency = frequency;
	}

	public int getTimeout() {
		return this.timeout;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}
}
