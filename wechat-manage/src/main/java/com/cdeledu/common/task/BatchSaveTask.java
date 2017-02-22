package com.cdeledu.common.task;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import com.cdeledu.util.Clock;
import com.mongodb.DBObject;

public class BatchSaveTask implements Runnable {
	private ArrayList<DBObject> docList;
	private DBObject doc;
	private long lastTime;
	private BatchTaskOption option;
	// static MongoService ms = new MongoService("chinatet");

	public BatchSaveTask(BatchTaskOption option) {
		this.option = option;
		this.docList = new ArrayList<DBObject>();
		this.lastTime = 0L;
	}
	@Override
	public void run() {
		System.out.println("BatchSaveTask for " + this.option.getCollectionName() + " started...");
		while(true){
			//存储聊天消息
			if(this.docList.size()>=this.option.getBatchSize()||((Clock.currentTimeMillis()-this.lastTime)>=this.option.getFrequency())){
				this.lastTime = Clock.currentTimeMillis();
				if(!this.docList.isEmpty()){
					try {
					// 	ms.getCollection(this.option.getCollectionName()).insert(this.docList);
					} catch (Exception e) {
					}
					this.docList.clear();
				}
			}else{
				try {
					this.doc = this.option.getQueue().poll(this.option.getTimeout(), TimeUnit.SECONDS);
				} catch (InterruptedException e) {
				}
				if(null!=this.doc){
					this.docList.add(this.doc);
				}
			}
		}
	}

}
