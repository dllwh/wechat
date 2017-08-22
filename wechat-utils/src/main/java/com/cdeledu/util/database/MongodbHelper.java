package com.cdeledu.util.database;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;


import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.lang3.StringUtils;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ServerAddress;
import com.mongodb.WriteConcern;
import com.mongodb.WriteResult;

/**
 * @类描述: 使用Java操作Mongodb
 *       <ul>
 *       <li>
 *       DBCollection类:指定数据库中指定集合的实例,提供了增删改查等一系列操作.在关系型数据库中,对数据的增删改查操作是建立在表的基础上的
 *       ,在mongodb中是建立在集合的基础上进行的</li>
 *       <li>DBObject接口:DBObject是键值的映射.因此,可以将DBObject的实现类作为查询的返回结果,也可以作为查询条件</li>
 *       <li>DBCursor:游标，返回结果的集合,是DBObject的迭代器</li>
 *       </ul>
 * @创建者: 独泪了无痕
 * @创建日期: 2016年1月17日 上午10:56:32
 * @版本: V1.0
 * @since: JDK 1.7
 * @see <a href="http://tech.it168.com/a2011/0617/1206/000001206231_all.shtml">
 *      Mongodb快速入门之使用Java操作Mongodb</a>
 */
public class MongodbHelper {
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	// 实例化Mongo对象，连接27017端
	private MongoClient mongoClient = null;
	// 连接数据库，假如数据库不存在的话，mongodb会自动建立
	private DB db = null;
	// 从Mongodb中获得数据集合，如果该数据集合不存在，Mongodb会为其新建立
	private DBCollection conn = null;
	private static MongodbHelper mongodbHelper = null;
	private static Map<String, DBCollection> dbCollectionMap = new ConcurrentHashMap<String, DBCollection>();
	// 连接服务器的地址(不只有一个)
	static String serverAddr = "";
	static String serverAddrs = "";
	// 可选的指定端口，如果不填，默认为27017
	static Integer serverPort = 0;
	// 要连接数据库的名称.如果没有指定，默认的数据库为admin。
	static String datebase = "";
	// 是否分片
	private static volatile boolean isShard = true;

	static {
		CompositeConfiguration config = new CompositeConfiguration();

		try {
			// 从配置文件中获取属性值
			config.addConfiguration(new PropertiesConfiguration(
					"datasource/mongodb.properties"));

			serverAddrs = config.getString("mongodb.serverAddrs");
			serverAddr = config.getString("mongodb.serverAddr");
			serverPort = config.getInt("mongodb.serverPort");
			datebase = config.getString("mongodb.datebase");
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 使用单例模式获取DB对象
	 */
	@SuppressWarnings("deprecation")
	private MongodbHelper() {
		try {
			if (db == null) {
				MongoClientOptions.Builder build = new MongoClientOptions.Builder();
				/*
				 * 一个线程访问数据库的时候，在成功获取到一个可用数据库连接之前的最长等待时间为2分钟
				 * 这里比较危险，如果超过maxWaitTime都没有获取到这个连接的话，该线程就会抛出Exception
				 * 故这里设置的maxWaitTime应该足够大，以免由于排队线程过多造成的数据库访问失败
				 */
				build.maxWaitTime(1000 * 60 * 2);
				// 与数据库建立连接的timeout设置为1分钟(连接超时时间)
				build.connectTimeout(1000 * 60 * 1);
				// 保持连接
				build.socketKeepAlive(true);
				// 套接字超时时间，0无限制(保持连接)
				build.socketTimeout(0);
				// 连接池设置为300个连接,默认为100
				build.connectionsPerHost(300);
				// 线程队列数，如果连接线程排满了队列就会抛出“Out of semaphores to get db”错误。
				build.threadsAllowedToBlockForConnectionMultiplier(5000);
				build.writeConcern(WriteConcern.ACKNOWLEDGED);

				MongoClientOptions myOptions = build.build();
				// 创建Mongo对象
				if (StringUtils.isNotBlank(serverAddrs)) {
					logger.info("-----启动集群分片数据库: " + serverAddrs);
					String[] iplist = serverAddrs.split(",");
					List<ServerAddress> addresses = new ArrayList<ServerAddress>();
					ServerAddress address = null;
					for (String ip : iplist) {
						isShard = true;
						address = new ServerAddress(ip, serverPort);
						addresses.add(address);
					}
					mongoClient = new MongoClient(addresses, myOptions);
				} else {
					logger.info("-----启动单机数据库：: " + serverAddr);
					isShard = false;
					mongoClient = new MongoClient(new ServerAddress(serverAddr,
							serverPort), myOptions);
				}

				// 获取指定的数据库 :如果默认没有创建,mongodb会自动创建
				if (isShard) {
					// 创建分片数据库:暂时未实现
					// createShardDB();
				}

				db = mongoClient.getDB(datebase);
			}
		} catch (Exception e) {
			String msg = "连接mongodb失败, 服务器地址:%s , 端口: %s";
			if (isShard)
				String.format(msg, serverAddrs, serverPort);
			String.format(msg, serverAddr, serverPort);
			logger.error(msg);
			throw new RuntimeException(e.getClass().getName() + ": " + e.getMessage(),e);
		}
	}

	public static MongodbHelper getInstance() {
		if (mongodbHelper == null) {
			init();
		}
		return mongodbHelper;
	}

	private static synchronized void init() {
		if (mongodbHelper == null) {
			mongodbHelper = new MongodbHelper();
		}
	}
	
	/**
	 * @方法描述: 对象销毁，清除内存
	 * @创建者: 皇族灬战狼
	 * @创建时间: 2016年3月4日 下午5:14:11
	 */
	public void destory() {
		if (null != mongoClient) {
			mongoClient.close();
		}
		mongoClient = null;
		db = null;
		conn = null;
	}

	/**
	 * @方法描述: 获取指定的collectionName集合
	 *        <ul>
	 *        <li>若没有,则创建并保存到dbCollectionMap中</li>
	 *        <li>若有,则直接从dbCollectionMap中读取</li>
	 *        </ul>
	 * @创建者: 皇族灬战狼
	 * @创建时间: 2016年3月4日 下午4:26:03
	 * @param connName
	 *            集合名称
	 * @return 集合连接对象
	 */
	public DBCollection getDBCollection(String connName) {
		DBCollection collection = null;
		if (dbCollectionMap.containsKey(connName)) {
			collection = dbCollectionMap.get(connName);
		} else {
			collection = db.getCollection(connName);
			if (collection != null) {
				dbCollectionMap.put(connName, collection);
			}
		}
		return collection;
	}

	/**
	 * @方法描述: 从Mongodb中获取collection数据集
	 * @创建者: 皇族灬战狼
	 * @创建时间: 2016年3月4日 下午4:59:40
	 */
	public List<String> getCollectionNames() {
		List<String> resultList = new ArrayList<String>();
		Set<String> collections = db.getCollectionNames();
		for (String collectionName : collections) {
			resultList.add(collectionName);
		}
		return resultList;
	}

	/**
	 * ==============================================================
	 * 数据查询query、find操作
	 * ==============================================================
	 */
	/**
	 * @param <T>
	 * @方法描述: 查询所有数据
	 * @创建者: 皇族灬战狼
	 * @创建时间: 2016年3月4日 下午4:55:55
	 * @param connName
	 * @param clazz
	 * @param query
	 * @return
	 */
	public <T> List<T> findAll(String connName, Class<T> clazz, DBObject query) {
		// 创建返回的结果集
		List<T> resultList = new ArrayList<T>();
		conn = getDBCollection(connName);
		T bean = null;

		DBCursor cursor = null;
		if (null != conn) {
			// 返回集合中所有的文档
			cursor = conn.find(query);
			try {
				while (cursor.hasNext()) {
					DBObject dbObject = (DBObject) cursor.next();
					if (null != clazz)
						bean = clazz.newInstance();
					bean = dB2Bean(dbObject, bean);
					resultList.add(bean);
				}
			} catch (Exception e) {
				logger.error("根据指定条件获取 " + connName + " 中的部分数据异常", e);
				e.printStackTrace();
			}
		}
		return resultList;
	}

	/**
	 * @param <T>
	 * @方法描述: 分页查询
	 * @创建者: 皇族灬战狼
	 * @创建时间: 2016年3月4日 下午4:43:50
	 * @param connName
	 * @param className
	 * @param skip
	 * @param limit
	 * @return
	 */
	public <T> List<T> findListByPage(String connName, Class<T> clazz,
			DBObject query, int skip, int limit) {
		// 创建返回的结果集
		List<T> resultList = new ArrayList<T>();
		conn = getDBCollection(connName);
		DBCursor cursor = null;
		T bean = null;

		if (null != conn) {
			// 返回集合中所有的文档
			cursor = conn.find(query).skip(skip).limit(limit);
			try {
				while (cursor.hasNext()) {
					DBObject dbObject = (DBObject) cursor.next();
					if (null != clazz)
						bean = clazz.newInstance();
					bean = dB2Bean(dbObject, bean);
					resultList.add(bean);
				}
			} catch (Exception e) {
				logger.error("根据指定条件获取 " + connName + " 中的部分数据异常", e);
				e.printStackTrace();
			}
		}
		return resultList;
	}

	/**
	 * @param <T>
	 * @方法描述: 分页查询，按某一列排序
	 * @创建者: 皇族灬战狼
	 * @创建时间: 2016年3月4日 下午4:43:55
	 * @param connName
	 * @param query
	 * @param skip
	 *            跳过指定数量的数据
	 * @param limit
	 *            读取指定数量的数据:用来指定返回结果的最大数量
	 * @param orderByColumn
	 *            : 排序字段
	 * @param order
	 *            : 1或-1来指定排序方式是升序或降序:如果没有指定排序方式，默认按照升序排列
	 * @return
	 */
	public <T> List<T> findListByPage(String connName, Class<T> clazz,
			DBObject query, int skip, int limit, String orderByColumn,
			Integer order) {
		// 创建返回的结果集
		List<T> resultList = new ArrayList<T>();

		conn = getDBCollection(connName);
		DBObject sort = null;
		DBCursor cursor = null;
		T bean = null;

		if (null != conn) {
			if (order == 1 || order == -1)
				sort = new BasicDBObject(orderByColumn, order);
			else
				sort = new BasicDBObject(orderByColumn, 1);
			// 返回集合中所有的文档
			cursor = conn.find(query).sort(sort).skip(skip).limit(limit);
			try {
				while (cursor.hasNext()) {
					DBObject dbObject = (DBObject) cursor.next();
					if (null != clazz)
						bean = clazz.newInstance();
					bean = dB2Bean(dbObject, bean);
					resultList.add(bean);
				}
			} catch (Exception e) {
				logger.error("根据指定条件获取 " + connName + " 中的部分数据异常", e);
				e.printStackTrace();
			}
		}
		return resultList;
	}

	/**
	 * @param <T>
	 * @方法描述: 分页查询，按某一列排序
	 * @创建者: 皇族灬战狼
	 * @创建时间: 2016年3月4日 下午4:43:55
	 * @param connName
	 * @param query
	 * @param skip
	 *            跳过指定数量的数据
	 * @param limit
	 *            读取指定数量的数据:用来指定返回结果的最大数量
	 * @param orderByColumn
	 *            : 排序字段
	 * @return
	 */
	public <T> List<T> findListByPage(String connName, Class<T> clazz,
			DBObject query, int skip, int limit, String orderByColumn) {
		// 创建返回的结果集
		List<T> resultList = new ArrayList<T>();
		T bean = null;
		conn = getDBCollection(connName);
		DBCursor cursor = null;

		if (null != conn) {
			// 返回集合中所有的文档
			cursor = conn.find(query).sort(new BasicDBObject(orderByColumn, 1))
					.limit(limit).skip(skip);
			try {
				while (cursor.hasNext()) {
					DBObject dbObject = (DBObject) cursor.next();
					if (null != clazz)
						bean = clazz.newInstance();
					bean = dB2Bean(dbObject, bean);
					resultList.add(bean);
				}
			} catch (Exception e) {
				logger.error("根据指定条件获取 " + connName + " 中的部分数据异常", e);
				e.printStackTrace();
			}
		}
		return resultList;
	}

	/**
	 * @param <T>
	 * @方法描述: 获得数据库中的第一个document
	 *        <ul>
	 *        <li>查询满足条件的第一条记录{不意味着数据库满足条件的只有一条记录}</li>
	 *        </ul>
	 * @创建者: 皇族灬战狼
	 * @创建时间: 2016年3月4日 下午4:44:52
	 * @param connName
	 * @param className
	 * @return
	 * @throws Exception
	 * @throws InstantiationException
	 */
	public <T> T findOne(String connName, Class<T> clazz, DBObject query) {
		T bean = null;
		conn = getDBCollection(connName);
		if (null != conn) {
			// 返回集合中一条文档
			try {
				if (null != clazz)
					bean = clazz.newInstance();
				bean = dB2Bean(conn.findOne(query), bean);
			} catch (Exception e) {
				logger.error("根据指定条件获取 " + connName + " 中的部分数据异常", e);
				e.printStackTrace();
			}
		}
		return bean;
	}

	/**
	 * @param <T>
	 * @方法描述: 根据条件id查询数据
	 * @创建者: 皇族灬战狼
	 * @创建时间: 2016年3月4日 下午4:45:43
	 * @param id
	 * @param connName
	 * @param className
	 * @return
	 */
	public <T> T findOneById(String connName, Class<T> clazz, String id) {
		DBObject result = null;
		conn = getDBCollection(connName);
		T bean = null;
		if (null != conn) {
			result = conn.findOne(new ObjectId(id));
			try {
				if (null != clazz)
					bean = clazz.newInstance();
				bean = dB2Bean(result, bean);
			} catch (Exception e) {
				logger.error("根据指定条件获取 " + connName + " 中的部分数据异常", e);
				e.printStackTrace();
			}
		}
		return bean;
	}

	/**
	 * @方法描述: 是否存在
	 * @创建者: 皇族灬战狼
	 * @创建时间: 2016年3月4日 下午7:11:35
	 * @return
	 */
	public boolean isExsit(String connName, DBObject query) {
		boolean result = false;
		DBCursor dbCursor = null;
		conn = getDBCollection(connName);
		if (null != conn) {
			dbCursor = conn.find(query);
			if (null != dbCursor && dbCursor.hasNext()) {
				result = true;
			}
		}
		return result;
	}

	/**
	 * ==============================================================
	 * 数据插入:insert 操作
	 * ==============================================================
	 */
	/**
	 * @方法描述: 增加不重复数据
	 * @创建者: 皇族灬战狼
	 * @创建时间: 2016年3月4日 下午4:46:43
	 * @param obj
	 * @param connName
	 * @return
	 */
	public boolean insert(String connName, DBObject documents) {
		boolean result = false;
		conn = getDBCollection(connName);
		// 返回结果
		WriteResult writeResult = null;
		// 仅仅插入不存在的
		if (null != conn && isExsit(connName, documents)) {
			// getN()获取影响行数
			writeResult = conn.insert(documents);
			if (null != writeResult && writeResult.getN() > 0) {
				result = true;
			}

		}
		return result;
	}

	/**
	 * @方法描述: 插入
	 * @创建者: 皇族灬战狼
	 * @创建时间: 2016年3月11日 上午11:07:56
	 * @param connName
	 * @param paramMap
	 */
	public void insert(String connName, Map<String, Object> paramMap) {

	}

	/**
	 * @方法描述: 批量插入
	 * @创建者: 皇族灬战狼
	 * @创建时间: 2016年3月11日 上午11:08:26
	 * @param collection
	 * @param paramList
	 */
	public void insertBatch(String connName, List<Map<String, Object>> paramList) {
		if (CollectionUtils.isEmpty(paramList)) {
			return;
		}
	}

	/**
	 * ==============================================================
	 * 数据更新:update 操作 begin
	 * ==============================================================
	 */
	/**
	 * @方法描述: 更新数据
	 * @创建者: 皇族灬战狼
	 * @创建时间: 2016年3月4日 下午4:48:25
	 * @param connName
	 * @param query
	 *            update的查询条件，类似sql update查询内where后面
	 * @param update
	 *            update的对象和一些更新的操作符(如$,$inc...)
	 * @return
	 */
	public boolean update(String connName, DBObject query, DBObject update) {
		boolean result = false;
		// 返回结果
		WriteResult writeResult = null;
		conn = getDBCollection(connName);
		if (null != conn) {
			writeResult = conn.update(query, update);
			if (null != writeResult && writeResult.getN() > 0) {
				result = true;
			}
		}
		return result;
	}

	/**
	 * @方法描述: 更新数据
	 * @创建者: 皇族灬战狼
	 * @创建时间: 2016年3月5日 下午12:14:53
	 * @param connName
	 * @param query
	 *            update的查询条件，类似sql update查询内where后面的
	 * @param update
	 *            update的对象和一些更新的操作符（如$,$inc...）等
	 * @param upsert
	 *            可选.如果不存在update的记录,是否插入objNew,true为插入,默认是false
	 * @param multi
	 *            可选.默认是false,只更新找到的第一条记录;如果这个参数为true,就把按条件查出来多条记录全部更新
	 * @return
	 */
	public boolean update(String connName, DBObject query, DBObject update,
			boolean upsert, boolean multi) {
		boolean result = false;
		conn = getDBCollection(connName);
		// 返回结果
		WriteResult writeResult = null;
		if (null != conn) {
			writeResult = conn.update(query, update, upsert, multi);
			if (null != writeResult && writeResult.getN() > 0) {
				result = true;
			}
		}
		return result;
	}

	/**
	 * @方法描述: 更新数据:只能是单条记录
	 *        <ul>
	 *        <li>
	 *        如果在collection内已经存在一个和x对象相同的"_id"的记录,
	 *        mongodb就会把x对象替换collection内已经存在的记录
	 *        ,否则将会插入x对象,如果x内没有_id,系统会自动生成一个再插入</li>
	 *        </ul>
	 * @创建者: 皇族灬战狼
	 * @创建时间: 2016年3月5日 下午12:18:48
	 * @param connName
	 * @param document
	 * @return
	 */
	public int save(String connName, DBObject document) {
		int result = -1;
		conn = getDBCollection(connName);
		if (null != conn) {
			conn.save(document).getN();
		}
		return result;
	}

	/**
	 * ==============================================================
	 * 数据删除:remove 操作
	 * ==============================================================
	 */
	/**
	 * @方法描述: 根据条件删除数据
	 * @创建者: 皇族灬战狼
	 * @创建时间: 2016年3月4日 下午4:50:18
	 * @param connName
	 * @param dbObject
	 * @return
	 */
	public boolean remove(String connName, DBObject dbObject) {
		boolean result = false;
		conn = getDBCollection(connName);
		if (null != conn) {
			// 返回结果
			WriteResult writeResult = null;
			writeResult = conn.remove(dbObject);
			if (null != writeResult && writeResult.getN() > 0) {
				result = true;
			}

		}
		return result;
	}

	/**
	 * @方法描述: 删除第一个document
	 * @创建者: 皇族灬战狼
	 * @创建时间: 2016年3月5日 上午11:23:48
	 * @param connName
	 * @return
	 */
	public boolean removeFirst(String connName) {
		boolean result = false;
		conn = getDBCollection(connName);
		if (null != conn) {
			// 返回结果
			WriteResult writeResult = null;
			writeResult = conn.remove(conn.findOne());
			if (null != writeResult && writeResult.getN() > 0) {
				result = true;
			}
		}
		return result;
	}

	/**
	 * @方法描述: 删除所有的document
	 * @创建者: 皇族灬战狼
	 * @创建时间: 2016年3月5日 上午11:53:47
	 * @param connName
	 */
	public void removeAll(String connName) {
		conn = getDBCollection(connName);
		if (null != conn) {
			DBCursor dbCursor = conn.find();
			while (dbCursor.hasNext()) {
				conn.remove(dbCursor.next());
			}
		}
	}

	/**
	 * ===================================================================
	 * 数据之统计: getCount 操作
	 * ===================================================================
	 */

	/**
	 * @方法描述: 根据条件统计数据
	 * @创建者: 皇族灬战狼
	 * @创建时间: 2016年3月4日 下午4:51:01
	 * @param connName
	 * @return
	 */
	public int getCount(String connName, DBObject query) {
		int result = 0;
		conn = getDBCollection(connName);
		if (null != conn) {
			result = conn.find(query).count();
		}
		return result;
	}

	/**
	 * ===================================================================
	 * 数据之操作: 类型转换
	 * ===================================================================
	 */
	/**
	 * @方法描述: 将Mongo对象转换为自定义对象(把DBObject转换成bean对象 )
	 * @创建者: 皇族灬战狼
	 * @创建时间: 2016年3月7日 下午7:10:14
	 * @param dbObject
	 * @param className
	 * @return
	 * @throws Exception
	 */
	private <T> T dB2Bean(DBObject dbObject, T bean) {
		if (null == bean) {
			return null;
		}
		try {
			// 获取对象对应类中的所有属性域
			Field[] Fields = bean.getClass().getDeclaredFields();

			for (Field field : Fields) {
				// 获取属性名
				String varName = field.getName();
				Object object = dbObject.get(varName);
				if (object != null) {
					BeanUtils.setProperty(bean, varName, object);
				}
			}
		} catch (Exception e) {
			logger.error("DBObject对象转为" + bean.getClass().getName() + "异常", e);
			e.printStackTrace();
		}
		return bean;
	}

	/**
	 * 
	 * @方法描述: 把实体bean对象转换成DBObject
	 * @创建者: 皇族灬战狼
	 * @创建时间: 2016年3月11日 上午10:44:19
	 * @param bean
	 * @return
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 */
	@SuppressWarnings("unused")
	private <T> DBObject bean2DBObject(T bean) throws IllegalArgumentException,
			IllegalAccessException {
		if (null == bean) {
			return null;
		}
		DBObject dbObject = new BasicDBObject();
		// 获取对象对应类中的所有属性域
		Field[] fields = bean.getClass().getDeclaredFields();

		for (Field field : fields) {
			// 获取属性名
			String varName = field.getName();
			// 修改访问控制权限
			boolean accessFlag = field.isAccessible();
			if (!accessFlag) {
				field.setAccessible(true);
			}
			Object param = field.get(bean);
			if (param == null) {
				continue;
			} else if (param instanceof Integer) {// 判断变量的类型
				int value = ((Integer) param).intValue();
				dbObject.put(varName, value);
			} else if (param instanceof String) {
				String value = (String) param;
				dbObject.put(varName, value);
			} else if (param instanceof Double) {
				double value = ((Double) param).doubleValue();
				dbObject.put(varName, value);
			} else if (param instanceof Float) {
				float value = ((Float) param).floatValue();
				dbObject.put(varName, value);
			} else if (param instanceof Long) {
				long value = ((Long) param).longValue();
				dbObject.put(varName, value);
			} else if (param instanceof Boolean) {
				boolean value = ((Boolean) param).booleanValue();
				dbObject.put(varName, value);
			} else if (param instanceof Date) {
				Date value = (Date) param;
				dbObject.put(varName, value);
			}
			// 恢复访问控制权限
			field.setAccessible(accessFlag);
		}
		return dbObject;
	}

	/** ----------------------------- 类型转换 end ------------------------------- */
}
