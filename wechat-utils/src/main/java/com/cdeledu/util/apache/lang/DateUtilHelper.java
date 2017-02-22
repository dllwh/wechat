package com.cdeledu.util.apache.lang;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;

/**
 * 
 * @ClassName: DateHelper
 * @Description:
 *               <ul>
 *               <li>日期时间工具类</li>
 *               <li>继承与org.apache.commons.lang3.time.DateUtils</li>
 *               </ul>
 * @author: 独泪了无痕
 * @date: 2015年8月28日 上午8:04:32
 * @version: V1.0
 * @history:
 */
public class DateUtilHelper extends DateUtils {
	/** -------------------------- 私有属性 begin ------------------------------- */
	/** 毫秒 */
	public final static long MS = 1;
	/** 每秒钟的毫秒数 */
	public final static long SECOND_MS = DateUtils.MILLIS_PER_SECOND;
	/** 每分钟的毫秒数 */
	public final static long MINUTE_MS = DateUtils.MILLIS_PER_MINUTE;
	/** 每小时的毫秒数 */
	public final static long HOUR_MS = DateUtils.MILLIS_PER_HOUR;
	/** 每天的毫秒数 */
	public final static long DAY_MS = DateUtils.MILLIS_PER_DAY;

	/** 时间格式01 */
	public final static String NORM_TIME_PATTERN_ONE = "yyyy-MM-dd HH:mm";
	/** 时间格式02 */
	public final static String NORM_TIME_PATTERN_TWO = "MM-dd HH:mm";
	/** 时间格式03 */
	public final static String NORM_TIME_PATTERN_THREE = "HH:mm";

	/** 标准日期格式 : 英文简写（默认） */
	public final static String NORM_DATE_PATTERN_ENG = "yyyy-MM-dd";
	/** 日期时间格式(中文简写) */
	public final static String NORM_DATE_PATTERN_CN = "yyyy年MM月dd";
	/** 标准时间格式(英文) */
	public final static String NORM_TIME_PATTERN_ENG = "HH:mm:ss";
	/** 标准时间格式 (中文) */
	public final static String NORM_TIME_PATTERN_CN = "HH时mm分ss秒";
	/** 标准日期时间格式 */
	public final static String NORM_DATETIME_PATTERN_ENG = "yyyy-MM-dd HH:mm:ss";
	/** 标准日期时间格式(中文全称) */
	public final static String NORM_DATETIME_PATTERN_CN = "yyyy年MM月dd日  HH时mm分ss秒";

	/** 标准日期（不含时间）格式化器 */
	private final static SimpleDateFormat NORM_DATE_FORMAT_ENG = new SimpleDateFormat(
			NORM_DATE_PATTERN_ENG);
	private final static SimpleDateFormat NORM_DATE_FORMAT_CN = new SimpleDateFormat(
			NORM_DATE_PATTERN_CN);
	/** 标准时间格式化器(英文) */
	private final static SimpleDateFormat NORM_TIME_FORMAT_ENG = new SimpleDateFormat(
			NORM_TIME_PATTERN_ENG);
	/** 标准时间格式化器(中文) */
	private final static SimpleDateFormat NORM_TIME_FORMAT_CN = new SimpleDateFormat(
			NORM_TIME_PATTERN_CN);
	/** 标准日期时间格式化器(英文) */
	private final static SimpleDateFormat NORM_DATETIME_FORMAT_ENG = new SimpleDateFormat(
			NORM_DATETIME_PATTERN_ENG);
	/** 标准日期时间格式化器(中文) */
	private final static SimpleDateFormat NORM_DATETIME_FORMAT_CN = new SimpleDateFormat(
			NORM_DATETIME_PATTERN_CN);

	/** -------------------------- 私有属性 end ------------------------------- */

	/** -------------------------- 公有方法 begin ------------------------------- */
	/**
	 * @方法:当前时间，格式 yyyy-MM-dd HH:mm:ss
	 * @创建人:独泪了无痕
	 * @return 当前时间的标准形式字符串
	 */
	public static String getCurrentTime() {
		return formatDateTime(new Date(), false);
	}

	/**
	 * @方法: 当前日期，格式 yyyy-MM-dd
	 * @创建人:独泪了无痕
	 * @return 当前日期的标准形式字符串
	 */
	public static String getCurrentDate() {
		return formatDate(new Date(), false);
	}

	/**
	 * @Description: 当前年份
	 * @author: 独泪了无痕
	 * @return
	 */
	public static int getCurrYear() {
		return Calendar.getInstance().get(Calendar.YEAR);
	}

	/**
	 * @方法:检查日期字符串是否符合格式定义
	 * @创建人:独泪了无痕
	 * @param dateStr
	 * @param format
	 * @return
	 */
	public static Boolean checkPattern(String dateStr, String format) {
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		try {
			formatter.parse(dateStr);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	/**
	 * 
	 * @方法:计算两个日期相差天数
	 * @创建人:独泪了无痕
	 * @param date1
	 *            减数日期
	 * @param date2
	 *            被减数日期
	 * @return long 返回类型
	 */
	public static long getDiffDays(Date date1, Date date2) {
		return (date1.getTime() - date2.getTime()) / DAY_MS;
	}

	/**
	 * @方法描述: 计算两个日期相差月份数
	 * @param date1
	 *            被减数日期
	 * @param date2
	 *            被减数日期
	 * @return
	 */
	public static Integer getDiffMonths(Date date1, Date date2) {
		Calendar calendar = Calendar.getInstance();

		// 得到第一个日期的年分和月份数
		calendar.setTime(date1);
		int yearOne = calendar.get(Calendar.YEAR);
		int monthOne = calendar.get(Calendar.MONDAY);

		// 得到第二个日期的年份和月份
		calendar.setTime(date2);
		int yearTwo = calendar.get(Calendar.YEAR);
		int monthTwo = calendar.get(Calendar.MONDAY);

		return (yearOne - yearTwo) * 12 + (monthOne - monthTwo);
	}

	/**
	 * @方法:产生周序列,即得到当前时间所在的年度是第几周
	 * @创建人:独泪了无痕
	 * @return
	 */
	public static String getSeqWeek() {
		String result = "当前时间所在是:";
		Calendar c = Calendar.getInstance(Locale.CHINA);
		String week = Integer.toString(c.get(Calendar.WEEK_OF_YEAR));
		if (week.length() == 1)
			week = "0" + week;
		String year = Integer.toString(c.get(Calendar.YEAR));
		return result + year + "年,第 " + week + "周";
	}

	/*----------------------------- Format(格式化) begin -----------------------------*/
	/**
	 * @方法:根据特定格式格式化日期
	 * @创建人:独泪了无痕
	 * @param date
	 *            被格式化的日期
	 * @param format
	 *            格式
	 * @return 格式化后的字符串
	 */
	public static String format(Date date, String format) {
		return new SimpleDateFormat(format).format(date);
	}

	/**
	 * 
	 * @方法描述:格式化日期时间
	 * @param date
	 *            被格式化的日期时间
	 * @param isCN
	 *            判断返回值是否需要是中文下的时间格式
	 * @return String 返回类型
	 */
	public static String formatDateTime(Date date, boolean isCN) {
		if (isCN)
			return NORM_DATETIME_FORMAT_CN.format(date);
		return NORM_DATETIME_FORMAT_ENG.format(date);
	}

	/**
	 * @param date
	 *            被格式化的日期
	 * @param isCN
	 *            判断返回值是否需要是中文下的时间格式
	 * @return 格式化后的字符串
	 */
	public static String formatDate(Date date, boolean isCN) {
		if (isCN)
			return NORM_DATE_FORMAT_CN.format(date);
		return NORM_DATE_FORMAT_ENG.format(date);
	}

	/*----------------------------- Format(格式化) end -------------------------------*/

	/*----------------------------- Parse(解析) begin -----------------------------*/
	/**
	 * @方法描述:将特定格式的日期转换为Date对象
	 * @param dateStr
	 *            特定格式的日期
	 * @param format
	 *            格式，例如yyyy-MM-dd
	 * @return 日期对象
	 */
	public static Date parse(String dateStr, String format) {
		try {
			return (new SimpleDateFormat(format)).parse(dateStr);
		} catch (ParseException e) {
			throw new RuntimeException("Parse " + dateStr + " with format " + format + " error!",
					e);
		}
	}

	/**
	 * @方法描述:转化日期时间
	 * @param source
	 *            标准形式的时间字符串
	 * @param isCN
	 * @return
	 * @return：Date 返回类型
	 */
	public static Date parseDateTime(String source, boolean isCN) {
		String pattern = "";
		try {
			if (isCN) {
				pattern = NORM_DATETIME_FORMAT_CN.toPattern();
				return NORM_DATETIME_FORMAT_CN.parse(source);
			} else {
				pattern = NORM_DATETIME_FORMAT_ENG.toPattern();
				return NORM_DATETIME_FORMAT_ENG.parse(source);
			}
		} catch (Exception e) {
			throw new RuntimeException("Parse " + source + " with format " + pattern + " error!",
					e);
		}
	}

	/**
	 * @方法描述:转化日期
	 * @param source
	 *            标准形式的时间字符串
	 * @param isCN
	 *            判断返回值是否需要是中文下的时间格式
	 * @return
	 */
	public static Date parseDate(String source, boolean isCN) {
		String pattern = "";
		try {
			if (isCN) {
				pattern = NORM_DATE_FORMAT_CN.toPattern();
				return NORM_DATE_FORMAT_CN.parse(source);
			} else {
				pattern = NORM_DATE_FORMAT_ENG.toPattern();
				return NORM_DATE_FORMAT_ENG.parse(source);
			}
		} catch (Exception e) {
			throw new RuntimeException("Parse " + source + " with format " + pattern + " error!",
					e);
		}
	}

	/**
	 * @方法描述:转化时间
	 * @param dateStr
	 * @param isCN
	 * @return Date 返回类型
	 */
	public static Date parseTime(String timeStr, boolean isCN) {
		String pattern = "";
		try {
			if (isCN) {
				pattern = NORM_TIME_FORMAT_CN.toPattern();
				return NORM_TIME_FORMAT_CN.parse(timeStr);
			} else {
				pattern = NORM_TIME_FORMAT_ENG.toPattern();
				return NORM_TIME_FORMAT_ENG.parse(timeStr);
			}
		} catch (Exception rte) {
			throw new RuntimeException("Parse " + timeStr + " with format " + pattern + " error!",
					rte);
		}
	}

	/*----------------------------- Parse(解析) end -------------------------------*/

	/*----------------------------- Offset begin -------------------------------*/
	/** 昨天 */
	public static Date yesterday() {
		return offsiteDate(new Date(), Calendar.DAY_OF_YEAR, -1);
	}

	/** 上周 */
	public static Date lastWeek() {
		return offsiteDate(new Date(), Calendar.WEEK_OF_YEAR, -1);
	}

	/** 上个月 */
	public static Date lastMouth() {
		return offsiteDate(new Date(), Calendar.MONTH, -1);
	}

	/**
	 * @方法描述: 获取指定日期偏移指定时间后的时间
	 * @param date
	 *            基准日期
	 * @param calendarField
	 *            偏移的粒度大小（小时、天、月等）使用Calendar中的常数
	 * @param offsite
	 *            偏移量，正数为向后偏移，负数为向前偏移
	 * @return 偏移后的日期
	 */
	public static Date offsiteDate(Date date, int calendarField, int offsite) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(calendarField, offsite);
		return cal.getTime();
	}

	/**
	 * @方法:获取某时间的开始毫秒数
	 * @创建人:独泪了无痕
	 * @param calendar
	 *            时间
	 * @param calendarField
	 *            类型，只支持年开始的秒数，月开始的秒数，日开始的秒数，小时开始的秒数，分钟开始的秒数
	 * @return 毫秒数
	 */
	public static long getSecoendOfBegin(Calendar calendar, int calendarField) {

		Calendar cal = calendar == null ? Calendar.getInstance() : (Calendar) calendar.clone();
		cal.set(Calendar.AM_PM, 0);

		switch (calendarField) {
		case Calendar.YEAR:
			cal.set(cal.get(Calendar.YEAR), 0, 1, 0, 0, 0);
			break;
		case Calendar.MONTH:
			cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), 1, 0, 0, 0);
			break;
		case Calendar.DATE:
			cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE), 0, 0,
					0);
			break;
		case Calendar.HOUR:
			cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE),
					cal.get(Calendar.HOUR), 0, 0);
			break;
		case Calendar.MINUTE:
			cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE),
					cal.get(Calendar.HOUR), cal.get(Calendar.MINUTE), 0);
			break;
		default:
			throw new RuntimeException("不支持的时间模式。");
		}

		cal.set(Calendar.MILLISECOND, 0);

		return cal.getTimeInMillis();
	}

	/**
	 * @方法:获取参数1指定时间类型的开始毫秒数
	 * @创建人:独泪了无痕
	 * @param calendarField
	 * @return
	 */
	public static long getSecoendOfBegin(int calendarField) {
		return getSecoendOfBegin(null, calendarField);
	}

	/**
	 * @方法描述: 获取前/后 N 天日期(M月d日)
	 * @author: 独泪了无痕
	 * @param diff
	 * @return
	 */
	public static String getMonthDay(int diff) {
		DateFormat df = new SimpleDateFormat("M月d日");
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DAY_OF_YEAR, diff);
		return df.format(c.getTime());
	}

	/**
	 * @方法描述: 整数(秒数)转换为时分秒格式(xx:xx:xx)
	 *        <ul>
	 *        <li>1.等于0时; 2.大于等于86400时</li>
	 *        </ul>
	 * @创建者: 皇族灬战狼
	 * @创建时间: 2016年3月7日 下午3:36:50
	 * @param time
	 * @return
	 */
	public static String secToTime(int time) {
		String hour = "00";
		String minute = "00";
		String second = "00";

		if (0 < time && time < 60) {
			second = StringUtils.substring("00" + time, -2);
		} else if (60 <= time && time < 3600) {
			minute = StringUtils.substring("00" + time / 60, -2);
			second = StringUtils.substring("00" + time % 60, -2);
		} else if (time >= 3600 && time < 86400) {
			hour = StringUtils.substring("00" + time / 3600, -2);
			minute = StringUtils.substring("00" + time % 3600 / 60, -2);
			second = StringUtils.substring("00" + time % 60, -2);
		}
		return hour + ":" + minute + ":" + second;
	}

	/**
	 * @方法描述: 根据日期获取星期
	 * @param strdate
	 * @return
	 */
	public static String getWeekDayByDate(String strdate) {
		final String dayNames[] = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
		SimpleDateFormat sdfInput = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		Date date = new Date();
		try {
			date = sdfInput.parse(strdate);
		} catch (ParseException e) {
			throw new RuntimeException("日期类型转换出错", e);
		}
		calendar.setTime(date);
		int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
		if (dayOfWeek < 0)
			dayOfWeek = 0;
		return dayNames[dayOfWeek];
	}

	/**
	 * @方法描述: 区分今天昨天前天
	 * @param createTime
	 * @return
	 */
	public static String parseNewDate(String createTime) {
		try {
			String ret = "";
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			long create = sdf.parse(createTime).getTime();
			Calendar now = Calendar.getInstance();
			long ms = 1000 * (now.get(Calendar.HOUR_OF_DAY) * 3600 + now.get(Calendar.MINUTE) * 60
					+ now.get(Calendar.SECOND));// 毫秒数
			long ms_now = now.getTimeInMillis();
			if (ms_now - create < ms) {
				ret = "今天";
			} else if (ms_now - create < (ms + 24 * 3600 * 1000)) {
				ret = "昨天";
			} else if (ms_now - create < (ms + 24 * 3600 * 1000 * 2)) {
				ret = "前天";
			} else {
				ret = "更早";
			}
			return ret;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/** -------------------------- 公有方法 end ------------------------------- */
}
