package com.cdeledu.common.constants;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import com.mongodb.DBObject;

public class MongoConstants {
	/** 队列 */
	public static BlockingQueue<DBObject> mongoQueue = new LinkedBlockingQueue<DBObject>();
	public static final String[] INHERENT_URIS = { "remove", "add", "save", "insert", "edit",
			"update", "delete", "batch", "creat", "modify", "change", "del" };
}
