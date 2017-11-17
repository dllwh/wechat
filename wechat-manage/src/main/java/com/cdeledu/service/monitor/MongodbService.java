package com.cdeledu.service.monitor;

public interface MongodbService {

	/**  获取mongodb列表 */ 
	void getMongodbList();
	/** 获取mongodb节点数量 */
	Integer countMongodb();
	/** 获取mongodb节点信息 */
	void getMongodb(Long id);
	/** 获取mongodb基础信息 */
	void getMongodbOverview(Long id);
	/** 获取mongodb数据库列表 */
	void getMongodbListDatabases(Long id);
	
}
