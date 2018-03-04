package com.cdeledu.util.apache.collection;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.ListUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * @说明 :
 * 
 * @author : 独泪了无痕
 */
public class ListUtilHelper extends ListUtils {
	/**
	 * 
	 * @Title：indexOf
	 * @Description：查询某个对象是否在list中
	 * @param list
	 * @param predicate
	 * @return
	 * @return：int 返回类型
	 */
	public static <E> int indexOf(List<E> list, String predicate) {
		if (CollectionHelper.isNotEmpty(list) && StringUtils.isNoneBlank(predicate)) {
			for (int i = 0; i < list.size(); i++) {
				String item = (String) list.get(i);
				if (predicate.equalsIgnoreCase(item)) {
					return i;
				}
			}
		}
		return -1;
	}

	/**
	 * 
	 * @方法名称: addDistinctEntry
	 * @方法描述: 向list中添加不重复元素
	 * 
	 * @param sourceList
	 * @param entry
	 * @return
	 */
	public static <V> boolean addDistinctEntry(List<V> sourceList, V entry) {
		return (sourceList != null && !sourceList.contains(entry)) ? sourceList.add(entry) : false;
	}

	/**
	 * 
	 * @方法名称: invertList
	 * @方法描述: 倒叙输出
	 * 
	 * @param sourceList
	 * @return
	 */
	public static <V> List<V> invertList(List<V> sourceList) {
		if (CollectionUtils.isEmpty(sourceList)) {
			return sourceList;
		}

		List<V> invertList = new ArrayList<V>(sourceList.size());
		for (int i = sourceList.size() - 1; i >= 0; i--) {
			invertList.add(sourceList.get(i));
		}
		return invertList;
	}

	/**
	 * 
	 * @方法名称: addDistinctList
	 * @方法描述: 将entryList 中的不用于 sourceList 的元素全部添加
	 * 
	 * @param sourceList
	 * @param entryList
	 * @return
	 */
	public static <V> int addDistinctList(List<V> sourceList, List<V> entryList) {
		if (sourceList == null || CollectionUtils.isEmpty(entryList)) {
			return 0;
		}

		int sourceCount = sourceList.size();
		for (V entry : entryList) {
			if (!sourceList.contains(entry)) {
				sourceList.add(entry);
			}
		}
		return sourceList.size() - sourceCount;
	}

	/**
	 * 
	 * @方法名称: distinctList
	 * @方法描述: 删除list中的元素
	 * 
	 * @param sourceList
	 * @return 删除重复元素的个数
	 */
	public static <V> int distinctList(List<V> sourceList) {
		if (CollectionUtils.isEmpty(sourceList)) {
			return 0;
		}

		int sourceCount = sourceList.size();
		int sourceListSize = sourceList.size();
		for (int i = 0; i < sourceListSize; i++) {
			for (int j = (i + 1); j < sourceListSize; j++) {
				if (sourceList.get(i).equals(sourceList.get(j))) {
					sourceList.remove(j);
					sourceListSize = sourceList.size();
					j--;
				}
			}
		}
		return sourceCount - sourceList.size();
	}

	/**
	 * @方法描述: 拼接字符串
	 * @param list
	 *            列表
	 * @param split
	 *            分隔符
	 * @return
	 */
	public static String join(List<String> list, String split) {
		if (list == null){
			return null;
		}
		String[] array = list.toArray(new String[] {});
		StringBuilder s = new StringBuilder(128);
		for (int i = 0; i < array.length; i++) {
			if (i > 0) {
				s.append(split);
			}
			s.append(array[i]);
		}
		return s.toString();
	}

	public static <E> boolean isNotEmpty(List<E> list) {
		return !isEmpty(list);
	}

	public static <E> boolean isEmpty(List<E> list) {
		if (list != null && list.size() > 0) {
			return false;
		} else {
			return true;
		}
	}
	/*--------------------------公有方法 end   -------------------------------*/
}
