package com.cdeledu.common.reflection.json;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @类描述: JSON和JAVA的POJO的相互转换
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建日期: 2016年4月4日 下午1:36:30
 * @版本: V1.0
 * @since: JDK 1.7
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public class JSONUtilHelper {
	/** ----------------------------------------------------- Fields start */
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(JSONUtilHelper.class);
	/** ----------------------------------------------------- Fields end */

	/**
	 * @方法:将数组转换成JSON
	 * @创建人:独泪了无痕
	 * @param object
	 * @return
	 */
	public static String array2json(Object object) {
		JSONArray jsonArray = JSONArray.fromObject(object);
		return jsonArray.toString();
	}

	/**
	 * @方法:将JSON转换成数组,其中valueClz为数组中存放的对象的Class
	 * @创建人:独泪了无痕
	 * @param json
	 * @param valueClz
	 * @return
	 */
	public static Object json2Array(String json, Class valueClz) {
		JSONArray jsonArray = JSONArray.fromObject(json);
		return JSONArray.toArray(jsonArray, valueClz);
	}

	/**
	 * @方法:将Collection转换成JSON
	 * @创建人:独泪了无痕
	 * @param object
	 * @return
	 */
	public static String collection2json(Object object) {
		JSONArray jsonArray = JSONArray.fromObject(object);
		return jsonArray.toString();
	}

	/**
	 * @方法:将Map转换成JSON
	 * @创建人:独泪了无痕
	 * @param object
	 * @return
	 */
	public static String map2json(Object object) {
		JSONObject jsonObject = JSONObject.fromObject(object);
		return jsonObject.toString();
	}

	/**
	 * @方法:将JSON转换成Map
	 * @创建人:独泪了无痕
	 * @param keyArray
	 *            :Map的key
	 * @param json
	 * @param valueClz
	 *            : 为Map中value的Class
	 * @return
	 */
	public static Map json2Map(Object[] keyArray, String json, Class valueClz) {
		JSONObject jsonObject = JSONObject.fromObject(json);
		Map classMap = new HashMap();

		for (int i = 0; i < keyArray.length; i++) {
			classMap.put(keyArray[i], valueClz);
		}

		return (Map) JSONObject.toBean(jsonObject, Map.class, classMap);
	}

	/**
	 * @方法:将POJO转换成JSON
	 * @创建人:独泪了无痕
	 * @param object
	 * @return
	 */
	public static String bean2json(Object object) {
		JSONObject jsonObject = JSONObject.fromObject(object);
		return jsonObject.toString();
	}

	// 将JSON转换成POJO,其中beanClz为POJO的Class
	public static Object json2Object(String json, Class beanClz) {
		return JSONObject.toBean(JSONObject.fromObject(json), beanClz);
	}

	/**
	 * @方法: 将String转换成JSON
	 * @创建人:独泪了无痕
	 * @param key
	 * @param value
	 * @return
	 */
	public static String string2json(String key, String value) {
		JSONObject object = new JSONObject();
		object.put(key, value);
		return object.toString();
	}

	/**
	 * @方法:将JSON转换成String
	 * @创建人:独泪了无痕
	 * @param json
	 * @param key
	 * @return
	 */
	public static String json2String(String json, String key) {
		JSONObject jsonObject = JSONObject.fromObject(json);
		return jsonObject.get(key).toString();
	}

	/**
	 * @方法:将List对象序列化为JSON文本
	 * @创建人:独泪了无痕
	 * @param list
	 * @return
	 */
	public static <T> String toJSONString(List<T> list) {
		JSONArray jsonArray = JSONArray.fromObject(list);

		return jsonArray.toString();
	}

	/**
	 * @方法:将对象序列化为JSON文本
	 * @创建人:独泪了无痕
	 * @param object
	 * @return
	 */
	public static String toJSONString(Object object) {
		JSONArray jsonArray = JSONArray.fromObject(object);

		return jsonArray.toString();
	}

	/**
	 * @方法:将JSON对象数组序列化为JSON文本
	 * @创建人:独泪了无痕
	 * @param jsonArray
	 * @return
	 */
	public static String toJSONString(JSONArray jsonArray) {
		return jsonArray.toString();
	}

	/**
	 * @方法:将JSON对象序列化为JSON文本
	 * @创建人:独泪了无痕
	 * @param jsonObject
	 * @return
	 */
	public static String toJSONString(JSONObject jsonObject) {
		return jsonObject.toString();
	}

	/**
	 * @方法:将对象转换为List对象
	 * @创建人:独泪了无痕
	 * @param object
	 * @return
	 */
	public static List toArrayList(Object object) {
		List arrayList = new ArrayList();

		JSONArray jsonArray = JSONArray.fromObject(object);

		Iterator it = jsonArray.iterator();
		while (it.hasNext()) {
			JSONObject jsonObject = (JSONObject) it.next();

			Iterator keys = jsonObject.keys();
			while (keys.hasNext()) {
				Object key = keys.next();
				Object value = jsonObject.get(key);
				arrayList.add(value);
			}
		}

		return arrayList;
	}

	/**
	 * @方法:将对象转换为JSON对象数组
	 * @创建人:独泪了无痕
	 * @param object
	 * @return
	 */
	public static JSONArray toJSONArray(Object object) {
		return JSONArray.fromObject(object);
	}

	/**
	 * @方法:将对象转换为JSON对象
	 * @创建人:独泪了无痕
	 * @param object
	 * @return
	 */
	public static JSONObject toJSONObject(Object object) {
		return JSONObject.fromObject(object);
	}

	/***
	 * 将对象转换为HashMap
	 * 
	 * @param object
	 * @return
	 */
	public static HashMap toHashMap(Object object) {
		HashMap<String, Object> data = new HashMap<String, Object>();
		JSONObject jsonObject = JSONUtilHelper.toJSONObject(object);
		Iterator it = jsonObject.keys();
		while (it.hasNext()) {
			String key = String.valueOf(it.next());
			Object value = jsonObject.get(key);
			data.put(key, value);
		}

		return data;
	}

	/**
	 * @方法:将JSON对象数组转换为传入类型的List
	 * @创建人:独泪了无痕
	 * @param jsonArray
	 * @param objectClass
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static <T> List<T> toList(JSONArray jsonArray, Class<T> objectClass) {
		return JSONArray.toList(jsonArray, objectClass);
	}

	/**
	 * @方法:将JSON对象转换为传入类型的对象
	 * @创建人:独泪了无痕
	 * @param jsonObject
	 * @param beanClass
	 * @return
	 */
	public static <T> T toBean(JSONObject jsonObject, Class<T> beanClass) {
		return (T) JSONObject.toBean(jsonObject, beanClass);
	}
}
