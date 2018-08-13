package com.cdeledu.util.database.mongodb;

import java.util.Arrays;
import java.util.Set;

import org.bson.Document;

import com.google.common.collect.Sets;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;

/**
 * 
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: mongo 单机 帮助类
 * @创建者: 皇族灬战狼
 * @联系方式: duleilewuhen@sina.com
 * @创建时间: 2018年7月17日 下午5:29:15
 * @版本: V1.1.1
 * @since: JDK 1.7
 */
public final class MongodbSingleHelper {
	/** ----------------------------------------------------- Fields start */
	protected MongoClient mongoClient;
	protected MongoDatabase mongoDatabase;
	/** 服务器地址 */
	protected String host;
	/** 服务器端口 */
	protected int port;
	/** 是否开启验证 */
	protected boolean auth = false;
	/** 用户名 */
	protected String userName;
	/** 数据库名称 */
	protected String databaseName;
	/** 密码 */
	protected String password;

	/** ----------------------------------------------------- Fields end */

	public MongodbSingleHelper(String host, int port, String databaseName) {
		this.host = host;
		this.port = port;
		this.databaseName = databaseName;
		this.auth = false;
		getMongoDataBase(databaseName);
	}

	public MongodbSingleHelper(String host, int port, String userName, String databaseName,
			String password) {
		this.host = host;
		this.port = port;
		this.userName = userName;
		this.databaseName = databaseName;
		this.password = password;
		this.auth = true;
		getMongoDataBase(databaseName);
	}

	/**
	 * @方法描述 : 获取Mongo客户端
	 * @param host
	 * @param port
	 * @return
	 */
	private synchronized MongoClient getMongoClient() {
		if (mongoClient == null) {
			if (!auth) {
				// 连接到 mongodb 服务
				mongoClient = new MongoClient(host, port);
			} else {
				// 认证信息（三个参数分别为：用户名、数据库名称、密码）
				MongoCredential credential = MongoCredential.createCredential(userName,
						databaseName, password.toCharArray());

				mongoClient = new MongoClient(new ServerAddress(host, port),
						Arrays.asList(credential));
			}
		}
		return mongoClient;
	}

	/**
	 * @方法描述 : 获取到指定db（若不存在，则mongo会创建该db)
	 * @param databaseName
	 * @return
	 */
	private MongoDatabase getMongoDataBase(String databaseName) {
		if (mongoDatabase == null) {
			if (mongoClient == null) {
				getMongoClient();
			}
			mongoDatabase = mongoClient.getDatabase(databaseName);
		}
		return mongoDatabase;
	}

	/**
	 * @方法描述 : 获取所有db名称列表（mongodb未开启auth认证下可用）
	 * @return
	 */
	public Set<String> getAllDatabaseNames() throws Exception {
		Set<String> resultSet = Sets.newHashSet();
		MongoClient mongoClient;
		MongoCursor<String> mongoCursor = null;
		try {
			mongoClient = getMongoClient();
			MongoIterable<String> databaseNames = mongoClient.listDatabaseNames();
			mongoCursor = databaseNames.iterator();
			while (mongoCursor.hasNext()) {
				resultSet.add(mongoCursor.next());
			}
		} finally {
			// 需要手动去释放它
			if (mongoCursor != null) {
				mongoCursor.close();
			}
		}
		return resultSet;
	}

	/**
	 * @方法描述 : 删除一个数据库
	 * @param databaseName
	 */
	public void dropDataBase() {
		mongoClient.dropDatabase(databaseName);
	}

	/**
	 * @方法描述 : 创建集合
	 */
	public void createCollection(String collectionName) {
		mongoDatabase.createCollection(collectionName);
	}

	/**
	 * 
	 * @方法描述 : 获取所有集合
	 * @param databaseName
	 * @return
	 */
	public Set<String> getAllCollectionNames() {
		Set<String> resultSet = Sets.newHashSet();
		for (String name : mongoDatabase.listCollectionNames()) {
			resultSet.add(name);
		}
		return resultSet;
	}

	/**
	 * 
	 * @方法描述 : 获取collection对象 - 指定Collection(若不存在，则mongo会创建该集合)
	 * @param databaseName
	 * @param CollectionName
	 * @return
	 */
	protected MongoCollection<Document> getCollection(String collectionName) {
		MongoCollection<Document> collection = mongoDatabase.getCollection(collectionName);
		return collection;
	}

	/**
	 * @方法描述 : 获取collection对象的数据量- 指定Collection(若不存在，则mongo会创建该集合)
	 * @param databaseName
	 * @param collectionName
	 * @return
	 */
	public Long getCollectionCount(String collectionName) {
		return getCollection(collectionName).count();
	}

	/**
	 * @方法描述 : 删除集合
	 * @param databaseName
	 * @param CollectionName
	 */
	public void dropCollection(String collectionName) {
		getCollection(collectionName).drop();
	}

	public void find(String collectionName) {
		getCollection(collectionName).find();
	}

	public void findOne(String collectionName) {
		// getCollection(collectionName).findOneAndDelete();
		// getCollection(collectionName).findOneAndReplace();
		// getCollection(collectionName).findOneAndUpdate();
	}

	public void update(String collectionName) {
		// getCollection(collectionName).updateMany();
		// getCollection(collectionName).updateOne();
	}

	public void insert(String collectionName) {
		// getCollection(collectionName).insertMany();
	}

	public void insertList(String collectionName) {
		// getCollection(collectionName).insertOne();
	}

	public void delete(String collectionName) {
		// getCollection(collectionName).deleteMany();
		// getCollection(collectionName).deleteOne();
	}
}
