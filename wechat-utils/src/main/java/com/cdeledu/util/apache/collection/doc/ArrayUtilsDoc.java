package com.cdeledu.util.apache.collection.doc;

import java.util.Comparator;
import java.util.Map;

/**
 * 
 * @类描述: ArrayUtils 操作方法 说明文档
 * @创建者: 独泪了无痕
 * @创建日期: 2015年8月29日 下午2:21:49
 * @版本: V1.0
 * @since: JDK 1.7
 * @see <a href=
 *      "http://commons.apache.org/proper/commons-lang/javadocs/api-release/org/apache/commons/lang3/">
 *      org.apache.commons.lang3</a>
 */
interface ArrayUtilsDoc {
	/** 向数组指定位置添加元素 */
	public <T> T[] add(T[] array, int index, T element);

	/** 向数组中末尾添加元素 */
	public <T> T[] add(T[] array, T element);

	/** 向数组中添加所有元素 */
	@SuppressWarnings("unchecked")
	public <T> T[] addAll(T[] array1, T... array2);

	/** 复制数组 */
	public <T> T[] clone(T[] array);

	/** 查询某个object是否在数组中 */
	public boolean contains(Object[] array, Object objectToFind);

	/** 获取数组的长度 */
	public int getLength(Object array);

	public int hashCode(Object array);

	/** 查询某个object在数组中的位置，若没找到则返回-1 */
	public int indexOf(Object[] array, Object objectToFind);

	/** 查询某个object在数组中的位置，从指定的起始位置开始搜索.若没找到则返回-1 */
	public int indexOf(Object[] array, Object objectToFind, int startIndex);

	/** 判断数组是否为空 , 不为空返回false,为空true(null和length = 0 的时候都是空) */
	public boolean isEmpty(Object[] array);

	/**
	 * 判断两个数组是否相等，采用的EqualsBuilder进行判断<br/>
	 * 只有当两个数组的数据类型,长度,数值顺序都相同的时候,该方法才会返回True
	 */
	public boolean isEquals(Object array1, Object array2);

	/** 判断数组是否不为空 , 不为空返回true,为空false */
	public <T> boolean isNotEmpty(T[] array);

	/** 判断两个数组长度是否相等,长度相等返回true,否则返回false.相比较的两个数组类型必须相同 */
	public boolean isSameLength(Object[] array1, Object[] array2);

	/** 判断两个数组的类型是否相同,相同返回true,否则返回false */
	public boolean isSameType(Object array1, Object array2);

	/** 判断数组是否按照自然书序排序的 */
	<T> boolean isSorted(T[] array);

	/** 判断数组是否按照自然书序排序的 */
	public <T> boolean isSorted(T[] array, Comparator<T> comparator);

	/** 反向查询某个object在数组中的位置.若没找到则返回-1 */
	public int lastIndexOf(Object[] array, Object objectToFind);

	/** 反向查询某个object在数组中的位置。从指定起始位置开始搜索.若没找到则返回-1 */
	public int lastIndexOf(Object[] array, Object objectToFind, int startIndex);

	/** null 转化成空数组 */
	public Object[] nullToEmpty(Object[] array);

	/** 删除数组某个指定索引（位置）的元素 */
	public <T> T[] remove(T[] array, int index);

	/** 从数组中移除指定所有索引的元素 */
	public <T> T[] removeAll(T[] array, int... indices);

	/** 删除数组中指定的对象（从正序开始搜索，删除第一个） */
	public <T> T[] removeElement(T[] array, Object element);

	/** 从数组中移除指定所有的元素 */
	@SuppressWarnings("unchecked")
	public <T> T[] removeElements(T[] array, T... values);

	/** 反转数组 */
	public void reverse(Object[] array);

	/** 反转数组 */
	public void reverse(Object[] array, int startIndexInclusive, int endIndexExclusive);

	/** 截取子数组 ： 根据 起始索引 startIndexInclusive 到 结束索引startIndexInclusive */
	public <T> T[] subarray(T[] array, int startIndexInclusive, int endIndexExclusive);

	/** 构建数组 */
	@SuppressWarnings("unchecked")
	public <T> T[] toArray(T... items);

	/**
	 * 将二维数组转换成Map<br/>
	 * 如果数组里是Entry，则其Key与Value就是新Map的Key和Value。<br/>
	 * 如果是Object[]则Object[0]为Key，Object[1]为Value<br/>
	 * 对于Object[]数组里的元素必须是instanceof Object[]或者Entry,即不支持基本数据类型数组<br/>
	 * 如:ArrayUtils.toMap(new Object[]{new int[]{1,2},new int[]{3,4}})会出异常<br/>
	 */
	public Map<Object, Object> toMap(Object[] array);

	/** 将基本类型数组转换成包装类型 */
	public Integer[] toObject(int[] array);

	/** 将包装类型数组转换成基本类型 */
	public int[] toPrimitive(Integer[] array);

	/** 将包装类型数组转换成基本类型 */
	public int[] toPrimitive(Integer[] array, int valueForNull);

	/** 将一个数组转换成String,用于打印 输出 */
	public String toString(Object array);

	/** 将一个数组转换成String,用于打印 输出 */
	public String toString(Object array, String stringIfNull);
}
