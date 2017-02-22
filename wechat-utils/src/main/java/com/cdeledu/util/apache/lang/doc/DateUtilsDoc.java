package com.cdeledu.util.apache.lang.doc;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * 
 * @ClassName: DateDoc
 * @Description: DateUtils 方法说明(文档);
 * @author: 独泪了无痕
 * @date: 2015年8月28日 下午5:35:48
 * @version: V1.0
 * @history:
 */
interface DateUtilsDoc {
	/** 取得指定日期以后若干天的日期。如果要得到以前的日期，参数用负数 */
	public Date addDays(Date date, int amount);;

	/** 增加了一个多小时的日期 */
	public Date addHours(Date date, int amount);;

	/** 添加一个毫秒数为日期 */
	public Date addMilliseconds(Date date, int amount);;

	/** 增加了一个数分钟的日期 */
	public Date addMinutes(Date date, int amount);;

	/** 取得指定日期以后某月的日期。如果要得到以前月份的日期，参数用负数 */
	public Date addMonths(Date date, int amount);;

	/** 增加了一个秒数的日期 */
	public Date addSeconds(Date date, int amount);;

	/** 取得指定日期以后某周的日期。如果要得到以前周的日期，参数用负数 */
	public Date addWeeks(Date date, int amount);;

	/** 取得指定日期以后某年的日期。如果要得到以前年份的日期，参数用负数 */
	public Date addYears(Date date, int amount);;

	/** 计算已过去的天数,从哪儿开始算呢,根据第2个参数fragment来确定 */
	public long getFragmentInDays(Date date, int fragment);;

	/** 计算已过去的小时数,从哪儿开始算呢,根据第2个参数fragment来确定 */
	public long getFragmentInHours(Date date, int fragment);;

	/** 计算已过去的毫秒数,从哪儿开始算呢,根据第2个参数fragment来确定 */
	public long getFragmentInMilliseconds(Date date, int fragment);;

	/** 计算已过去的分钟数,从哪儿开始算呢,根据第2个参数fragment来确定 */
	public long getFragmentInMinutes(Date date, int fragment);;

	/** 计算已过去的秒数,从哪儿开始算呢,根据第2个参数fragment来确定 */
	public long getFragmentInSeconds(Date date, int fragment);;

	/** 两个日期对象是否相等（只比较年－月－日） */
	public boolean isSameDay(Date date1, Date date2);;

	/** 比较两个日期是否完全相等（精确到毫秒） */
	public boolean isSameInstant(Date date1, Date date2);;

	/** 两个日历对象是否表示相同的本地时间。 */
	public boolean isSameLocalTime(Calendar cal1, Calendar cal2);;

	/** 解析一个字符串,尝试各种不同的解析器的日期,使用默认的日期格式的符号进行了 */
	public Date parseDate(String str, Locale locale, String... parsePatterns);;

	/** 解析一个字符串,尝试各种不同的解析器的日期 */
	public Date parseDate(String str, String... parsePatterns);;

	/** 解析一个字符串,尝试各种不同的解析器的日期, 使用默认的日期格式的符号进行了现场 */
	public Date parseDateStrictly(String str, Locale locale,
			String... parsePatterns);;

	/** 解析一个字符串,尝试各种不同的解析器的日期 */
	public Date parseDateStrictly(String str, String... parsePatterns);;

	/** 设置当天日期返回一个新的对象(设置天数 ) */
	public Date setDays(Date date, int amount);;

	/** 设置时间日期字段返回一个新的对象 (设置当前小时数) */
	public Date setHours(Date date, int amount);;

	/** 设置时间日期字段返回一个新的约会对象 (设置毫秒数) */
	public Date setMilliseconds(Date date, int amount);;

	/** 设置时间日期字段返回日期返回一个新的对象(设置当前分钟数) */
	public Date setMinutes(Date date, int amount);;

	/** 设置字段返回日期返回一个新的对象(设置当前分钟数) */
	public Date setMonths(Date date, int amount);;

	/** 设置字段返回日期返回一个新的对象(设置当前秒数) */
	public Date setSeconds(Date date, int amount);;

	/** 设置字段返回日期返回一个新的对象(设置年分 修改传入时间年份为amount) */
	public Date setYears(Date date, int amount);;

	/** (时间日历类转换) */
	public Calendar toCalendar(Date date);

	/** 将日期按照任意范围截整，关键看第二个参数(MONTH、DATE、HOUR) */
	public Date truncate(Date date, int field);

	/** 将日期按照任意范围截整，关键看第二个参数 (MONTH、DATE、HOUR) */
	public Date truncate(Object date, int field);

	/** */
	public int truncatedCompareTo(Date date1, Date date2, int field);

	/** */
	public boolean truncatedEquals(Date date1, Date date2, int field);
}
